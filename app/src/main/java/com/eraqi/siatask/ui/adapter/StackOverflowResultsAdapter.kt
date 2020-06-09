package com.eraqi.siatask.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eraqi.siatask.R
import com.eraqi.siatask.data.model.Item

class StackOverflowResultsAdapter(val context:Context, val items:List<Item>): RecyclerView.Adapter<StackOverflowResultsAdapter.StackOverflowResultViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StackOverflowResultsAdapter.StackOverflowResultViewHolder {
        return StackOverflowResultViewHolder(LayoutInflater.from(context).inflate(R.layout.question_item, parent, false))
    }

    override fun getItemCount(): Int {
       return items.size
    }

    override fun onBindViewHolder(
        holder: StackOverflowResultsAdapter.StackOverflowResultViewHolder,
        position: Int
    ) {
        var item = items.get(position)
      holder.userName.setText(item.owner.displayName)
        holder.questionTitle.setText(item.title)
        holder.tags.setText(item.tags.asIterable().forEach { item->item+"," }.toString())
    }
    class StackOverflowResultViewHolder(val v: View) :RecyclerView.ViewHolder(v){
        var userName = v.findViewById<TextView>(R.id.tv_username)
        var questionTitle = v.findViewById<TextView>(R.id.tv_question)
        var tags = v.findViewById<TextView>(R.id.tv_tags)



    }
}