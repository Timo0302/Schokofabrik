/** Schokolade.
Die Klasse beinhaltet alle Parameter und Methoden der
Schokoladen-Objekte.
@author Timo Wein
@version 1.0
*/
package Aufgabe1;

import java.util.Calendar;

public class Schokolade {
	//Objektvariablen
	
	/**Der Name der Schokolade.*/
	private String name;
	/**Der Kakaoanteil der Schokolade.*/
	private double cPart;
	/**Die Kennzeichnung, ob Bioschokolade oder nicht.*/
	private boolean bioC;	
	/**Calendar Objekt für das Haltbarkeitsdatum der Schokolade.*/
	private Calendar date;

	/** Konstruktor.
	 * @param name Der Name des Schokoladenobjekts.
	 * @param cPart Der Kakaoanteil des Schokoladenobjekts.
	 * @param date Das Haltbarkeitsdatum des Schokoladenobjekts.
	 * @param bioC Bioschokolade ja/nein.*/
	public Schokolade(String name, double cPart, Calendar date, boolean bioC){
		this.name = name;
		this.cPart = cPart;
		this.date = date;
		this.bioC = bioC;		
	}	
	
	//Objektmethoden
	
	/**Ein Getter für den Namen.
	 * @return Der Name der Schokolade.*/
	public String getName(){
		return this.name;
	}	
	
	/**Ein Getter für den Kakaoanteil.
	 * @return Der Kakaoanteil der Schokolade.*/
	public double getCPart(){
		return this.cPart;
	}
	
	/**Methode, um das Datum als verwertbaren String zu erhalten.
	 * @return Haltbarkeitsdatum als String.*/
	public String getDateAsString(){
		return this.date.get(Calendar.DATE) + "." + (this.date.get(Calendar.MONTH)+1) + "." +
			   this.date.get(Calendar.YEAR);
	}
	/**Ein Getter für das Calendar-Objekt.
	 * @return Das Calendar-Objekt.*/
	public Calendar getDateObject(){
		return this.date;
	}
	
	/**Ein Getter für den Tag.
	 * @return Der Tag des Haltbarkeitsdatums.*/
	public int getDateDay(){
		return this.date.get(Calendar.DATE);
	}
	
	/**Ein Getter für den Monat.
	 * @return Der Monat des Haltbarkeitsdatums.*/
	public int getDateMonth(){
		return this.date.get(Calendar.MONTH)+1;
	}
	
	/**Ein Getter für das Jahr.
	 * @return Das Jahr des Haltbarkeitsdatums.*/
	public int getDateYear(){
		return this.date.get(Calendar.YEAR);
	}
	
	/**Ein Getter für Bioschokolade ja/nein.
	 * @return Der boolsche Wert, ob Bioschokolade.*/
	public boolean getBioC(){
		return this.bioC;
	}
	
	/**Methode, um alle Objektvariablen als String formatiert zu erhalten.
	 * @return String, der ordentlich untereinander die Objektvariablen aufführt.*/
	public String toString(){
		return "Name: " + this.name + "\nKakaogehalt: " 
			   + this.cPart + "\nBioschokolade: " + this.bioC + "\nHaltbarkeitsdatum: " + this.getDateAsString();
	}
}


