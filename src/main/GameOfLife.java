package main;

import java.util.Random;
import java.util.Scanner;

public class GameOfLife {
	
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int werte [] = eingabe(), egZahl;
		boolean zellen[][] = new boolean[werte[0]][werte[1]];
		zellen = verteiler(werte[2], zellen);
		
		while(true)
		{
			ausgabe(zellen);
			egZahl = godMode();
			
			if(egZahl == 1)
			{
				zellen = evolution(zellen);
				
			}
			else if(egZahl == 2)
			{
				zellen = changeLife(zellen);
			}
			else if(egZahl == 3)
			{
				break;
			}
		}
		sc.close();
	}
	
	public static boolean[][] verteiler(int iLZellen, boolean[][] boZellen)
	{
		Random r = new Random();
		int irSpalte, irZeile;
		
		if (boZellen.length * boZellen[0].length < iLZellen) {
			iLZellen = boZellen.length * boZellen[0].length;
		}
		
		for(int i = 0; i < iLZellen; i++)
		{
			while(true)
			{
				irSpalte = r.nextInt(boZellen.length);
				irZeile = r.nextInt(boZellen[0].length);
				
				 if(boZellen[irSpalte][irZeile] == false)
				 {
					 boZellen[irSpalte][irZeile] = true;
					 break;
				 }
			}
		}
		return boZellen;
	}
	// Methode in der die Evolution fortgesetzt wird
	public static boolean [] [] evolution(boolean zellen[][]) {
		//Klonen des Zellen arrays
		boolean altZellen [] [] = new boolean [zellen.length] [];
		for (int i = 0;i < zellen.length;i++) {
			altZellen[i] = zellen[i].clone();
		}
		
		//Zellenarray durchlaufen
		for (int i = 0;i < zellen.length;i++) {
			for (int n = 0;n < zellen[0].length;n++) {
				zellen[i][n] = checkLife(altZellen, i, n);
			}
		}
		return zellen;
	}
	
	// Methode in der geprüft wird welchen Status eine Zelle hat
	public static boolean checkLife(boolean zellen[][], int x, int y) {
		byte leben = 0;
		// Durchlaufen der umstehenden Zellen
		for (int i = x - 1;i <= x + 1;i++) {
			for (int n = y - 1;n <= y + 1;n++) {
				// Wenn nach einer Zelle gepüft wird, die nicht existiert wird es hier abgefangen
				try {
					// Wenn die Zelle lebt, wird die Anzahl der lebenden Zellen um 1 erhöht
					//								V		V	Hier wird dafür gesorgt das die Zelle sich nicht selbst mitzaehlt
					if (zellen[i][n] == true && (i != x || n != y)) {
						leben++;
					}
				} catch(Exception ex) { }
			}
		}
		
		// Je nach Anzahl der lebenden Zellen um eine Zelle herum wird true oder false zurückgegeben
		if (leben < 2 || leben > 3) {
			return false;
		} else if (leben == 3) {
			return true;
		}
		// Leben muss 2 entsprechen, daher wird der Wert beibehalten
		return zellen[x][y];
	}
	
	public static int godMode() {
	    
	    int choice = 0;
	    boolean mistake = false;
	    
	    System.out.println("Aktion wählen:");
	    System.out.println("1 = Setze die Evolution fort!");
	    System.out.println("2 = Greife in die Evolution ein!");
	    System.out.println("3 = Verbrenne die Schöpfung!");
	    
	    do {
	        mistake = false;
	        
	        try {
	        choice = Integer.parseInt(sc.nextLine());
	        }
	        
	        catch (Exception ex) {
	            choice = 0;
	            mistake = true;
	        }
	        
	        if (choice < 1 || choice > 3) {
	            System.out.print("Gewünschte Aktion als Zahl [1-3): ");
	            mistake = true;
	            choice = 0;
	        }
	    
	    } while (mistake == true);
	    
	    return choice;
	}
	 
	 
	 
	 
	public static boolean[][] changeLife(boolean zellen[][]) { //array übergeben
	    
	    int choiceSpalte = 0;
	    int choiceZeile = 0;
	    boolean mistake = false;
	    
	    System.out.print("Zelle wählen [Zeile]: ");
	    
	    do {
	        mistake = false;
	        
	        try {
	        choiceSpalte = sc.nextInt();
	        choiceSpalte -= 1;
	        }
	        
	        catch (Exception e) {
	            choiceSpalte = 0;
	            mistake = true;
	            sc.next();
	        }
	        
	        if (choiceSpalte < 0 || choiceSpalte + 1 > zellen.length) {
	            System.out.print("Zelle [Zeile] im Bereich der Schöpfung wählen: ");
	            mistake = true;
	            choiceSpalte = 0;
	        }
	    
	    } while (mistake == true);
	    
	    System.out.print("Zelle wählen [Spalte]: ");
	    
	    do {
	        mistake = false;
	        
	        try {
	        choiceZeile = sc.nextInt();
	        choiceZeile -= 1;
	        }
	        
	        catch (Exception e) {
	            choiceZeile = 0;
	            mistake = true;
	            sc.next();
	        }
	        
	        if (choiceZeile < 0 || choiceZeile + 1 > zellen[choiceSpalte].length) {
	            System.out.print("Zelle [Spalte] im Bereich der Schöpfung wählen: ");
	            mistake = true;
	            choiceZeile = 0;
	        }
	    
	    } while (mistake == true);
	    
	    if (zellen[choiceSpalte][choiceZeile] == false) {
	        zellen[choiceSpalte][choiceZeile] = true;
	    } else {
	        zellen[choiceSpalte][choiceZeile] = false;
	    }
	    
	    return zellen;
	    
	}
	
	public static void ausgabe(boolean zellen[][]) {
		for(int i = 0; i < zellen.length;i ++) {
			for(int n = 0; n < zellen[0].length;n ++) {
				if(zellen[i][n]) {
					System.out.print("O ");
				} else {
					System.out.print("X ");
				}
				
			}
			System.out.println();
		}
	}
	
	public static int[] eingabe() {
		int schale[] = new int[3];
		while(true) {
			try {
				System.out.print("Bitte geben Sie die Anzahl Spalten ein: ");
				schale[0] = Integer.parseInt(sc.nextLine());
				System.out.print("Bitte geben Sie die Anzahl Zeilen ein: ");
				schale[1] = Integer.parseInt(sc.nextLine());
				System.out.print("Bitte geben Sie die Anzahl lebendiger Zellen ein: ");
				schale[2] = Integer.parseInt(sc.nextLine());
				if (schale[0] > 0 && schale[1] > 0 && schale[2] > 0) {
					break;
				}
				schale[0] = 1 / 0;
			}
			catch (Exception ex) {
				System.out.println("Sie müssen eine Zahl eingeben!");
			}
		}
		return schale;
	}
}