package nogsantos.ufg.br.qrscanner.Utilities;

import android.content.Context;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Environment;
import android.os.Vibrator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * Created by nogsantos on 8/20/14.
 *
 * Utilidades para interação com o hardware do dispositivo
 */
public final class HardwareUtils {
    /**
     * Cria um arquivo no diretório Documents do dispoditivo
     */
    public static void createFile(String text, String fileName, String fileExtension) {
        try {
            File external = Environment.getExternalStorageDirectory();
            String sdcardPath = external.getPath();
            File file = new File(sdcardPath + "/Documents/"+fileName+"."+fileExtension);
            file.createNewFile();
            FileWriter filewriter = new FileWriter(file);
            BufferedWriter out = new BufferedWriter(filewriter);

            out.write(text);

            out.close();
            filewriter.close();
        } catch (Exception e) {
            android.util.Log.d("failed to save file", e.toString());
        }
    }
    /**
     * Beep
     */
    public static void makeABeep(){
        ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 100);
        toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);

    }
    /**
     * Vibra o dispositivo
     */
    public static void vibrate(Context context){
        Vibrator vibs = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibs.vibrate(800);
    }
}
