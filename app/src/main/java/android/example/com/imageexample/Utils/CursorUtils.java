package android.example.com.imageexample.Utils;

import android.content.Context;
import android.database.Cursor;
import android.example.com.imageexample.Modal.Contracts;


public final class CursorUtils {
    private CursorUtils(){}

    public static Cursor getCursor_UserAndEstate(Context context,String userToken, String orderBy){
        if(context == null || userToken == null){
            return null;
        }
        String selection = Contracts.UserAndEstate.USER_TOKEN + "=?";
        String[] selectors = new String[]{userToken};
        return context.getContentResolver().query(
                Contracts.UserAndEstate.CONTENT_URI,
                null,
                selection,
                selectors,
                orderBy
        );
    }


}
