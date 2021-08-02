package lineales.estatico;

/**
 *
 * @author Martin
 */
public class Cola {

    private final static int TAMANIO = 10;
    private Object[] arreglo;
    private int frente;
    private int fin;

    /**
     * Estructura lineal estática en la que el primer elemento en entrar es el 
     * primero en salir. La longitud está definifa por un tamaño fijo. Se utilizan
     * punteros(posicion de un arreglo) para el frente y el fin de una cola.
     *
     *
     */
    public Cola() {
        this.arreglo = new Object[TAMANIO];
        this.frente = 0;
        this.fin = 0;

    }

    /**
     * Método para agregar elementos a la cola. 
     * Como la cola estática es circular, hay que asegurarse de que 
     * que cuando pase por el tamaño definido por el arreglo, por ejemplo:
     * tam 8 (tamaño máximo), agregando un elemento debería dar 1, para asegurarnos
     * usamos el resto de la división para obtener el elemento.
     * Devuelve true si pude agregarse, falso en caso de que la cola esté llena.
     *
     * @param elemento de Tipo Object
     * @return seInserto variable booleana
     */
    public boolean poner(Object elemento) {
        boolean seInserto = false;
        if ((!this.estaLlena())) {
            this.arreglo[fin] = elemento;
            this.fin = (fin + 1) % Cola.getLongitud();
            seInserto = true;
        }
        return seInserto;
    }

    /**
     * Método para quitar elementos de la cola. Quita el primer elemento que
     * esta en la fila, frente en este caso, y cambia el puntero por uno más
     * adelante. Si el puntero de frente es igual al del fin, es una cola vacia.
     * 
     * Devuelve true si pude agregarse, falso caso contrario
     *
     *
     * @return sacoElemento variable booleana
     */
    public boolean sacar() {
        boolean sacoElemento = false;
        if (!this.esVacia()) {
            this.frente = (this.frente + 1) % Cola.getLongitud();
            sacoElemento = true;
        }
        return sacoElemento;
    }

    /**
     * Método observador ObtenerFrente, devuelve el frente de la cola, cuyo
     * elemento es de TipoELemento
     *
     * @return Object es de TipoELemento
     */
    public Object obtenerFrente() {
        return this.arreglo[frente];
    }

    /**
     * Método para clonar la cola actual. Creando nuevos variables pero con el
     * mismo contenido.
     * 
     * @return clone de tipo Cola
     */
    
    public Cola clone() {
        Cola clone = new Cola();

        if (!this.esVacia()) {
            clone.frente = this.frente;
            clone.fin = this.fin;
            for (int i = 0; i < Cola.getLongitud(); i++) {
                clone.arreglo[i] = arreglo[i];
            }

        }
        return clone;
    }

    /**
     * Verifica si la cola ya esta llena.
     * 
     * @return boolean 
     */
    
    public boolean estaLlena() {
        return ((this.fin + 1) % Cola.getLongitud() == frente);
    }

    /**
     * Devuelve la longitud de la fila de la Cola, cuantos elementos en total
     * pueden ponerse en la fila.
     * 
     * @return TAMANIO int
     */
    
    public static int getLongitud() {
        return Cola.TAMANIO;
    }

    /**
     * Verifica que la cola esta vacia. Devuele true en caso de estarlo, false
     * caso contrario.
     * 
     * @return boolean 
     */
    
    public boolean esVacia() {
        return (this.frente == fin);
    }
    
    /**
     * Método para quitar todos los elementos de la cola y dejarla vacia.
     */

    public void vaciar() {
        this.frente = 0;
        this.fin = 0;
    }

    /**
     * Método para devolver una cadena de caracteres con información sobre lo
     * que la Cola tiene actualmente.
     * 
     * @return cadena String
     */
    @Override
    public String toString() {
        String cadena = " -> ";
        if (this.esVacia()) {
            cadena += "| VACIA | ";
        } else {
            int i = frente;
            while (i != fin) {
                if ((i + 1) % Cola.getLongitud() != fin) {
                    cadena += "| " + this.arreglo[i] + " | -> ";
                } else {
                    cadena += "| " + this.arreglo[i] + " |.";
                }
                i = (i + 1) % Cola.getLongitud();
                //7

            }
        }
        return cadena;
    }
}
