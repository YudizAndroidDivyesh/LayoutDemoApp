package com.example.demooflayouts.liveDataViewModel.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.demooflayouts.R
import com.example.demooflayouts.databinding.FragmentAddDataBinding
import com.example.demooflayouts.liveDataViewModel.data.UserInfo
import com.example.demooflayouts.liveDataViewModel.viewModelFile.UserModel


class AddDataFragment : Fragment() {

    private val vm by lazy {   ViewModelProvider(requireActivity())[UserModel::class.java]}

    lateinit var binding : FragmentAddDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_add_data, container, false)
        if(!binding.nameEt.text.isNotEmpty()){
        vm.detail.observe(viewLifecycleOwner, Observer {
            binding.nameEt.setText(it.name)
            binding.phoneEt.setText(it.phone)
            binding.mailEt.setText(it.email)
            binding.addressEt.setText(it.address)
            binding.topicEt.setText(it.topic)
        })}

        // Inflate the layout for this fragment
        binding.saveBtn.setOnClickListener {
            saveUserInfo()

            activity!!.supportFragmentManager.beginTransaction().replace(R.id.userInfoFrm,UserInfoFragment()).commit()
        }
        return binding.root
    }

    private fun saveUserInfo() {

        vm.saveDetails(UserInfo(binding.nameEt.text.toString(),
            binding.phoneEt.text.toString(),binding.mailEt.text.toString(),binding.addressEt.text.toString(),binding.topicEt.text.toString()))
    }
}