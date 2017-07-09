package android.example.com.imageexample.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.example.com.imageexample.ObserverPattern.Network.NetworkStatePublisher;
import android.example.com.imageexample.ui.MainActivity;
import android.example.com.imageexample.ui.NetworkInfoActivty;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v7.preference.PreferenceManager;
import android.util.Log;


public class NetworkBroadcastReceiver extends BroadcastReceiver {
    public static final String IS_ONLINE_KEY = "IS_ONLINE_KEY";
    @Override
    public void onReceive(Context context, Intent intent) {
//        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
//        if(networkInfo != null && networkInfo.isConnected()){
//            context.startActivity(new Intent(context, MainActivity.class));
//        }else{
//            context.startActivity(new Intent(context, NetworkInfo.class));
//
//        }
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = activeNetInfo != null && activeNetInfo.isConnectedOrConnecting();
        SharedPreferences sharedPreferencesCompat = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferencesCompat.edit().putBoolean(IS_ONLINE_KEY, isConnected);
        if (isConnected) {
            Log.i("ethan", "connected" + isConnected);
            NetworkStatePublisher.getInstance().setOnline(true);
            NetworkStatePublisher.getInstance().notifyData();
        }else{
            NetworkStatePublisher.getInstance().setOnline(false);
            NetworkStatePublisher.getInstance().notifyData();
            Log.i("ethan", "connected" + isConnected);
        }
    }
}
