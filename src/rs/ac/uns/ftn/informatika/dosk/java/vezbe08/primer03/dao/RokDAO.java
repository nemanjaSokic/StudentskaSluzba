package rs.ac.uns.ftn.informatika.dosk.java.vezbe08.primer03.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.informatika.dosk.java.vezbe08.primer03.model.IspitniRok;

public class RokDAO {

	
	public static IspitniRok getRokById(Connection conn, int id) throws SQLException{
		
		IspitniRok ir = null;
		String s = "SELECT rok_id,naziv,pocetak,kraj FROM ispitni_rokovi WHERE rok_id = " + id + ";";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(s);
		
		while(rs.next()){
			int rok = rs.getInt(1);
			String naziv = rs.getString(2);
			Date pocetak = rs.getDate(3);
			Date kraj = rs.getDate(4);
			
			ir = new IspitniRok(rok, naziv, pocetak, kraj);
		
		}
		st.close();
		rs.close();
		return ir;
	}
	public static List<IspitniRok> getRokAll(Connection conn) throws SQLException{
		List<IspitniRok> rokovi = new ArrayList<IspitniRok>();
		
		String s = "SELECT rok_id,naziv,pocetak,kraj FROM ispitni_rokovi;";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(s);
		
		while(rs.next()){
			int rok = rs.getInt(1);
			String naziv = rs.getString(2);
			Date pocetak = rs.getDate(3);
			Date kraj = rs.getDate(4);
			
			IspitniRok ir = new IspitniRok(rok, naziv, pocetak, kraj);
			rokovi.add(ir);
		}
		st.close();
		rs.close();
		return rokovi;
	}
	
	
	public static boolean addRok(Connection conn, IspitniRok ir) throws SQLException{
		boolean retVal = false;
		
		String s = "INSERT IGNORE INTO ispitni_rokovi(naziv,pocetak,kraj) values(?,?,?);";
		PreparedStatement ps = conn.prepareStatement(s);
		
		ps.setString(1, ir.getNaziv());
		ps.setDate(2, ir.getPocetak());
		ps.setDate(3, ir.getKraj());
		
		if(ps.executeUpdate() == 1){
			System.out.println("Ispitni rok je uspešno dodat.");
			retVal = true;
		}else{
			System.out.println("Greška pri dodavanju studenta.");
		}
		ps.close();
		return retVal;
	}
	
	public static boolean deleteRok(Connection conn, int id) throws SQLException{
		boolean retVal = false;
		
		String s = "DELETE FROM ispitni_rokovi WHERE rok_id = " + id + ";";
		Statement st = conn.createStatement();

		
		if(st.executeUpdate(s) == 1){
			System.out.println("Uspešno ste obrisali.");
			retVal = true;
		}else{
			System.out.println("Greška pri prisanju.");
		}
		st.close();
		return retVal;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
