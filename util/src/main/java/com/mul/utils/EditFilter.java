package com.mul.utils;

import android.text.InputFilter;
import android.text.Spanned;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ProjectName: utils
 * @Package: com.mul.utils
 * @ClassName: EditFilter
 * @Author: zdd
 * @CreateDate: 2020/1/19 10:39
 * @Description: 添加EditFilter工具类用于检测edittext的输入长度限制
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/1/19 10:39
 * @UpdateRemark: 更新说明
 * @Version: 1.0.0
 */
public class EditFilter implements InputFilter {
    int MAX_EN;
    String regEx = "[\\u4e00-\\u9fa5]";
    private final static Pattern idcard = Pattern.compile("\\^(^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$)|(^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])((\\d{4})|\\d{3}[Xx])$)$");

    public EditFilter(int mAX_EN) {
        super();
        MAX_EN = mAX_EN;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end,
                               Spanned dest, int dstart, int dend) {
        int destCount = dest.toString().length()
                + getChineseCount(dest.toString());
        int sourceCount = source.toString().length()
                + getChineseCount(source.toString());
        if (destCount + sourceCount > MAX_EN) {
            int surplusCount = MAX_EN - destCount;
            String result = "";
            int index = 0;
            while (surplusCount > 0) {
                char c = source.charAt(index);
                if (isChinest(c + "")) {
                    if (sourceCount >= 2) {
                        result += c;
                    }
                    surplusCount = surplusCount - 2;
                } else {
                    result += c;
                    surplusCount = surplusCount - 1;
                }
                index++;
            }
            return result;
        } else {
            return source;
        }
    }

    /**
     * 校验身份证
     */
    public static Pattern idNumPattern = Pattern.compile(
            "^[1-9][0-7]\\d{4}((19\\d{2}(0[13-9]|1[012])(0[1-9]|[12]\\d|30))|(19\\d{2}(0[13578]|1[02])31)|(19\\d{2}02(0[1-9]|1\\d|2[0-8]))|(19([13579][26]|[2468][048]|0[48])0229))\\d{3}(\\d|X|x)?$");
    /**
     * 身份证最后一位校验位
     */
    public static String[] ID_JIAO_YAN = new String[]{"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
    /**
     * 身份证前17位系数
     */
    public static int[] ID_XI_SHU = new int[]{7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

    public static boolean isIdentityCard(String idCard) {
        // 校验身份证格式
        boolean flag = idNumPattern.matcher(idCard).matches();
        // 计算身份证最后一位是否正确
        if (idCard.length() < 18) {
//            showToast("请输入正确的二代身份证号码");
            return false;
        }
        char[] chars = idCard.toCharArray();
        int calculLast = 0;
        for (int i = 0; i < chars.length - 1; i++) {
            calculLast += Integer.parseInt(chars[i] + "") * EditFilter.ID_XI_SHU[i];
        }
        int i = calculLast % 11;
        String a = EditFilter.ID_JIAO_YAN[i];
        String b = chars[chars.length - 1] + "";
        if (a.toUpperCase().equals(b.toUpperCase())) {
            flag = true;
        }
        return flag;
    }

    private int getChineseCount(String str) {
        int count = 0;
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        while (m.find()) {
            for (int i = 0; i <= m.groupCount(); i++) {
                count = count + 1;
            }
        }
        return count;
    }

    private boolean isChinest(String source) {
        return Pattern.matches(regEx, source);
    }

    /**
     * url中有中文字符转码
     *
     * @param str 原字符
     * @return 转码之后的url
     */
    public static String utf8Togb2312(String str) {
        String data = "";
        try {
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (c + "".getBytes().length > 1 && c != ':' && c != '/') {
                    data = data + java.net.URLEncoder.encode(c + "", "utf-8");
                } else {
                    data = data + c;
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 获取倒计时的时间
     *
     * @param timeInterval 时长 单位（秒）
     */
    public static String getCountDown(long timeInterval) {
        if (timeInterval <= 60) {
            return timeInterval + "秒";
        } else if (timeInterval <= 60 * 60) {
            long mins = timeInterval / 60;
            long seconds = timeInterval % 60;
            return mins + "分" + seconds + "秒";
        } else if (timeInterval <= 60 * 60 * 24) {
            long seconds = timeInterval % 60;
            long hours = timeInterval / 60 / 60;
            long mins = timeInterval / 60 % 60;
            return hours + "时" + mins + "分" + seconds + "秒";
        } else {//if (timeInterval <= 60 * 60 * 24 * 3)
            long seconds = timeInterval % 60;
            long hours = timeInterval / 60 / 60 % 24;
            long mins = timeInterval / 60 % 60;
            long days = timeInterval / 60 / 60 / 24;
            return days + "天" + hours + "时" + mins + "分" + seconds + "秒";
        }
    }
}
