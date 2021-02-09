package com.example.roomwordssample.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.roomwordssample.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 *//*
public class ProductFragment extends Fragment {

    public ProductFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product, container, false);
    }
    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tvInfo = view.findViewById(R.id.tv_product_info);
        ProductFragmentArgs args = ProductFragmentArgs.fromBundle(getArguments());
        //String name = args.getProductName();
        //Float amount = args.getAmount();
        //Integer itemCount = args.getItemCount();
        tvInfo.setText(getString(R.string.name_lable)
                + args.getProductName()
                + "\n" +
                " Count "
                + args.getItemCount()
                + "\n" +
                " Amount "
                + args.getAmount());
    }
}*/