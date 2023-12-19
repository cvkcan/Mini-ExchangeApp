package com.cvkcan.exchangeapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cvkcan.exchangeapp.R
import com.cvkcan.exchangeapp.model.Basket

class GetBasketRecyclerAdapter(val getBasketDatas : ArrayList<Basket>)
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
            getBasketDatas.get(position).CurrencieTypes+ "\n" +
                    getBasketDatas.get(position).PerQuantity
    }
}