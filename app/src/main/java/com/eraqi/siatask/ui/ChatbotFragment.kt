package com.eraqi.siatask.ui

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eraqi.siatask.R

import com.eraqi.siatask.presentation.ResultsViewModel
import com.eraqi.siatask.ui.adapter.ChatbotAdapter
import com.eraqi.siatask.ui.adapter.StackOverflowResultsAdapter
/**
 *This class is the interface for the chat between the bot and the user
 *
 */
class ChatbotFragment: Fragment() {
    lateinit var chatbotRecyclerView:RecyclerView

    lateinit var chatbotAdapter :ChatbotAdapter
     var  recyclerViewState: Parcelable? = null


    var sort = ""
    var order = ""
    var tagg = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_chatbot, container, false)
        chatbotRecyclerView = v.findViewById<RecyclerView>(R.id.rv_chatbot)
        chatbotAdapter = ChatbotAdapter(requireContext())
        if (savedInstanceState != null){
            //fill recycler view
            chatbotRecyclerView.layoutManager = LinearLayoutManager(context)
            chatbotAdapter.question = savedInstanceState.getStringArrayList("items")!!
            chatbotRecyclerView.adapter = chatbotAdapter
            tagg = savedInstanceState.getString("tagg","")
            sort = savedInstanceState.getString("sort","")
            order = savedInstanceState.getString("order","")

        }
        else{
            setUpChatRecyclerView()
        }

        chatbotAdapter.onAnswerClicked = { answer, nextPostion ->

            when(nextPostion){

                 1 -> {
                     tagg = answer
                     chatbotAdapter.addQuestion(nextPostion)
                     chatbotAdapter.notifyDataSetChanged()
                 }
                2 ->{
                    order = answer
                    chatbotAdapter.addQuestion(nextPostion)
                    chatbotAdapter.notifyDataSetChanged()
                }
                3 -> {

                    sort = answer
                    val args = Bundle()
                    args.putString("tagg", tagg)
                    args.putString("order", order)
                    args.putString("sort", sort)
                    findNavController().navigate(R.id.action_chatbotFragment_to_resultsFragment, args)

                }

            }



        }

        return v

    }

    /**
     *this function is reposnbile for initializing the recyclerview, but calling the adapter and adding the first question
     * @return Nothing.
     */
    fun setUpChatRecyclerView(){
        chatbotRecyclerView.layoutManager = LinearLayoutManager(context)
        chatbotAdapter.addQuestion(0)
        chatbotRecyclerView.adapter = chatbotAdapter
    }


    /**
     *this function is reposnbile for setting the current state of the fragment , gets called when the fragment uses onStop()
     * which gets called when the screen of the phone rotates or the app goes to background
     *@param outState : sets  the current state of the fragment.
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("tagg", tagg)
        outState.putString("sort", sort)
        outState.putString("order", order)
        outState.putStringArrayList("items", chatbotAdapter.question)
    }








}