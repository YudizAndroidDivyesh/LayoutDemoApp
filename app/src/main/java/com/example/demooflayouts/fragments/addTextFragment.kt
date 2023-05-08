package com.example.demooflayouts.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.demooflayouts.FragmentCommunicater
import com.example.demooflayouts.R


class addTextFragment : Fragment() {

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
        val transaction =  inflater.inflate(R.layout.fragment_add_text, container, false)
        val goBackBtn = transaction.findViewById<Button>(R.id.backFragment)
        val etName = transaction.findViewById<EditText>(R.id.etName)

        goBackBtn.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("message",etName.text.toString())
            val fragment2 = ImgFragment()
            fragment2.arguments = bundle
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.frameLayout,fragment2).commit()


        }


        return transaction
    }


}