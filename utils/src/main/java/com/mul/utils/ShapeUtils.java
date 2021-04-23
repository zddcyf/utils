package com.mul.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

/**
 * @ProjectName: youlinanzhuoyuanshengduan
 * @Package: com.wisdom.tdweilaiapp.utils
 * @ClassName: ShapeUtils
 * @Author: zdd
 * @CreateDate: 2020/3/4 8:43
 * @Description: 画圆工具类
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/3/4 8:43
 * @UpdateRemark: 更新说明
 * @Version: 1.0.0
 */
public class ShapeUtils {
    public static GradientDrawable getShape(Context mContext, int colorId) {
        return getShape(mContext, -1, colorId, -1, -1, "", "", GradientDrawable.RECTANGLE);
    }

    public static GradientDrawable getShape(Context mContext, int radius, int colorId) {
        return getShape(mContext, radius, colorId, -1, -1, "", "", GradientDrawable.RECTANGLE);
    }

    public static GradientDrawable getShape(Context mContext, int radius, int colorId, int strokeWidth, int strokeColorBg) {
        return getShape(mContext, radius, colorId, strokeWidth, strokeColorBg, "", "", GradientDrawable.RECTANGLE);
    }

    public static GradientDrawable getShape(String colorStr) {
        return getShape(null, -1, -1, -1, -1, colorStr, colorStr, GradientDrawable.RECTANGLE);
    }

    public static GradientDrawable getShape(int radius, String colorStr) {
        return getShape(null, radius, -1, -1, -1, colorStr, colorStr, GradientDrawable.RECTANGLE);
    }

    public static GradientDrawable getShape(int radius, String colorStr, int strokeWidth, String strokeColorStr) {
        return getShape(null, radius, -1, strokeWidth, -1, colorStr, strokeColorStr, GradientDrawable.RECTANGLE);
    }

    public static GradientDrawable getShape(int shape, Context mContext, int colorId) {
        return getShape(mContext, -1, colorId, -1, -1, "", "", shape);
    }

    public static GradientDrawable getShape(int shape, Context mContext, int radius, int colorId) {
        return getShape(mContext, radius, colorId, -1, -1, "", "", shape);
    }

    public static GradientDrawable getShape(int shape, Context mContext, int radius, int colorId, int strokeWidth, int strokeColorBg) {
        return getShape(mContext, radius, colorId, strokeWidth, strokeColorBg, "", "", shape);
    }

    public static GradientDrawable getShape(String colorStr, int shape) {
        return getShape(null, -1, -1, -1, -1, colorStr, colorStr, shape);
    }

    public static GradientDrawable getShape(int radius, String colorStr, int shape) {
        return getShape(null, radius, -1, -1, -1, colorStr, colorStr, shape);
    }

    public static GradientDrawable getShape(int radius, String colorStr, int strokeWidth, String strokeColorStr, int shape) {
        return getShape(null, radius, -1, strokeWidth, -1, colorStr, strokeColorStr, shape);
    }

    /**
     * 创建背景图
     *
     * @param mContext       上下文。非必须。不传无法使用id的color字段
     * @param radius         圆角度。需要用dp值转换成培训
     * @param colorId        背景色id
     * @param strokeWidth    外边框的宽度
     * @param strokeColorBg  外边框的背景色id
     * @param colorStr       背景色（#ff00ff）
     * @param strokeColorStr 外边框的背景色（#ff00ff）
     * @param shape          背景色的形状shape的形状，默认为矩形，可以设置为矩形（rectangle）、椭圆形(oval)、线(line)、环形(ring)
     * @return
     */
    private static GradientDrawable getShape(Context mContext, int radius, int colorId, int strokeWidth, int strokeColorBg
            , String colorStr, String strokeColorStr, int shape) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(shape);
        if (radius != -1) {
            drawable.setCornerRadius(radius);
        }

        if (null != mContext) {
            drawable.setColor(mContext.getResources().getColor(colorId));
            if (strokeWidth != -1) {
                drawable.setStroke(strokeWidth, mContext.getResources().getColor(strokeColorBg));
            }
        } else {
            drawable.setColor(Color.parseColor(colorStr));
            if (strokeWidth != -1) {
                drawable.setStroke(strokeWidth, Color.parseColor(strokeColorStr));
            }
        }
        return drawable;
    }
}
