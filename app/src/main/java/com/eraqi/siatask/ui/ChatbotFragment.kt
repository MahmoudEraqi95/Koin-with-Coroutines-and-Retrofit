package com.eraqi.siatask.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eraqi.siatask.R
import com.eraqi.siatask.Result
import com.eraqi.siatask.data.Constants
import com.eraqi.siatask.data.callback.ChatAnswerClickListner
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
    val PAGE_SIZE = "10"
    var params = ArrayList<String>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_chatbot, container, false)
        chatbotRecyclerView = v.findViewById<RecyclerView>(R.id.rv_chatbot)
        questionRecyclerView  = v.findViewById(R.id.rv_stackoverflow_questions)
        questionRecyclerView.layoutManager = LinearLayoutManager(context)
        chatbotRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = ChatbotAdapter(requireContext())
        adapter.addQuestion(0)
        chatbotRecyclerView.adapter = adapter
        adapter.onAnswerClicked = {answer, nextPostion ->
            params.add(answer)
            if(nextPostion<Constants.questions.size) {
                adapter.addQuestion(nextPostion)
                adapter.notifyDataSetChanged()
            }else{
                val viewModelFactory = ChatbotViewModelFactory(params[0], params[1], params[2], PAGE_SIZE)
                var chatbotViewMode:ChatbotViewModel = ViewModelProviders.of(this,viewModelFactory).get(ChatbotViewModel::class.java)
                chatbotViewMode.resultMutableLiveData.observe(viewLifecycleOwner, Observer{
                    println("Observer")
                    when(it){
                        is Result.Success-> {
                            resultAdapter = StackOverflowResultsAdapter(
                                requireContext(),
                                (it.data as StackExchangeResponse).items
                            )
                            questionRecyclerView.adapter = resultAdapter
                        }
                        is Result.Error -> Toast.makeText(context, it.exception.toString(), Toast.LENGTH_LONG).show()
                    }
                })
            }

        }

        return v

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }



}