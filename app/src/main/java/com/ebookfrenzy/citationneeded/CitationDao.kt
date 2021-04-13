package com.ebookfrenzy.citationneeded

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CitationDao {
    @Insert
    fun insertCitation(citation : Citation) : Long

    @Query("DELETE FROM CITATION WHERE CITATION_ID = :citationID")
    fun deleteCitation(citationID : Long)

    @Query("DELETE FROM TAG WHERE CITATION_ID = :citationID")
    fun deleteTags(citationID : Long)

    @Insert
    fun insertTag(tag : Tag)

    @Query("SELECT * FROM CITATION WHERE AUTHOR_FNAME = LOWER(:fname) AND AUTHOR_LNAME = LOWER(:lname)")
    fun filterByAuthor(fname : String, lname : String) : List<Citation>

    @Query("SELECT * FROM CITATION WHERE BOOK_NAME = LOWER(:bookName)")
    fun filterByBook(bookName : String) : List<Citation>

    @Query("""
    SELECT
        C.CITATION_ID,
        C.AUTHOR_FNAME,
        C.AUTHOR_LNAME,
        C.BOOK_NAME,
        C.CITATION
    FROM
	    (SELECT 
            CITATION_ID
        FROM
            TAG
	    WHERE
            LOWER(TAG) = LOWER(:tag)) T
	
	    JOIN

	    CITATION C
	
	    ON T.CITATION_ID = C.CITATION_ID""")
    fun searchByTag(tag : String) : List<Citation>
}