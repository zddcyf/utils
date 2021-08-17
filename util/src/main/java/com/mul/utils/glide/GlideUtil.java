package com.mul.utils.glide;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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
 * @ProjectName: MulUtils
 * @Package: com.mul.utils.glide
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
    public static <T> void load(ImageView imageView, T imgUrl) {
        load(imageView, imgUrl, -1, -1, -1, GlideTransformationEnum.GTFE_DEFAULT, GlideCropEnum.GCE_DEFAULT);
    }

    public static <T> void load(ImageView imageView, T imgUrl, int placeholderImg) {
        load(imageView, imgUrl, placeholderImg, placeholderImg, -1, GlideTransformationEnum.GTFE_DEFAULT, GlideCropEnum.GCE_DEFAULT);
    }

    public static <T> void load(ImageView imageView, T imgUrl, int placeholderImg, int errorImg) {
        load(imageView, imgUrl, placeholderImg, errorImg, -1, GlideTransformationEnum.GTFE_DEFAULT, GlideCropEnum.GCE_DEFAULT);
    }

    public static <T> void load(ImageView imageView, T imgUrl, int placeholderImg, int errorImg, int radius) {
        load(imageView, imgUrl, placeholderImg, errorImg, radius, GlideTransformationEnum.GTFE_DEFAULT, GlideCropEnum.GCE_DEFAULT);
    }

    public static <T> void load(ImageView imageView, T imgUrl, int placeholderImg, int errorImg, int radius, @GlideTransformationEnum long glideTransformationEnum) {
        load(imageView, imgUrl, placeholderImg, errorImg, radius, glideTransformationEnum, GlideCropEnum.GCE_DEFAULT);
    }

    public static <T> void load(ImageView imageView, int radius, T imgUrl) {
        load(imageView, imgUrl, -1, -1, radius, GlideTransformationEnum.GTFE_DEFAULT, GlideCropEnum.GCE_DEFAULT);
    }

    public static <T> void load(ImageView imageView, int radius, T imgUrl, int placeholderImg) {
        load(imageView, imgUrl, placeholderImg, placeholderImg, radius, GlideTransformationEnum.GTFE_DEFAULT, GlideCropEnum.GCE_DEFAULT);
    }

    public static <T> void load(ImageView imageView, int radius, T imgUrl, int placeholderImg, int errorImg) {
        load(imageView, imgUrl, placeholderImg, errorImg, radius, GlideTransformationEnum.GTFE_DEFAULT, GlideCropEnum.GCE_DEFAULT);
    }

    public static <T> void load(ImageView imageView, int radius, T imgUrl, int placeholderImg, int errorImg, @GlideTransformationEnum long glideTransformationEnum) {
        load(imageView, imgUrl, placeholderImg, errorImg, radius, glideTransformationEnum, GlideCropEnum.GCE_DEFAULT);
    }

    public static <T> void load(ImageView imageView, int radius, T imgUrl, int placeholderImg, int errorImg, @GlideTransformationEnum long glideTransformationEnum, @GlideCropEnum String... crops) {
        load(imageView, imgUrl, placeholderImg, errorImg, radius, glideTransformationEnum, crops);
    }

    public static <T> void load(ImageView imageView, T imgUrl, @GlideCropEnum String... crops) {
        load(imageView, imgUrl, -1, -1, -1, GlideTransformationEnum.GTFE_DEFAULT, crops);
    }

    public static <T> void load(ImageView imageView, T imgUrl, int placeholderImg, @GlideCropEnum String... crops) {
        load(imageView, imgUrl, placeholderImg, placeholderImg, -1, GlideTransformationEnum.GTFE_DEFAULT, crops);
    }

    public static <T> void load(ImageView imageView, T imgUrl, int placeholderImg, int errorImg, @GlideCropEnum String... crops) {
        load(imageView, imgUrl, placeholderImg, errorImg, -1, GlideTransformationEnum.GTFE_DEFAULT, crops);
    }

    public static <T> void load(ImageView imageView, int radius, T imgUrl, int placeholderImg, int errorImg, @GlideCropEnum String... crops) {
        load(imageView, imgUrl, placeholderImg, errorImg, radius, GlideTransformationEnum.GTFE_DEFAULT, crops);
    }

    public static <T> void load(ImageView imageView, int radius, T imgUrl, @GlideTransformationEnum long glideTransformationEnum) {
        load(imageView, imgUrl, -1, -1, radius, glideTransformationEnum, GlideCropEnum.GCE_DEFAULT);
    }

    public static <T> void load(ImageView imageView, int radius, T imgUrl, int placeholderImg, @GlideTransformationEnum long glideTransformationEnum) {
        load(imageView, imgUrl, placeholderImg, placeholderImg, radius, glideTransformationEnum, GlideCropEnum.GCE_DEFAULT);
    }

    /**
     * 加载图片
     *
     * @param imageView               view
     * @param url                     图片地址
     * @param placeholderImg          缓存图片
     * @param errorImg                异常加载的图片
     * @param radius                  圆角度
     * @param glideTransformationEnum glide圆角控制器
     * @param crops                   glide展示样式控制器
     */
    @SuppressLint("CheckResult")
    public static <T> void load(ImageView imageView
            , T url
            , int placeholderImg
            , int errorImg
            , int radius
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
        if (url instanceof Integer && mContext.getResources().getResourceTypeName((Integer) url).contains("drawable")) {
            load = requestManager.load(url);
        } else if (url instanceof File) {
            load = requestManager.load(Uri.fromFile((File) url));
        } else if (url instanceof String) {
            load = requestManager.load(url.toString().replace("http://", "https://"));
        } else if (url instanceof Bitmap) {
            load = requestManager.load(url);
        } else {
            load = requestManager.load("");
        }

        if (-1 != radius) {
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

        if (-1 != radius) {
            // .asBitmap()
            // .skipMemoryCache(true) 这两个属性必须使用。否则圆角不生效
            CornerTransform transformation = new CornerTransform(imageView.getContext(), ScreenUtil.dpToPx(radius));
            //只是绘制左上角和右上角圆角
            if (glideTransformationEnum == GlideTransformationEnum.GTFE_DEFAULT) {
                transformation.setExceptCorner(false, false, false, false);
//                requestOptions.skipMemoryCache(true);
                if (glideCropEnums.contains(GlideCropEnum.CENTER_CROP)) {
                    requestOptions.transform(new CenterCrop(), new RoundedCorners(ScreenUtil.dpToPx(radius)));
                }
                if (glideCropEnums.contains(GlideCropEnum.CIRCLE_CROP)) {
                    requestOptions.transform(new CircleCrop(), new RoundedCorners(ScreenUtil.dpToPx(radius)));
                }
                if (glideCropEnums.contains(GlideCropEnum.GCE_DEFAULT)) {
                    requestOptions.transform(new CenterCrop(), new RoundedCorners(ScreenUtil.dpToPx(radius)));
                }
            }
            if (glideTransformationEnum == GlideTransformationEnum.CROP_TOP_LR) {
                transformation.setExceptCorner(false, false, true, true);
//                load.skipMemoryCache(true);
                load.transform(transformation);
            }
            if (glideTransformationEnum == GlideTransformationEnum.CROP_BOTTOM_LR) {
                transformation.setExceptCorner(true, true, false, false);
//                load.skipMemoryCache(true);
                load.transform(transformation);
            }
        }

        load.apply(requestOptions);

        if (url instanceof Bitmap) {
            ViewGroup.LayoutParams params = imageView.getLayoutParams();
            if (null != params && params.width > 0 && params.height > 0) {
                load.override(params.width, params.height);
            }
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
}
