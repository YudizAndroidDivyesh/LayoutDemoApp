package com.example.demooflayouts.liveDataViewModel.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.demooflayouts.R
import com.example.demooflayouts.databinding.FragmentUserInfoBinding
import com.example.demooflayouts.liveDataViewModel.viewModelFile.UserModel

class UserInfoFragment : Fragment() {
  lateinit var binding : FragmentUserInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding  = DataBindingUtil.inflate(inflater,R.layout.fragment_user_info, container, false)

        userData()

        binding.editInfoBtn.setOnClickListener {
         activity?.supportFragmentManager!!.beginTransaction().replace(R.id.userInfoFrm,AddDataFragment()).commit()
        }
        return binding.root
    }

    private fun userData() {
        val vm = ViewModelProvider(requireActivity())[UserModel::class.java]
        vm.detail.observe(viewLifecycleOwner, Observer {
            binding.nameTv.text = it.name
            binding.phoneTv.text = it.phone
            binding.emailTv.text = it.email
            binding.addressTv.text = it.address
            vm.data(it.topic)

            Log.e("Topic",it.topic)
        })
    }
}