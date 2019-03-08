package com.example.rushi.studentdatabasehandler.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;
import com.example.rushi.studentdatabasehandler.db.Student;
import com.example.rushi.studentdatabasehandler.repository.StudentRepository;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentViewModel extends AndroidViewModel
{
  private StudentRepository studentRepository;
  private LiveData<List<Student>> students;
  private static final String TAG = StudentViewModel.class.getSimpleName();

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
    addDataToCloudFirestore(student);
  }

  private void addDataToCloudFirestore(Student student)
  {
    // Add data to cloud firestore
    // Access a Cloud Firestore instance from your Activity
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    // Create a new user with a first and last name
    Map<String, Object> user = new HashMap<>();
    user.put("first", student.getFirstName());
    user.put("last", student.getLastName());
    user.put("color", student.getColor());

    // Add a new document with a generated ID
    db.collection("users")
        .add(user)
        .addOnSuccessListener(new OnSuccessListener<DocumentReference>()
        {
          @Override
          public void onSuccess(DocumentReference documentReference)
          {
            Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
          }
        })
        .addOnFailureListener(new OnFailureListener()
        {
          @Override
          public void onFailure(@NonNull Exception e)
          {
            Log.w(TAG, "Error adding document", e);
          }
        });
  }
}
