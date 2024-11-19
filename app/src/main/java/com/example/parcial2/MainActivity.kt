package com.example.parcial2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: Adapter
    private var driversList = mutableListOf<Driver>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerDrivers)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = Adapter(driversList) { driver ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("driver", driver) // Enviar datos al detalle
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        fetchDrivers()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun fetchDrivers() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = getRetrofit().create(ApiService::class.java).getDrivers()
            if (response.isSuccessful) {
                driversList.clear()
                driversList.addAll(response.body()?.MRData?.DriverTable?.Drivers ?: emptyList())
                runOnUiThread { adapter.notifyDataSetChanged() }
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://ergast.com/api/f1/2020/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
