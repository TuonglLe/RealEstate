package android.example.com.imageexample.ObserverPattern.OnSreenEstateCursor;

import android.database.Cursor;
import android.example.com.imageexample.ObserverPattern.Subject;

import java.util.ArrayList;
import java.util.List;



public class OnScreenEstateCursorPublisher implements Subject<OnScreenEstateCursorObserver> {

    private static volatile OnScreenEstateCursorPublisher instance;
    public static OnScreenEstateCursorPublisher getInstance(){
        if(instance == null){
            synchronized (OnScreenEstateCursorPublisher.class){
                instance = new OnScreenEstateCursorPublisher();
            }
        }
        return instance;
    }

    private List<OnScreenEstateCursorObserver> observers;
    private Cursor onScreeenEstateCursor;

    public OnScreenEstateCursorPublisher() {
        observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(OnScreenEstateCursorObserver observer) {
        if(observer == null){
            return;
        }
        observers.add(observer);
    }

    @Override
    public void unregister(OnScreenEstateCursorObserver observer) {
        if(observer == null){
            return;
        }
        observers.remove(observer);
    }

    @Override
    public void notifyData() {
        for(OnScreenEstateCursorObserver observer: observers){
            observer.update(onScreeenEstateCursor);
        }
    }

    public void setOnScreeenEstateCursor(Cursor onScreeenEstateCursor) {
        this.onScreeenEstateCursor = onScreeenEstateCursor;
    }
}
