package com.codinginflow.imagesearchapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codinginflow.imagesearchapp.R
import com.codinginflow.imagesearchapp.adapter.UnsplashPhotoAdapter
import com.codinginflow.imagesearchapp.databinding.FragmentGalleryBinding
import com.codinginflow.imagesearchapp.models.UnsplashPhoto
import com.codinginflow.imagesearchapp.viewmodels.GalleryViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GalleryFragment : Fragment() {

    lateinit var binding: FragmentGalleryBinding
    private val viewModel by viewModels<GalleryViewModel>()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
                LayoutInflater.from(requireContext()),
                R.layout.fragment_gallery,
                container,
                false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = UnsplashPhotoAdapter()
        binding.apply {
            recyclerview.setHasFixedSize(true)
            recyclerview.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            recyclerview.adapter = adapter
        }

        viewModel.photos.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }
}