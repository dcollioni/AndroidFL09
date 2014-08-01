package br.edu.qi.imagens;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;


public class MainActivity extends Activity {

	Spinner spPais;
	ImageView ivBandeira;
	
	private void carregarComponentes() {
		spPais = (Spinner)findViewById(R.id.spPais);
		ivBandeira = (ImageView)findViewById(R.id.ivBandeira);
	} // fecha método
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        carregarComponentes();
        carregarPaises();
        
        spPais.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				
				Pais p = (Pais)parent.getItemAtPosition(position);
				
				int bandeira = p.getBandeira();
				
				ivBandeira.setImageResource(bandeira);				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
    } // fecha método
    
    private void carregarPaises() {
    	ArrayList<Pais> paises = new ArrayList<Pais>();
    	
    	paises.add(new Pais("Brasil", R.drawable.brasil));
    	paises.add(new Pais("Alemanha", R.drawable.alemanha));
    	paises.add(new Pais("Argentina", R.drawable.argentina));
    	paises.add(new Pais("Inglaterra", R.drawable.inglaterra));
    	paises.add(new Pais("Itália", R.drawable.italia));
    	
    	ArrayAdapter<Pais> adapter = new ArrayAdapter<Pais>(
    			getBaseContext(),
    			android.R.layout.simple_spinner_item,
    			paises
		);
    	adapter.setDropDownViewResource(
    			android.R.layout.simple_spinner_dropdown_item);
    	
    	spPais.setAdapter(adapter);
    	
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
