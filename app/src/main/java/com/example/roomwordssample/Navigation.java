package com.example.roomwordssample;

import android.os.Bundle;

import com.example.roomwordssample.ViewModel.SharedViewModelNavigation;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import android.view.Menu;

import static androidx.navigation.Navigation.findNavController;
/*
Open the main activity, In this activity, we are setting bottom navigation,
side navigation (for landscape mode) and setting up action menu as well.
For doing all things we are using Navigation Component only.
 */

public class Navigation extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private NavController navController;
    private DrawerLayout drawerLayout;

    //SharedViewModelNavigation
    private SharedViewModelNavigation mSharedViewModelNavigation;

    private BottomAppBar mBottomAppBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //My new code
        //SharedViewModelNavigation
        mSharedViewModelNavigation = new ViewModelProvider(this).get(SharedViewModelNavigation.class);

       // getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment1, new FirstFragment()).commit();
        mBottomAppBar = findViewById(R.id.bottomAppBar);
        setSupportActionBar(mBottomAppBar);

        //mBottomAppBar.setOnMenuItemClickListener();
        //mBottomAppBar.setNavigationOnClickListener();




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.bottom_nav_menu, menu);

        return super.onCreateOptionsMenu(menu);

    }

        //----Starting here


     /*   navController = findNavController(this, R.id.nav_host_fragment1);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        drawerLayout = findViewById(R.id.drawer_layout);
        setUpBottomNav(navController);
        setUpSideNav(navController);
        setUpActionBar(navController);


        //SharedViewModelNavigation
        mSharedViewModelNavigation = new ViewModelProvider(this).get(SharedViewModelNavigation.class);

    }
    private void setUpBottomNav(NavController navController) {
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }
    private void setUpSideNav(NavController navController) {
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }
    private void setUpActionBar(NavController navController) {
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        boolean navigated = NavigationUI.onNavDestinationSelected(item, navController);
        return navigated || super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(
                findNavController(this, R.id.nav_host_fragment1), drawerLayout);
    }*/
}