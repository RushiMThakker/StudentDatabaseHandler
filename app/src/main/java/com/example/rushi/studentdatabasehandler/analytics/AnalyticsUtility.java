package com.example.rushi.studentdatabasehandler.analytics;

import static com.example.rushi.studentdatabasehandler.App.getFirebaseAnalyticsUtility;

public class AnalyticsUtility
{
  public void logColorSelectedEvent(String color)
  {
    getFirebaseAnalyticsUtility().logColorSelectedEvent(color);
  }
}
