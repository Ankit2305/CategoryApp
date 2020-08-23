package com.example.categoryapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.categoryapp.adapters.CategoryAdapter
import com.example.categoryapp.models.Category
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    val API_URL = "https://run.mocky.io/v3/f79cbce1-a70e-4313-8d76-00d19ee3b4c1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        setTitle("Category")
        window.statusBarColor = Color.WHITE

        recyclerView.layoutManager = LinearLayoutManager(this)
        fetchJSON()
    }

    fun fetchJSON() {
        progressBar.visibility = View.VISIBLE
        val request = Request.Builder().url(API_URL).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback{
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread{
                    Toast.makeText(this@MainActivity, "Some error occured while fetching data", Toast.LENGTH_SHORT).show()
                    progressBar.visibility = View.GONE
                }
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()

                val gson = Gson()
                val categories = gson.fromJson(body, Array<Category>::class.java)

                runOnUiThread{
                    recyclerView.adapter = CategoryAdapter(categories)
                    progressBar.visibility = View.GONE
                }
            }

        })
    }
}