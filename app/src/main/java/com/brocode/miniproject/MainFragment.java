package com.brocode.miniproject;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class MainFragment extends Fragment {

    List<GraphObject> graphs = new ArrayList<>();

    private int cardSize = 0;


    public void initiateFragment(int cardSize){
       this.cardSize = cardSize;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        //HOW TO; Make a graph and display it
        //---------------
        GraphObject graph1 = new GraphObject();
        graph1.setTitle("Test Graph");
        graph1.setBudget(100);
        graph1.setTime(100);
        graph1.setCoordinates(new int[][]{{45,50},{80,99}});


        addGraph(graph1);
        //----------------

        GraphObject graph2 = new GraphObject();
        graph2.setTitle("Test Graph 2");
        graph2.setBudget(5);
        graph2.setTime(8);
        graph2.setCoordinates(new int[][]{{5,5},{1,3}});


        addGraph(graph2);


        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity(), graphs);
        recyclerView.setAdapter(adapter);

        return view;
    }


    private void addGraph(GraphObject graphObject){
        graphs.add(graphObject);
    }




}
