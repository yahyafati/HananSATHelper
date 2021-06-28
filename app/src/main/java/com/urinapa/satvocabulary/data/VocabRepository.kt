package com.urinapa.satvocabulary.data

import androidx.lifecycle.LiveData

class VocabRepository(private val vocabDao: VocabDao) {

    val readAllData: LiveData<List<Vocab>> = vocabDao.readAllData()
    val countVocabs: LiveData<Int> = vocabDao.countVocabs()

    suspend fun addVocab(vocab: Vocab) {
        vocabDao.addVocab(vocab)
    }

    suspend fun updateVocab(vocab: Vocab) {
        vocabDao.updateVocab(vocab)
    }

    suspend fun deleteVocab(vocab: Vocab) {
        vocabDao.deleteVocab(vocab)
    }

    suspend fun deleteAllVocabs() {
        vocabDao.deleteAllVocabs()
    }
}