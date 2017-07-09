package android.example.com.imageexample.ObserverPattern.Network;

import android.example.com.imageexample.ObserverPattern.Publisher;



public class NetworkStatePublisher extends Publisher<NetworkStateObserver> {

    private volatile  static NetworkStatePublisher instance;
    public static NetworkStatePublisher getInstance(){
        if(instance == null){
            synchronized (NetworkStatePublisher.class){
                instance = new NetworkStatePublisher();
            }
        }
        return instance;
    }

    private boolean isOnline;
    @Override
    protected void updateObserver(NetworkStateObserver networkStateObserver) {
        networkStateObserver.notify(isOnline);
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }
}
