package com.brocode.miniproject;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.OnSeekChangeListener;
import com.warkiz.widget.SeekParams;

import java.util.ArrayList;
import java.util.List;

public class BudgetFragment extends Fragment {

    List<GraphObject> graphs = new ArrayList<>();

    TextView allHours;



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_budget, container, false);

        allHours = view.findViewById(R.id.textViewTotalHours);

        graphs.clear();


        allHours.setText(jsonReader.totalHours + "h");

        //SETS budget

        setBudget(view, 1, jsonReader.budget_maxBudget, 25000);


        RecyclerView recyclerViewPpl = view.findViewById(R.id.recyclerViewPeople);
        recyclerViewPpl.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerViewPeople adapterPpl = new RecyclerViewPeople(getActivity(), jsonReader.totalPeople);
        recyclerViewPpl.setAdapter(adapterPpl);
        recyclerViewPpl.setNestedScrollingEnabled(false);

        //HOW TO; Make a graph and display it
        //---------------
        GraphObject graph1 = new GraphObject();
        graph1.setTitle("PV");
        graph1.setBudget(jsonReader.budget_maxBudget);
        graph1.setTime(jsonReader.budget_PV_coordinats.get(jsonReader.budget_PV_coordinats.size() - 1)[0]);
        graph1.setCoordinates(jsonReader.budget_PV_coordinats.toArray(new int[0][]));


        addGraph(graph1);
        //----------------

        GraphObject graph2 = new GraphObject();
        graph2.setTitle("AC");
        graph2.setBudget(jsonReader.budget_maxBudget);
        graph2.setTime(jsonReader.budget_AC_coordinats.get(jsonReader.budget_AC_coordinats.size() - 1)[0]);
        graph2.setCoordinates(jsonReader.budget_AC_coordinats.toArray(new int[0][]));


        addGraph(graph2);
        //----------------

        GraphObject graph3 = new GraphObject();
        graph3.setTitle("EV");
        graph3.setBudget(jsonReader.budget_maxBudget);
        graph3.setTime(jsonReader.budget_EV_coordinats.get(jsonReader.budget_EV_coordinats.size() - 1)[0]);
        graph3.setCoordinates(jsonReader.budget_EV_coordinats.toArray(new int[0][]));


        addGraph(graph3);
        //----------------

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerViewGraph adapter = new RecyclerViewGraph(getActivity(), graphs);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);

        return view;
    }

    private void setBudget(View view, int min, int max, final int progress) {
        final TextView minTextView = view.findViewById(R.id.progressBarStartText);
        TextView maxTextView = view.findViewById(R.id.progressBarEndText);
        final IndicatorSeekBar slider = view.findViewById(R.id.slider);
        //minTextView.setText(progress + " kr");


        minTextView.setText(min + " kr");
        maxTextView.setText(max + " kr");

        slider.setMin(min);
        slider.setMax(max);
        slider.setProgress(progress);
        //slider.setEnabled(false);
        slider.setOnSeekChangeListener(new OnSeekChangeListener() {
            @Override
            public void onSeeking(SeekParams seekParams) {
                slider.setProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(IndicatorSeekBar seekBar) {
                slider.setProgress(progress);
            }

            @Override
            public void onStopTrackingTouch(IndicatorSeekBar seekBar) {
                slider.setProgress(progress);
            }
        });


    }


    private void addGraph(GraphObject graphObject){
        graphs.add(graphObject);
    }

}
