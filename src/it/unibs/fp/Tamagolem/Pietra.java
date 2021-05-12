package it.unibs.fp.Tamagolem;

public class Pietra {
    private String nomeElemento;
    private Elemento elemento;

    public Pietra(String _nomeElemento)
    {
        this.nomeElemento=_nomeElemento;
        this.elemento = Grafo1.getElemento(_nomeElemento);
    }

    public String getNomeElemento()
    {
        return nomeElemento;
    }
    public Elemento getElement() {
    	return elemento;
    }
}