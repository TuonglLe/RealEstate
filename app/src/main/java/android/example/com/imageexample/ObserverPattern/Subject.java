package android.example.com.imageexample.ObserverPattern;



public interface Subject<T> {
    void registerObserver(T t);
    void unregister(T t);
    void notifyData();
}
