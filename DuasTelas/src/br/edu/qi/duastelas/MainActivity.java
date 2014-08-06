package br.edu.qi.duastelas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity {

	EditText etN1, etN2;
	Button btnSomar;
	Intent intent;
	
	private void carregarComponentes() {
		etN1 = (EditText)findViewById(R.id.etN1);
		etN2 = (EditText)findViewById(R.id.etN2);
		btnSomar = (Button)findViewById(R.id.btnSomar);
	} // fecha método
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        carregarComponentes();
        
        // Cria a intent definindo Resultado como destino
        intent = new Intent(this, Resultado.class);
        
        btnSomar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				double n1 = Double.parseDouble(etN1.getText().toString());
				double n2 = Double.parseDouble(etN2.getText().toString());
				
				intent.putExtra("n1", n1);
				intent.putExtra("n2", n2);
				
				// executa a intent
				startActivity(intent);
				
			} // fecha método
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
