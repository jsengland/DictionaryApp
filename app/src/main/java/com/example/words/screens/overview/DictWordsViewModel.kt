package com.example.words.screens.overview

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.words.database.WordDao
import com.example.words.database.WordDatabase
import com.example.words.database.WordRepository
import com.example.words.entity.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DictWordsViewModel(
    dao: WordDao,
    application: Application
) : AndroidViewModel(application) {



    val repository: WordRepository
    private var _currentFilter = MutableLiveData<WordsFilter>()
    val currentFilter: LiveData<WordsFilter>
        get() = _currentFilter


//    private val _dictWords = dao.getActiveWords() - dont think i need
    var dictWords: LiveData<List<Word>>
//        get() = _dictWords - dont think i need

    init {
        dictWords = dao.getActiveWords()
        _currentFilter.value = WordsFilter.SHOW_ACTIVE

        val wordDao = WordDatabase.getInstance(application).wordDao
        repository = WordRepository(wordDao)
    }

    fun changeFilter(filter: WordsFilter, dao: WordDao) {
        dictWords = when (filter) {
            WordsFilter.SHOW_ALL -> dao.getAllWords()
            WordsFilter.SHOW_ACTIVE -> dao.getActiveWords()
            else -> dao.getInactiveWords()
        }
        _currentFilter.value = filter
    }


    fun updateWord(word: Word){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateWord(word)
        }
    }
}

enum class WordsFilter {
    SHOW_ALL,
    SHOW_ACTIVE,
    SHOW_INACTIVE
}