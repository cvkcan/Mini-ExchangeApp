package com.cvkcan.exchangeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class GetBasketRecyclerAdapter(val getBasketDatas : ArrayList<String>)
    : RecyclerView.Adapter<GetBasketRecyclerAdapter.MyBasketVH>(){
    class MyBasketVH(itemView : View) : RecyclerView.ViewHolder(itemView) {

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyBasketVH {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_getbasket,parent,false)
        return MyBasketVH(itemView)
    }

    override fun getItemCount(): Int {
        return getBasketDatas.size
    }

    override fun onBindViewHolder(holder: MyBasketVH, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.getBasketText).text =
            getBasketDatas.get(position)
    }
}