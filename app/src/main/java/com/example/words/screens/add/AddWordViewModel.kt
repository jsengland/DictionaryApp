package com.example.words.screens.add

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.words.database.WordDatabase
import com.example.words.database.WordRepository
import com.example.words.entity.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddWordViewModel(
    word: Word, application: Application
) : AndroidViewModel(application) {
    val word = word

    val readAlldata: LiveData<List<Word>>
    private val repository: WordRepository

    init{
        val wordDao = WordDatabase.getInstance(application).wordDao
        repository = WordRepository(wordDao)
        readAlldata = repository.readAllData
    }

    fun addWord(word: Word){
        viewModelScope.launch(Dispatchers.IO){
            repository.addWord(word)
        }
    }

}