package com.eraqi.siatask.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

import com.eraqi.siatask.R
/**
 * this class represents the first screen that appears after the splash, it just contain a button that navigates you to the chatbot screen
 */
class MainFragment :Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.fragment_main, container, false)
        val button = v.findViewById<Button>(R.id.btn_navigate_to_chatbot)
        button?.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_chatbotFragment, null)
        }



        return v
    }

}