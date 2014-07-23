package br.edu.qi.toastalert;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class MainActivity extends Activity {

	Button btnOla;
	RelativeLayout layout;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        showToast("Seja bem-vindo!");
        showToast("Mensagem rápida...");
        
        AlertDialog.Builder alert = 
        		new AlertDialog.Builder(this);
        
        alert.setTitle("Boas-vindas");
        alert.setMessage("Olá, seja bem-vindo!");
        alert.setNeutralButton("OK", null);
        alert.show();
        
        layout = (RelativeLayout)findViewById(R.id.layout);
        
        btnOla = (Button)findViewById(R.id.btnOla);
        btnOla.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showToast("Olá");
				layout.setBackgroundResource(R.color.azul);
			}
		});
        
        btnOla.setOnLongClickListener(new View.OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				showToast("Long Olá");
				return true;
			}
		});
        
    } // fecha método
    
    private void showToast(String msg) {
    	Toast.makeText(
    			this,
    			msg,
    			Toast.LENGTH_LONG).show();
    } // fecha método


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
