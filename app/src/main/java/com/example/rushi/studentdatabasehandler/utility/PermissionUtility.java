package com.example.rushi.studentdatabasehandler.utility;

import android.Manifest;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;

public class PermissionUtility
{
  public static final int MY_PERMISSIONS_REQUEST_CAMERA = 300;
  public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 301;

  public static void getCameraPermission(Activity activity)
  {
    // Permission is not granted
    // Should we show an explanation?
    if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA))
    {
      // Show an explanation to the user *asynchronously* -- don't block
      // this thread waiting for the user's response! After the user
      // sees the explanation, try again to request the permission.
    } else
    {
      // No explanation needed; request the permission
      ActivityCompat.requestPermissions(activity, new String[] { Manifest.permission.CAMERA }, MY_PERMISSIONS_REQUEST_CAMERA);

      // MY_PERMISSIONS_REQUEST_CAMERA is an
      // app-defined int constant. The callback method gets the
      // result of the request.
    }
  }

  public static void getCameraPermission(Fragment fragment)
  {
    // Permission is not granted
    // Should we show an explanation?
    if (fragment.shouldShowRequestPermissionRationale(Manifest.permission.CAMERA))
    {
      // Show an explanation to the user *asynchronously* -- don't block
      // this thread waiting for the user's response! After the user
      // sees the explanation, try again to request the permission.
    } else
    {
      // No explanation needed; request the permission
      fragment.requestPermissions(new String[] { Manifest.permission.CAMERA }, MY_PERMISSIONS_REQUEST_CAMERA);

      // MY_PERMISSIONS_REQUEST_CAMERA is an
      // app-defined int constant. The callback method gets the
      // result of the request.
    }
  }

  public static void getWriteExternalStoragePermission(Activity activity)
  {
    // Permission is not granted
    // Should we show an explanation?
    if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE))
    {
      // Show an explanation to the user *asynchronously* -- don't block
      // this thread waiting for the user's response! After the user
      // sees the explanation, try again to request the permission.
    } else
    {
      // No explanation needed; request the permission
      ActivityCompat.requestPermissions(activity, new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE },
          MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);

      // MY_PERMISSIONS_REQUEST_CAMERA is an
      // app-defined int constant. The callback method gets the
      // result of the request.
    }
  }

  public static void getWriteExternalStoragePermission(Fragment fragment)
  {
    // Permission is not granted
    // Should we show an explanation?
    if (fragment.shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE))
    {
      // Show an explanation to the user *asynchronously* -- don't block
      // this thread waiting for the user's response! After the user
      // sees the explanation, try again to request the permission.
    } else
    {
      // No explanation needed; request the permission
      fragment.requestPermissions(new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE },
          MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);

      // MY_PERMISSIONS_REQUEST_CAMERA is an
      // app-defined int constant. The callback method gets the
      // result of the request.
    }
  }
}
