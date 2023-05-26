package com.example.demooflayouts.CustomAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demooflayouts.R

class MyUserAdapter(val context: Context ,private var userList: ArrayList<User>) : RecyclerView.Adapter<MyUserAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_userdata,parent,false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind( position,userList,holder )
        holder.btnDelete.setOnClickListener {
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, userList.size)
            userList.removeAt(position)
        }

        holder.checkBoxDeleteBtn.setOnClickListener {
            userList[position].isCheck = !userList[position].isCheck
            notifyItemChanged(position)
        }


//        holder.checkBoxDeleteBtn.setOnCheckedChangeListener{ _,isChecked ->
//            userList[position].isCheck = isChecked
//            notifyItemChanged(position)
//            //      Toast.makeText(context, "${user.isCheck}", Toast.LENGTH_SHORT).show()
//        }
    }

    override fun getItemCount() = userList.size


    class ViewHolder(itemView : View ):RecyclerView.ViewHolder(itemView) {
        fun bind(
            position: Int,
            userList: List<User>,
            holder : ViewHolder
        ) {
            val user = userList[position]
            holder.checkBoxDeleteBtn.isChecked = user.isCheck
            holder.nameTv.text =  "$position ${userList.size} ${user.name}"
            holder.phoneTv.text = user.phoneNumber.toString()
            holder.mailTv.text = user.email
        }

    private val nameTv = itemView.findViewById<TextView>(R.id.tv_name)
    private  val phoneTv = itemView.findViewById<TextView>(R.id.tv_phone)
    private  val mailTv = itemView.findViewById<TextView>(R.id.tv_email)
    val btnDelete = itemView.findViewById<ImageButton>(R.id.delete_Btn)
    val checkBoxDeleteBtn = itemView.findViewById<CheckBox>(R.id.cb_delete)

    }
}