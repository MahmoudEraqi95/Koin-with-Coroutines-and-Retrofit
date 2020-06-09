package com.eraqi.siatask.ui.adapter

import android.content.Context
import android.provider.SyncStateContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eraqi.siatask.R
import com.eraqi.siatask.data.Constants
import com.eraqi.siatask.data.model.Item
import java.util.zip.Inflater

class ChatbotAdapter (var context:Context):RecyclerView.Adapter<ChatbotAdapter.ChatbotViewHolder>(){
    var onAnswerClicked:((String, Int)->Unit)? = null


    init {
        println("adapter started")
    }

    var question = ArrayList<String>()

    fun addQuestion(i:Int){
        question.add(Constants.questions[i])

        notifyItemInserted(i)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChatbotAdapter.ChatbotViewHolder {
       return ChatbotViewHolder(LayoutInflater.from(context).inflate(R.layout.chat_item, parent, false))
    }

    override fun getItemCount(): Int {
        return question.size
    }

    override fun onBindViewHolder(holder: ChatbotAdapter.ChatbotViewHolder, position: Int) {
       holder.question.setText(question[position])
       holder.answers.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
       var answersAdapter = AnswersAdapter(context, Constants.answers[position], position+1)
       holder.answers.adapter = answersAdapter
       answersAdapter.onItemClick = {
          answer, nextPosition-> onAnswerClicked!!.invoke(answer, nextPosition)
       }

    }

    class ChatbotViewHolder (var v: View):RecyclerView.ViewHolder(v){

        var question = v.findViewById<TextView>(R.id.tv_question)
        var answers = v.findViewById<RecyclerView>(R.id.rv_answers)

    }
}