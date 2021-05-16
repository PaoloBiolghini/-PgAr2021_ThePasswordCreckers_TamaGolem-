package it.unibs.fp.Tamagolem;

import java.util.ArrayList;

public class Tamagolem {

	private String ID ;
	public static int MaxNumberOfRocks;
    private int vita;
    private ArrayList<Pietra> listaPietre = new ArrayList<Pietra>();

    public String getID() {
    	return ID;
    }
    public Tamagolem(int _vita, String _ID) {
        this.vita=_vita;
        this.ID = _ID;
    }

    public void addPietre(Pietra pietra) {
        this.listaPietre.add(pietra);
        
    }

    public ArrayList<Pietra> getListaPietre() {
        return listaPietre;
    }

    /**
     * invocato quando viene colpito e ritorna true se vivo e false se morto
     * @param damage
     * @return
     */
    public boolean hit(int damage){
        vita-=damage;

        if(vita<=0)
            return false;

        return true;
    }
    
    /**
     * aggiunge le pietre scelte durante l'evocazione al golem
     * @param _lista
     */
    
    public void addPietre(ArrayList<Pietra> _lista) {
        this.listaPietre=_lista;
        Tamagolem.MaxNumberOfRocks = _lista.size();
    }

	public int getVita() {
		return vita;
	}

	public void setVita(int vita) {
		this.vita = vita;
	}

	public void setListaPietre(ArrayList<Pietra> listaPietre) {
		this.listaPietre = listaPietre;
	}
	

}
