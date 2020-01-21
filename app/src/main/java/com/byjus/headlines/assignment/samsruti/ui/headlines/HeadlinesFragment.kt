package com.byjus.headlines.assignment.samsruti.ui.headlines

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.byjus.headlines.assignment.samsruti.R

class HeadlinesFragment : Fragment() {

    companion object {
        fun newInstance() = HeadlinesFragment()
    }

    private lateinit var viewModel: HeadlinesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.headlines_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HeadlinesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
