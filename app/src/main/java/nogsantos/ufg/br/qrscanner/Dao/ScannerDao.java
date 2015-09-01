package nogsantos.ufg.br.qrscanner.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nogsantos.ufg.br.qrscanner.Model.ScannerModel;
import nogsantos.ufg.br.qrscanner.Utilities.Functions;

/**
 * Created by nogsantos on 8/23/14.
 */
public class ScannerDao {

    private SQLiteDatabase database;
    private ScannerBaseDao dbHelper;
    /*
     * Table fields
     */
    private String[] columns = {
        ScannerBaseDao._ID,
        ScannerBaseDao.DATE,
        ScannerBaseDao.TEXT,
    };
    /**
     * Constructor
     */
    public ScannerDao(Context context) {
        dbHelper = new ScannerBaseDao(context);
    }
    /**
     * Open DB
     */
    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }
    /**
     * Close DB
     */
    public void close(){
        dbHelper.close();
    }
    /*
     * Insert values
     */
    public long insert(ScannerModel pScannerValue){
        ContentValues values = new ContentValues();
        /*
         * Values
         */
        values.put(ScannerBaseDao.DATE,pScannerValue.getDate());
        values.put(ScannerBaseDao.TEXT,pScannerValue.getText());
        /*
         * Inserting
         */
        return database.insert(
            ScannerBaseDao.TB_NAME,
            null,
            values
        );
    }
    /**
     * Update
     */
    public int update(ScannerModel pScannerValue){
        long _id             = pScannerValue.get_id();
        ContentValues values = new ContentValues();
        /*
         * New Values
         */
        values.put(ScannerBaseDao.DATE, pScannerValue.getDate());
        values.put(ScannerBaseDao.TEXT, pScannerValue.getText());
        /*
         * Updating
         */
        return database.update(
            ScannerBaseDao.TB_NAME,
            values,
            ScannerBaseDao._ID + " = " + _id,
            null
        );
    }
    /**
     * Delete
     */
    public void delete(ScannerModel pScannerValue){
        long _id = pScannerValue.get_id();
        /*
         * Deleting
         */
        database.delete(
            ScannerBaseDao.TB_NAME,
            ScannerBaseDao._ID + " = " + _id,
            null
        );
    }
    /**
     * Query
     */
    public List<ScannerModel> query() {
        List<ScannerModel> vQuery = new ArrayList<ScannerModel>();

        Cursor cursor = database.query(
            ScannerBaseDao.TB_NAME,
            columns,
            null,
            null,
            null,
            null,
            ScannerBaseDao.DATE
        );

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            ScannerModel objScanner = cursorToScanner(cursor);
            vQuery.add(objScanner);
            cursor.moveToNext();
        }
        /*
         * Tenha certeza que você fechou o cursor
         */
        cursor.close();
        Functions.progressDialogDimiss();
        return vQuery;
    }
    /**
     * Converter o Cursor de dados no objeto POJO ContatoVO
     */
    private ScannerModel cursorToScanner(Cursor cursor) {
        ScannerModel objScanner = new ScannerModel();
        objScanner.set_id(cursor.getLong(0));
        objScanner.setDate(cursor.getLong(1));
        objScanner.setText(cursor.getString(2));
        return objScanner;
    }
}
