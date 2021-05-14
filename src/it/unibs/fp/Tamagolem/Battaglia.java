package it.unibs.fp.Tamagolem;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

/**
 * La classe Battaglia per gestire una lotta tra due Player In particolare il
 * metodo Battle restituira il vincitore della battaglia
 * 
 */
public class Battaglia {

	final static int DEAD = 0;

	/**
	 * Lotta tra Tamagolem di due Giocatori
	 * 
	 * @param A
	 * @param B
	 * @return Vincitore
	 */
	public static Player battle(Player A, Player B) {

		/*
		 * a tiene conto dell'indice del tamagolem in uso del giocatore A b tiene conto
		 * dell'indice del tamagolem in uso del giocatore B
		 */
		int a = 0;
		int b = 0;
		/*
		 * Prima evocazione da parte di entrambi i giocatori
		 */
		Battaglia.evocation(A, A.getListaTamagolem().get(a));
		
		Battaglia.evocation(B, B.getListaTamagolem().get(b));
		/*
		 * Usando il metodo fight() otteniamo il tamagolem che ha perso successivamente
		 * verra tolto dalla lista dei tama disponibile del giocatore che lo ha evocato
		 */
		Battaglia.statoBattaglia(A, B);
		do {

			boolean loser = Battaglia.fight(A.getListaTamagolem().get(a), B.getListaTamagolem().get(b));
			/*
			 * se il golem apparteneva ad A verra rimosso dalla sua lista e l'indice a
			 * incrementa di uno e verra fatta l'evocazione del nuovo tamagolem col metodo
			 * evocation(). stessa cosa ma su liste diverse se il golem deceduto apparteneva
			 * a b
			 */
			
			if (!loser) {
				System.out.println(A.getPlayerName()+" ha perso un tamagolem");
				A.getListaTamagolem().remove(a);
				if (!A.getListaTamagolem().isEmpty()) {
					Battaglia.evocation(A, A.getListaTamagolem().get(a));
				}
				
			} else {
				System.out.println(B.getPlayerName()+" ha perso un TamaGolem");
				B.getListaTamagolem().remove(b);
				if (!B.getListaTamagolem().isEmpty()) {
					Battaglia.evocation(B, B.getListaTamagolem().get(b));
				}
			}
			/*
			 * il ciclo termina quando una delle liste dei giocatori è vuota
			 */
			Battaglia.statoBattaglia(A, B);
		} while (!(A.getListaTamagolem().isEmpty()) && !(B.getListaTamagolem().isEmpty()));

		/*
		 * restituisce il vincitore della lotta
		 */
		if (A.getListaElementi().isEmpty()) {
			return A;
		} else {
			return B;
		}
	}

	/**
	 * Consente al player corrente di scegliere 3 pietre per il Tamagolem evocato
	 * (EVOCAZIONE)
	 * 
	 * @param currentPlayer
	 * @param current
	 */
	public static void evocation(Player currentPlayer, Tamagolem current) {
		ArrayList<Pietra> pietreScelte = new ArrayList<Pietra>();
		System.out.println("SCEGLI 3 PIETRE PER IL TAMAGOLEM");
		int numPietre = 0;
//		currentPlayer.leggiSetPietre();
		do {
			Pietra newRock = currentPlayer.addPietraToTama();
			pietreScelte.add(newRock);
			numPietre++;
		} while (numPietre != currentPlayer.getNUMEROPIETRE());
		current.addPietre(pietreScelte);
	}



	/**
	 * Restituisce true se la prima roccia prevale sulla seconda, false altrimenti
	 * 
	 * @param rock1
	 * @param rock2
	 * @return
	 */
	public static boolean whichOneIsStronger(Pietra rock1, Pietra rock2) {
		boolean rock1Stronger = true;
		int value1 = rock1.getElement().getGrafo().get(rock2.getNomeElemento());
		int value2 = rock2.getElement().getGrafo().get(rock2.getNomeElemento());
		if (value1 < value2) {
			rock1Stronger = false;
		}
		return rock1Stronger;
	}

	/**
	 * Scontro tra due tamagolem, restituisce true se il golem 1 batte il 2
	 * 
	 * @param pikachu
	 * @param eevee
	 * @return
	 */
	public static boolean fight(Tamagolem pikachu, Tamagolem eevee) {
		boolean someoneIsDead = false;
		int i = 0;
		System.out.println("-*-*-"+pikachu.getID()+ " VS " + eevee.getID()+"-*-*-");
		do {
			i = i % pikachu.getListaPietre().size();

			Pietra pikachuBadBoy = pikachu.getListaPietre().get(i);
			Pietra eeveeBadBeast = eevee.getListaPietre().get(i);

			displayBattle(pikachu, eevee, pikachuBadBoy, eeveeBadBeast);
			
			Map<String, Integer> pikachuGraph = pikachuBadBoy.getElement().getGrafo();
			Map<String, Integer> eeveeGraph = eeveeBadBeast.getElement().getGrafo();

			String pikachuRockElementName = pikachuBadBoy.getNomeElemento();
			String eeveeRockElementName = eeveeBadBeast.getNomeElemento();

			if (Battaglia.whichOneIsStronger(pikachuBadBoy, eeveeBadBeast)) {
				eevee.setVita(eevee.getVita() - pikachuGraph.get(eeveeRockElementName));
				if(eevee.getVita() <DEAD) {
					eevee.setVita(DEAD);
				}
			} else {
				pikachu.setVita(pikachu.getVita() - eeveeGraph.get(pikachuRockElementName));
				if(pikachu.getVita() <DEAD) {
					pikachu.setVita(DEAD);
				}
			}
			if (eevee.getVita() <= DEAD || pikachu.getVita() <= DEAD) {
				someoneIsDead = true;
			}
			i++;
		} while (!someoneIsDead);
		if (eevee.getVita() <= DEAD) {
			return true;
		} else
			return false;
	}
/*
	public static String stringDecentFormat(String nome) {
		String primaLettera = nome.substring(0, 1).toUpperCase();
		String resto = nome.substring(1);
		return primaLettera + resto;

	}
*/
	/**
	 * Stampa a video I golem con i rispettivi punti vita e le roccie lanciate
	 * @param A
	 * @param B
	 * @param a
	 * @param b
	 */
	private static void displayBattle(Tamagolem A, Tamagolem B, Pietra a , Pietra b) {
		String Forte = null;
		if(Battaglia.whichOneIsStronger(a, b)) {
			Forte = a.getNomeElemento();
		} else {
			Forte = b.getNomeElemento();
		} 
		if(a.getNomeElemento().equals(b.getNomeElemento())) {
			Forte = "Nothing!";
		}
		System.out.printf("[ %s PV : %-2d ] %-6s --> %-8s <-- %6s [ %s PV : %d ]\n", A.getID(), A.getVita(), a.getNomeElemento(),Forte, b.getNomeElemento(), B.getID(),B.getVita());
		
	}

	/**
	 * Stampa a video il nome dei Player e i Tamagolem rimanenti
	 * @param A
	 * @param B
	 */
	private static void statoBattaglia(Player A , Player B) {
		System.out.printf("%s Tgolem rimasti: %d  VS  %s Tgolem rimasti: %d\n", A.getPlayerName(), A.getListaTamagolem().size(), B.getPlayerName(), B.getListaTamagolem().size() );
	}
	
	/**
	 * Dichiara il vincitore
	 * @param winner
	 */
	public static void praiseWinner(Player winner) {
		System.out.println("Grande tu si che sei forte, meno male che ci sei tu vai cosi ben fatto "+winner.getPlayerName());
	}
	
	
}
