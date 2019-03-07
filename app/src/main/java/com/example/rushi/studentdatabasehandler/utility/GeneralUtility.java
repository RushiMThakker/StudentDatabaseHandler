package com.example.rushi.studentdatabasehandler.utility;

import android.content.Context;
import android.content.res.Resources;

public class GeneralUtility
{
  public static int getScreenWidth()
  {
    return Resources.getSystem().getDisplayMetrics().widthPixels;
  }

  public static int getScreenHeight()
  {
    return Resources.getSystem().getDisplayMetrics().heightPixels;
  }
}
