import java.sql.Date;




import org.codehaus.jackson.annotate.JsonProperty;

import org.codehaus.jackson.annotate.JsonPropertyOrder;


@JsonPropertyOrder({"nom", "datedecreation", "theme", "prix","vop"})
public abstract class oeuvre  {
	
	
	
	private String nom;
	private Date datecreation;
	private String theme;
	private int prix;
	private boolean vop;
	  @JsonProperty("nom")

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Date getDatecreation() {
		return datecreation;
	}
	  @JsonProperty("datedecreation")

	public void setDatecreation(Date datecreation) {
		this.datecreation = datecreation;
	}
	  @JsonProperty("theme")

	public String getTheme() {
		return theme;
	}
	
	public void setTheme(String theme) {
		this.theme = theme;
	}
	  @JsonProperty("prix")

	public int getPrix() {
		return prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
	  @JsonProperty("vop")

	public boolean isVop() {
		return vop;
	}
	public void setVop(boolean vop) {
		this.vop = vop;
	}
	public oeuvre(String nom, Date datecreation, String theme, int prix, boolean vop) {
		super();
		this.nom = nom;
		this.datecreation = datecreation;
		this.theme = theme;
		this.prix = prix;
		this.vop = vop;
	}
	public oeuvre() {
		super();
	}
	
	

}
