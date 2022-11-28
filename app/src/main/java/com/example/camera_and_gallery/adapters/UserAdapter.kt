package com.example.camera_and_gallery.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.camera_and_gallery.R
import com.example.camera_and_gallery.database.User
import com.example.camera_and_gallery.databinding.ItemUserBinding

class UserAdapter(val list: ArrayList<User>) : RecyclerView.Adapter<UserAdapter.MyViewHolder>() {
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(user: User) {
            val bind = ItemUserBinding.bind(itemView)

            bind.tv.text = user.name
            bind.iv.setImageURI(Uri.parse(user.imgPath))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size
}