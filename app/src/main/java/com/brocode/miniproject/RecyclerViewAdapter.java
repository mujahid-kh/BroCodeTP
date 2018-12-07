package com.brocode.miniproject;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    private LayoutInflater mInflater;
    List<GraphObject> graphObject;

    // data is passed into the constructor
    RecyclerViewAdapter(Context context, List<GraphObject> graphObject) {
        this.mInflater = LayoutInflater.from(context);
        this.graphObject = graphObject;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.rv_item_cards, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //holder.mSampleTextView.setText(mSampleText.get(position));
        holder.graph.setTitle("Assfaces per km");
        LineGraphSeries<DataPoint> series = null;
        for(int i = 0; i < graphObject.get(position).getCoordinates().length; i++) {
            series = new LineGraphSeries<>(new DataPoint[]{
                    new DataPoint(0, 0),
                    new DataPoint(graphObject.get(position).getCoordinates()[i][0], graphObject.get(position).getCoordinates()[i][1]),
            });
            holder.graph.addSeries(series);
        }
        holder.graph.getViewport().setXAxisBoundsManual(true);
        holder.graph.getViewport().setYAxisBoundsManual(true);
        holder.graph.getViewport().setMaxX(graphObject.get(position).getTime());
        holder.graph.getViewport().setMaxY(graphObject.get(position).getBudget());
        holder.graph.getViewport().setMinX(0);
        holder.graph.getViewport().setMinY(0);
        holder.graph.setTitle(graphObject.get(position).getTitle());

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return graphObject.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder{
        GraphView graph;


        ViewHolder(View itemView) {
            super(itemView);
            //mSampleTextView = itemView.findViewById(R.id.textView);
            graph = (GraphView) itemView.findViewById(R.id.graph);
        }

    }



}