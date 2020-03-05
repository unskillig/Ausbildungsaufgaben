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
package TuermeVonHanoi;
/**
 *
 */
public class TuermeVonHanoi {

	public void tuermeVonHanoi(int hoehe, int von, int nach, int ueber){
		if(hoehe > 0){
			tuermeVonHanoi(hoehe-1, von, ueber, nach);
			System.out.println("Von " + von + " nach " + nach);
			tuermeVonHanoi(hoehe-1, ueber, nach, von);
		}
	}

	public static void main(String[] args) {
		TuermeVonHanoi t = new TuermeVonHanoi();
		int hoehe = 3;
		t.tuermeVonHanoi(hoehe, 1, 2, 3);
	}

}


