package android.example.com.imageexample;

import android.content.Context;
import android.database.Cursor;
import android.example.com.imageexample.Modal.Contracts;
import android.example.com.imageexample.ObserverPattern.OnSreenEstateCursor.OnScreenEstateCursorPublisher;
import android.example.com.imageexample.ui.MainActivity;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.ListPreference;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;



public class AppLoaderCallBack implements LoaderManager.LoaderCallbacks<Object> {
    private static final String LOG_TAG = AppLoaderCallBack.class.getSimpleName();

    private Context context;

    public AppLoaderCallBack(Context context) {
        this.context = context;

    }

    @Override
    public Loader<Object> onCreateLoader(int id, Bundle args) {
        Log.d(LOG_TAG, args == null?"bundle == null": args.toString());
        return new GenerateEstateLoader(context, args);
    }

    @Override
    public void onLoaderReset(Loader<Object> loader) {
        loader.reset();
    }

    @Override
    public void onLoadFinished(Loader<Object> loader, Object data) {
        if(data != null) {
            if(data instanceof Cursor){
                Log.d(LOG_TAG, "Loader's returned object != Cursor");
                Cursor cursor = (Cursor) data;
                OnScreenEstateCursorPublisher.getInstance().setOnScreeenEstateCursor(cursor);
                OnScreenEstateCursorPublisher.getInstance().notifyData();
            } else {
                Log.d(LOG_TAG, "Loader's returned object == Cursor");
            }
        } else {
            Log.d(LOG_TAG, "Loader's returned object == null");
        }
    }


}
