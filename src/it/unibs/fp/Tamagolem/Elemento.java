package it.unibs.fp.Tamagolem;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Elemento {

    private Map<String,Integer> grafo=new HashMap();
    private int sommaattuale;
    private int massimoVoti;
    private String nome;

    public Elemento(String _nome)
    {
        this.nome=_nome;
        sommaattuale=0;
    }

    public int getSommaattuale()
    {
        return sommaattuale;
    }


    /***
     * funzione che dati n passaggi mancatnti ed in base alla somma attuale restituisce il valore massimo
     * @param mancanti
     * @return
     */
    public int  maxElement(int mancanti)
    {
        int valore=sommaattuale-4*mancanti;

        if(-valore>4)
            return 4;


        if(valore!=0)
            return -(valore);



        return 0;
    }

    /***
     * dati n mancanti restituisce il valore minimo assumibile
     * @param mancanti
     * @return
     */
    public int minElement(int mancanti)
    {
        int valore=sommaattuale+4*mancanti;
        if(-valore<-4)
            return -4;
        if(valore!=0)
            return -(valore);

        return 0;
    }

    /***
     * richiamando le funzioni min e max calcola l'insieme delle soluzioni dato dagli elementi compresi tra min e max diversi da 0
     * @param mancanti
     * @param reverse
     * @return
     */
    public Set<Integer> getSolutionSet(int mancanti, int reverse)
    {
        int max=maxElement(mancanti);
        int min=minElement(mancanti);
        Set<Integer> result=new HashSet<>();
        for(int i=min;i<=max;i++)
            if(i!=0)
                if(!(mancanti==1 && i==-sommaattuale))
                    result.add(i*reverse);

        return result;
    }

    /***
     * viene invocato nel controllo e restituisce l'insieme di soluzioni dopo aver temporeanemente aggiunto un valore
     * @param mancanti
     * @param reverse
     * @param n
     * @return
     */
    public Set<Integer> getFutureSolutionSet(int mancanti, int reverse,int n)
    {
        mancanti--;
        sommaattuale+=n;
        Set<Integer> result=getSolutionSet(mancanti,reverse);
        System.out.println(result);
        sommaattuale-=n;
        return result;
    }

    /***
     * funzione di inserimento
     * @param e1
     * @param interazione
     */
    public void insert(String e1,int interazione){
        sommaattuale+=interazione;
        grafo.put(e1,interazione);
    }

    public Map<String,Integer> getGrafo()
    {
        return grafo;
    }

    public String getNome()
    {
        return nome;
    }

}
