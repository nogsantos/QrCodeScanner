package nogsantos.ufg.br.qrscanner.Utilities;

import android.content.Context;
import android.content.Intent;

/**
 * Created by nogsantos on 8/21/14.
 */
public final class Functions {
    /**
     * 
     */
    public static void flushCurrentActivity(Context context, Class newClass) {
        Intent intent = new Intent(context, newClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }
}
