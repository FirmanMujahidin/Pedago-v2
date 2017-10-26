package com.pedago2.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "dcmaster_db";

    String TABLE_LOGIN = "login";
    String TABLE_BIODATA = "biodata";

    String CREATE_TABLE_LOGIN = "CREATE TABLE " + TABLE_LOGIN + "("
            + "mail TEXT, "
            + "pass TEXT)";

    String CREATE_TABLE_biodata = "CREATE TABLE " + TABLE_BIODATA
            + "(id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "url_foto TEXT, "
            + "nama TEXT, "
            + "alamat TEXT, "
            + "tgl_lahir TEXT, "
            + "pendidikan TEXT, "
            + "profesi TEXT, "
            + "file_pdf TEXT)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_LOGIN);
        db.execSQL(CREATE_TABLE_biodata);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS login");
        db.execSQL("DROP TABLE IF EXISTS biodata");
        onCreate(db);
    }

    public void createTablesLogin() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(CREATE_TABLE_LOGIN);
    }

    public void createTablesbiodata() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(CREATE_TABLE_biodata);
    }

    public void dropTables(String table_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
    }

    public void InsertLogin(String res[]) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("mail", res[0]); // Contact Name
        values.put("pass", res[1]); // Contact Phone

        // Inserting Row
        db.insert("login", null, values);
        db.close(); // Closing database connection
    }

    public void Insertbiodata(String res[]) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("url_foto", res[0]);
        values.put("nama", res[1]);
        values.put("alamat", res[2]);
        values.put("tgl_lahir", res[3]);
        values.put("pendidikan", res[4]);
        values.put("profesi", res[5]);
        values.put("file_pdf", res[6]);

        // Inserting Row
        db.insert("biodata", null, values);
        db.close(); // Closing database connection
    }

    public void Updatebiodata(String res[], String url_foto) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("url_foto", res[0]);
        values.put("nama", res[1]);
        values.put("alamat", res[2]);
        values.put("tgl_lahir", res[3]);
        values.put("pendidikan", res[4]);
        values.put("profesi", res[5]);
        values.put("file_pdf", res[6]);

//        int i = db.update("biodata", values, "nama = " + url_foto, null);
//        // Inserting Row
//        db.update("biodata", values, "nama=?", new String[]{url_foto});
//        //AND file_pdf LIKE "+url_pdf new String[]{url_foto, url_pdf}

        try {
            int i = db.update("biodata", values, "nama =?", new String[]{url_foto});
        } catch (SQLiteException e) {
            e.printStackTrace();
        }

//        db.execSQL("UPDATE biodata SET " +
//                "url_foto='" + res[0] + "'," +
//                "nama='" + res[1] + "'," +
//                "alamat='" + res[2] + "'," +
//                "tgl_lahir='" + res[3] + "'," +
//                "pendidikan='" + res[4] + "'," +
//                "profesi='" + res[5] + "'," +
//                "file_pdf='" + res[6] + "' WHERE nama='" + url_foto + "'");
//        db.close(); // Closing database connection
    }

    public String getAllDataLogin()[] {

        String query = "SELECT * FROM login";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        String data[] = new String[]{"", ""};

        if (cursor.moveToFirst()) {
            do {
                data[0] = cursor.getString(0);
                data[1] = cursor.getString(1);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return data;
    }

    public String getAllDataBiodata()[] {

        String query = "SELECT * FROM biodata";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        String data[] = new String[]{"", "", "", "", "", "", ""};

        if (cursor.moveToFirst()) {
            do {
                data[0] = cursor.getString(0);
                data[1] = cursor.getString(1);
                data[2] = cursor.getString(2);
                data[3] = cursor.getString(3);
                data[4] = cursor.getString(4);
                data[5] = cursor.getString(5);
                data[6] = cursor.getString(6);

            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return data;
    }

    public Cursor getAllDataBiodataCursor() {
        String query = "SELECT * FROM biodata";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    public void deleteItem(String index) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("biodata", "nama = ?", new String[]{index});
    }

    public void deleteDataLogin(String index) {
        SQLiteDatabase db = this.getWritableDatabase();
//        int a=db.delete("login", "mail=?", new String[]{index});
//        boolean bool = db.delete("login", "mail=" + index + "", null) > 0;
//        db.delete(DATABASE_TABLE, KEY_NAME + "=" + name, null) > 0;

//        coba ini
        db.execSQL("DELETE FROM " + "login" + " WHERE " + "mail" + "='"+index+"';");
        db.close();

    }

    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    public int getDataLoginCount() {
        String countQuery = "SELECT * FROM login";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public int getDataUpdateBiodataCount() {
        String countQuery = "SELECT * FROM biodata";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        return count;
    }
}
