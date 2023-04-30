package com.mungsil.myfirstproject

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

abstract class BaseActivity: AppCompatActivity() {
    abstract fun permissionGranted(requestCode: Int)
    abstract fun permissionDenied(requestCode: Int)

    fun requirePermissions(permissions: Array<String>, requestCode: Int) {
        //안드로이드 6.0 미만이면 permissionGranted
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            permissionGranted(requestCode)
        }
        else{
            val isAllPermissionsGranted=permissions.all{
                checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED }
            if (isAllPermissionsGranted) {
                permissionGranted(requestCode)
            }else{
                Log.d("권한","요청")
                ActivityCompat.requestPermissions(this, permissions, requestCode)
            }
        }
    }

    //사용자가 권한을 승인하거나 거부한 다음 호출되는 메서드

    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        if(grantResults[0]  == PackageManager.PERMISSION_GRANTED ){//승인
            permissionGranted(requestCode)
        }else{
            Log.d("권한","거부")
            permissionDenied(requestCode)//거부
        }

    }


}