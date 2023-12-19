package com.cvkcan.exchangeapp.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.provider.Settings.Global
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
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
    private val context : Context,
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
                println("SIDGHSDKJDLLD")
//                insertBasket()


//                GlobalScope.launch {
//                    val users = getUser()
//                    println(users)
//                }

            }
        }

        //region DB Area

        fun insertBasket() {
            GlobalScope.launch(Dispatchers.IO) {
                val basketDao: BasketDao = GeneralDatabase.getInstance(binding.root.context)
                    .basketDao()
                val basket = Basket(
                    44,
                    "EUR",
                    444
                )
                basketDao.insertBasket(basket)
            }
        }
        fun updateBasket(){
            GlobalScope.launch(Dispatchers.IO){
                val basketDao : BasketDao = GeneralDatabase.getInstance(binding.root.context)
                    .basketDao()
                val basket = Basket(
                    44,
                    "EUR",
                    111
                )
                basketDao.updateBasket(basket)
                val allBasket : List<Basket> = basketDao.getAllBaskets()
                println(allBasket.get(0).PerQuantity)
            }
        }
        fun deleteBasket(){
            GlobalScope.launch(Dispatchers.IO){
                val basketDao : BasketDao = GeneralDatabase.getInstance(binding.root.context)
                    .basketDao()
                val basketId : Int = 44
                basketDao.deleteBasketById(basketId)
                val allBasket : List<Basket> = basketDao.getAllBaskets()
                println(allBasket.get(0).PerQuantity)

            }
        }
        fun getBasket() {
            GlobalScope.launch(Dispatchers.IO){
                val basketDao : BasketDao = GeneralDatabase
                    .getInstance(binding.root.context)
                    .basketDao()
                basketDao.getAllBaskets()
            }
        }
        fun insertUser(){
            GlobalScope.launch(Dispatchers.IO){
                val userDao : UserInformationDao = GeneralDatabase.getInstance(binding.root.context)
                    .userInformationDao()
                val user = UserInformation(3131,
                    25000,
                    10,
                    250,
                    500)
                userDao.insertUserInformation(user)
                val allUser : List<UserInformation> = userDao.getAllUserInformation()
                println(allUser.get(0).Balance)
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
