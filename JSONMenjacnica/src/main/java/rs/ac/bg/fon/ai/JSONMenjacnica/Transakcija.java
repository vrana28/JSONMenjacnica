package rs.ac.bg.fon.ai.JSONMenjacnica;
import java.util.Date;

public class Transakcija {

	public String izvornaValuta;
	public String krajnjaValuta;
	public double pocetniIznos;
	public double konvertovaniIznos;
	public Date datumTranskacije;
	
	public String getIzvornaValuta() {
		return izvornaValuta;
	}
	public void setIzvornaValuta(String izvornaValuta) {
		this.izvornaValuta = izvornaValuta;
	}
	public String getKrajnjaValuta() {
		return krajnjaValuta;
	}
	public void setKrajnjaValuta(String krajnjaValuta) {
		this.krajnjaValuta = krajnjaValuta;
	}
	public double getPocetniIznos() {
		return pocetniIznos;
	}
	public void setPocetniIznos(double pocetniIznos) {
		this.pocetniIznos = pocetniIznos;
	}
	public double getKonvertovaniIznos() {
		return konvertovaniIznos;
	}
	public void setKonvertovaniIznos(double konvertovaniIznos) {
		this.konvertovaniIznos = konvertovaniIznos;
	}
		
	public Date getDatumTranskacije() {
		return datumTranskacije;
	}
	public void setDatumTranskacije(Date datumTranskacije) {
		this.datumTranskacije = datumTranskacije;
	}
	
	@Override
	public String toString() {
		return "Transakcija [izvornaValuta=" + izvornaValuta + ", krajnjaValuta=" + krajnjaValuta + ", pocetniIznos="
				+ pocetniIznos + ", konvertovaniIznos=" + konvertovaniIznos + ", datumTranskacije=" + datumTranskacije
				+ "]";
	}
	
	
	
}
