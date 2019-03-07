package com.example.rushi.studentdatabasehandler.utility;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import com.example.rushi.studentdatabasehandler.BuildConfig;
import com.example.rushi.studentdatabasehandler.R;
import com.example.rushi.studentdatabasehandler.databinding.DlgImageChooserBinding;
import java.io.File;

import static com.example.rushi.studentdatabasehandler.constants.IntentConstants.REQUEST_IMAGE_CAMERA;
import static com.example.rushi.studentdatabasehandler.constants.IntentConstants.REQUEST_IMAGE_GALLERY;
import static com.example.rushi.studentdatabasehandler.utility.GeneralUtility.getScreenWidth;

public class ImageSelectionUtility implements View.OnClickListener
{
  private static final String TAG = ImageSelectionUtility.class.getSimpleName();
  private Context context;
  private Activity activity;
  private Fragment fragment;
  private Dialog imageSourceChooserDialog;

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
        break;
    }
    if(imageSourceChooserDialog.isShowing()) imageSourceChooserDialog.dismiss();
  }

  private void openGalleryImages()
  {
    Intent requestImageIntent = new Intent();
    requestImageIntent.setAction(Intent.ACTION_GET_CONTENT);
    requestImageIntent.setType("image/*");
    Log.d(TAG, "getImage: "+requestImageIntent.resolveActivity(context.getPackageManager()));
    if(fragment!=null) fragment.startActivityForResult(requestImageIntent, REQUEST_IMAGE_GALLERY);
    else if(activity!=null) activity.startActivityForResult(requestImageIntent, REQUEST_IMAGE_GALLERY);
  }

  private void openCamera()
  {
    File capturedImageFile = new File(Environment.getExternalStorageDirectory(), "temp.jpg");
    Intent requestImageIntent = new Intent();
    requestImageIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
    requestImageIntent.setType("image/*");
    requestImageIntent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(context,
        BuildConfig.APPLICATION_ID + ".provider", capturedImageFile));
    Log.d(TAG, "getImage: "+requestImageIntent.resolveActivity(context.getPackageManager()));
    if(fragment!=null) fragment.startActivityForResult(requestImageIntent, REQUEST_IMAGE_CAMERA);
    else if(activity!=null) activity.startActivityForResult(requestImageIntent, REQUEST_IMAGE_CAMERA);
  }
}
