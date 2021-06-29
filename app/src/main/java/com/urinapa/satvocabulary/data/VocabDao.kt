package com.urinapa.satvocabulary.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface VocabDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addVocab(vocab: Vocab)

    @Update
    suspend fun updateVocab(vocab: Vocab)

    @Delete
    suspend fun deleteVocab(vocab: Vocab)

    @Query("DELETE from vocabularies")
    suspend fun deleteAllVocabs()

    @Query("SELECT * from vocabularies")
    fun readAllData(): LiveData<List<Vocab>>

    @Query("SELECT * from vocabularies")
    suspend fun getAllVocabularies(): List<Vocab>

    @Query("SELECT count(*) from vocabularies")
    fun countVocabs() : LiveData<Int>

//    fun updateUser(vocab: Vocab)
//    fun deleteUser(vocab: Vocab)
//    fun addUser(vocab: Vocab)
//    fun addUser(vocab: Vocab)
}