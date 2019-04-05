package com.example.demoroomviewmodellivedata.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

// {Note.class} nếu có nhiều bảng khác nhau => thêm phần tử vào trong này
// @Database(entities = {Note.class}, version = 1)
// ===> dùng "entities =" để khai báo các table thêm vào Database
// ===> "version =" chỉ định version của Database
@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    // Ở đây dùng singletone để tạo NoteDataBase

    private static NoteDatabase instance;

    public abstract NoteDAO getNoteDao();

    // synchronized để đảm bảo trong 1 thời điểm chỉ có duy nhất 1 đối tượng sử dụng cái getInstance này
    public static synchronized NoteDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
