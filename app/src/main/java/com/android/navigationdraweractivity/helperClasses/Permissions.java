package com.android.navigationdraweractivity.helperClasses;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by manep on 8/21/2017.
 */

public class Permissions extends Activity {
    private static final int STORAGE_REQUEST_CODE = 222;
    private static final int CALL_REQUEST_CODE = 333;
    private static final int CAMERA_REQUEST_CODE = 444;

    private static boolean isStoragePermissionStatus,isCallPermissionStatus,isCameraPermissionStatus,isLocationPermissionStatus;



    public static void getStoragePermission(Activity activity) {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            isStoragePermissionStatus = true;
        else
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    STORAGE_REQUEST_CODE);
    }
    public static void getCallPermissions(Activity activity) {

        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED)
            isCallPermissionStatus = true;
       /* else
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST_CODE)*/;
    }
    public static void getCameraPermissons(Activity activity) {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
            isCameraPermissionStatus = true;
        else
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA},
                    CAMERA_REQUEST_CODE);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissions[0].equals(Manifest.permission.ACCESS_COARSE_LOCATION) || permissions[0].equals(Manifest.permission.ACCESS_FINE_LOCATION)) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                isLocationPermissionStatus = true;
        } else if (permissions[0].equals(Manifest.permission.CAMERA)) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                isCameraPermissionStatus = true;
        } else if (permissions[0].equals(Manifest.permission.CALL_PHONE)) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                isCallPermissionStatus = true;
        } else if (permissions[0].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                isStoragePermissionStatus = true;
        }
    }
}
