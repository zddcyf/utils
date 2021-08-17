package com.mul.utils;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;

import com.mul.utils.log.LogUtil;
import com.mul.utils.manager.GlobalManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.channels.FileChannel;

/**
 * @ProjectName: MulUtils
 * @Package: com.mul.utils
 * @ClassName: FileAccessor
 * @Author: zdd
 * @CreateDate: 2019/12/2 15:09
 * @Description: 文件工具类
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/12/2 15:09
 * @UpdateRemark: 更新说明
 * @Version: 1.0.0
 */
public enum FileAccessor {
    INSTANCE;
    public String CHAIN_FILE = "";
    public String FILE_FILE_CRASH;
    public String FILE_FILE_CRASH_LOG;
    public String FILE_FILE_CRASH_ZIP;
    public String FILE_IMAGE = "image";
    public String FILE_IMAGE_FACE = "image/face";
    public String FILE_AD = "ad";
    public String FILE_IMAGE_COMPRESS = "compress";
    public String FILE_IMAGE_COMPRESS_MEET = "compressmeet";
    public String FILE_VIDEO = "video";
    public String FILE_FILE = "file";
    public String FILE_NEUCORE_NB = "neucore/nb";
    public String FILE_AUDIO = "audio";
    public String FILE_APK = "apk";
    public String FILE_DOWNLOAD_AD_PATH; // 需要传递给下载apk的下载器
    public String FILE_DOWNLOAD_APK_PATH; // 需要传递给下载apk的下载器

    /**
     * 获取文件大小
     */
    private FileChannel fc;
    private double fileSize;

    public void createFile(String directory) {
        StringBuilder sb = new StringBuilder();
        FILE_DOWNLOAD_AD_PATH = StringUtils.parseString(directory, "/ad");
        FILE_DOWNLOAD_APK_PATH = StringUtils.parseString(directory, "/apk");
        CHAIN_FILE = String.format("%s%s%s", getExternalStorePath(), File.separator, directory);
        File chainFile = new File(CHAIN_FILE);
        if (!chainFile.exists()) {
            boolean mMkdirs = chainFile.mkdirs();
            sb.append(chainFile.getAbsolutePath()).append("=").append(mMkdirs).append("\n");
        }
        FILE_FILE_CRASH = String.format("%s%s%s", CHAIN_FILE, File.separator, "crach");
        File mFileCrash = new File(FILE_FILE_CRASH);
        if (!mFileCrash.exists()) {
            boolean mMkdirs = mFileCrash.mkdirs();
            sb.append(mFileCrash.getAbsolutePath()).append("=").append(mMkdirs).append("\n");
        }

        FILE_FILE_CRASH_LOG = String.format("%s%s%s", FILE_FILE_CRASH, File.separator, "log");
        File mFileCraskLog = new File(FILE_FILE_CRASH_LOG);
        if (!mFileCraskLog.exists()) {
            boolean mMkdirs = mFileCraskLog.mkdirs();
            sb.append(mFileCraskLog.getAbsolutePath()).append("=").append(mMkdirs).append("\n");
        }

        FILE_FILE_CRASH_ZIP = String.format("%s%s%s", FILE_FILE_CRASH, File.separator, "zip");
        File mFileCrashZip = new File(FILE_FILE_CRASH_ZIP);
        if (!mFileCrashZip.exists()) {
            boolean mMkdirs = mFileCrashZip.mkdirs();
            sb.append(mFileCrashZip.getAbsolutePath()).append("=").append(mMkdirs).append("\n");
        } else {
            // 删除zip下的目录
            deleteFilesByDirectory(mFileCrashZip);
        }

        FILE_IMAGE = String.format("%s%s%s", CHAIN_FILE, File.separator, "image");
        File image = new File(FILE_IMAGE);
        if (!image.exists()) {
            boolean mMkdirs = image.mkdirs();
            sb.append(image.getAbsolutePath()).append("=").append(mMkdirs).append("\n");
        }

        FILE_IMAGE_FACE = String.format("%s%s%s", FILE_IMAGE, File.separator, "face");
        File imageFace = new File(FILE_IMAGE_FACE);
        if (!imageFace.exists()) {
            boolean mMkdirs = imageFace.mkdirs();
            sb.append(imageFace.getAbsolutePath()).append("=").append(mMkdirs).append("\n");
        }

        FILE_AD = String.format("%s%s%s", CHAIN_FILE, File.separator, "ad");
        File ad = new File(FILE_AD);
        if (!ad.exists()) {
            boolean mMkdirs = ad.mkdirs();
            sb.append(ad.getAbsolutePath()).append("=").append(mMkdirs).append("\n");
        }
//        else {
//            // 每次重启都删除广告
//            deleteFilesByDirectory(ad);
//        }

        FILE_IMAGE_COMPRESS = String.format("%s%s%s", CHAIN_FILE, File.separator, "compress");
        File imageCompress = new File(FILE_IMAGE_COMPRESS);
        if (!imageCompress.exists()) {
            boolean mMkdirs = imageCompress.mkdirs();
            sb.append(imageCompress.getAbsolutePath()).append("=").append(mMkdirs).append("\n");
        }
        FILE_IMAGE_COMPRESS_MEET = String.format("%s%s%s", CHAIN_FILE, File.separator, "compressmeet");
        File imageCompressmeet = new File(FILE_IMAGE_COMPRESS_MEET);
        if (!imageCompressmeet.exists()) {
            boolean mMkdirs = imageCompressmeet.mkdirs();
            sb.append(imageCompressmeet.getAbsolutePath()).append("=").append(mMkdirs).append("\n");
        }

        FILE_VIDEO = String.format("%s%s%s", CHAIN_FILE, File.separator, "video");
        File video = new File(FILE_VIDEO);
        if (!video.exists()) {
            boolean mMkdirs = video.mkdirs();
            sb.append(video.getAbsolutePath()).append("=").append(mMkdirs).append("\n");
        }

        FILE_FILE = String.format("%s%s%s", CHAIN_FILE, File.separator, "file");
        File file = new File(FILE_FILE);
        if (!file.exists()) {
            boolean mMkdirs = file.mkdirs();
            sb.append(file.getAbsolutePath()).append("=").append(mMkdirs).append("\n");
        }

        FILE_NEUCORE_NB = String.format("%s%s%s", CHAIN_FILE, File.separator, "neucore/nb");
        File fileNeucoreNb = new File(FILE_NEUCORE_NB);
        if (!fileNeucoreNb.exists()) {
            boolean mMkdirs = fileNeucoreNb.mkdirs();
            sb.append(fileNeucoreNb.getAbsolutePath()).append("=").append(mMkdirs).append("\n");
        }
        FILE_AUDIO = String.format("%s%s%s", CHAIN_FILE, File.separator, "audio");
        File audio = new File(FILE_AUDIO);
        if (!audio.exists()) {
            boolean mMkdirs = audio.mkdirs();
            sb.append(audio.getAbsolutePath()).append("=").append(mMkdirs).append("\n");
        }
        FILE_APK = String.format("%s%s%s", CHAIN_FILE, File.separator, "apk");
//        File mExternalFilesDir = GlobalManager.INSTANCE.context.getExternalFilesDir();
        FILE_APK = String.format("%s%s%s", CHAIN_FILE, File.separator, "apk");
        File apk = new File(FILE_APK);
        if (!apk.exists()) {
            boolean mMkdirs = apk.mkdirs();
            sb.append(apk.getAbsolutePath()).append("=").append(mMkdirs).append("\n");
        } else {
            // 删除zip下的目录
            deleteFilesByDirectory(apk);
        }
        if (!DataUtils.isEmpty(sb.toString())) {
            LogUtil.saveI(sb.toString());
        }
    }

    /**
     * 是否存在外置存储卡
     *
     * @return true有 false没有
     */
    public boolean isExistExternalStore() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    /**
     * 外置存储卡的路径
     *
     * @return 外置存储卡的路径
     */
    public String getExternalStorePath() {
        if (isExistExternalStore()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return null;
    }

    /**
     * 通知系统已添加文件
     *
     * @param url 添加文件路径
     */
    public void sendNotice(String url) {
        GlobalManager.INSTANCE.context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                Uri.parse("file://" + url)));
//                Uri.parse("file://" + APPS_ROOT_DIR)));
    }

    /**
     * 删除本地文件
     */
    public void deleteFile() {
        deleteFilesByDirectory(new File(CHAIN_FILE));
    }

    /**
     * 删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理
     */
    public void deleteFilesByDirectory(String file) {
        deleteFilesByDirectory(new File(file));
    }

    /**
     * 删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理
     */
    public void deleteFilesByDirectory(File file) {
        if (file != null && file.exists() && file.isDirectory()) {
            for (File item : file.listFiles()) {
                if (item.isDirectory()) {
                    deleteFilesByDirectory(item);
                } else {
                    boolean mDelete = item.delete();
                    LogUtil.saveI("文件" + item.getAbsolutePath() + "删除" + mDelete);
                }
            }
        }
    }

    public void deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            boolean mDelete = file.delete();
            LogUtil.saveI("文件" + file.getAbsolutePath() + "删除" + mDelete);
        }
    }

    public String obtainFileSize() {
        fileSize = 0;
        return MathUtils.fileSizeInt(obtainFileSizeUtil(new File(CHAIN_FILE)));
    }

    /**
     * 查询到本地缓存的大小
     */
    public double obtainFileSizeUtil(File file) {
        if (file != null && file.exists() && file.isDirectory()) {
            for (File item : file.listFiles()) {
                if (item.isDirectory()) {
                    obtainFileSizeUtil(item);
                } else {
                    try {
                        FileInputStream fis = new FileInputStream(item);
                        fc = fis.getChannel();
                        fileSize += fc.size();
                    } catch (IOException e) {
                        LogUtil.saveE("获取文件大小异常:" + e.toString());
                    } finally {
                        if (null != fc) {
                            try {
                                fc.close();
                            } catch (IOException e) {
                                LogUtil.saveE("获取文件大小finally关闭异常：" + e.toString());
                            }
                        }
                    }
                }
            }
        }
        return fileSize;
    }

    public String parseFile(File file) {
        return parseFile(file, null, "");
    }

    public String parseFile(AssetManager assetManager, String fileName) {
        return parseFile(null, assetManager, fileName);
    }

    /**
     * 将文件中的json串读取出来
     *
     * @param file     文件路径
     * @param fileName 文件名字
     * @return 返回文件内容信息
     */
    public String parseFile(File file, AssetManager assetManager, String fileName) {
        InputStream stream = null;
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        try {
            if (null == file) {
                stream = assetManager.open(fileName);
            } else {
                stream = new FileInputStream(file);
            }
            br = new BufferedReader(new InputStreamReader(stream));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != br) {
                    br.close();
                }
                if (null != stream) {
                    stream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    /**
     * 保存bitmap到本地,这里使用质量压缩
     *
     * @param mBitmap  图片
     * @param filePath 文件路径
     * @param fileName 文件名字
     * @return 返回图片路径
     */
    public String saveBitmap(Bitmap mBitmap, String filePath, String fileName) {
        return saveBitmap(mBitmap, filePath, fileName, 50);
    }

    /**
     * 保存bitmap到本地,这里使用质量压缩
     *
     * @param mBitmap
     * @param filePath 文件路径
     * @param fileName 文件名字
     * @param quality  文件压缩质量
     * @return 返回图片路径
     */
    public String saveBitmap(Bitmap mBitmap, String filePath, String fileName, int quality) {
        if (DataUtils.isEmpty(mBitmap)) {
            return "";
        }
        File filePic;
        try {
            filePic = new File(filePath, fileName);
            if (!filePic.exists()) {
                filePic.getParentFile().mkdirs();
                filePic.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(filePic);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, quality, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            // Auto-generated catch block
            e.printStackTrace();
            return null;
        }

        return filePic.getAbsolutePath();
    }

    /**
     * 压缩图片使用,采用BitmapFactory.decodeFile。这里是尺寸压缩
     */
    public Bitmap bitmapFactory(String filePath, String fileName) {
        return bitmapFactory(StringUtils.parseString(filePath, "/", fileName), 200, 200);
    }

    /**
     * 压缩图片使用,采用BitmapFactory.decodeFile。这里是尺寸压缩
     *
     * @param fileUrl   文件路径
     * @param reqWidth  我们期望的图片的宽，单位px
     * @param reqHeight 我们期望的图片的高，单位px
     * @return 返回一个处理好的bitmap
     */
    public Bitmap bitmapFactory(String fileUrl, int reqWidth, int reqHeight) {
        // 配置压缩的参数
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true; //获取当前图片的边界大小，而不是将整张图片载入在内存中，避免内存溢出
        BitmapFactory.decodeFile(fileUrl, options);
        options.inJustDecodeBounds = false;
        // inSampleSize的作用就是可以把图片的长短缩小inSampleSize倍，所占内存缩小inSampleSize的平方
        options.inSampleSize = caculateSampleSize(options, reqWidth, reqHeight);
        return BitmapFactory.decodeFile(fileUrl, options); // 解码文件
    }

    /**
     * 计算出所需要压缩的大小
     *
     * @param options   图片配置信息
     * @param reqWidth  我们期望的图片的宽，单位px
     * @param reqHeight 我们期望的图片的高，单位px
     * @return 返回压缩比例
     */
    private int caculateSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int sampleSize = 1;
        int picWidth = options.outWidth;
        int picHeight = options.outHeight;
        if (picWidth > reqWidth || picHeight > reqHeight) {
            int halfPicWidth = picWidth / 2;
            int halfPicHeight = picHeight / 2;
            while (halfPicWidth / sampleSize > reqWidth || halfPicHeight / sampleSize > reqHeight) {
                sampleSize *= 2;
            }
        }
        return sampleSize;
    }
}
