package android.example.com.imageexample.ObserverPattern.EstatePublisher.GoogleMapPunlisher;

import android.example.com.imageexample.GoogleCallBack.GoogleMapKits;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.util.List;



public interface GoogleMapKitsObserver {
    void update(GoogleMapKits googleMapKits);
}
