package com.freelance.yahia.test1;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {


    private int mNotificationsCount = 0;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        toolbar.setNavigationContentDescription("Back");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        new FetchCountTask().execute();





        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);



        try {
            // https://stylaq-app.herokuapp.com/dbd/item_status?status=approved

            URL url = new URL("http://services.groupkt.com/country/get/all");


            ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm.getActiveNetworkInfo() != null)
                new GetJsonA().execute(url);
        }catch (Exception e){Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();}


        String[] itemsNameR = new String[]{"itemaa", "itemaa", "itemaa"};
        String[] fabricsR = new String[]{"fabricaa", "itemaa", "itemaa"};
        String[] colorsAvailR = new String[]{"colora", "itemaa", "itemaa"};
        String[] pricesR = new String[]{"pricea4", "itemaa", "itemaa"};
        Boolean[] itemsStatusR = new Boolean[]{true, false, true};




        mAdapter = new MyAdapter(itemsNameR, fabricsR, colorsAvailR, pricesR ,itemsStatusR);

            mRecyclerView.setAdapter(mAdapter);

    }



    private class GetJsonA extends AsyncTask<URL, Integer, String>{

        @Override
        protected String doInBackground(URL... urls) {
            try {
                String authorization = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1YjUzNDFlNWNkM2NhNzAwMTRiMDAwYTYiLCJhY2Nlc3MiOiJCcmFuRHNTaGFsbDgwMG0iLCJmIjoiZmFzaCIsImlhdCI6MTUzNDE0NzU0OX0.caUWY9MF5QMksgP1Pckv98V9vvoGC5-sbc7pvKCSopo";
                HttpURLConnection urlConnection = (HttpURLConnection) urls[0].openConnection();

                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("X-Auth-Token", authorization);



                    urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);
                urlConnection.setReadTimeout(15000);
                urlConnection.setConnectTimeout(15000);



                   InputStream in = urlConnection.getInputStream();


                    BufferedReader bReader = new BufferedReader(new InputStreamReader(in, "utf-8"), 8);
                    StringBuilder sBuilder = new StringBuilder();

                    String line = null;
                    while ((line = bReader.readLine()) != null) {
                        sBuilder.append(line);
                    }

                    in.close();
                    String fileString = sBuilder.toString();
                    return fileString;

            }catch (Exception e){
                Toast.makeText(getApplicationContext(), e.toString() + "third", Toast.LENGTH_LONG).show();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String fileString) {
            super.onPostExecute(fileString);
            try {
         //       JSONObject jsonAll = new JSONObject(fileString);

         //       Toast.makeText(getApplicationContext(), fileString /* jsonAll.toString()*/, Toast.LENGTH_LONG).show();
            }catch (Exception e){Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();}
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_settings);
        LayerDrawable icon = (LayerDrawable) item.getIcon();
        Utils.setBadgeCount(this, icon, mNotificationsCount);

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
            updateNotificationsBadge(0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void updateNotificationsBadge(int count) {
        mNotificationsCount = count;

        // force the ActionBar to relayout its MenuItems.
        // onCreateOptionsMenu(Menu) will be called again.
        invalidateOptionsMenu();
    }

    class FetchCountTask extends AsyncTask<Void, Void, Integer> {

        @Override
        protected Integer doInBackground(Void... params) {
            // example count. This is where you'd
            // query your data store for the actual count.
            return 5;
        }

        @Override
        public void onPostExecute(Integer count) {
            updateNotificationsBadge(count);
        }
    }


}
