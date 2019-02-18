package com.example.rushi.studentdatabasehandler.analytics.firebaseanalyticsutility;

import android.os.Bundle;
import com.example.rushi.studentdatabasehandler.analytics.AnalyticsConstants;
import com.google.firebase.analytics.FirebaseAnalytics;

import static com.example.rushi.studentdatabasehandler.App.getFirebaseAnalytics;

/**
 * Firebase Analytics Utility class to support logging events and adding user property when
 * necessary.
 * This will get firebase instance from app class and performs necessary actions.
 */
public class FAUtility
{
  public void logColorSelectedEvent(String color)
  {
    FirebaseAnalytics firebaseAnalytics = getFirebaseAnalytics();
    Bundle param = new Bundle();
    param.putString(AnalyticsConstants.CommonConstants.Param.COLOR, color);
    firebaseAnalytics.logEvent(AnalyticsConstants.CommonConstants.Event.COLOR_SELECTED, param);
  }
}
