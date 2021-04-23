package com.mul.utils.glide;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringDef;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.mul.utils.ScreenUtil;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * @ProjectName: youlinanzhuoyuanshengduan
 * @Package: com.wisdom.tdweilaiapp.utils.glide
 * @ClassName: CornerTransform
 * @Author: zdd
 * @CreateDate: 2019/12/11 20:30
 * @Description: glide图片展示工具
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/12/11 20:30
 * @UpdateRemark: 更新说明
 * @Version: 1.0.0
 */
public class GlideUtil {
    /**
     * glide展示样式控制器
     */
    @StringDef({GlideCropEnum.GCE_DEFAULT, GlideCropEnum.CIRCLE_CROP, GlideCropEnum.CENTER_CROP})
    public @interface GlideCropEnum {
        String GCE_DEFAULT = "100";// 默认样式。不设置
        String CIRCLE_CROP = "101";// 设置成圆形
        String CENTER_CROP = "102";// 中心裁剪
    }

    public static final long GTFE_DEFAULT = 200;//默认为所有的都有圆角
    public static final long CROP_TOP_LR = 201;// 设置只显示顶部的两个圆角
    public static final long CROP_BOTTOM_LR = 202;// 设置只显示底部的两个圆角

    /**
     * glide圆角控制器
     */
    @LongDef({GTFE_DEFAULT, CROP_TOP_LR, CROP_BOTTOM_LR})
    public @interface GlideTransformationEnum {
    }

    /**
     * 网络地址
     *
     * @param imageView view
     * @param url       图片地址
     */
    public static void load(ImageView imageView, String url) {
        load(imageView, url, null, -1, -1, -1, -1, GTFE_DEFAULT, GlideCropEnum.GCE_DEFAULT);
    }

    public static void load(ImageView imageView, String url, @DrawableRes int placeholderImg) {
        load(imageView, url, null, -1, placeholderImg, placeholderImg, -1, GTFE_DEFAULT, GlideCropEnum.GCE_DEFAULT);
    }

    public static void load(ImageView imageView, String url, int placeholderImg, int errorImg) {
        load(imageView, url, null, -1, placeholderImg, errorImg, -1, GTFE_DEFAULT, GlideCropEnum.GCE_DEFAULT);
    }

    public static void load(ImageView imageView, String url, @GlideCropEnum String... crops) {
        load(imageView, url, null, -1, -1, -1, -1, GTFE_DEFAULT, crops);
    }

    public static void load(ImageView imageView, String url, int placeholderImg, @GlideCropEnum String... crops) {
        load(imageView, url, null, -1, placeholderImg, placeholderImg, -1, GTFE_DEFAULT, crops);
    }

    public static void load(ImageView imageView, String url, int placeholderImg, int errorImg, @GlideCropEnum String... crops) {
        load(imageView, url, null, -1, placeholderImg, errorImg, -1, GTFE_DEFAULT, crops);
    }

    public static void load(ImageView imageView, int raduis, String url) {
        load(imageView, url, null, -1, -1, -1, raduis, GTFE_DEFAULT, GlideCropEnum.GCE_DEFAULT);
    }

    public static void load(ImageView imageView, int placeholderImg, int raduis, String url) {
        load(imageView, url, null, -1, placeholderImg, placeholderImg, raduis, GTFE_DEFAULT, GlideCropEnum.GCE_DEFAULT);
    }

    public static void load(ImageView imageView, int placeholderImg, int errorImg, int raduis, String url) {
        load(imageView, url, null, -1, placeholderImg, errorImg, raduis, GTFE_DEFAULT, GlideCropEnum.GCE_DEFAULT);
    }

    public static void load(ImageView imageView, int raduis, String url, @GlideCropEnum String... crops) {
        load(imageView, url, null, -1, -1, -1, raduis, GTFE_DEFAULT, crops);
    }

    public static void load(ImageView imageView, int placeholderImg, int raduis, String url, @GlideCropEnum String... crops) {
        load(imageView, url, null, -1, placeholderImg, placeholderImg, raduis, GTFE_DEFAULT, crops);
    }

    public static void load(ImageView imageView, int placeholderImg, int errorImg, int raduis, String url, @GlideCropEnum String... crops) {
        load(imageView, url, null, -1, placeholderImg, errorImg, raduis, GTFE_DEFAULT, crops);
    }

    public static void load(ImageView imageView, String url, @GlideTransformationEnum long glideTransformationEnum) {
        load(imageView, url, null, -1, -1, -1, -1, glideTransformationEnum, GlideCropEnum.GCE_DEFAULT);
    }

    public static void load(ImageView imageView, String url, int placeholderImg, @GlideTransformationEnum long glideTransformationEnum) {
        load(imageView, url, null, -1, placeholderImg, placeholderImg, -1, glideTransformationEnum, GlideCropEnum.GCE_DEFAULT);
    }

    public static void load(ImageView imageView, String url, int placeholderImg, int errorImg, @GlideTransformationEnum long glideTransformationEnum) {
        load(imageView, url, null, -1, placeholderImg, errorImg, -1, glideTransformationEnum, GlideCropEnum.GCE_DEFAULT);
    }

    public static void load(ImageView imageView, String url, @GlideTransformationEnum long glideTransformationEnum, @GlideCropEnum String... crops) {
        load(imageView, url, null, -1, -1, -1, -1, glideTransformationEnum, crops);
    }

    public static void load(ImageView imageView, String url, int placeholderImg, @GlideTransformationEnum long glideTransformationEnum, @GlideCropEnum String... crops) {
        load(imageView, url, null, -1, placeholderImg, placeholderImg, -1, glideTransformationEnum, crops);
    }

    public static void load(ImageView imageView, String url, int placeholderImg, int errorImg, @GlideTransformationEnum long glideTransformationEnum, @GlideCropEnum String... crops) {
        load(imageView, url, null, -1, placeholderImg, errorImg, -1, glideTransformationEnum, crops);
    }

    public static void load(ImageView imageView, int raduis, String url, @GlideTransformationEnum long glideTransformationEnum) {
        load(imageView, url, null, -1, -1, -1, raduis, glideTransformationEnum, GlideCropEnum.GCE_DEFAULT);
    }

    public static void load(ImageView imageView, int placeholderImg, int raduis, String url, @GlideTransformationEnum long glideTransformationEnum) {
        load(imageView, url, null, -1, placeholderImg, placeholderImg, raduis, glideTransformationEnum, GlideCropEnum.GCE_DEFAULT);
    }

    public static void load(ImageView imageView, int placeholderImg, int errorImg, int raduis, String url, @GlideTransformationEnum long glideTransformationEnum) {
        load(imageView, url, null, -1, placeholderImg, errorImg, raduis, glideTransformationEnum, GlideCropEnum.GCE_DEFAULT);
    }

    public static void load(ImageView imageView, int raduis, String url, @GlideTransformationEnum long glideTransformationEnum, @GlideCropEnum String... crops) {
        load(imageView, url, null, -1, -1, -1, raduis, glideTransformationEnum, crops);
    }

    public static void load(ImageView imageView, int placeholderImg, int raduis, String url, @GlideTransformationEnum long glideTransformationEnum, @GlideCropEnum String... crops) {
        load(imageView, url, null, -1, placeholderImg, placeholderImg, raduis, glideTransformationEnum, crops);
    }

    public static void load(ImageView imageView, int placeholderImg, int errorImg, int raduis, String url, @GlideTransformationEnum long glideTransformationEnum, @GlideCropEnum String... crops) {
        load(imageView, url, null, -1, placeholderImg, errorImg, raduis, glideTransformationEnum, crops);
    }

    /**
     * drawable地址
     *
     * @param imageView view
     * @param drawPath  图片路径
     */
    public static void load(ImageView imageView, int drawPath) {
        load(imageView, "", null, drawPath, -1, -1, -1, GTFE_DEFAULT, GlideCropEnum.GCE_DEFAULT);
    }

    public static void load(ImageView imageView, @GlideTransformationEnum long glideTransformationEnum, int raduis, int drawPath) {
        load(imageView, "", null, drawPath, -1, -1, raduis, glideTransformationEnum, GlideCropEnum.GCE_DEFAULT);
    }

    public static void load(ImageView imageView, int drawPath, int placeholderImg) {
        load(imageView, "", null, drawPath, placeholderImg, placeholderImg, -1, GTFE_DEFAULT, GlideCropEnum.GCE_DEFAULT);
    }

    public static void load(ImageView imageView, int drawPath, int placeholderImg, int errorImg) {
        load(imageView, "", null, drawPath, placeholderImg, errorImg, -1, GTFE_DEFAULT, GlideCropEnum.GCE_DEFAULT);
    }

    public static void load(ImageView imageView, int drawPath, @GlideCropEnum String... crops) {
        load(imageView, "", null, drawPath, -1, -1, -1, GTFE_DEFAULT, crops);
    }

    public static void load(ImageView imageView, int drawPath, int placeholderImg, @GlideCropEnum String... crops) {
        load(imageView, "", null, drawPath, placeholderImg, placeholderImg, -1, GTFE_DEFAULT, crops);
    }

    public static void load(ImageView imageView, int drawPath, int placeholderImg, int errorImg, @GlideCropEnum String... crops) {
        load(imageView, "", null, drawPath, placeholderImg, errorImg, -1, GTFE_DEFAULT, crops);
    }

    public static void load(ImageView imageView, int drawPath, @GlideTransformationEnum long glideTransformationEnum) {
        load(imageView, "", null, drawPath, -1, -1, -1, glideTransformationEnum, GlideCropEnum.GCE_DEFAULT);
    }

    public static void load(ImageView imageView, int drawPath, int placeholderImg, @GlideTransformationEnum long glideTransformationEnum) {
        load(imageView, "", null, drawPath, placeholderImg, placeholderImg, -1, glideTransformationEnum, GlideCropEnum.GCE_DEFAULT);
    }

    public static void load(ImageView imageView, int drawPath, int placeholderImg, int errorImg, @GlideTransformationEnum long glideTransformationEnum) {
        load(imageView, "", null, drawPath, placeholderImg, errorImg, -1, glideTransformationEnum, GlideCropEnum.GCE_DEFAULT);
    }

    public static void load(ImageView imageView, int drawPath, @GlideTransformationEnum long glideTransformationEnum, @GlideCropEnum String... crops) {
        load(imageView, "", null, drawPath, -1, -1, -1, glideTransformationEnum, crops);
    }

    public static void load(ImageView imageView, int drawPath, int placeholderImg, @GlideTransformationEnum long glideTransformationEnum, @GlideCropEnum String... crops) {
        load(imageView, "", null, drawPath, placeholderImg, placeholderImg, -1, glideTransformationEnum, crops);
    }

    public static void load(ImageView imageView, int drawPath, int placeholderImg, int errorImg, @GlideTransformationEnum long glideTransformationEnum, @GlideCropEnum String... crops) {
        load(imageView, "", null, drawPath, placeholderImg, errorImg, -1, glideTransformationEnum, crops);
    }

    /**
     * 本地文件
     *
     * @param imageView view
     * @param filePath  文件路径
     */
    public static void load(ImageView imageView, File filePath) {
        load(imageView, "", filePath, -1, -1, -1, -1, GTFE_DEFAULT, GlideCropEnum.GCE_DEFAULT);
    }

    public static void load(ImageView imageView, File filePath, int placeholderImg) {
        load(imageView, "", filePath, -1, placeholderImg, placeholderImg, -1, GTFE_DEFAULT, GlideCropEnum.GCE_DEFAULT);
    }

    public static void load(ImageView imageView, File filePath, int placeholderImg, int errorImg) {
        load(imageView, "", filePath, -1, placeholderImg, errorImg, -1, GTFE_DEFAULT, GlideCropEnum.GCE_DEFAULT);
    }

    public static void load(ImageView imageView, File filePath, @GlideCropEnum String... crops) {
        load(imageView, "", filePath, -1, -1, -1, -1, GTFE_DEFAULT, crops);
    }

    public static void load(ImageView imageView, File filePath, int placeholderImg, @GlideCropEnum String... crops) {
        load(imageView, "", filePath, -1, placeholderImg, placeholderImg, -1, GTFE_DEFAULT, crops);
    }

    public static void load(ImageView imageView, File filePath, int placeholderImg, int errorImg, @GlideCropEnum String... crops) {
        load(imageView, "", filePath, -1, placeholderImg, errorImg, -1, GTFE_DEFAULT, crops);
    }

    public static void load(ImageView imageView, int raduis, File filePath) {
        load(imageView, "", filePath, -1, -1, -1, raduis, GTFE_DEFAULT, GlideCropEnum.GCE_DEFAULT);
    }

    public static void load(ImageView imageView, int placeholderImg, int raduis, File filePath) {
        load(imageView, "", filePath, -1, placeholderImg, placeholderImg, raduis, GTFE_DEFAULT, GlideCropEnum.GCE_DEFAULT);
    }

    public static void load(ImageView imageView, int placeholderImg, int errorImg, int raduis, File filePath) {
        load(imageView, "", filePath, -1, placeholderImg, errorImg, raduis, GTFE_DEFAULT, GlideCropEnum.GCE_DEFAULT);
    }

    public static void load(ImageView imageView, int raduis, File filePath, @GlideCropEnum String... crops) {
        load(imageView, "", filePath, -1, -1, -1, raduis, GTFE_DEFAULT, crops);
    }

    public static void load(ImageView imageView, int placeholderImg, int raduis, File filePath, @GlideCropEnum String... crops) {
        load(imageView, "", filePath, -1, placeholderImg, placeholderImg, raduis, GTFE_DEFAULT, crops);
    }

    public static void load(ImageView imageView, int placeholderImg, int errorImg, int raduis, File filePath, @GlideCropEnum String... crops) {
        load(imageView, "", filePath, -1, placeholderImg, errorImg, raduis, GTFE_DEFAULT, crops);
    }

    public static void load(ImageView imageView, File filePath, @GlideTransformationEnum long glideTransformationEnum) {
        load(imageView, "", filePath, -1, -1, -1, -1, glideTransformationEnum, GlideCropEnum.GCE_DEFAULT);
    }

    public static void load(ImageView imageView, File filePath, int placeholderImg, @GlideTransformationEnum long glideTransformationEnum) {
        load(imageView, "", filePath, -1, placeholderImg, placeholderImg, -1, glideTransformationEnum, GlideCropEnum.GCE_DEFAULT);
    }

    public static void load(ImageView imageView, File filePath, int placeholderImg, int errorImg, @GlideTransformationEnum long glideTransformationEnum) {
        load(imageView, "", filePath, -1, placeholderImg, errorImg, -1, glideTransformationEnum, GlideCropEnum.GCE_DEFAULT);
    }

    public static void load(ImageView imageView, File filePath, @GlideTransformationEnum long glideTransformationEnum, @GlideCropEnum String... crops) {
        load(imageView, "", filePath, -1, -1, -1, -1, glideTransformationEnum, crops);
    }

    public static void load(ImageView imageView, File filePath, int placeholderImg, @GlideTransformationEnum long glideTransformationEnum, @GlideCropEnum String... crops) {
        load(imageView, "", filePath, -1, placeholderImg, placeholderImg, -1, glideTransformationEnum, crops);
    }

    public static void load(ImageView imageView, File filePath, int placeholderImg, int errorImg, @GlideTransformationEnum long glideTransformationEnum, @GlideCropEnum String... crops) {
        load(imageView, "", filePath, -1, placeholderImg, errorImg, -1, glideTransformationEnum, crops);
    }

    public static void load(ImageView imageView, int raduis, File filePath, @GlideTransformationEnum long glideTransformationEnum) {
        load(imageView, "", filePath, -1, -1, -1, raduis, glideTransformationEnum, GlideCropEnum.GCE_DEFAULT);
    }

    public static void load(ImageView imageView, int placeholderImg, int raduis, File filePath, @GlideTransformationEnum long glideTransformationEnum) {
        load(imageView, "", filePath, -1, placeholderImg, placeholderImg, raduis, glideTransformationEnum, GlideCropEnum.GCE_DEFAULT);
    }

    public static void load(ImageView imageView, int placeholderImg, int errorImg, int raduis, File filePath, @GlideTransformationEnum long glideTransformationEnum) {
        load(imageView, "", filePath, -1, placeholderImg, errorImg, raduis, glideTransformationEnum, GlideCropEnum.GCE_DEFAULT);
    }

    public static void load(ImageView imageView, int raduis, File filePath, @GlideTransformationEnum long glideTransformationEnum, @GlideCropEnum String... crops) {
        load(imageView, "", filePath, -1, -1, -1, raduis, glideTransformationEnum, crops);
    }

    public static void load(ImageView imageView, int placeholderImg, int raduis, File filePath, @GlideTransformationEnum long glideTransformationEnum, @GlideCropEnum String... crops) {
        load(imageView, "", filePath, -1, placeholderImg, placeholderImg, raduis, glideTransformationEnum, crops);
    }

    public static void load(ImageView imageView, int placeholderImg, int errorImg, int raduis, File filePath, @GlideTransformationEnum long glideTransformationEnum, @GlideCropEnum String... crops) {
        load(imageView, "", filePath, -1, placeholderImg, errorImg, raduis, glideTransformationEnum, crops);
    }

    /**
     * bitmap
     *
     * @param imageView view
     */
    public static void load(ImageView imageView, Bitmap mBitmap) {
        load(imageView, mBitmap, -1, -1, -1, GTFE_DEFAULT, GlideCropEnum.GCE_DEFAULT);
    }

    public static void load(ImageView imageView, Bitmap mBitmap, @DrawableRes int placeholderImg) {
        load(imageView, mBitmap, placeholderImg, placeholderImg, -1, GTFE_DEFAULT, GlideCropEnum.GCE_DEFAULT);
    }

    public static void load(ImageView imageView, Bitmap mBitmap, int placeholderImg, int errorImg) {
        load(imageView, mBitmap, placeholderImg, errorImg, -1, GTFE_DEFAULT, GlideCropEnum.GCE_DEFAULT);
    }

    public static void load(ImageView imageView, Bitmap mBitmap, @GlideCropEnum String... crops) {
        load(imageView, mBitmap, -1, -1, -1, GTFE_DEFAULT, crops);
    }

    public static void load(ImageView imageView, Bitmap mBitmap, int placeholderImg, @GlideCropEnum String... crops) {
        load(imageView, mBitmap, placeholderImg, placeholderImg, -1, GTFE_DEFAULT, crops);
    }

    public static void load(ImageView imageView, Bitmap mBitmap, int placeholderImg, int errorImg, @GlideCropEnum String... crops) {
        load(imageView, mBitmap, placeholderImg, errorImg, -1, GTFE_DEFAULT, crops);
    }

    public static void load(ImageView imageView, int raduis, Bitmap mBitmap) {
        load(imageView, mBitmap, -1, -1, raduis, GTFE_DEFAULT, GlideCropEnum.GCE_DEFAULT);
    }

    public static void load(ImageView imageView, int placeholderImg, int raduis, Bitmap mBitmap) {
        load(imageView, mBitmap, placeholderImg, placeholderImg, raduis, GTFE_DEFAULT, GlideCropEnum.GCE_DEFAULT);
    }

    public static void load(ImageView imageView, int placeholderImg, int errorImg, int raduis, Bitmap mBitmap) {
        load(imageView, mBitmap, placeholderImg, errorImg, raduis, GTFE_DEFAULT, GlideCropEnum.GCE_DEFAULT);
    }

    public static void load(ImageView imageView, int raduis, Bitmap mBitmap, @GlideCropEnum String... crops) {
        load(imageView, mBitmap, -1, -1, raduis, GTFE_DEFAULT, crops);
    }

    public static void load(ImageView imageView, int placeholderImg, int raduis, Bitmap mBitmap, @GlideCropEnum String... crops) {
        load(imageView, mBitmap, placeholderImg, placeholderImg, raduis, GTFE_DEFAULT, crops);
    }

    public static void load(ImageView imageView, int placeholderImg, int errorImg, int raduis, Bitmap mBitmap, @GlideCropEnum String... crops) {
        load(imageView, mBitmap, placeholderImg, errorImg, raduis, GTFE_DEFAULT, crops);
    }

    public static void load(ImageView imageView, Bitmap mBitmap, @GlideTransformationEnum long glideTransformationEnum) {
        load(imageView, mBitmap, -1, -1, -1, glideTransformationEnum, GlideCropEnum.GCE_DEFAULT);
    }

    public static void load(ImageView imageView, Bitmap mBitmap, int placeholderImg, @GlideTransformationEnum long glideTransformationEnum) {
        load(imageView, mBitmap, placeholderImg, placeholderImg, -1, glideTransformationEnum, GlideCropEnum.GCE_DEFAULT);
    }

    public static void load(ImageView imageView, Bitmap mBitmap, int placeholderImg, int errorImg, @GlideTransformationEnum long glideTransformationEnum) {
        load(imageView, mBitmap, placeholderImg, errorImg, -1, glideTransformationEnum, GlideCropEnum.GCE_DEFAULT);
    }

    public static void load(ImageView imageView, Bitmap mBitmap, @GlideTransformationEnum long glideTransformationEnum, @GlideCropEnum String... crops) {
        load(imageView, mBitmap, -1, -1, -1, glideTransformationEnum, crops);
    }

    public static void load(ImageView imageView, Bitmap mBitmap, int placeholderImg, @GlideTransformationEnum long glideTransformationEnum, @GlideCropEnum String... crops) {
        load(imageView, mBitmap, placeholderImg, placeholderImg, -1, glideTransformationEnum, crops);
    }

    public static void load(ImageView imageView, Bitmap mBitmap, int placeholderImg, int errorImg, @GlideTransformationEnum long glideTransformationEnum, @GlideCropEnum String... crops) {
        load(imageView, mBitmap, placeholderImg, errorImg, -1, glideTransformationEnum, crops);
    }

    public static void load(ImageView imageView, int raduis, Bitmap mBitmap, @GlideTransformationEnum long glideTransformationEnum) {
        load(imageView, mBitmap, -1, -1, raduis, glideTransformationEnum, GlideCropEnum.GCE_DEFAULT);
    }

    public static void load(ImageView imageView, int placeholderImg, int raduis, Bitmap mBitmap, @GlideTransformationEnum long glideTransformationEnum) {
        load(imageView, mBitmap, placeholderImg, placeholderImg, raduis, glideTransformationEnum, GlideCropEnum.GCE_DEFAULT);
    }

    public static void load(ImageView imageView, int placeholderImg, int errorImg, int raduis, Bitmap mBitmap, @GlideTransformationEnum long glideTransformationEnum) {
        load(imageView, mBitmap, placeholderImg, errorImg, raduis, glideTransformationEnum, GlideCropEnum.GCE_DEFAULT);
    }

    public static void load(ImageView imageView, int raduis, Bitmap mBitmap, @GlideTransformationEnum long glideTransformationEnum, @GlideCropEnum String... crops) {
        load(imageView, mBitmap, -1, -1, raduis, glideTransformationEnum, crops);
    }

    public static void load(ImageView imageView, int placeholderImg, int raduis, Bitmap mBitmap, @GlideTransformationEnum long glideTransformationEnum, @GlideCropEnum String... crops) {
        load(imageView, mBitmap, placeholderImg, placeholderImg, raduis, glideTransformationEnum, crops);
    }

    public static void load(ImageView imageView, int placeholderImg, int errorImg, int raduis, Bitmap mBitmap, @GlideTransformationEnum long glideTransformationEnum, @GlideCropEnum String... crops) {
        load(imageView, mBitmap, placeholderImg, errorImg, raduis, glideTransformationEnum, crops);
    }

    @SuppressLint("CheckResult")
    public static void load(ImageView imageView
            , String url
            , File filePath
            , int drawPath
            , int placeholderImg
            , int errorImg
            , int raduis
            , @GlideTransformationEnum long glideTransformationEnum
            , @GlideCropEnum String... crops) {
        Context mContext = imageView.getContext();
        if (null == mContext) {
            return;
        }

        if (mContext instanceof Activity && isDestroy((Activity) mContext)) {
            return;
        }

        RequestManager requestManager = Glide.with(mContext);
        RequestBuilder<Drawable> load;
        // 图片地址
        if (-1 != drawPath) {
            load = requestManager.load(drawPath);
        } else if (null != filePath) {
            load = requestManager.load(Uri.fromFile(filePath));
        } else if (!TextUtils.isEmpty(url)) {
            url = url.replace("http://", "https://");
            load = requestManager.load(url);
        } else {
            load = requestManager.load("");
        }

        if (-1 != raduis) {
            // .asBitmap()
            // .skipMemoryCache(true) 这两个属性必须使用。否则圆角不生效
            requestManager.asBitmap();
        }

        // 图片样式
        RequestOptions requestOptions = new RequestOptions();
        if (-1 != placeholderImg) {
            requestOptions.placeholder(placeholderImg);
        }
        if (-1 != errorImg) {
            requestOptions.error(errorImg);
        }
        List<String> glideCropEnums = Arrays.asList(crops);
        if (glideCropEnums.contains(GlideCropEnum.CENTER_CROP)) {
            requestOptions.centerCrop();
        }
        if (glideCropEnums.contains(GlideCropEnum.CIRCLE_CROP)) {
            requestOptions.circleCrop();
        }
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        requestOptions.dontAnimate();


        if (-1 != raduis) {
            // .asBitmap()
            // .skipMemoryCache(true) 这两个属性必须使用。否则圆角不生效
            CornerTransform transformation = new CornerTransform(imageView.getContext(), ScreenUtil.dpToPx(raduis));
            //只是绘制左上角和右上角圆角
            if (glideTransformationEnum == GTFE_DEFAULT) {
                transformation.setExceptCorner(false, false, false, false);
//                requestOptions.skipMemoryCache(true);
                if (glideCropEnums.contains(GlideCropEnum.CENTER_CROP)) {
                    requestOptions.transform(new CenterCrop(), new RoundedCorners(ScreenUtil.dpToPx(raduis)));
                }
                if (glideCropEnums.contains(GlideCropEnum.CIRCLE_CROP)) {
                    requestOptions.transform(new CircleCrop(), new RoundedCorners(ScreenUtil.dpToPx(raduis)));
                }
                if (glideCropEnums.contains(GlideCropEnum.GCE_DEFAULT)) {
                    requestOptions.transform(new CenterCrop(), new RoundedCorners(ScreenUtil.dpToPx(raduis)));
                }
            }
            if (glideTransformationEnum == CROP_TOP_LR) {
                transformation.setExceptCorner(false, false, true, true);
//                load.skipMemoryCache(true);
                load.transform(transformation);
            }
            if (glideTransformationEnum == CROP_BOTTOM_LR) {
                transformation.setExceptCorner(true, true, false, false);
//                load.skipMemoryCache(true);
                load.transform(transformation);
            }
        }

        load.apply(requestOptions);

//        ViewGroup.LayoutParams params = imageView.getLayoutParams();
//        if (null != params && params.width > 0 && params.height > 0) {
//            load.override(params.width, params.height);
//        }

        load.into(imageView);
    }

    @SuppressLint("CheckResult")
    public static void load(ImageView imageView
            , Bitmap mBitmap
            , int placeholderImg
            , int errorImg
            , int raduis
            , @GlideTransformationEnum long glideTransformationEnum
            , @GlideCropEnum String... crops) {
        Context mContext = imageView.getContext();
        if (null == mContext) {
            return;
        }

        if (mContext instanceof Activity && isDestroy((Activity) mContext)) {
            return;
        }

        RequestManager requestManager = Glide.with(mContext);
        RequestBuilder<Drawable> load;
        // 图片地址
        load = requestManager.load(mBitmap);

        if (-1 != raduis) {
            // .asBitmap()
            // .skipMemoryCache(true) 这两个属性必须使用。否则圆角不生效
            requestManager.asBitmap();
        }

        // 图片样式
        RequestOptions requestOptions = new RequestOptions();
        if (-1 != placeholderImg) {
            requestOptions.placeholder(placeholderImg);
        }
        if (-1 != errorImg) {
            requestOptions.error(errorImg);
        }
        List<String> glideCropEnums = Arrays.asList(crops);
        if (glideCropEnums.contains(GlideCropEnum.CENTER_CROP)) {
            requestOptions.centerCrop();
        }
        if (glideCropEnums.contains(GlideCropEnum.CIRCLE_CROP)) {
            requestOptions.circleCrop();
        }
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        requestOptions.dontAnimate();
        load.apply(requestOptions);

        if (-1 != raduis) {
            // .asBitmap()
            // .skipMemoryCache(true) 这两个属性必须使用。否则圆角不生效
            CornerTransform transformation = new CornerTransform(imageView.getContext(), ScreenUtil.dpToPx(raduis));
            //只是绘制左上角和右上角圆角
            if (glideTransformationEnum == GTFE_DEFAULT) {
                transformation.setExceptCorner(false, false, false, false);
            }
            if (glideTransformationEnum == CROP_TOP_LR) {
                transformation.setExceptCorner(false, false, true, true);
            }
            if (glideTransformationEnum == CROP_BOTTOM_LR) {
                transformation.setExceptCorner(true, true, false, false);
            }
//            load.skipMemoryCache(true);
            load.transform(transformation);
        }

        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        if (null != params && params.width > 0 && params.height > 0) {
            load.override(params.width, params.height);
        }

        load.into(imageView);
    }

    /**
     * 判断Activity是否Destroy
     *
     * @param mActivity 上下文
     * @return 是否存在
     */
    public static boolean isDestroy(Activity mActivity) {
        if (mActivity == null || mActivity.isFinishing() || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && mActivity.isDestroyed())) {
            return true;
        } else {
            return false;
        }
    }

    public static void load(ImageView imageView, String imageUrl, MySimpleTarget mySimpleTarget) {
        Context mContext = imageView.getContext();
        if (null == mContext) {
            return;
        }

        if (mContext instanceof Activity && isDestroy((Activity) mContext)) {
            return;
        }

        Glide.with(mContext).load(imageUrl).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                if (null != mySimpleTarget) {
                    mySimpleTarget.onResourceReady(resource);
                }
            }
        });
    }

    public interface MySimpleTarget {
        void onResourceReady(@NonNull Drawable resource);
    }
}
