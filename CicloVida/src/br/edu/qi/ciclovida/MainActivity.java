package br.edu.qi.ciclovida;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

	int restart = 0;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //Log.v("ciclovida", "Create");
        //Log.d("ciclovida", "Create");
        Log.i("ciclovida", "Create");
        //Log.w("ciclovida", "Create");
        //Log.e("ciclovida", "Create");
        
    } // fecha método
    
    @Override
    protected void onPause() {
    	Log.i("ciclovida", "Pause");
    	
    	super.onPause();
    } // fecha método
    
    @Override
    protected void onResume() {
    	Log.i("ciclovida", "Resume");
    	
    	Toast.makeText(
    			getBaseContext(),
    			Integer.toString(restart),
    			Toast.LENGTH_SHORT).show();

    	super.onResume();
    } // fecha método
    
    @Override
    protected void onStop() {
    	Log.i("ciclovida", "Stop");
    	
    	super.onStop();
    } // fecha método
    
    @Override
    protected void onRestart() {
    	Log.i("ciclovida", "Restart");
    	
    	restart++;
    	
    	super.onRestart();
    } // fecha método
    
    @Override
    protected void onDestroy() {
    	Log.i("ciclovida", "Destroy");
    	
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
