package br.edu.qi.cadastroestados;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

public class MainActivity extends Activity {

	// objeto para manipular o banco de dados
	ObjectContainer db;
	final String DB4O_BANCO = "Estados.db4o";
	
	EditText etNome, etSigla;
	Button btnIncluir;
	ListView lvEstados;
	
	ArrayList<Estado> estados;
	ArrayAdapter<Estado> adapter;
	
	private void carregarComponentes() {
		etNome = (EditText)findViewById(R.id.etNome);
		etSigla = (EditText)findViewById(R.id.etSigla);
		btnIncluir = (Button)findViewById(R.id.btnIncluir);
		lvEstados = (ListView)findViewById(R.id.lvEstados);
	} // fecha método
	
	private void configurarLista() {
		// inicia o array de estados vazio
		estados = new ArrayList<Estado>();
		
		// cria o adapter passando o array
		adapter = new ArrayAdapter<Estado>(
			getBaseContext(),
			android.R.layout.simple_list_item_activated_1,
			estados);
		
		adapter.setDropDownViewResource(
			android.R.layout.simple_list_item_1);
		
		// configura a lista com o adapter criado
		lvEstados.setAdapter(adapter);
		
		// configura o modo de escolha da lista
		lvEstados.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
	} // fecha método
	
	private void abrirBanco() {
		// pegar o diretório 'data'
		String dir = getDir("data", 0) + "/";
		
		// monta o caminho do arquivo do banco
		String banco = dir + DB4O_BANCO;
		
		// abre o banco de dados
		db = Db4oEmbedded.openFile(
				Db4oEmbedded.newConfiguration(),
				banco);
	} // fecha método
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        carregarComponentes();
        configurarLista();
        
        abrirBanco();
        carregarEstados();
        
        btnIncluir.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				incluirEstado();
			}
		});
        
        lvEstados.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				// pega o objeto clicado
				Estado estado = (Estado)parent.getItemAtPosition(position);
				
				excluirEstado(estado);
				
				return true;
			}
		});
    } // fecha método
    
    private void carregarEstados() {
    	// consulta os estados no banco
    	List<Estado> resultados = db.query(Estado.class);
    	
    	
    	
    	// limpa o array de estados
    	estados.clear();
    	
    	// adiciona os resultados do banco no array
    	estados.addAll(resultados);
    	
    	// ordena os resultados por nome
    	Collections.sort(estados, new Comparator<Estado>() {
    	        @Override
    	        public int compare(Estado e1, Estado e2)
    	        {
    	            return  e1.getNome().compareTo(e2.getNome());
    	        }
    	    });
    	
    	// notifica o adapter sobre a mudança
    	adapter.notifyDataSetChanged();
    } // fecha método
    
    private void incluirEstado() {
		// pega os valores dos campos
    	String nome = etNome.getText().toString();
    	String sigla = etSigla.getText().toString();
    	
    	// cria um objeto Estado com os valores
    	Estado estado = new Estado(nome, sigla);
    	
    	// armazena o objeto no banco
    	db.store(estado);
    	
    	// confirma a operação
    	db.commit();
    	
    	// limpa os campos
    	etNome.setText(null);
    	etSigla.setText(null);
    	
    	// recarrega os estados
    	carregarEstados();
    } // fecha método
    
    private void excluirEstado(final Estado estado) {
    	Builder alertBuilder = new AlertDialog.Builder(this);
    	alertBuilder.setTitle("Confirmação");
    	alertBuilder.setMessage("Confirma a exclusão do registro?");
    	
    	alertBuilder.setNegativeButton("Não", null);
    	alertBuilder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				// exclui o objeto no banco
		    	db.delete(estado);
		    	
		    	// confirma a operação
		    	db.commit();
		    	
		    	// recarrega a lista de estados
		    	carregarEstados();
			}
		});
    	
    	alertBuilder.show();
    } // fecha método
    
    @Override
    protected void onDestroy() {
    	
    	// fecha o banco de dados
    	if (db != null) {
    		db.close();
    	}
    	
    	super.onDestroy();
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
