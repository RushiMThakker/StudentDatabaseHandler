package com.example.rushi.studentdatabasehandler.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import com.example.rushi.studentdatabasehandler.db.Student;
import com.example.rushi.studentdatabasehandler.repository.StudentRepository;
import java.util.ArrayList;
import java.util.List;

public class StudentViewModel extends AndroidViewModel
{
  private StudentRepository studentRepository;
  private LiveData<List<Student>> students;

  public StudentViewModel(Application application)
  {
    super(application);
    this.studentRepository = new StudentRepository(application);
    students = studentRepository.getStudents();
  }

  public LiveData<List<Student>> getStudents()
  {
    return students;
  }

  public void insert(Student student)
  {
    studentRepository.insert(student);
  }
}
