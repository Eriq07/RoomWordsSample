package com.example.roomwordssample;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.roomwordssample.DaoClass.WordDao;
import com.example.roomwordssample.DatabaseClass.WordRoomDatabase;

public class MyWorker extends Worker {

    WordDao worKerDao;

    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);

        //Getting DAO
        WordRoomDatabase db = WordRoomDatabase.getDatabase(getApplicationContext());
        worKerDao = db.wordDao();

    }

    @NonNull
    @Override
    public Result doWork() {

        deleteAllWordsBackground();

        displayNotification();

        return Result.success();
    }

    public void deleteAllWordsBackground(){
//        WordDao workerDao;
//        workerDao = dao;
//        workerDao.deleteAll();
        worKerDao.deleteAll();
    }

    private void displayNotification(){

        NotificationManager manager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.O){

            NotificationChannel channel =
                    new NotificationChannel("simplifiedcoding", "deletingall", NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);

        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "simplifiedcoding")
                .setContentTitle("DELETING ALL")
                .setContentText("This is deleting everything")
                .setSmallIcon(R.mipmap.ic_launcher);

        manager.notify(1, builder.build());

    }
}
