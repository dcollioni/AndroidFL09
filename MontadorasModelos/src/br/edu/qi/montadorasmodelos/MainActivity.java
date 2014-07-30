package br.edu.qi.montadorasmodelos;

import java.lang.reflect.Array;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends Activity {

	Spinner spMontadora, spModelo;
	
	private void carregarComponentes() {
		spMontadora = (Spinner)findViewById(R.id.spMontadora);
		spModelo = (Spinner)findViewById(R.id.spModelo);
	} // fecha método
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        carregarComponentes();
        
        spMontadora.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				
				String montadora = parent
									.getItemAtPosition(position)
									.toString();
				
				/*Toast.makeText(
						getBaseContext(),
						montadora,
						Toast.LENGTH_SHORT).show();*/
				
				ArrayList<Modelo> modelos = carregarModelos(montadora);
				
				ArrayAdapter<Modelo> adapter =
						new ArrayAdapter<Modelo>(
								getBaseContext(),
								android.R.layout.simple_spinner_item,
								modelos);
				
				adapter.setDropDownViewResource(
						android.R.layout.simple_spinner_dropdown_item
						);
				
				spModelo.setAdapter(adapter);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
        
        spModelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				
				Modelo modelo = (Modelo)
								parent.getItemAtPosition(position);
				
				Toast.makeText(
						getBaseContext(),
						"Código: " + modelo.getCodigo(),
						Toast.LENGTH_SHORT).show();
				
				Toast.makeText(
						getBaseContext(),
						"Nome: " + modelo.getNome(),
						Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
    } // fecha método
    
    
    private ArrayList<Modelo> carregarModelos(String montadora) {
    	
    	ArrayList<Modelo> modelos = new ArrayList<Modelo>();
    	
    	if (montadora.equals("Fiat")) {
    		modelos.add(new Modelo(11, "Linea"));
    		modelos.add(new Modelo(12, "Palio"));
    		modelos.add(new Modelo(13, "Siena"));
    	}
    	else if (montadora.equals("Ford")) {
    		modelos.add(new Modelo(21, "Edge"));
    		modelos.add(new Modelo(22, "Focus"));
    		modelos.add(new Modelo(23, "Fusion"));
    	}
    	else if (montadora.equals("GM")) {
    		modelos.add(new Modelo(31, "Agile"));
    		modelos.add(new Modelo(32, "Cruze"));
    		modelos.add(new Modelo(33, "Sonic"));
    	}
    	else if (montadora.equals("Volkswagen")) {
    		modelos.add(new Modelo(41, "Gol"));
    		modelos.add(new Modelo(42, "Jetta"));
    		modelos.add(new Modelo(43, "Voyage"));
    	}
    	
    	return modelos;
    	
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
