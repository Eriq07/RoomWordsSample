package com.example.roomwordssample;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.roomwordssample.DaoClass.WordDao;
import com.example.roomwordssample.DatabaseClass.WordRoomDatabase;

public class DeleteWordWorker extends Worker {

    WordDao worKerDao;
    public DeleteWordWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);


        //Getting DAO
        WordRoomDatabase db = WordRoomDatabase.getDatabase(getApplicationContext());
        worKerDao = db.wordDao();
    }

    @NonNull
    @Override
    public Result doWork() {

//        Data data = getInputData();
//        Word resourceWord = data.getClass("KEY_WORD");
//        worKerDao.deleteWord();
        return null;
    }
}
