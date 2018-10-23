/** SchokoladenHandlung. 
Die Klasse verwaltet den kompletten Schokoladenbestand
und besitzt Methoden zur Ausgabe in die Konsole und 
Schnittstellen zum User. Arbeitet mit Objekten der Klasse
Schokolade. 
@author Timo Wein
@version 1.0
*/
package Aufgabe1;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

public class SchokoladenHandlung {
	/** Ein Array - Objekt der Klasse Schokolade. */
	Schokolade[] inventory;	
		
	/** Konstruktor. Erstellen des Arrays und Einlesen der Textdatei.*/				
	public SchokoladenHandlung(){
		inventory = new Schokolade[100];
		readFile();			
	}
	
	/**readFile Methode. Einlesen des Textdokumentes und Erstellen der Schokoladen-Objekte anhand
	 * der gegebenen Daten 
	 * @throws FileNotFoundException. Eingef�gt, falls das Programm so umfunktioniert
	 * wird, dass beliebige .txt eingef�gt werden k�nnen.*/
	public void readFile(){
		try{
			Scanner input = new Scanner(new File("Schokodaten.txt"));
			int i = 0;
			while(input.hasNextLine()){
				String line;
				line = input.nextLine();
				
				//Aufteilen des Strings anhand von Kommata
				String[] temp = line.split(",");
				
				//Calendar Import f�r besseres Handling der Haltbarkeitsdata
				Calendar date = new GregorianCalendar();
				
				//mit dem vorher erstellten Array wird einem Kalenderelement das Datum zugewiesen
				date.set(Integer.parseInt(temp[2]),Integer.parseInt(temp[3])-1, Integer.parseInt(temp[4]));
				
				//�berpr�fung auf Bioschokolade anhand des Arrays 
				boolean bio = false;
				if(temp[5].equals("bio"))
					bio = true;
				
				//Schokolade Objekt wird im inventory Array der SchokoladenHandlung erstellt
				inventory[i] = new Schokolade(temp[0],Double.parseDouble(temp[1]), date, bio);
				i++;
			}
		}
		catch(FileNotFoundException e){
			System.err.println("File not found!");
		}		
	}
	
	/**R�ckgabemethode, um den kompletten Bestand zu erhalten.
	 * @return String s, der den gesamten Bestand enh�lt.*/
	public String getDataAll(){
		String s = "Kompletter Schokoladenbestand:\n\n";
		for(int i=0;i<inventory.length;i++)
			if(inventory[i]!=null){
				s += inventory[i].toString();
				s += "\n_________________________\n";
			}
		return s;
	}
	
	/**R�ckgabemethode, um den Bestand nach Kakaoanteil sortiert zu erhalten.
	 * @param x Programminterner Wert. Wird von main-Methode zur Fallunterscheidung
	 * 			von > und < benutzt.
	 * @param y Der Kakaoanteil.
	 * @return String l, der den nach dem Kriterium sortierten Bestand enth�lt.*/
	public String getDataPerCParts(int x, double y){
		String l;
		if(x==0){
			l = "Schokoladenbestand mit weniger als "+y+"% Kakaogehalt:\n\n";
			for(int i=0;i<inventory.length;i++)
				
				//�berpr�fung Arrayfeld leer und Kakaogehalt < y
				if(inventory[i]!=null && inventory[i].getCPart() < y){
					l += inventory[i].toString();
					l += "\n_________________________\n";
				}				
			return l;
		}			
		else{
			l = "Schokoladenbestand mit mehr als "+y+"% Kakaogehalt\n\n";
			for(int j=0;j<inventory.length;j++)
				
				//�berpr�fung Arrayfeld leer und Kakaogehalt > y
				if(inventory[j]!=null && inventory[j].getCPart() > y){
					l += inventory[j].toString();
					l += "\n_________________________\n";
				}
			return l;
		}
	}
		
	/**R�ckgabemethode, um den Bestand nach den Haltbarkeitsdata sortiert zu erhalten.
	 * @param x Programminterner Wert. Wird von main-Methode zur Fallunterscheidung
	 * 			von Calendar.before() und Calendar.after() benutzt.
	 * @param date Calendar-Objekt, welches gew�nschtes Datum enth�lt.
	 * @return String l, der den nach dem Kriterium sortierten Bestand enth�lt.*/
	public String getDataPerHDate(int x, Calendar date){
		String l;
		if(x==0){
			l = "Schokoladenbestand, der vor dem "+date.get(Calendar.DATE) + "." +
				(date.get(Calendar.MONTH)+1)+ "." + date.get(Calendar.YEAR)+" ablaeuft:\n\n"; 
			for(int i=0;i<inventory.length;i++)
				//Benutzung der Calender - Methode "after", um das Datum zu vergleichen
				if(inventory[i]!=null && date.after(inventory[i].getDateObject())){
					l += inventory[i].toString();
					l += "\n_________________________\n";
				}
			return l;
		}
		else{
			l = "Schokoladenbestand, der nach dem "+date.get(Calendar.DATE) + "." +
				(date.get(Calendar.MONTH)+1)+ "." + date.get(Calendar.YEAR)+" ablaeuft:\n\n";
			for(int i=0;i<inventory.length;i++)
				//Benutzung der Calender - Methode "before", um das Datum zu vergleichen
				if(inventory[i]!=null && date.before(inventory[i].getDateObject())){
					l += inventory[i].toString();
					l += "\n_________________________\n";
				}
			return l;
		}
	}
	
	/**R�ckgabemethode, um den Bestand nach Bioschokolade sortiert zu erhalten.
	 * @param x Programminterner Wert. Wird von main-Methode zur Fallunterscheidung
	 * 			von Bioschokolade verwendet.
	 * @return String l, der den nach dem Kriterium sortierten Bestand enth�lt.*/	 
	public String getDataPerBioC(int x){
		String l;
		if(x == 1){
			l = "Schokoladenbestand, der vollständig biologisch ist:\n\n";
			for(int i=0;i<inventory.length;i++)
				if(inventory[i]!=null && inventory[i].getBioC()){
					l += inventory[i].toString();
					l += "\n_________________________\n";
				}
			return l;
		}
		else{
			l = "Schokoladenbestand, der nicht vollständig biologisch ist:\n\n";
			for(int i=0;i<inventory.length;i++)
				if(inventory[i]!=null && !inventory[i].getBioC()){
					l += inventory[i].toString();
					l += "\n_________________________\n";
				}
			return l;
		}
	}
	
	/**Methode, um SchokoladenHandlung zu erstellen und die Anfragen
	 * des Users zu bearbeiten.
	 * @param args Kommandozeilenparameter.
	 * @throws InputMismatchException. Im ersten Abschnitt keine Zahl eingegeben.
	 * @throws InputMismatchException. In Case3 keine Zahl eingegeben.
	 * @throws ParseException. Eingabe des Datums im falschen Format.
	 * @throws Exception. In Case2 'vor' oder 'nach' inkorrekt geschrieben.
	 * @throws Exception. In Case4 'gro�er' oder 'kleiner' inkorrekt geschrieben.*/	
	public static void main(String[]args){
		SchokoladenHandlung a = new SchokoladenHandlung();
		while(true){
			
			//Einleitung, Anbieten der gegebenen Ausgabem�glichkeiten
			System.out.println("Guten Tag!\nDruecken Sie die folgende Taste zur Ausgabe des Bestandes sortiert nach:\n" +
					"1: Gesamter Bestand\n"+
					"2: Haltbarkeitsdatum\n" +
					"3: Bioschokolade\n"+
					"4: Kakaogehalt\n"+
					"5: Exit");
			
			//�berpr�fung der Usereingabe
			int x = 0;
			Scanner input = new Scanner(System.in);
			while(x>5 || x<1){
				try{				
					x = input.nextInt();
					if(x>5 || x<1)
						System.err.println("Bitte eine Zahl von 1-5 verwenden!");
				}
				catch(InputMismatchException e){
					System.err.println("Bitte eine Zahl von 1-5 verwenden!");
				}
			}
			switch(x){
			
			//Ausgabe aller Daten
			case 1: System.out.println(a.getDataAll()); break;
			
			//Ausgabe nach Haltbarkeitsdatum
			case 2: System.out.println("Bitte geben Sie das Datum in folgendem Format ein:\n"+
					"'vor dd.mm.jjjj' oder 'nach dd.mm.jjjj'");
					String[] temp; 
					Calendar m;
					Scanner input2 = new Scanner(System.in);
					while(true){
						String l = input2.nextLine();
						temp = l.split(" ");
						
						//Vergleich des eingegebenen Textes mit Pr�position und Datum
						try{
							if(!temp[0].equals("vor")&&!temp[0].equals("nach"))
								throw new Exception();
							SimpleDateFormat s = new SimpleDateFormat("dd.MM.yyyy");
							
							/* Simple Datei Format - Import, um den String direkt in den 
							 * Calendar parsen zu k�nnen*/
							s.parse(temp[1]);
							m = s.getCalendar();
							break;
							
						}
						//Abfangen der Exception bei falschen Eingaben
						catch(ParseException e){
							System.err.println("Falsches Format!");
						}
						catch(Exception e2){
							System.err.println("Bitte 'vor' / 'nach' und das Datum korrekt eingeben!");
						}
					}
					if(temp[0].equals("vor"))
						System.out.println(a.getDataPerHDate(0,m));
					else
						System.out.println(a.getDataPerHDate(1,m)); break;
						
			//Ausgabe sortiert nach Bioschokolade
			case 3: System.out.println("Wollen Sie Bio- oder keine Bioschokolade ausgegeben bekommen?"+
									   "\n1: biologisch\n2: nicht biologisch");
					Scanner input3 = new Scanner(System.in);
					int b = 0;
					
					//Abfrage nach 1 oder 2 durch den User
					while(b!=1 && b!=2){
						try{
							b = input3.nextInt();
						}
						catch(InputMismatchException e){
							System.err.println("Dieses ist keine Zahl!");
						}
					}						
					if(b==1)
						System.out.println(a.getDataPerBioC(1));
					else
						System.out.println(a.getDataPerBioC(2));
					break;
					
			//Ausgabe sortiert nach Kakaoanteil		
			case 4: System.out.println("Bitte geben Sie den gewuenschten Kakaoanteil an und ob der Bestand "+
									   "groe�er / kleiner als dieser Wert ausgegeben werden soll!\nFormat: "+
									   "groe�er xx oder kleiner xx");
					String[]temp2;
					double anteil = 0;
					Scanner input4 = new Scanner(System.in);
					while(true){
						String l2 = input4.nextLine();
						temp2 = l2.split(" ");
						
						//Abfrage, ob groe�er oder kleiner gefordert ist
						try{
							if(!temp2[0].equals("groe�er") && !temp2[0].equals("kleiner"))
								//Eigene Exception, falls die W�rter nicht korrekt eingegeben wurden
								throw new Exception();
							anteil = Double.parseDouble(temp2[1]);
							break;
							
						}
						catch(NumberFormatException e){
							System.err.println("Die Zahl wurde falsch eingegeben!");
						}
						catch(Exception e2){
							System.err.println("Bitte 'groe�er' / 'kleiner' und den Anteil korrekt eingeben!");
						}
					}
					if(temp2[0].equals("kleiner"))
						System.out.println(a.getDataPerCParts(0, anteil));
					else
						System.out.println(a.getDataPerCParts(1, anteil));
					break;
			
			//Programmende		
			case 5: System.exit(0);
			}
		}
	}
}
