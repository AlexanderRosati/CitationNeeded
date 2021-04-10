package com.ebookfrenzy.citationneeded

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class CitationViewModel(application : Application) : AndroidViewModel(application) {
    private val repository : CitationRepository = CitationRepository(application)

    fun insertCitation(citation : Citation) {
        repository.insertCitation(citation)
    }

    fun insertTag(tag : Tag) {
        repository.insertTag(tag)
    }
}