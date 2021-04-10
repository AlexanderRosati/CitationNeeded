package com.ebookfrenzy.citationneeded

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import com.ebookfrenzy.citationneeded.Citation
import com.ebookfrenzy.citationneeded.CitationDao

@Database(entities = [(Citation::class), (Tag::class)], version = 1)
abstract class CitationRoomDatabase : RoomDatabase() {
    abstract fun citationDao() : CitationDao

    companion object {
        private var INSTANCE : CitationRoomDatabase? = null

        internal fun getDatabase (context : Context) : CitationRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(CitationRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE =
                            Room.databaseBuilder<CitationRoomDatabase>(
                                context.applicationContext,
                                    CitationRoomDatabase::class.java,
                                        "citation_database").build()
                    }
                }
            }

            return INSTANCE
        }
    }
}