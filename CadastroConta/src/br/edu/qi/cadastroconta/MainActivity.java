package br.edu.qi.cadastroconta;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class MainActivity extends Activity {

	RadioGroup rgTipoConta;
	RadioButton rbContaCorrente, rbContaPoupanca;
	EditText etNumero;
	CheckBox cbCartaoCredito, cbTalaoCheque, cbSeguroCartao;
	Button btnSalvar;
	
	private void carregarComponentes() {
		
		rgTipoConta = (RadioGroup)
				findViewById(R.id.rgTipoConta);
		
		rbContaCorrente = (RadioButton)
				findViewById(R.id.rbContaCorrente);
		
		rbContaPoupanca = (RadioButton)
				findViewById(R.id.rbContaPoupanca);
		
		etNumero = (EditText)findViewById(R.id.etNumero);
		
		cbCartaoCredito = (CheckBox)
				findViewById(R.id.cbCartaoCredito);
		
		cbTalaoCheque = (CheckBox)
				findViewById(R.id.cbTalaoCheque);
		
		cbSeguroCartao = (CheckBox)
				findViewById(R.id.cbSeguroCartao);
		
		btnSalvar = (Button)
				findViewById(R.id.btnSalvar);
		
	} // fecha método
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        carregarComponentes();
        
        btnSalvar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Conta c = new Conta();
				
				String tipo = "";
				
				if (rbContaCorrente.isChecked()) {
					tipo = "Corrente";
				}
				else if (rbContaPoupanca.isChecked()) {
					tipo = "Poupança";
				}
				
				int numero = Integer.parseInt(
							etNumero.getText().toString()						
						);
				
				boolean cartaoCredito = cbCartaoCredito.isChecked();
				boolean talaoCheque = cbTalaoCheque.isChecked();
				boolean seguroCartao = cbSeguroCartao.isChecked();
				
				c.setTipo(tipo);
				c.setNumero(numero);
				c.setCartaoCredito(cartaoCredito);
				c.setTalaoCheque(talaoCheque);
				c.setSeguroCartao(seguroCartao);
				
				Toast.makeText(
						getBaseContext(),
						c.toString(),
						Toast.LENGTH_LONG).show();
			} // fecha onClick
		});
        
        rgTipoConta.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				
				if (rbContaCorrente.isChecked()) {
					cbTalaoCheque.setEnabled(true);
				}
				else {
					cbTalaoCheque.setEnabled(false);
					cbTalaoCheque.setChecked(false);
				}
			}
		});
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
