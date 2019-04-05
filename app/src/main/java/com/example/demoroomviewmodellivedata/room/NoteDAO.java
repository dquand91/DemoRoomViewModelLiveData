package com.example.demoroomviewmodellivedata.room;


import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

// DAO : Data Access Object
// @Dao là 1 interface để khai báo các phương thức tương tác với DataBase
// Để thêm các câu SQL truy vấn
@Dao
public interface NoteDAO {

    // Để thêm 1 dòng vào DataBase
    // Ở đây mình thêm 1 dòng của đối tượng Note và bảng "note_table"
    @Insert
    void insert(Note note);

    // Để update 1 dòng trong DataBase
    // Ở đây mình update 1 dòng của đối tượng Note và bảng "note_table"
    @Update
    void update(Note note);

    // @Delete Để delete dòng trong DataBase
    // Ở đây mình delete 1 dòng của đối tượng Note và bảng "note_table"
    // Hiện tại mình chỉ delete 1 dòng, chứ chưa có delete nhiều dòng
    // Mình có thể truyền vào 1 list Object để xóa cả list
    @Delete
    void delete(Note note);

    // @Query để custom 1 câu query bất kỳ
    @Query("DELETE FROM note_table")
    void deleteAllNotes();


    // "SELECT * FROM note_table ORDER BY priority_column DESC"
    //      ==> Lấy tất cả phần tử trong bảng note_table và sắp xếp theo thứ tự của cột priority_column
    // ===> Đối tượng return trả về sau khi dùng câu lệnh này là List<Note> (danh sách các Note)
    // ===> Và phải bộc List<Note> trong LiveData<> vì mình sẽ quan sát và cập nhật data lên UI sau khi get được data từ Database.
    // Phải dùng "priority_column" chứ ko dùng "priority" vì bên Note.java,
    //         mình đã tạo bảng note_table với tên cột của biến priority là "priority_column"
    @Query("SELECT * FROM note_table ORDER BY priority_column DESC")
    LiveData<List<Note>>getAllNotes();
}
