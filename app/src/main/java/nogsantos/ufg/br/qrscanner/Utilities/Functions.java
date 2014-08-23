package nogsantos.ufg.br.qrscanner.Utilities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by nogsantos on 8/21/14.
 */
public final class Functions {
    /**
     * 
     */
    /**
     * Chama a nova activity fechando a atual.
     *
     * @param context Context,[getApplicationContext()]
     * @param newClass Class, [Class.class]
     * @param activity Activity, [this]
     */
    public static void callActivity(Context context, Class newClass, Activity activity){
        Intent targetActivity = new Intent(context, newClass);
        targetActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(targetActivity);
        activity.finish();
    }


}
