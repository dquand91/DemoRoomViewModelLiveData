package com.example.demoroomviewmodellivedata.viewmodel;

import android.app.Application;

import com.example.demoroomviewmodellivedata.repository.NoteRepository;
import com.example.demoroomviewmodellivedata.room.Note;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

// Sẽ tương tác với Repository để lấy data và cập nhật lên View thông qua LiveData
// Đồng thời giải quyết được case xoay màn hình ko bị reset data
public class NoteViewModel extends AndroidViewModel {
    private NoteRepository repository;
    private LiveData<List<Note>> allNotes;
 
    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
        allNotes = repository.getAllNotes();
    }
 
    public void insert(Note note) {
        repository.insert(note);
    }
 
    public void update(Note note) {
        repository.update(note);
    }
 
    public void delete(Note note) {
        repository.delete(note);
    }
 
    public void deleteAllNotes() {
        repository.deleteAllNotes();
    }
 
    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
}