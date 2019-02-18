package com.example.rushi.studentdatabasehandler.analytics;

import com.example.rushi.studentdatabasehandler.App;
import com.example.rushi.studentdatabasehandler.analytics.firebaseanalyticsutility.FAUtility;
import com.google.firebase.analytics.FirebaseAnalytics;

import static com.example.rushi.studentdatabasehandler.App.getFirebaseAnalytics;
import static com.example.rushi.studentdatabasehandler.App.getFirebaseAnalyticsUtility;

public class AnalyticsUtility
{
  public void logColorSelectedEvent(String color)
  {
    getFirebaseAnalyticsUtility().logColorSelectedEvent(color);
  }
}
