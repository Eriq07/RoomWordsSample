package com.example.roomwordssample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StartActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    ViewPager pager;
    //ViewPager2 pager;
    TabLayout mTabLayout;
    TabItem firstItem, secondItem, thirdItem;
    PagerAdapter adapter;

    private CustomReceiver mReceiver;
  //  androidx.viewpager.widget.PagerAdapter adapter;

    //SharedPreferences
    SharedPreferences mSharedPreferences;

    EditText userEmail;
    TextView userTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);



     //   /*

        pager = findViewById(R.id.viewpager);
        mTabLayout = findViewById(R.id.tablayout);
        firstItem = findViewById(R.id.firstitem);
        secondItem = findViewById(R.id.seconditem);
        thirdItem =  findViewById(R.id.thirditem);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
         navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

         toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        adapter = new PagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, mTabLayout.getTabCount());
        pager.setAdapter(adapter);

       // pager.setAdapter(adapter);

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
       // pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
         pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
       // pager.addOnAttachStateChangeListener((View.OnAttachStateChangeListener) new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

//         if(savedInstanceState == null){
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MessageFragment()).commit();
//        navigationView.setCheckedItem(R.id.nav_message);
//         }

     //    */
       // runRetrofit();
         /*------------Broadcasts---------*/

      // 1. In MainActivity.java, create a CustomReceiver object as a member variable and initialize it.
         mReceiver = new CustomReceiver();

// 2. Create an intent filter with Intent actions
//Intent filters specify the types of intents a component can receive.
// They are used in filtering out the intents based on Intent values like action and category.
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        filter.addAction(Intent.ACTION_POWER_CONNECTED);

        //3. Register the receiver using the activity context.
        this.registerReceiver(mReceiver, filter);

//Instanciate sharedpreference
        mSharedPreferences = getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);
        String email = mSharedPreferences.getString("email", "");

      /*  userEmail = findViewById(R.id.userEmail);
       // userEmail.setText(email);

        userTitle = findViewById(R.id.userTitle);
        userTitle.setText(email);*/

        Log.d("Sharedpref", email);
        

    }
    //override the onDestroy() method and unregister your receiver
    @Override
    protected void onDestroy() {
        //Unregister the receiver
        this.unregisterReceiver(mReceiver);
        super.onDestroy();
    }

   /* private void runRetrofit() {

        Call<StackApiResponse> call = RetrofitClient.getInstance()
                .getApi()
                .getAnswers(1,50,"stackoverflow");
        call.enqueue(new Callback<StackApiResponse>() {
            @Override
            public void onResponse(Call<StackApiResponse> call, Response<StackApiResponse> response) {
                StackApiResponse stackApiResponse = response.body();
                Toast.makeText(StartActivity.this, String.valueOf(stackApiResponse.items.size()), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<StackApiResponse> call, Throwable t) {

            }
        });
    }*/




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_message:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MessageFragment()).commit();
                break;

            case R.id.nav_chat:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ChatFragment()).commit();
                break;

            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
                break;

            case R.id.nav_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }

         userEmail = findViewById(R.id.userEmail);
         String emailString = userEmail.getText().toString();

         SharedPreferences.Editor editor = mSharedPreferences.edit();

         editor.putString("email", emailString);
         editor.commit();
         Toast.makeText(this,"Email is Saved", Toast.LENGTH_SHORT).show();

        String email = mSharedPreferences.getString("email", "");


        userEmail = findViewById(R.id.userEmail);
        userEmail.setText(email);

        userTitle = findViewById(R.id.userTitle);
        userTitle.setText(email);




    }
}