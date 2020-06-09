package com.eraqi.siatask.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eraqi.siatask.R
import com.eraqi.siatask.data.callback.ChatAnswerClickListner

 class AnswersAdapter(var c:Context, var answers:List<String>, val nextQuestion:Int):RecyclerView.Adapter<AnswersAdapter.AnswersViewHolder>() {

     var onItemClick: ((String, Int) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AnswersAdapter.AnswersViewHolder {
       return AnswersViewHolder(LayoutInflater.from(c).inflate(R.layout.answers_item, parent, false))
    }

    override fun getItemCount(): Int {
        return answers.size
    }

    override fun onBindViewHolder(holder: AnswersAdapter.AnswersViewHolder, position: Int) {
      holder.answerText.setText(answers[position])
      holder.answerText.setOnClickListener({

       onItemClick!!.invoke(holder.answerText.text.toString(), nextQuestion)

      })
    }

    class AnswersViewHolder(var v:View) :RecyclerView.ViewHolder(v){

        var answerText = v.findViewById<TextView>(R.id.tv_suggested_answer)


       /* fun <T : RecyclerView.ViewHolder> T.listen(event: (answer: String, nextPosition: Int) -> Unit): T {
            answerText.setOnClickListener {
                event.invoke(answerText.text.toString(), )
            }
            return this
        }*/

    }

}