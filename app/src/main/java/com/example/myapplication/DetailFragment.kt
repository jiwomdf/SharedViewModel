package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myapplication.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    companion object {
        fun newInstance(): DetailFragment {
            return DetailFragment()
        }
    }

    lateinit var binding: FragmentDetailBinding
    private val sharedViewModel by activityViewModels<SingleViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvName.text = sharedViewModel.data?.name ?: ""
        binding.tvCategory.text = sharedViewModel.data?.category ?: ""
        binding.btnNext.setOnClickListener {
            (requireActivity() as SingleActivity).loadFragment(InputFragment.newInstance())
        }
    }
}