package com.example.parcial2

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    private lateinit var tvName: TextView
    private lateinit var tvNationality: TextView
    private lateinit var tvDateOfBirth: TextView
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        tvName = findViewById(R.id.textViewNameDetail)
        tvNationality = findViewById(R.id.textViewNationality)
        tvDateOfBirth = findViewById(R.id.textViewDateOfBirth)
        imageView = findViewById(R.id.imageViewDriverDetail)

        val driver: Driver = intent.getParcelableExtra("driver") ?: return

        tvName.text = "${driver.givenName} ${driver.familyName}"
        tvNationality.text = "Nationality: ${driver.nationality}"
        tvDateOfBirth.text = "Date of Birth: ${driver.dateOfBirth}"
        Glide.with(this).load(driver.image).into(imageView)
    }
}
