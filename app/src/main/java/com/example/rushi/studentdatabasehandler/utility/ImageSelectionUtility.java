package com.example.rushi.studentdatabasehandler.utility;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import com.example.rushi.studentdatabasehandler.BuildConfig;
import com.example.rushi.studentdatabasehandler.IRequestPermissionResult;
import com.example.rushi.studentdatabasehandler.R;
import com.example.rushi.studentdatabasehandler.databinding.DlgImageChooserBinding;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_OK;
import static com.example.rushi.studentdatabasehandler.constants.IntentConstants.REQUEST_IMAGE_CAMERA;
import static com.example.rushi.studentdatabasehandler.constants.IntentConstants.REQUEST_IMAGE_GALLERY;
import static com.example.rushi.studentdatabasehandler.utility.GeneralUtility.getScreenWidth;
import static com.example.rushi.studentdatabasehandler.utility.PermissionUtility.MY_PERMISSIONS_REQUEST_CAMERA;
import static com.example.rushi.studentdatabasehandler.utility.PermissionUtility.MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE;
import static com.example.rushi.studentdatabasehandler.utility.PermissionUtility.getCameraPermission;
import static com.example.rushi.studentdatabasehandler.utility.PermissionUtility.getWriteExternalStoragePermission;

public class ImageSelectionUtility implements View.OnClickListener, IRequestPermissionResult
{
  private static final String TAG = ImageSelectionUtility.class.getSimpleName();
  private Context context;
  private Activity activity;
  private Fragment fragment;
  private Dialog imageSourceChooserDialog;
  private String currentPhotoPath;

  public ImageSelectionUtility(Activity activity)
  {
    this.context = this.activity = activity;
  }

  public ImageSelectionUtility(Context context, Fragment fragment)
  {
    this.context = context;
    this.fragment = fragment;
  }

  public void openImageSourceChooserDialog()
  {
    DlgImageChooserBinding dlgImageChooserBinding =
        DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.dlg_image_chooser, null,false);
    imageSourceChooserDialog = new Dialog(context, R.style.ExplodeFadeAnimationAlertDialog);
    imageSourceChooserDialog.setContentView(dlgImageChooserBinding.getRoot());
    dlgImageChooserBinding.rlCamera.setOnClickListener(this);
    dlgImageChooserBinding.rlGallery.setOnClickListener(this);
    int dialogWidth = (int)(getScreenWidth() - getScreenWidth()/4.0f);
    imageSourceChooserDialog.getWindow().setLayout(dialogWidth, CardView.LayoutParams.WRAP_CONTENT);
    imageSourceChooserDialog.show();
  }

  @Override public void onClick(View view)
  {
    switch (view.getId())
    {
      case R.id.rlGallery:
        openGalleryImages();
        break;
      case R.id.rlCamera:
        openCamera();
        //We are checking for WRITE_EXTERNAL_STORAGE permission because we are allowing camera app to write to file via file provider
        //if we only wanted to get thumbnail we could have got it via normal camera intent without requesting for permission even in manifest
        /*
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED)
        {
          if (fragment != null)
            getWriteExternalStoragePermission(fragment);
          else if (activity != null)
            getWriteExternalStoragePermission(activity);
        }else
        {
          openCamera();
        }
        */
        break;
    }
    if(imageSourceChooserDialog.isShowing()) imageSourceChooserDialog.dismiss();
  }

  private void openGalleryImages()
  {
    Intent requestImageIntent = new Intent();
    requestImageIntent.setAction(Intent.ACTION_GET_CONTENT);
    requestImageIntent.setType("image/*");

    if(requestImageIntent.resolveActivity(context.getPackageManager())!=null)
    {
      Log.d(TAG, "getImage: " + requestImageIntent.resolveActivity(context.getPackageManager()));
      if (fragment != null)
        fragment.startActivityForResult(requestImageIntent, REQUEST_IMAGE_GALLERY);
      else if (activity != null)
        activity.startActivityForResult(requestImageIntent, REQUEST_IMAGE_GALLERY);
    }
  }

  private void openCamera()
  {
      File capturedImageFile = null;
      try
      {
        /*
        capturedImageFile = createImageFile();
        Uri photoUri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider",
            capturedImageFile);
        Log.d(TAG, "openCamera: photoUri" + photoUri);
        */
        Intent requestImageIntent = new Intent();
        requestImageIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        //requestImageIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);

        if (requestImageIntent.resolveActivity(context.getPackageManager()) != null)
        {
          Log.d(TAG,
              "getImage: " + requestImageIntent.resolveActivity(context.getPackageManager()));
          if (fragment != null)
            fragment.startActivityForResult(requestImageIntent, REQUEST_IMAGE_CAMERA);
          else if (activity != null)
            activity.startActivityForResult(requestImageIntent, REQUEST_IMAGE_CAMERA);
        }
      } catch (Exception e)
      {
        Toast.makeText(context, "Error opening file for camera", Toast.LENGTH_SHORT).show();
        e.printStackTrace();
      }
  }

  private File createImageFile() throws IOException
  {
    // Create an image file name
    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    String imageFileName = "JPEG_" + timeStamp + "_";
    File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
    File image = File.createTempFile(
        imageFileName,  /* prefix */
        ".jpg",         /* suffix */
        storageDir      /* directory */
    );

    // Save a file: path for use with ACTION_VIEW intents
    currentPhotoPath = image.getAbsolutePath();
    return image;
  }

  @Override public void onRequestPermissionResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults)
  {
    switch (requestCode)
    {
      case MY_PERMISSIONS_REQUEST_CAMERA:
        if((permissions[0].equalsIgnoreCase(Manifest.permission.CAMERA))
            && grantResults.length>0
            && grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
          openCamera();
        }
        break;
      case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE:
        if((permissions[0].equalsIgnoreCase(Manifest.permission.WRITE_EXTERNAL_STORAGE))
            && grantResults.length>0
            && grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
          openCamera();
        }
        break;
    }
  }

  public Bitmap getImageBitmapOnActivityResult(int requestCode, int resultCode, Intent data)
  {
    Bitmap bitmap = null;
    switch (resultCode)
    {
      case RESULT_OK:
        switch (requestCode)
        {
          case REQUEST_IMAGE_CAMERA:
            bitmap = BitmapFactory.decodeFile(currentPhotoPath, null);
            break;
          case REQUEST_IMAGE_GALLERY:
            try
            {
              bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), data.getData());
            } catch (IOException e)
            {
              e.printStackTrace();
            }
            break;
        }
    }
    return bitmap;
  }
}
