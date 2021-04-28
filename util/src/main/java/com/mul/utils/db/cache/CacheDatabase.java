package com.mul.utils.db.cache;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mul.utils.db.dao.CacheDao;
import com.mul.utils.db.dao.ListCacheDao;
import com.mul.utils.db.request.ApiCache;
import com.mul.utils.db.request.ApiListCache;
import com.mul.utils.log.LogUtil;
import com.mul.utils.manager.GlobalManager;

/**
 * @ProjectName: utils
 * @Package: com.mul.utils.db.cache
 * @ClassName: CacheDatabase
 * @Author: zdd
 * @CreateDate: 2020/7/15 16:37
 * @Description: java类作用描述
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/7/15 16:37
 * @UpdateRemark: 更新说明
 * @Version: 1.0.0
 * entities 数据库中有哪些表
 * version 版本号
 * exportSchema 默认为true。会导出一个json文件。会将所有的sql语句打印出一个文件
 */
@Database(entities = {ApiCache.class, ApiListCache.class}, version = 2, exportSchema = true)
public abstract class CacheDatabase extends RoomDatabase {
//    public static String DB_NAME = "FaceIdentifyDb";

    private static CacheDatabase database;

    public static void init(String dbName) {
        // 创建一个内存数据库，这种创建方式只存在于内存中，也就是说一旦进程被杀死。数据随时丢失
//        Room.inMemoryDatabaseBuilder()
//        Migration migration = new Migration(1, 2) {
//            @Override
//            public void migrate(@NonNull SupportSQLiteDatabase database) {
//                int mVersion = database.getVersion();
//                LogUtil.e("mVersion=" + mVersion);
//                database.execSQL("alter table ApiCache ADD COLUMN createTime INTEGER NOT NULL DEFAULT 0");
//                database.execSQL("alter table ListApiCache ADD COLUMN createTime INTEGER NOT NULL DEFAULT 0");
//            }
//        };

        // allowMainThreadQueries() 是否允许在主线程进行查询
        // addCallback() 数据库创建和打开后的回调
        // setQueryExecutor() 设置查询的线程池
        // setJournalMode() room的日志模式
        // fallbackToDestructiveMigration() 数据库升级异常后根据指定版本进行回滚
        LogUtil.saveI("数据库名:" + dbName);
        database = Room.databaseBuilder(GlobalManager.INSTANCE.context, CacheDatabase.class, dbName)
                .allowMainThreadQueries()
//                .addCallback()
//                .setQueryExecutor()
//                .openHelperFactory()
//                .setJournalMode()
//                .fallbackToDestructiveMigration()
//                .addMigrations(migration)
                .build();
    }

    public abstract CacheDao getCacheDao();

    public abstract ListCacheDao getListCacheDao();

    public static CacheDatabase get() {
        return database;
    }

//    static Migration sMigration = new Migration(1, 3) {
//        @Override
//        public void migrate(@NonNull SupportSQLiteDatabase database) {
//            database.execSQL("alter table teacher rename to student");
//            database.execSQL("alter table teacher add column teacher_age INTEGER NOT NULL default 0");
//        }
//    };
}