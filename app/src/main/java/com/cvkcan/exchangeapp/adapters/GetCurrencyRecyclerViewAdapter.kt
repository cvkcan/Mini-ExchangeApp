package com.cvkcan.exchangeapp.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.cvkcan.exchangeapp.R
import com.cvkcan.exchangeapp.apis.ApiResponse
import com.cvkcan.exchangeapp.databinding.RecyclerGetcurrencyBinding
import com.cvkcan.exchangeapp.model.Basket
import com.cvkcan.exchangeapp.model.UserInformation
import com.cvkcan.exchangeapp.roomdb.BasketDao
import com.cvkcan.exchangeapp.roomdb.GeneralDatabase
import com.cvkcan.exchangeapp.roomdb.UserInformationDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GetCurrencyRecyclerViewAdapter(
    private val currencyTypes: List<ApiResponse>,
    private val listener: Listener,
    private val type: String,

) :
    RecyclerView.Adapter<GetCurrencyRecyclerViewAdapter.CurrencyVH>() {
    interface Listener {
        fun onItemClick(apiResponse: ApiResponse)
    }

    class CurrencyVH(private val binding: RecyclerGetcurrencyBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("ResourceType")
        fun bind(currencies: ApiResponse, listener: Listener, type: String) {
            val bColor = binding.dumendenDeneme

            when {
                currencies.usd.exchangeRatioDirection == "caret-up" -> bColor.setBackgroundColor(Color.BLUE)
                currencies.usd.exchangeRatioDirection == "caret-down" -> bColor.setBackgroundColor(Color.RED)
                else -> bColor.setBackgroundColor(Color.YELLOW)
            }

            itemView.setOnClickListener {
                listener.onItemClick(currencies)
            }

            binding.currencyType.text = when (type) {
                "USD" -> "USD"
                "EUR" -> "EUR"
                else -> "GOLD"
            }

            binding.currencyBuyingPrice.text = when (type) {
                "USD" -> currencies.usd.forexBuying
                "EUR" -> currencies.euro.forexBuying
                else -> currencies.gold.forexBuying
            }

            binding.currencySellingPrice.text = when (type) {
                "USD" -> currencies.usd.forexSelling
                "EUR" -> currencies.euro.forexSelling
                else -> currencies.gold.forexSelling
            }

            binding.currencyVariant.text = when (type) {
                "USD" -> currencies.usd.variation
                "EUR" -> currencies.euro.variation
                else -> currencies.gold.variation
            }

            binding.redirectBrowser.setOnClickListener {
                redirectUrl()
            }

            binding.buyCurrency.setOnClickListener {
                when (type) {
                    "USD" -> {
//                        insertBasket("USD")
//                        deleteBasket("USD")
                        updateBasket("USD")
                    }
                    "EUR" -> {
//                        insertBasket("EUR")
//                        deleteBasket("EUR")
                        updateBasket("EUR")
                    }
                    "GOLD" -> {
//                        insertBasket("GOLD")
//                        deleteBasket("GOLD")
                        updateBasket("GOLD")
                    }
                }

//                GlobalScope.launch {
//                    val users = getUser()
//                    println(users)
//                }

            }
        }

        //region DB Area

        fun insertBasket(type : String) {
            GlobalScope.launch(Dispatchers.IO) {
                val basketDao: BasketDao = GeneralDatabase.getInstance(binding.root.context)
                    .basketDao()
                val basket = Basket(
                    when (type){
                        "USD" ->{
                            11
                        }
                        "EUR" ->{
                            22
                        }

                        else -> {33}
                    },
                    type,
                    itemView.findViewById<EditText>(R.id.quantityText).text.toString().toInt()
                )
                println(type)
                basketDao.insertBasket(basket)
            }
        }
        fun updateBasket(type : String){
            var id = 0
            when (type){
                "USD" ->{
                    id = 11
                }
                "EUR" ->{
                    id = 22
                }
                "GOLD" -> {id = 33}
            }
            GlobalScope.launch(Dispatchers.IO){
                val basketDao : BasketDao = GeneralDatabase.getInstance(binding.root.context)
                    .basketDao()
                var getQuantity = basketDao.getBasketById(id)
                val basket = Basket(
                    id,
                    type,
                    itemView.findViewById<EditText>(R.id.quantityText).text
                        .toString().toInt() + getQuantity.PerQuantity.toString().toInt()
                )
                basketDao.updateBasket(basket)

            }
            Toast.makeText(binding.root.context, "The Purchase was succesfull!"
                , Toast.LENGTH_LONG).show()
        }
        fun deleteBasket(type : String){
            GlobalScope.launch(Dispatchers.IO){
                val basketDao : BasketDao = GeneralDatabase.getInstance(binding.root.context)
                    .basketDao()
                val basketId : Int = 11
                basketDao.deleteBasketById(basketId)
                val allBasket : List<Basket> = basketDao.getAllBaskets()
            }
        }
        suspend fun getBasket() : List<Basket> {
            return withContext(Dispatchers.IO){
                val basketDao : BasketDao = GeneralDatabase
                    .getInstance(binding.root.context)
                    .basketDao()

                return@withContext basketDao.getAllBaskets()
            }
        }

        fun insertUser(){
            GlobalScope.launch(Dispatchers.IO){
                val userDao : UserInformationDao = GeneralDatabase.getInstance(binding.root.context)
                    .userInformationDao()
                val user = UserInformation(22,
                    25000,
                    10,
                    250,
                    500)
                userDao.insertUserInformation(user)
                val allUser : List<UserInformation> = userDao.getAllUserInformation()
            }
        }
        fun deleteUser(){
            GlobalScope.launch(Dispatchers.IO){
                val userDao : UserInformationDao = GeneralDatabase
                    .getInstance(binding.root.context)
                    .userInformationDao()
                val userId : Int = 3131
                userDao.deleteUserInformationById(userId)
            }
        }

        fun updateUser(){
            GlobalScope.launch(Dispatchers.IO){
                val userDao : UserInformationDao = GeneralDatabase
                    .getInstance(binding.root.context)
                    .userInformationDao()
                val user = UserInformation(3131,
                    99999,
                    11,
                    22,
                    33)
                userDao.updateUserInformation(user)
            }
        }
        suspend fun getUser(): List<UserInformation> {
            return withContext(Dispatchers.IO) {
                val userDao: UserInformationDao = GeneralDatabase
                    .getInstance(binding.root.context)
                    .userInformationDao()

                return@withContext userDao.getAllUserInformation()
            }
        }


        //endregion
        private fun redirectUrl() {
            val msg = AlertDialog.Builder(itemView.context)
            msg.setTitle("Routing Screen")
            msg.setMessage("Have it be redirected to the Trade page?")
            msg.setNegativeButton("No", DialogInterface.OnClickListener { dialog, which -> })

            msg.setPositiveButton("Yes") { _, _ ->
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.isyatirim.com.tr/tr-tr/Sayfalar/default.aspx")
                )
                itemView.context.startActivity(intent)
            }

            msg.show()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyVH {
        val binding = RecyclerGetcurrencyBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CurrencyVH(binding)
    }

    override fun getItemCount(): Int = currencyTypes.size

    override fun onBindViewHolder(holder: CurrencyVH, position: Int) {
        holder.bind(currencyTypes[position], listener, type)
    }
}
