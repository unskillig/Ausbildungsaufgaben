import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
public class StreamOperations {

	public static void main(String[] args) {

		List<String> liste = Arrays.asList("Hamster", "Vogel", "Hund", "Katze", "Fisch");

		// .filter filtert die Strings aus der Liste heraus, die 4 oder weniger Zeichen haben
		// .map ändert die Strings der Liste
		// .sorted sortiert die Liste
		// .forEach beendet die Streamoperationen und gibt jedes Element der Liste aus
		liste.stream().filter(elem -> elem.length() > 4).map(String::toUpperCase).sorted().forEach(System.out::println);

		// gibt jedes Element der Liste aus
		liste.stream().forEach(System.out::println);

		// .collect.toList() fügt die Elemente der neuen Liste hinzu
		List<String> ueberarbeiteteListe = liste.stream().map(String::toLowerCase).filter(elem -> elem.length() > 4).collect(Collectors.toList());
		ueberarbeiteteListe.stream().forEach(System.out::println);

		List<String> zuTrimmendeListe = Arrays.asList("Hamster ", "Vogel", "Hund  ", "Katze", "Fisch");
		zuTrimmendeListe.stream().forEach(System.out::println);
		zuTrimmendeListe.stream().map(String::trim).forEach(System.out::println);

	}
}

/*
 *
 * File History
 * ==============
 * $Log$
 */
