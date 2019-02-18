package com.example.rushi.studentdatabasehandler;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.example.rushi.studentdatabasehandler.databinding.ActivityAnalysisBinding;

import static com.example.rushi.studentdatabasehandler.App.getAnalyticsUtility;

public class AnalysisActivity extends AppCompatActivity implements View.OnClickListener
{
  ActivityAnalysisBinding activityAnalysisBinding;
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);

    activityAnalysisBinding = DataBindingUtil.setContentView(this,R.layout.activity_analysis);
    activityAnalysisBinding.btnGreen.setOnClickListener(this);
    activityAnalysisBinding.btnRed.setOnClickListener(this);
    activityAnalysisBinding.btnYellow.setOnClickListener(this);
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
    }
  }
}
