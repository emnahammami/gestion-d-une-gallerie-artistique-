import java.sql.Date;
import java.util.ArrayList;

public class thread extends Thread {
	
private oeuvre o;

	
	public oeuvre getO() {
	return o;
}


public void setO(oeuvre o) {
	this.o = o;
}


	public thread(oeuvre o) {
	super();
	this.o = o;
}


	public void run() {
		System.out.println(o.getNom());
		
		
	}
	
	

}
