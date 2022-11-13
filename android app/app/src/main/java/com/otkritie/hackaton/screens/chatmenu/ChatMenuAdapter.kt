package com.otkritie.hackaton.screens.chatmenu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.otkritie.hackaton.R

class ChatMenuAdapter(private val onClick: () -> Unit) :
    RecyclerView.Adapter<ChatMenuAdapter.ChatMenuViewHolder>() {
    class ChatMenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatMenuViewHolder {
        TODO()
    }

    override fun onBindViewHolder(holder: ChatMenuViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO()
    }
}