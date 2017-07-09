package android.example.com.imageexample.ui;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.example.com.imageexample.BroadcastReceiver.NetworkBroadcastReceiver;
import android.example.com.imageexample.ObserverPattern.Network.NetworkStateObserver;
import android.example.com.imageexample.ObserverPattern.Network.NetworkStatePublisher;
import android.example.com.imageexample.Utils.ContextUtils;
import android.example.com.imageexample.Utils.NetworkHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.example.com.imageexample.R;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;

public class NetworkInfoActivty extends AppCompatActivity implements NetworkStateObserver{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_info_activty);
        NetworkStatePublisher.getInstance().registerObserver(this);
        ContextUtils.setupToolbar(this, (Toolbar) findViewById(R.id.tool_bar));

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

    @Override
    public void notify(boolean isOnline) {
        if(isOnline){
            startActivity(
                    new Intent(this, MainActivity.class)
            );
        }
    }
}
