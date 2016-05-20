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

public class NastavnikDAO {

	public static Nastavnik getNastavnikById(Connection conn, int id) throws SQLException{
		Nastavnik n = null;
		String query = "SELECT ime,prezime,zvanje FROM nastavnici WHERE nastavnik_id = " + id + ";";
		Statement st = conn.createStatement();
		ResultSet rset = st.executeQuery(query);
		while(rset.next()){
			String ime = rset.getString(1);
			String prezime = rset.getString(2);
			String zvanje = rset.getString(3);
			
			
			n = new Nastavnik(id,ime,prezime,zvanje);
			
		}
		st.close();rset.close();
		
		
		return n;
	}
	
	public static List<Nastavnik> getAllNastavnik(Connection conn) throws SQLException{
		List<Nastavnik> retVal = new ArrayList<Nastavnik>();
		String query = "SELECT n.nastavnik_id,n.ime,n.prezime,n.zvanje,pr.predmet_id,pr.naziv FROM nastavnici n " + 
						"LEFT JOIN predaje p on n.nastavnik_id = p.nastavnik_id LEFT JOIN " + 
						"predmeti pr on pr.predmet_id = p.predmet_id;";
		
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		while(rs.next()){
			int nasId = rs.getInt(1);
			String nasIme = rs.getString(2);
			String prezime = rs.getString(3);
			String zvanje = rs.getString(4);
			int predmetId = rs.getInt(5);
			
			Predmet p = PredmetDAO.getPredmetById(conn, predmetId);
			
			
			Nastavnik noviNastavnik = new Nastavnik(nasId,nasIme,prezime,zvanje);
			Nastavnik n = proveraNastavnikaUListi(retVal, noviNastavnik);
			
				n.getPredmetiPredavaca().add(p);
		
			
			
		}
		st.close();
		rs.close();
		return retVal;
	}

	private static Nastavnik proveraNastavnikaUListi(List<Nastavnik> retVal, Nastavnik n) {
		
		for(Nastavnik nastavnik : retVal){
			if (nastavnik.equals(n)){
				return nastavnik;
			}
		}
		retVal.add(n);
		
		return n;
	}

	
	public static boolean add(Connection conn, Nastavnik nastavnik) throws SQLException{
		boolean retVal = false;
		String query = "INSERT IGNORE INTO nastavnici(nastavnik_id,ime,prezime,zvanje) values(?,?,?,?);";
		PreparedStatement pst = conn.prepareStatement(query);
		pst.setInt(1, nastavnik.getIdNast());
		pst.setString(2, nastavnik.getImeNast());
		pst.setString(3, nastavnik.getPrezimeNast());
		pst.setString(4, nastavnik.getZvanjeNast());
		
		if(pst.executeUpdate() == 1){
			System.out.println("Nastavnik je uspešno dodat.");
			retVal = true;
		}else{
			System.out.println("Greška pri dodavanju nastavnika.");
		}
		pst.close();
		return retVal;
	}
	
	public static boolean update(Connection conn, Nastavnik n) throws SQLException{
		boolean retVal = false;
		String query = "update nastavnici set ime = ?, prezime = ?, zvanje = ? where nastavnik_id = " + n.getIdNast();
		PreparedStatement prst = conn.prepareStatement(query);
		prst.setString(1, n.getImeNast());
		prst.setString(2, n.getPrezimeNast());
		prst.setString(3, n.getZvanjeNast());
		if(prst.executeUpdate() == 1){
			System.out.println("Nastavnik uspešno izmenjen.");
			retVal = true;
		}else{
			System.out.println("Greška pri izmeni.");
		}
		prst.close();
		return retVal;
	}
	
	
	public static boolean delete(Connection conn, int id) throws SQLException{
		boolean retVal = false;
		String s = "DELETE FROM nastavnici WHERE nastavnik_id = " + id +";";
		Statement st = conn.createStatement();
		if(st.executeUpdate(s) == 1){
			System.out.println("Uspešno ste obrisali nastavnika.");
			retVal = true;
		}else{
			System.out.println("Greška pri brisanju nastavnika.");
		}
		st.close();
		return retVal;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
