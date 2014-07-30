package br.edu.qi.calculoimc;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;


public class MainActivity extends Activity {

	RadioButton rbMasculino, rbFeminino;
	EditText etPeso, etAltura;
	CheckBox cbMostrarPeso;
	Button btnCalcular, btnLimpar;
	TextView tvResultado, tvValorCalculado, tvPesoIdeal;
	LinearLayout llPesoIdeal;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        carregarComponentes();
        
        btnCalcular.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				double peso = Double.parseDouble(etPeso.getText().toString());
				double altura = Double.parseDouble(etAltura.getText().toString());
				double valorImc = peso / (altura*altura); 
				
				String resultado = "";
				double pesoIdeal = 0;
				
				if (rbMasculino.isChecked()) {
					resultado = pegarResultadoMasculino(valorImc);
					pesoIdeal = calcularPesoIdealMasculino(altura);
				}
				else {
					resultado = pegarResultadoFeminino(valorImc);
					pesoIdeal = calcularPesoIdealFeminino(altura);
				}
				
				tvResultado.setText(resultado);
				tvValorCalculado.setText(Double.toString(valorImc));
				tvPesoIdeal.setText(Double.toString(pesoIdeal));
				
				if (cbMostrarPeso.isChecked()) {
					llPesoIdeal.setVisibility(LinearLayout.VISIBLE);
				}
				else {
					llPesoIdeal.setVisibility(LinearLayout.INVISIBLE);
				}
			}
		});
        
        btnLimpar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				rbMasculino.setChecked(true);
				etPeso.setText(null);
				etAltura.setText(null);
				cbMostrarPeso.setChecked(false);
				tvResultado.setText(null);
				tvValorCalculado.setText(null);
				tvPesoIdeal.setText(null);
				llPesoIdeal.setVisibility(LinearLayout.INVISIBLE);
			}
		});
        
        cbMostrarPeso.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					llPesoIdeal.setVisibility(LinearLayout.VISIBLE);
				}
				else {
					llPesoIdeal.setVisibility(LinearLayout.INVISIBLE);
				}
			}
		});
    }

    private void carregarComponentes() {
    	rbMasculino = (RadioButton)findViewById(R.id.rbMasculino);
    	rbFeminino = (RadioButton)findViewById(R.id.rbFeminino);
    	etPeso = (EditText)findViewById(R.id.etPeso);
    	etAltura = (EditText)findViewById(R.id.etAltura);
    	cbMostrarPeso = (CheckBox)findViewById(R.id.cbMostrarPeso);
    	btnCalcular = (Button)findViewById(R.id.btnCalcular);
    	btnLimpar = (Button)findViewById(R.id.btnLimpar);
    	tvResultado = (TextView)findViewById(R.id.tvResultado);
    	tvValorCalculado = (TextView)findViewById(R.id.tvValorCalculado);
    	tvPesoIdeal = (TextView)findViewById(R.id.tvPesoIdeal);
    	llPesoIdeal = (LinearLayout)findViewById(R.id.llPesoIdeal);
    } // fecha método
    
    private String pegarResultadoMasculino(double imc) {
    	String resultado = "";
    	
    	if (imc < 19.9) {
    		resultado = "Sobpeso";
    	}
    	else if (imc <= 23.6) {
    		resultado = "Normal";
    	}
    	else if (imc <= 27.7) {
    		resultado = "Sobrepeso";
    	}
    	else {
    		resultado = "Obesidade";
    	}
    	
    	return resultado;
    } // fecha método
    
    private String pegarResultadoFeminino(double imc) {
    	String resultado = "";
    	
    	if (imc < 19.6) {
    		resultado = "Sobpeso";
    	}
    	else if (imc <= 24.2) {
    		resultado = "Normal";
    	}
    	else if (imc <= 28.8) {
    		resultado = "Sobrepeso";
    	}
    	else {
    		resultado = "Obesidade";
    	}
    	
    	return resultado;
    } // fecha método
    
    private double calcularPesoIdealMasculino(double altura) {
    	
    	return (altura * altura) * 21.5;
    	
    } // fecha método 
    
    private double calcularPesoIdealFeminino(double altura) {
    	
    	return (altura * altura) * 22;
    	
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
