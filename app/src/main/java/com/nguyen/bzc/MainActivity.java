package com.nguyen.bzc;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.nguyen.bzc.adapters.SalesListAdapter;
import com.nguyen.bzc.model.GetterData;
import com.nguyen.bzc.model.Sale;
import com.nguyen.bzc.model.SalesManager;
import com.nguyen.bzc.utility.AlertUtils;
import com.nguyen.bzc.utility.ConnectionDetector;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView saleListView;
    private SalesListAdapter salesListAdapter = null;

    private ISaleSelectedListener mSalesListener = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set application context
        ApplicationContext.getInstance().setApplicationContext(getApplicationContext());
        ApplicationContext.getInstance().setMainActivity(MainActivity.this);

        // Check if there is network connection or not
        if (!ConnectionDetector.getInstance().isConnectingToInternet()) {
            AlertUtils.showAlertDialog(MainActivity.this, getResources().getString(R.string.no_connection),
                    getResources().getString(R.string.message_connection), false);

        } else {
            GetterData.getData();
            ArrayList<Sale> arrayList = new ArrayList<>(SalesManager.
                    getInstance().getAllSales().values());
            Object[] array = arrayList.toArray();
            String[] strings = new String[array.length];
            for (int i = 0; i < strings.length; i++) {
                strings[i] = ((Sale) array[i]).getName();
            }

            salesListAdapter = new SalesListAdapter(this, android.R.layout.simple_list_item_1, strings, 1);
            saleListView = (ListView) findViewById(R.id.sales_list_view);
            saleListView.setAdapter(salesListAdapter);

            mSalesListener = new ISaleSelectedListener() {
                @Override
                public void onSaleClick(String nameOfSelectedSale) {
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    Bundle bundle = new Bundle();

                    // Add the name of selected sale to intent
                    bundle.putString("name of sale", nameOfSelectedSale);
                    intent.putExtras(bundle);

                    // Start Detail activity
                    startActivity(intent);
                }
            };

            saleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if ((mSalesListener != null) && (salesListAdapter != null)) {
                        mSalesListener.onSaleClick(salesListAdapter.getItem(position));

                    }
                }
            });
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //When change display mode, do nothing to keep app continue run
    }

    public interface ISaleSelectedListener {
        void onSaleClick(String selectedLanguage);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        GetterData.shutdown();
    }
}
