package com.cvkcan.exchangeapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.text.trimmedLength
import androidx.recyclerview.widget.RecyclerView
import com.cvkcan.exchangeapp.R

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
        return getAdvice.count()
    }

    override fun onBindViewHolder(holder: GiveAdviceVH, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.setAdviceText).text = getAdvice[0]
    }
}