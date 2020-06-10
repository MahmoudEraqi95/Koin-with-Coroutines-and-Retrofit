package com.eraqi.siatask.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eraqi.siatask.R
import com.eraqi.siatask.Result
import com.eraqi.siatask.data.Constants

import com.eraqi.siatask.data.model.StackExchangeResponse
import com.eraqi.siatask.presentation.ChatbotViewModel
import com.eraqi.siatask.presentation.factory.ChatbotViewModelFactory
import com.eraqi.siatask.ui.adapter.ChatbotAdapter
import com.eraqi.siatask.ui.adapter.StackOverflowResultsAdapter

class ChatbotFragment: Fragment() {
    lateinit var chatbotRecyclerView:RecyclerView
    lateinit var questionRecyclerView:RecyclerView
    lateinit var adapter :ChatbotAdapter
    lateinit var resultAdapter:StackOverflowResultsAdapter
    lateinit var chatbotViewModel:ChatbotViewModel
    lateinit var loadingProgressBar: ProgressBar
    val PAGE_SIZE = "10"
    var sort = ""
    var order = ""
    var tagg = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_chatbot, container, false)
        loadingProgressBar = v.findViewById(R.id.pb_loading_questions)
        chatbotRecyclerView = v.findViewById<RecyclerView>(R.id.rv_chatbot)
        setUpChatRecyclerView()
        questionRecyclerView  = v.findViewById(R.id.rv_stackoverflow_questions)
        questionRecyclerView.layoutManager = LinearLayoutManager(context)

        adapter.onAnswerClicked = { answer, nextPostion ->

            when(nextPostion){

                 1 -> {
                     tagg = answer
                     adapter.addQuestion(nextPostion)
                     adapter.notifyDataSetChanged()
                 }
                2 ->{
                    order = answer
                    adapter.addQuestion(nextPostion)
                    adapter.notifyDataSetChanged()
                }
                3 -> {
                    loadingProgressBar.visibility = View.VISIBLE
                    sort = answer
                    observeResultsFromViewModel()
                }

            }

        }

        return v

    }


    fun setUpChatRecyclerView(){
        chatbotRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = ChatbotAdapter(requireContext())
        adapter.addQuestion(0)
        chatbotRecyclerView.adapter = adapter
    }


    fun observeResultsFromViewModel(){
        println("Observing")


        val viewModelFactory = ChatbotViewModelFactory(tagg, order, sort, PAGE_SIZE)
        chatbotViewModel = ViewModelProviders.of(this,viewModelFactory).get(ChatbotViewModel::class.java)
        chatbotViewModel.resultMutableLiveData.observe(viewLifecycleOwner, Observer{

            when(it){
                is Result.Success-> {
                    chatbotRecyclerView.visibility = View.GONE
                    loadingProgressBar.visibility = View.GONE
                    Toast.makeText(context,  "Questions' Size: "+(it.data as StackExchangeResponse).items.size, Toast.LENGTH_LONG).show()
                    resultAdapter = StackOverflowResultsAdapter(
                        requireContext(),
                        (it.data as StackExchangeResponse).items
                    )
                    questionRecyclerView.adapter = resultAdapter
                }
                is Result.Error -> {
                    loadingProgressBar.visibility = View.GONE
                    Toast.makeText(context, it.exception.toString(), Toast.LENGTH_LONG).show()
                }
            }
        })
    }





}