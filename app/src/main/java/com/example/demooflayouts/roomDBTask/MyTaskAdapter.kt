package com.example.demooflayouts.roomDBTask

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demooflayouts.R

class MyTaskAdapter(private val taskList : List<TaskDetails>) : RecyclerView.Adapter<MyTaskAdapter.ViewHolder>() {
    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(position: Int, holder: ViewHolder, taskList: List<TaskDetails>) {
            val taskData = taskList[position]
            holder.etTitle.text = "$position + ${taskData.taskTitle}"
            holder.etDesc.text = taskData.taskDesc
        }

        private val etTitle = itemView.findViewById<TextView>(R.id.tv_task_title)
        private val etDesc = itemView.findViewById<TextView>(R.id.tv_task_desc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
       ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_user_task_data,parent,false))


    override fun getItemCount() = taskList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position,holder,taskList)
    }
}