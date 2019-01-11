package com.brocode.miniproject;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class jsonReader {


    public static HashMap<String, Integer> totalPeople;

    public static int totalHours = 0;

    public static int budget_maxBudget;


    public static List<int[]> budget_PV_coordinats;
    public static List<int[]> budget_AC_coordinats;
    public static List<int[]> budget_EV_coordinats;

    public static List<String> schedule_list;
    public static List<String[]> schedule_dates;

    public static List<String> tasks;
    public static List<String[]> tasks_contributors;


    private static void parseJSON(String data) {
        Log.d("JSON -> ", data);
        try {
            JSONObject jsonReader = new JSONObject(data);


            JSONObject budget = jsonReader.getJSONObject("budget");


            tasks = new ArrayList<>();
            totalPeople = new HashMap<String, Integer>();

            for (int i = 0; i < jsonReader.getJSONObject("tasks_contributors").names().length(); i++) {
                String task = jsonReader.getJSONObject("tasks_contributors").names().get(i).toString();

                tasks.add(task);

                for (int o = 0; o < jsonReader.getJSONObject("tasks_contributors").getJSONObject(task).names().length(); o++) {
                    String name = jsonReader.getJSONObject("tasks_contributors").getJSONObject(task).names().get(o).toString();

                    Log.d("JSON DEBUGGER", name);

                    for (int p = 0; p < jsonReader.getJSONObject("tasks_contributors").getJSONObject(task).getJSONArray(name).length(); p++) {
                        int hour = jsonReader.getJSONObject("tasks_contributors").getJSONObject(task).getJSONArray(name).getInt(p);

                        totalHours += hour;

                        if (totalPeople.containsKey(name)) {
                            totalPeople.put(name, totalPeople.get(name) + hour);
                        } else {
                            totalPeople.put(name, hour);
                        }
                    }
                }
            }



            budget_maxBudget = budget.getInt("budget_amount");


            JSONArray arrayCoordinates1 = budget.getJSONArray("budget_PV_coordinates");
            budget_PV_coordinats = new ArrayList<>();
            for (int i = 0; i < arrayCoordinates1.length(); i++) {
                int[] coords = toIntArray(arrayCoordinates1.getJSONArray(i));
                budget_PV_coordinats.add(new int[]{coords[0], coords[1]});
            }

            JSONArray arrayCoordinates2 = budget.getJSONArray("budget_AC_coordinates");
            budget_AC_coordinats = new ArrayList<>();
            for (int i = 0; i < arrayCoordinates2.length(); i++) {
                int[] coords = toIntArray(arrayCoordinates2.getJSONArray(i));
                budget_AC_coordinats.add(new int[]{coords[0], coords[1]});
            }

            JSONArray arrayCoordinates3 = budget.getJSONArray("budget_EV_coordinates");
            budget_EV_coordinats = new ArrayList<>();
            for (int i = 0; i < arrayCoordinates3.length(); i++) {
                int[] coords = toIntArray(arrayCoordinates3.getJSONArray(i));
                budget_EV_coordinats.add(new int[]{coords[0], coords[1]});
            }


            JSONArray tasksList = jsonReader.getJSONArray("tasks");

            schedule_list = new ArrayList<>();
            for (int i = 0; i < tasksList.length(); i++) {
                schedule_list.add(tasksList.get(i).toString());
            }


            schedule_dates = new ArrayList<>();

            JSONArray scheduleDates = jsonReader.getJSONArray("array_task_dates");
            for (int i = 0; i < scheduleDates.length(); i++) {
                schedule_dates.add(toStringArray(scheduleDates.getJSONArray(i)));
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public static boolean readFile(Context context) {
        File sdcard = Environment.getExternalStorageDirectory();
        File sdcardDirectory = new File(sdcard, context.getString(R.string.app_name));

        if (!sdcardDirectory.exists()) {
            sdcardDirectory.mkdir();
        }

        File file = new File(sdcardDirectory, "data.json");

        if (file.exists()) {

            StringBuilder text = new StringBuilder();

            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;

                while ((line = br.readLine()) != null) {
                    text.append(line);
                    text.append('\n');
                }
                br.close();
                parseJSON(text.toString());
                return true;
            } catch (IOException e) {
                //You'll need to add proper error handling here
            }


        } else {
            Log.e("ERROR", "JSON file doesn't exist");
        }
        return false;
    }

    public static String[] toStringArray(JSONArray array) {
        if (array == null)
            return null;

        String[] arr = new String[array.length()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = array.optString(i);
        }
        return arr;
    }

    public static int[] toIntArray(JSONArray array) {
        if (array == null)
            return null;

        int[] arr = new int[array.length()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = array.optInt(i);
        }
        return arr;
    }


}
