package com.example.rushi.studentdatabasehandler;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.example.rushi.studentdatabasehandler.adapter.StudentListAdapter;
import com.example.rushi.studentdatabasehandler.databinding.ActivityStudentListBinding;
import com.example.rushi.studentdatabasehandler.db.Student;
import com.example.rushi.studentdatabasehandler.repository.StudentRepository;
import com.example.rushi.studentdatabasehandler.viewmodels.StudentViewModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.example.rushi.studentdatabasehandler.AnalysisActivity.EXTRA_REPLY;

public class StudentListActivity extends AppCompatActivity
{
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    ActivityStudentListBinding activityStudentListBinding =
        DataBindingUtil.setContentView(this, R.layout.activity_student_list);

    StudentViewModel studentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);
    RecyclerView recyclerView = findViewById(R.id.rcvStudentList);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    final StudentListAdapter adapter = new StudentListAdapter(this);
    recyclerView.setAdapter(adapter);
    studentViewModel.getStudents().observe(this, new Observer<List<Student>>()
    {
      @Override public void onChanged(@Nullable List<Student> students)
      {
        adapter.setStudents(students);
      }
    });

    activityStudentListBinding.fbAddStudent.setOnClickListener(new View.OnClickListener()
    {
      @Override public void onClick(View view)
      {
        startActivityForResult(new Intent(StudentListActivity.this, AnalysisActivity.class),100);
      }
    });
  }

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data)
  {
    switch (resultCode)
    {
      case RESULT_OK:
        addStudent((Student) data.getSerializableExtra(EXTRA_REPLY));
        break;
    }

  }

  private void addStudent(Student student)
  {
    StudentRepository studentRepository = new StudentRepository(getApplication());
    studentRepository.insert(student);
  }
}
