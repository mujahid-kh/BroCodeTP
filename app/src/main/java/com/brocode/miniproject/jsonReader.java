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
import java.util.List;

public class jsonReader {

    public static String[] budget_people;
    public static String[] budget_hours;

    public static int budget_maxBudget;

    public static List<int[]> budget_PV_coordinats;

    public static List<String> schedule_list;

    public static List<String> tasks;
    public static List<String[]> tasks_contributors;


    private static void parseJSON(String data) {
        Log.d("JSON -> ", data);
        try {
            JSONObject jsonReader = new JSONObject(data);


            JSONObject budget = jsonReader.getJSONObject("budget");

            budget_people = toStringArray(budget.getJSONArray("array_persons"));
            budget_hours = toStringArray(budget.getJSONArray("array_hours"));

            budget_maxBudget = budget.getInt("budget_amount");


            JSONArray arrayCoordinates = budget.getJSONArray("budget_PV_coordinates");

            budget_PV_coordinats = new ArrayList<>();
            for (int i = 0; i < arrayCoordinates.length(); i++) {
                int[] coords = toIntArray(arrayCoordinates.getJSONArray(i));
                budget_PV_coordinats.add(new int[]{coords[0], coords[1]});
            }


            JSONArray tasksList = jsonReader.getJSONArray("tasks");

            schedule_list = new ArrayList<>();
            for (int i = 0; i < tasksList.length(); i++) {
                schedule_list.add(tasksList.get(i).toString());
            }

            tasks = new ArrayList<>();
            tasks_contributors = new ArrayList<>();

            JSONObject tasksContributors = jsonReader.getJSONObject("tasks_contributors");
            for (int i = 0; i < tasksContributors.length(); i++) {
                JSONArray contributors = tasksContributors.getJSONArray(tasksContributors.names().getString(i));

                Log.d("JSON", tasksContributors.names().getString(i));

                List<String> list = new ArrayList<>();
                for (int o = 0; o < contributors.length(); o++) {
                    list.add(contributors.get(o).toString());
                    Log.d("JSON - ", contributors.get(o).toString());
                }
                tasks.add(tasksContributors.names().getString(i));
                tasks_contributors.add(list.toArray(new String[0]));
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
