package android.example.com.imageexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.example.com.imageexample.GoogleCallBack.GoogleMapKits;
import android.example.com.imageexample.Modal.Estate.Estate;
import android.example.com.imageexample.Modal.Contracts;
import android.example.com.imageexample.Utils.ContentProviderUtils;
import android.example.com.imageexample.Utils.LocationUtils;
import android.example.com.imageexample.Utils.Utils;
import android.location.Address;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.*;
import android.support.v4.content.CursorLoader;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class CursorLoaderCallBack implements LoaderManager.LoaderCallbacks<Cursor> {
    public static final int ESTATE_LOADER_ID = 531;
    private static final String LOG_TAG = CursorLoaderCallBack.class.getSimpleName();

    private Context context;
    private ProgressBar progressBar;
    private String selection, orderBy;
    private String[] projection, selectors;
    private LatLngBounds latLngBounds;
    private LatLng northest, southwest;
    private double minLat, minLng, maxLat, maxLng;



    public CursorLoaderCallBack(Context context, ProgressBar progressBar, LatLngBounds latLngBounds) {
        this.context = context;
        this.progressBar = progressBar;
        this.latLngBounds = latLngBounds;
    }



    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if(progressBar != null){
            progressBar.setVisibility(View.VISIBLE);
        }

        switch (id){
            case ESTATE_LOADER_ID:
                Log.d(LOG_TAG, "Loader is loading...");
                return new CursorLoader(
                        context,
                        Contracts.Estate.CONTENT_URI,
                        this.projection,
                        this.selection,
                        this.selectors,
                        this.orderBy
                );
            default:
                return null;
        }
    }

    @Override
    public void onLoadFinished(final Loader<Cursor> loader, Cursor cursor) {
        if(cursor == null){
            return;
        }


        Log.d(LOG_TAG, "Cursor count: " + cursor.getCount());

        switch (loader.getId()){
            case ESTATE_LOADER_ID:
                handleEstateLoaderRequest(cursor);
                break;
//                if(cursor.getCount() < GoogleMapKits.MAX_MARKERS){
//                    if(latLngBounds == null){
//                        return;
//                    }
//                    final int neededEstate = GoogleMapKits.MAX_MARKERS - cursor.getCount();
//                    AsyncTask asyncTask = new AsyncTask() {
//                        @Override
//                        protected Object doInBackground(Object[] params) {
//
//                            for(int i = 0; i < neededEstate; i++) {
//                                Address address = null;
//                                while (address == null) {
//                                    double randomLat = Utils.getRandom(minLat, maxLat);
//                                    double randomLng = Utils.getRandom(minLng, maxLng);
//                                    LatLng randomLatLng = new LatLng(randomLat, randomLng);
//                                    address = LocationUtils.getAddress(context, randomLatLng);
//                                }
//                                double actualLat = address.getLatitude();
//                                double actualLng = address.getLongitude();
//                                String placeID = LocationUtils.getPlaceID(context, actualLat, actualLng);
//                                Estate estate = new Estate.Builder().setAddress(address.getAddressLine(0))
//                                        .setPostalCode(address.getPostalCode())
//                                        .setPrice(Utils.getRandom(Estate.MIN_PRICE, Estate.MAX_PRICE))
//                                        .setPlaceId(placeID)
//                                        .setLat(actualLat)
//                                        .setLng(actualLng)
//                                        .build();
//                                Log.d(LOG_TAG, estate.toString());
//                                context.getContentResolver().insert(Contracts.Estate.CONTENT_URI, ContentProviderUtils.createContentValue(estate));
//
//                                Cursor imageCursor = context.getContentResolver().query(
//                                        Contracts.Image.CONTENT_URI,
//                                        null,
//                                        null,
//                                        null,
//                                        null
//                                );
//
//                                if(imageCursor == null){
//                                    Log.d(LOG_TAG, "imageCursor == null");
//                                }else{
//                                    int count = imageCursor.getCount();
//                                    Log.d(LOG_TAG, "imageCursor: " + count);
//
//                                    int random1 = -1;
//                                    int random2 = -1;
//
//                                    while (random1 == random2){
//                                        random1 = new Random().nextInt(count - 1);
//                                        random2 = new Random().nextInt(count - 1);
//                                    }
//                                    String link1 = "";
//                                    String link2 = "";
//                                    if(imageCursor.moveToPosition(random1)){
//                                        link1 = imageCursor.getString(Contracts.Image.INDEX_LINK);
//                                    }
//                                    if(imageCursor.moveToPosition(random2)){
//                                        link2 = imageCursor.getString(Contracts.Image.INDEX_LINK);
//                                    }
//                                    imageCursor.close();
//
//                                    ContentValues values1 = new ContentValues();
//                                    values1.put(Contracts.EstateAndImage.ESTATE_PRIMARY, placeID);
//                                    values1.put(Contracts.EstateAndImage.IMAGE_PRIMARY, link1);
//                                    context.getContentResolver().insert(
//                                            Contracts.EstateAndImage.CONTENT_URI,
//                                            values1
//                                    );

//                                    ContentValues values2 = new ContentValues();
//                                    values2.put(Contracts.EstateAndImage.ESTATE_PRIMARY, placeID);
//                                    values2.put(Contracts.EstateAndImage.IMAGE_PRIMARY, link2);
//                                    context.getContentResolver().insert(
//                                            Contracts.EstateAndImage.CONTENT_URI,
//                                            values2
//                                    );
//
//
//
//                                }
//
//                            }
//                            return null;
//                        }
//
//                        @Override
//                        protected void onPostExecute(Object o) {
//                            super.onPostExecute(o);
//                            onLoaderReset(loader);
//                        }
//                    };

//                    asyncTask.execute();


//                }else{
//                    if(markers == null || googleMap == null){
//                        return;
//                    }
//                    List<Estate> estates = new ArrayList<>();
//                    while (cursor.moveToNext()){
//                        Estate estate = Estate.newInstance(cursor);
//                        estates.add(estate);
//                    }
//                }
            default:
                break;
        }

        cursor.close();
       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               if(progressBar != null){
                   progressBar.setVisibility(View.GONE);
               }
           }
       }, 300);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        loader.forceLoad();
    }

    public void setLatLngBounds(LatLngBounds latLngBounds) {
        this.latLngBounds = latLngBounds;
        northest = latLngBounds.northeast;
        southwest = latLngBounds.southwest;
        minLat = Math.min(northest.latitude, southwest.latitude);
        maxLat = Math.max(northest.latitude, southwest.latitude);
        minLng = Math.min(northest.longitude, southwest.longitude);
        maxLng = Math.max(northest.longitude, southwest.longitude);

        selection = Contracts.Estate.LATITUDE + ">=? AND " + Contracts.Estate.LATITUDE + " <=? AND "
                + Contracts.Estate.LONGTITUDE + ">=? AND " + Contracts.Estate.LONGTITUDE + " <=?";
        selectors = new String[]{
                String.valueOf(minLat),
                String.valueOf(maxLat),
                String.valueOf(minLng),
                String.valueOf(maxLng),
        };

    }

    private void handleEstateLoaderRequest(Cursor estateCursor) {
       if( estateCursor != null ) {
           if(estateCursor.getCount() < GoogleMapKits.MAX_MARKERS) {

           } else {

           }
       }
    }

    private void gernerate2RadnomsImageForEstate(Estate estate) {
        Cursor imageCursor = context.getContentResolver().query(
                                        Contracts.Image.CONTENT_URI,
                                        null,
                                        null,
                                        null,
                                        null
        );

        if(imageCursor == null){
            Log.d(LOG_TAG, "imageCursor == null");
        }else{
            int count = imageCursor.getCount();
            Log.d(LOG_TAG, "imageCursor: " + count);
            int random1 = -1;
            int random2 = -1;
            while (random1 == random2){
                random1 = new Random().nextInt(count - 1);
                random2 = new Random().nextInt(count - 1);
            }
            String link1 = "";
            String link2 = "";
            if(imageCursor.moveToPosition(random1)){
                link1 = imageCursor.getString(Contracts.Image.INDEX_LINK);
            }
            if(imageCursor.moveToPosition(random2)){
                link2 = imageCursor.getString(Contracts.Image.INDEX_LINK);
            }



            ContentValues values1 = new ContentValues();
            values1.put(Contracts.EstateAndImage.ESTATE_PRIMARY, estate.getPlaceID());
            values1.put(Contracts.EstateAndImage.IMAGE_PRIMARY, link1);
            context.getContentResolver().insert(
                    Contracts.EstateAndImage.CONTENT_URI,
                    values1
            );

            ContentValues values2 = new ContentValues();
            values2.put(Contracts.EstateAndImage.ESTATE_PRIMARY, estate.getPlaceID());
            values2.put(Contracts.EstateAndImage.IMAGE_PRIMARY, link2);
            context.getContentResolver().insert(
                    Contracts.EstateAndImage.CONTENT_URI,
                    values2
            );

            imageCursor.close();
        }
    }

}
