package nogsantos.ufg.br.qrscanner;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import nogsantos.ufg.br.qrscanner.Utilities.Functions;
import nogsantos.ufg.br.qrscanner.Utilities.HardwareUtils;


public class ResultActivity extends Activity {

    EditText txtResult;
    Button btSave;
    String varTxtResult;
    /**
     * Create
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        btSave    = (Button) findViewById(R.id.btSalvar);
        txtResult = (EditText) findViewById(R.id.idResult);
        Intent it = getIntent();
        if(it != null){
            String params = it.getStringExtra("res");
            if(params != null){
                txtResult.setText(params);
                varTxtResult = params;
            }
        }
    }
    /**
     * Save result as file on device
     */
    public void saveListener(View view){
        final EditText txtUrl = new EditText(this);
        txtUrl.setHint("");
        new AlertDialog.Builder(this)
            .setTitle(R.string.save_result)
            .setMessage(R.string.save_result_message)
            .setView(txtUrl)
            .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    String name = txtUrl.getText().toString();
                    if(name.trim().length()>0){
                        HardwareUtils.createFile(varTxtResult,name);
                        Toast.makeText(getApplicationContext(),R.string.save_result_success,Toast.LENGTH_LONG).show();
                    }
                }
            })
            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                }
            })
            .show();
    }
    /**
     * Back press button listener
     */
    @Override
    public void onBackPressed() {
        Functions.callActivity(getApplicationContext(), MainActivity.class, this);
        this.finish();
    }
}
