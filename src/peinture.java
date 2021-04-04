import java.sql.Date;
import org.codehaus.jackson.annotate.JsonProperty;

import org.codehaus.jackson.annotate.JsonPropertyOrder;


@JsonPropertyOrder({"taille"})
public class peinture extends oeuvre {
	private int taille;
	 @JsonProperty("taille")
	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public peinture(String nom, Date datecreation, String theme, int prix, boolean vop, int taille) {
		super(nom, datecreation, theme, prix, vop);
		this.taille = taille;
	}


}
