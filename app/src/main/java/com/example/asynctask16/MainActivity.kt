package com.example.asynctask16

import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.asynctask16.databinding.ActivityMainBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var postAdapter: PostAdapter
    private lateinit var fetchDataTask: FetchDataTask

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        postAdapter = PostAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = postAdapter

        fetchDataTask = FetchDataTask(postAdapter)
        if (savedInstanceState == null) {
            fetchDataTask.execute()
        } else {
            val posts = savedInstanceState.getParcelableArrayList<Post>("posts")
            postAdapter.submitList(posts)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val posts = postAdapter.currentList.toMutableList()
        outState.putParcelableArrayList("posts", ArrayList(posts))
    }

    override fun onDestroy() {
        super.onDestroy()
        fetchDataTask.cancel(true)
    }

    private class FetchDataTask(private val postAdapter: PostAdapter) :
        AsyncTask<Void, Void, List<Post>>() {

        override fun doInBackground(vararg params: Void?): List<Post>? {
            try {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                val api = retrofit.create(ApiService::class.java)
                val response = api.getPosts().execute()

                if (response.isSuccessful) {
                    return response.body()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return null
        }

        override fun onPostExecute(result: List<Post>?) {
            result?.let {
                postAdapter.submitList(it)
            }
        }
    }

    interface ApiService {
        @GET("/posts")
        fun getPosts(): retrofit2.Call<List<Post>>
    }
}