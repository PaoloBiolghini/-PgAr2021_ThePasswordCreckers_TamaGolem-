package it.unibs.fp.Tamagolem;

import java.util.ArrayList;

public class Tamagolem {

    private int vita;
    private ArrayList<Pietra> listaPietre;

    public Tamagolem(int _vita) {
        this.vita=_vita;
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


}
