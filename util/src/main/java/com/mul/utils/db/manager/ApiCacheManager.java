package com.mul.utils.db.manager;

import com.mul.utils.ByteConvertUtils;
import com.mul.utils.DataUtils;
import com.mul.utils.db.cache.CacheDatabase;
import com.mul.utils.db.request.ApiCache;

import java.util.List;

/**
 * @ProjectName: utils
 * @Package: com.mul.utils.db.manager
 * @ClassName: ApiCacheManager
 * @Author: zdd
 * @CreateDate: 2020/7/15 20:26
 * @Description: 缓存的管理类
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/7/15 20:26
 * @UpdateRemark: 更新说明
 * @Version: 1.0.0
 */
public class ApiCacheManager {
    /**
     * 写入数据库缓存
     *
     * @param key  数据key,用于数据查询时快速定位到数据
     * @param body 数据内容
     * @param <B>  数据类型
     */
    public static <B> void save(String key, B body) {
        ApiCache apiCache = new ApiCache();
        apiCache.key = key;
        apiCache.datas = ByteConvertUtils.toByteArray(body);
        CacheDatabase.get().getCacheDao().save(apiCache);
    }

    public static <B> void update(String key, B body) {
        ApiCache apiCache = new ApiCache();
        apiCache.key = key;
        apiCache.datas = ByteConvertUtils.toByteArray(body);
        CacheDatabase.get().getCacheDao().update(apiCache);
    }

    public static Object getCache(String key) {
        ApiCache cache = CacheDatabase.get().getCacheDao().getCache(key);
        if (null != cache && !DataUtils.isEmpty(cache.datas)) {
            return ByteConvertUtils.toObject(cache.datas);
        }
        return null;
    }

    public static <T> void delete(String key, T body) {
        ApiCache cache = new ApiCache();
        cache.key = key;
        cache.datas = ByteConvertUtils.toByteArray(body);
        CacheDatabase.get().getCacheDao().delete(cache);
    }

    public static void deleteAll() {
        CacheDatabase.get().getCacheDao().deleteAll();
    }

    public List<ApiCache> getAllAscDatas(String key) {
        return CacheDatabase.get().getCacheDao().getAllAscDatas(key);
    }

    public List<ApiCache> getAllDescDatas(String key) {
        return CacheDatabase.get().getCacheDao().getAllDescDatas(key);
    }
}