package com.ebookfrenzy.citationneeded

import android.app.Application
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.lifecycle.Observer
import com.ebookfrenzy.citationneeded.databinding.FragmentSearchByBookBinding

class SearchByBookFragment(application: Application) : Fragment() {
    private var binding : FragmentSearchByBookBinding? = null
    private var viewModel : CitationViewModel = CitationViewModel(application)
    private var adapter : SearchRecyclerAdapter = SearchRecyclerAdapter(mutableListOf(), viewModel)

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri : Uri)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchByBookBinding.bind(view)
        binding?.recyclerViewSearchByBookTitle?.layoutManager = LinearLayoutManager(context)
        binding?.recyclerViewSearchByBookTitle?.adapter= adapter

        val searchByBookTitleObserver = Observer<List<Citation>> {
            searchResults -> adapter.updateCitations(searchResults)
        }

        viewModel.getSearchByBookTittleResult().observe(viewLifecycleOwner, searchByBookTitleObserver)

        binding?.searchBookBtn?.setOnClickListener {
            val bookTitle = binding?.editTextSearchByBookTitle?.text.toString()


            if (bookTitle!=null){
                viewModel.searchByBookTitle( bookTitle )
                binding?.editTextSearchByBookTitle?.setText("");
            }

        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_by_book, container, false)
    }


    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}