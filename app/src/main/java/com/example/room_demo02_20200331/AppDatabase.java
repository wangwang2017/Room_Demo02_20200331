package com.example.room_demo02_20200331;

import android.content.Context;
import android.location.Address;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Student.class}, version = 3,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;
    public static synchronized AppDatabase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class
                                    ,"jettDB")
                    //可以强制在主线程运行数据库操作
                    .allowMainThreadQueries()
                    //强制升级  会删除所有数据
//                    .fallbackToDestructiveMigration()
                    //正常的应该用这个  会添加字段  并且保留原有的数据
                    .addMigrations(MIGRATION_2_3)
                    .build();
        }
        return instance;
    }

    //升级  添加字段 数据库版本2升级到3
    static final Migration MIGRATION_2_3 =new Migration(2,3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("alter table student add column flag2 integer not null default 1");
        }
    };

    //数据库升级
    static final Migration MIGRATION_3_4 =new Migration(3,4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("alter table student add column flag2 integer not null default 1");

//            database.execSQL("create table student_temp (uid integer primary key not null,name text,pwd text,addressId)");
//            database.execSQL("insert into student (uid,name,pwd,addressid)" + " select uid,name,pwd,addressid from student");
//            database.execSQL("drop table student");
//            database.execSQL("alter table student_temp rename to student");

        }
    };
    public abstract  StudentDao studentDao();
}






