package com.example.camera_and_gallery.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    var name: String,
    var imgPath: String
) {
    @PrimaryKey
    var id: Int? = null
}
