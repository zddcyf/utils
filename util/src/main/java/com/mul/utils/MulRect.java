package com.mul.utils;

import java.io.Serializable;

/**
 * @ProjectName: MulUtils
 * @Package: com.mul.utils
 * @ClassName: MulRect
 * @Author: zdd
 * @CreateDate: 2021/3/12 11:23:47
 * @Description: java类作用描述
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/3/12 11:23:47
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MulRect implements Serializable {
    public int left;
    public int top;
    public int right;
    public int bottom;

    public MulRect() {

    }

    public MulRect(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }
}
