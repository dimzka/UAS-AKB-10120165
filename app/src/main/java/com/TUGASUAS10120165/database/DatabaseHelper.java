package com.TUGASUAS10120165.database;
// 10120165 - Muhamad Dimas Azka Syarif Umair - IF4
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.TUGASUAS10120165.model.Note;
import com.TUGASUAS10120165.NoteInterface;

public class DatabaseHelper extends SQLiteOpenHelper implements NoteInterface {

    public DatabaseHelper(@Nullable Context context) {
        super(context, "db_diary", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE tabel_catatan (id TEXT, title TEXT,category TEXT, description TEXT, date TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE tabel_catatan");
    }

    public Cursor read(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM tabel_catatan", null);
    }

    public boolean create (Note note){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("INSERT INTO tabel_catatan VALUES ('"+note.getId()+"','"+note.getTitle()+"','"+note.getCategory()+"','"+note.getDesc()+"','"+note.getDate()+"')");
        return true;
    }

    public boolean update (Note note){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("UPDATE tabel_catatan SET title='"+note.getTitle()+"',category='"+note.getCategory()+"', description='"+note.getDesc()+"', date='"+note.getDate()+"' WHERE id='"+note.getId()+"'");
        return true;
    }

    public boolean delete(String id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM tabel_catatan WHERE id='"+id+"'");
        return true;
    }
}


// 10120165 - Muhamad Dimas Azka Syarif Umair - IF4

