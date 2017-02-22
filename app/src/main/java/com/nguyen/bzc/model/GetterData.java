package com.nguyen.bzc.model;

import com.nguyen.bzc.utility.FileUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

// Class to get data from website
public class GetterData {
    private static Thread threadToGetData;
    public static void getData() {

        final String currentUrl = "https://api.gilt.com/v1/sales/active.json?apikey=" +
                "e8a850cdcf1aa4fc2b06c5c3118b211142dcba68b80450d9646de8e177184c7a";

        threadToGetData = new Thread(new Runnable() {
            @Override
            public void run() {
                String result = "";
                HttpURLConnection urlConnection = null;

                try {

                    // Connect to website
                    URL url = new URL(currentUrl);
                    urlConnection = (HttpURLConnection) url.openConnection();

                    int responseCode = urlConnection.getResponseCode();
                    // Get result in json format
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        result = FileUtil.readStream(urlConnection.getInputStream());
                    }

                    JSONObject obj = new JSONObject(result);

                    // Parse the result to extract its content
                    JSONArray arr = obj.getJSONArray("sales");
                    //String temp = "";
                    for (int i = 0; i < arr.length(); i++) {
                        String name = arr.getJSONObject(i).getString("name");
                        String detail = arr.getJSONObject(i).getString("sale");
                        String key = arr.getJSONObject(i).getString("sale_key");
                        String store = arr.getJSONObject(i).getString("store");
                        String description = arr.getJSONObject(i).getString("description");
                        String saleURL = arr.getJSONObject(i).getString("sale_url");
                        String begins = arr.getJSONObject(i).getString("begins");
                        String ends = arr.getJSONObject(i).getString("ends");

                        JSONObject images = arr.getJSONObject(i).getJSONObject("image_urls");
                        String temp1 = images.toString();
                        int index1 = temp1.indexOf("[");
                        int index2 = temp1.indexOf("]");
                        temp1 = temp1.substring(index1 + 1, index2);

                        JSONObject imageObj = new JSONObject(temp1);
                        String imageURL = imageObj.getString("url");

                        // Create new sale model
                        Sale newSale = new Sale(name, detail, key, store, description, saleURL, begins, ends, imageURL);

                        // Add a sale to stock it
                        SalesManager.getInstance().addSale(newSale);

                        /*temp += name + "\n" + detail +
                                "\n" + key +
                                "\n" + detail +
                                "\n" + key +
                                "\n" + store +
                                "\n" + description +
                                "\n" + saleURL +
                                "\n" + begins +

                                "\n" + ends +

                                "\n" + imageURL +
                                " \n------------------------------\n";*/
                    }


                } catch (IOException e) {
                    e.printStackTrace();

                } catch (JSONException e) {
                    e.printStackTrace();

                } finally {
                    if (threadToGetData != null) {
                        synchronized (threadToGetData) {
                            threadToGetData.notifyAll();
                        }
                    }
                }
            }
        });
        threadToGetData.start();

        if (threadToGetData != null) {
            synchronized (threadToGetData) {
                try {
                    threadToGetData.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            threadToGetData = null;
        }
    }

    public static void shutdown() {
        if (threadToGetData != null) {
            synchronized (threadToGetData) {
                threadToGetData.notifyAll();
                threadToGetData = null;
            }
        }
    }
}
