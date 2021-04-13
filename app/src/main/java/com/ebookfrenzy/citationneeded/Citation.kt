package com.ebookfrenzy.citationneeded

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CITATION")
class Citation {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "CITATION_ID")
    var citationID : Long = 0

    @ColumnInfo(name = "AUTHOR_FNAME")
    @NonNull
    var fname : String = ""

    @ColumnInfo(name = "AUTHOR_LNAME")
    @NonNull
    var lname : String = ""

    @ColumnInfo(name = "BOOK_NAME")
    var bookName : String = ""

    @ColumnInfo(name = "CITATION")
    @NonNull
    var citation : String = ""

    constructor(fname: String, lname : String, bookName : String, citation : String) {
        this.fname = fname
        this.lname = lname
        this.bookName = bookName
        this.citation = citation
    }
}