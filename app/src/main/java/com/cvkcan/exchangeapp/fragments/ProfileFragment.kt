package com.cvkcan.exchangeapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cvkcan.exchangeapp.adapters.GetBasketRecyclerAdapter
import com.cvkcan.exchangeapp.adapters.GiveAdviceRecyclerAdapter
import com.cvkcan.exchangeapp.R
import com.cvkcan.exchangeapp.model.Basket
import com.cvkcan.exchangeapp.roomdb.BasketDao
import com.cvkcan.exchangeapp.roomdb.GeneralDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

        val aboutMyBasket = ArrayList<Basket>()

        // Launch a coroutine to get the basket data
        GlobalScope.launch(Dispatchers.Main) {
            aboutMyBasket.addAll(getBasket())

            // Now you can use aboutMyBasket for further operations
            val layout = LinearLayoutManager(this@ProfileFragment.context)
            val recyclerView: RecyclerView = requireView().findViewById(R.id.recyclerView)
            recyclerView.layoutManager = layout
            val adapter = GetBasketRecyclerAdapter(aboutMyBasket)
            recyclerView.adapter = adapter
        }
    }

    suspend fun getBasket(): List<Basket> {
        return withContext(Dispatchers.IO) {
            val basketDao: BasketDao = GeneralDatabase
                .getInstance(requireContext())
                .basketDao()

            return@withContext basketDao.getAllBaskets()
        }
    }

}