package com.example.rushi.studentdatabasehandler;

import android.content.Intent;
import android.support.annotation.NonNull;

public interface IRequestPermissionResult
{
  void onRequestPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults);
}
