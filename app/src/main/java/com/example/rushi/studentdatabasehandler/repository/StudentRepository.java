package com.example.rushi.studentdatabasehandler.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import com.example.rushi.studentdatabasehandler.db.Student;
import com.example.rushi.studentdatabasehandler.db.StudentDao;
import com.example.rushi.studentdatabasehandler.db.StudentRoomDatabase;
import java.util.ArrayList;

public class StudentRepository
{
  private StudentDao studentDao;
  private LiveData<ArrayList<Student>> students;

  public StudentRepository(Application application)
  {
    StudentRoomDatabase studentRoomDatabase = StudentRoomDatabase.getStudentDatabase(application);
    studentDao = studentRoomDatabase.studentDao();
    students = studentDao.getAllStudents();
  }

  public LiveData<ArrayList<Student>> getStudents()
  {
    return students;
  }

  public void insert(Student student)
  {
    new InsertAsyncTask(studentDao).execute(student);
  }

  private static class InsertAsyncTask extends AsyncTask<Student,Void, Void>
  {
    StudentDao studentDao;

    InsertAsyncTask(StudentDao studentDao)
    {
      this.studentDao = studentDao;
    }

    @Override protected Void doInBackground(final Student... params)
    {
      studentDao.addStudent(params[0]);
      return null;
    }
  }
}
