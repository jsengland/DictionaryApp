package com.example.words.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.words.entity.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordRepository(private val wordDao: WordDao) {
    val readAllData: LiveData<List<Word>> = wordDao.getAllWords()

    suspend fun addWord(word: Word){
        wordDao.insertWord(word)
    }



    suspend fun updateWord(word: Word){
        wordDao.updateWord(word)
    }
}