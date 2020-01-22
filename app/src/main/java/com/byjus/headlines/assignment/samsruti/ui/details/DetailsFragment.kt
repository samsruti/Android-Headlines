package com.byjus.headlines.assignment.samsruti.ui.details

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs

import com.byjus.headlines.assignment.samsruti.R
import com.byjus.headlines.assignment.samsruti.databinding.DetailsFragmentBinding

class DetailsFragment : Fragment() {

    private val detailsFragmentArgs: DetailsFragmentArgs by navArgs()

    private lateinit var viewModel: DetailsViewModel
    private lateinit var binding: DetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DetailsFragmentBinding.inflate(inflater)
        binding.setLifecycleOwner(this)

        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val selectedHeadline = detailsFragmentArgs.headlineDetails
        val viewModelFactory = DetailsViewModel.Factory(selectedHeadline)
        viewModel = ViewModelProviders.of(
            this, viewModelFactory).get(DetailsViewModel::class.java)
        binding.viewModel = viewModel



    }

}
