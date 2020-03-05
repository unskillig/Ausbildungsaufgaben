import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/*
 * (c) 2010 by dwpbank Deutsche WertpapierService Bank AG
 */
/**
 * Actual Version
 * ==============
 * @version $Revision: 1.1 $
 * @author $Author: akrumeich $
 * For a detailed history of this file see bottom !
 */
/**
 *
 */
public class Derby {

	public static Connection getConnection() throws Exception {

		// Standard-Embedded-Driver
		final String driver = "org.apache.derby.jdbc.EmbeddedDriver";

		//jdbc:derby:database-name(Ordnername im Programmordner):attribute=value(create=true --> db wird erstellt, sofern sie nicht existiert)
		final String url = "jdbc:derby:derbyDB;create=true";
		try {
			Class.forName(driver).newInstance();

			Connection conn = DriverManager.getConnection(url);
			System.out.println("Connected");
			System.out.println();
			return conn;
		} catch (Exception e) {
			System.out.println(e);
		}

		return null;
	}



	public void createTablePersonen() throws Exception {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String createTable = "CREATE TABLE personen(" + "PERSONEN_ID INT NOT NULL, "
				+ "PERSONEN_FIRSTNAME VARCHAR(50) NOT NULL, " + "PERSONEN_LASTNAME VARCHAR(50) NOT NULL, "
				+ "PERSONEN_PROFESSION VARCHAR(50) NOT NULL, " + "PERSONEN_INCOME INT NOT NULL, " + "ABTEILUNGS_ID INT NOT NULL, " + "PRIMARY KEY (PERSONEN_ID), "
				+ "FOREIGN KEY(ABTEILUNGS_ID) REFERENCES abteilungen (ABTEILUNGS_ID)" +")";

		try {
			dbConnection = getConnection();
			preparedStatement = dbConnection.prepareStatement(createTable);
			preparedStatement.executeUpdate();
			preparedStatement.close();

			System.out.println("Table \'personen\' is created!");

		} catch (Exception e) {
			System.out.println(e);
		}
	}


	public void insertIntoPersonenTabelle() throws Exception {

		try {


			Connection con = getConnection();
			PreparedStatement stmt1 = con.prepareStatement(
					"INSERT INTO personen " + "VALUES(1, 'Lennart', 'Gundelbacher', 'Rechtsanwalt', 4330, 2)");
			stmt1.executeUpdate();
			stmt1.close();

			PreparedStatement stmt2 = con.prepareStatement(
					"INSERT INTO personen " + "VALUES(2, 'Jan-Nikolas', 'Völker', 'Fachinformatiker', 740, 3)");
			stmt2.executeUpdate();
			stmt2.close();

			PreparedStatement stmt3 = con
					.prepareStatement("INSERT INTO personen " + "VALUES(3, 'Maren', 'Hackstein', 'Erzieherin', 2540, 4)");
			stmt3.executeUpdate();
			stmt3.close();

			PreparedStatement stmt4 = con.prepareStatement(
					"INSERT INTO personen " + "VALUES(4, 'Jule', 'van Treeck', 'Designer', 4500, 1)");
			stmt4.executeUpdate();
			stmt4.close();

			PreparedStatement stmt5 = con.prepareStatement(
					"INSERT INTO personen " + "VALUES(5, 'Shari', 'Jansen', 'Fachinformatiker', 730, 3)");
			stmt5.executeUpdate();
			stmt5.close();

			PreparedStatement stmt6 = con
					.prepareStatement("INSERT INTO personen " + "VALUES(6, 'Oliver', 'Brings', 'Fachinformatiker', 650, 3)");
			stmt6.executeUpdate();
			stmt6.close();




		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void createTableAbteilungen()throws Exception {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String createTable = "CREATE TABLE abteilungen(" + "ABTEILUNGS_ID INT NOT NULL, "
		+ "ABTEILUNGS_BEZ VARCHAR(20) NOT NULL, "
		+ "PRIMARY KEY (ABTEILUNGS_ID)" + ")";

		try {
			dbConnection = getConnection();
			preparedStatement = dbConnection.prepareStatement(createTable);
			preparedStatement.executeUpdate();
			preparedStatement.close();

			System.out.println("Table \'abteilungen\' is created!");

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void insertIntoAbteilungsTabelle() throws Exception {

		try {
			Connection con = getConnection();
			PreparedStatement stmt1 = con
					.prepareStatement("INSERT INTO abteilungen " + "VALUES(1, 'Design')");
			stmt1.executeUpdate();
			stmt1.close();

			PreparedStatement stmt2 = con
					.prepareStatement("INSERT INTO abteilungen " + "VALUES(2, 'Recht')");
			stmt2.executeUpdate();
			stmt2.close();


			PreparedStatement stmt3 = con
					.prepareStatement("INSERT INTO abteilungen " + "VALUES(3, 'IT')");
			stmt3.executeUpdate();
			stmt3.close();


			PreparedStatement stmt4 = con
					.prepareStatement("INSERT INTO abteilungen " + "VALUES(4, 'Erziehung')");
			stmt4.executeUpdate();
			stmt4.close();


		} catch (Exception e) {
			System.out.println(e);
		}
	}


	public void dropTable(String name) throws Exception {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String dropTable = "DROP TABLE " + name;

		try {
			dbConnection = getConnection();
			preparedStatement = dbConnection.prepareStatement(dropTable);
			preparedStatement.executeUpdate();
			preparedStatement.close();

			System.out.println("Table " + name + " was dropped!");

		} catch (Exception e) {
			System.out.println(e);
		}
	}


	public void ausgabePersonenTable() throws Exception {
		try {
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT * FROM personen JOIN abteilungen ON personen.ABTEILUNGS_ID = abteilungen.ABTEILUNGS_ID");
			ResultSet result = statement.executeQuery();
			System.out.println("PersonenListe");
			System.out.println("--------------------------------------------------------------------------");
			System.out.printf("%-8s%-14s%-14s%-20s%-11s%-5s%n", "PersID", "Nachname", "Vorname", "Beruf", "Einkommen", "AbtID");
			while (result.next()) {
				System.out.printf("%-8s", result.getString("PERSONEN_ID"));
				System.out.printf("%-14s",result.getString("PERSONEN_LASTNAME"));
				System.out.printf("%-14s",result.getString("PERSONEN_FIRSTNAME"));
				System.out.printf("%-20s",result.getString("PERSONEN_PROFESSION"));
				System.out.printf("%-11s",result.getString("PERSONEN_INCOME"));
				System.out.printf("%-5s",result.getString("ABTEILUNGS_BEZ"));
				System.out.println();

			}

			statement.close();
			result.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void ausgabePersonenTableSortiertNachName() throws Exception {
		try {
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT * FROM personen JOIN abteilungen ON personen.ABTEILUNGS_ID = abteilungen.ABTEILUNGS_ID ORDER BY PERSONEN_LASTNAME ASC");
			ResultSet result = statement.executeQuery();

			System.out.println("PersonenListe sortiert nach Nachname");
			System.out.println("--------------------------------------------------------------------------");
			System.out.printf("%-8s%-14s%-14s%-20s%-11s%-5s%n", "PersID", "Nachname", "Vorname", "Beruf", "Einkommen", "AbtID");
			while (result.next()) {
				System.out.printf("%-8s", result.getString("PERSONEN_ID"));
				System.out.printf("%-14s",result.getString("PERSONEN_LASTNAME"));
				System.out.printf("%-14s",result.getString("PERSONEN_FIRSTNAME"));
				System.out.printf("%-20s",result.getString("PERSONEN_PROFESSION"));
				System.out.printf("%-11s",result.getString("PERSONEN_INCOME"));
				System.out.printf("%-5s",result.getString("ABTEILUNGS_BEZ"));
				System.out.println();

			}

			statement.close();
			result.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void ausgabePersonenTableSortiertNachEinkommen() throws Exception {
		try {
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT * FROM personen JOIN abteilungen ON personen.ABTEILUNGS_ID = abteilungen.ABTEILUNGS_ID ORDER BY PERSONEN_INCOME DESC");
			ResultSet result = statement.executeQuery();

			System.out.println("PersonenListe sortiert nach Einkommen");
			System.out.println("--------------------------------------------------------------------------");
			System.out.printf("%-8s%-14s%-14s%-20s%-11s%-5s%n", "PersID", "Nachname", "Vorname", "Beruf", "Einkommen", "AbtID");
			while (result.next()) {
				System.out.printf("%-8s", result.getString("PERSONEN_ID"));
				System.out.printf("%-14s",result.getString("PERSONEN_LASTNAME"));
				System.out.printf("%-14s",result.getString("PERSONEN_FIRSTNAME"));
				System.out.printf("%-20s",result.getString("PERSONEN_PROFESSION"));
				System.out.printf("%-11s",result.getString("PERSONEN_INCOME"));
				System.out.printf("%-5s",result.getString("ABTEILUNGS_BEZ"));
				System.out.println();

			}

			statement.close();
			result.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}


	public void ausgabeJoinAbteilungsID(String id) throws Exception {
		try {
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT * FROM personen JOIN abteilungen ON personen.ABTEILUNGS_ID = abteilungen.ABTEILUNGS_ID WHERE abteilungen.ABTEILUNGS_ID = " + id);
			ResultSet result = statement.executeQuery();

			System.out.println("Inner JOIN AbteilungsID");
			System.out.println("--------------------------------------------------------------------------");
			System.out.printf("%-20s%-20s%n", "Nachname", "AbteilungsBez");
			while (result.next()) {
				System.out.printf("%-20s", result.getString("PERSONEN_LASTNAME"));
				System.out.printf("%-20s", result.getString("ABTEILUNGS_BEZ"));
				System.out.println();
			}

			statement.close();
			result.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void ausgabeEinkommenHoeherAls(String grenze) throws Exception {
		try {
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT * FROM personen WHERE PERSONEN_INCOME >= " + grenze + " ORDER BY PERSONEN_INCOME DESC");
			ResultSet result = statement.executeQuery();

			System.out.println("Ausgabe Einkommen höher als " + grenze + " €");
			System.out.println("--------------------------------------------------------------------------");
			System.out.printf("%-20s%-20s%n", "Nachname", "Einkommen");
			while (result.next()) {
				System.out.printf("%-20s", result.getString("PERSONEN_LASTNAME"));
				System.out.printf("%s%s", result.getString("PERSONEN_INCOME"), " €");
				System.out.println();
			}

			statement.close();
			result.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void ausgabeEinkommenHoeherAlsUndBestimmterBeruf(String grenze, String abteilungsID) throws Exception {
		try {
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT * FROM personen WHERE PERSONEN_INCOME >= "
					+ "" + grenze + " AND ABTEILUNGS_ID = " + abteilungsID + " ORDER BY PERSONEN_INCOME DESC");
			ResultSet result = statement.executeQuery();

			System.out.println("Ausgagbe Einkommen höher als " + grenze + " € und AbteilungsID = " + abteilungsID);
			System.out.println("--------------------------------------------------------------------------");
			System.out.printf("%-20s%-20s%n", "Nachname", "Einkommen");
			while (result.next()) {
				System.out.printf("%-20s", result.getString("PERSONEN_LASTNAME"));
				System.out.printf("%s%s", result.getString("PERSONEN_INCOME"), " €");
				System.out.println();
			}

			statement.close();
			result.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void ausgabeAnzahlPersonen() throws Exception {
		try {
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT * FROM personen");
			ResultSet result = statement.executeQuery();
			System.out.println("Ausgagbe Anzahl Personen in Personen-Tabelle");
			System.out.println("--------------------------------------------------------------------------");
			int zaehler = 0;
			while (result.next()) {
				zaehler++;
			}

			statement.close();
			result.close();
			System.out.println(zaehler);

		} catch (Exception e) {
			System.out.println(e);
		}
	}


	public void ausgabeCount() throws Exception {
		try {
			int zaehler = 0;
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT COUNT(*) FROM personen");
			ResultSet set = statement.executeQuery();
			System.out.println("Ausgabe COUNT von Personen-Tabelle");
			System.out.println("--------------------------------------------------------------------------");
			while(set.next()){
				zaehler++;
			}
			System.out.println(zaehler);
			statement.close();
			set.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

//	public void initDB() {
	// nicht nötig, da bereits in getConnection() die Datenbank erstellt wird
//		Statement stat = null;
//		Connection dbConnection = null;
//		try {
//			dbConnection = getConnection();
//			stat = dbConnection.createStatement();
//			stat.executeUpdate("CREATE DATABASE DB1;");
//
//			System.out.println("DB \"DB1\" is created!");
//
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//	}

//	public void createTablePersAbt() throws Exception {
//		Connection dbConnection = null;
//		PreparedStatement preparedStatement = null;
//		String createTable = "CREATE TABLE persAbt(" + "USER_ID INT NOT NULL, " + "ABTEILUNGS_ID INT NOT NULL, "
//		+ "PRIMARY KEY (USER_ID, ABTEILUNGS_ID), " + "FOREIGN KEY(USER_ID) REFERENCES personen (USER_ID), " + "FOREIGN KEY(ABTEILUNGS_ID) REFERENCES abteilungen (ABTEILUNGS_ID)" +")";
//
//		try {
//			dbConnection = getConnection();
//			preparedStatement = dbConnection.prepareStatement(createTable);
//			preparedStatement.executeUpdate();
//
//			System.out.println("Table \'persAbt\' is created!");
//
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//	}

//	public void insertIntoPersAbtTabelle() throws Exception {
//
//		try {
//			Connection con = getConnection();
//
//			//Variante 1: Einzelanweisungen
//			PreparedStatement stmt1 = con
//					.prepareStatement("INSERT INTO persAbt " + "VALUES(1, 1)");
//			stmt1.executeUpdate();
//
//			PreparedStatement stmt2 = con
//					.prepareStatement("INSERT INTO persAbt " + "VALUES(2, 3)");
//			stmt2.executeUpdate();
//
//
//			PreparedStatement stmt3 = con
//					.prepareStatement("INSERT INTO persAbt " + "VALUES(3, 3)");
//			stmt3.executeUpdate();
//
//			PreparedStatement stmt4 = con
//					.prepareStatement("INSERT INTO persAbt " + "VALUES(4, 2)");
//			stmt4.executeUpdate();
//
//			PreparedStatement stmt5 = con
//					.prepareStatement("INSERT INTO persAbt " + "VALUES(5, 3)");
//			stmt5.executeUpdate();
//
//
//			PreparedStatement stmt6 = con
//					.prepareStatement("INSERT INTO persAbt " + "VALUES(6, 4)");
//			stmt6.executeUpdate();
//
//			//Variante 2: Batch-Anweisungen
////			stmt1.addBatch("INSERT INTO persAbt " + "VALUES(1, 1)");
////			stmt1.addBatch("INSERT INTO persAbt " + "VALUES(2, 3)");
////			stmt1.addBatch("INSERT INTO persAbt " + "VALUES(3, 3)");
////			stmt1.addBatch("INSERT INTO persAbt " + "VALUES(4, 2)");
////			stmt1.addBatch("INSERT INTO persAbt " + "VALUES(5, 3)");
////			stmt1.addBatch("INSERT INTO persAbt " + "VALUES(6, 4)");
//
//			//int[] sqlReturnCodes = stmt1.executeBatch();
//
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//	}


	public static void main(String[] args) throws Exception {
		Derby d = new Derby();
		d.ausgabePersonenTable();
		d.ausgabePersonenTableSortiertNachName();
		d.ausgabePersonenTableSortiertNachEinkommen();
		d.ausgabeJoinAbteilungsID("3");
		d.ausgabeEinkommenHoeherAls("1000");
		d.ausgabeAnzahlPersonen();
		d.ausgabeEinkommenHoeherAlsUndBestimmterBeruf("600", "3");
		d.ausgabeCount();


	}

}

/*
 *
 * File History
 * ==============
 * $Log$
 */
