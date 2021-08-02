package generic_structure.lineales.dinamico;

/**
 * @author Martin
 */
public class Lista {

    private Nodo cabecera;
    private int longitud;

    /**
     * Lista es una secuencia de elementos que constituye una estructura
     * flexible. Donde puede crecer y acortarse lo que sea necesario. Puede
     * acceder a los elementos con una posición para quitarlos elementos o
     * recuperarlos. Tiene un nodo cabecera que es el primer elemento de la
     * lista.
     */
    public Lista() {
        this.cabecera = null;
        this.longitud = 0;
    }

    /**
     * Método para insertar elementos a la lista en una posicion establecida, Al
     * insertar un elemento hay dos casos criticos. Cuando esta vacio; Cuando se
     * encuentra entre elementos insertados. Cabe mencionar que "pos" debe ser
     * mayor o igual que uno y menor o igual que la longitud.
     *
     * @param elem
     * @param pos
     * @return seInserto boolean
     */
    public boolean insertar(Object elem, int pos) {

        boolean seInserto = false;
        if (pos >= 1 && pos <= this.longitud() + 1) {
            Nodo nuevo;
            //Primer caso
            if (pos == 1) {
                this.cabecera = new Nodo(elem, this.cabecera);

            }//Segundo caso
            else {
                Nodo aux = this.cabecera;
                int i = 1;
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                //temporal
                nuevo = new Nodo(elem, aux.getEnlace());
                aux.setEnlace(nuevo);

            }
            //Comentar linea 
            this.longitud += 1;
            //
            seInserto = true;
        }
        return seInserto;
    }

    /**
     * Método para quitar elementos de la lista en una posición indicada. Al
     * sacar un elemento hay tres casos criticos.
     * <p>
     * Cuando es el primer elemento.
     * <p>
     * Cuando se encuentra entre elementos insertados.
     * <p>
     * Cabe mencionar que "pos" debe ser mayor o igual que uno y menor o igual
     * que la longitud.
     *
     * @param pos
     * @return seElimino boolean
     */
    public boolean eliminar(int pos) {

        boolean seElimino = false;
        if (pos >= 1 && pos <= this.longitud()) {
            Nodo aux = this.cabecera;
            //primer caso
            if (pos == 1) {
                this.cabecera = this.cabecera.getEnlace();
            }//segundo caso
            else {
                for (int i = 1; i < pos - 1; i++) {
                    aux = aux.getEnlace();
                }

                aux.setEnlace(aux.getEnlace().getEnlace());
            }
            //Comentar linea
            this.longitud -= 1;
            //
            seElimino = true;
        }
        return seElimino;
    }

    /**
     * Método para recuperar un elemento de la lista con una posición recibida
     * como argumento.
     *
     * @param pos
     * @return elem Object
     */
    public Object recuperar(int pos) {
        Object elem = null;
        int n = longitud();
        if (pos >= 1 && pos <= n) {

            int i = 1;
            Nodo aux = this.cabecera;
            boolean encontro = false;
            while (!encontro && i <= n) {
                if (i == pos) {
                    encontro = true;
                    elem = aux.getElemento();
                } else {
                    aux = aux.getEnlace();
                    i++;
                }
            }
        }
        return elem;
    }

    /**
     * Método para localizar los elementos devolviendo la posición en la que se
     * encuentra. En caso de no encontrarlo devuelve -1.
     *
     * @param elem Object
     * @return posicionElemento Integer
     */
    public int localizar(Object elem) {
        int posicionElemento = -1;
        int i = 1;
        Nodo aux = this.cabecera;
        while (aux != null && !aux.getElemento().equals(elem)) {
            aux = aux.getEnlace();
            i++;
        }
        if (aux != null) {
            posicionElemento = i;
        }
        return posicionElemento;
    }

    /**
     * Método observador longitud(). Devuelve la longitud de la lista.
     *
     * @return longitud Integer
     */
    public int longitud() {
        /*
        
        int longitud = 0;
        if (!this.esVacia()) {
            Nodo aux = this.cabecera;
            while (aux != null) {
                longitud++;
                aux = aux.getEnlace();
            }
        }
         */
        return this.longitud;
    }

    /**
     * Método para clonar la lista, creando una nueva lista con el mismo
     * contenido. Utilizando un método recursivo para enlazar todos los
     * elementos de la lista.
     * <p>
     * Devuelve una lista vacia en caso que la lista a la que se quiere clonar
     * también esta vacia.
     *
     * @return Lista
     */
    @Override

    public Lista clone() {
        Lista clone = new Lista();

        if (!this.esVacia()) {
            //clone.cabecera = recursivoClone(this.cabecera);
            clone.cabecera = new Nodo(this.cabecera.getElemento(), null);
            //clone.longitud = 1;
            clone.longitud = recursivoClone(this.cabecera.getEnlace(), clone.cabecera);
        }
        return clone;
    }

    private int recursivoClone(Nodo auxiliar, Nodo clone) {
        int longitud = 1;
        if (auxiliar != null) {
            clone.setEnlace(new Nodo(auxiliar.getElemento(), null));

            longitud = recursivoClone(auxiliar.getEnlace(), clone.getEnlace()) + 1;
        }
        return longitud;
    }

    /**
     * Método para quitar todos los elementos de la lista dejando la cabecera
     * nula.
     */
    public void vaciar() {
        this.cabecera = null;
        this.longitud = 0;
    }

    /**
     * Método para verificar si la lista está vacia.
     * <p>
     * Devuelve true en caso de estar vacia, false caso contrario.
     *
     * @return
     */
    public boolean esVacia() {
        return this.cabecera == null;
    }

    /**
     * Método que devuelve una cadena de caracteres con la información de la
     * lista.
     *
     * @return cadena String
     */
    @Override
    public String toString() {
        String cadena = "▒ ";

        if (this.esVacia()) {
            cadena += " V-A-C-I-A ";
        } else {
            int n = longitud();
            Nodo aux = this.cabecera;
            while (aux != null) {
                if (aux.getEnlace() == null) {
                    cadena += aux.getElemento() + " ";
                } else {
                    cadena += " " + aux.getElemento() + " ║";

                }
                aux = aux.getEnlace();
            }
        }
        cadena += " ▒";

        return cadena;
    }

    /**
     * Método para invertir la lista. Se realizaron de forma iterativa y
     * recursiva
     * <p>
     * El caso recursivo, se utiliza un auxiliar para mantener los punteros e ir
     * accediendo para llegar al último elemento que sería el último nodo que se
     * crea. Es decir, modificamos la cabecera actual por un nuevo nodo, y a ese
     * nodo le enviamos como argumento: el elemento del auxiliar que recorremos
     * de forma recursiva, y el anterior cabecera.
     * <p>
     * Cuando a la cabecera apunta a un nuevo nodo, el primer elemento que se
     * ingresa al ser recursivo e ir enlazandolos será el último en acceder.
     * <p>
     * El caso iterativo es parecido. Solo que usa una estratura while para
     * verificar que el nodo que se esta recorriendo no sea nulo. :D
     */
    public void invertir() {
        if (!esVacia()) {
            Nodo aux = this.cabecera;
            this.cabecera = null;

            invertirRecursivo(aux);
        }
    }

    private void invertirRecursivo(Nodo auxiliar) {

        if (auxiliar != null) {
            this.cabecera = new Nodo(auxiliar.getElemento(), this.cabecera);
            invertirRecursivo(auxiliar.getEnlace());
        }
    }

    public void invertirIterativo() {
        if (!esVacia()) {
            Nodo aux = this.cabecera;
            this.cabecera = null;
            while (aux != null) {
                this.cabecera = new Nodo(aux.getElemento(), this.cabecera);
                aux = aux.getEnlace();
            }
        }
    }

    /**
     * Método para eliminar repeticiones de la lista.
     *
     * @param aparicion Object
     */

    public void eliminarApariciones(Object aparicion) {
        if (!this.esVacia()) {
            while (this.cabecera != null) {
                if (this.cabecera.getElemento().equals(aparicion)) {
                    this.cabecera = this.cabecera.getEnlace();
                }
            }
            Nodo aux = this.cabecera;
            Nodo enlace;
            while (aux != null) {
                enlace = aux.getEnlace();
                if (enlace != null && enlace.getElemento().equals(aparicion)) {
                    aux.setEnlace(enlace.getEnlace());
                }
                aux = aux.getEnlace();
            }
        }
    }

    /**
     * Método recursivo para eliminar repeticiones de la lista.
     *
     * @param aparicion Object
     */
    public void eliminarAparicionesR(Object aparicion) {
        if (!this.esVacia()) {
            while (this.cabecera.getElemento().equals(aparicion)) {
                this.cabecera = this.cabecera.getEnlace();
                this.longitud--;
            }
            if (this.cabecera != null) {
                eliminarAparRecursivo(this.cabecera, this.cabecera.getEnlace(), aparicion);
            }
        }
    }

    private void eliminarAparRecursivo(Nodo ant, Nodo sgte, Object aparicion) {
        Nodo original = null;
        if (ant != null) {
            if (sgte != null && sgte.getElemento().equals(aparicion)) {
                ant.setEnlace(sgte.getEnlace());
                this.longitud--;
            }
            ant = ant.getEnlace();

            if (ant != null) {

                eliminarAparRecursivo(ant, ant.getEnlace(), aparicion);
            }

        }
    }

    /**
     * Método para modificar un elemento de la lista.
     *
     * @param elem TipoElemento
     * @param elemNuevo TipoElemento
     * @return boolean
     */
    public boolean modificar(Object elem, Object elemNuevo) {
        boolean seModifico = false;
        if (!this.esVacia()) {
            Nodo aux = this.cabecera;
            while (!seModifico && aux != null) {
                if (aux.getElemento().equals(elem)) {
                    aux.setElemento(elemNuevo);;
                    seModifico = true;
                } else {
                    aux = aux.getEnlace();
                }
            }
        }
        return seModifico;
    }

    /**
     * Método que devuelve una lista, recorriendo todos los elementos de la lista que cumplen
     * con la condición de ser multiplo del elemento dado por parámetro.
     *
     * @param num Integer
     * @return Lista
     */

    public Lista obtenerMultiplos(int num) {
        Lista multiplos = new Lista();

        if (!esVacia()) {
            Nodo aux = this.cabecera;
            Nodo aux2 = multiplos.cabecera;

            int i = 1;

            while (aux != null) {
                if (i % num == 0) {
                    multiplos.cabecera.setEnlace(new Nodo(aux.getElemento(), multiplos.cabecera));
                }
                aux = aux.getEnlace();
                i++;
            }
        }
        return multiplos;
    }

    /**
     * Método que busca un elemento, una vez encontrado inserta un elemento despues del mismo.
     * @param valor1 TipoElemento
     * @param valor2 TipoElemento
     */

    public void insertarPosterior1(Object valor1, Object valor2) {

        if (!this.esVacia()) {
            Nodo aux = this.cabecera;
            Nodo temporal;

            while (aux != null) {
                if (aux.getElemento().equals(valor1)) {

                    temporal = aux.getEnlace();
                    aux.setEnlace(new Nodo(valor2, temporal));
                    System.out.println(aux.getElemento());
                    aux = aux.getEnlace().getEnlace();
                } else {
                    aux = aux.getEnlace();
                }
            }
        }
    }
    /**
     * Método que busca un elemento, una vez encontrado inserta un elemento despues del mismo.
     * @param valor1 TipoElemento
     * @param valor2 TipoElemento
     */
    public void insertarPosterior(Object valor1, Object valor2) {
        if (!this.esVacia()) {
            if (this.longitud == 1 && this.cabecera.getElemento().equals(valor1)) {
                this.cabecera = new Nodo(valor2, this.cabecera);
            } else {
                Nodo aux = this.cabecera;
                boolean esPrimero = true;
                while (aux != null) {
                    if (esPrimero && aux.getElemento().equals(valor1)) {
                        this.cabecera = new Nodo(valor2, this.cabecera);
                        esPrimero = false;
                    } else if (aux.getElemento().equals(valor1)) {
                        if (aux.getEnlace() == null) {
                            aux.setEnlace(new Nodo(valor2, null));
                        } else {
                            Nodo temp = aux.getEnlace();
                            aux.setEnlace(new Nodo(valor2, temp));
                        }

                    }
                    aux = aux.getEnlace();
                }
            }
        }
    }

    /**
     * Método que busca un elemento, una vez encontra elimina su siguiente, en caso de que el siguiente
     * sea el elemento buscado no se elimina.
     * @param buscado
     */

    public void eliminarSiguiente(Object buscado) {
        if (this.cabecera != null) {
            eliminarSiguienteAuxiliar(this.cabecera, buscado);
        }
    }

    private void eliminarSiguienteAuxiliar(Nodo aux, Object buscado) {

        while (aux != null) {

            if (aux.getElemento().equals(buscado)) {
                Nodo sgte = aux.getEnlace();
                //Controlar en caso de que el siguiente sea igual al buscado
                if (sgte != null && !sgte.getElemento().equals(buscado)) {
                    aux.setEnlace(sgte.getEnlace());
                }else{
                    //Caso que sean iguales o que siguiente sea siguiente
                }

            }
            aux = aux.getEnlace();
        }
    }

    public void modificarLista(Lista original) {
        if(!original.esVacia()){
            this.vaciar();
            this.cabecera = new Nodo(original.cabecera.getElemento(), null);
            //clone.longitud = 1;
            this.longitud = recursivoClone(original.cabecera.getEnlace(), this.cabecera);
        }

    }


}
