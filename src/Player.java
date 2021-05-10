import java.util.ArrayList;
import java.util.Random;

public class Player {

    private ArrayList<Tamagolem> listaTamagolem;
    private ArrayList<Pietra> listaPietre;
    private ArrayList<String> listaElementi=new ArrayList<>();
    private int NUMEROPIETRE;
    private int numeroTamagolem;
    private int SCORTAPIETRE;
    private final int VITA=10;

    public Player(int n)
    {
        this.NUMEROPIETRE=((n+1)/3)+1;

        this.numeroTamagolem =((n-1)*(n-2)/(2*this.NUMEROPIETRE));
        this.SCORTAPIETRE=((2* numeroTamagolem *NUMEROPIETRE)/n)*n;

        listaTamagolem=new ArrayList<>();
        listaPietre=new ArrayList<>();

        for(Elemento e:Grafo1.getListaElementi())
            listaElementi.add(e.getNome());



        for(int i = 0; i< numeroTamagolem; i++) {
            Tamagolem t=new Tamagolem(VITA);
            listaTamagolem.add(t);
        }
    }

    private void creaSetPietre()
    {
        Random rand=new Random();
        for(int i=0;i<SCORTAPIETRE;i++) {
            int pos=rand.nextInt(listaElementi.size());
            Pietra p=new Pietra(listaElementi.get(pos));
            listaPietre.add(i,p);
        }

    }

    public void leggiSetPietre() {
        System.out.println("LISTA PIETRES");
        for(String s:listaElementi) {
            int count=0;
            for(Pietra p:listaPietre)
                if(p.getNomeElemento().equals(s))
                    count++;

            if(count>0)
                System.out.println(s+"   n:"+count);

        }

    }

}
