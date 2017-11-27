package com.innotek.demotestproject.database.greendaouse;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.database.Database;

/**
 * Created by admin on 2017/7/26.
 */

public class UpdateDbOpenHelper extends DaoMaster.OpenHelper {

    public UpdateDbOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

 /*   @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
         MigrationHelper.getInstance().migrate(db,UserDao.class);

    }*/

    /**
     * 数据库升级
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        //操作数据库的更新 有几个表升级都可以传入到下面
        MigrationHelper.getInstance().migrate(db,UserDao.class);
    }
}
