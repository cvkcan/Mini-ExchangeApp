package com.cvkcan.exchangeapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cvkcan.exchangeapp.adapters.GetBasketRecyclerAdapter
import com.cvkcan.exchangeapp.adapters.GiveAdviceRecyclerAdapter
import com.cvkcan.exchangeapp.R
import com.cvkcan.exchangeapp.model.Basket
import com.cvkcan.exchangeapp.model.UserInformation
import com.cvkcan.exchangeapp.roomdb.BasketDao
import com.cvkcan.exchangeapp.roomdb.GeneralDatabase
import com.cvkcan.exchangeapp.roomdb.UserInformationDao
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
            totalValue.add("Advice")
            val layout = LinearLayoutManager(this.context)
            val recyclerView: RecyclerView = requireView().findViewById(R.id.recyclerView2)
            recyclerView.layoutManager = layout
            val adapter = GiveAdviceRecyclerAdapter(totalValue)
            recyclerView.adapter = adapter
        }
        updateBalanceText()
        val aboutMyBasket = ArrayList<Basket>()
        GlobalScope.launch(Dispatchers.Main) {
            aboutMyBasket.addAll(getBasket())
            val layout = LinearLayoutManager(this@ProfileFragment.context)
            val recyclerView: RecyclerView = requireView().findViewById(R.id.recyclerView)
            recyclerView.layoutManager = layout
            val adapter = GetBasketRecyclerAdapter(aboutMyBasket)
            recyclerView.adapter = adapter
        }
        view.findViewById<Button>(R.id.editBalanceButton).setOnClickListener {
            updateUser()
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
    suspend fun getUser(): List<UserInformation> {
        return withContext(Dispatchers.IO) {
            val userDao: UserInformationDao = GeneralDatabase
                .getInstance(requireContext())
                .userInformationDao()

            return@withContext userDao.getAllUserInformation()
        }
    }
    fun updateUser(){
        var moneyCounter = view?.findViewById<EditText>(R.id.moneyCountText)
        if (moneyCounter!!.text.isNotEmpty()){
            GlobalScope.launch(Dispatchers.IO){
                var previousBalance = getUser().get(0).Balance
                val userDao : UserInformationDao = GeneralDatabase
                    .getInstance(requireContext())
                    .userInformationDao()
                val user = UserInformation(999,
                    moneyCounter.text.toString().toInt() + previousBalance,
                    getUser().get(0).GoldQuantity,
                    getUser().get(0).EurQuantity,
                    getUser().get(0).UsdQuantity)
                userDao.updateUserInformation(user)
            }
            updateBalanceText()
            Toast.makeText(requireContext(), "Balance was updated!"
                , Toast.LENGTH_LONG).show()

        }
        Toast.makeText(requireContext(), "Please fill the money count area."
            , Toast.LENGTH_LONG).show()
    }
    fun updateBalanceText(){
        GlobalScope.launch(Dispatchers.Main){
            var balance : Int = 0
            if  (getUser().isNotEmpty()){
                balance  = getUser().get(0).Balance
            }
            view?.findViewById<TextView>(R.id.showBalanceText)?.text =
                "Balance : " + balance.toString()
        }
    }

}