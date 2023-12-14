package com.cvkcan.exchangeapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProfileFragment : Fragment() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val giveAdvice = view.findViewById<Button>(R.id.giveAdvice)
        giveAdvice.setOnClickListener {
            val totalValue = ArrayList<String>()
            totalValue.add("2500 GOLD")
            totalValue.add("1222 EURO")
            val layout = LinearLayoutManager(this.context)
            val recyclerView: RecyclerView = requireView().findViewById(R.id.recyclerView2)
            recyclerView.layoutManager = layout
            val adapter = GiveAdviceRecyclerAdapter(totalValue)
            recyclerView.adapter = adapter
        }
        val aboutMyBasket = ArrayList<String>()
        aboutMyBasket.add("Boş veri yollanacak.")
        val layout = LinearLayoutManager(this.context)
        val recyclerView: RecyclerView = requireView().findViewById(R.id.recyclerView)
        recyclerView.layoutManager = layout
        val adapter = GetBasketRecyclerAdapter(aboutMyBasket)
        recyclerView.adapter = adapter

    }
}