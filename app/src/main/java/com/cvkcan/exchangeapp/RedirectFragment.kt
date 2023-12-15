package com.cvkcan.exchangeapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RedirectFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_redirect, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonUsd = view.findViewById<Button>(R.id.usdButton)
        val buttonEuro = view.findViewById<Button>(R.id.euroButton)
        val buttonGold = view.findViewById<Button>(R.id.goldButton)
        val buttonProfile = view.findViewById<Button>(R.id.profileButton)

        buttonUsd.setOnClickListener {
            findNavController().navigate(R.id.action_redirectFragment_to_usdFragment)
        }

        buttonEuro.setOnClickListener {
            findNavController().navigate(R.id.action_redirectFragment_to_euroFragment)
        }

        buttonGold.setOnClickListener {
            findNavController().navigate(R.id.action_redirectFragment_to_goldFragment)
        }

        buttonProfile.setOnClickListener {
            findNavController().navigate(R.id.action_redirectFragment_to_profileFragment)
        }
    }
}