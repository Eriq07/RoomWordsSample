package com.example.roomwordssample;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.roomwordssample.entities.Word;

import java.util.List;

public class WordViewModel extends AndroidViewModel {
    private static final String NAME_KEY = "wordskey";

    private WordRepository mRepository;
    private LiveData<List<Word>> mAllWords;

   // private SavedStateHandle mState;

    //Add a constructor that gets a reference to the WordRepository and gets the list of all words from the WordRepository.
    //public WordViewModel(@NonNull Application application, SavedStateHandle savedStateHandle) {
    public WordViewModel(@NonNull Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();


        //ALL NEW STUFF TO TRY
//        mState = savedStateHandle;
//        mState.set(NAME_KEY, mAllWords.getValue());


    }

//    public void setQuery(List<Word> AllWords ) {
//        state.set("query", AllWords);
//    }

//    LiveData<List<Word>> getAllWords() {
//        return mState.getLiveData(NAME_KEY);
//    }





//Add a "getter" method that gets all the words. This completely hides the implementation from the UI.
   LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }
//    Create a wrapper insert() method that calls the Repository's insert() method.
//    In this way, the implementation of insert() is completely hidden from the UI.
    public void insert (Word word){
        mRepository.insert(word);
    }

    //Make the deleteAll() method available to the MainActivity by adding it to the WordViewModel.
    public void deleteAll() {mRepository.deleteAll();}

    public void deleteWord(Word word) {mRepository.deleteWord(word);}

}
