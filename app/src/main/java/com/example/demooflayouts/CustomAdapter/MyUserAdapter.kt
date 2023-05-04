package com.example.demooflayouts.CustomAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.demooflayouts.R

class MyUserAdapter(val context: Context ,var userList: ArrayList<User>) : RecyclerView.Adapter<MyUserAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_userdata,parent,false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.btn_delete.setOnClickListener {
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, userList.size)
            userList.removeAt(position)
        }

        holder.bind(position,userList,context)

    }

    override fun getItemCount() = userList.size


    class ViewHolder(itemView : View ):RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int, userList: List<User>,context: Context) {
            val user = userList[position]

            name_tv.text =  "$position ${userList.size} ${user.name}"
            phone_tv.text = user.phoneNumber.toString()
            mail_tv.text = user.email
            checkBox_delete_btn.setOnCheckedChangeListener{ _,isChecked ->
                Toast.makeText(context, "${userList[position]}", Toast.LENGTH_SHORT).show()
            }
        }

     private val name_tv = itemView.findViewById<TextView>(R.id.tv_name)
     private val phone_tv = itemView.findViewById<TextView>(R.id.tv_phone)
     private val mail_tv = itemView.findViewById<TextView>(R.id.tv_email)
     val btn_delete = itemView.findViewById<ImageButton>(R.id.delete_Btn)
    val checkBox_delete_btn = itemView.findViewById<CheckBox>(R.id.cb_delete)

    }
}