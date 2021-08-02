package conjuntistas.hash.cerrado;

public class CeldaHash {
    private Object element;
    private int estado ;

    public CeldaHash(Object element, int estado) {
        this.element = element;
        this.estado = estado;
    }

    public Object getElement() {
        return element;
    }

    public void setElement(Object element) {
        this.element = element;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
