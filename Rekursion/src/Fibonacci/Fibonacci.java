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
package Fibonacci;

import java.util.Scanner;

/**
 *
 */
public class Fibonacci {

	Scanner scan = new Scanner(System.in);
	private int zahl;


public int zahlEingeben(){
	System.out.println("Fibonacci an Stelle: ");
	zahl = scan.nextInt();

	return zahl;
}

public int fib(int zahl){
	if(zahl <= 2) {
		return 1;
	} else{
		return fib(zahl-1) + fib(zahl-2);
	}
}

	public static void main(String[] args) {
		/*
		 *
		Fibonacci f = new Fibonacci();
		int stelle = f.zahlEingeben();
		System.out.println(f.fib(stelle));

		*/


	}
}
