package collioni.douglas.cadastrolivros;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class Db4oHelper {
	
	private String dir;
	private final String DB4O_BANCO = "Livros.db4o";
	protected ObjectContainer db;
	
	public Db4oHelper(String dir) {
		this.dir = dir;
	}
	
	public void abrirBanco() {
		String banco = dir + DB4O_BANCO;
		db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), banco);
	}
	
	public void fecharBanco() {
		if (db != null) {
			db.close();
		}
	}
	
	public ObjectContainer db() {
		return db;
	}
}
