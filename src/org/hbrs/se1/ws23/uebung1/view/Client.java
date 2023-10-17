package org.hbrs.se1.ws23.uebung1.view;

import org.hbrs.se1.ws23.uebung1.control.Translator;
import org.hbrs.se1.ws23.uebung1.control.TranslatorFactory;

public class Client implements Translator {

		/*
		 * Methode zur Ausgabe einer Zahl auf der Console
		 * (auch bezeichnet als CLI, Terminal)
		 *
		 */
		Translator germanTranslator = TranslatorFactory.createGermanTranslator();
		public String display(int num) {
			// In dieser Methode soll die Methode translateNumber
			// mit dem übergegebenen Wert der Variable aNumber
			// aufgerufen werden.
			//
			// Strenge Implementierung gegen das Interface Translator gewuenscht!
		 	Client client = new Client();
	 		String value = client.translateNumber(num);
			System.out.println(value);
	 		return value;

		 }

		@Override
		public String translateNumber(int number) {
			return germanTranslator.translateNumber(number);
		}

}





