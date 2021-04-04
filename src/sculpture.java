import java.sql.Date;
import org.codehaus.jackson.annotate.JsonProperty;

import org.codehaus.jackson.annotate.JsonPropertyOrder;
@JsonPropertyOrder({"volume","poids","matiereutilise"})
public class sculpture extends oeuvre {
	
	private int volume;
	private int poids;
	private String matiereutilise;
	 @JsonProperty("volume")

	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	 @JsonProperty("poids")

	public int getPoids() {
		return poids;
	}
	public void setPoids(int poids) {
		this.poids = poids;
	}
	 @JsonProperty("matiereutilise")

	public String getMatiereutilise() {
		return matiereutilise;
	}
	public void setMatiereutilise(String matiereutilise) {
		this.matiereutilise = matiereutilise;
	}
	public sculpture(String nom, Date datecreation, String theme, int prix, boolean vop, int volume, int poids,
			String matiereutilise) {
		super(nom, datecreation, theme, prix, vop);
		this.volume = volume;
		this.poids = poids;
		this.matiereutilise = matiereutilise;
	}
	
	
	
	
	
	
	
	

}
