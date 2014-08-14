package collioni.douglas.cadastrolivros;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class MainActivity extends Activity {

	Db4oHelper db4o;
	
	ListView lvLivros;
	ArrayList<Livro> livros;
	ArrayAdapter<Livro> adapter;
	
	long livroID;
	
	final int MENU_EXCLUIR = 1;
	
	private void configurarLista() {
		lvLivros = (ListView)findViewById(R.id.lvLivros);
		
		livros = new ArrayList<Livro>();
		
		adapter = new ArrayAdapter<Livro>(
					getBaseContext(),
					android.R.layout.simple_dropdown_item_1line,
					livros);
		
		adapter.setDropDownViewResource(
					android.R.layout.simple_list_item_1);
		
		lvLivros.setAdapter(adapter);
	} // fecha método
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        String dir = getDir("data", 0) + "/";
        db4o = new Db4oHelper(dir);
        
        configurarLista();
        
        lvLivros.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// pega o livro selecionado
				Livro livro = (Livro)parent.getItemAtPosition(position);
				
				// pega o ID do livro no banco de dados
				livroID = db4o.db().ext().getID(livro);
				
				// cria uma intent para a tela de cadastro
				Intent i = new Intent(
								MainActivity.this,
								FormActivity.class);
				
				// passa o ID do livro para a intent
				i.putExtra("livroID", livroID);
				
				// executa a intent
				startActivity(i);
			}
		});
        
        registerForContextMenu(lvLivros);
    } // fecha método
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
    		ContextMenuInfo menuInfo) {
    	
    	if (v.getId() == R.id.lvLivros) {
    		// adiciona o item "Excluir" no menu de contexto
    		menu.add(1, MENU_EXCLUIR, 100, R.string.excluir_livro);
    		
    		// pega um objeto adapter do menu
    		AdapterContextMenuInfo adapter = (AdapterContextMenuInfo)
    											menuInfo;
    		
    		// pega o livro clicado pela posição na lista
    		Livro livro = (Livro)
    					lvLivros.getItemAtPosition(adapter.position);
    		
    		// pega o ID do livro no banco de dados
    		livroID = db4o.db().ext().getID(livro);
    	}
    	
    	super.onCreateContextMenu(menu, v, menuInfo);
    } // fecha método
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
    	// pega o id do item clicado
    	int id = item.getItemId();
    	
    	// verifica se é a opção "Excluir livro"
    	if (id == MENU_EXCLUIR) {
    		// pega o livro do banco de dados pelo ID
    		Livro livro = db4o.db().ext().getByID(livroID);
    		
    		// exclui o livro no banco
    		db4o.db().delete(livro);
    		
    		// confirma a operação
    		db4o.db().commit();
    		
    		// recarrega a lista de livros
    		carregarLivros();
    	}
    	
    	return super.onContextItemSelected(item);
    } // fecha método
    
    @Override
    protected void onResume() {
    	db4o.abrirBanco();
    	carregarLivros();
    	super.onResume();
    } // fecha método
    
    private void carregarLivros() {
    	// consulta os livros no banco
    	List<Livro> resultados = db4o.db().query(Livro.class);
    	
    	// atualiza o array de livros do adapter
    	livros.clear();
    	livros.addAll(resultados);
    	
    	// notifica a alteração no adapter
    	adapter.notifyDataSetChanged();
    } // fecha método
    
    @Override
    protected void onPause() {
    	db4o.fecharBanco();
    	super.onPause();
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
    	
    	if (id == R.id.novo_livro) {
    		// abre a activity de cadastro
    		Intent i = new Intent(
							MainActivity.this,
							FormActivity.class);
    		
    		startActivity(i);
    	}
    	
        return super.onOptionsItemSelected(item);
    }
}
