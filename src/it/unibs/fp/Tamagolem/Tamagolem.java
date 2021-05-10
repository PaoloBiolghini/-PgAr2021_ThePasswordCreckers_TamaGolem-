package it.unibs.fp.Tamagolem;

import java.util.ArrayList;

public class Tamagolem {

    private int vita;
    private ArrayList<Pietra> listaPietre;

    public Tamagolem(int _vita) {
        this.vita=_vita;
    }

    public void addPietre(ArrayList<Pietra> _lista) {
        this.listaPietre=_lista;
    }


}
