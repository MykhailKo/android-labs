package com.example.lab1app;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class ActionLogFileUtil {
    private static String filename = "user-action.log";

    public static void writeActionLogFileFile(String content, Context ctx) {
        Date now = new Date();
        try (FileOutputStream fos = ctx.openFileOutput(filename, Context.MODE_APPEND)) {
            fos.write((now.toString() + ":" + content).getBytes());
        } catch (IOException e) {
            Toast.makeText(ctx, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public static String readActionLogFile(Context ctx) {
        StringBuilder sb = new StringBuilder();
        try (FileInputStream fis = ctx.openFileInput(filename)) {
            InputStreamReader inputStreamReader =
                    new InputStreamReader(fis, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                sb.insert(0, "\n").insert(0,line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            Toast.makeText(ctx, e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            String contents = sb.toString();
            return contents;
        }
    }
}
