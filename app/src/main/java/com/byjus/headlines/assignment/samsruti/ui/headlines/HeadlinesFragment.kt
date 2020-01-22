package com.byjus.headlines.assignment.samsruti.ui.headlines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.byjus.headlines.assignment.samsruti.databinding.HeadlinesFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HeadlinesFragment : Fragment() {

    private val headlinesViewModel by viewModel<HeadlinesViewModel>()

    private var headlinesListAdapter: HeadlinesListAdapter? = null

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

        viewBinding.viewModel = headlinesViewModel

        viewBinding.headlinesRecyclerview.adapter = HeadlinesListAdapter(HeadlinesListAdapter.CallBackClickListener{
            headlinesViewModel.displayNewsDetails(it)
        })


        headlinesViewModel.navigateToSelectedNews.observe(this, Observer {
            if (it!=null){
                this.findNavController().navigate(HeadlinesFragmentDirections.actionHeadlinesFragmentToDetailsFragment(it))
                headlinesViewModel.displayNewsDetailsComplete()
            }
        })
    }

}
