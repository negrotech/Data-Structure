package conjuntistas.hash.abierto;

public class Nodo {
    private Object element;
    private Nodo enlace;

    public Nodo(Object element, Nodo enlace) {
        this.element = element;
        this.enlace = enlace;
    }

    //Observadores

    public Object getElement(){
        return this.element;
    }
    public Nodo getEnlace(){
        return this.enlace;
    }

    //MOdificadores

    public void setElement(Object element){
        this.element  = element;
    }
    public void setEnlace(Nodo enlace) {
        this.enlace = enlace;
    }

}
