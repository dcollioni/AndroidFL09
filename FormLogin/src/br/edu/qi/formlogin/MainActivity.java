package br.edu.qi.formlogin;

import android.R.bool;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;


public class MainActivity extends Activity {

	EditText etUsuario, etSenha;
	Button btnEntrar;
	ToggleButton tbValidar;
	
	private void carregarComponentes() {
		
		etUsuario = (EditText)findViewById(R.id.etUsuario);
		etSenha = (EditText)findViewById(R.id.etSenha);
		btnEntrar = (Button)findViewById(R.id.btnEntrar);
		tbValidar = (ToggleButton)findViewById(R.id.tbValidar);
		
	} // fecha método
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        carregarComponentes();
        
        btnEntrar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String usuario = etUsuario.getText().toString();
				String senha = etSenha.getText().toString();
				boolean validar = tbValidar.isChecked();
				
				if (validar) {
					String senhaCorreta = "android";
					
					if (senha.equals(senhaCorreta)) {
						showToast(getString(R.string.bem_vindo) + usuario);
					}
					else {
						showToast(getString(R.string.senha_incorreta));
					}
				}
				else {
					showToast(getString(R.string.sem_validacao));
				}
				
				/*
				Toast.makeText(
						getBaseContext(),
						"Olá, " + usuario,
						Toast.LENGTH_SHORT).show();
				
				Toast.makeText(
						getBaseContext(),
						"Sua senha é: " + senha,
						Toast.LENGTH_SHORT).show();
				
				String validar = tbValidar.isChecked() ? "Sim" : "Não";
				
				Toast.makeText(
						getBaseContext(),
						"Validar: " + validar,
						Toast.LENGTH_SHORT).show();
				*/
			}
		});
    } // fecha método
    
    
    private void showToast(String msg) {
    	Toast.makeText(
    			this,
    			msg,
    			Toast.LENGTH_SHORT).show();
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
