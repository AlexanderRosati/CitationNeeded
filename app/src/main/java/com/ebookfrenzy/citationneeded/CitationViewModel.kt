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

    // filter by AUTHOR
    fun searchByAuthor (firstName:String, lastName:String){
        repository.searchByAuthor(firstName, lastName)
    }

    //return MutableLiveData for searching by Author
    fun getSearchByAuthorResult () : MutableLiveData<List<Citation>> {
        return repository.getSearchByAuthorResult()
    }

    //filter by BOOK TITLE
    fun searchByBookTitle(bookTitle : String)
    {
        repository.searchByBookTitle(bookTitle)
    }

    //return MutableLiveData for searching Book Title
    fun getSearchByBookTittleResult() : MutableLiveData<List<Citation>> {
        return repository.getSearchByBookResult()
    }

}