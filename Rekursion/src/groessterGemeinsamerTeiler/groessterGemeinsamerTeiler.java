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
package groessterGemeinsamerTeiler;
/**
 *
 */
public class groessterGemeinsamerTeiler {


	public int ggt(int x, int y){

		int rest = x % y;
		if(rest == 0) {
			return y;
		} else {
			return ggt(y, rest);
		}

	}


	public static void main(String[] args) {
		groessterGemeinsamerTeiler g = new groessterGemeinsamerTeiler();
		System.out.println("Größter gemeinsamer Teiler: " + g.ggt(100, 10));

	}

}

/*
 *
 * File History
 * ==============
 * $Log$
 */
