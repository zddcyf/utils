package com.mul.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import com.mul.utils.log.LogUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

/**
 * @ProjectName: youlinanzhuoyuanshengduan
 * @Package: com.wisdom.tdweilaiapp.utils
 * @ClassName: ShapeUtils
 * @Author: zdd
 * @CreateDate: 2020/3/4 8:43
 * @Description: 画圆工具类
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/3/4 8:43
 * @UpdateRemark: 更新说明
 * @Version: 1.0.0
 */
public enum SPUtil {
    INSTANCE;

    private SharedPreferences sp;

    /**
     * 应用sp文件的名称
     *
     * @param spName
     */
    public void init(Context mContext, String spName) {
        sp = mContext.getSharedPreferences(spName, Activity.MODE_PRIVATE);
    }

    /**
     * @param key   保存数据的键值
     * @param value 要保存的数据
     */
    public void put(String key, Object value) {
        // 根据value的类型调用不同的putXxx()
        if (value instanceof String) {
            sp.edit().putString(key, (String) value).commit();
        } else if (value instanceof Integer) {
            sp.edit().putInt(key, (Integer) value).commit();
        } else if (value instanceof Boolean) {
            sp.edit().putBoolean(key, (Boolean) value).commit();
        } else if (value instanceof Float) {
            sp.edit().putFloat(key, (Float) value).commit();
        } else if (value instanceof Long) {
            sp.edit().putFloat(key, (Long) value).commit();
        } else {
            saveObjectData(key, value);
        }
    }

    public <V> V get(String key, V defaultValue) {
        Object value = defaultValue;
        if (defaultValue instanceof String) {
            value = sp.getString(key, (String) defaultValue);
        } else if (defaultValue instanceof Integer) {
            value = sp.getInt(key, (Integer) defaultValue);
        } else if (defaultValue instanceof Long) {
            value = sp.getLong(key, (Long) defaultValue);
        } else if (defaultValue instanceof Boolean) {
            value = sp.getBoolean(key, (Boolean) defaultValue);
        } else if (defaultValue instanceof Float) {
            value = sp.getFloat(key, (Float) defaultValue);
        } else {
            value = getObjectData(key);
        }
        return (V) value;
    }

    private boolean saveObjectData(String key, Object value) {
        // 创建字节输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            // 创建对象输出流，并封装字节流
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            // 将对象写入字节流
            oos.writeObject(value);
            // 将字节流编码成base64的字符窜
            String oAuth_Base64 = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
            return sp.edit().putString(key, oAuth_Base64).commit();
        } catch (IOException e) {
            LogUtil.saveE("sp存储异常" + e.toString());
            e.printStackTrace();
            return false;
        }
    }

    private <T> T getObjectData(String key) {
        String productBase64 = sp.getString(key, null);
        if (productBase64 == null) {
            return null;
        }
        byte[] base64 = Base64.decode(productBase64.getBytes(), Base64.DEFAULT);
        //封装到字节流
        ByteArrayInputStream bais = new ByteArrayInputStream(base64);
        try {
            //再次封装
            ObjectInputStream bis = new ObjectInputStream(bais);
            try {
                //读取对象
                T t = (T) bis.readObject();
                return t;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据key移除 sp中对应的数据
     *
     * @param key 保存数据的键值
     */
    public void remove(String key) {
        sp.edit().remove(key).commit();
    }

    /**
     * 清楚 sp 中的存储的数据
     */
    public void clear() {
        sp.edit().clear();
    }
}
