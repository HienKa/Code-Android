package com.nguyen.bzc.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Hien on 12/13/2016.
 */

public class FileUtil {
    // Converting InputStream to String

    public static String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }

    /*public static void writeToFile(String str) {
        try {
            File sdCard = Environment.getExternalStorageDirectory();
            File dir = new File(sdCard.getAbsolutePath() + "/HienFiles");
            dir.mkdirs();
            File file = new File(dir, "hien_source_web.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fOut = new FileOutputStream(file);

            OutputStreamWriter osw = new OutputStreamWriter(fOut);

            osw.write(str);
            osw.flush();
            osw.close();
            //textBox.setText(" ");
        } catch (Exception e) {
            Log.e("WRFile", e.toString());
        }

    }*/
}
