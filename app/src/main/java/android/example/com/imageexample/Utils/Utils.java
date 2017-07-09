package android.example.com.imageexample.Utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.security.PublicKey;
import java.util.List;
import java.util.Random;



public class Utils {

    public static double getRandom(double num1, double num2){
        double min = Math.min(num1, num2);
        double max = Math.max(num1, num2);
        return min + (max - min) * new Random().nextDouble();
    }



    public static String getAddress(Context context, LatLng latLng){
        Geocoder geocoder = new Geocoder(context);
        try {
            List<Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            return addresses.get(0).getAddressLine(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
