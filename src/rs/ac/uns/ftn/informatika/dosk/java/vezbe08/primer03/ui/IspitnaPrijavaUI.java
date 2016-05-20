package rs.ac.uns.ftn.informatika.dosk.java.vezbe08.primer03.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.informatika.dosk.java.vezbe08.primer03.dao.IspitnaPrijavaDAO;
import rs.ac.uns.ftn.informatika.dosk.java.vezbe08.primer03.dao.PredmetDAO;
import rs.ac.uns.ftn.informatika.dosk.java.vezbe08.primer03.dao.RokDAO;
import rs.ac.uns.ftn.informatika.dosk.java.vezbe08.primer03.dao.StudentDAO;
import rs.ac.uns.ftn.informatika.dosk.java.vezbe08.primer03.model.IspitnaPrijava;
import rs.ac.uns.ftn.informatika.dosk.java.vezbe08.primer03.model.IspitniRok;
import rs.ac.uns.ftn.informatika.dosk.java.vezbe08.primer03.model.Predmet;
import rs.ac.uns.ftn.informatika.dosk.java.vezbe08.primer03.model.Student;
import rs.ac.uns.ftn.informatika.dosk.java.vezbe08.primer03.utils.ScannerWrapper;

public class IspitnaPrijavaUI {

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
				ispisiSveIspitnePrijave();
				break;
			case 2:
				unosNoveIspitnePrijave();
				break;
			case 3:
				brisanjePodatakaOIspitnojPrijavi();
				break;
			default:
				System.out.println("Nepostojeca komanda");
				break;
			}
		}
	}

	private static void brisanjePodatakaOIspitnojPrijavi() {
		
		
	}

	private static void unosNoveIspitnePrijave() throws SQLException {
		System.out.println("Unesite id predmeta: ");
		int idP = ScannerWrapper.ocitajCeoBroj();
		Predmet pr = PredmetDAO.getPredmetById(ApplicationUI.conn, idP);
		if(pr == null){
			System.out.println("Predmet ne postoji.");
			return;
		}
		
		System.out.println("Unesite id studenta: ");
		int idS = ScannerWrapper.ocitajCeoBroj();
		Student st = StudentDAO.getStudentById(ApplicationUI.conn, idS);
		if(st == null){
			System.out.println("Student ne postoji.");
			return;
		}
		
		System.out.println("Unesite id roka: ");
		int idR = ScannerWrapper.ocitajCeoBroj();
		IspitniRok ir = RokDAO.getRokById(ApplicationUI.conn, idR);
		if(ir == null){
			System.out.println("Ispitni rok ne postoji.");
			return;
		}
		System.out.println("Unesite broj poena na teoriji: ");
		int teorija = ScannerWrapper.ocitajCeoBroj();
		System.out.println("Unesite broj poena na zadacima: ");
		int zadaci = ScannerWrapper.ocitajCeoBroj();
		IspitnaPrijava ip = new IspitnaPrijava(pr,st,ir,teorija,zadaci);
		IspitnaPrijavaDAO.dodajPrijavu(ApplicationUI.conn, ip);
	}

	private static void ispisiSveIspitnePrijave() throws SQLException {
		List<IspitnaPrijava> svePrijave = new ArrayList<IspitnaPrijava>();
		svePrijave = IspitnaPrijavaDAO.prikazSvihPrijava(ApplicationUI.conn);
		for (int i = 0; i < svePrijave.size(); i++) {
			System.out.println(svePrijave.get(i));
		}
	}

	private static void ispisiMenu() {
		System.out.println("Rad sa ispitnim prijavama- opcije:");
		System.out.println("\tOpcija broj 1 - ispis svih ispitnih prijava");
		System.out.println("\tOpcija broj 2 - unos nove ispitne prijave");
		System.out.println("\tOpcija broj 3 - brisanje ispitne prijave");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija broj 0 - IZLAZ");

		
	
		
	}

}
