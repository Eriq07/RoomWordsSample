package com.example.roomwordssample.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Random;

public class SharedViewModelNavigation extends ViewModel {

    private static final String TAG = "SharedViewModelNavigation";
    private MutableLiveData<String> updates;


    public LiveData<String> getupdates(){
        //Code to load updates
        if(updates == null){

            updates = new MutableLiveData<>();
            createNumber();

        }


        return updates;
    }


    public void createNumber(){
        Log.i(TAG, "Create new number");
        Random random = new Random();
        //updates = "Number: " + (random.nextInt(10 - 1) + 1);
        updates.setValue("Number: " + (random.nextInt(10 - 1) + 1));
    }
}
