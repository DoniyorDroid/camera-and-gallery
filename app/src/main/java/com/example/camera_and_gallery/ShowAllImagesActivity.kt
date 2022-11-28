package com.example.camera_and_gallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.camera_and_gallery.adapters.UserAdapter
import com.example.camera_and_gallery.database.UserDatabase
import com.example.camera_and_gallery.databinding.ActivityShowAllImagesBinding

class ShowAllImagesActivity : AppCompatActivity() {
    lateinit var adapter: UserAdapter
    lateinit var binding: ActivityShowAllImagesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowAllImagesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = UserDatabase.getInstance(this)

        val list = database.userDao().getAllUser() as ArrayList
        adapter = UserAdapter(list)
        binding.rv.adapter = adapter
    }
}