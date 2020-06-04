package com.mad.frontliners.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mad.frontliners.R
import com.mad.frontliners.model.Appreciate
import com.mad.frontliners.Optimistic
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.rv_layout.view.*


class FrontlinerAdapter(val context: Optimistic, private val places: ArrayList<Appreciate>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        holder.itemView.discover_image.clipToOutline = true
        Picasso.get().load(places[position].url).into(holder.itemView.discover_image)

        holder.itemView.discover_image.resume()
        holder.itemView.discover_title.text = places[position].title
        holder.itemView.tour_description.text = places[position].description


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.rv_layout, parent, false)
        return ViewHolder(v)


    }

    override fun getItemCount(): Int {
        return places.size
    }

}

class ViewHolder(v: View?) : RecyclerView.ViewHolder(v!!), View.OnClickListener {
    override fun onClick(v: View?) {


    }

    init {
        itemView.setOnClickListener(this)
    }


    val image = itemView.discover_image!!
    val title = itemView.discover_image!!
    val desc = itemView.discover_image!!


}