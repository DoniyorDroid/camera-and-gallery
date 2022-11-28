package com.example.camera_and_gallery

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.camera_and_gallery.databinding.ActivityGalleryBinding
import java.io.File
import java.io.FileOutputStream

class GalleryActivity : AppCompatActivity() {
    lateinit var binding: ActivityGalleryBinding
    val REQUEST_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnNewVersion.setOnClickListener {
            getImageFromGalleryNewVersion()
        }
    }

    // old
    private fun getImageFromGalleryOldVersion() {
        startActivityForResult(Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "image/*"
        }, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (REQUEST_CODE == requestCode && resultCode == Activity.RESULT_OK) {
            val uri = data?.data
            binding.iv.setImageURI(uri)

            val stream = contentResolver.openInputStream(uri!!)
            val file = File(filesDir, "image.jpg")
            val outputStream = FileOutputStream(file)
            stream?.copyTo(outputStream)
            stream?.close()
            val path = file.absolutePath

            Toast.makeText(this, path, Toast.LENGTH_SHORT).show()
        }
    }

    // new
    private val getImageContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri ?: return@registerForActivityResult
            binding.iv.setImageURI(uri)

            val stream = contentResolver.openInputStream(uri)
            val file = File(filesDir, "image.jpg")
            val outputStream = FileOutputStream(file)
            stream?.copyTo(outputStream)
            stream?.close()
            val path = file.absolutePath

            Toast.makeText(this, path, Toast.LENGTH_SHORT).show()
        }

    private fun getImageFromGalleryNewVersion() {
        getImageContent.launch("image/*")
    }
}