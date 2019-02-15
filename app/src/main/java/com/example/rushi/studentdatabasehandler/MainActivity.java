package com.example.rushi.studentdatabasehandler;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.example.rushi.studentdatabasehandler.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
{
  ActivityMainBinding activityMainBinding;
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

    activityMainBinding.btnSubmit.setOnClickListener(new View.OnClickListener()
    {
      @Override public void onClick(View view)
      {
        if(activityMainBinding.etUserName.getText().toString().equals("admin")
            && activityMainBinding.etPassword.getText().toString().equals("admin"))
        {
          startActivity(new Intent(MainActivity.this, AnalysisActivity.class));
          finish();
        }
      }
    });
  }
}
