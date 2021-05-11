package it.unibs.fp.Tamagolem;

import java.util.ArrayList;
import java.util.Random;
import it.unibs.fp.mylib.InputDati;

public class Player {

    private ArrayList<Tamagolem> listaTamagolem;
    private ArrayList<Pietra> listaPietre;
    private ArrayList<String> listaElementi=new ArrayList<>();
    private Tamagolem currentTamagolem;
    private int NUMEROPIETRE;
    private int NUMEROTAMAGOLEM;
    private int SCORTAPIETRE;
    private int tamagolemSelected;
    private final int VITA=10;

    public Player(int n)
    {
        this.NUMEROPIETRE=((n+1)/3)+1;
        tamagolemSelected=-1;
        this.NUMEROTAMAGOLEM =((n-1)*(n-2)/(2*this.NUMEROPIETRE));
        this.SCORTAPIETRE=((2* NUMEROTAMAGOLEM *NUMEROPIETRE)/n)*n;

        creaTamagolem();
        creaSetPietre();
    }

    /**
     * inserisce nell'arraylist un insieme di tamagolem con n di vita
     */
    private void creaTamagolem(){
        listaTamagolem=new ArrayList<>();
        for(Elemento e:Grafo1.getListaElementi())
            listaElementi.add(e.getNome());

        for(int i = 0; i< NUMEROTAMAGOLEM; i++) {
            Tamagolem t=new Tamagolem(VITA);
            listaTamagolem.add(t);
        }
    }

    /**
     * crea il set di pietre del player
     */
    private void creaSetPietre()
    {
        listaPietre=new ArrayList<>();
        Random rand=new Random();
        for(int i=0;i<SCORTAPIETRE;i++) {
            int pos=rand.nextInt(listaElementi.size());
            Pietra p=new Pietra(listaElementi.get(pos));
            listaPietre.add(i,p);
        }

    }


    /**
     * visualizza tutte le pietre presenti nel set di pietre e inserendo l' indice a fianco di queste il payer decide
     * di inserire quella pietra all'interno del tamagolem corrente
     */
    public void addPietraToTama() {
        System.out.println("----------LISTA PIETRE----------");
        System.out.println("Indice           Nome:");
        ArrayList<Pietra> listaAttuali=new ArrayList<>();
        int i=1;
        //itero per ogni elemento della lista e controllo che ci sia almeno un elemento nel set disponibile
        for(String s:listaElementi) {
            int count=0;

            for(Pietra p:listaPietre)
                if(p.getNomeElemento().equals(s)) {
                    count++;
                    listaAttuali.add(p);
                    break;
                }

            if(count>0) {
                System.out.println(i+"   "+s);
                i++;
            }
        }

        int scelta=InputDati.leggiIntero("Pietra n:",1,listaAttuali.size());
        scelta--;

        // aggiungo la pietra scelta dall'utente e la rimuovo dalla lista del player

        Pietra pietraSelected=listaAttuali.get(scelta);

        currentTamagolem.addPietre(pietraSelected);

        listaPietre.remove(pietraSelected);
    }

    public boolean newTama(){
        //se il currentama!=null allora devo rimettere le pietre nel set del player
        if(currentTamagolem!=null)
            listaPietre.addAll(currentTamagolem.getListaPietre());

        //incremento il contatore e controllo che siano presenti ancora tamagolem
        tamagolemSelected++;
        if(tamagolemSelected>=NUMEROTAMAGOLEM)
            return false;

        //se esistono allora deve creare il set di pietre
        currentTamagolem=listaTamagolem.get(tamagolemSelected);
        for(int i=0;i<NUMEROPIETRE;i++)
            addPietraToTama();


        return true;
    }

    public Tamagolem getCurrentTamagolem(){
        return currentTamagolem;
    }


}
