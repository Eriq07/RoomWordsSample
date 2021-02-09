package com.example.roomwordssample.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.parceler.Parcel;

@Entity(tableName = "word_table")
public class Word {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    private String mWord;

//Required by Parcel
   // public Word(){}

    public Word(@NonNull String word){
        this.mWord = word;

    }
    public String getWord() {

        return this.mWord;
    }

}