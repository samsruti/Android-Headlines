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
import kotlinx.android.synthetic.main.activity_main.*

class HeadlinesFragment : Fragment() {

    companion object {
        fun newInstance() = HeadlinesFragment()
    }

    private lateinit var viewModel: HeadlinesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.headlines_fragment, container, false)
        root.findViewById<LinearLayout>(R.id.baseitem).setOnClickListener {
            this.findNavController().navigate(HeadlinesFragmentDirections.actionHeadlinesFragmentToDetailsFragment())
        }
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HeadlinesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
