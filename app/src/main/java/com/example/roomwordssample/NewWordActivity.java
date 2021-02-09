package com.example.roomwordssample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class NewWordActivity extends AppCompatActivity {

    public static final String TAG = "NewWordActivity";

// 1.   One of the simplest ways to get your app's package name is to use BuildConfig.APPLICATION_ID,
//    which returns the applicationId property's value from your module-level build.gradle file**.**
    public static final String ACTION_CUSTOM_BROADCAST =
           BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";

    private CustomReceiver mReceiver;

    public static final String EXTRA_REPLY = "com.example.android.roomwordssample.REPLY";

    private Button mSnackbar;
    private CoordinatorLayout mSnackbarLayout;

    private EditText mEditWordView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);
        mEditWordView = findViewById(R.id.edit_word);



        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditWordView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String word = mEditWordView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, word);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
        final Button buttonStart = findViewById(R.id.button_startActivity);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentStart = new Intent(NewWordActivity.this, StartActivity.class);
                startActivity(intentStart);
                finish();

            }
        });

        mReceiver = new CustomReceiver();

      //  inside onCreate() method, get an instance of LocalBroadcastManager and register your receiver with the custom Intent action:
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(mReceiver,
                        new IntentFilter(ACTION_CUSTOM_BROADCAST));


    }

    public void sendCustomBroadcast(View view) {
        //2. Create a new Intent, with your custom action string as the argument.

        Intent customBroadcastIntent = new Intent(ACTION_CUSTOM_BROADCAST);

        //3. After the custom Intent declaration, send the broadcast using the LocalBroadcastManager class:
        LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent);
    }


    //Inside the onDestroy() method, unregister your receiver from the LocalBroadcastManager:
    @Override
    protected void onDestroy() {

        LocalBroadcastManager.getInstance(this)
                .unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    public void startCustomSnackbar(View view) {

        mSnackbarLayout = findViewById(R.id.snackbar_layout);


        Snackbar snackbar = Snackbar.make(mSnackbarLayout, "You just clicked the Snackbar button", Snackbar.LENGTH_LONG);
        snackbar.setDuration(10000);
        snackbar.setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE);

        snackbar.setAction("OKAY", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do something here
            }
        });
                snackbar.show();
    }

    public void startNavigationActivity(View view) {
        Log.d(TAG, "The button as been pressed");

        Intent intentNavigation = new Intent(NewWordActivity.this, Navigation.class);
        startActivity(intentNavigation);

        finish();
    }
}