package generic_structure.lineales.estatico;

/**
 *
 * @author Martin
 */
public class Pila {

    private final static int TAMANIO = 10;
    private Object[] arreglo;
    private int tope;

    /**
     * Constructor para pila estática. Sin parámetros.
     */
    public Pila() {
        this.arreglo = new Object[TAMANIO];
        this.tope = -1;
    }

    /**
     * Función encargada de agregar un elemento en el tope. En caso de que se
     * agregue en el arreglo devuelve TRUE, caso contrario devuelve FALSE (pila
     * llena)
     *
     * @param elemento es de TipoElemento y es el que se apilará
     * @return boolean
     */
    public boolean apilar(Object elemento) {
        boolean seApilo;
        if ((this.tope + 1) >= TAMANIO) {
            seApilo = false;
        } else {
            this.arreglo[tope + 1] = elemento;
            this.tope += 1;
            seApilo = true;
        }
        return seApilo;
    }

    /**
     * Función encargada de quitar elementos del tope de la pila. En caso de que
     * el tope sea igual a -1 devuelve FALSE (pila vacia). Caso contrario TRUE y
     * se cambia el puntero o la dirección del tope del arreglo.
     *
     * @return boolean
     */
    public boolean desapilar() {
        boolean seDesapilo;
        if (this.tope == -1) {
            seDesapilo = false;
        } else {
            this.tope -= 1;
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
        return (this.tope != -1) ? this.arreglo[tope] : null;
    }

    /**
     * Verifica si la pila se encuentra vacia
     *
     * @return boolean
     */
    public boolean esVacia() {
        return (this.tope == -1);
    }

    /**
     * Vacia la pila cambiando dirección del indice del arreglo a -1.
     *
     */
    public void vaciar() {
        this.tope = -1;
    }

    /**
     * Clona la pila actual, copiando los elementos de los arreglos.
     *
     * @return boolean
     */
    public Pila clone() {
        Pila clone = new Pila();
        if (!this.esVacia()) {
            clone.tope = this.tope;
            for (int i = 0; i <= tope; i++) {
                clone.arreglo[i] = this.arreglo[i];
            }
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

            for (int i = tope; i >= 0; i--) {
                if ((int) this.arreglo[i] < 10) {
                    cadena += "\n░-- " + this.arreglo[i] + "---░";

                } else {
                    cadena += "\n░--" + this.arreglo[i] + "---░";
                }
            }

        }
        cadena += "\n░-------░";
        cadena += "\n┴-------┴";
        return cadena;
    }
}
