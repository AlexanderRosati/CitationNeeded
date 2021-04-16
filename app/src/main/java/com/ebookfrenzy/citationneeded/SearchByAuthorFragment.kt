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
import com.ebookfrenzy.citationneeded.databinding.FragmentSearchByAuthorBinding

class SearchByAuthorFragment(application: Application) : Fragment() {
    private var binding : FragmentSearchByAuthorBinding? = null
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
        binding = FragmentSearchByAuthorBinding.bind(view)
        binding?.recyclerViewSearchByAuthor?.layoutManager = LinearLayoutManager(context)
        binding?.recyclerViewSearchByAuthor?.adapter= adapter

        val searchByAuthorResultObserver = Observer<List<Citation>> {
            searchResults -> adapter.updateCitations(searchResults)
        }

        viewModel.getSearchByAuthorResult().observe(viewLifecycleOwner, searchByAuthorResultObserver)

        binding?.searchAuthorBtn?.setOnClickListener {
            val firstName = binding?.editTextFirstName?.text.toString()
            val lastName = binding?.editTextLastName?.text.toString()

            if (firstName!=null && lastName!=null){
                viewModel.searchByAuthor( firstName, lastName )
                binding?.editTextFirstName?.setText("");
                binding?.editTextLastName?.setText("");

            }

        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_by_author, container, false)
    }
    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}