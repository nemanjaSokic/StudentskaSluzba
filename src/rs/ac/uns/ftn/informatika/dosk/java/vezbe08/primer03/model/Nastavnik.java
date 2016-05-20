package rs.ac.uns.ftn.informatika.dosk.java.vezbe08.primer03.model;

import java.util.ArrayList;
import java.util.List;

public class Nastavnik {

	protected int idNast;
	protected String imeNast;
	protected String prezimeNast;
	protected String zvanjeNast;
	protected List<Predmet> predmetiPredavaca = new ArrayList<Predmet>();
	
	
	
	

	public Nastavnik(int idNast, String imeNast, String prezimeNast, String zvanjeNast) {
		
		this.idNast = idNast;
		this.imeNast = imeNast;
		this.prezimeNast = prezimeNast;
		this.zvanjeNast = zvanjeNast;
		this.predmetiPredavaca = new ArrayList<Predmet>();
	}
	
	

	public Nastavnik(int idNast, String imeNast, String prezimeNast, String zvanjeNast,
			List<Predmet> predmetiPredavaca) {
		
		this.idNast = idNast;
		this.imeNast = imeNast;
		this.prezimeNast = prezimeNast;
		this.zvanjeNast = zvanjeNast;
		this.predmetiPredavaca = predmetiPredavaca;
	}



	public Nastavnik() {
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idNast;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nastavnik other = (Nastavnik) obj;
		if (idNast != other.idNast)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Nastavnik " + idNast + ", " + imeNast + " " + prezimeNast + " - "
				+ zvanjeNast + " preda je na predmetu/ima: \n" + predmetiPredavaca + ".";
	}

	public int getIdNast() {
		return idNast;
	}

	public void setIdNast(int idNast) {
		this.idNast = idNast;
	}

	public String getImeNast() {
		return imeNast;
	}

	public void setImeNast(String imeNast) {
		this.imeNast = imeNast;
	}

	public String getPrezimeNast() {
		return prezimeNast;
	}

	public void setPrezimeNast(String prezimeNast) {
		this.prezimeNast = prezimeNast;
	}

	public String getZvanjeNast() {
		return zvanjeNast;
	}

	public void setZvanjeNast(String zvanjeNast) {
		this.zvanjeNast = zvanjeNast;
	}
	
	
	public List<Predmet> getPredmetiPredavaca() {
		return predmetiPredavaca;
	}
	
	public void setPredmetiPredavaca(ArrayList<Predmet> predmetiPredavaca) {
		this.predmetiPredavaca = predmetiPredavaca;
	}
	
	
}
