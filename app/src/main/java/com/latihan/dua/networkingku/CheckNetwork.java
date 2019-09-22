package com.latihan.dua.networkingku;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

public class CheckNetwork {

    Context context;

    public CheckNetwork(Context context) {
        this.context = context;
    }

    public boolean checkinternet(){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();

        return info != null && info.isConnected();
    }
}
