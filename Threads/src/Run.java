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
public class Run {

	public static void main(String[] args) {
		Thread t1 = new Thread(new Fibonacci('A'));
		Thread t2 = new Thread(new Fibonacci('B'));
		Thread t3 = new Thread(new Fibonacci('C'));
		Thread t4 = new Thread(new Fibonacci('D'));
		Thread t5 = new Thread(new Fibonacci('E'));
		Thread t6 = new Thread(new Fibonacci('F'));
		Thread t7 = new Thread(new Fibonacci('G'));

		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();

	}

}

/*
 *
 * File History
 * ==============
 * $Log$
 */
