package com.example.rushi.studentdatabasehandler.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.ArrayList;
import java.util.List;

@Dao
public interface StudentDao
{
  @Insert
  void addStudent(Student student);

  @Query("DELETE FROM student")
  void deleteAll();

  @Query("SELECT * FROM student ORDER BY id ASC")
  LiveData<List<Student>> getAllStudents();
}
