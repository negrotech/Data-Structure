package lineales.dinamico;

/**
 * @author Martin
 */
public class Pila {

    private Nodo tope;

    /**
     * Constructor para pila dinámica. Sin parámetros.
     */
    public Pila() {
        this.tope = null;
    }

    /**
     * Función encargada de agregar un elemento en el tope. Si el tope es null,
     * crea un nuevo nodo y se lo agrega al tope con enlace null, caso contrario
     * crea un nuevo nodo, pero el enlace es eñ anterior tope.
     *
     * @param elemento es de TipoElemento y es el que se apilará
     * @return boolean
     */
    public boolean apilar(Object elemento) {

        this.tope = new Nodo(elemento, tope);

        return true;
    }

    /**
     * Función encargada de quitar elementos del tope de la pila. En caso de que
     * la pila este vacia devuelve FALSE. Caso contrario quitar el elemento tope
     * y lo cambia por su enlace,
     *
     * @return boolean
     */
    public boolean desapilar() {
        boolean seDesapilo;
        if (this.esVacia()) {
            seDesapilo = false;
        } else {
            this.tope = tope.getEnlace();;
            seDesapilo = true;
        }
        return seDesapilo;
    }

    /**
     * Devuelve el último elemento que se agrego a la pila.
     *
     * @return Object
     */
    public Object obtenerTope() {
        return (this.tope == null) ? null : this.tope.getElemento();
    }

    /**
     * Verifica si la pila se encuentra vacia
     *
     * @return boolean
     */
    public boolean esVacia() {
        return (this.tope == null);
    }

    /**
     * Vacia la pila cambiando el tope a null, borrando las referencias a él
     */
    public void vaciar() {
        this.tope = null;
    }

    /**
     * Clona todos los elementos de la pila en un objeto de tipo Pila. Verifica
     * si la pila se encuentra vacia. Utilizamos de forma recursiva para
     * recorrer los nodos y acceder al último elemento. El método utiliza
     * un método auxiliar cloneRecursivo que recibe por parámetro el nodo tope.
     *
     * @return Pila
     */
    public Pila clone() {
        Pila clone = new Pila();
        if (!this.esVacia()) {
            clone.tope = this.cloneRecursivo(tope);
        }
        return clone;
    }

    /**
     * Método privado encargado de copiar todos los elementos enlazados a un nuevo nodo.
     * que instanciamos
     *
     * @return Nodo aux
     */
    private Nodo cloneRecursivo(Nodo aux) {
        Nodo clone = null;

        if (aux != null) {
            clone = new Nodo(aux.getElemento(), cloneRecursivo(aux.getEnlace()));
        }
        return clone;
    }

    @Override
    public String toString() {

        String cadena = "\n┬-------┬";
        cadena += "\n░-------░";
        if (this.esVacia()) {
            cadena += "\n░ VACIA ░";

        } else {

            Nodo aux = tope;
            while (aux != null) {
                cadena += "\n░-- " + aux.getElemento() + "---░";
                aux = aux.getEnlace();
            }
            cadena += "\n░-------░";
            cadena += "\n┴-------┴";

        }
        return cadena;
    }

}
