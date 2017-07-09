package android.example.com.imageexample.ObserverPattern.OnSreenEstateCursor;

import android.database.Cursor;



public interface OnScreenEstateCursorObserver {
    void update(Cursor onScreenEstateCursor);
}
