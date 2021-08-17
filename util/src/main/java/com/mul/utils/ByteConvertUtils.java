package com.mul.utils;

import com.mul.utils.log.LogUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 * @ProjectName: MulUtils
 * @Package: com.mul.utils
 * @ClassName: ByteConvertUtils
 * @Author: liys
 * @CreateDate: 2021/3/11 14:23:32
 * @Description: java类作用描述
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/3/11 14:23:32
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ByteConvertUtils {
    /**
     * 写到byte数组中
     *
     * @param body 数据集
     * @param <B>  泛型类型
     * @return 转换成数组的数据
     */
    public static <B> byte[] toByteArray(B body) {
        ByteArrayOutputStream baos = null;
        ObjectOutput oop = null;
        try {
            baos = new ByteArrayOutputStream();
            oop = new ObjectOutputStream(baos);
            oop.writeObject(body);
            oop.flush();
            return baos.toByteArray();
        } catch (Exception e) {
            LogUtil.saveD("body=" + body + ",e.getMessage()=" + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (null != baos) {
                    baos.close();
                }
                if (null != oop) {
                    oop.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new byte[0];
    }

    /**
     * 根据泛型返回对应的数据
     *
     * @param datas 数据集
     * @param <T>   泛型类型
     * @return 返回泛型对应的类型
     */
    public static <T> T getData(byte[] datas) {
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        try {
            bais = new ByteArrayInputStream(datas);
            ois = new ObjectInputStream(bais);
            return GenericUtils.cancelUnchecked(ois.readObject());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != bais) {
                    bais.close();
                }
                if (null != ois) {
                    ois.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 返回对应的数据
     *
     * @param datas 数据集
     * @return 返回object
     */
    public static Object toObject(byte[] datas) {
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        try {
            bais = new ByteArrayInputStream(datas);
            ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != bais) {
                    bais.close();
                }
                if (null != ois) {
                    ois.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
