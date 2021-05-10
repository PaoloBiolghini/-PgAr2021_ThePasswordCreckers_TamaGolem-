package it.unibs.fp.Tamagolem;

import java.util.ArrayList;
import java.util.Random;
import it.unibs.fp.mylib.InputDati;

public class Player {

    private ArrayList<Tamagolem> listaTamagolem;
    private ArrayList<Pietra> listaPietre;
    private ArrayList<String> listaElementi=new ArrayList<>();
    private int NUMEROPIETRE;
    private int numeroTamagolem;
    private int SCORTAPIETRE;
    private int tamagolemSelected;
    private final int VITA=10;

    public Player(int n)
    {
        this.NUMEROPIETRE=((n+1)/3)+1;
        tamagolemSelected=-1;
        this.numeroTamagolem =((n-1)*(n-2)/(2*this.NUMEROPIETRE));
        this.SCORTAPIETRE=((2* numeroTamagolem *NUMEROPIETRE)/n)*n;

        creaTamagolem();
        creaSetPietre();

    }

    private void creaTamagolem(){
        listaTamagolem=new ArrayList<>();
        for(Elemento e:Grafo1.getListaElementi())
            listaElementi.add(e.getNome());

        for(int i = 0; i< numeroTamagolem; i++) {
            Tamagolem t=new Tamagolem(VITA);
            listaTamagolem.add(t);
        }
    }


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

    public void createTamasetpietre(){
        tamagolemSelected++;
        for(int i=0;i<NUMEROPIETRE;i++)
            addPietraToTama();
    }


    public void addPietraToTama() {
        System.out.println("LISTA PIETRE");
        System.out.println("Inserisci:");
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

        //trovo il currentama e gli aggiungo la pietra scelta dall'utente
        Tamagolem currentTama=listaTamagolem.get(tamagolemSelected);
        Pietra pietraSelected=listaAttuali.get(scelta);

        currentTama.addPietre(pietraSelected);

        listaPietre.remove(pietraSelected);


    }

}
