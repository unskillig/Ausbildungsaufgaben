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
package Fakultaet;
/**
 *
 */
public class Fakultaet {



	public int fakultaet(int zahl){
		if(zahl == 0) {
			return 1;
		} else {
			return fakultaet(zahl-1) * zahl;
		}
	}





	public static void main(String[] args) {

		Fakultaet f = new Fakultaet();
		System.out.println("Ergebnis: " + f.fakultaet(5));

	}

}

/*
 *
 * File History
 * ==============
 * $Log$
 */
