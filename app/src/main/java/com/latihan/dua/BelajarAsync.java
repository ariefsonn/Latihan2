package com.latihan.dua;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.toolbox.JsonObjectRequest;
import com.latihan.dua.networkingku.CheckConectToWww;
import com.latihan.dua.networkingku.CheckNetwork;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BelajarAsync extends AppCompatActivity {

    String url = "http://api.banghasan.com/quran/format/json/surat";
    CheckNetwork status;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        status = new CheckNetwork(this);

        new tampilkansemua().execute(url);

    }

    class tampilkansemua extends AsyncTask<String, String, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {

            try {

                if (status.checkinternet()){
                    JSONObject jsonObject = new JSONObject(CheckConectToWww.connectWww(strings[0]));
                    JSONArray array = jsonObject.getJSONArray("hasil");

                    for (int i = 0; i < array.length(); i++){

                       JSONObject object = array.getJSONObject(i);
                       String nama = object.getString("nama");
                       String arti = object.getString("arti");
                       String asma = object.getString("asma");
                       String nomor = object.getString("nomor");

                       Log.d("hasiltampil", "nomor:" + " " + nomor + " " + "nama:" + " " + nama + " " + "arti:" + " " + arti + " " + "asma:" + " " + asma + " ");

                    }

                    return "1";
                }else {
                    Toast.makeText(getApplicationContext(), "PERIKSA JARINGAN ANDA", Toast.LENGTH_LONG).show();
                }
                return "";
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
}
