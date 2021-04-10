package com.ebookfrenzy.citationneeded

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TAG", primaryKeys = ["TAG", "CITATION_ID"])
class Tag {
    @ColumnInfo(name = "TAG")
    @NonNull
    var tag : String = ""

    @ColumnInfo(name = "CITATION_ID")
    @NonNull
    var citationID : Int = 0
}