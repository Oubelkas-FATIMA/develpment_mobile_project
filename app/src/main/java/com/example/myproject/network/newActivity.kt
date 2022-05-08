package com.example.myproject.network

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.myproject.R

class newActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)

        var imageUser: ImageView
        var userName: TextView
        var datePost: TextView
        var imagepost: ImageView
        var description: TextView
        var tag_0: Button
        var tag_2: Button
        var tag_3: Button

        imageUser = findViewById(R.id.imageUser)
        userName = findViewById(R.id.userName)
        datePost = findViewById(R.id.datePost)
        imagepost = findViewById(R.id.imagepost)
        tag_0 = findViewById(R.id.tag_0)
        tag_2 = findViewById(R.id.tag_2)
        tag_3 = findViewById(R.id.tag_3)
        description = findViewById(R.id.description)

        
        val imageUse = intent.getIntExtra("imageUser", 0)
        val postDate = intent.getStringExtra("postDate")
        val nameUser = intent.getStringExtra("nameUser")
        val postImage = intent.getIntExtra("postImage", 0)
        val descriptionS = intent.getStringExtra("description")
        val tag0 = intent.getStringExtra("tag_0")
        val tag1 = intent.getStringExtra("tag_1")
        val tag2 = intent.getStringExtra("tag_2")

        datePost.text = postDate
        userName.text = nameUser
        tag_0.text = tag0
        tag_2.text = tag1
        tag_3.text = tag2
        description.text = descriptionS

        imageUser.setImageResource(imageUse)
        imagepost.setImageResource(postImage)

    }
}