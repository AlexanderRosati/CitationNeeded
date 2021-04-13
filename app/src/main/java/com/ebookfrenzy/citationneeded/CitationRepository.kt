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

    fun insertCitation(newCitation : Citation, tags : List<Tag>) {
        CoroutineScope(IO).launch {
            val citationID = insertCitationAsync(newCitation)
            for (tag in tags) {
                if (citationID != null) {
                    tag.citationID = citationID
                }
                insertTagAsync(tag)
            }
        }
    }

    private suspend fun insertCitationAsync(newCitation : Citation) : Long? {
        return citationDao?.insertCitation(newCitation)
    }

    private suspend fun insertTagAsync(tag : Tag) {
        citationDao?.insertTag(tag)
    }
}