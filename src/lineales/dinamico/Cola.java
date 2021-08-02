package lineales.dinamico;

/**
 *
 * @author Martin
 */
public class Cola {

    private Nodo frente;
    private Nodo fin;
    
    /**
     * Estructura lineal dinamica en la que el primer elemento en entrar es el 
     * primero en salir. El tamaño de una cola dinámica no tiene fin, ya que
     * por cada elemento que se agregar se van enlazando. Se utilizan
     * punteros(Nodos) para el frente y el fin de una cola.
     *
     *
     */

    public Cola() {
        this.fin = null;
        this.frente = null;
    }

    /**
     * Método para agregar elementos a la cola. Si es el primer elemento en 
     * ingresar, se crea un nuevo nodo, "frente" y hacemos que la variable
     * "fin" apunte a frente en primera instancia. En caso de ya haber elementos,
     * cambiamos el último elemento de la fila por el nuevo que ingreso, siendo
     * el nuevo, el ultimo en la cola.
     *
     * @param elemento de tipo Object
     * @return seInserto variable booleana
     */
    
    public boolean poner(Object elemento) {
        if (this.esVacia()) {
            this.frente = new Nodo(elemento);
            this.fin = frente;
        } else {
            this.fin.setEnlace(new Nodo(elemento));
            this.fin = fin.getEnlace();
        }
        return true;
    }
    
     /**
     * Método para quitar elementos de la cola. Quita el primer elemento que
     * esta en la fila, frente en este caso, y cambia el puntero por uno más
     * adelante, cambia al enlace siguiente. 
     * 
     * Devuelve true si pude agregarse, falso en caso de que la cola este vacia.
     *
     *
     * @return sacoElemento variable booleana
     */

    public boolean sacar() {
        boolean sacoElemento = false;
        if (!this.esVacia()) {
            this.frente = frente.getEnlace();
            if (this.frente == null) {
                this.fin = null;
            }
            sacoElemento = true;
        }
        return sacoElemento;
    }
    /**
     * Método observador obntenerFrente, devuelve el elemento frente de la cola
     * de tipoElemento.
     * @return Object
     */

    public Object obtenerFrente() {
        return (frente != null)
                ? frente.getElemento()
                : null;
    }
    
    /**
     * Método para clonar la cola actual. Creando nuevos variables pero con el
     * mismo contenido. Utilizamos un método recursivo recursiveClone(frente)
     * para recorrer el mismo agregando nuevos nodos.
     * 
     * @return clone de tipo Clone
     */

    public Cola clone() {
        Cola clone = new Cola();

        if (!this.esVacia()) {

            Nodo auxFrente = recursivoClone(this.frente);
            clone.frente = auxFrente;
            while (auxFrente != null) {
                if (auxFrente.getEnlace() == null) {
                    clone.fin = auxFrente;
                }
                auxFrente = auxFrente.getEnlace();
            }

        }
        return clone;
    }

    private Nodo recursivoClone(Nodo frente) {
        Nodo auxiliar = null;

        if (frente != null) {
            auxiliar = new Nodo(frente.getElemento(), recursivoClone(frente.getEnlace()));
        }
        return auxiliar;
    }

    /**
     * Método para quitar todos los elementos y dejarla vacia.
     */
    
    public void vaciar() {
        this.fin = null;
        this.frente = null;
    }

    /**
     * Verifica si la Cola está vacia.
     * @return boolean
     */
    
    public boolean esVacia() {
        return (this.frente == null);
    }

    /**
     * Método para devolver una cadena de caracteres con información sobre lo
     * que la Cola tiene actualmente.
     * 
     * @return cadena String
     */
    
    public String toString() {
        String cadena = "";
        if (this.esVacia()) {
            cadena += "| VACIA | ";
        } else {
            Nodo aux = frente;
            while (aux != null) {
                if (aux.getEnlace() != null) {
                    cadena += "" + aux.getElemento() + "  ";
                } else {
                    cadena += " " + aux.getElemento() + " ";
                }
                aux = aux.getEnlace();

            }
        }
        return cadena;
    }
}
