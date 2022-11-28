package com.example.camera_and_gallery

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.example.camera_and_gallery.database.User
import com.example.camera_and_gallery.database.UserDatabase
import com.example.camera_and_gallery.databinding.ActivityCameraBinding
import com.example.camera_and_gallery.databinding.ActivityGalleryBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class CameraActivity : AppCompatActivity() {
    lateinit var binding: ActivityCameraBinding
    lateinit var currentPhotoPath: String
    lateinit var photoUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnOldVersion.setOnClickListener {
            getImageFromCameraNewVersion()
        }

        val database = UserDatabase.getInstance(this)
        binding.save.setOnClickListener {
            val name = binding.etName.text.toString()
            val user = User(name, currentPhotoPath)
            println("loggg $currentPhotoPath")

            database.userDao().addUser(user)
        }
    }

    private fun getImageFromCameraNewVersion() {
        val imageFile = createImageFile()
        photoUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID, imageFile)
        getTakeImageContent.launch(photoUri)
    }

    private var getTakeImageContent =
        registerForActivityResult(ActivityResultContracts.TakePicture()) {
            if (it) {
                binding.iv.setImageURI(photoUri)
            }
        }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        //...../JPEG_13.08.2022.jpg
        val timeStamp: String =
            SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        ).apply {
            currentPhotoPath = absolutePath
        }
    }
}