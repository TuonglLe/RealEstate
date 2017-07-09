package android.example.com.imageexample.Modal;

import android.content.Context;
import android.example.com.imageexample.Modal.Estate.Estate;
import android.example.com.imageexample.Modal.EstateJson.JsonEstate;
import android.example.com.imageexample.Modal.EstateJson.JsonEstateHolder;
import android.example.com.imageexample.Utils.JsonUtil;
import android.example.com.imageexample.Utils.Utils;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;

public class EstateGenerator {
    private static final String LOG_TAG = EstateGenerator.class.getSimpleName();
    private static final String BASE_URL = "https://maps.googleapis.com/maps/api/geocode/json?key=AIzaSyBfGyRJ8EZWuQibLanyZEM4OKSMabV-Feo&latlng=";


    public static Estate generateEstate(LatLng latLng) {

        String reponseString = JsonUtil.getJasonResponse(BASE_URL + latLng.latitude + "," +latLng.longitude);

        ObjectMapper objectMapper = new ObjectMapper();
        Log.d("Hello", objectMapper.toString());
        Estate estate = null;
        try {
            JsonEstateHolder jsonEstateHolder = objectMapper.readValue( reponseString, JsonEstateHolder.class);
            estate = jsonEstateHolder.getFirstEstate();
        } catch (IOException e) {
            Log.d("Hello", objectMapper.toString() + "null");
            e.printStackTrace();
        }

        return estate;
    }

}
