package com.example.roomwordssample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomwordssample.Adaptor.ItemAdapter;
import com.example.roomwordssample.ViewModel.ItemViewModel;

public class MessageFragment extends Fragment {

    View v;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_message,container, false);
        recyclerView = v.findViewById(R.id.message_recyclerview);
      //  ItemAdapter itemAdapter = new ItemAdapter(getContext());
        //This is a try
        ItemViewModel itemViewModel = ViewModelProviders.of(getActivity()).get(ItemViewModel.class);
        final ItemAdapter adapter = new ItemAdapter(getContext());

        //HERE TOO
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        //TO HERE

        itemViewModel.itemPagedList.observe(getViewLifecycleOwner(), new Observer<PagedList<Item>>() {
            @Override
            public void onChanged(PagedList<Item> items) {

                adapter.submitList(items);
            }
        });

        recyclerView.setAdapter(adapter);

        //End of try
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.setHasFixedSize(true);
       // recyclerView.setAdapter(ad);



        return v;
    }

  //  recyclerView = f

  /*  @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ItemViewModel itemViewModel = ViewModelProviders.of(getActivity()).get(ItemViewModel.class);
        final ItemAdapter adapter = new ItemAdapter(getContext());

        itemViewModel.itemPagedList.observe(this, new Observer<PagedList<Item>>() {
            @Override
            public void onChanged(PagedList<Item> items) {

                adapter.submitList(items);
            }
        });

       // recyclerView.setAdapter(adapter);


    }*/
}
