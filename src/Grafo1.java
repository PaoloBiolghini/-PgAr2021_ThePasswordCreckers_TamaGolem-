import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Grafo1 {

    private  String[] elementiTutti={"Zero","Uno","Due","Tre","Quattro","Cinque","Sei","Sette","Otto","Nove","Dieci"};
    private ArrayList<Elemento> listaElementi;
    private int numeroElementi;

    public Grafo1(int _nelementi)
    {
        this.numeroElementi=_nelementi;
        listaElementi=new ArrayList<>();
        for(int i=0;i<numeroElementi;i++) {
            Elemento e = new Elemento(elementiTutti[i]);
            listaElementi.add(e);
        }

        Random rand=new Random();
        for(int i=0;i<numeroElementi-1;i++)
        {

            for(int j=i+1;j<numeroElementi;j++)
            {
                System.out.println("-------------------------------");
                int valueToAdd=0;
                int  mancantii=numeroElementi-j-1;
                int mancantij=numeroElementi-i-2;

                //calcolo il set soluzioni
                Set<Integer> solutionSet=listaElementi.get(i).getSolutionSet(mancantii,1);



                //calcolo soluzioni del secondo elemento
                Set<Integer> set2=listaElementi.get(j).getSolutionSet(mancantij,-1);

                System.out.println("somma i:"+listaElementi.get(i).getSommaattuale()+ solutionSet);
                System.out.println("somma j:"+listaElementi.get(j).getSommaattuale()+ set2);

                solutionSet.retainAll(set2);


                Set<Integer> setcheck=new HashSet<>();
                Set<Integer> setcheck1=new HashSet<>();
                System.out.println("size:"+solutionSet.size()+"   soluzioni:"+solutionSet);
                if(solutionSet.size()==1)
                {
                    for(Integer e:solutionSet)
                        valueToAdd=e;
                }else{
                    do{
                        System.out.println("------ controllo di i:"+i+" j:"+j);
                        //dati la i e la j controllo che esistano soluzioni nei passaggi successivi tra i e j+1 e i+1 e j
                        //prendo un valore dall'intersezione tra i due insiemi e controllo se esistiono soluzioni nei casi successivi
                        int compare=rand.nextInt(solutionSet.size());
                        int l=0;
                        for(Integer p:solutionSet)
                        {
                            if(compare==l)
                                valueToAdd=p;
                            l++;
                        }
                        solutionSet.remove(valueToAdd);
                        System.out.println("controllo con"+valueToAdd+" set:"+solutionSet);
                        //creo variabile add per scongiurare di controllare la posizione n-n
                        int add1=1;
                        if(i-j==1)
                        {
                            add1=2;
                        }

                        int checki=i;
                        int checkj=j+add1;
                        //se la j è all'utlima posizione non è possibile controllare n - out of array
                        //quindi controllo i ed i+i (per esempio passo da 5-9 a 6-7
                        if(j==9)
                        {
                            checki=i+1;
                            checkj=i+2;
                        }

                        //controllo se esistono delle soluzione degli elementi successivi
                        System.out.println("Primo controllo con i:"+i+"  j:"+(j+add1));
                        setcheck=listaElementi.get(checki).getFutureSolutionSet(mancantii,+1,valueToAdd);
                        setcheck.retainAll(listaElementi.get(checkj).getSolutionSet(mancantij,-1));


                        //inserisco controllo per evitare di controllare n-n
                        int add=1;
                        if(j-i==1)
                            add=2;

                        //controllo che esistano soluzioni per i+add e j
                        System.out.println("secondo controllo con i:"+(i+add)+" con rimanenti"+(mancantij-add) +"    j:"+j+" mancanti:"+(mancantij-add));
                        setcheck1=listaElementi.get(j).getFutureSolutionSet(mancantij,+1,-valueToAdd);

                        System.out.println("somma j="+listaElementi.get(j).getSommaattuale() +" i:"+listaElementi.get(i+add).getSommaattuale());
                        System.out.println("primo futuro elemento:"+setcheck1+"  secondo:"+listaElementi.get(i+add).getSolutionSet(mancantii-add,-1));

                        setcheck1.retainAll(listaElementi.get(i+add).getSolutionSet(mancantij,-1));
                        //System.out.println("set di controllo"+setcheck);
                        System.out.println("size di controllo:"+setcheck.size()+"  secondo size:"+setcheck1.size());

                        //se non mancano elementi j allora non è necessario fare il controllo
                        //questo evita un errore
                        if(mancantij==0)
                            setcheck1.add(1);

                    }while(setcheck.size()==0 || setcheck1.size()==0);

                }

                System.out.println("valore:"+valueToAdd);

                listaElementi.get(i).insert(listaElementi.get(j).getNome(),valueToAdd);
                listaElementi.get(j).insert(listaElementi.get(i).getNome(),-valueToAdd);
                System.out.println("da "+i+" a "+j+"  valore di"+valueToAdd);

            }
        }

        for(Elemento e:listaElementi)
        {
            e.insert(e.getNome(),0);
        }
        for(Elemento e:listaElementi) {
            System.out.println(e.getNome());
            System.out.println(e.getGrafo());
        }
        System.out.println("creazione terminata");
    }



}