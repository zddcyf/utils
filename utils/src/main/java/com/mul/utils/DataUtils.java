package com.mul.utils;

import android.text.Editable;
import android.text.TextUtils;

import com.mul.utils.log.LogExceptionResult;
import com.mul.utils.log.LogUtil;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @ProjectName: youlinanzhuoyuanshengduan
 * @Package: com.wisdom.tdweilaiapp.utils
 * @ClassName: DataUtils
 * @Author: zdd
 * @CreateDate: 2020/5/18 15:07
 * @Description: 数据判断工具类
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/18 15:07
 * @UpdateRemark: 更新说明
 * @Version: 1.0.0
 */
public class DataUtils {
    public static boolean isEmpty(String data) {
        return TextUtils.isEmpty(data);
    }

    public static boolean isEmpty(Editable data) {
        return TextUtils.isEmpty(data);
    }

    public static boolean isEmpty(Object data) {
        return null == data || data.equals("");
    }

    public static boolean isEmpty(List data) {
        return null == data || data.size() == 0;
    }

    public static boolean isEmpty(Map data) {
        return null == data || data.size() == 0;
    }

    public static boolean isEmpty(byte[] data) {
        return null == data || data.length == 0;
    }

    public static boolean equals(String data, String data1) {
        return TextUtils.equals(data, data1);
    }

    public static boolean equalsIgnoreCase(String data, String data1) {
        return data.equalsIgnoreCase(data1);
    }

    /**
     * 判断是否为数字 true为是 false为不是
     *
     * @param data 源数据
     * @return 是否是纯数字
     */
    public static boolean isNumber(String data) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(data).matches();
    }

    /**
     * 判断是否基本类型 true为基本类型 false为非基本类型
     *
     * @param object 源数据
     * @return 是否是基本数据类型
     */
    public static boolean isBasicType(Object object) {
        if (null == object) {
            return false;
        }
        try {
            Field field = object.getClass().getField("TYPE");
            Class clz = (Class) field.get(null);
            return clz.isPrimitive();
        } catch (Exception e) {
            if (object instanceof String) {
                LogUtil.saveI("object=" + object);
                return true;
            } else {
                LogUtil.saveE("object=" + object + ",mE=" + LogExceptionResult.getException(e));
                return false;
            }
        }
    }

    public static int parseInt(String string) {
        int result = -1;
        try {
            result = Integer.parseInt(string);
        } catch (Exception e) {
            LogUtil.saveE("string=" + string + ",mE=" + LogExceptionResult.getException(e));
        }
        return result;
    }
}
