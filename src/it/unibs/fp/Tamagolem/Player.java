package it.unibs.fp.Tamagolem;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import it.unibs.fp.mylib.InputDati;

public class Player {

	private String playerName;
	private ArrayList<Tamagolem> listaTamagolem;
	private ArrayList<Pietra> listaPietre;
	private ArrayList<String> listaElementi = new ArrayList<>();
	private Tamagolem currentTamagolem;
	private int NUMEROPIETRE;
	private int NUMEROTAMAGOLEM;
	private int SCORTAPIETRE;
	private int tamagolemSelected;
	private final int VITA = 10;

	public Player(int n, String name) {
		this.NUMEROPIETRE = ((n + 1) / 3) + 1;
		tamagolemSelected = -1;
		this.NUMEROTAMAGOLEM = ((n - 1) * (n - 2) / (2 * this.NUMEROPIETRE));
		this.SCORTAPIETRE = ((2 * NUMEROTAMAGOLEM * NUMEROPIETRE) / n) * n;
		this.playerName = name;
		creaTamagolem();
		creaSetPietre();
	}

	public String getPlayerName() {
		return playerName;
	}
	/**
	 * inserisce nell'arraylist un insieme di tamagolem con n di vita
	 */
	private void creaTamagolem() {
		listaTamagolem = new ArrayList<>();
		for (Elemento e : Grafo1.getListaElementi())
			listaElementi.add(e.getNome());

		for (int i = 0; i < NUMEROTAMAGOLEM; i++) {
			Tamagolem t = new Tamagolem(VITA,"GLM"+i+playerName.substring(0,1).toUpperCase());
			listaTamagolem.add(t);
		}
	}
	
	

	/**
	 * crea il set di pietre del player
	 */
	private void creaSetPietre() {
		listaPietre = new ArrayList<>();
		Random rand = new Random();
		for (int i = 0; i < SCORTAPIETRE; i++) {
			int pos = rand.nextInt(listaElementi.size());
			Pietra p = new Pietra(listaElementi.get(pos));
			listaPietre.add(i, p);
		}

	}

	/**
	 * visualizza tutte le pietre presenti nel set di pietre e inserendo l' indice a
	 * fianco di queste il payer decide di inserire quella pietra all'interno del
	 * tamagolem corrente
	 */
	public Pietra addPietraToTama() {
		System.out.println("----------LISTA PIETRE----------");
		System.out.println("Indice            Nome: "+playerName);
		ArrayList<Pietra> listaAttuali = new ArrayList<Pietra>();
		int i = 1;
		// itero per ogni elemento della lista e controllo che ci sia almeno un elemento
		// nel set disponibile
		for (String s : listaElementi) {
			int count = 0;

			for (Pietra p : listaPietre)
				if (p.getNomeElemento().equals(s)) {
					count++;
					listaAttuali.add(p);
					break;
				}
			if (count > 0) {
			//	System.out.println(i + "   " + s + "   quantit�: " + numberOfthisTypeOfRock(s));
				System.out.printf("%d   %-8s      disponibili: %d\n",i,s,numberOfthisTypeOfRock(s));
				i++;
			}
		}

		int scelta = InputDati.leggiIntero("Pietra n:", 1, listaAttuali.size());
		scelta--;

		// aggiungo la pietra scelta dall'utente e la rimuovo dalla lista del player

		Pietra pietraSelected = listaAttuali.get(scelta);

//		currentTamagolem.addPietre(pietraSelected);

		listaPietre.remove(pietraSelected);
		
		return pietraSelected;
	}

	public boolean newTama() {
		// se il currentama!=null allora devo rimettere le pietre nel set del player
		if (currentTamagolem != null)
			listaPietre.addAll(currentTamagolem.getListaPietre());

		// incremento il contatore e controllo che siano presenti ancora tamagolem
		tamagolemSelected++;
		if (tamagolemSelected >= NUMEROTAMAGOLEM)
			return false;

		// se esistono allora deve creare il set di pietre
		currentTamagolem = listaTamagolem.get(tamagolemSelected);
		for (int i = 0; i < NUMEROPIETRE; i++)
			addPietraToTama();

		return true;
	}

	public Tamagolem getCurrentTamagolem() {
		return currentTamagolem;
	}

	public ArrayList<Tamagolem> getListaTamagolem() {
		return listaTamagolem;
	}

	public ArrayList<Pietra> getListaPietre() {
		return listaPietre;
	}

	public ArrayList<String> getListaElementi() {
		return listaElementi;
	}

	public int getNUMEROPIETRE() {
		return NUMEROPIETRE;
	}

	public int getNumeroTamagolem() {
		return NUMEROTAMAGOLEM;
	}

	public int getSCORTAPIETRE() {
		return SCORTAPIETRE;
	}

	public int getVITA() {
		return VITA;
	}

	public void setListaTamagolem(ArrayList<Tamagolem> listaTamagolem) {
		this.listaTamagolem = listaTamagolem;
	}

	public void setListaPietre(ArrayList<Pietra> listaPietre) {
		this.listaPietre = listaPietre;
	}

	public void setListaElementi(ArrayList<String> listaElementi) {
		this.listaElementi = listaElementi;
	}

	public void setNUMEROPIETRE(int nUMEROPIETRE) {
		NUMEROPIETRE = nUMEROPIETRE;
	}

	public void setNumeroTamagolem(int numeroTamagolem) {
		this.NUMEROTAMAGOLEM = numeroTamagolem;
	}

	public void setSCORTAPIETRE(int sCORTAPIETRE) {
		SCORTAPIETRE = sCORTAPIETRE;
	}

	public void leggiSetPietre() {
		System.out.println("LISTA PIETRES");
		for (String s : listaElementi) {
			int count = 0;
			for (Pietra p : listaPietre) {
				if (p.getNomeElemento().equals(s))
					count++;
			}

			if (count > 0) {
				System.out.println(s + "   n:" + count);
			}

		}

	}

	/**
	 * Crea un oggetto Player 
	 * 
	 * @param numElementi
	 * @return Player creato
	 */
	public static Player insertPlayer(int numElementi) {
		String nome = InputDati.leggiStringaNonVuota("Nome giocatore -> ");
		Player newBorn = new Player(numElementi, nome);
		return newBorn;
	}

	/**
	 * Stampa a video lo stato del giocatore tra cui nome e numero di golem rimasti
	 */
	public void statusPlayer() {
		System.out.printf("%s\nGolem disponibili: %d\n", playerName , listaTamagolem.size());
	}
	
	/**
	 * It tells whether a rock is present or not in the arraylist listapietre
	 * @param nome
	 * @return
	 */
	public boolean isThisRockPresent(String nome) {
		boolean present = false ;
		for (int i = 0 ; i < listaPietre.size(); i++) {
			String currentRockName = listaPietre.get(i).getNomeElemento();
			if(currentRockName.equals(nome)) {
				return true;
			}
		}
		return present ;
	}
	
	private int numberOfthisTypeOfRock(String nome) {
		int count = 0 ;
		for ( int i = 0 ; i < listaPietre.size(); i++ ) {
			String currentRock = listaPietre.get(i).getNomeElemento();
			if(currentRock.equals(nome)) {
				count++;
			}
		}
		return count;
	}
	public Pietra chooseRock() {
		ArrayList <Pietra> lista = new ArrayList<Pietra>();	
		for( int i = 0 ; i < listaPietre.size();i++) {
			int count = 0 ;
			for (String element : listaElementi) {
				if(element.equals(getListaPietre().get(i).getNomeElemento())) {
					count++;
				}
			}
			if (count > 0) {
				lista.add(listaPietre.get(i));
				System.out.println(listaPietre.get(i).getNomeElemento() + " n: "+count);
			}
		}
		int indexChosen = InputDati.leggiIntero("Scegli la pietra (indice)-> ", 0, lista.size());
		Pietra prescelto = lista.get(indexChosen);
		listaPietre.remove(prescelto);
		return prescelto ;
	}
}
