package br.edu.qi.menus;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class ListaActivity extends Activity {

	ListView lvPessoas;
	ImageView ivIcone;
	
	final int MENU_EDITAR = 1;
	final int MENU_EXCLUIR = 2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista);
		
		lvPessoas = (ListView)findViewById(R.id.lvPessoas);
		ivIcone = (ImageView)findViewById(R.id.ivIcone);
		
		// registra que a lista terá um menu de contexto
		registerForContextMenu(lvPessoas);
		registerForContextMenu(ivIcone);
	} // fecha método
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		
		if (v.getId() == R.id.lvPessoas) {
			menu.add(1, MENU_EDITAR, 100, R.string.editar);
			menu.add(1, MENU_EXCLUIR, 200, R.string.excluir);
		}
		else if (v.getId() == R.id.ivIcone) {
			getMenuInflater().inflate(R.menu.icone, menu);
		}
		
		super.onCreateContextMenu(menu, v, menuInfo);
	} // fecha método
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		
		int id = item.getItemId();
		
		switch (id) {
			case MENU_EDITAR:
				Toast.makeText(
						getBaseContext(), 
						"Editando...", 
						Toast.LENGTH_SHORT).show();
				break;
			
			case MENU_EXCLUIR:
				Toast.makeText(
						getBaseContext(),
						"Excluindo...",
						Toast.LENGTH_SHORT).show();
				break;
				
			case R.id.ver_icone:
				Toast.makeText(
						getBaseContext(),
						"Ver imagem...", 
						Toast.LENGTH_SHORT).show();
				break;
				
			case R.id.ocultar_icone:
				ivIcone.setVisibility(View.INVISIBLE);
				break;
		}
		
		return super.onContextItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.lista, menu);
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
