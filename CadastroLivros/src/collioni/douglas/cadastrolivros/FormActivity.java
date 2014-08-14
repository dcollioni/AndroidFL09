package collioni.douglas.cadastrolivros;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class FormActivity extends Activity {

	Db4oHelper db4o;
	
	EditText etTitulo, etAutor, etAno, etPaginas, etIsbn;
	Spinner spCategoria;
	Button btnSalvar, btnCancelar;
	
	Livro livro;
	long livroID;
	
	Intent intent;
	
	private void carregarComponentes() {
		etTitulo = (EditText)findViewById(R.id.etTitulo);
		etAutor = (EditText)findViewById(R.id.etAutor);
		etAno = (EditText)findViewById(R.id.etAno);
		etPaginas = (EditText)findViewById(R.id.etPaginas);
		etIsbn = (EditText)findViewById(R.id.etIsbn);
		spCategoria = (Spinner)findViewById(R.id.spCategoria);
		btnSalvar = (Button)findViewById(R.id.btnSalvar);
		btnCancelar = (Button)findViewById(R.id.btnCancelar);
	} // fecha método
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_form);
		
		String dir = getDir("data", 0) + "/";
		db4o = new Db4oHelper(dir);
		
		livro = new Livro();
		
		intent = getIntent();
		
		carregarComponentes();
		
		btnSalvar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				salvarLivro();
			} // fecha método
		});
		
		btnCancelar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	} // fecha método
	
	private void salvarLivro() {
		// pega os valores dos campos
		String titulo = etTitulo.getText().toString();
		String autor = etAutor.getText().toString();
		String categoria = spCategoria.getSelectedItem().toString();
		String isbn = etIsbn.getText().toString();
		int ano = Integer.parseInt(etAno.getText().toString());
		int paginas = Integer.parseInt(etPaginas.getText().toString());
		
		// atualiza o objeto com os valores
		livro.setTitulo(titulo);
		livro.setAutor(autor);
		livro.setCategoria(categoria);
		livro.setIsbn(isbn);
		livro.setAno(ano);
		livro.setPaginas(paginas);
		
		// armazena o objeto no banco
		db4o.db().store(livro);
		
		// fecha a activity
		finish();
	} // fecha método
	
	@Override
	protected void onResume() {
		db4o.abrirBanco();
		
		carregarLivro();
		
		super.onResume();
	} // fecha método
	
	private void carregarLivro() {
		// pega o parâmetro livroID da intent se existir
		livroID = intent.getLongExtra("livroID", 0);
		
		if (livroID > 0) {
			// pega o livro do banco pelo seu id
			livro = db4o.db().ext().getByID(livroID);
			db4o.db().ext().activate(livro);
			
			// popula o formulário com os valores do livro
			carregarForm();
		}
	} // fecha método
	
	private void carregarForm() {
		etTitulo.setText(livro.getTitulo());
		etAutor.setText(livro.getAutor());
		etIsbn.setText(livro.getIsbn());
		etAno.setText(Integer.toString(livro.getAno()));
		etPaginas.setText(Integer.toString(livro.getPaginas()));
		
		ArrayAdapter<String> adapter = (ArrayAdapter<String>)
								spCategoria.getAdapter();
		int posicao = adapter.getPosition(livro.getCategoria());
		spCategoria.setSelection(posicao);
	} // fecha método
	
	@Override
	protected void onPause() {
		db4o.fecharBanco();
		super.onPause();
	} // fecha método

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		
		return super.onOptionsItemSelected(item);
	}
}
