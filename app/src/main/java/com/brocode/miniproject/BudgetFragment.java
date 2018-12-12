package com.brocode.miniproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BudgetFragment extends Fragment {

    List<GraphObject> graphs = new ArrayList<>();

    List<String> nameList;
    List<Integer> hourList;

    TextView allHours;



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        allHours = (TextView) view.findViewById(R.id.textViewTotalHours);

        nameList = new ArrayList<>();
        hourList = new ArrayList<>();

        //HOW TO; Add people
        //---------------

        nameList.add("Peepz");
        nameList.add("Bois");
        nameList.add("Dudeees");

        hourList.add(5);
        hourList.add(2);
        hourList.add(9);
        //---------------

        int allHoursTogether = 0;
        for(int hour: hourList){
            allHoursTogether += hour;
        }
        allHours.setText(allHoursTogether + "h");


        RecyclerView recyclerViewPpl = view.findViewById(R.id.recyclerViewPeople);
        recyclerViewPpl.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerViewPeople adapterPpl = new RecyclerViewPeople(getActivity(), nameList, hourList);
        recyclerViewPpl.setAdapter(adapterPpl);
        recyclerViewPpl.setNestedScrollingEnabled(false);

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
        //----------------

        GraphObject graph3 = new GraphObject();
        graph3.setTitle("Test Graph 3");
        graph3.setBudget(5);
        graph3.setTime(8);
        graph3.setCoordinates(new int[][]{{5,5},{1,3}});


        addGraph(graph3);
        //----------------

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerViewGraph adapter = new RecyclerViewGraph(getActivity(), graphs);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);

        return view;
    }


    private void addGraph(GraphObject graphObject){
        graphs.add(graphObject);
    }

}
