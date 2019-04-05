package com.example.demoroomviewmodellivedata.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


// @Entity Để tạo 1 bảng (table) trong dataBase
// Ở đây table tên là "note_table"
@Entity(tableName = "note_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private String description;

    // @ColumnInfo để thay đổi tên của column.
    // Ở đây mình ko muốn dùng tên mặc định của column là "priority" nên mình sửa lại thành "priority_column"
    @ColumnInfo(name = "priority_column")
    private int priority;

    public Note(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }
}
