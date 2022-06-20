package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myapplication.databinding.FragmentConfirmBinding
import com.example.myapplication.databinding.FragmentInputBinding

class ConfirmFragment : Fragment() {

    companion object {
        fun newInstance(): ConfirmFragment {
            return ConfirmFragment()
        }
    }

    lateinit var binding: FragmentConfirmBinding
    private val sharedViewModel by activityViewModels<SingleViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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
        binding.tvName.text = sharedViewModel.data?.name ?: ""
        binding.tvCategory.text = sharedViewModel.data?.category ?: ""
        binding.tvQuantity.text = "Quantity ${sharedViewModel.data?.qty}"
        binding.btnNext.setOnClickListener {
            startActivity(Intent(requireContext(), SuccessActivity::class.java))
            requireActivity().finish()
        }
    }

}