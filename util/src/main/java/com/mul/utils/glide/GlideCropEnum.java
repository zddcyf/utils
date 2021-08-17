package com.mul.utils.glide;

import androidx.annotation.StringDef;

/**
 * @ProjectName: MulUtils
 * @Package: com.mul.utils.glide
 * @ClassName: GlideCropEnum
 * @Author: zdd
 * @CreateDate: 2021/8/16 10:28:07
 * @Description: glide展示样式控制器
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/8/16 10:28:07
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@StringDef({GlideCropEnum.GCE_DEFAULT, GlideCropEnum.CIRCLE_CROP, GlideCropEnum.CENTER_CROP})
public @interface GlideCropEnum {
    String GCE_DEFAULT = "100";// 默认样式。不设置
    String CIRCLE_CROP = "101";// 设置成圆形
    String CENTER_CROP = "102";// 中心裁剪
}
