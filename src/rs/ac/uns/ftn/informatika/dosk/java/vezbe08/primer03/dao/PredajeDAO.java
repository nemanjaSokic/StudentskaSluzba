package rs.ac.uns.ftn.informatika.dosk.java.vezbe08.primer03.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.informatika.dosk.java.vezbe08.primer03.model.Nastavnik;
import rs.ac.uns.ftn.informatika.dosk.java.vezbe08.primer03.model.Predmet;

public class PredajeDAO {

	public static List<Predmet> getPredmetByNastavnikId(Connection conn,int id) throws SQLException{
		List<Predmet> predmetiNastavnika = new ArrayList<Predmet>();
		String s = "select n.nastavnik_id,n.ime,n.prezime,n.zvanje,pr.predmet_id,pr.naziv from nastavnici n left join predaje p on p.nastavnik_id =  n.nastavnik_id join predmeti pr on pr.predmet_id = p.predmet_id;";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(s);
		while(rs.next()){
			int predId = rs.getInt(5);
			Predmet pr = PredmetDAO.getPredmetById(conn, predId);
			int nasId = rs.getInt(1);
			if(id == nasId){
				predmetiNastavnika.add(pr);
			}
		}
		
		return predmetiNastavnika;
	}
	
	public static List<Nastavnik> getNastavnikByPredmtId(Connection conn, int id) throws SQLException{
		List<Nastavnik> nastavniciPredmeta = new ArrayList<Nastavnik>();
//		String s = "select nastavnici.nastavnik_id,nastavnici.ime,nastavnici.prezime,nastavnici.zvanje,predmeti.predmet_id, predmeti.naziv" + 
// " from nastavnici join predaje on nastavnici.nastavnik_id = predaje.nastavnik_id join predmeti on predmeti.predmet_id = predaje.predmet_id;";
		String s1 = "SELECT * FROM predaje;";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(s1);
		while(rs.next()){
			int idNas = rs.getInt(1);
			int idPred = rs.getInt(2);
			
			Nastavnik n = NastavnikDAO.getNastavnikById(conn, idNas);
			
			if(id == idPred){
				nastavniciPredmeta.add(n);
			}
			
			
		}
		
		return nastavniciPredmeta;
	}
	
	public static void dodavanjeNastavnikaNaPredmet(Connection conn,int idNas,int idPred) throws SQLException{
		
		String s = "INSERT IGNORE INTO predaje(nastavnik_id,predmet_id) values(?,?);";
		PreparedStatement ps = conn.prepareStatement(s);
		
		ps.setInt(1, idNas);
		ps.setInt(2, idPred);
		
		if(ps.executeUpdate() == 1){
			System.out.println("Dodavanje nastavnika uspešno");
		}else{
			System.out.println("Greška pri dodavanju."
					+ "\nNastavnik je veæ angažovan na tom predmetu.");
		}
	}
	
	public static void deleteNastavnikaNaPredmet(Connection conn, int idNas, int idPre) throws SQLException{
		
		String s = "DELETE FROM predaje WHERE nastavnik_id = ? and predmet_id = ?;";
		PreparedStatement ps = conn.prepareStatement(s);
		
		

		
		ps.setInt(1, idNas);
		ps.setInt(2, idPre);
		
		if(ps.executeUpdate() == 1){
			System.out.println("Uspešno obrisan nastavnik sa predmeta.");
		}else{
			System.out.println("Greška pri brisanju.\nNastavnik nije na navedenom predmetu.");
		}
		
		
	}
	
	
	
	
	
	
	
}
