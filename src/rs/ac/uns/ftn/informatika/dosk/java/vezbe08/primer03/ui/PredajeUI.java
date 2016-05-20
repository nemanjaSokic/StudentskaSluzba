package rs.ac.uns.ftn.informatika.dosk.java.vezbe08.primer03.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.informatika.dosk.java.vezbe08.primer03.dao.NastavnikDAO;
import rs.ac.uns.ftn.informatika.dosk.java.vezbe08.primer03.dao.PredajeDAO;
import rs.ac.uns.ftn.informatika.dosk.java.vezbe08.primer03.dao.PredmetDAO;
import rs.ac.uns.ftn.informatika.dosk.java.vezbe08.primer03.model.Nastavnik;
import rs.ac.uns.ftn.informatika.dosk.java.vezbe08.primer03.model.Predmet;
import rs.ac.uns.ftn.informatika.dosk.java.vezbe08.primer03.utils.ScannerWrapper;

public class PredajeUI {

	public static void menu() throws SQLException {
		int odluka = -1;
		while (odluka != 0) {
			ispisiMenu();
			System.out.print("opcija:");
			odluka = ScannerWrapper.ocitajCeoBroj();
			switch (odluka) {
			case 0:
				System.out.println("Izlaz");
				break;
			case 1:
				ispisiPredmeteZaNastavnike();
				break;
			case 2:
				ispisiNastavnikeZaPredmet();
				break;
			case 3:
				dodajNastavnikaNaPredmet();
				break;
			case 4:
				ukloniNastavnikaSaPredmeta();
				break;
			
			default:
				System.out.println("Nepostojeca komanda");
				break;
			}
		}
	}

	private static void ukloniNastavnikaSaPredmeta() throws SQLException {
		System.out.println("Unesite id nastavnika:");
		int id = ScannerWrapper.ocitajCeoBroj();
		Nastavnik n = NastavnikDAO.getNastavnikById(ApplicationUI.conn, id);
		if(n == null){
			System.out.println("Nastavnik ne postoji u evidenciji.");
			return;
		}
		System.out.println("Unesite id predmeta: ");
		int idP = ScannerWrapper.ocitajCeoBroj();
		Predmet p = PredmetDAO.getPredmetById(ApplicationUI.conn, idP);
		if(p == null){
			System.out.println("Predmet ne postoji.");
			return;
		}
		PredajeDAO.deleteNastavnikaNaPredmet(ApplicationUI.conn, id, idP);
	}

	private static void dodajNastavnikaNaPredmet() throws SQLException {
		System.out.println("Unesite id nastavnika: ");
		int idNas = ScannerWrapper.ocitajCeoBroj();
		
		Nastavnik n = NastavnikDAO.getNastavnikById(ApplicationUI.conn, idNas);
		if(n == null){
			System.out.println("Nastavnik ne postoji.");
			return;
		}
		System.out.println("Unesite id predmeta: ");
		int idPre = ScannerWrapper.ocitajCeoBroj();
		Predmet p = PredmetDAO.getPredmetById(ApplicationUI.conn, idPre);
		if(p == null){
			System.out.println("Predmet ne postoji.");
			return;
		}
		PredajeDAO.dodavanjeNastavnikaNaPredmet(ApplicationUI.conn, idNas, idPre);
		
	}

	private static void ispisiNastavnikeZaPredmet() throws SQLException {
		List<Nastavnik> sviNastavnici = new ArrayList<Nastavnik>();
		System.out.println("Upisite id predmeta: ");
		int id = ScannerWrapper.ocitajCeoBroj();
		Predmet p = PredmetDAO.getPredmetById(ApplicationUI.conn, id);
		if(p == null){
			System.out.println("Predmet sa šifrom " + id + " ne postoji.");
			return;
		}
		sviNastavnici = PredajeDAO.getNastavnikByPredmtId(ApplicationUI.conn, id);
		if(sviNastavnici.isEmpty()){
			System.out.println("Ne postoje nastavnici na predmetu " + p.getNaziv());
		}
		System.out.println("Nastavnici koji su na predmetu " + p.getNaziv() + ": ");
		for (int i = 0; i < sviNastavnici.size(); i++) {
			System.out.println(sviNastavnici.get(i).getImeNast() + " " + sviNastavnici.get(i).getPrezimeNast() + ", " + sviNastavnici.get(i).getZvanjeNast());
		}
	}

	private static void ispisiPredmeteZaNastavnike() throws SQLException {
		List<Predmet> sviPredmeti = new ArrayList<Predmet>();
		System.out.println("Upiši id nastastavnika: ");
		int id = ScannerWrapper.ocitajCeoBroj();
		Nastavnik nas = NastavnikDAO.getNastavnikById(ApplicationUI.conn, id);
		if(nas == null){
			System.out.println("Nastavnik sa id-jem " + id + " ne postoji u evidenciji.");
			return;
		}
		sviPredmeti = PredajeDAO.getPredmetByNastavnikId(ApplicationUI.conn, id);
		if(sviPredmeti.isEmpty()){
			System.out.println("Nastavnik ne predaje nijedan predmet.");
		}
		System.out.println("Nastavnik " + nas.getImeNast() + " " + nas.getPrezimeNast() + " je angažovan na sledeæim redmetima: ");
		
		for (int i = 0; i < sviPredmeti.size(); i++) {
			System.out.println(sviPredmeti.get(i));
		}
		
	}

	private static void ispisiMenu() {
		System.out.println("Rad sa predmetima nastavnika - opcije:");
		System.out.println("\tOpcija broj 1 - predmeti koje nastavnik predaje");
		System.out.println("\tOpcija broj 2 - nastavnici koji predaju predmet");
		System.out.println("\tOpcija broj 3 - dodavanje nastavnika na predmet");
		System.out.println("\tOpcija broj 4 - uklanjanje nastavnika sa predmeta");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija broj 0 - IZLAZ");
		
	}
		
	

}
