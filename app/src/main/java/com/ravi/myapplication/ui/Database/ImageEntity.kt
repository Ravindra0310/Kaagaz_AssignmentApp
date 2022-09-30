package com.ravi.myapplication.ui.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="kaagaz_table")
data class ImageEntity (
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "TimeStamp") val TimeStamp : String,
    @ColumnInfo(name = "Albums") val Albums: String) {

        @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Int? = null

}
