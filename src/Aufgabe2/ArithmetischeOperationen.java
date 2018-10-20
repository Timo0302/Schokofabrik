/** ArithmetischeOperationen.
 * Die Klasse berechnet die Wurzel aus einer und die Potenz
 * aus zwei gegebenen Zahlen, die vom User jeweils eingegeben werden.
@author Timo Wein
@version 1.0
*/
package Aufgabe2;
import java.util.*;

public class ArithmetischeOperationen {
	
	/**Division zweier ganzer Zahlen mit Ergebnis
	 * als Gleitkommazahl.
	 * @param a Dividend.
	 * @param b Divisor.
	 * @throws ArithmeticException. Bei Division durch 0.
	 * @return Ergebnis der Division als Gleitkommazahl.*/
	public static double divide(int a,int  b){
		if(b == 0)
			throw new ArithmeticException();
		return (double)a/b;
	}
	
	/**Wurzelziehen einer Zahl.
	 * @param zahl Eingegebene Zahl, aus der die Wurzel gezogen wird.
	 * @throws ArithmeticException. Bei Zahl < 0.
	 * @return Ergebnis des Wurzelziehens.*/
	public static double sqrt(int zahl){
		if(zahl < 0)
			throw new ArithmeticException();
		return Math.sqrt(zahl);
	}
	/**main Methode als Schnittstelle zwischen Programm und User.
	 * @param args Kommandozeilenparameter
	 * @exception ArithmeticException. Bei Division durch 0. Geworfen durch Objektmethode divide.
	 * @exception ArithmeticException. Bei Wurzel aus einer negativen Zahl. Geworfen
	 * 			  durch Objektmethode sqrt. 
	 * @throws InputMismatchException. Wenn keine 2 Zahlen für Methode divide eingegeben wurden.
	 * @throws InputMismatchException. Keine Zahl zum Wurzelziehen eingegeben.
	 * @throws Exception. Weder Wurzel noch divide eingegeben.*/
	public static void main(String[]args){
		Scanner input = new Scanner(System.in);
		System.out.println("Bitte geben Sie 'Wurzel' oder 'divide' für die jeweilige Operation ein");
		String s = "";
		while(!s.equals("divide") && !s.equals("Wurzel")){		
			s = input.nextLine();
			try{
				if(!s.equals("divide") && !s.equals("Wurzel"))
					throw new Exception();
			}
			catch(Exception e){
				System.err.println("Bitte divide oder Wurzel eingeben!");
			}
		}
		if(s.equals("divide")){
			System.out.println("Bitte geben Sie 2 Zahlen ein. Es wird die 1. durch die 2. Zahl dividiert!");
			try{
				int x = input.nextInt();
				int y = input.nextInt();
				try{
					System.out.println(divide(x,y));
				}
				catch(ArithmeticException e){
					System.err.println("Division durch 0 ist nicht definiert!");
				}
			}
			/*Meiner Meinung benötigt man hier keine Exception, um die Eingabe von nur einer Zahl
			  abzufangen, da der Scanner so lange wartet, bis die zweite eingegeben ist. Sollte diese falsch sein
			  fängt das immer noch der untere Catch-Block ab.*/
			catch(InputMismatchException e){
				System.err.println("Keine 2 Zahlen gefunden!");
			}
		}		
		else{
			System.out.println("Bitte geben Sie die gewuenschte Zahl ein!");
			try{
				int x = input.nextInt();
				try{
					System.out.println(sqrt(x));					
				}
				catch(ArithmeticException e){
					System.err.println("Die Wurzel aus einer negativen Zahl ist in R nicht definiert!");
				}			
			}
			catch(InputMismatchException e){
				System.err.println("Sie haben keine Zahl eingegeben!");
			}
		}
	}
}
		
			
				

