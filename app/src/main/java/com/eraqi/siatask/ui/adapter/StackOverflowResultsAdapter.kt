package com.eraqi.siatask.ui.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eraqi.siatask.R
import com.eraqi.siatask.data.model.Item
/**
 * this class is responsible for making the data fit the a specfic view or layout
 */
class StackOverflowResultsAdapter(val context:Context, val items:List<Item>): RecyclerView.Adapter<StackOverflowResultsAdapter.StackOverflowResultViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StackOverflowResultsAdapter.StackOverflowResultViewHolder {
        return StackOverflowResultViewHolder(LayoutInflater.from(context).inflate(R.layout.question_item, parent, false))
    }
    /**
     *this function retrieves the number of items to let the adapter know how many rows exactly it would create
     * @return NumberOfRows.
     */
    override fun getItemCount(): Int {
       return items.size
    }

    /**
     * this function is responsible for setting for binding the data to the view
     */
    override fun onBindViewHolder(
        holder: StackOverflowResultsAdapter.StackOverflowResultViewHolder,
        position: Int
    ) {
        var item = items.get(position)
        holder.userName.setText(item.owner.displayName)
        holder.questionTitle.setText(item.title)
        holder.tags.setText(item.tags.toString())
        holder.v.setOnClickListener({
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(item.link)))
        })
    }
    /**
     * this class creates the view which holds the data for every item in the adapter
     */
    class StackOverflowResultViewHolder(val v: View) :RecyclerView.ViewHolder(v){
        var userName = v.findViewById<TextView>(R.id.tv_username)
        var questionTitle = v.findViewById<TextView>(R.id.tv_question)
        var tags = v.findViewById<TextView>(R.id.tv_tags)




    }
}