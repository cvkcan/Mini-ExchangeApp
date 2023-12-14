package com.cvkcan.exchangeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GiveAdviceRecyclerAdapter(val getAdvice : ArrayList<String>)
    : RecyclerView.Adapter<GiveAdviceRecyclerAdapter.GiveAdviceVH>() {
    class GiveAdviceVH(itemView : View) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiveAdviceVH {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_giveadvice,parent,false)
        return GiveAdviceVH(itemView)
    }

    override fun getItemCount(): Int {
        return getAdvice.size
    }

    override fun onBindViewHolder(holder: GiveAdviceVH, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.setAdviceText).text = getAdvice.get(position)
    }
}