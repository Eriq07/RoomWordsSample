package com.example.roomwordssample.DaoClass;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.roomwordssample.entities.Word;

import java.util.List;

@Dao
public interface WordDao {

    // This method will create a DataSource for Paging. It will provide Number of Items required to
    // show on a page. DataSource.Factory is used to creating a DataSource.
    @Query("SELECT * from word_table ORDER BY word ASC")
    public abstract DataSource.Factory<Integer, Word> getAllUsers();

    // We do not need a conflict strategy, because the word is our primary key, and you cannot
    // add two items with the same primary key to the database. If the table has more than one
    // column, you can use @Insert(onConflict = OnConflictStrategy.REPLACE) to update a row.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Word word);

    @Query("DELETE FROM word_table")
    void deleteAll();

    @Query("SELECT * from word_table ORDER BY word ASC")
    LiveData<List<Word>> getAllWords();


    //Part B: Deleting data from a Room database
    //In the WordDao interface, add a method to get any word
//    Room issues the database query when the getAnyWord() method is called and returns an array containing one word.
//    You don't need to write any additional code to implement it.
    @Query("SELECT * from word_table LIMIT 1")
    Word[] getAnyWord();

    //Task 4: Delete a single word
    @Delete
    void deleteWord(Word word);

}
