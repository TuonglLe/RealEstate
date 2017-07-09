package android.example.com.imageexample.ObserverPattern.EstatePublisher;

import android.example.com.imageexample.Modal.Estate.Estate;
import android.example.com.imageexample.ObserverPattern.Publisher;
import android.example.com.imageexample.ObserverPattern.Subject;

import java.util.List;


public class EstatesPublisher extends Publisher<EstateObserver>{
    private static volatile EstatesPublisher instance;
    public static EstatesPublisher getInstance(){
        if(instance == null){
            synchronized (EstatesPublisher.class){
                instance = new EstatesPublisher();
            }
        }
        return instance;
    }



    private List<Estate> estates;

    public void setEstates(List<Estate> estates) {
        this.estates = estates;
    }


    @Override
    protected void updateObserver(EstateObserver estateObserver) {
        estateObserver.update(estates);
    }
}
