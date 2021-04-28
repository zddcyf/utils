package com.mul.utils;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import androidx.annotation.RequiresApi;
import androidx.core.content.FileProvider;

import com.mul.utils.deivice.DeviceManager;
import com.mul.utils.manager.GlobalManager;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ProjectName: utils
 * @Package: com.mul.utils
 * @ClassName: AppInstall
 * @Author: zdd
 * @CreateDate: 2021/4/15 9:44:30
 * @Description: APP的工具类（安装、卸载、杀死进程）
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/15 9:44:30
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ApkUtil {
    private static final ApkUtil APK_UTIL = new ApkUtil();

    /**
     * 是否静默安装 true为是 false为不是
     */
    private boolean isDirectInstall = false;
    /**
     * 位置来源回调值
     */
    private int actionManageUnknownAppSources = 10005;

    private ApkUtil() {
    }

    public static ApkUtil get() {
        return APK_UTIL;
    }

    public ApkUtil setDirectInstall(boolean isDirectInstall) {
        this.isDirectInstall = isDirectInstall;
        return this;
    }

    public ApkUtil setActionManageUnknownAppSources(int actionManageUnknownAppSources) {
        this.actionManageUnknownAppSources = actionManageUnknownAppSources;
        return this;
    }

    /**
     * 结束当前进程
     * 替换了main中的System.exit(0);
     */
    public void kill() {
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    public void installApk(String fileUrl, String fileName) {
        File apkFile = new File(fileUrl, fileName);
        if (!apkFile.exists()) {
            return;
        }
        if (isDirectInstall) {
            if (install(apkFile.getAbsolutePath())) {
                DeviceManager.getInstance().reboot();
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startInstallO(apkFile);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            startInstallN(apkFile);
        } else {
            startInstall(apkFile);
        }
    }

    /**
     * 静默安装 必须获取root权限才可使用
     *
     * @return 是否安装成功
     */
    private boolean install(String apk) {
        try {
            Process process = Runtime.getRuntime().exec("su");
            PrintWriter PrintWriter = new PrintWriter(process.getOutputStream());
            PrintWriter.println("pm install -r " + apk);
            PrintWriter.flush();
            PrintWriter.close();
            int value = process.waitFor();
            return value == 0;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * android1.x-6.x
     *
     * @param apkFile 需要安装的文件
     */
    private void startInstall(File apkFile) {
        Intent install = new Intent(Intent.ACTION_VIEW);
        install.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
        install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        GlobalManager.INSTANCE.currentActivity().startActivity(install);
    }

    /**
     * android7.x
     *
     * @param apkFile 需要安装的文件
     */
    private void startInstallN(File apkFile) {
        //参数1 上下文, 参数2 在AndroidManifest中的android:authorities值, 参数3  共享的文件
        Uri apkUri = FileProvider.getUriForFile(GlobalManager.INSTANCE.context, "com.jolly.edu.fileprovider", apkFile);
        Intent install = new Intent(Intent.ACTION_VIEW);
        //由于没有在Activity环境下启动Activity,设置下面的标签
        install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //添加这一句表示对目标应用临时授权该Uri所代表的文件
        install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        install.setDataAndType(apkUri, "application/vnd.android.package-archive");
        GlobalManager.INSTANCE.currentActivity().startActivity(install);
    }

    /**
     * android8.x
     * actionManageUnknownAppSources在onActivityResult回调中使用
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startInstallO(File apkFile) {
        boolean isGranted = GlobalManager.INSTANCE.context.getPackageManager().canRequestPackageInstalls();
        if (isGranted) startInstallN(apkFile);//安装应用的逻辑(写自己的就可以)
        else {
            Uri packageURI = Uri.parse("package:" + GlobalManager.INSTANCE.context.getPackageName());
            Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI);
            GlobalManager.INSTANCE.currentActivity().startActivityForResult(intent, actionManageUnknownAppSources);
        }
    }

    /**
     * 卸载
     */
    public void uninstall() {
        clearApp();
        shutdown();
    }

    /**
     * 清理app数据
     */
    public void clearApp() {
        FileAccessor.INSTANCE.deleteFilesByDirectory(new File("data/data/" + GlobalManager.INSTANCE.context.getPackageName()));
        // import com.sdk.tdweilai.basex.BaseX;
        // import com.sdk.tdweilai.basex.utils.FileUtil;
        // FileUtil.DeleteAll(new File("data/data/" + BaseX.mContext().getPackageName()));
    }

    /**
     * 关机
     */
    public void shutdown() {
        try {
            Runtime.getRuntime().exec("su");
            Runtime.getRuntime().exec("reboot -p");
        } catch (IOException ignored) {
        }
    }
}
