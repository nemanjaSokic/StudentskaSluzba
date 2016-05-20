package rs.ac.uns.ftn.informatika.dosk.java.vezbe08.primer03.ui;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import rs.ac.uns.ftn.informatika.dosk.java.vezbe08.primer03.dao.RokDAO;
import rs.ac.uns.ftn.informatika.dosk.java.vezbe08.primer03.model.IspitniRok;
import rs.ac.uns.ftn.informatika.dosk.java.vezbe08.primer03.utils.ScannerWrapper;

public class RokUI {


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
				ispisiSveRokove();
				break;
			case 2:
				unosNovogRoka();
				break;
			case 3:
				brisanjePodatakaORoku();
				break;
			default:
				System.out.println("Nepostojeca komanda");
				break;
			}
		}
	}

	private static void brisanjePodatakaORoku() throws SQLException {
		System.out.println("Unesite id ispitnog roka: ");
		int id = ScannerWrapper.ocitajCeoBroj();
		IspitniRok ir = RokDAO.getRokById(ApplicationUI.conn, id);
		if(ir == null){
			System.out.println("Ispitni rok ne postoji pod id-jem " + id);
		}
		RokDAO.deleteRok(ApplicationUI.conn, id);
	}

	private static void unosNovogRoka() throws SQLException {
		try{
		System.out.println("Unesite naziv ispitnog roka: ");
		String naziv = ScannerWrapper.ocitajTekst();
		System.out.println("Unesite poèetak ispitnog roka (yyy-mm-dd): ");
		
		Date pocetak = Date.valueOf(ScannerWrapper.ocitajTekst());
		System.out.println("Unesite kraj ispitnog roka (yyy-mm-dd): ");
		Date kraj = Date.valueOf(ScannerWrapper.ocitajTekst());
		IspitniRok ir = new IspitniRok(0,naziv,pocetak,kraj);
		RokDAO.addRok(ApplicationUI.conn, ir);
		}catch(IllegalArgumentException e){
			System.out.println("Nepravilan format datuma.");
		}
		
	}

	private static void ispisiSveRokove() throws SQLException {
		List<IspitniRok> sviRokovi = new ArrayList<IspitniRok>();
		
		sviRokovi = RokDAO.getRokAll(ApplicationUI.conn);
		for (int i = 0; i < sviRokovi.size(); i++) {
			System.out.println(sviRokovi.get(i));
		}
	}

	private static void ispisiMenu() {
		System.out.println("Rad sa rokovima - opcije:");
		System.out.println("\tOpcija broj 1 - ispis svih rokova");
		System.out.println("\tOpcija broj 2 - unos novog roka");
		System.out.println("\tOpcija broj 3 - brisanje rokova");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija broj 0 - IZLAZ");
		
	
		
	}

}
