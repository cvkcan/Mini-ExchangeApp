package com.cvkcan.exchangeapp

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.graphics.blue
import androidx.recyclerview.widget.RecyclerView

class GetCurrencyRecyclerViewAdapter(
    private val currencyTypes: List<ApiResponse>,
    private val listener: Listener):
    RecyclerView.Adapter<GetCurrencyRecyclerViewAdapter.CurrencyVH>(){
    interface Listener{
        fun onItemClick(apiResponse: ApiResponse)
    }
    class CurrencyVH (view : View) : RecyclerView.ViewHolder(view) {
        @SuppressLint("ResourceType")
        fun bind(currencies : ApiResponse, listener : Listener) {
            val bColor = itemView.findViewById<LinearLayout>(R.layout.recycler_getcurrency)
            if (currencies.usd.exchangeRatioDirection == "caret-up") {
                bColor.setBackgroundColor(Color.BLUE)
            }
            else if (currencies.usd.exchangeRatioDirection == "caret-down"){
                bColor.setBackgroundColor(Color.RED)
            }
            else
                bColor.setVerticalGravity(Color.YELLOW)
            itemView.setOnClickListener {
                listener.onItemClick(currencies)
            }
            itemView.findViewById<TextView>(R.id.currencyType).text = "USD"
            itemView.findViewById<TextView>(R.id.currencyBuyingPrice).text =
                currencies.usd.forexBuying
            itemView.findViewById<TextView>(R.id.currencySellingPrice).text =
                currencies.usd.forexSelling
            itemView.findViewById<TextView>(R.id.currencyVariant).text =
                currencies.usd.variation
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_getcurrency,parent,false)
        return CurrencyVH(view)
    }

    override fun getItemCount(): Int {
        return currencyTypes.size
    }

    override fun onBindViewHolder(holder: CurrencyVH, position: Int) {
        holder.bind(currencyTypes[position], listener)
    }
}