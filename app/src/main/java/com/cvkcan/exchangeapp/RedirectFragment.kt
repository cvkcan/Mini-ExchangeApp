package com.cvkcan.exchangeapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


class RedirectFragment : Fragment() {
//    val db by lazy { DBHelper(this.requireContext())  }
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