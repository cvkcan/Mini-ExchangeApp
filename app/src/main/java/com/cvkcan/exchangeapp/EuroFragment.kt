package com.cvkcan.exchangeapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
                            GetCurrencyRecyclerViewAdapter(listOf(it), this@EuroFragment)

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

    override fun onItemClick(apiResponse: ApiResponse) {
        TODO("Not yet implemented")
    }
}
