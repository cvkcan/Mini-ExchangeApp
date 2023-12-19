package com.cvkcan.exchangeapp.fragments

import android.content.Context
import android.content.pm.PackageManager.ResolveInfoFlags
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.cvkcan.exchangeapp.MainActivity
import com.cvkcan.exchangeapp.R
import com.cvkcan.exchangeapp.adapters.GetCurrencyRecyclerViewAdapter
import com.cvkcan.exchangeapp.apis.ApiResponse
import com.cvkcan.exchangeapp.apis.CurrencyAPI
import com.cvkcan.exchangeapp.databinding.RecyclerGetcurrencyBinding
import com.cvkcan.exchangeapp.model.Basket
import com.cvkcan.exchangeapp.roomdb.BasketDao
import com.cvkcan.exchangeapp.roomdb.GeneralDatabase
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EuroFragment : Fragment(), GetCurrencyRecyclerViewAdapter.Listener {
    private val BASE_URL: String = "https://api.genelpara.com/"
    private var currencyModels: ApiResponse? = null
    private var getCurrencyRecyclerViewAdapter : GetCurrencyRecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this.context)
        view?.findViewById<RecyclerView>(R.id.euroRecyclerView)?.layoutManager = layoutManager
        loadData()

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize your RecyclerView and adapter here
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(requireContext())
        view.findViewById<RecyclerView>(R.id.euroRecyclerView)?.layoutManager = layoutManager

        getCurrencyRecyclerViewAdapter?.let {
            view.findViewById<RecyclerView>(R.id.euroRecyclerView)?.adapter = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_euro, container, false)
    }

    private fun loadData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(CurrencyAPI::class.java)
        val callData = service.getData()

        callData.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    apiResponse?.let {
                        currencyModels = it
                        getCurrencyRecyclerViewAdapter =
                            GetCurrencyRecyclerViewAdapter(requireContext(),listOf(it),
                                this@EuroFragment,"EUR")

                        // Set the adapter here after it's initialized
                        view?.findViewById<RecyclerView>(R.id.euroRecyclerView)?.adapter =
                            getCurrencyRecyclerViewAdapter
                    }
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    fun executionBasket() {
        val basketDao: BasketDao = GeneralDatabase.getInstance(requireContext()).basketDao()
        val basket = Basket(
            44,
            "EUR",
            444
        )
        basketDao.insertBasket(basket)
        val allBaskets: List<Basket> = basketDao.getAllBaskets()
        println(allBaskets.get(0).PerQuantity)
    }

    override fun onItemClick(apiResponse: ApiResponse) {
        //
    }
}

