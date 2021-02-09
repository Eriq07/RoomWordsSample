package com.example.roomwordssample;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.example.roomwordssample.DaoClass.WordDao;
import com.example.roomwordssample.DatabaseClass.WordRoomDatabase;
import com.example.roomwordssample.entities.Word;

import java.util.List;

public class WordRepository {

    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;
    private WorkManager mWorkManager;

    //Add a constructor that gets a handle to the database and initializes the member variables.
    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
       // mAllWords = mWordDao.getAllWords();

        PagedList.Config config = (new PagedList.Config.Builder())
                .setPageSize(20)
                .setEnablePlaceholders(false)
                .setPrefetchDistance(10)
                .build();

        mAllWords = (new LivePagedListBuilder(mWordDao.getAllUsers(),config)).build();

         mWorkManager = WorkManager.getInstance(application);
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

//    Add a wrapper for the insert() method. Use an AsyncTask to call insert() on a non-UI thread,
//    or your app will crash. Room ensures that you don't do any long-running operations on the main thread,
//    which would block the UI.
  /*  public void insert (Word word) {
        new insertAsyncTask(mWordDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        public insertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }*/

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(Word word) {
        WordRoomDatabase.databaseWriteExecutor.execute(() -> {
            mWordDao.insert(word);
        });
    }

    //Worker class
   // OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(MyWorker.class).build();


    //In the WordRepository class, add the deleteAll() method to invoke the AsyncTask that you defined.
    public void deleteAll(){

//        MyWorker worker = new MyWorker()
  //      WorkManager.getInstance().enqueue(request);

           OneTimeWorkRequest blurRequest =
                new OneTimeWorkRequest.Builder(MyWorker.class)
                        .build();

        mWorkManager.enqueue(blurRequest);
    }



    public void deleteWord(Word word)  {
        new deleteWordAsyncTask(mWordDao).execute(word);
    }

  //  @SuppressLint("RestrictedApi")
   // public void deleteWord(Word word)  {

       private static class deleteWordAsyncTask extends AsyncTask<Word, Void, Void> {
            private WordDao mAsyncTaskDao;

            deleteWordAsyncTask(WordDao dao) {
                mAsyncTaskDao = dao;
            }

            @Override
            protected Void doInBackground(final Word... params) {
                mAsyncTaskDao.deleteWord(params[0]);
                return null;
            }
        }
        
/*
        if (word != null) {
//            builder.putString("KEY_WORD", parcelable)
//            builder.put("KEY_WORD", Word word)
            Data data = new Data.Builder()
                    .put("KEY_WORD", word)
                    .build();

        
      //
            OneTimeWorkRequest deleteRequest = new OneTimeWorkRequest.Builder(DeleteWordWorker.class)
                    .setInputData(data)
                        .build();

        mWorkManager.enqueue(deleteRequest);
        }*/

    //}


}
