package com.mul.utils.params;

import com.mul.utils.DataUtils;
import com.mul.utils.MD5;
import com.mul.utils.log.LogUtil;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * @ProjectName: MulUtils
 * @Package: com.mul.utils.params
 * @ClassName: ParmsSplice
 * @Author: zdd
 * @CreateDate: 2020/9/29 14:16:38
 * @Description: 参数拼接
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/9/29 14:16:38
 * @UpdateRemark: 更新说明
 * @Version: 1.0.0
 */
public class ParamsSplice {
    @SafeVarargs
    public static String paramSplice(Map<String, Object>... mParams) {
        return paramSplice("", mParams);
    }

    @SafeVarargs
    public static String paramSplice(String keyAndValue, Map<String, Object>... mParams) {
        Map<String, Object> sortMap = new TreeMap<>(new MapKeyComparator());
        for (Map<String, Object> mParam : mParams) {
            sortMap.putAll(mParam);
        }

        StringBuilder md5Str = new StringBuilder();

        for (Map.Entry<String, Object> mEntry : sortMap.entrySet()) {
            md5Str.append(mEntry.getKey());
            md5Str.append("=");
            md5Str.append(mEntry.getValue());
            md5Str.append("&");
        }
        if (!DataUtils.isEmpty(keyAndValue)) {
            md5Str.append(keyAndValue);
            md5Str.append("&");
        }
        LogUtil.saveI("HttpData", "拼接后的参数:" + md5Str.toString());
        return Objects.requireNonNull(MD5.MD5(md5Str.substring(0, md5Str.length() - 1))).toUpperCase();
    }
}