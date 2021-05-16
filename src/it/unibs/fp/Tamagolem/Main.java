package it.unibs.fp.Tamagolem;

import java.util.ArrayList;
import java.util.Random;

import it.unibs.fp.mylib.InputDati;

public class Main {

	public static String INIZIO = "TAMAGOLEM 2021", NUM_ELEMENTS = "Numero di elementi -> ";
	
    public static void main(String[] args) {

		System.out.println(INIZIO);
		
		boolean go = false ;
		
		Random rand = new Random();
	
		do {
			
			int numElements = rand.nextInt(7)+4;
			System.out.println("Numero di elementi in gioco "+ numElements);
			
			Grafo1 Equilibrio = new Grafo1(numElements);

			System.out.println("GIOCATORE 1");
			Player A = Player.insertPlayer(numElements,new ArrayList<Pietra>());
			System.out.println("GIOCATORE 2");
			Player B = Player.insertPlayer(numElements, A.getListaPietre());
			
			Battaglia.praiseWinner(Battaglia.battle(A, B));
			
			Equilibrio.showEquilibrio();
			
			go = InputDati.yesOrNo("Nuova Partita? ");
			
		}while(go);
		
		System.out.println("TAMAGOLEM 2021 - END");
    }


    
}
