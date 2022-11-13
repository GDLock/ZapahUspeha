package com.otkritie.hackaton.screens.chat

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.otkritie.hackaton.R
import com.otkritie.hackaton.domain.model.MessageViewRenderer
import java.util.Calendar

class ChatAdapter : ListAdapter<MessageViewRenderer, ChatAdapter.ChatViewHolder>(DiffUtilCallback()) {

    private var msgList = emptyList<MessageViewRenderer>()

    class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.message_item, parent, false)
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {

        //Получаем актуальное время
        val date = Calendar.getInstance().time

        //tv времени отправки сообщений
        val tvTimeUser = holder.itemView.findViewById<TextView>(R.id.tv_date_user)
        val tvTimeOpponent = holder.itemView.findViewById<TextView>(R.id.tv_date_opponent)

        //tv Текстов сообщений
        val msgUser = holder.itemView.findViewById<TextView>(R.id.tv_user_message)
        val msgOpponent = holder.itemView.findViewById<TextView>(R.id.tv_opponent_message)
        val llOpponent = holder.itemView.findViewById<LinearLayout>(R.id.rl_message_opponent)
        val llUser = holder.itemView.findViewById<LinearLayout>(R.id.rl_message_user)

        if (msgList[position].isMine) {
            msgUser.text = msgList[position].text
            llOpponent.visibility = View.GONE
            llUser.visibility = View.VISIBLE
        } else {
            msgOpponent.text = msgList[position].text
            llUser.visibility = View.GONE
            llOpponent.visibility = View.VISIBLE
        }

        tvTimeUser.text = msgList[position].time
        tvTimeOpponent.text = msgList[position].time

    }

    override fun getItemCount(): Int {
        return msgList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<MessageViewRenderer>) {
        msgList = list
        notifyDataSetChanged()
    }
}

class DiffUtilCallback: DiffUtil.ItemCallback<MessageViewRenderer>() {

    override fun areItemsTheSame(oldItem: MessageViewRenderer, newItem: MessageViewRenderer): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MessageViewRenderer, newItem: MessageViewRenderer): Boolean {
        return oldItem == newItem
    }
}
