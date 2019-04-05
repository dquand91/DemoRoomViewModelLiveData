package com.example.demoroomviewmodellivedata.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

// {Note.class} nếu có nhiều bảng khác nhau => thêm phần tử vào trong này
// @Database(entities = {Note.class}, version = 1)
// ===> dùng "entities =" để khai báo các table thêm vào Database
// ===> "version =" chỉ định version của Database
@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    // Ở đây dùng singletone để tạo NoteDataBase

    private static NoteDatabase instance;

    // Không cần implement cái abstract method getNoteDao() vì khi khởi tạo Room.databaseBuilder trong method "getInstance" bên dưới
    // Room đã tạo dùm rồi.
    // *** Nếu có 1 cái table nào khác thì thêm 1 cái getAbcdefDAO vào tương tự, rồi dùng DAO mới để truy vấn table mới đó
    public abstract NoteDAO getNoteDao();

    // synchronized để đảm bảo trong 1 thời điểm chỉ có duy nhất 1 đối tượng sử dụng cái getInstance này
    public static synchronized NoteDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(myRoomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback myRoomCallback = new RoomDatabase.Callback() {

        // Will fire only 1 time when the database is created.
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            // Khi database được tạo => sẽ tiến hành 1 cái asynctask để insert data vào như bên dưới
            // Ở đây chỉ tạo data giả thôi
            // Thực tế sẽ dung retrofit để insert data vào
            new PopulateDbAsyncTask(instance).execute();
        }

        // Will fire when ever the database is opened
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDAO noteDao;

        private PopulateDbAsyncTask(NoteDatabase db) {
            noteDao = db.getNoteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("Title 1", "Description 1", 1));
            noteDao.insert(new Note("Title 2", "Description 2", 2));
            noteDao.insert(new Note("Title 3", "Description 3", 3));
            return null;
        }
    }
}
