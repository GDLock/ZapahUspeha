package com.otkritie.hackaton.screens.chatmenu

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.otkritie.hackaton.R
import com.otkritie.hackaton.domain.model.Question
import de.hdodenhof.circleimageview.CircleImageView

class QuestionAdapter() :
    RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {

    var listQ = emptyList<Question>()

    class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.questions_item_layout, parent, false)
        return QuestionViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val title = holder.itemView.findViewById<TextView>(R.id.tv_title_quest)
        val avatar = holder.itemView.findViewById<CircleImageView>(R.id.iv_avatar_quest)
        title.text = listQ[position].title
        avatar.setImageResource(listQ[position].img)
//        holder.itemView.setOnClickListener { onClick }

    }

    override fun getItemCount(): Int {
        return listQ.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<Question>) {
        listQ = list.reversed()
        notifyDataSetChanged()
    }

}