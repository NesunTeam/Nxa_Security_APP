package com.nesun.sec.apps.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nesun.sec.apps.bean.PrivateFileBean;
import com.nesun.sec.apps.db.PrivateFileDb;

import java.util.ArrayList;
import java.util.List;

public class PrivateFileDao {

    private PrivateFileDb dbHelper;

    public PrivateFileDao(Context context) {
        this.dbHelper = new PrivateFileDb(context);
    }


    /**
     * add a blacklist into database
     * @param privateFileBean the data need to added
     * @return true if success
     */
    public boolean add(PrivateFileBean privateFileBean) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // key - value
        ContentValues values = new ContentValues();
        values.put(PrivateFileDb.PRIVATEFILE_PATH, privateFileBean.getFilePath());
        values.put(PrivateFileDb.PRIVATEFILE_NAME, privateFileBean.getFileName());
        values.put(PrivateFileDb.PRIVATEFILE_SIZE, privateFileBean.getFileSize());
        values.put(PrivateFileDb.PRIVATEFILE_TIME, privateFileBean.getFileTime());
        values.put(PrivateFileDb.PRIVATEFILE_TYPE, privateFileBean.getFileType());

        long rowId = db.insert(PrivateFileDb.TB_PRIVATEFILE_NAME, null, values);
        db.close();

        return -1 != rowId;
    }




    /**
     * query all BlacklistBean
     * @return List<BlacklistBean>. It wouldn't be null.
     */
    public List<PrivateFileBean> selectAll() {
        List<PrivateFileBean> list = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(PrivateFileDb.TB_PRIVATEFILE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            // get value
            String name = cursor.getString(cursor.getColumnIndex(PrivateFileDb.PRIVATEFILE_NAME));
            String path = cursor.getString(cursor.getColumnIndex(PrivateFileDb.PRIVATEFILE_PATH));
            String size = cursor.getString(cursor.getColumnIndex(PrivateFileDb.PRIVATEFILE_SIZE));
            String time = cursor.getString(cursor.getColumnIndex(PrivateFileDb.PRIVATEFILE_TIME));
            int type = cursor.getInt(cursor.getColumnIndex(PrivateFileDb.PRIVATEFILE_TYPE));
            // add to list
            PrivateFileBean bean = new PrivateFileBean(name,time,size,path,type);
            list.add(bean);
        }
        cursor.close();
        db.close();
        return list;
    }



    /**
     * @param filepath the app package name
     * @return true if success, false otherwise
     */
    public boolean isExists(String filepath) {
        // get db
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(PrivateFileDb.TB_PRIVATEFILE_NAME, new String[]{"1"},
                PrivateFileDb.PRIVATEFILE_PATH + " = ?",
                new String[]{filepath}, null, null, null);
        boolean exists = cursor.moveToNext();
        // never forget closing
        cursor.close();
        db.close();
        return exists;
    }


    /**
     * select by path
     * @param path
     * @return BlacklistBean if find, otherwise return null
     */
    public PrivateFileBean selectByPath(String path) {
        PrivateFileBean bean = null;

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(PrivateFileDb.TB_PRIVATEFILE_NAME, null,
                PrivateFileDb.PRIVATEFILE_PATH + "=?",
                new String[]{path}, null, null, null);
        if(cursor.moveToNext()) {
            String subPath = cursor.getString(cursor.getColumnIndex(PrivateFileDb.PRIVATEFILE_PATH));
            String subName = cursor.getString(cursor.getColumnIndex(PrivateFileDb.PRIVATEFILE_NAME));
            String subSize = cursor.getString(cursor.getColumnIndex(PrivateFileDb.PRIVATEFILE_SIZE));
            String subTime = cursor.getString(cursor.getColumnIndex(PrivateFileDb.PRIVATEFILE_TIME));
            int type = cursor.getInt(cursor.getColumnIndex(PrivateFileDb.PRIVATEFILE_TYPE));
            // create bean
            bean = new PrivateFileBean(subName,subTime,  subSize,subPath,type);
        }
        cursor.close();
        db.close();
        return bean;
    }





}
