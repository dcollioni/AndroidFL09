package br.edu.qi.consultacep;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends Activity {

	EditText etCep;
	Button btnConsultar;
	TextView tvResultado;
	ProgressBar pbConsultando;
	
	final String URL = "http://correiosapi.apphb.com/cep/";
	
	private void carregarComponentes() {
		etCep = (EditText)findViewById(R.id.etCep);
		btnConsultar = (Button)findViewById(R.id.btnConsultar);
		tvResultado = (TextView)findViewById(R.id.tvResultado);
		pbConsultando = (ProgressBar)findViewById(R.id.pbConsultando);
	} // fecha método
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        carregarComponentes();
        
        btnConsultar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// pega o cep digitado no campo
				String cep = etCep.getText().toString();
				
				// executa a consulta
				new ConsultaAsync().execute(cep);
			}
		});
    } // fechar método


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
    } // fecha método
    
    private class ConsultaAsync extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {
			// passa mensagem de consultando
			publishProgress("Consultando...");
			
			// pega o cep passado
			String cep = params[0];
			
			// cria um cliente HTTP
			HttpClient client = new DefaultHttpClient();
			
			// cria um request
			HttpGet request = new HttpGet();
			
			// declara uma variável de resultado
			String resultado = "";
			
			try {
				// declara a URI a ser chamada
				URI uri = new URI(URL + cep);
				
				// passa a url para o objeto request
				request.setURI(uri);
				
				// declara um leitor com buffer
				BufferedReader reader = null;
				
				// declara um objeto de resposta
				HttpResponse response = null;
				
				// executa a requisição
				response = client.execute(request);
				
				// pega o conteúdo da resposta
				reader = new BufferedReader(
							new InputStreamReader(
								response.getEntity().getContent()
							)
						);
				
				// declara um buffer de string
				StringBuffer buff = new StringBuffer("");
				
				// declara uma variável para ler as linhas
				String line = "";
				
				// enquanto houver linhas no resultado
				while ((line = reader.readLine()) != null) {
					// adiciona a linha no buffer de string
					buff.append(line);
				}
				
				// fecha o leitor
				reader.close();
				
				// passa o resultado
				resultado = buff.toString();
			}
			catch (Exception e) {
				// registra a exceção ocorrida
				Log.e("consultaCEP", e.toString());
			}
			
			// retorna o resultado
			return resultado;
		} // fecha método
		
		@Override
		protected void onProgressUpdate(String... values) {
			// pega o valor
			String valor = values[0];
			
			// atualiza o text view resultado com a msg
			tvResultado.setText(valor);
			
			// mostra a barra de progresso
			pbConsultando.setVisibility(View.VISIBLE);
			
			super.onProgressUpdate(values);
		} // fecha método
		
		@Override
		protected void onPostExecute(String result) {
			// oculta a barra de progresso
			pbConsultando.setVisibility(View.INVISIBLE);
			
			try {
				// cria um objeto JSON com o resultado
				JSONObject json = new JSONObject(result);
				
				// pega o tipo de logradouro
				String tipo = json.getString("tipoDeLogradouro");
				
				// pega o logradouro
				String logradouro = json.getString("logradouro");
				
				// pega o bairro
				String bairro = json.getString("bairro");
				
				// pega a cidade
				String cidade = json.getString("cidade");
				
				// pega o estado
				String estado = json.getString("estado");
				
				// monta a string de resultado
				result = tipo
							+ " " + logradouro
							+ " - " + bairro
							+ " - " + cidade
							+ " - " + estado;
			}
			catch (Exception e) {
				try {
					// cria um objeto JSON com o resultado
					JSONObject json = new JSONObject(result);
					
					// pega a mensagem de erro
					result = json.getString("message");
				}
				catch (Exception e2) {
					Log.e("consultaCEP", e2.toString());
				}
			}
			
			// atualiza o text view com o resultado
			tvResultado.setText(result);
			
			super.onPostExecute(result);
		}
    	
    } // fecha sub classe
    
} // fecha classe
