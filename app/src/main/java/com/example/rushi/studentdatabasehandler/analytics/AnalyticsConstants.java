package com.example.rushi.studentdatabasehandler.analytics;

import com.example.rushi.studentdatabasehandler.BuildConfig;

public class AnalyticsConstants
{
  /**
   * This constant is used to identify which analytics tool shall be used. These would be useful
   * when we want to compare firebase and flurry. For demo purpose, I shall be going through experience booking flow
   * and logging events and user properties for the same.
   *  1 - Firebase
   *  2 - Facebook
   *  3 - Both tools
   */
  private static final int  ANALYTICS_TOOL= 3;
  public static final int isDebugAnalyticsOn = BuildConfig.DEBUG?ANALYTICS_TOOL:-1;

  public static class CommonConstants
  {
    public static  class Event
    {
      public static final String COLOR_SELECTED = "color_selected";
    }

    public static  class Param
    {
      //Params for login event
      public static final String COLOR = "color";
    }
  }
}
