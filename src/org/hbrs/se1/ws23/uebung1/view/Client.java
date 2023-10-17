package org.hbrs.se1.ws23.uebung1.view;

import org.hbrs.se1.ws23.uebung1.control.GermanTranslator;
import org.hbrs.se1.ws23.uebung1.control.Translator;
import org.hbrs.se1.ws23.uebung1.control.TranslatorFactory;

public class Client extends GermanTranslator implements Translator {

		/*
		 * Methode zur Ausgabe einer Zahl auf der Console
		 * (auch bezeichnet als CLI, Terminal)
		 *
		 */
		 void display( int num ) {
			// In dieser Methode soll die Methode translateNumber
			// mit dem übergegebenen Wert der Variable aNumber
			// aufgerufen werden.
			//
			// Strenge Implementierung gegen das Interface Translator gewuenscht!
		 	String ergebnis;
			GermanTranslator germanTranslator = (GermanTranslator) TranslatorFactory.createGermanTranslator();
			try {
				ergebnis = germanTranslator.translateNumber(num);
			} catch (ArrayIndexOutOfBoundsException e) {
				ergebnis = "Übersetzung der Zahl " + num + " nicht möglich (" + germanTranslator.version + ")";
			}

			System.out.println("Das Ergebnis der Berechnung: " +
					ergebnis );

		 }

	@Override
	public String translateNumber(int number) {
		return super.translateNumber(number);
	}

}





