package com.brocode.miniproject;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class jsonReader {


    private static void parseJSON(String data) {
        Log.d("JSON -> ", data);
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


}
