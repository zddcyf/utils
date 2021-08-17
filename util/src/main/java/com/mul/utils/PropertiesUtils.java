package com.mul.utils;

import com.mul.utils.manager.GlobalManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @ProjectName: MulUtils
 * @Package: com.mul.utils
 * @ClassName: PropertiesUtils
 * @Author: zdd
 * @CreateDate: 2020/7/7 17:29
 * @Description: Properties工具类
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/7/7 17:29
 * @UpdateRemark: 更新说明
 * @Version: 1.0.0
 */
public class PropertiesUtils {
    private final Map<String, Properties> mPropertiesMap = new HashMap<>();
    /**
     * 文件名
     */
    private String propertiesName;

    private PropertiesUtils() {
    }

    private static class PropertiesSingleton {
        private static final PropertiesUtils PROPERTIES_UTILS = new PropertiesUtils();
    }

    public static PropertiesUtils getInstance() {
        return PropertiesSingleton.PROPERTIES_UTILS;
    }

    /**
     * 添加公共文件
     *
     * @param propertiesName 文件名 "config.properties"
     */
    public void put(String propertiesName) {
        try {
            if (DataUtils.isEmpty(this.propertiesName)) {
                this.propertiesName = propertiesName;
            }
            Properties properties = new Properties();
            InputStream inputStream = GlobalManager.INSTANCE.app.getAssets().open(propertiesName);
            Reader reader = new InputStreamReader(inputStream, "UTF-8");
            properties.load(reader);
            mPropertiesMap.put(propertiesName, properties);
        } catch (IOException e) {
            e.printStackTrace();
            mPropertiesMap.remove(propertiesName);
        }
    }

    /**
     * 获取默认的公共文件的信息
     *
     * @param key 文件key
     * @return 返回数据信息
     */
    public String get(String key) {
        return get(propertiesName, key);
    }

    /**
     * 获取默认的公共文件的信息
     *
     * @param propertiesName 文件名
     * @param key            文件key
     * @return 返回数据信息
     */
    public String get(String propertiesName, String key) {
        if (mPropertiesMap.containsKey(propertiesName)) {
            return mPropertiesMap.get(propertiesName).getProperty(key);
        } else {
            return "";
        }
    }
}
