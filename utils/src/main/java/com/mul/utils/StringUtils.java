package com.mul.utils;

/**
 * @ProjectName: youlinanzhuoyuanshengduan
 * @Package: com.wisdom.tdweilaiapp.util
 * @ClassName: com.mul.libcommon.StringUtils
 * @Author: zdd
 * @CreateDate: 2019/12/9 15:03
 * @Description: 转换成字符串的工具类
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/12/9 15:03
 * @UpdateRemark: 更新说明
 * @Version: 1.0.0
 */
public class StringUtils {
    public static String parseString(Object... data) {
        StringBuffer sb = new StringBuffer();
        if (null == data) {
            return "";
        }
        for (Object obj : data) {
            if (null == obj) {
                sb.append("");
            } else {
                sb.append(obj);
            }
        }
        return sb.toString();
    }
}
