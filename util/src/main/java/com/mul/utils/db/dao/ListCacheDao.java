package com.mul.utils.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mul.utils.db.request.ApiListCache;

import java.util.List;

/**
 * @ProjectName: utils
 * @Package: com.mul.utils..dao
 * @ClassName: CacheDao
 * @Author: zdd
 * @CreateDate: 2020/7/15 20:35
 * @Description: java类作用描述
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/7/15 20:35
 * @UpdateRemark: 更新说明
 * @Version: 1.0.0
 */
@Dao
public interface ListCacheDao {
    String tableName = "ListApiCache";

    @Insert
    void insert(ApiListCache cache);

    //删除某一项
    @Delete
    void delete(ApiListCache cache);

    //删除单个
    @Query("DELETE FROM " + tableName + " WHERE `key`= :key and `dataId`= :dataId")
    void deleteFirst(String key, String dataId);

    //删除不等于data列表的数据
    @Query("DELETE FROM " + tableName + " WHERE `key`= :key and `data` not in (:datas)")
    void deleteNotInData(String key, Integer[] datas);

    //删除等于data列表的数据
    @Query("DELETE FROM " + tableName + " WHERE `key`= :key and `data` in (:datas)")
    void deleteInData(String key, Integer[] datas);

    //删除不等于dataId列表的数据
    @Query("DELETE FROM " + tableName + " WHERE `key`= :key and `dataId` not in (:dataIds)")
    void deleteNotInDataId(String key, Integer[] dataIds);

    //删除等于dateId列表的数据
    @Query("DELETE FROM " + tableName + " WHERE `key`= :key and `dataId` in (:dataIds)")
    void deleteInDataId(String key, Integer[] dataIds);

    //删全部
    @Query("DELETE FROM " + tableName + " WHERE `key`= :key")
    void deleteAll(String key);

    //改
    @Update
    void update(ApiListCache cache);

    //查全部
    @Query("SELECT * FROM " + tableName + " WHERE `key`= :key")
    List<ApiListCache> getAllDatas(String key);

    // 根据创建时间正序
    @Query("SELECT * FROM " + tableName + " WHERE `key`= :key order By `createTime` ASC")
    List<ApiListCache> getAllAscDatas(String key);

    // 根据创建时间倒序
    @Query("SELECT * FROM " + tableName + " WHERE `key`= :key order By `createTime` DESC")
    List<ApiListCache> getAllDescDatas(String key);

    //根据字段查询
    @Query("SELECT * FROM " + tableName + " WHERE `key`= :key and `data` like '%' || :data || '%'")
    List<ApiListCache> getAllLikeDatas(String key, String data);

    //根据字段查询
    @Query("SELECT * FROM " + tableName + " WHERE `key`= :key and `data` = :data")
    List<ApiListCache> getAllEqualsDatas(String key, String data);

    //根据字段查询
    @Query("SELECT * FROM " + tableName + " WHERE `id`= :id")
    ApiListCache getIdData(long id);

    //根据字段查询
    @Query("SELECT * FROM " + tableName + " WHERE `key`= :key and `dataId`= :dataId")
    ApiListCache getData(String key, String dataId);

//    //根据字段查询
//    @Query("SELECT * FROM " + tableName + " WHERE `key`= :key and `dataId`= :dataId and keys not NULL")
//    ApiListCache getData(String key, String dataId, String dd);
}