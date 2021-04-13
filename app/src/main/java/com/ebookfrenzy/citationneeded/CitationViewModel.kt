package com.ebookfrenzy.citationneeded

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class CitationViewModel(application : Application) : AndroidViewModel(application) {
    private val repository : CitationRepository = CitationRepository(application)

    //insert one record into CITATION table and multiple records into TAG table
    fun insertCitation(citation : Citation, tags : List<Tag>) {
        repository.insertCitation(citation, tags)
    }

    //delete one record from CITATION table and up to three records from TAG table
    fun deleteCitation(citationID : Long) {
        repository.deleteCitation(citationID)
    }

     //filter CITATION table by TAG
    fun searchByTag(tag : String) {
        repository.searchByTag(tag)
    }

    //return MutableLiveData for searching by tag
    fun getSearchByTagResult () : MutableLiveData<List<Citation>> {
        return repository.getSearchByTagResult()
    }
}