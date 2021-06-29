package com.urinapa.satvocabulary.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.urinapa.satvocabulary.DataClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class VocabViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<Vocab>>
    val countVocabs: LiveData<Int>
    private val repository: VocabRepository
    init {
        val vocabDao = VocabDatabase.getDatabase(application).vocabDao()
        repository = VocabRepository(vocabDao)
        readAllData = repository.readAllData
        countVocabs = repository.countVocabs
    }

    fun addVocab(vocab: Vocab) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addVocab(vocab)
        }
    }

    fun updateVocab(vocab: Vocab) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateVocab(vocab)
        }
    }

    fun deleteVocab(vocab: Vocab) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteVocab(vocab)
        }
    }

    fun deleteAllVocabs() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllVocabs()
        }
    }

    fun getAllVocabularies(): Job {
        lateinit var vocabularies: List<Vocab>;
        return viewModelScope.launch(Dispatchers.IO) {
            vocabularies = repository.getAllVocabularies()
            DataClass.vocabList = vocabularies
        }
    }
}