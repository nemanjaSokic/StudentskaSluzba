package rs.ac.uns.ftn.informatika.dosk.java.vezbe08.primer03.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.informatika.dosk.java.vezbe08.primer03.model.IspitnaPrijava;
import rs.ac.uns.ftn.informatika.dosk.java.vezbe08.primer03.model.IspitniRok;
import rs.ac.uns.ftn.informatika.dosk.java.vezbe08.primer03.model.Predmet;
import rs.ac.uns.ftn.informatika.dosk.java.vezbe08.primer03.model.Student;

public class IspitnaPrijavaDAO {

	public static List<IspitnaPrijava> prikazSvihPrijava(Connection conn) throws SQLException{
		
		List<IspitnaPrijava> prijave = new ArrayList<IspitnaPrijava>();
		
		String s = "select * from ispitne_prijave;";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(s);
		
		while(rs.next()){
			int studId = rs.getInt(1);
			int predId = rs.getInt(2);
			int rokId = rs.getInt(3);
			int teorija = rs.getInt(4);
			int zadaci = rs.getInt(5);
			
			Predmet predmet = PredmetDAO.getPredmetById(conn, predId);
			Student student = StudentDAO.getStudentById(conn, studId);
			IspitniRok rok = RokDAO.getRokById(conn, rokId);
			
			IspitnaPrijava ip = new IspitnaPrijava(predmet, student, rok, teorija, zadaci);
			prijave.add(ip);
		}
		
		st.close();
		rs.close();
		return prijave;
	}
	public static IspitnaPrijava prikazSvihPrijava(Connection conn,int idP,int idS,int idR) throws SQLException{
		
		IspitnaPrijava ip = null;
		
		String s = "select * from ispitne_prijave WHERE (student_id,predmet_id,rok_id) = (" + idS +"," + idP + "," + idR + ");";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(s);
		
		while(rs.next()){
			int studId = rs.getInt(1);
			int predId = rs.getInt(2);
			int rokId = rs.getInt(3);
			int teorija = rs.getInt(4);
			int zadaci = rs.getInt(5);
			
			Predmet predmet = PredmetDAO.getPredmetById(conn, predId);
			Student student = StudentDAO.getStudentById(conn, studId);
			IspitniRok rok = RokDAO.getRokById(conn, rokId);
			
			ip = new IspitnaPrijava(predmet, student, rok, teorija, zadaci);
			
		}
		
		st.close();
		rs.close();
		return ip;
	}
	
	
	public static boolean dodajPrijavu(Connection conn, IspitnaPrijava ip) throws SQLException{
		boolean retVal = false;
		
		String s = "INSERT IGNORE INTO ispitne_prijave(student_id,predmet_id,rok_id,teorija,zadaci) values(?,?,?,?,?);";
		PreparedStatement pr = conn.prepareStatement(s);
		
		pr.setInt(1, ip.getStudent().getId());
		pr.setInt(2, ip.getPredmet().getId());
		pr.setInt(3, ip.getRok().getId());
		pr.setInt(4, ip.getTeorija());
		pr.setInt(5, ip.getZadaci());
		
		if(pr.executeUpdate() == 1){
			System.out.println("Uspešno ste dodali ispitnu prijavu.");
			retVal = true;
		}else{
			System.out.println("Greška pri dodavanju.");
		}
		pr.close();
		return retVal;
	}
	
	public static boolean deletePrijavu(Connection conn,int idP,int idS,int idR) throws SQLException{
		boolean ret = false;
		
		String s = "DELETE FROM ispitne_prijave WHERE(student_id,predmet_id,rok_id,teorija,zadaci) values(?,?,?);";
		PreparedStatement pr = conn.prepareStatement(s);
		
		pr.setInt(1, idS);
		pr.setInt(2, idP);
		pr.setInt(3, idR);
		
		if(pr.executeUpdate() == 1){
			System.out.println("Ispitna prijava je uspešno obrisana.");
			ret = true;
		}else{
			System.out.println("Greška prilikom brisanja.");
		}
		pr.close();
		return ret;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
