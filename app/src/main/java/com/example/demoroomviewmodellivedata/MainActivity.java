package com.example.demoroomviewmodellivedata;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.demoroomviewmodellivedata.room.Note;
import com.example.demoroomviewmodellivedata.utils.AppLogger;
import com.example.demoroomviewmodellivedata.viewmodel.NoteViewModel;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private NoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // khởi tạo viewModel để thao tác với data (get, thêm, xóa, sửa)
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        // method getData
        // Dùng "observe" để đăng ký callback quan sát khi nào data có sự thay đổi.
        // Mỗi khi data có thay đổi => onChanged của callback này sẽ được fire => xử lý các logic tiếp theo trong này (Ví dụ: cập nhật UI cho recyclerView)
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                //update RecyclerView
                Toast.makeText(MainActivity.this, "onChanged", Toast.LENGTH_SHORT).show();
                AppLogger.error(TAG, notes);
            }


        });
    }
}
