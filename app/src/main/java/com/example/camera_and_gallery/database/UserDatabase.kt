package com.example.camera_and_gallery.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private var appDatabase: UserDatabase? = null

        @Synchronized
        fun getInstance(context: Context): UserDatabase {
            if (appDatabase == null) {
                appDatabase =
                    Room.databaseBuilder(context, UserDatabase::class.java, "user_database")
                        .allowMainThreadQueries()
                        .build()
            }
            return appDatabase!!
        }

    }
}