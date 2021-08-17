package com.mul.utils.glide;

import androidx.annotation.LongDef;

/**
 * @ProjectName: MulUtils
 * @Package: com.mul.utils.glide
 * @ClassName: GlideTransformationEnum
 * @Author: zdd
 * @CreateDate: 2021/8/16 10:28:57
 * @Description: glide圆角控制器
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/8/16 10:28:57
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@LongDef({GlideTransformationEnum.GTFE_DEFAULT, GlideTransformationEnum.CROP_TOP_LR, GlideTransformationEnum.CROP_BOTTOM_LR})
public @interface GlideTransformationEnum {
    long GTFE_DEFAULT = 200;//默认为所有的都有圆角
    long CROP_TOP_LR = 201;// 设置只显示顶部的两个圆角
    long CROP_BOTTOM_LR = 202;// 设置只显示底部的两个圆角
}
