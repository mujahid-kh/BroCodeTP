package com.brocode.miniproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.List;

public class RecyclerViewPeople extends RecyclerView.Adapter<RecyclerViewPeople.ViewHolder> {


    private LayoutInflater mInflater;
    private List<String> nameList;
    private List<Integer> hourList;

    // data is passed into the constructor
    RecyclerViewPeople(Context context, List<String> nameList, List<Integer> hourList) {
        this.mInflater = LayoutInflater.from(context);
        this.nameList = nameList;
        this.hourList = hourList;
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
        holder.name.setText(nameList.get(position));
        holder.hour.setText(hourList.get(position) + "h");

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return nameList.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name, hour;


        ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.textViewName);
            hour = (TextView) itemView.findViewById(R.id.textViewHour);
        }

    }



}