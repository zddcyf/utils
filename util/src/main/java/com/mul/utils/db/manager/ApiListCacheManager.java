package com.mul.utils.db.manager;

import com.mul.utils.ByteConvertUtils;
import com.mul.utils.db.cache.CacheDatabase;
import com.mul.utils.db.request.ApiListCache;

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
public class ApiListCacheManager {

    private ApiListCacheManager() {
    }

    public static ApiListCacheManager get() {
        return ApiListCacheManagerSington.API_LIST_CACHE_MANAGER;
    }

    private static class ApiListCacheManagerSington {
        private static final ApiListCacheManager API_LIST_CACHE_MANAGER = new ApiListCacheManager();
    }

    /**
     * 写入数据库缓存
     *
     * @param key    数据key,用于数据查询时快速定位到数据
     * @param dataId 数据唯一id
     * @param body   数据内容
     * @param <B>    数据类型
     */
    public <B> void insert(String key, String dataId, B body) {
        ApiListCache apiCache = new ApiListCache();
        apiCache.key = key;
        apiCache.dataId = dataId;
        apiCache.datas = ByteConvertUtils.toByteArray(body);
        CacheDatabase.get().getListCacheDao().insert(apiCache);
    }

    public <B> void insert(ApiListCache apiCache, B body) {
        apiCache.datas = ByteConvertUtils.toByteArray(body);
        CacheDatabase.get().getListCacheDao().insert(apiCache);
    }

    public <B> void insert(String key, String dataId, String data, B body) {
        ApiListCache apiCache = new ApiListCache();
        apiCache.key = key;
        apiCache.dataId = dataId;
        apiCache.data = data;
        apiCache.datas = ByteConvertUtils.toByteArray(body);
        CacheDatabase.get().getListCacheDao().insert(apiCache);
    }

    public <B> void update(ApiListCache apiCache, B body) {
        apiCache.datas = ByteConvertUtils.toByteArray(body);
        CacheDatabase.get().getListCacheDao().update(apiCache);
    }

//    public <B> void update(String key, String dataId, B body) {
//        ApiListCache apiCache = new ApiListCache();
//        apiCache.key = key;
//        apiCache.dataId = dataId;
//        apiCache.datas = toByteArray(body);
//        CacheDatabase.get().getListCacheDao().update(apiCache);
//    }

    public List<ApiListCache> getAllDatas(String key) {
        return CacheDatabase.get().getListCacheDao().getAllDatas(key);
    }

    public List<ApiListCache> getAllAscDatas(String key) {
        return CacheDatabase.get().getListCacheDao().getAllAscDatas(key);
    }

    public List<ApiListCache> getAllDescDatas(String key) {
        return CacheDatabase.get().getListCacheDao().getAllDescDatas(key);
    }

    public List<ApiListCache> getAllDatas(String key, String data) {
        return CacheDatabase.get().getListCacheDao().getAllDatas(key, data);
    }

    public ApiListCache getData(String key, String id) {
        return CacheDatabase.get().getListCacheDao().getData(key, id);
    }

    public <B> void delete(String key, B body) {
        ApiListCache cache = new ApiListCache();
        cache.key = key;
        cache.datas = ByteConvertUtils.toByteArray(body);
        CacheDatabase.get().getListCacheDao().delete(cache);
    }

    public void deleteFirst(String key, String dataId) {
        CacheDatabase.get().getListCacheDao().deleteFirst(key, dataId);
    }

    //删除不等于data列表的数据
    public void deleteNotInData(String key, Integer... datas) {
        CacheDatabase.get().getListCacheDao().deleteNotInData(key, datas);
    }

    //删除等于data列表的数据
    public void deleteInData(String key, Integer... datas) {
        CacheDatabase.get().getListCacheDao().deleteInData(key, datas);
    }

    //删除不等于dataId列表的数据
    public void deleteNotInDataId(String key, Integer... dataIds) {
        CacheDatabase.get().getListCacheDao().deleteNotInDataId(key, dataIds);
    }

    //删除等于dateId列表的数据
    public void deleteInDataId(String key, Integer... dataIds) {
        CacheDatabase.get().getListCacheDao().deleteInDataId(key, dataIds);
    }

    public void deleteAll(String key) {
        CacheDatabase.get().getListCacheDao().deleteAll(key);
    }
}