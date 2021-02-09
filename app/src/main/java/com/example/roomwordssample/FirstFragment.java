package com.example.roomwordssample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.roomwordssample.ViewModel.SharedViewModelNavigation;

public class FirstFragment extends Fragment {

    private NavController mNavController;

    //SharedViewModelNavigation
    private SharedViewModelNavigation mSharedViewModelNavigation;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mNavController = Navigation.findNavController(view);

        //SharedViewModelNavigation
        mSharedViewModelNavigation = new ViewModelProvider(requireActivity()).get(SharedViewModelNavigation.class);
        mSharedViewModelNavigation.getupdates().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                TextView updates = view.findViewById(R.id.textview_first);
                updates.setText(s);
            }
        });


        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mSharedViewModelNavigation.createNumber();

                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);


               // mNavController.navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });




    }
}