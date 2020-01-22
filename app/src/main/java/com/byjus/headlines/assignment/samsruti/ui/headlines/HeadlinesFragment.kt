package com.byjus.headlines.assignment.samsruti.ui.headlines

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.navigation.fragment.findNavController

import com.byjus.headlines.assignment.samsruti.R
import com.byjus.headlines.assignment.samsruti.databinding.HeadlinesFragmentBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class HeadlinesFragment : Fragment() {

    companion object {
        fun newInstance() = HeadlinesFragment()
    }

    private lateinit var headlinesViewModel: HeadlinesViewModel
    private lateinit var viewBinding: HeadlinesFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = HeadlinesFragmentBinding.inflate(inflater)

        viewBinding.setLifecycleOwner(this)


        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        headlinesViewModel = ViewModelProviders.of(this).get(HeadlinesViewModel::class.java)
        // TODO: Use the ViewModel
        viewBinding.viewModel = headlinesViewModel

        viewBinding.headlinesRecyclerview.adapter = HeadlinesListAdapter(HeadlinesListAdapter.CallBackClickListener{
            headlinesViewModel.displayNewsDetails(it)
        })
    }

}
