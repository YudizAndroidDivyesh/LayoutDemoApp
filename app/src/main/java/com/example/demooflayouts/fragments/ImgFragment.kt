package com.example.demooflayouts.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.demooflayouts.FragmentCommunicater
import com.example.demooflayouts.R


class ImgFragment : Fragment(),FragmentCommunicater{



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val transaction = inflater.inflate(R.layout.fragment_img, container, false)
        var data = activity as FragmentCommunicater
        val nextPageBtn  = transaction.findViewById<Button>(R.id.nextFragmentBtn)
        val frgTitle = transaction.findViewById<TextView>(R.id.fragmentTitle)
        nextPageBtn.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.frameLayout,addTextFragment()).commit()
        }
        frgTitle.text = arguments?.getString("message")

        val popMsg = transaction.findViewById<Button>(R.id.toastFragment)
        popMsg.setOnClickListener {
                data.passData("Message")
        }
        return transaction
    }

    override fun passData(msg: String) {

    }


}