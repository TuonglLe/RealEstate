package android.example.com.imageexample.ObserverPattern.Network;



public interface NetworkStateObserver {
    void notify(boolean isOnline);
}
