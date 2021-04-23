package com.mul.utils.deivice;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;

import com.mul.utils.DataUtils;
import com.mul.utils.NumberUtils;
import com.mul.utils.StringUtils;
import com.mul.utils.log.LogUtil;
import com.mul.utils.manager.GlobalManager;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;

/**
 * @ProjectName: FaceIdentify
 * @Package: com.frame.base.manager.device
 * @ClassName: DeviceServiceManager
 * @Author: zdd
 * @CreateDate: 2020/11/11 10:36:32
 * @Description: 设备管理类
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/11/11 10:36:32
 * @UpdateRemark: 更新说明
 * @Version: 1.0.0
 */
public class DeviceManager {
    @SuppressLint("StaticFieldLeak")
    private static final DeviceManager DEVICE_SERVICE_MANAGER = new DeviceManager();
    private Context mContext;

    private DeviceManager() {
        mContext = GlobalManager.INSTANCE.context;
    }

    public static DeviceManager getInstance() {
        return DEVICE_SERVICE_MANAGER;
    }

    /**
     * 重启
     */
    public void reboot() {
        try {
            Runtime.getRuntime().exec("su");
            Runtime.getRuntime().exec("reboot");
        } catch (IOException ignored) {
        }
    }

    /**
     * 设备重启
     */
    public void deviceReset() {
        try {
            Process process = Runtime.getRuntime().exec("su");
            PrintWriter printWriter = new PrintWriter(process.getOutputStream());
            printWriter.println("echo \"--wipe_data\" > cache/recovery/command");
            printWriter.println("reboot recovery");
            printWriter.flush();
            printWriter.close();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    public String dump(String table, String db_path) {
        try {
            String sql = table == null ? "offcn.sql" : table.toLowerCase() + ".sql";
            String du = table == null ? "" : " " + table.toLowerCase();
            String output = Environment.getExternalStorageDirectory() + "/crash/sql/";
            File out = new File(output);
            if (!out.isDirectory()) {
                out.mkdirs();
            }
            output += sql;
            Process process = Runtime.getRuntime().exec("su");
            PrintWriter printWriter = new PrintWriter(process.getOutputStream());
            printWriter.println("sqlite3");
            printWriter.println(".open " + db_path);
            printWriter.println(".output " + output);
            printWriter.println(".dump" + du);
            printWriter.println(".quit");
            printWriter.flush();
            printWriter.close();
            process.waitFor();
            return output;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关机广播：shine.intent.action.SHUTDOWN(订制客户为延迟7s执行关机)
     */
    public void shutDownBroadCast() {
        sendBroadcast("shine.intent.action.SHUTDOWN");
    }

    /**
     * 定时开关机的时间精度是1分钟，为避免时间失效，建议设置的关机时间离当前时间2分钟及以上值，开机时间离关机设置时间在3分钟及以上
     * 间隔的值
     *
     * @param timeonArray  开机时间
     * @param timeoffArray 关机时间
     */
    public void shutDownBroadCast(int[] timeonArray, int[] timeoffArray) {
        //携带的数据格式为：
        if (DataUtils.isEmpty(timeonArray)) {
            timeonArray = new int[]{2017, 7, 13, 8, 30};//下次开机具体日期时间，即在2017.7.13 8:30开机
        }
        if (DataUtils.isEmpty(timeoffArray)) {
            timeoffArray = new int[]{2017, 7, 13, 12, 30};//下次关机的时间
        }
        Intent intent = new Intent();
        intent.setAction("android.intent.action.setpoweronoff");
        intent.putExtra("timeon", timeonArray);
        intent.putExtra("timeoff", timeoffArray);
        intent.putExtra("enable", true); //使能开关机，true为打开，false为关闭
        mContext.sendBroadcast(intent);
    }

    /**
     * 重启
     */
    public void rebootShell() {
        CommandResult mReboot = ShellUtils.execCommand("reboot", false);
        LogUtil.saveI("设备重启=successMsg::" + mReboot.successMsg + "errorMsg::" + mReboot.errorMsg + "result::" + mReboot.result);
    }

    /**
     * 定时重启
     *
     * @param hour   时，每天定时重启时间点，24⼩时制
     * @param min    分，例如这里是2点10分钟重启
     * @param enable true则允许，false则禁止
     */
    public void rebootBroadCast(int hour, int min, boolean enable) {
        LogUtil.saveI("设备定时重启：时=" + hour + ":分=" + min + ":enable=" + enable);
        Intent intent = new Intent("com.xbh.action.REBOOT_DAILY");
        intent.putExtra("hour", hour);
        intent.putExtra("min", min);
        intent.putExtra("enable", enable);
        mContext.sendBroadcast(intent);
    }

    /**
     * 开屏广播：android.intent.action.exitsleep
     */
    public void exitSleep() {
        sendBroadcast("android.intent.action.exitsleep");
    }

    /**
     * 关屏广播：android.intent.action.gotosleep
     */
    public void gotoSleep() {
        sendBroadcast("android.intent.action.gotosleep");
    }

    /**
     * 设置系统时间(按24⼩时)
     */
    public void setSystemTimeType24() {
        Calendar ca = Calendar.getInstance();
        ca.set(2018, 6 - 1, 18, 16, 21, 15); //设置系统时间2018/06/18 16:21:15(注意月份要减1)
        ca.getTimeInMillis();
        Intent intent = new Intent("com.android.lango.setsystemtime");
        intent.putExtra("time", "" + ca.getTimeInMillis()); //String 类型
        mContext.sendBroadcast(intent);
    }

    /**
     * APP静默安装
     *
     * @param apkUrl 传⼊需要安装的APP的绝对路径/xx/xx/xx.apk
     */
    public void installApp(String apkUrl) {
        // 发送该广播时会携带被安装APP的安装路径，传递如下：
        Intent intent = new Intent("com.android.lango.installapp");
        //传⼊需要安装的APP的绝对路径/xx/xx/xx.apk
        intent.putExtra("apppath", apkUrl);
        mContext.sendBroadcast(intent);
    }

    /**
     * 打开和关闭以太网
     *
     * @param flag ture开 false关
     */
    public void enableEthernet(boolean flag) {
        flagSendBroadcast(flag, "com.xbh.action.ENABLE_ETHERNET");
    }

    /**
     * 设置以太网为动态获取IP模式
     *
     * @param mode auto：设置以太网为动态获取IP模式 static：设置以太网静态IP
     */
    public void setEtherentMode(String mode) {
        setEtherentMode(mode);
    }

    /**
     * 设置以太网IP模式
     *
     * @param mode    auto：设置以太网为动态获取IP模式     static：设置以太网静态IP
     * @param ip      192.168.1.100
     * @param gateway 192.168.1.1
     * @param netMask 255.255.255.0
     * @param dns1    8.8.8.8
     * @param dns2    4.4.4.4
     */
    public void setEtherentMode(String mode
            , String ip
            , String gateway
            , String netMask
            , String dns1
            , String dns2) {
        Intent intent = new Intent("com.xbh.action.SET_ETHERNET_MODE");
        intent.putExtra("mode", mode);
        if (mode.equals("static")) {
            intent.putExtra("ip", ip);
            intent.putExtra("gateway", gateway);
            intent.putExtra("netMask", netMask);
            intent.putExtra("dns1", dns1);
            intent.putExtra("dns2", dns2);
        }
        mContext.sendBroadcast(intent);
    }

    /**
     * 获取网络信息
     */
    public void getNetInfo() {
        //首先发送以下广播:
        Intent intent = new Intent("com.xbh.action.GET_NET_INFO");
        mContext.sendBroadcast(intent);
        //系统返回以下广播，请在APP中接收"com.xbh.action.NET_INFO"
        //注意,如网络未链接,返回type为"null"，并且其他信息为空
        if (intent.getAction().equals("com.xbh.action.NET_INFO")) {
            String type = intent.getStringExtra("type");//wifi,mobile,eth
            String mode = intent.getStringExtra("mode");//static, dhcp
            String ip = intent.getStringExtra("ip");
            String gateway = intent.getStringExtra("gateway");
            String netmask = intent.getStringExtra("netmask");
            String dns1 = intent.getStringExtra("dns1");
            String dns2 = intent.getStringExtra("dns2");
        }
    }

    /**
     * 保存开机日志
     *
     * @param on 为true则开机保存，为false则开机不保存(只保存最后5次开机日志)
     */
    public void setLogSave(boolean on) {
        Process process = null;
        String cmd = "setprop persist.sys.saveSystemLog xxx";
        try {
            if (on) {
                cmd = cmd.replace("xxx", "1");
            } else {
                cmd = cmd.replace("xxx", "0");
            }
            process = Runtime.getRuntime().exec(cmd);
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (process != null) {
                process.destroy();
            }
        }
    }

    /**
     * 设置守护进程
     *
     * @param pkg 为要守护的app包名，取消守护传参空字符 ""
     */
    public void setAppKeepLive(String pkg) {
        Process process = null;
        String cmd1 = "setprop persist.lgo.nooperateStartPkg xxx";
        String cmd2 = "setprop persist.lgo.keepNopAliveTimeSec xxx";
        try {
            if (DataUtils.isEmpty(pkg)) {
                cmd1 = cmd1.replace("xxx", "");
                cmd2 = cmd2.replace("xxx", "-1");
            } else {
                cmd1 = cmd1.replace("xxx", pkg);
                cmd2 = cmd2.replace("xxx", "15");
            }
            process = Runtime.getRuntime().exec(cmd1);
            process = Runtime.getRuntime().exec(cmd2);
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (process != null) {
                process.destroy();
            }
        }
    }

    /**
     * 设置控制台日志等级
     *
     * @param consoleLoglevel 0~15
     */
    public void setConsoleLoglevel(int consoleLoglevel) {
        Intent intent = new Intent("com.xbh.action.SET_CONSOLE_LOGLEVEL");
//        intent.putExtra("console_loglevel","7"); // 0~15
        intent.putExtra("console_loglevel", StringUtils.parseString(consoleLoglevel)); // 0~15
        mContext.sendBroadcast(intent);
    }

    /**
     * 设置背光
     *
     * @param brightness 0~100
     */
    public void setBacklight(int brightness) {
        Intent intent = new Intent("com.xbh.action.SET_BACKLIGHT");
//        intent.putExtra("brightness",100); // 0~100
        intent.putExtra("brightness", brightness); // 0~100
        mContext.sendBroadcast(intent);
    }

    /**
     * 获取客户型号/系统版本号/机器序列号
     * 客户型号 key值 ： ro.product.customer.model
     * 系统版本号key值： ro.build.date.ver
     * 机器序列号： ro.serialno
     * key：客户型号和系统版本号 key值
     * <p>
     * defaultValue ： 如果对应的key为没有值时会返回该值
     */
    public String getProperty(String key, String defaultValue) {
        String value = defaultValue;
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class, String.class);
            value = (String) (get.invoke(c, key, defaultValue));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 获取CPU ID
     */
    public String getFormattedCpuSerial() {
        String str = "";
        String strCpu = "";
        String cpuSerial = "0000000000000000";
        try {//read cpu serial
            Process pp = Runtime.getRuntime().exec("cat /proc/cpuinfo");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            for (; null != str; ) {
                str = input.readLine();
                if (str != null) {
                    if (str.indexOf("Serial") > -1) { // find cpu serial where a line
                        strCpu = str.substring(str.indexOf(":") + 1, str.length()); //extract cpu serial
                        cpuSerial = strCpu.trim(); // remove the blank
                        break;
                    }
                } else {
                    break;// end of file
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        LogUtil.saveD("获取CPU ID = getFormattedCpuSerial: " + cpuSerial);
        return cpuSerial;
    }

    /**
     * 获取wifi mac, 为android标准方法，wifi为打开状态时才能获取，另需要权限android.permission.ACCESS_WIFI_STATE
     */
    public String getWifiMAC() {
        WifiManager wifi = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        String macAddress = info.getMacAddress();
        if (macAddress != null) {
            LogUtil.saveD("getWifiMAC[" + macAddress + "]");
        }
        return macAddress;
    }

    /**
     * 获取以太网MAC，内部方法, 也可自行采用android标准方法
     */
    public String getEthMacAddress2() {
        String macString = "";
        Class<?> class1;
        try {
            class1 = Class.forName("android.app.XbhManager");

            Method getTipMethod3 = class1.getDeclaredMethod("getDefault");
            Object result_3 = getTipMethod3.invoke(null);

            Method method = result_3.getClass().getDeclaredMethod("getEthMac");
            macString = (String) method.invoke(result_3);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        LogUtil.saveD("获取以太网MAC，内部方法, 也可自行采用android标准方法 = getEthMacAddress2: " + macString);
        return macString;
    }

    /**
     * 获取系统版本信息
     * 获取取到的版本号与系统显示的"设置->关于设备->版本号"相同。
     *
     * @return
     */
    public String getSystemDisplayVersion() {
        return android.os.Build.DISPLAY;
    }

    /**
     * 设置是否禁止用户手动安装APK
     *
     * @param flag true则禁止，false则允许
     */
    public void isForbidInstallApp(boolean flag) {
        flagSendBroadcast(flag, "com.xbh.action.FORBID_INSTALL_APP");
    }

    /**
     * 设置是否禁止用户手动卸载APK
     *
     * @param flag true则禁止，false则允许
     */
    public void isForbidUninstallApp(boolean flag) {
        flagSendBroadcast(flag, "com.xbh.action.FORBID_UNINSTALL_APP");
    }

    /**
     * 设置是否触摸屏切换开关
     *
     * @param flag true则禁止，false则允许
     */
    public void isTouchscreenEn(boolean flag) {
        flagSendBroadcast(flag, "com.xbh.action.TOUCHSCREEN_EN");
    }

    /**
     * 设置是否Home键使能开关
     *
     * @param flag true则禁止，false则允许
     */
    public void isKeycodeHomeEn(boolean flag) {
        flagSendBroadcast(flag, "com.xbh.action.KEYCODE_HOME_EN");
    }

    /**
     * 设置是否Back键使能开关
     *
     * @param flag true则禁止，false则允许
     */
    public void isKeycodeBackEn(boolean flag) {
        flagSendBroadcast(flag, "com.xbh.action.KEYCODE_BACK_EN");
    }

    /**
     * 媒体音量
     *
     * @param volume 原始音量
     */
    public void setMediaVolume(double volume) {
        AudioManager mAudioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
        int max = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        double mV = Double.parseDouble(NumberUtils.multiply(NumberUtils.divide(max, 100, 2), volume + "", 2));
        int index;
        if (volume == -1) {
            index = (int) Math.round(max / 2.0);
//        } else if (volume > 0 && volume < 1) {
        } else if (volume >= 0 && volume < 1) {
//            index = (int) Math.round(max * volume);
            index = 0;
//        } else if (volume > max) {
        } else {
            if (mV < 1) {
                index = 1;
            } else {
                index = (int) mV;
            }
        }
//        else {
//            index = (int) volume;
//        }
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, index, 0);
        LogUtil.saveI("服务器端音量：" + volume + "当前媒体音量：" + index + ",最大媒体音量：" + max);
    }

    /**
     * 设备音量
     *
     * @param volume 原始音量
     */
    public void setNotiVolume(double volume) {
        AudioManager mAudioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
        int max = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM);
        double mV = Double.parseDouble(NumberUtils.multiply(NumberUtils.divide(max, 100, 2), volume + "", 2));
        int index;
        if (volume == -1) {
            index = (int) Math.round(max / 2.0);
        } else if (volume >= 0 && volume < 1) {
//            index = (int) Math.round(max * volume);
            index = 0;
//        } else if (volume > max) {
        } else {
//            index = Integer.parseInt(NumberUtils.multiply(NumberUtils.divide(max, 100, 2), volume + "", 0));
            if (mV < 1) {
                index = 1;
            } else {
                index = (int) mV;
            }
        }
//        else {
//            index = (int) volume;
//        }
        mAudioManager.setStreamVolume(AudioManager.STREAM_ALARM, index, 0);
        LogUtil.saveI("服务器端音量：" + volume + "当前媒体音量：" + index + ",最大媒体音量：" + max);
    }

    /**
     * @param mAction action
     */
    private void sendBroadcast(String mAction) {
        LogUtil.saveI("sendBroadcast：mAction=" + mAction);
        Intent intent = new Intent(mAction);
        mContext.sendBroadcast(intent);
    }

    private void flagSendBroadcast(boolean flag, String mAction) {
        LogUtil.saveI("flagSendBroadcast：flag=" + flag + ":mAction=" + mAction);
        Intent intent = new Intent(mAction);
        intent.putExtra("flag", flag);//true则允许，false则禁止
        mContext.sendBroadcast(intent);
    }

    /**
     * 拨打电话
     *
     * @param phoneNumber 手机号
     */
    public static void dialPhone(String phoneNumber) {
        Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));//跳转到拨号界面，同时传递电话号码
        GlobalManager.INSTANCE.currentActivity().startActivity(dialIntent);
    }
}
