package it.unibs.fp.Tamagolem;

import java.util.ArrayList;
import java.util.Random;

import it.unibs.fp.mylib.InputDati;

public class Main {

	public static String INIZIO = "TAMAGOLEM 2021", NUM_ELEMENTS = "Numero di elementi -> ";
	
    public static void main(String[] args) {

		System.out.println(INIZIO);
		
		int numElements = InputDati.leggiIntero(NUM_ELEMENTS, 4, 10);
		
		Grafo1 Equilibrio = new Grafo1(numElements);
		
		Player A = Player.insertPlayer(numElements);
		Player B = Player.insertPlayer(numElements);
		
		Battaglia.praiseWinner(Battaglia.battle(A, B));
		
		
    }


    
}
