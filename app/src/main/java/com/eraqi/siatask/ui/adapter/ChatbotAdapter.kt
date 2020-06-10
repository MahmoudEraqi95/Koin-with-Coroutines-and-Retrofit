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




    var question = ArrayList<String>()

    fun addQuestion(i:Int){
            if (question.size<Constants.questions.size && i != question.size-1) {
                question.add(Constants.questions[i])

                notifyItemInserted(i)
            }

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
            if (question.size<=Constants.questions.size) {
                holder.question.setText(question[position])
                holder.answers.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)


                var answersAdapter =
                    AnswersAdapter(context, Constants.answers[position], position + 1)

                holder.answers.adapter = answersAdapter
                answersAdapter.onItemClick = {

                        answer, nextPosition ->
                    kotlin.run {

                            onAnswerClicked!!.invoke(answer, nextPosition)
                    }


                }
            }

        println("questions size:"+ question.size)



     }

    class ChatbotViewHolder (var v: View):RecyclerView.ViewHolder(v){

        var question = v.findViewById<TextView>(R.id.tv_question)
        var answers = v.findViewById<RecyclerView>(R.id.rv_answers)

    }
}