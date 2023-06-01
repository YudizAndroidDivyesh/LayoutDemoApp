package com.example.demooflayouts.roomDBTask

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demooflayouts.R

class MyTaskAdapter(private val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<MyTaskAdapter.ViewHolder>() {
    private var taskList = ArrayList<TaskDetails>()

    class ViewHolder(
        itemView: View, onItemClickListener: OnItemClickListener, taskList: ArrayList<TaskDetails>
    ) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(position: Int, holder: ViewHolder, taskList: ArrayList<TaskDetails>) {
            val taskData = taskList[position]
            holder.etTitle.text = taskData.taskTitle
            holder.etDesc.text = taskData.taskDesc
        }

        init {
            itemView.setOnClickListener {
                onItemClickListener.onClick(adapterPosition, taskList)
            }
        }

        private val etTitle = itemView.findViewById<TextView>(R.id.tv_task_title)
        private val etDesc = itemView.findViewById<TextView>(R.id.tv_task_desc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.rv_user_task_data, parent, false)
        return ViewHolder(view, onItemClickListener, taskList)
    }

    override fun getItemCount() = taskList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position, holder, taskList)
    }

    fun setData(task: ArrayList<TaskDetails>) {
        this.taskList = task
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onClick(position: Int, taskList: ArrayList<TaskDetails>)
    }
}