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
        val user = userList[position]
        holder.checkBox_delete_btn.isChecked = user.isCheck

        holder.checkBox_delete_btn.setOnClickListener {
            userList[position].isCheck = !userList[position].isCheck
            notifyItemChanged(position)
        }

        holder.name_tv.text =  "$position ${userList.size} ${user.name}"
        holder.phone_tv.text = user.phoneNumber.toString()
        holder.mail_tv.text = user.email
//        holder.checkBox_delete_btn.setOnCheckedChangeListener{ _,isChecked ->
//            userList[position].isCheck = isChecked
//            notifyItemChanged(position)
//            //      Toast.makeText(context, "${user.isCheck}", Toast.LENGTH_SHORT).show()
//        }
    }

    override fun getItemCount() = userList.size


    class ViewHolder(itemView : View ):RecyclerView.ViewHolder(itemView) {
//        fun bind(
//            position: Int,
//            userList: List<User>,
//            context: Context,
//            myUserAdapter: MyUserAdapter
//        ) {
//
//        }

      val name_tv = itemView.findViewById<TextView>(R.id.tv_name)
      val phone_tv = itemView.findViewById<TextView>(R.id.tv_phone)
      val mail_tv = itemView.findViewById<TextView>(R.id.tv_email)
     val btn_delete = itemView.findViewById<ImageButton>(R.id.delete_Btn)
    val checkBox_delete_btn = itemView.findViewById<CheckBox>(R.id.cb_delete)

    }
}