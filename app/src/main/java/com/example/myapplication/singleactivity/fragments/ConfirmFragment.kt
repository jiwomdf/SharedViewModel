package com.example.myapplication.singleactivity.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myapplication.singleactivity.SingleViewModel
import com.example.myapplication.successactivity.SuccessActivity
import com.example.myapplication.databinding.FragmentConfirmBinding

class ConfirmFragment : Fragment() {

    companion object {
        fun newInstance(): ConfirmFragment {
            return ConfirmFragment()
        }
    }

    lateinit var binding: FragmentConfirmBinding
    private val sharedViewModel by activityViewModels<SingleViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentConfirmBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvUserName.text = sharedViewModel.user?.name ?: ""
        binding.tvUserAge.text = (sharedViewModel.user?.age ?: 0).toString()
        binding.tvItemName.text = sharedViewModel.item?.name ?: ""
        binding.tvCategory.text = sharedViewModel.item?.category ?: ""
        binding.tvQuantity.text = "Quantity ${sharedViewModel.item?.qty}"
        binding.btnNext.setOnClickListener {
            startActivity(Intent(requireContext(), SuccessActivity::class.java))
            requireActivity().finish()
        }
    }

}