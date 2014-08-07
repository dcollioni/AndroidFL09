package br.edu.qi.contatos;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends Activity {

	ListView lvContatos;
	ArrayList<Contato> contatos;
	Intent intent;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        carregarContatos();
        
        lvContatos = (ListView)findViewById(R.id.lvContatos);
        
        ArrayAdapter<Contato> adapter =
        		new ArrayAdapter<Contato>(
        				getBaseContext(),
        				android.R.layout.simple_list_item_1,
        				contatos
				);
        
        lvContatos.setAdapter(adapter);
        
        intent = new Intent(this, DetalheActivity.class);
        
        lvContatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Contato c = (Contato)
							parent.getItemAtPosition(position);
				
				intent.putExtra("contato", c);
				startActivityForResult(intent, 1);
			}
		});
    } // fecha método
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	
    	Toast.makeText(getBaseContext(),
    				   "Request code " + requestCode,
    				   Toast.LENGTH_SHORT).show();
    	
    	Toast.makeText(getBaseContext(),
				   	   "Result code " + resultCode,
				   	   Toast.LENGTH_SHORT).show();
    	
    	Bundle params = data != null
    					? data.getExtras()
						: null;
		
		if (params != null) {
			String retorno = params.getString("retorno");
			
			Toast.makeText(
					getBaseContext(),
					retorno,
					Toast.LENGTH_SHORT).show();
		}
    	    	
    	super.onActivityResult(requestCode, resultCode, data);
    }
    
    private void carregarContatos() {
    	
    	contatos = new ArrayList<Contato>();
    	
    	contatos.add(new Contato("Ana",
    							 "9999",
    							 "ana@email.com",
    							 "Poa"));
    	
    	contatos.add(new Contato("Bruna",
								 "8888",
								 "bruna@email.com",
								 "Canoas"));
    	
    	contatos.add(new Contato("Carlos",
								 "7777",
								 "carlos@email.com",
								 "São Leopoldo"));
    	
    	contatos.add(new Contato("Diego",
								 "6666",
								 "diego@email.com",
								 "Novo Hamburgo"));
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
