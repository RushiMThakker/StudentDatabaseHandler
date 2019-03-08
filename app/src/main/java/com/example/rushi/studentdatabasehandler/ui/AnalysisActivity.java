package com.example.rushi.studentdatabasehandler.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import com.example.rushi.studentdatabasehandler.R;
import com.example.rushi.studentdatabasehandler.databinding.ActivityAnalysisBinding;
import com.example.rushi.studentdatabasehandler.db.Student;
import com.example.rushi.studentdatabasehandler.utility.ImageSelectionUtility;

import static com.example.rushi.studentdatabasehandler.App.getAnalyticsUtility;
import static com.example.rushi.studentdatabasehandler.constants.IntentConstants.STUDENT;

public class AnalysisActivity extends AppCompatActivity implements View.OnClickListener
{
  private ActivityAnalysisBinding activityAnalysisBinding;
  private ImageSelectionUtility imageSelectionUtility;
  private static final String TAG = AnalysisActivity.class.getSimpleName();

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data)
  {
    Bitmap bitmap = imageSelectionUtility.getImageBitmapOnActivityResult(requestCode, resultCode, data);
    if(bitmap!=null) activityAnalysisBinding.imgStudentPhoto.setImageBitmap(bitmap);
  }

  @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults)
  {
    imageSelectionUtility.onRequestPermissionResult(requestCode, permissions, grantResults);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);

    activityAnalysisBinding = DataBindingUtil.setContentView(this, R.layout.activity_analysis);
    activityAnalysisBinding.btnGreen.setOnClickListener(this);
    activityAnalysisBinding.btnRed.setOnClickListener(this);
    activityAnalysisBinding.btnYellow.setOnClickListener(this);
    activityAnalysisBinding.btnSubmit.setOnClickListener(this);
    activityAnalysisBinding.imgStudentPhoto.setOnClickListener(this);
  }

  @Override public void onClick(View view)
  {
    switch (view.getId())
    {
      case R.id.btnRed:
        activityAnalysisBinding.rlMain.setBackgroundColor(Color.RED);
        getAnalyticsUtility().logColorSelectedEvent("red");
        break;
      case R.id.btnGreen:
        activityAnalysisBinding.rlMain.setBackgroundColor(Color.GREEN);
        getAnalyticsUtility().logColorSelectedEvent("green");
        break;
      case R.id.btnYellow:
        activityAnalysisBinding.rlMain.setBackgroundColor(Color.YELLOW);
        getAnalyticsUtility().logColorSelectedEvent("yellow");
        break;
      case R.id.btnSubmit:
        addStudent();
        break;
      case R.id.imgStudentPhoto:
        initialiseImageSelection();
        break;
    }
  }

  /**
   * Adds student in local db
   */
  private void addStudent()
  {
    Intent replyIntent = new Intent();
    Student student = new Student(activityAnalysisBinding.tietStudentFirstName.getText().toString(),
        activityAnalysisBinding.tietStudentLastName.getText().toString(),
        ((RadioButton) findViewById(activityAnalysisBinding.rgGroupColor.getCheckedRadioButtonId()))
            .getText()
            .toString());
    replyIntent.putExtra(STUDENT, student);
    setResult(RESULT_OK, replyIntent);
    finish();
  }

  private void initialiseImageSelection()
  {
    imageSelectionUtility = new ImageSelectionUtility(this);
    imageSelectionUtility.openImageSourceChooserDialog();
  }
}
