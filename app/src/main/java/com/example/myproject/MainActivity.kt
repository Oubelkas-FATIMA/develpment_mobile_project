package com.example.myproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myproject.network.RetrofitInstance
import com.example.myproject.network.RetrofitResponse
import com.example.myproject.network.newActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    
    lateinit var recyclerViewPost: RecyclerView
    lateinit var newArrayList: ArrayList<PostsResult>
    lateinit var toolbar : androidx.appcompat.widget.Toolbar
    lateinit var  imageSearch : ImageView
    lateinit var editSearch: EditText
    lateinit var postAdapter : PostsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerViewPost = findViewById(R.id.recyclerViewPost)
        toolbar = findViewById(R.id.toolbar2)
        imageSearch = findViewById(R.id.imageSearch)
        editSearch = findViewById(R.id.editSearch)


        setSupportActionBar(toolbar)

        val postsServices = RetrofitInstance.buildPostService()
        postsServices.getPostData().enqueue(object : Callback<RetrofitResponse> {
            override fun onResponse(
                call: Call<RetrofitResponse>,
                response: Response<RetrofitResponse>
            ) {
                if (response.isSuccessful) {
                    val result = response.body()
                    result?.let {
                        postAdapter = PostsAdapter(result.data)
                        newArrayList = ArrayList(result.data)
                        recyclerViewPost.apply {
                            layoutManager = LinearLayoutManager(this@MainActivity)
                            adapter = postAdapter
                        }
                        postAdapter.setOnItemClickListener(object : PostsAdapter.onItemClickListener{
                            override fun onItemClick(position: Int) {
                                val intent = Intent(this@MainActivity, newActivity::class.java)
                                intent.putExtra("imageUser", newArrayList[position].image)
                                intent.putExtra("postDate", newArrayList[position].publishDate)
                                intent.putExtra("nameUser", newArrayList[position].owner.firstName)
                                intent.putExtra("postImage", newArrayList[position].owner.picture)
                                intent.putExtra("description", newArrayList[position].text)
                                intent.putExtra("tag_0", newArrayList[position].tags[0])
                                intent.putExtra("tag_1", newArrayList[position].tags[1])
                                intent.putExtra("tag_2", newArrayList[position].tags[2])
                                startActivity(intent)

                            }

                        })
                    }
                }
            }

            override fun onFailure(call: Call<RetrofitResponse>, t: Throwable) {
                Log.d("MainActivity", "onFailure: ****************************** " + t.message)
                Toast.makeText(
                    applicationContext,
                    "Erreur serveur ou bien erreur connexion internet, verifier votre conn int",
                    Toast.LENGTH_LONG
                ).show()
            }
            /* recyclerViewPost.LayoutManager = LinearLayoutManager(this@MainActivity)
            recyclerViewPost.adapter = PostsAdapter(result) */


        })
        imageSearch.setOnClickListener{
            val search = editSearch.text.toString()
            postAdapter.filter.filter(search)

        }


    } // fin oncreate


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menue, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.itemAdd -> {
                Toast.makeText(this, "Add new post", Toast.LENGTH_LONG).show()
            }
            R.id.itemConfig -> {
                Toast.makeText(this, "Add configuration", Toast.LENGTH_LONG).show()

            }
            R.id.itemLogout -> {
                Toast.makeText(this, "Logout", Toast.LENGTH_LONG).show()

            }
        }
        return super.onOptionsItemSelected(item)
    }



}