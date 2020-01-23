package com.byjus.headlines.assignment.samsruti.ui.headlines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.byjus.headlines.assignment.samsruti.R
import com.byjus.headlines.assignment.samsruti.databinding.HeadlinesFragmentBinding
import com.byjus.headlines.assignment.samsruti.domain.ApiStatus
import com.byjus.headlines.assignment.samsruti.domain.Headline
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

        val viewModelAdapter= HeadlinesListAdapter(HeadlinesListAdapter.CallBackClickListener{
            headlinesViewModel.displayNewsDetails(it)
        })

        headlinesViewModel.headlinesLiveData.observe(viewLifecycleOwner, Observer<List<Headline>> { headlines ->
            headlines?.apply {
                viewModelAdapter?.submitList(headlines)
            }
        })

        headlinesViewModel.status.observe(this, Observer {
            when(it){
                ApiStatus.ERROR ->{
                    Toast.makeText(viewBinding.root.context,"Network Error!",Toast.LENGTH_SHORT).show()
                }
            }
        })



        viewBinding.headlinesRecyclerView.adapter = viewModelAdapter

        viewBinding.root.findViewById<RecyclerView>(R.id.headlines_recycler_view).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelAdapter
        }

        headlinesViewModel.navigateToSelectedNetworkNews.observe(this, Observer {
            if (it!=null){
                this.findNavController().navigate(HeadlinesFragmentDirections.actionHeadlinesFragmentToDetailsFragment(it))
                headlinesViewModel.displayNewsDetailsComplete()
            }
        })
    }

}
