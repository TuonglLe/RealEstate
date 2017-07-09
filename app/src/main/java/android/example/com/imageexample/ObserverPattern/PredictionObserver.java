package android.example.com.imageexample.ObserverPattern;

import com.google.android.gms.location.places.AutocompletePrediction;



public interface PredictionObserver{
    void update(AutocompletePrediction autocompletePrediction);
}
