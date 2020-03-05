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
public class Fibonacci implements Runnable {

	private char c;

	@Override
	public void run() {
		fibonacci(0, 1, 0);
	}

	public Fibonacci(char c) {
		this.c = c;
	}

	public void fibonacci(long i, long j, long grenze) {
		long summe = i + j;
		grenze++;
		System.out.println(c + ": " + summe);
		if (grenze < 70) {
			fibonacci(summe, i, grenze);
		}
		return;
	}

	/**
	 * @return the c
	 */
	public char getC() {
		return c;
	}

	/**
	 * @param c the c to set
	 */
	public void setC(char c) {
		this.c = c;
	}

}

/*
 *
 * File History
 * ==============
 * $Log$
 */
