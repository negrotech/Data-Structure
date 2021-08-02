package grafo.lista_adyecencia;

public class NodoVertice {

    private Object element;
    private NodoVertice sgteVertice;
    private NodoAdy primerAdy;

    public NodoVertice(Object element, NodoVertice sgteVertice, NodoAdy ady){
        this.element = element;
        this.sgteVertice = sgteVertice;
        this.primerAdy = ady;
    }

    public Object getElement() {
        return element;
    }

    public void setElement(Object element) {
        this.element = element;
    }

    public NodoVertice getSgteVertice() {
        return sgteVertice;
    }

    public void setSgteVertice(NodoVertice sgteVertice) {
        this.sgteVertice = sgteVertice;
    }

    public NodoAdy getPrimerAdy() {
        return primerAdy;
    }

    public void setPrimerAdy(NodoAdy primerAdy) {
        this.primerAdy = primerAdy;
    }
}
