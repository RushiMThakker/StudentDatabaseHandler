package com.example.rushi.studentdatabasehandler;

import android.app.Application;
import android.content.Context;
import com.example.rushi.studentdatabasehandler.analytics.AnalyticsUtility;
import com.example.rushi.studentdatabasehandler.analytics.firebaseanalyticsutility.FAUtility;
import com.example.rushi.studentdatabasehandler.utility.ImageSelectionUtility;
import com.google.firebase.analytics.FirebaseAnalytics;

public class App extends Application
{
  private static FirebaseAnalytics firebaseAnalytics;
  private static AnalyticsUtility analyticsUtility;
  private static FAUtility firebaseAnalyticsUtility;
  private static Context appContext;

  @Override public void onCreate()
  {
    super.onCreate();
    appContext = this;
  }

  public static FirebaseAnalytics getFirebaseAnalytics()
  {
    if(firebaseAnalytics==null)
    {
      firebaseAnalytics = FirebaseAnalytics.getInstance(appContext);
    }
    return firebaseAnalytics;
  }

  public static FAUtility getFirebaseAnalyticsUtility()
  {
    if(firebaseAnalyticsUtility==null)
    {
      firebaseAnalyticsUtility = new FAUtility();
    }
    return firebaseAnalyticsUtility;
  }

  public static AnalyticsUtility getAnalyticsUtility()
  {
    if(analyticsUtility==null)
    {
      analyticsUtility = new AnalyticsUtility();
    }
    return analyticsUtility;
  }
}
