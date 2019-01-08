package com.brocode.miniproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Set;

public class RecyclerViewPeople extends RecyclerView.Adapter<RecyclerViewPeople.ViewHolder> {


    private LayoutInflater mInflater;
    private HashMap<String, Integer> people;

    // data is passed into the constructor
    RecyclerViewPeople(Context context, HashMap<String, Integer> people) {
        this.mInflater = LayoutInflater.from(context);
        this.people = people;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.rv_item_people, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //holder.mSampleTextView.setText(mSampleText.get(position));
        Set peopleList = people.keySet();
        holder.name.setText((String) peopleList.toArray()[position]);
        holder.hour.setText(people.get(peopleList.toArray()[position]) + "h");

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return people.size() - 1;
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name, hour;


        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textViewName);
            hour = itemView.findViewById(R.id.textViewHour);
        }

    }



}