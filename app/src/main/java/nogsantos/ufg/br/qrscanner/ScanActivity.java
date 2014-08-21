package nogsantos.ufg.br.qrscanner;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.widget.FrameLayout;

import net.sourceforge.zbar.Config;
import net.sourceforge.zbar.Image;
import net.sourceforge.zbar.ImageScanner;
import net.sourceforge.zbar.Symbol;
import net.sourceforge.zbar.SymbolSet;

import nogsantos.ufg.br.qrscanner.Utilities.Functions;
import nogsantos.ufg.br.qrscanner.Utilities.HardwareUtils;


public class ScanActivity extends Activity{
        private Camera mCamera;
        private CameraPreview mPreview;
        private Handler autoFocusHandler;
        ImageScanner scanner;
        private boolean barcodeScanned = false;
        private boolean previewing     = true;
        static {
            System.loadLibrary("iconv");
            System.loadLibrary("zbarjni");
        }
        /**
         *
         */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_scan);

            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

            autoFocusHandler = new Handler();
            mCamera          = getCameraInstance();
        /*
         * Instance barcode scanner
         */
            scanner = new ImageScanner();
            scanner.setConfig(0, Config.X_DENSITY, 3);
            scanner.setConfig(0, Config.Y_DENSITY, 3);

            mPreview = new CameraPreview(this, mCamera, previewCb, autoFocusCB);
            FrameLayout preview = (FrameLayout)findViewById(R.id.cameraPreview);
            preview.addView(mPreview);
        }
        /**
         *
         */
        @Override
        public void onPause() {
            super.onPause();
            releaseCamera();
        }
        /**
         *  A safe way to get an instance of the Camera object.
         */
    public static Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open();
        } catch (Exception e){
        }
        return c;
    }
    /**
     *
     */
    private void releaseCamera() {
        if (mCamera != null) {
            previewing = false;
            mCamera.setPreviewCallback(null);
            mCamera.release();
            mCamera = null;
        }
    }
    /**
     *
     */
    private Runnable doAutoFocus = new Runnable() {
        public void run() {
            if (previewing)
                mCamera.autoFocus(autoFocusCB);
        }
    };
    /**
     *
     */
    Camera.PreviewCallback previewCb = new Camera.PreviewCallback() {
        public void onPreviewFrame(byte[] data, Camera camera) {
            Camera.Parameters parameters = camera.getParameters();
            Camera.Size size = parameters.getPreviewSize();

            Image barcode = new Image(size.width, size.height, "Y800");
            barcode.setData(data);

            int result = scanner.scanImage(barcode);

            if (result != 0) {
                previewing = false;
                mCamera.setPreviewCallback(null);
                mCamera.stopPreview();

                SymbolSet syms = scanner.getResults();
                Intent objResult = new Intent(getApplicationContext(), ResultActivity.class);
                for (Symbol sym : syms) {
                    objResult.putExtra("res",sym.getData());
                    barcodeScanned = true;
                }
                HardwareUtils.makeABeep();
                HardwareUtils.vibrate(getApplicationContext());
                startActivity(objResult);
                finish();
            }
        }
    };
    /*
     * Mimic continuous auto-focusing
     */
    Camera.AutoFocusCallback autoFocusCB = new Camera.AutoFocusCallback() {
        public void onAutoFocus(boolean success, Camera camera) {
            autoFocusHandler.postDelayed(doAutoFocus, 1000);
        }
    };
    /**
     *
     */
    @Override
    public void onBackPressed() {
        Functions.flushCurrentActivity(getApplicationContext(), MainActivity.class);
        finish();
        System.exit(0);
    }
}
