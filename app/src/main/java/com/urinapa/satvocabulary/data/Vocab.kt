package com.urinapa.satvocabulary.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vocabularies")
data class Vocab(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val word: String,
    val definition: String,
    val example: String
)
