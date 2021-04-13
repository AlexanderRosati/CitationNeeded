package com.ebookfrenzy.citationneeded

import android.app.Application
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ebookfrenzy.citationneeded.databinding.FragmentSearchByTagBinding

class SearchByTagFragment(application: Application) : Fragment() {
    private var binding : FragmentSearchByTagBinding? = null //binding
    private var viewModel : CitationViewModel = CitationViewModel(application) //view model
    private var adapter : SearchRecyclerAdapter = SearchRecyclerAdapter(mutableListOf(), viewModel) //adapter for recycler view

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri : Uri)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchByTagBinding.bind(view) //get binding
        binding?.recyclerViewSearchByTag?.layoutManager = LinearLayoutManager(context) //set layout manager
        binding?.recyclerViewSearchByTag?.adapter = adapter //set adapter for recycler view

        //update data for adapter when results return
        val searchByTagResultObserver = Observer<List<Citation>> {
            searchResults -> adapter.updateCitations(searchResults)
        }
        viewModel.getSearchByTagResult().observe(viewLifecycleOwner, searchByTagResultObserver)

        //search button is clicked
        binding?.searchBtn?.setOnClickListener {
            val searchItem = binding?.editTextSearchByTag?.text.toString()
            if (searchItem != null) {
                //filter CITATION table by tag and clear edit text
                viewModel.searchByTag(searchItem)
                binding?.editTextSearchByTag?.setText("")
            }
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_by_tag, container, false)
    }
}