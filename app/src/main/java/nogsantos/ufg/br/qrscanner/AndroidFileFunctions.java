package nogsantos.ufg.br.qrscanner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.content.Context;
import android.os.Environment;

public class AndroidFileFunctions {

    public static String getFileValue(String fileName, Context context) {
        try {
            StringBuffer outStringBuf = new StringBuffer();
            String inputLine = "";
            /*
             * We have to use the openFileInput()-method the ActivityContext
             * provides. Again for security reasons with openFileInput(...)
             */
            FileInputStream fIn = context.openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fIn);
            BufferedReader inBuff = new BufferedReader(isr);
            while ((inputLine = inBuff.readLine()) != null) {
                outStringBuf.append(inputLine);
                outStringBuf.append("\n");
            }
            inBuff.close();
            return outStringBuf.toString();
        } catch (IOException e) {
            return null;
        }
    }

    public static boolean appendFileValue(String fileName, String value,
                                          Context context) {
        return writeToFile(fileName, value, context, Context.MODE_APPEND);
    }

    public static boolean setFileValue(String fileName, String value,
                                       Context context) {
        return writeToFile(fileName, value, context,
                Context.MODE_WORLD_READABLE);
    }

    public static boolean writeToFile(String fileName, String value,
                                      Context context, int writeOrAppendMode) {
        // just make sure it's one of the modes we support
        if (writeOrAppendMode != Context.MODE_WORLD_READABLE
                && writeOrAppendMode != Context.MODE_WORLD_WRITEABLE
                && writeOrAppendMode != Context.MODE_APPEND) {
            return false;
        }
        try {
            /*
             * We have to use the openFileOutput()-method the ActivityContext
             * provides, to protect your file from others and This is done for
             * security-reasons. We chose MODE_WORLD_READABLE, because we have
             * nothing to hide in our file
             */
            FileOutputStream fOut = context.openFileOutput(fileName,
                    writeOrAppendMode);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            // Write the string to the file
            osw.write(value);
            // save and close
            osw.flush();
            osw.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static void deleteFile(String fileName, Context context) {
        context.deleteFile(fileName);
    }

    static void createFile(String text) {
        try {
            File external = Environment.getExternalStorageDirectory();
            String sdcardPath = external.getPath();
            File file = new File(sdcardPath + "/Documents/senha.txt");
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
}