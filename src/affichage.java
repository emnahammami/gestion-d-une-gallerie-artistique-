import java.sql.Date;

public class affichage extends Thread {
	
private oeuvre o;

	public oeuvre getO() {
	return o;
}




public void setO(oeuvre o) {
	this.o = o;
}




	public affichage(oeuvre o) {
	super();
	this.o = o;
}
	public void run() {
		System.out.println(o.getPrix());
		
		
	}
	

}
