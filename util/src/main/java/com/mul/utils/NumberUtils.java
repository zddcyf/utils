package com.mul.utils;

import com.mul.utils.log.LogUtil;

import java.math.BigDecimal;

/**
 * @ProjectName: utils
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
    public static String add(int var1, int var2) {
        if (var1 < var2) {
            return String.format("%s", var1);
        }
        BigDecimal bd1 = new BigDecimal(String.format("%s", var1));
        BigDecimal bd2 = new BigDecimal(String.format("%s", var2));
        String result = bd1.add(bd2).toString();
        return result;
    }

    /**
     * 加法运算
     *
     * @param var1 加数
     * @param var2 被加数
     */
    public static String add(double var1, double var2) {
        if (var1 < var2) {
            return String.format("%s", var1);
        }
        BigDecimal bd1 = new BigDecimal(String.format("%s", var1));
        BigDecimal bd2 = new BigDecimal(String.format("%s", var2));
        String result = bd1.add(bd2).toString();
        return result;
    }

    /**
     * 加法运算
     *
     * @param var1 加数
     * @param var2 被加数
     */
    public static String add(String var1, String var2) {
        if (!DataUtils.isBasicType(var1) && !DataUtils.isBasicType(var2)) {
            LogUtil.e("数字格式不正确");
            return "0";
        }

        BigDecimal bd1 = new BigDecimal(String.format("%s", var1));
        BigDecimal bd2 = new BigDecimal(String.format("%s", var2));
        String result = bd1.add(bd2).toString();
        return result;
    }

    /**
     * 减法运算
     *
     * @param var1 减数
     * @param var2 被减数
     */
    public static String subtract(int var1, int var2) {
        return subtract(var1, var2, -1);
    }

    /**
     * 减法运算
     *
     * @param var1  减数
     * @param var2  被减数
     * @param scale 保留小数点后位数
     */
    public static String subtract(int var1, int var2, int scale) {
        if (var1 < var2) {
            return String.format("%s", var1);
        }
        BigDecimal bd1 = new BigDecimal(String.format("%s", var1));
        BigDecimal bd2 = new BigDecimal(String.format("%s", var2));
        if (scale != -1) {
            int indexScale = scale + 1;
            String result = bd1.subtract(bd2).setScale(indexScale, BigDecimal.ROUND_HALF_UP).toString();
            return result.substring(0, result.indexOf(".") + indexScale);
        }
        return bd1.subtract(bd2).toString();
    }

    /**
     * 减法运算
     *
     * @param var1 减数
     * @param var2 被减数
     */
    public static String subtract(double var1, double var2) {
        return subtract(var1, var2, -1);
    }

    /**
     * 减法运算
     *
     * @param var1  减数
     * @param var2  被减数
     * @param scale 保留小数点后位数
     */
    public static String subtract(double var1, double var2, int scale) {
        if (var1 < var2) {
            return String.format("%s", var1);
        }
        BigDecimal bd1 = new BigDecimal(String.format("%s", var1));
        BigDecimal bd2 = new BigDecimal(String.format("%s", var2));
        if (scale != -1) {
            int indexScale = scale + 1;
            String result = bd1.subtract(bd2).setScale(indexScale, BigDecimal.ROUND_HALF_UP).toString();
            return result.substring(0, result.indexOf(".") + indexScale);
        }
        return bd1.subtract(bd2).toString();
    }

    /**
     * 减法运算
     *
     * @param var1  减数
     * @param var2  被减数
     */
    public static String subtract(String var1, String var2) {
        return subtract(var1, var2, -1);
    }

    /**
     * 减法运算
     *
     * @param var1  减数
     * @param var2  被减数
     * @param scale 保留小数点后位数
     */
    public static String subtract(String var1, String var2, int scale) {
        if (!DataUtils.isBasicType(var1) && !DataUtils.isBasicType(var2)) {
            LogUtil.e("数字格式不正确");
            return "0";
        }

        BigDecimal bd1 = new BigDecimal(String.format("%s", var1));
        BigDecimal bd2 = new BigDecimal(String.format("%s", var2));
        if (scale != -1) {
            int indexScale = scale + 1;
            String result = bd1.subtract(bd2).setScale(indexScale, BigDecimal.ROUND_HALF_UP).toString();
            return result.substring(0, result.indexOf(".") + indexScale);
        }
        return bd1.subtract(bd2).toString();
    }


    /**
     * 乘法运算
     *
     * @param var1  乘数
     * @param var2  被乘数
     * @param scale 保留小数点后位数
     */
    public static String multiply(int var1, int var2, int scale) {
        if (var1 < var2) {
            return String.format("%s", var1);
        }
        BigDecimal bd1 = new BigDecimal(String.format("%s", var1));
        BigDecimal bd2 = new BigDecimal(String.format("%s", var2));
        if (scale != -1) {
            int indexScale = scale + 1;
            String result = bd1.multiply(bd2).setScale(indexScale, BigDecimal.ROUND_HALF_UP).toString();
            if (scale == 0) {
                return result.substring(0, result.indexOf("."));
            }else {
                return result.substring(0, result.indexOf(".") + indexScale);
            }
        }
        return bd1.multiply(bd2).toString();
    }

    /**
     * 乘法运算
     *
     * @param var1  乘数
     * @param var2  被乘数
     * @param scale 保留小数点后位数
     */
    public static String multiply(double var1, double var2, int scale) {
        if (var1 < var2) {
            return String.format("%s", var1);
        }
        BigDecimal bd1 = new BigDecimal(String.format("%s", var1));
        BigDecimal bd2 = new BigDecimal(String.format("%s", var2));
        if (scale != -1) {
            int indexScale = scale + 1;
            String result = bd1.multiply(bd2).setScale(indexScale, BigDecimal.ROUND_HALF_UP).toString();
            if (scale == 0) {
                return result.substring(0, result.indexOf("."));
            }else {
                return result.substring(0, result.indexOf(".") + indexScale);
            }
        }
        return bd1.multiply(bd2).toString();
    }

    /**
     * 乘法运算
     *
     * @param var1  乘数
     * @param var2  被乘数
     * @param scale 保留小数点后位数
     */
    public static String multiply(String var1, String var2, int scale) {
        if (!DataUtils.isBasicType(var1) && !DataUtils.isBasicType(var2)) {
            LogUtil.e("数字格式不正确");
            return "0";
        }

        BigDecimal bd1 = new BigDecimal(var1);
        BigDecimal bd2 = new BigDecimal(var2);

        if (scale != -1) {
            int indexScale = scale + 1;
            String result = bd1.multiply(bd2).setScale(indexScale, BigDecimal.ROUND_HALF_UP).toString();
            if (scale == 0) {
                return result.substring(0, result.indexOf("."));
            }else {
                return result.substring(0, result.indexOf(".") + indexScale);
            }
        }
        return bd1.multiply(bd2).toString();
    }

    /**
     * 除法运算
     *
     * @param var1  除数
     * @param var2  被除数
     * @param scale 保留小数点后位数
     */
    public static String divide(int var1, int var2, int scale) {
        if (var2 <= 0) {
            return String.format("%s", var1);
        }
        BigDecimal bd1 = new BigDecimal(String.format("%s", var1));
        BigDecimal bd2 = new BigDecimal(String.format("%s", var2));

        int indexScale = scale + 1;
        String result = bd1.divide(bd2, indexScale, BigDecimal.ROUND_HALF_UP).toString();
        String data = result.substring(0, result.lastIndexOf(".") + indexScale);
        if (Integer.parseInt(data.substring(data.lastIndexOf(".") + 1)) == 0) {
            return data.substring(0, data.lastIndexOf("."));
        } else {
            return data;
        }
    }

    /**
     * 除法运算
     *
     * @param var1  除数
     * @param var2  被除数
     * @param scale 保留小数点后位数
     */
    public static String divide(double var1, double var2, int scale) {
        if (var1 < var2) {
            return String.format("%s", var1);
        }
        BigDecimal bd1 = new BigDecimal(String.format("%s", var1));
        BigDecimal bd2 = new BigDecimal(String.format("%s", var2));

        int indexScale = scale + 1;
        String result = bd1.divide(bd2, indexScale, BigDecimal.ROUND_HALF_UP).toString();
        String data = result.substring(0, result.lastIndexOf(".") + indexScale);
        if (Integer.parseInt(data.substring(data.lastIndexOf(".") + 1)) == 0) {
            return data.substring(0, data.lastIndexOf("."));
        } else {
            return data;
        }
    }

    /**
     * 除法运算
     *
     * @param var1  除数
     * @param var2  被除数
     * @param scale 保留小数点后位数
     */
    public static String divide(String var1, String var2, int scale) {
        if (!DataUtils.isBasicType(var1) && !DataUtils.isBasicType(var2)) {
            LogUtil.e("数字格式不正确");
            return "0";
        }

        BigDecimal bd1 = new BigDecimal(var1);
        BigDecimal bd2 = new BigDecimal(var2);

        /**
         * -1为小于
         * 0为等于
         * 1为大于
         */
        if (bd1.compareTo(bd2) < 0) {
            return var1;
        }

        int indexScale = scale + 1;
        String result = bd1.divide(bd2, indexScale, BigDecimal.ROUND_HALF_UP).toString();
        String data = result.substring(0, result.lastIndexOf(".") + indexScale);
        if (Integer.parseInt(data.substring(data.lastIndexOf(".") + 1)) == 0) {
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
