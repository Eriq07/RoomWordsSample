package com.example.roomwordssample.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.roomwordssample.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
/*
public class HomeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button viewProfile = view.findViewById(R.id.buttonViewProfile);
        viewProfile.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                // find navigation
                NavController navController = Navigation.findNavController(view);
                navController.navigate(HomeFragmentDirections.nextAction());
                //One line also can do like this
                // Navigation.findNavController(view).navigate(HomeFragmentDirections.nextAction());
            }
        });
    }
}*/