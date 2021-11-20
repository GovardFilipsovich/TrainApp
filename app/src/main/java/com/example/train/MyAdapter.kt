package com.example.train

import Country
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MyAdapter(private val countries: List<Country>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var text1: TextView? = null
        var text2: TextView? = null
        var img: ImageView? = null

        init {
            text1 = itemView.findViewById(R.id.name_country)
            text2 = itemView.findViewById(R.id.popul)
            img = itemView.findViewById(R.id.img_head)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_flags, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.text1?.text = countries[position].name
        holder.text2?.text = "population " + countries[position].population
        Picasso.get().load(countries[position].url_png).fit().centerCrop().into(holder.img)
    }

    override fun getItemCount() = countries.size
}