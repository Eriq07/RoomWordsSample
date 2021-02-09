package com.example.roomwordssample;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.roomwordssample.entities.Word;

//Create a class WordListAdapter that extends ListAdapter.
//Create the DiffUtil.ItemCallback implementation as a static class in the WordListAdapter
public class WordCustomListAdaptor extends ListAdapter<Word, WordCustomViewHolder> {

    protected WordCustomListAdaptor(@NonNull DiffUtil.ItemCallback<Word> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public WordCustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return WordCustomViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull WordCustomViewHolder holder, int position) {
        Word current = getItem(position);
        holder.bind(current.getWord());

    }

    static class WordDiff extends DiffUtil.ItemCallback<Word> {

        @Override
        public boolean areItemsTheSame(@NonNull Word oldItem, @NonNull Word newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Word oldItem, @NonNull Word newItem) {
            return oldItem.getWord().equals(newItem.getWord());
        }
    }


}
