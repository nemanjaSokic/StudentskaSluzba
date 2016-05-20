package rs.ac.uns.ftn.informatika.dosk.java.vezbe08.primer03.ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import rs.ac.uns.ftn.informatika.dosk.java.vezbe08.primer03.utils.ScannerWrapper;

public class ApplicationUI {

	public static Connection conn;
	
	static {
		try {
			// ucitavanje MySQL drajvera
			Class.forName("com.mysql.jdbc.Driver");

			// konekcija
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/studentskasluzba", 
					"NemanjaToshiba", "rootroot");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws SQLException  {
		int odluka = -1;
		while (odluka != 0) {
			ApplicationUI.ispisiMenu();
			
			System.out.print("opcija:");
			odluka = ScannerWrapper.ocitajCeoBroj();
			
			switch (odluka) {
			case 0:
				System.out.println("Izlaz iz programa");
				break;
			case 1:
				StudentUI.menu();
				break;
			case 2:
				PredmetUI.menu();
				break;
			case 3:
				PohadjaUI.menu();
				break;
			case 4:
				NastavnikUI.menu();
				break;
			case 5:
				PredajeUI.menu();
				break;
			case 6:
				RokUI.menu();
				break;
			case 7:
				IspitnaPrijavaUI.menu();
				break;
			default:
				System.out.println("Nepostojeca komanda");
				break;
			}
		}
	}
	
	// ispis teksta osnovnih opcija
	public static void ispisiMenu() {
		System.out.println("Studentska Sluzba - Osnovne opcije:");
		System.out.println("\tOpcija broj 1 - Rad sa studentima");
		System.out.println("\tOpcija broj 2 - Rad sa predmetima");
		System.out.println("\tOpcija broj 3 - Rad sa pohadjanjem predmeta");
		System.out.println("\tOpcija broj 4 - Rad sa nastavnicima");
		System.out.println("\tOpcija broj 5 - Rad sa predavanjem predmeta");
		System.out.println("\tOpcija broj 6 - Rad sa rokovima ispita");
		System.out.println("\tOpcija broj 7 - Rad sa ispitnim prijavama");
		System.out.println("\t\t ...");
		System.out.println("\tOpcija broj 0 - IZLAZ IZ PROGRAMA");
	}

}
