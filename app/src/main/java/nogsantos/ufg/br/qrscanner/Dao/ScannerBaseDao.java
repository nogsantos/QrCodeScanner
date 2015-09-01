package nogsantos.ufg.br.qrscanner.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nogsantos on 8/23/14.
 */
public class ScannerBaseDao extends SQLiteOpenHelper {
    /*
     * DB info
     */
    private static final String DB_NAME = "fscaner";
    private static final int DB_VERSION = 1;
    /*
     * Fields
     */
    public static final String TB_NAME  = "scanner";
    public static final String _ID      = "_id";
    public static final String DATE     = "date";
    public static final String TEXT     = "text";
    /**
     * Constructor
     */
    public ScannerBaseDao(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    /**
     * Create table scanner
     */
    private String createTbScanner(){
        StringBuilder sql = new StringBuilder();
        sql.append(" create table ");
        sql.append(" "+TB_NAME+" ( ");
        sql.append(" "+_ID+" integer primary key auto increment,  ");
        sql.append(" "+DATE+" numeric not null  ");
        sql.append(" "+TEXT+" text not null); ");
        return sql.toString();
    }
    /**
     * Create tables
     */
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(createTbScanner());
    }
    /**
     * Upgrade method
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS scanner");
        /*
         * Recreating
         */
        onCreate(db);
    }
}
