package com.innotek.demotestproject.database.greendaouse;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by admin on 2017/7/25.
 */

public class DataBaseMannager {

    private Context context;
    private static DataBaseMannager mInstance;
    private DaoMaster.DevOpenHelper openHelper;
    private String dbName = "whq_db";
    private DaoMaster daoWriteMaster;
    private DaoMaster daoReadMaster;
    private UpdateDbOpenHelper updateDbOpenHelper;

    public DataBaseMannager(Context context) {
        this.context = context;
        openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
    }

    /**
     * 获取单例引用
     *
     * @param context
     * @return
     */
    public static DataBaseMannager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (DataBaseMannager.class) {
                if (mInstance == null) {
                    mInstance = new DataBaseMannager(context);
                }
            }
        }
        return mInstance;
    }


    /**
     * 获取可读数据库
     */
    private SQLiteDatabase getReadableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        if(updateDbOpenHelper == null){
            updateDbOpenHelper = new UpdateDbOpenHelper(context,dbName,null);
        }
        SQLiteDatabase db = updateDbOpenHelper.getReadableDatabase();
        return db;
    }

    /**
     * 获取可写数据库
     */
    private SQLiteDatabase getWriteableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        // SQLiteDatabase db = openHelper.getWritableDatabase();
        //数据库更新操作
        if(updateDbOpenHelper == null){
            updateDbOpenHelper = new UpdateDbOpenHelper(context,dbName,null);
        }
        SQLiteDatabase db = updateDbOpenHelper.getWritableDatabase();
        return db;
    }

    public DaoMaster getWriteDaoMaster() {
        if (daoWriteMaster == null) {
            daoWriteMaster = new DaoMaster(getWriteableDatabase());
        }
        return daoWriteMaster;
    }

    public DaoMaster getReadDaoMaster() {
        if (daoReadMaster == null) {
            daoReadMaster = new DaoMaster(getReadableDatabase());
        }
        return daoReadMaster;
    }


    public void insertUserData(User user) {
        DaoMaster daoMaster = getWriteDaoMaster();
        DaoSession daoSession = daoMaster.newSession();
        daoSession.getUserDao().insert(user);
      //  daoSession.getUserNewDao().insert(user);
    }

    public void insertUserListData(List<User> userList) {
        if (userList == null || userList.isEmpty()) {
            return;
        }
        DaoMaster daoMaster = getWriteDaoMaster();
        DaoSession daoSession = daoMaster.newSession();
        daoSession.getUserDao().insertInTx(userList);
    }



    public <T> List<T> checkUserData() {
        DaoMaster daoMaster = getReadDaoMaster();
        DaoSession daoSession = daoMaster.newSession();
        QueryBuilder<T> queryBuilder = (QueryBuilder<T>) daoSession.getUserDao().queryBuilder();
        List<T> userList = queryBuilder.build().list();
        return userList;
    }



    public void deleteUserData() {
        DaoMaster daoMaster = getWriteDaoMaster();
        DaoSession daoSession = daoMaster.newSession();
        daoSession.getUserDao().deleteAll();
    }

    public boolean isHasSameData(User user) {
        DaoMaster daoMaster = getReadDaoMaster();
        QueryBuilder<User> queryBuilder = daoMaster.newSession().getUserDao().queryBuilder();
        List<User> userList = queryBuilder.where(UserDao.Properties.UserName.like(user.getUserName())).list();
        if (userList == null) {
            return false;
        } else {
            if (userList != null && userList.size() == 0) {
                return false;
            }
            if (userList != null && userList.size() > 0) {
                return true;
            }
        }
        return false;

    }

}
