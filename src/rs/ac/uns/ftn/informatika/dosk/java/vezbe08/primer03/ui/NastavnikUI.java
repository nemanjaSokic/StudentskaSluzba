package rs.ac.uns.ftn.informatika.dosk.java.vezbe08.primer03.ui;

import java.sql.SQLException;
import java.util.List;

import rs.ac.uns.ftn.informatika.dosk.java.vezbe08.primer03.dao.NastavnikDAO;
import rs.ac.uns.ftn.informatika.dosk.java.vezbe08.primer03.model.Nastavnik;
import rs.ac.uns.ftn.informatika.dosk.java.vezbe08.primer03.utils.ScannerWrapper;

public class NastavnikUI {

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
				ispisiSveNastavnike();
				break;
			case 2:
				unosNovogNastavnika();
				break;
			case 3:
				izmenaPodatakaONastavniku();
				break;
			case 4:
				brisanjePodatakaONastavniku();
				break;
			default:
				System.out.println("Nepostojeca komanda");
				break;
			}
		}
	}

	private static void brisanjePodatakaONastavniku() throws SQLException {
		System.out.println("Unesite id nastavnika za brisanje: ");
		int id = ScannerWrapper.ocitajCeoBroj();
		Nastavnik n = NastavnikDAO.getNastavnikById(ApplicationUI.conn, id);
		if(n == null){
			System.out.println("Nastavnik ne postoji u sistemu.");
				return ;
		}
		NastavnikDAO.delete(ApplicationUI.conn, id);
		
	}

	private static void izmenaPodatakaONastavniku() throws SQLException {
		System.out.println("Unesite id nastavnika za izmenu: ");
		int id = ScannerWrapper.ocitajCeoBroj();
		Nastavnik n = NastavnikDAO.getNastavnikById(ApplicationUI.conn, id);
		if(n == null){
			System.out.println("Nastavnik ne postoji u sistemu.");
				return ;
		}
		System.out.println("Unesite ime nastavnika: ");
		String ime = ScannerWrapper.ocitajTekst();
		System.out.println("Unesite prezime: ");
		String prezime = ScannerWrapper.ocitajTekst();
		System.out.println("Unesite zvanje profesora: ");
		String zvanje = ScannerWrapper.ocitajTekst();
		Nastavnik noviN = new Nastavnik(id,ime,prezime,zvanje);
		NastavnikDAO.update(ApplicationUI.conn, noviN);
	}

	private static void unosNovogNastavnika() throws SQLException {
		
		System.out.println("Unesite ime profesora: ");
		String ime = ScannerWrapper.ocitajTekst();
		System.out.println("Unesite prezime: ");
		String prezime = ScannerWrapper.ocitajTekst();
		System.out.println("Unesite zvanje profesora: ");
		String zvanje = ScannerWrapper.ocitajTekst();
		Nastavnik n = new Nastavnik(0,ime,prezime,zvanje);
		
		
		NastavnikDAO.add(ApplicationUI.conn, n);
	}

	private static void ispisiMenu() {
		
			System.out.println("Rad sa nastavnicima - opcije:");
			System.out.println("\tOpcija broj 1 - ispis svih nastavnika ");
			System.out.println("\tOpcija broj 2 - unos novog nastavnika");
			System.out.println("\tOpcija broj 3 - izmena nastavnika");
			System.out.println("\tOpcija broj 4 - brisanje nastavnika");
			System.out.println("\t\t ...");
			System.out.println("\tOpcija broj 0 - IZLAZ");
		
		
	}

	private static void ispisiSveNastavnike() throws SQLException {
		List<Nastavnik> nastavnici = NastavnikDAO.getAllNastavnik(ApplicationUI.conn);
		for (int i = 0; i < nastavnici.size(); i++) {
			System.out.println(nastavnici.get(i));
		}
		
	}
	
}
