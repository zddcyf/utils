package com.mul.utils.db.request;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.mul.utils.db.ByteConvertUtils;

import java.io.Serializable;

/**
 * @ProjectName: utils
 * @Package: com.mul.utils.db.request
 * @ClassName: ApiCache
 * @Author: zdd
 * @CreateDate: 2020/7/15 17:17
 * @Description: java类作用描述
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/7/15 17:17
 * @UpdateRemark: 更新说明
 * @Version: 1.0.0
 */
@Entity(tableName = "ApiCache")
public class ApiCache implements Serializable {
    @PrimaryKey(autoGenerate = false)
    @NonNull
    public String key;
    public byte[] datas;
    public long createTime = System.currentTimeMillis();

    public <T> T getData() {
        return ByteConvertUtils.getData(datas);
    }
}