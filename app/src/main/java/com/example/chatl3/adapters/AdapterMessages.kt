package com.example.chatl3.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chatl3.R
import com.example.chatl3.adapters.AdapterMessages.*
import com.example.chatl3.entity.Msg

class AdapterMessages(private val context: Context, private val listMsg: List<Msg>):
    RecyclerView.Adapter<Holder>() {

    class Holder(private val view: View):RecyclerView.ViewHolder(view) {
            val user: TextView = view.findViewById(R.id.item_txt_user)
            val msg: TextView = view.findViewById(R.id.item_txt_message)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = ViewGroup.inflate(context, R.layout.item_message,null)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val msg = listMsg[position]
        holder.user.text = msg.user
        holder.msg.text = msg.msg

    }

    override fun getItemCount(): Int {
        return listMsg.size
    }
}