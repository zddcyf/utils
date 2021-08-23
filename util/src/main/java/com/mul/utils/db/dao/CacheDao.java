package com.mul.utils.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.mul.utils.db.request.ApiCache;

import java.util.List;

/**
 * @ProjectName: utils
 * @Package: com.mul.utils.db.dao
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
public interface CacheDao {
    String tableName = "ApiCache";

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long save(ApiCache cache);

    /**
     * 注意，冒号后面必须紧跟参数名，中间不能有空格。大于小于号和冒号中间是有空格的。
     * select *from cache where【表中列名】 =:【参数名】------>等于
     * where 【表中列名】 < :【参数名】 小于
     * where 【表中列名】 between :【参数名1】 and :【参数2】------->这个区间
     * where 【表中列名】like :参数名----->模糊查询
     * where 【表中列名】 in (:【参数名集合】)---->查询符合集合内指定字段值的记录
     *
     * @param key 数据key
     * @return 返回单条数据
     */

    //如果是一对多,这里可以写List<Cache>
//    @Query("select *from " + tableName + " where 'key'=:key")
    @Query("select *from " + tableName + " where `key`=:key")
    ApiCache getCache(String key);

    // 根据创建时间正序
    @Query("SELECT * FROM " + tableName + " WHERE `key`= :key order By `createTime` ASC")
    List<ApiCache> getAllAscDatas(String key);

    // 根据创建时间倒序
    @Query("SELECT * FROM " + tableName + " WHERE `key`= :key order By `createTime` DESC")
    List<ApiCache> getAllDescDatas(String key);

    //只能传递对象昂,删除时根据Cache中的主键 来比对的
    @Delete
    int delete(ApiCache cache);

    //删全部
    @Query("DELETE FROM " + tableName)
    void deleteAll();

    //只能传递对象昂,删除时根据Cache中的主键 来比对的
    @Update(onConflict = OnConflictStrategy.REPLACE)
    int update(ApiCache cache);
}