package com.urinapa.satvocabulary.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "vocabularies")
@Parcelize
class Vocab(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val word: String,
    val definition: String,
    val example: String
): Parcelable {
    fun formatDefinition() : String {
        val definitionList = definition.split("\n")
        val exampleList = example.split("\n")
        var definition = ""
        for (i in definitionList.indices) {
            definition += "${definitionList[i]}\n\t${exampleList[i]}\n\n"
        }
        return definition
    }
}
