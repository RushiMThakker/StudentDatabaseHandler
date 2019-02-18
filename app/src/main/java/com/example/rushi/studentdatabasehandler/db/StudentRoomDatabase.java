package com.example.rushi.studentdatabasehandler.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Student.class},version = 1)
public abstract class StudentRoomDatabase extends RoomDatabase
{
  private static volatile StudentRoomDatabase studentRoomDatabase;
  public abstract StudentDao studentDao();

  public static StudentRoomDatabase getStudentDatabase(final Context context)
  {
    if(studentRoomDatabase == null)
    {
      studentRoomDatabase = Room.databaseBuilder(context, StudentRoomDatabase.class, "student_db").build();
    }
    return studentRoomDatabase;
  }
}
