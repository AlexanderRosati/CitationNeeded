package com.ebookfrenzy.citationneeded

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CitationRepository(application : Application) {
    private var citationDao : CitationDao? = null

    init {
        val db : CitationRoomDatabase? = CitationRoomDatabase.getDatabase(application)
        citationDao = db?.citationDao()
    }

    fun insertCitation(newCitation : Citation) {
        CoroutineScope(IO).launch {
            insertCitationAsync(newCitation)
        }
    }

    private suspend fun insertCitationAsync(newCitation : Citation) {
        citationDao?.insertCitation(newCitation)
    }

    fun insertTag(tag : Tag) {
        CoroutineScope(IO).launch {
            insertTagAsync(tag)
        }
    }

    private suspend fun insertTagAsync(tag : Tag) {
        citationDao?.insertTag(tag)
    }
}