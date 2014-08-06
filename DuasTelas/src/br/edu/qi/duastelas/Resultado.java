package br.edu.qi.duastelas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Resultado extends Activity {

	TextView tvN1, tvN2, tvResultado;
	Button btnVoltar;
	Intent intent;
	Activity me;
	
	private void carregarComponentes() {
		tvN1 = (TextView)findViewById(R.id.tvN1);
		tvN2 = (TextView)findViewById(R.id.tvN2);
		tvResultado = (TextView)findViewById(R.id.tvResultado);
		btnVoltar = (Button)findViewById(R.id.btnVoltar);
		me = this;
	} // fecha método
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resultado);
		carregarComponentes();
		
		// recebe a intent enviada
		intent = getIntent();
		
		// recebe os valores extras
		double n1 = intent.getDoubleExtra("n1", 0);
		double n2 = intent.getDoubleExtra("n2", 0);
		
		// atualiza os text views com os valores recebidos
		tvN1.setText(Double.toString(n1));
		tvN2.setText(Double.toString(n2));
		
		// calcula a soma
		double resultado = n1 + n2;
		
		// atualiza o text view com o resultado
		tvResultado.setText(Double.toString(resultado));
		
		btnVoltar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// finaliza esta activity
				me.finish();
				
			} // fecha método
		});
		
	} // fecha método

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.resultado, menu);
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
