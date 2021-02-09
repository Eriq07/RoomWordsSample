package com.example.roomwordssample.DatabaseClass;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.roomwordssample.DaoClass.WordDao;
import com.example.roomwordssample.entities.Word;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {

    //Define the DAOs that work with the database. Provide an abstract "getter" method for each @Dao
    public abstract WordDao wordDao();

    private static WordRoomDatabase INSTANCE;

//    We've created an ExecutorService with a fixed thread pool that you will use to run database operations
//    asynchronously on a background thread.
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    //    Create the WordRoomDatabase as a singleton to prevent having multiple instances of the database opened at the same time,
//    which would be a bad thing.
    public static WordRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WordRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class, "word_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /* ---Old code version

//    ----Create the callback for populating the database-----
//    To delete all content and repopulate the database whenever the app is started,
//    you create a RoomDatabase.Callback and override the onOpen() method.
//    Because you cannot do Room database operations on the UI thread,
//    onOpen() creates and executes an AsyncTask to add content to the database.

    //   1. Add the onOpen() callback in the WordRoomDatabase class:
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

//  2.  Create an inner class PopulateDbAsync that extends AsycTask.
//    Implement the doInBackground() method to delete all words, then create new ones.
//    Here is the code for the AsyncTask that deletes the contents of the database,
//   then populates it with an initial list of words. Feel free to use your own words!


     // Populate the database in the background.

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final WordDao mDao;
        String[] words = {"dolphin", "crocodile", "cobra"};

        PopulateDbAsync(WordRoomDatabase db) {
            mDao = db.wordDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate the database
            // when it is first created
            mDao.deleteAll();

            for (int i = 0; i <= words.length - 1; i++) {
                Word word = new Word(words[i]);
                mDao.insert(word);
            }
            return null;
        }
    }*/


//----- New version of the code ---
//To delete all content and populate the database when the app is installed, you create a RoomDatabase.Callback and override onCreate().
//Because you cannot do Room database operations on the UI thread,
//  onCreate() uses the previously defined databaseWriteExecutor to execute a lambda on a background thread.
//  The lambda deletes the contents of the database, then populates it with the two words "Hello" and "World".
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                WordDao dao = INSTANCE.wordDao();
                dao.deleteAll();

                // If we have no words, then create the initial list of words
                if (dao.getAnyWord().length < 1) {
                    Word word = new Word("Hello");
                    dao.insert(word);
                    word = new Word("World");
                    dao.insert(word);
                }
            });
        }
    };


}
