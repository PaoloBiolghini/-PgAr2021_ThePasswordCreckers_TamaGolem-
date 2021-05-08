import java.util.ArrayList;
import java.util.Random;

public class Grafo {

    private  String[] elementiTutti={"Zero","Uno","Due","Tre","Quattro","Cinque","Sei","Sette","Otto","Nove","Dieci"};
    private ArrayList<Elemento> listaElementi;
    private int numeroElementi;

    public Grafo(int _nelementi)
    {
        this.numeroElementi=_nelementi;
        listaElementi=new ArrayList<>();
        for(int i=0;i<numeroElementi;i++) {
            Elemento e = new Elemento(elementiTutti[i]);
            listaElementi.add(e);
        }

        Random rand=new Random();
        for(int i=0;i<numeroElementi-2;i++)
        {

            for(int j=i+1;j<numeroElementi;j++)
            {
                int valueToAdd=0;
                int mancantii,mancantij;
                mancantii=numeroElementi-j-1;
                //calcolo il massimo ed il minimo degli elemenit possibili
                int max1=listaElementi.get(i).maxElement(mancantii);
                int min1=listaElementi.get(i).minElement(mancantii);

                System.out.println("primo elemento"+max1+"   "+min1 +" con mancanti="+(mancantii)+"con somma"+listaElementi.get(i).getSommaattuale());
                //System.out.println("secondo elemento"+max2+"   "+min2+" con mancanti="+(numeroElementi-i-2)+"con somma"+listaElementi.get(j).getSommaattuale());


                //ciclo per controllare che ogni soluzione sia contemplata anche neglie elementi successvi
                mancantij=numeroElementi-i-2;
                int max2=listaElementi.get(j).maxElement(mancantij);
                int min2=listaElementi.get(j).minElement(mancantij);

                //inverto il segno per far si che siano valori "uniderzionali"
                int supp=-max2;
                max2=-min2;
                min2=supp;

                System.out.println("secondo elemento"+max2+"   "+min2+" con mancanti="+mancantij+"con somma"+listaElementi.get(j).getSommaattuale());


                //controllo quale dei valori sono i più interni per cercare l'insieme di soluzioni comprese
                if(min1==max1){
                    valueToAdd=min1;
                }else if(min2==max2) {
                    valueToAdd=min2;
                }else{
                    int mainmin=min1;
                    int mainmax=max1;
                    System.out.println(mainmax+" "+mainmin);


                    System.out.println("secondo elemento"+max2+"   "+min2+" con mancanti="+mancantij+"con somma"+listaElementi.get(j).getSommaattuale());


                    if(mainmax>max2 && mainmax>=-4)
                        mainmax=max2;

                    if(mainmin<min2 && mainmin<=4)
                        mainmin=min2;


                    if(mainmax<mainmin)
                    {

                        mainmin=mainmax;

                    }




                    System.out.println(mainmax+" "+mainmin);
                    System.out.println("controllo"+(mainmax -1*mainmin+1));

                    valueToAdd=rand.nextInt((mainmax -1*mainmin+1))+mainmin;



                    //controllo che il valore sia diverso da zero così da non aver interazioni nulle
                    //così da evitare l'utilizzo del rand che allungherebbe infinitamente l' esecuizione
                    if(valueToAdd==0)
                    {
                        //assegno di deafult il valore =1 e controllo che sia un valore nell insieme di soluzioni
                        valueToAdd=1;
                        if(valueToAdd>mainmax||valueToAdd<mainmin)
                        {
                            //se 1 non è nelle soluzioni allora lo è sicuramente il -1
                            valueToAdd=-1;
                        }
                    }





                    //creo i vari controlli se manca un elemento la somma non si deve annullare
                    if(mancantii==1&& mancantij==1)
                    {
                        int sommai=listaElementi.get(i).getSommaattuale();
                        int sommaj=listaElementi.get(j).getSommaattuale();
                        if(valueToAdd==-sommai || valueToAdd==-sommaj)
                        {
                            //creo un ciclo di controllo che i il valore da aggiungere sia corretto aggiungendo in serie +1 -2 +3 -4 +5
                            int add=1;
                            int count=1;
                            do{
                                valueToAdd+=add*count;
                                count++;
                                add*=-1;
                            }while(valueToAdd==-sommai || valueToAdd==-sommaj ||valueToAdd>mainmax||valueToAdd<mainmin || valueToAdd==0);
                            valueToAdd+=1;
                            if(valueToAdd>mainmax||valueToAdd<mainmin)
                            {
                                //se 1 non è nelle soluzioni allora lo è sicuramente il -1
                                valueToAdd-=2;
                            }
                        }

                    }else if(mancantii==1)
                    {
                        int sommai=listaElementi.get(i).getSommaattuale();
                        //controllo che se è a n 8 non venga inserito essattamente la somma
                        if(valueToAdd==-sommai)
                        {
                            valueToAdd+=1;
                            if(valueToAdd>mainmax||valueToAdd<mainmin)
                            {
                                //se 1 non è nelle soluzioni allora lo è sicuramente il -1
                                valueToAdd-=2;
                            }
                        }
                    }else if(mancantij==1)
                    {
                        int sommaj=listaElementi.get(j).getSommaattuale();

                        if(valueToAdd==-sommaj)
                        {
                            valueToAdd+=1;
                            if(valueToAdd>mainmax||valueToAdd<mainmin)
                            {
                                //se 1 non è nelle soluzioni allora lo è sicuramente il -1
                                valueToAdd-=2;
                            }
                        }

                    }

                }


                listaElementi.get(i).insert(listaElementi.get(j).getNome(),valueToAdd);
                listaElementi.get(j).insert(listaElementi.get(i).getNome(),-valueToAdd);
                System.out.println("da "+i+" a "+j+"  valore di"+valueToAdd);

            }
        }
        listaElementi.get(numeroElementi-2).insert(listaElementi.get(numeroElementi-1).getNome(),listaElementi.get(numeroElementi-1).getSommaattuale());
        listaElementi.get(numeroElementi-1).insert(listaElementi.get(numeroElementi-2).getNome(),-listaElementi.get(numeroElementi-1).getSommaattuale());
        for(Elemento e:listaElementi)
        {
            System.out.println(e.getNome()+" SOMMA "+e.getSommaattuale()+"   "+e.getGrafo());
        }
        System.out.println("creazione terminata");
    }
}
