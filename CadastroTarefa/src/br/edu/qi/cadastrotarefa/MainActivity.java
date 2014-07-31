package br.edu.qi.cadastrotarefa;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends Activity {

	ListView lvTarefas;
	ArrayList<Tarefa> tarefas;
	ArrayAdapter<Tarefa> adapter;
	EditText etNovaTarefa;
	Button btnIncluir;
	
	private void carregarComponentes() {
		lvTarefas = (ListView)findViewById(R.id.lvTarefas);
		etNovaTarefa = (EditText)findViewById(R.id.etNovaTarefa);
		btnIncluir = (Button)findViewById(R.id.btnIncluir);
	} // fecha método
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        carregarComponentes();
        carregarTarefas();
        
        adapter = new ArrayAdapter<Tarefa>(
        			getBaseContext(),
        			android.R.layout.simple_list_item_activated_1,
        			tarefas
        		);
        adapter.setNotifyOnChange(true);
        
        lvTarefas.setAdapter(adapter);
        lvTarefas.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        
        lvTarefas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Tarefa t = tarefas.get(position);
				adapter.remove(t);
				
				return true;
			}
		});
        
        btnIncluir.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String descricao = etNovaTarefa.getText().toString();
				int codigo = descricao.hashCode();
				
				Tarefa t = new Tarefa(codigo, descricao);
				adapter.add(t);
			}
		});
        
        lvTarefas.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Tarefa t = tarefas.get(position);
				
				Toast.makeText(
						getBaseContext(),
						Integer.toString(t.getCodigo()),
						Toast.LENGTH_SHORT).show();
			}
		});
        
    } // fecha método
    
    private void carregarTarefas() {
    	tarefas = new ArrayList<Tarefa>();
    	for (int i = 1; i < 1; i++) {
    		tarefas.add(
    					new Tarefa(i, "Tarefa " + i)
    				);
    	}
    }


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
