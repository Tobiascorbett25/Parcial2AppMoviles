package com.example.parcial2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class Adapter(private val drivers: List<Driver>, private val onItemClickListener: (Driver) -> Unit) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val name: TextView = view.findViewById(R.id.textViewName)
        private val imageView: ImageView = view.findViewById(R.id.imageViewDriver)

        fun bind(driver: Driver) {
            name.text = "${driver.givenName} ${driver.familyName}"
            Glide.with(view.context).load(driver.image).into(imageView)

            view.setOnClickListener { onItemClickListener(driver) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_list_driver, parent, false))
    }

    override fun getItemCount(): Int = drivers.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(drivers[position])
    }
}
