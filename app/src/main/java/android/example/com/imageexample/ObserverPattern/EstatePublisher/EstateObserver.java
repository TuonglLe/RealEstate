package android.example.com.imageexample.ObserverPattern.EstatePublisher;

import android.example.com.imageexample.Modal.Estate.Estate;

import java.util.List;



public interface EstateObserver {
    void update(List<Estate> estates);
}
