package com.example.retrofitkotlin.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitkotlin.R
import com.example.retrofitkotlin.model.CryptoModel
import org.w3c.dom.Text

internal class CustomAdapter(private var itemsList: ArrayList<CryptoModel>) : RecyclerView.Adapter <CustomAdapter.MyViewHolder>() {

   inner class MyViewHolder(view : View) : RecyclerView.ViewHolder(view){

       var cagatay : TextView

       init {
           cagatay = itemView.findViewById(R.id.cagatay)
           //cagatay.setBackgroundColor(Color.RED)
       }


   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.cagatay.text = itemsList.get(position).toString()


    }

    override fun getItemCount(): Int {
        return itemsList.size
    }


}