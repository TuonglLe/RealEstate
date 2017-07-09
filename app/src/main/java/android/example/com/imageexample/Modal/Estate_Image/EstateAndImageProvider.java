package android.example.com.imageexample.Modal.Estate_Image;

import android.example.com.imageexample.Modal.AppDBHelper;
import android.example.com.imageexample.Modal.Contracts;
import android.example.com.imageexample.Modal.ThirdTableProvider;



public class EstateAndImageProvider extends ThirdTableProvider {
    public static final String AUTHOTIRY = "android.example.com.imageexample.Modal.Estate_Image.EstateAndImageProvider";
    @Override
    public boolean onCreate() {
        initialize(
                AUTHOTIRY,
                new AppDBHelper(getContext()),
                Contracts.EstateAndImage.CONTENT_URI,
                Contracts.EstateAndImage.DEFAULT_PROJECTION,
                Contracts.EstateAndImage.ESTATE_PRIMARY,
                Contracts.EstateAndImage.IMAGE_PRIMARY

        );
        return true;
    }
}
