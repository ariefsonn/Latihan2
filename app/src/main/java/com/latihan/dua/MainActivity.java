package com.latihan.dua;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.latihan.dua.adapter.AdapterPertama;
import com.latihan.dua.modal.Modakr;
import com.latihan.dua.networkingku.CheckNetwork;
import com.latihan.dua.okrequest.OkRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recycler_view;
    GridLayoutManager glm;
    AdapterPertama adapterPertama;
    ArrayList<Modakr>modakr;

    RequestQueue requestQueue;
    String url = "https://al-quran-8d642.firebaseio.com/data.json?print=pretty";

    CheckNetwork checkNetwork;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler_view = findViewById(R.id.recycler_view);
        glm = new GridLayoutManager(this, 1);
        recycler_view.setLayoutManager(glm);

        modakr = new ArrayList<>();

        checkNetwork = new CheckNetwork(this);
        if (checkNetwork.checkinternet()){
            Toast.makeText(getApplicationContext(), "BERHASIL CONNECT", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(getApplicationContext(), "GAGAL CONNECT", Toast.LENGTH_LONG).show();
        }

        modakr.add(new Modakr("ROG ZEPRUS S GX701", getDrawable(R.drawable.rog)));

        adapterPertama = new AdapterPertama(this, modakr);
        recycler_view.setAdapter(adapterPertama);

       // new tampilkaLogApi().execute("https://al-quran-8d642.firebaseio.com/data.json?print=pretty");

        requestQueue = Volley.newRequestQueue(this);
        //requestJsonObj();

        requestJsonArray();
    }

    class tampilkaLogApi extends AsyncTask<String, String, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                JSONArray jsonObject = new JSONArray(OkRequest.getUrl(strings[0]));
                Log.d("myjson", jsonObject + "");

                return "1";
            } catch (JSONException e) {
                e.printStackTrace();
                return "0";
            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

    private void requestJsonObj(){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Myjsonobjectvolley", response + "");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsonObjectRequest);
    }

    private void requestJsonArray(){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("Myjsonarray", response + "");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("SALAH", error + "");
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    }


