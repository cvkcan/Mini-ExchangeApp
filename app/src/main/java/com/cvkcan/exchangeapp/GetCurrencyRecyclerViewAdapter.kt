package com.cvkcan.exchangeapp

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView

class GetCurrencyRecyclerViewAdapter(
    private val currencyTypes: List<ApiResponse>,
    private val listener: Listener,
    private val type : String):
    RecyclerView.Adapter<GetCurrencyRecyclerViewAdapter.CurrencyVH>(){
    interface Listener{
        fun onItemClick(apiResponse: ApiResponse)
    }
    class CurrencyVH(view: View) : RecyclerView.ViewHolder(view) {
        @SuppressLint("ResourceType")
        fun bind(currencies: ApiResponse, listener: Listener, type : String) {
            if (type == "USD"){
                val bColor = itemView.findViewById<LinearLayout>(R.id.dumendenDeneme)
                if (currencies.usd.exchangeRatioDirection == "caret-up") {
                    bColor.setBackgroundColor(Color.BLUE)
                } else if (currencies.usd.exchangeRatioDirection == "caret-down") {
                    bColor.setBackgroundColor(Color.RED)
                } else
                    bColor.setBackgroundColor(Color.YELLOW)

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
            else if(type == "EUR"){
                val bColor = itemView.findViewById<LinearLayout>(R.id.dumendenDeneme)
                if (currencies.euro.exchangeRatioDirection == "caret-up") {
                    bColor.setBackgroundColor(Color.BLUE)
                } else if (currencies.euro.exchangeRatioDirection == "caret-down") {
                    bColor.setBackgroundColor(Color.RED)
                } else
                    bColor.setBackgroundColor(Color.YELLOW)

                itemView.setOnClickListener {
                    listener.onItemClick(currencies)
                }
                itemView.findViewById<TextView>(R.id.currencyType).text = "EUR"
                itemView.findViewById<TextView>(R.id.currencyBuyingPrice).text =
                    currencies.euro.forexBuying
                itemView.findViewById<TextView>(R.id.currencySellingPrice).text =
                    currencies.euro.forexSelling
                itemView.findViewById<TextView>(R.id.currencyVariant).text =
                    currencies.euro.variation
            }
            else {
                val bColor = itemView.findViewById<LinearLayout>(R.id.dumendenDeneme)
                if (currencies.gold.exchangeRatioDirection == "caret-up") {
                    bColor.setBackgroundColor(Color.BLUE)
                } else if (currencies.gold.exchangeRatioDirection == "caret-down") {
                    bColor.setBackgroundColor(Color.RED)
                } else
                    bColor.setBackgroundColor(Color.YELLOW)

                itemView.setOnClickListener {
                    listener.onItemClick(currencies)
                }
                itemView.findViewById<TextView>(R.id.currencyType).text = "GOLD"
                itemView.findViewById<TextView>(R.id.currencyBuyingPrice).text =
                    currencies.gold.forexBuying
                itemView.findViewById<TextView>(R.id.currencySellingPrice).text =
                    currencies.gold.forexSelling
                itemView.findViewById<TextView>(R.id.currencyVariant).text =
                    currencies.gold.variation
            }
            itemView.findViewById<Button>(R.id.redirectBrowser).setOnClickListener {
                redirectUrl()
            }
            itemView.findViewById<Button>(R.id.buyCurrency).setOnClickListener {

            }
        }
        fun redirectUrl(){
            val msg = AlertDialog.Builder(itemView.context)
            msg.setTitle("Routing Screen")
            msg.setMessage("Have it be redirected to the Trade page?")
            msg.setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
            })
            msg.setPositiveButton("Yes") {DialogInterface, i ->
                val intent = Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.isyatirim.com.tr/tr-tr/Sayfalar/default.aspx"))
                itemView.context.startActivity(intent)
            }
            msg.show()
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
        holder.bind(currencyTypes[position], listener,type)
    }


}