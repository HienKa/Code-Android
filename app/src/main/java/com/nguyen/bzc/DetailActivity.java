package com.nguyen.bzc;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.nguyen.bzc.model.Sale;
import com.nguyen.bzc.model.SalesManager;
import com.nguyen.bzc.utility.AlertUtils;
import com.nguyen.bzc.utility.ConnectionDetector;

import org.joda.time.DateTime;

import java.io.InputStream;

public class DetailActivity extends AppCompatActivity {
    public static final long A_DAY_IN_MILISECONDS = 24 * 60 * 60 * 1000;
    public static final long AN_HOUR_IN_MILISECONDS = 60 * 60 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView imageView = (ImageView) findViewById(R.id.image);
        TextView tvName = (TextView) findViewById(R.id.name);
        TextView tvStore = (TextView) findViewById(R.id.store);
        TextView tvDescription = (TextView) findViewById(R.id.description);
        TextView tvDuration = (TextView) findViewById(R.id.duration);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        // Get name of selected sale from main activity
        String nameOfSale = bundle.getString("name of sale");

        Sale sale = SalesManager.getInstance().getSale(nameOfSale);
        String name = sale.getName();
        String description = sale.getDescription();
        String store = sale.getStore();
        String begins = sale.getBegins();
        String ends = sale.getEnds();
        String imageUrl = sale.getImageURL();

        // Caculate the duration
        long millisSinceEpochBegin = new DateTime(begins).getMillis();
        long millisSinceEpochEnd = new DateTime(ends).getMillis();
        long duration = millisSinceEpochEnd - millisSinceEpochBegin;
        long days = duration / (A_DAY_IN_MILISECONDS);
        long hours = (duration - days * A_DAY_IN_MILISECONDS) / (AN_HOUR_IN_MILISECONDS);

        // Display the details
        tvName.setText(name);
        tvDescription.setText(description);
        tvStore.setText("Store: " + store);
        tvDuration.setText("Sale ends " + days + " days, " + hours + " hours");

        // Check if there is network connection or not
        if (!ConnectionDetector.getInstance().isConnectingToInternet()) {
            AlertUtils.showAlertDialog(DetailActivity.this, getResources().getString(R.string.no_connection),
                    getResources().getString(R.string.message_connection), false);

        } else {
            // show The Image in a ImageView
            new DownloadImageTask(imageView).execute(imageUrl);
        }
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //do nothing to keep app continue run
    }

    // Download in image from url
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

}
