package com.brocode.miniproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.archit.calendardaterangepicker.customviews.DateRangeCalendarView;

import org.angmarch.views.NiceSpinner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class ScheduleFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);

        fixSchedule(view);


        return view;
    }

    void fixSchedule(View view) {
        final NiceSpinner niceSpinner = view.findViewById(R.id.nice_spinner);
        niceSpinner.attachDataSource(jsonReader.schedule_list);
        updateSpinner(niceSpinner, view);
        niceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateSpinner(niceSpinner, view);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //calendarView.resetAllSelectedViews();
    }

    void updateSpinner(NiceSpinner niceSpinner, View view) {
        String[] selectedDate = jsonReader.schedule_dates.get(niceSpinner.getSelectedIndex());
        Log.d("APP", selectedDate[0]);
        Log.d("APP", selectedDate[1]);

        String[] startDates = selectedDate[0].split("-");
        String[] endDates = selectedDate[1].split("-");


        DateRangeCalendarView calendarView = view.findViewById(R.id.calendar);

        Calendar now = Calendar.getInstance();
        now.set(Calendar.YEAR, Integer.parseInt(startDates[0]));
        now.set(Calendar.MONTH, Integer.parseInt(startDates[1]) - 1);
        Calendar later = Calendar.getInstance();
        later.set(Calendar.YEAR, Integer.parseInt(endDates[0]));
        later.set(Calendar.MONTH, Integer.parseInt(endDates[1]));

        calendarView.setVisibleMonthRange(now, later);

        Calendar startSelectionDate = Calendar.getInstance();
        startSelectionDate.set(Integer.parseInt(startDates[0]), Integer.parseInt(startDates[1]), Integer.parseInt(startDates[2]));
        Calendar endSelectionDate = Calendar.getInstance();
        endSelectionDate.set(Integer.parseInt(endDates[0]), Integer.parseInt(endDates[1]), Integer.parseInt(endDates[2]));

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        Calendar from = Calendar.getInstance();
        Calendar to = Calendar.getInstance();
        try {
            from.setTime(format.parse(selectedDate[0]));
            to.setTime(format.parse(selectedDate[1]));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        calendarView.setSelectedDateRange(from, to);


        Calendar current = Calendar.getInstance();
        current.set(Integer.parseInt(startDates[0]), Integer.parseInt(startDates[1]), Integer.parseInt(startDates[2]));
        calendarView.setCurrentMonth(current);

        calendarView.setEditable(false);
    }
}