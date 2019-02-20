package com.example.rushi.studentdatabasehandler.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = { Student.class }, version = 1)
public abstract class StudentRoomDatabase extends RoomDatabase
{
  private static volatile StudentRoomDatabase studentRoomDatabase;

  public abstract StudentDao studentDao();

  public static StudentRoomDatabase getStudentDatabase(final Context context)
  {
    if (studentRoomDatabase == null)
    {
      studentRoomDatabase =
          Room.databaseBuilder(context, StudentRoomDatabase.class, "student_db").addCallback(sRoomDatabaseCallback).build();
    }
    return studentRoomDatabase;
  }

  private static RoomDatabase.Callback sRoomDatabaseCallback =
      new RoomDatabase.Callback()
      {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db)
        {
          super.onOpen(db);
          new PopulateDbAsync(studentRoomDatabase).execute();
        }
      };

  private static class PopulateDbAsync extends AsyncTask<Void, Void, Void>
  {
    private final StudentDao mDao;

    PopulateDbAsync(StudentRoomDatabase db)
    {
      mDao = db.studentDao();
    }

    @Override
    protected Void doInBackground(final Void... params)
    {
      mDao.deleteAll();
      Student student = new Student("Rushi","Thakker","red");
      mDao.addStudent(student);
      student = new Student("Riddhi","Thakker","red");
      mDao.addStudent(student);
      return null;
    }
  }
}
