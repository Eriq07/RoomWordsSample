package com.example.roomwordssample;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;

import com.example.roomwordssample.entities.Word;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    public static final String CHANNEL_ID = "BroadcastChannel_id";

    public static final String CUSTOM =
            BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM";
    NotificationCompat.Builder builder;
    CustomReceiver mReceiver001;
    //   ----     Display the words-----
//      1.  In MainActivity, create a member variable for the ViewModel,
//        because all the activity's interactions are with the WordViewModel only.

    private WordViewModel mWordViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //-----instate state
      //  if(savedInstanceState != null)

/* -----The old version of code---

     //2.   In the onCreate() method, get a ViewModel from the ViewModelProviders class.
//        Use ViewModelProviders to associate your ViewModel with your UI controller.
//        When your app first starts, the ViewModelProviders class creates the ViewModel.
//        When the activity is destroyed, for example through a configuration change, the ViewModel persists.
//        When the activity is re-created, the ViewModelProviders return the existing ViewModel.
        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewWordActivity.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

        // ---Old implementation----
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final WordListAdapter adapter = new WordListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


//     3.   Also in onCreate(), add an observer for the LiveData returned by getAllWords().
//        When the observed data changes while the activity is in the foreground,
//        the onChanged() method is invoked and updates the data cached in the adapter.
//         Note that in this case, when the app opens, the initial data is added, so onChanged() method is called.

        mWordViewModel.getAllWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(@Nullable final List<Word> words) {
                // Update the cached copy of the words in the adapter.
                adapter.setWords(words);
            }
        });*/



        // ---The new version of the code

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewWordActivity.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });


       /* RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final WordCustomListAdaptor adapter = new WordCustomListAdaptor(new WordCustomListAdaptor.WordDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));*/
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final WordListAdapter adapter = new WordListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


  //      Use ViewModelProvider to associate your ViewModel with your Activity.
//   When your Activity first starts, the ViewModelProviders will create the ViewModel.
//  When the activity is destroyed, for example through a configuration change, the ViewModel persists.
//        When the activity is re-created, the ViewModelProviders return the existing ViewModel. For more information, see ViewModel.
        mWordViewModel = new ViewModelProvider(this).get(WordViewModel.class);
        mWordViewModel.getAllWords().observe(this, words -> {
            // Update the cached copy of the words in the adapter.
            //adapter.submitList(words);
            adapter.setWords(words);
        });



        // Add the functionality to swipe items in the
// recycler view to delete that item
        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView,
                                          RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder,
                                         int direction) {
                        int position = viewHolder.getAdapterPosition();
                        Word myWord = adapter.getWordAtPosition(position);
                        Toast.makeText(MainActivity.this, "Deleting " +
                                myWord.getWord(), Toast.LENGTH_LONG).show();

                        // Delete the word
                        mWordViewModel.deleteWord(myWord);
                    }
                });

        helper.attachToRecyclerView(recyclerView);


       /* mReceiver001 = new CustomReceiver();

        //  inside onCreate() method, get an instance of LocalBroadcastManager and register your receiver with the custom Intent action:
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(mReceiver001,
                        new IntentFilter(ACTION_CUSTOM_BROADCAST));*/
        mReceiver001 = new CustomReceiver();

// 2. Create an intent filter with Intent actions
//Intent filters specify the types of intents a component can receive.
// They are used in filtering out the intents based on Intent values like action and category.
        IntentFilter filter = new IntentFilter();
        filter.addAction(CUSTOM);


        //3. Register the receiver using the activity context.
        this.registerReceiver(mReceiver001, filter);
    }




//    In MainActivity, add the onActivityResult() callback for the NewWordActivity.
//    If the activity returns with RESULT_OK,
//    insert the returned word into the database by calling the insert() method of the WordViewModel.

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Word word = new Word(data.getStringExtra(NewWordActivity.EXTRA_REPLY));
            mWordViewModel.insert(word);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //int id = item.getItemId();

        //Switch statement
        switch (item.getItemId()) {
            case R.id.clear_data:
                // Add a toast just for confirmation
                Toast.makeText(this, "Clearing the data...",
                        Toast.LENGTH_SHORT).show();

                // Delete the existing data
                mWordViewModel.deleteAll();
                return true;
            case R.id.customBroadcastAction:
                //Call Broadcast channel
                createNotificationChannel();
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

// notificationId is a unique int for each notification that you must define
                notificationManager.notify(1573, builder.build());

                return true;

            case R.id.customToastAction:
                //displayToast("Delete choice clicked.");
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.custom_toast,
                        (ViewGroup) findViewById(R.id.custom_toast_container));

                TextView text = (TextView) layout.findViewById(R.id.text);
                text.setText("This is a custom toast");

                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();

                return true;
            case R.id.settingsFragment:

                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

        //noinspection SimplifiableIfStatement
       /* if (id == R.id.clear_data) {
            // Add a toast just for confirmation
            Toast.makeText(this, "Clearing the data...",
                    Toast.LENGTH_SHORT).show();

            // Delete the existing data
            mWordViewModel.deleteAll();
            return true;
        }

        return super.onOptionsItemSelected(item);*/
    }

    //Creat Notification channel
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
           // CharSequence name = getString(R.string.channel_name);
            //String description = getString(R.string.channel_description);

            CharSequence name = "BroadcastChannel";
            String description = "This is the channel description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        // Create an explicit intent for an Activity in your app
       /* Intent intent = new Intent(this, NewWordActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);*/


        Intent snoozeIntent = new Intent(this, CustomReceiver.class);
       snoozeIntent.setAction(CUSTOM);
        snoozeIntent.putExtra("ID", 1573);
        PendingIntent snoozePendingIntent =
                PendingIntent.getBroadcast(this, 0, snoozeIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.droid)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .addAction(R.drawable.droid, "Snooze",
                        snoozePendingIntent);



    }


}