package com.TUGASUAS10120165;

import android.database.Cursor;

import com.TUGASUAS10120165.model.Note;

public interface NoteInterface {

    public Cursor read();
    public boolean create(Note note);
    public boolean update(Note note);
    public boolean delete(String id);
}


// 10120165 - Muhamad Dimas Azka Syarif Umair - IF4

