package com.mul.utils;

import com.mul.utils.log.LogUtil;

import java.math.BigDecimal;

/**
 * @ProjectName: MulUtils
 * @Package: com.mul.utils
 * @ClassName: NumberUtils
 * @Author: zdd
 * @CreateDate: 2020/5/26 15:03
 * @Description: 数字运算工具类
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/26 15:03
 * @UpdateRemark: 更新说明
 * @Version: 1.0.0
 */
public class NumberUtils {
    /**
     * 加法运算
     *
     * @param var1 加数
     * @param var2 被加数
     */
    public static <T, V> String add(T var1, V var2) {
        return add(var1, var2, -1, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 加法运算
     *
     * @param var1 加数
     * @param var2 被加数
     */
    public static <T, V> String add(T var1, V var2, int scale) {
        return add(var1, var2, scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 加法运算
     *
     * @param var1         加数
     * @param var2         被加数
     * @param scale        保留小数点后位数 如果是-1则不限制位数
     * @param roundingMode 保留小数点模式
     */
    public static <T, V> String add(T var1, V var2, int scale, int roundingMode) {
//        if (var1 < var2) {
//            return String.format("%s", var1);
//        }
        if (!DataUtils.isBasicType(var1) && !DataUtils.isBasicType(var2)) {
            LogUtil.e("数字格式不正确");
            return "0";
        }
        BigDecimal bd1 = new BigDecimal(var1.toString());
        BigDecimal bd2 = new BigDecimal(var2.toString());
        if (scale == -1) {
            return bd1.add(bd2).toString();
        }
        int indexScale = scale + 2;
        String result = bd1.add(bd2).setScale(indexScale, roundingMode).toString();
        String data = result.substring(0, result.lastIndexOf(".") + (indexScale - 1));
        int index = data.lastIndexOf(".") + 1;
        if (index == data.length() || Integer.parseInt(data.substring(index)) == 0) {
            return data.substring(0, data.lastIndexOf("."));
        } else {
            return data;
        }
    }

    /**
     * 减法运算
     *
     * @param var1 加数
     * @param var2 被加数
     */
    public static <T, V> String subtract(T var1, V var2) {
        return subtract(var1, var2, -1, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 减法运算
     *
     * @param var1 加数
     * @param var2 被加数
     */
    public static <T, V> String subtract(T var1, V var2, int scale) {
        return subtract(var1, var2, scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 减法运算
     *
     * @param var1         加数
     * @param var2         被加数
     * @param scale        保留小数点后位数 如果是-1则不限制位数
     * @param roundingMode 保留小数点模式
     */
    public static <T, V> String subtract(T var1, V var2, int scale, int roundingMode) {
//        if (var1 < var2) {
//            return String.format("%s", var1);
//        }
        if (!DataUtils.isBasicType(var1) && !DataUtils.isBasicType(var2)) {
            LogUtil.e("数字格式不正确");
            return "0";
        }
        BigDecimal bd1 = new BigDecimal(var1.toString());
        BigDecimal bd2 = new BigDecimal(var2.toString());
        if (scale == -1) {
            return bd1.subtract(bd2).toString();
        }
        int indexScale = scale + 2;
        String result = bd1.subtract(bd2).setScale(indexScale, roundingMode).toString();
        String data = result.substring(0, result.lastIndexOf(".") + (indexScale - 1));
        int index = data.lastIndexOf(".") + 1;
        if (index == data.length() || Integer.parseInt(data.substring(index)) == 0) {
            return data.substring(0, data.lastIndexOf("."));
        } else {
            return data;
        }
    }

    /**
     * 乘法运算
     *
     * @param var1 加数
     * @param var2 被加数
     */
    public static <T, V> String multiply(T var1, V var2) {
        return multiply(var1, var2, -1, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 乘法运算
     *
     * @param var1 加数
     * @param var2 被加数
     */
    public static <T, V> String multiply(T var1, V var2, int scale) {
        return multiply(var1, var2, scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 乘法运算
     *
     * @param var1         加数
     * @param var2         被加数
     * @param scale        保留小数点后位数 如果是-1则不限制位数
     * @param roundingMode 保留小数点模式
     */
    public static <T, V> String multiply(T var1, V var2, int scale, int roundingMode) {
//        if (var1 < var2) {
//            return String.format("%s", var1);
//        }
        if (!DataUtils.isBasicType(var1) && !DataUtils.isBasicType(var2)) {
            LogUtil.e("数字格式不正确");
            return "0";
        }
        BigDecimal bd1 = new BigDecimal(var1.toString());
        BigDecimal bd2 = new BigDecimal(var2.toString());
        if (scale == -1) {
            return bd1.multiply(bd2).toString();
        }
        int indexScale = scale + 2;
        String result = bd1.multiply(bd2).setScale(indexScale, roundingMode).toString();
        String data = result.substring(0, result.lastIndexOf(".") + (indexScale - 1));
        int index = data.lastIndexOf(".") + 1;
        if (index == data.length() || Integer.parseInt(data.substring(index)) == 0) {
            return data.substring(0, data.lastIndexOf("."));
        } else {
            return data;
        }
    }

    /**
     * 除法运算
     *
     * @param var1 除数
     * @param var2 被除数
     */
    public static <T, V> String divide(T var1, V var2) {
        return divide(var1, var2, -1, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 除法运算
     *
     * @param var1  除数
     * @param var2  被除数
     * @param scale 保留小数点后位数
     */
    public static <T, V> String divide(T var1, V var2, int scale) {
        return divide(var1, var2, scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 除法运算
     *
     * @param var1         除数
     * @param var2         被除数
     * @param scale        保留小数点后位数 如果是-1则不限制位数
     * @param roundingMode 保留小数点模式
     */
    public static <T, V> String divide(T var1, V var2, int scale, int roundingMode) {
        if (!DataUtils.isBasicType(var1) && !DataUtils.isBasicType(var2)) {
            LogUtil.e("数字格式不正确");
            return "0";
        }

        if (DataUtils.isEmpty(var1) || DataUtils.isEmpty(var2)) {
            LogUtil.i("请填写正确数字");
            return "0";
        }

        if (DataUtils.isContainsOneToNine(var2.toString())) {
            LogUtil.i("除数不能为0");
            return "0";
        }
        BigDecimal bd1 = new BigDecimal(var1.toString());
        BigDecimal bd2 = new BigDecimal(var2.toString());

        if (scale == -1) {
            return bd1.divide(bd2, roundingMode).toString();
        }

        int indexScale = scale + 2;
        String result = bd1.divide(bd2, indexScale, roundingMode).toString();
        String data = result.substring(0, result.lastIndexOf(".") + (indexScale - 1));
        int index = data.lastIndexOf(".") + 1;
        if (index == data.length() || Integer.parseInt(data.substring(index)) == 0) {
            return data.substring(0, data.lastIndexOf("."));
        } else {
            return data;
        }
    }

    public static String numberConvertStr(int position) {
        String temp = "";
        switch (position) {
            case 0:
                temp = "一";
                break;
            case 1:
                temp = "二";
                break;
            case 2:
                temp = "三";
                break;
            case 3:
                temp = "四";
                break;
            case 4:
                temp = "五";
                break;
            case 5:
                temp = "六";
                break;
            case 6:
                temp = "七";
                break;
        }
        return temp;
    }
}
