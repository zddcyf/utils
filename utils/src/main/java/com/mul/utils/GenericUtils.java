package com.mul.utils;

import com.mul.utils.log.LogExceptionResult;
import com.mul.utils.log.LogUtil;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @ProjectName: jolly
 * @Package: com.mul.libcommon
 * @ClassName: GenericUtils
 * @Author: zdd
 * @CreateDate: 2020/8/14 16:25
 * @Description: java类作用描述
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/8/14 16:25
 * @UpdateRemark: 更新说明
 * @Version: 1.0.0
 */
public class GenericUtils {
    public static Class genericViewModel(Object object, int genericIndex) {
        Class mClz = null;
        //利用 子类传递的 泛型参数实例化出absViewModel 对象。
        try {
            ParameterizedType type = (ParameterizedType) object.getClass().getGenericSuperclass();
            Type[] arguments = type.getActualTypeArguments();
            if (arguments.length > genericIndex) {
                Type argument = arguments[genericIndex];
                mClz = ((Class) argument);
            }
        } catch (Exception e) {
            e.getMessage();
        }

        return mClz;
    }

    @SuppressWarnings("unchecked")
    public static <T> T cancelUnchecked(Object object) {
        try {
            return (T) object;
        } catch (Exception mE) {
            mE.printStackTrace();
            LogUtil.saveE(LogExceptionResult.getException(mE));
            return null;
        }
    }
}