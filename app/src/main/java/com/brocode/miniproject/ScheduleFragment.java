package com.brocode.miniproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.archit.calendardaterangepicker.customviews.DateRangeCalendarView;

import org.angmarch.views.NiceSpinner;

import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;


public class ScheduleFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);

        fixSchedule(view);


        return view;
    }

    void fixSchedule(View view) {
        NiceSpinner niceSpinner = view.findViewById(R.id.nice_spinner);
        List<String> dataset = new LinkedList<>(Arrays.asList("One", "Two", "Three", "Four", "Five"));
        niceSpinner.attachDataSource(dataset);

        DateRangeCalendarView calendarView = view.findViewById(R.id.calendar);

        Calendar now = Calendar.getInstance();
        now.add(Calendar.MONTH, 0);
        Calendar later = Calendar.getInstance();
        later.add(Calendar.MONTH, 1);

        calendarView.setVisibleMonthRange(now, later);

        Calendar startSelectionDate = Calendar.getInstance();
        startSelectionDate.add(Calendar.DATE, -1);
        Calendar endSelectionDate = Calendar.getInstance();
        endSelectionDate.add(Calendar.DATE, 4);

        calendarView.setSelectedDateRange(startSelectionDate, endSelectionDate);

        Calendar current = Calendar.getInstance();
        calendarView.setCurrentMonth(current);

        calendarView.setEditable(false);
        //calendarView.resetAllSelectedViews();
    }
}