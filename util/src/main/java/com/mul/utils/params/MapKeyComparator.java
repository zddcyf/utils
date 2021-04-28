package com.mul.utils.params;

import java.util.Comparator;

/**
 * @ProjectName: utils
 * @Package: com.mul.utils.params
 * @ClassName: MapKeyComparator
 * @Author: zdd
 * @CreateDate: 2020/9/29 14:13:46
 * @Description: 对map进行排序
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/9/29 14:13:46
 * @UpdateRemark: 更新说明
 * @Version: 1.0.0
 */
public class MapKeyComparator implements Comparator<String> {
    public int compare(String str1, String str2) {
        return str1.compareTo(str2);
    }
}