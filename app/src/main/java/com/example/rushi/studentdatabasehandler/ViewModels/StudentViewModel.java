package com.example.rushi.studentdatabasehandler.ViewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import com.example.rushi.studentdatabasehandler.db.Student;
import com.example.rushi.studentdatabasehandler.repository.StudentRepository;
import java.util.ArrayList;

public class StudentViewModel extends ViewModel
{
  private StudentRepository studentRepository;
  private LiveData<ArrayList<Student>> students;

  public StudentViewModel(
      StudentRepository studentRepository)
  {
    this.studentRepository = studentRepository;
    students = studentRepository.getStudents();
  }

  public LiveData<ArrayList<Student>> getStudents()
  {
    return students;
  }

  public void insert(Student student)
  {
    studentRepository.insert(student);
  }
}
