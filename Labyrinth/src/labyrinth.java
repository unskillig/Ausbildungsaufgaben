import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
public class labyrinth {

	int zaehlerSchritte = 0;									// globale Variable

	private char[][] parseLabyrinthFile() {						// Labyrinth-Feld einlesen und in charArray parsen

		String zeile = null;
		List<String> inhalte = new ArrayList<String>();

		try (FileReader fr = new FileReader("labyrinth2.txt")) {
			BufferedReader br = new BufferedReader(fr);

			while ((zeile = br.readLine()) != null) {

				//System.out.println(zeile);
				inhalte.add(zeile);
			}

			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		int lengthOfLine = inhalte.get(0).length();
		int depthOfLine = inhalte.size();

		char[][] labyrinth = new char[depthOfLine][lengthOfLine];

		for (int i = 0; i < depthOfLine; i++) {
			labyrinth[i] = inhalte.get(i).toCharArray();
		}

		return labyrinth;
	}

	private void LabyrinthDrucken(char[][] lab) {			// Labyrinth drucken

		for (int i = 0; i < lab.length; i++) {

			for (int j = 0; j < lab[0].length; j++) {
				System.out.print(lab[i][j]);
			}

			System.out.println();
		}
		System.out.println();
	}

	private int getXStart(char[][] lab) {					// X-Koordinate vom Startpunkt holen
		int start = 0;

		for (int i = 0; i < lab.length; i++) {

			for (int j = 0; j < lab[0].length; j++) {
				if (lab[i][j] == 'S') {
					start = j;
				}
			}

		}
		return start;
	}

	private int getYStart(char[][] lab) {					// Y-Koordinate vom Startpunkt holen
		int start = 0;

		for (int i = 0; i < lab.length; i++) {

			for (int j = 0; j < lab[0].length; j++) {
				if (lab[i][j] == 'S') {
					start = i;
				}
			}

		}
		return start;
	}

	public void wegPruefungRekursiv(char[][] lab, int i, int j) {

		if (lab[i][j] != 'S' && lab[i][j] != 'E') {					// nur ausführen, wenn lab[i][j] nicht S und nicht E ist
			lab[i][j] = 'X';
		}

		zaehlerSchritte++;

//		if (zaehlerSchritte % 25 == 0) {
//			LabyrinthDrucken(lab);
//		}

		int right = j + 1;
		int left = j - 1;
		int up = i + 1;
		int down = i - 1;

		if (lab[i][j] == 'E') {										// im Erfolgsfall ausführen

			System.out.println("Geschafft!");
			LabyrinthDrucken(lab);

		} else {

			if (i != 0) {											// prüfen, ob überhaupt noch nach oben gegangen werden darf
				if (lab[up][j] == ' ' || lab[up][j] == 'E') {

					wegPruefungRekursiv(lab, up, j);
				}
			}

			if (j != lab[0].length - 1) {							// prüfen, ob überhaupt noch nach rechts gegangen werden darf
				if (lab[i][right] == ' ' || lab[i][right] == 'E') {

					wegPruefungRekursiv(lab, i, right);
				}
			}

			if (i != lab.length - 1) {								// prüfen, ob überhaupt noch nach unten gegangen werden darf
				if (lab[down][j] == ' ' || lab[down][j] == 'E') {

					wegPruefungRekursiv(lab, down, j);
				}

			}

			if (j != 0) {											// prüfen, ob überhaupt noch nach links gegangen werden darf
				if (lab[i][left] == ' ' || lab[i][left] == 'E') {

					wegPruefungRekursiv(lab, i, left);
				}
			}

		}

		lab[i][j] = ' ';											// wenn nicht gelaufen werden kann: backtracking --> Xe werden durch Leerzeichen ersetzt, bis die letzte mögliche Alternativroute erreicht ist

//		if (zaehlerSchritte % 25 == 0) {
//			LabyrinthDrucken(lab);
//		}

	}

	public static void main(String[] args) {
		int startX, startY;
		labyrinth laby = new labyrinth();

		char[][] lab = laby.parseLabyrinthFile();

		laby.LabyrinthDrucken(lab);
		startX = laby.getXStart(lab);
		startY = laby.getYStart(lab);
		System.out.println("Startkoordinaten: " + startX + " " + startY);
		laby.wegPruefungRekursiv(lab, startY, startX);

	}

}
