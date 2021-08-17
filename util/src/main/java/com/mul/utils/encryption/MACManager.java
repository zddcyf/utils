package com.mul.utils.encryption;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class MACManager {
    @SuppressLint("StaticFieldLeak")
    private static MACManager macManager;

    private final Context context;

    private MACManager(Context context) {
        this.context = context;
    }

    public static MACManager getInstance(Context context) {
        if (macManager == null) {
            macManager = new MACManager(context);
        }
        return macManager;
    }

    public String getLocalEthernetMacAddress() {
        String mac = null;
        try {
            Enumeration localEnumeration = NetworkInterface.getNetworkInterfaces();
            while (localEnumeration.hasMoreElements()) {
                NetworkInterface localNetworkInterface = (NetworkInterface) localEnumeration.nextElement();
                String interfaceName = localNetworkInterface.getDisplayName();
                if (interfaceName == null) {
                    continue;
                }
                if (interfaceName.equals("eth0")) {
                    // MACAddr = convertMac(localNetworkInterface
                    // .getHardwareAddress());
                    mac = convertToMac(localNetworkInterface.getHardwareAddress());
                    mac=mac.replaceAll(":", "").trim();
                    if (mac.startsWith("0:")) {
                        mac = "0" + mac;
                    }
                    break;
                }

            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return mac;
    }

    private static String convertToMac(byte[] mac) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mac.length; i++) {
            byte b = mac[i];
            int value = 0;
            if (b >= 0 && b <= 16) {
                value = b;
                sb.append("0" + Integer.toHexString(value));
            } else if (b > 16) {
                value = b;
                sb.append(Integer.toHexString(value));
            } else {
                value = 256 + b;
                sb.append(Integer.toHexString(value));
            }
            if (i != mac.length - 1) {
                sb.append(":");
            }
        }
        return sb.toString();
    }


    private boolean tryOpenMAC(WifiManager manager) {
        boolean softOpenWifi = false;
        int state = manager.getWifiState();
        if (state != WifiManager.WIFI_STATE_ENABLED && state != WifiManager.WIFI_STATE_ENABLING) {
            manager.setWifiEnabled(true);
            softOpenWifi = true;
        }
        return softOpenWifi;
    }

    //尝试关闭MAC
    private static void tryCloseMAC(WifiManager manager) {
        manager.setWifiEnabled(false);
    }

    //尝试获取MAC地址
    @SuppressLint("HardwareIds")
    private String tryGetMAC(WifiManager manager) {
        WifiInfo wifiInfo = manager.getConnectionInfo();
        if (wifiInfo == null || TextUtils.isEmpty(wifiInfo.getMacAddress())) {
            return null;
        }
        return wifiInfo.getMacAddress().replaceAll(":", "").trim();
    }

    //尝试读取MAC地址
    public String getMacFromDevice(int internal) {
        String mac;
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        mac = tryGetMAC(wifiManager);
        if (!TextUtils.isEmpty(mac)) {
            return mac;
        }
        //获取失败，尝试打开wifi获取
        boolean isOkWifi = tryOpenMAC(wifiManager);
        for (int index = 0; index < internal; index++) {
            //假设第一次没有成功，第二次做100毫秒的延迟。
            if (index != 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            mac = tryGetMAC(wifiManager);
            if (!TextUtils.isEmpty(mac)) {
                break;
            }
        }
        //尝试关闭wifi
        if (isOkWifi) {
            tryCloseMAC(wifiManager);
        }
        return mac;
    }
}
