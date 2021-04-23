package com.mul.utils;

import java.math.BigDecimal;

/**
 * Created by lihuiqiang on 17/5/3.
 * for:数学转化工具类
 */

public class MathUtils {
    /**
     * 去除字符串 没有的小数点 和 0
     */
    public static String removeZero(String s) {
        if (s.indexOf("") > 0) {
            //正则表达
            s = s.replaceAll("0+?$", "");//去掉后面无用的零
            return s.replaceAll("[.]$", "");//如小数点后面全是零则去掉小数点
        }
        return s;
    }

    /**
     * 将B向上转化的方法
     *
     * @param fileSize
     * @return
     */
    public static String fileSize(double fileSize) {
        String size = null;
        double dSize;
        if (fileSize <= 0) {
//            size = "0M";
            size = "";
        } else if (fileSize > 0 && fileSize < 1024) {
            size = fileSize + "B";
        } else {
            dSize = fileSize / 1024;
            if (dSize < 1024) {
                size = formatNumber(dSize, 4, 2) + "KB";
            } else {
                dSize = formatNumber(dSize, 4, 3) / 1024;
                if (dSize < 1024) {
                    size = formatNumber(dSize, 4, 2) + "M";
                } else {
                    dSize = formatNumber(dSize, 4, 3) / 1024;
                    if (dSize < 1024) {
                        size = formatNumber(dSize, 4, 2) + "G";
                    } else {
                        size = formatNumber(dSize, 4, 2) + "T";
                    }
                }
            }
        }
        return size;
    }

    /**
     * 将B向上转化的方法
     *
     * @param fileSize
     * @return
     */
    public static String fileSizeInt(double fileSize) {
        String size = null;
        double dSize;
        if (fileSize <= 0) {
//            size = "0M";
            size = "";
        } else if (fileSize > 0 && fileSize < 1024) {
            size = "";
        } else {
            dSize = fileSize / 1024;
            if (dSize < 1024) {
                size = "";
            } else {
                dSize = formatNumber(dSize, 4, 3) / 1024;
                if (dSize < 1024) {
                    size = formatNumber(dSize, 4, 2) + "M";
//                    size = ((int) dSize) + "M";
                } else {
                    dSize = formatNumber(dSize, 4, 3) / 1024;
                    if (dSize < 1024) {
                        size = formatNumber(dSize, 4, 2) + "G";
//                        size = ((int) dSize) + "G";
                    } else {
                        size = formatNumber(dSize, 4, 2) + "T";
//                        size = ((int) dSize) + "T";
                    }
                }
            }
        }
        return size;
    }

    /**
     * 将B向上转化的方法
     *
     * @param fileSize
     * @return
     */
    public static String fileSizeIntKB(double fileSize) {
        String size = null;
        double dSize;
        if (fileSize <= 0) {
//            size = "0M";
            size = "0KB";
        } else if (fileSize > 0 && fileSize < 1024) {
            size = formatNumber(formatNumber(fileSize, 4, 2) / 1000, 4, 2) + "KB";
        } else {
            dSize = fileSize / 1000;
            if (dSize < 1024) {
                size = formatNumber(formatNumber(fileSize, 4, 2) / 1000, 4, 2) + "KB";
            } else {
                dSize = formatNumber(dSize, 4, 2) / 1000;
                if (dSize < 1024) {
                    size = formatNumber(dSize, 4, 2) + "M";
//                    size = ((int) dSize) + "M";
                } else {
                    dSize = formatNumber(dSize, 4, 3) / 1000;
                    if (dSize < 1024) {
                        size = formatNumber(dSize, 4, 2) + "G";
//                        size = ((int) dSize) + "G";
                    } else {
                        size = formatNumber(dSize, 4, 2) + "T";
//                        size = ((int) dSize) + "T";
                    }
                }
            }
        }
        return size;
    }

    /**
     * @param number       数
     * @param roundingMode 表示四舍五入，可以选择其他舍值方式，例如去尾，等等. 传几到几舍弃
     * @param scale        设置位数
     * @return
     */
    public static double formatNumber(double number, int roundingMode, int scale) {

        BigDecimal bd = new BigDecimal(number);

        bd = bd.setScale(scale, roundingMode);

        return bd.doubleValue();
    }
}
