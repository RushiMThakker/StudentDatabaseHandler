package com.example.rushi.studentdatabasehandler.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;
import com.example.rushi.studentdatabasehandler.BuildConfig;
import com.example.rushi.studentdatabasehandler.R;
import com.example.rushi.studentdatabasehandler.databinding.ActivityAnalysisBinding;
import com.example.rushi.studentdatabasehandler.db.Student;
import com.example.rushi.studentdatabasehandler.utility.ImageSelectionUtility;
import java.io.File;
import java.io.IOException;

import static com.example.rushi.studentdatabasehandler.App.getAnalyticsUtility;
import static com.example.rushi.studentdatabasehandler.constants.IntentConstants.REQUEST_IMAGE_CAMERA;
import static com.example.rushi.studentdatabasehandler.constants.IntentConstants.REQUEST_IMAGE_GALLERY;
import static com.example.rushi.studentdatabasehandler.constants.IntentConstants.STUDENT;

public class AnalysisActivity extends AppCompatActivity implements View.OnClickListener
{
  private ActivityAnalysisBinding activityAnalysisBinding;
  private static final String TAG = AnalysisActivity.class.getSimpleName();

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data)
  {
    switch (resultCode)
    {
      case RESULT_OK:
        switch (requestCode)
        {
          case REQUEST_IMAGE_CAMERA:
            Toast.makeText(this, "Uri of selected image is:"+data.getData(), Toast.LENGTH_LONG).show();
            break;
          case REQUEST_IMAGE_GALLERY:
            Bitmap bitmap = null;
            try
            {
              bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
            } catch (IOException e)
            {
              e.printStackTrace();
            }
            activityAnalysisBinding.imgStudentPhoto.setImageBitmap(bitmap);
            break;

        }
    }
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
        getImage();
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
        ((RadioButton)findViewById(activityAnalysisBinding.rgGroupColor.getCheckedRadioButtonId())).getText().toString());
    replyIntent.putExtra(STUDENT, student);
    setResult(RESULT_OK, replyIntent);
    finish();
  }

  private void getImage()
  {
    ImageSelectionUtility imageSelectionUtility = new ImageSelectionUtility(this);
    imageSelectionUtility.openImageSourceChooserDialog();
  }
}
