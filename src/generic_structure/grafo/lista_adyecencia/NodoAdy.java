package generic_structure.grafo.lista_adyecencia;

public class NodoAdy {
    private NodoVertice vertice;
    private NodoAdy sgteAdyacente;
    private String etiqueta;

    public NodoAdy(NodoVertice vertice, NodoAdy sgteAdyacente, String etiqueta) {
        this.vertice = vertice;
        this.sgteAdyacente = sgteAdyacente;
        this.etiqueta = etiqueta;
    }

    public NodoVertice getVertice() {
        return vertice;
    }

    public void setVertice(NodoVertice vertice) {
        this.vertice = vertice;
    }

    public NodoAdy getSgteAdyacente() {
        return sgteAdyacente;
    }

    public void setSgteAdyacente(NodoAdy sgteAdyacente) {
        this.sgteAdyacente = sgteAdyacente;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }
}
