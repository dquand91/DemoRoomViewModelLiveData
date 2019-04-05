package com.example.demoroomviewmodellivedata.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.example.demoroomviewmodellivedata.room.Note;
import com.example.demoroomviewmodellivedata.room.NoteDAO;
import com.example.demoroomviewmodellivedata.room.NoteDatabase;

import java.util.List;

import androidx.lifecycle.LiveData;

public class NoteRepository {
    private NoteDAO noteDao;
    private LiveData<List<Note>> allNotes;
 
    public NoteRepository(Application application) {
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.getNoteDao();
        allNotes = noteDao.getAllNotes();
    }

    // Quy định các method cho ViewMode truy cập DataBase
    // Sẽ gọi các method asynctask đã tạo bên dưới
    // Các method này thông qua DAO để truy cập DataBase
    public void insert(Note note) {
        new InsertNoteAsyncTask(noteDao).execute(note);
    }
 
    public void update(Note note) {
        new UpdateNoteAsyncTask(noteDao).execute(note);
    }
 
    public void delete(Note note) {
        new DeleteNoteAsyncTask(noteDao).execute(note);
    }
 
    public void deleteAllNotes() {
        new DeleteAllNotesAsyncTask(noteDao).execute();
    }
 
    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    // Ứng với mỗi câu lệnh query SQL ta sẽ tạo 1 asyntask tương ứng
    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDAO noteDao;
 
        private InsertNoteAsyncTask(NoteDAO noteDao) {
            this.noteDao = noteDao;
        }
 
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    // Ứng với mỗi câu lệnh query SQL ta sẽ tạo 1 asyntask tương ứng
    private static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDAO noteDao;
 
        private UpdateNoteAsyncTask(NoteDAO noteDao) {
            this.noteDao = noteDao;
        }
 
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    // Ứng với mỗi câu lệnh query SQL ta sẽ tạo 1 asyntask tương ứng
    private static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDAO noteDao;
 
        private DeleteNoteAsyncTask(NoteDAO noteDao) {
            this.noteDao = noteDao;
        }
 
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    // Ứng với mỗi câu lệnh query SQL ta sẽ tạo 1 asyntask tương ứng
    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDAO noteDao;
 
        private DeleteAllNotesAsyncTask(NoteDAO noteDao) {
            this.noteDao = noteDao;
        }
 
        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }
}