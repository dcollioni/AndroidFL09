package br.edu.qi.contatos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetalheActivity extends Activity {

	TextView tvNome, tvTelefone, tvEmail, tvCidade;
	Button btnEditar, btnExcluir;
	Intent intent;
	
	private void carregarComponentes() {
		tvNome = (TextView)findViewById(R.id.tvNome);
		tvTelefone = (TextView)findViewById(R.id.tvTelefone);
		tvEmail = (TextView)findViewById(R.id.tvEmail);
		tvCidade = (TextView)findViewById(R.id.tvCidade);
		btnEditar = (Button)findViewById(R.id.btnEditar);
		btnExcluir = (Button)findViewById(R.id.btnExcluir);
		
		intent = getIntent();
	} // fecha método
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalhe);
		carregarComponentes();
		
		Contato c = (Contato)
					intent.getSerializableExtra("contato");
		
		tvNome.setText(c.getNome());
		tvTelefone.setText(c.getTelefone());
		tvEmail.setText(c.getEmail());
		tvCidade.setText(c.getCidade());
		
		btnEditar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent();
				i.putExtra("retorno", "Editar");
				setResult(2, i);
				finish();
			}
		});
		
		btnExcluir.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent();
				i.putExtra("retorno", "Excluir");
				setResult(2, i);
				finish();
			}
		});
		
	} // fecha método

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detalhe, menu);
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
