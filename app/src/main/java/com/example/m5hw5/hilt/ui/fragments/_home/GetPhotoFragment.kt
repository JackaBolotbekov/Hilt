package com.example.m5hw5.hilt.ui.fragments._home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.m5hw5.R
import com.example.m5hw5.databinding.FragmentGetPhotoBinding
import com.example.m5hw5.hilt.ui.adapter.PhotoAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GetPhotoFragment : Fragment() {

    private var binding: FragmentGetPhotoBinding? = null
    private val viewModel: GetViewModel by viewModels()
    private val photoAdapter = PhotoAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGetPhotoBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setUpRequests()
        setUpObserves()
        setOnClickListeners()
    }

    private fun initialize() {
        binding?.recyclerView?.adapter = photoAdapter
    }

    private fun setUpRequests() {
        viewModel.getPhoto()
    }

    private fun setUpObserves() {
        viewModel.photoLiveData.observe(viewLifecycleOwner) {
            photoAdapter.submitList(it)
        }
        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setOnClickListeners() {
        binding?.btnAdd?.setOnClickListener {
            findNavController().navigate(R.id.action_getPhotoFragment_to_photoFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}