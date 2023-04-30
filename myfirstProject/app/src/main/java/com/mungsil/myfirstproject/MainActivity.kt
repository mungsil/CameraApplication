package com.mungsil.myfirstproject

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.mungsil.myfirstproject.databinding.ActivityMainBinding
import java.util.logging.Logger

class MainActivity : BaseActivity() {

    val PERM_STORAGE=99 // 외부 저장소 권한 처리
    val PERM_CAMERA=100 // 카메라 권한 처리
    val REQ_CAMERA=101 // 카메라 촬영 요청

    val binding by lazy{ActivityMainBinding.inflate(layoutInflater)} //뷰 바인딩 방식 사용

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //외부 저장소 권한 허용
        requirePermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),PERM_STORAGE)

        // 드라이브에서 불러 오기 버튼 클릭 시 해당 메소드 실행
        binding.findByDriveBtn.setOnClickListener{
            binding.findByDriveBtn.text="드라이브 불러오는 중"
        }

    }

    override fun permissionGranted(requestCode: Int){
        when (requestCode) {
            PERM_STORAGE -> setViews()
            PERM_CAMERA -> openCamera()

        }
    }

    private fun setViews() {
        binding.takePictureBtn.setOnClickListener{
            requirePermissions(arrayOf(Manifest.permission.CAMERA),PERM_CAMERA)
        }
    }

    fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)


    }

    override fun permissionDenied(requestCode: Int){
        when (requestCode) {
            PERM_STORAGE -> {
                Toast.makeText(
                    baseContext,
                    "외부 저장소 권한을 승인해야 앱을 사용할 수 있습니다.",
                    Toast.LENGTH_LONG
                ).show()
                //finish()
            }

            PERM_CAMERA -> {
                Toast.makeText(baseContext,
                                "카메라 권한을 승인해야 카메라를 사용할 수 있습니다.",
                                Toast.LENGTH_LONG).show()
            }

        }
    }

}