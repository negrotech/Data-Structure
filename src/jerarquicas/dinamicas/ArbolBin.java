package jerarquicas.dinamicas;

import lineales.dinamico.Cola;
import lineales.dinamico.Lista;

/**
 * @author Martin
 */
public class ArbolBin {

    private NodoArbol raiz;

    /**
     * Arbol binario es conjunto finito de nodos, donde se distingue un nodo
     * llamado raiz, que desde la raiz se puede acceder a todo la estructura. La
     * raiz se instancia en nulo. Un arbol binario puede tener un máximo de 0 a
     * 2 hijos.
     */
    public ArbolBin() {
        this.raiz = null;
    }

    /**
     * *
     * Método encargado de agregar elementos al arbol binario. se necesita de de
     * elemento padre, el elemento que se va a agregar y en que posicion
     * (izquierda o derecha).
     * <p>
     * Si es el primer elemento, se crea un nuevo NodoArbol y se lo asigna a la
     * raiz.
     * <p>
     * En caso de que ya halla elementos, busca el nodo padre y verifica que el
     * padre exista y que la posicion elegida no este ocupada.
     *
     * @param elemNuevo de tipo Object
     * @param elemPadre de tipo Object
     * @param lugar     de tipo CHAR
     * @return
     */
    public boolean insertar(Object elemNuevo, Object elemPadre, char lugar) {
        boolean seInserto = true;
        if (this.raiz == null) {
            this.raiz = new NodoArbol(elemNuevo);
        } else {
            NodoArbol padre = obtenerNodo(elemPadre, this.raiz);
            if (padre != null) {
                if (lugar == 'I' && padre.getIzquierdo() == null) {
                    padre.setIzquierdo(new NodoArbol(elemNuevo));

                } else {
                    if (lugar == 'D' && padre.getDerecho() == null) {
                        padre.setDerecho(new NodoArbol(elemNuevo));

                    } else {
                        seInserto = false;
                    }
                }
            } else {
                seInserto = false;
            }
        }

        return seInserto;
    }

    /**
     * Método auxiliar encargado de buscar un nodo en el árbol. Su búsqueda es
     * de orden lineal. En el peores de los casos va hasta el último elemento.
     * <p>
     * En caso de no encontrarlo devuelve null.
     *
     * @param elem de Tipo Object
     * @param aux  de Tipo NodoArbol
     * @return
     */
    public NodoArbol obtenerNodo(Object elem, NodoArbol aux) {
        NodoArbol buscado = null;

        if (aux != null) {
            //Preguntar a la raiz

            if (aux.getElemento().equals(elem)) {
                buscado = aux;
            } else {
                buscado = obtenerNodo(elem, aux.getIzquierdo());
                if (buscado == null) {
                    buscado = obtenerNodo(elem, aux.getDerecho());
                }
            }
        }
        return buscado;
    }

    public boolean alterarParte(Object hi, Object hd, Object pp) {
        boolean seAltero = false;
        if (this.raiz != null) {
            //Obtener nodo
            NodoArbol aux = this.raiz;

            if (aux != null) {
                //Preguntar a la raiz
                seAltero = alterarParteRecursivo(aux, hi, hd, pp);
            }

        }
        return seAltero;
    }

    private boolean alterarParteRecursivo(NodoArbol aux, Object hi, Object hd, Object pp) {
        boolean encontro = false;
        if (aux != null) {

            if (aux.getElemento().equals(pp)) {
                encontro = true;
                if (aux.getIzquierdo() != null) {
                    aux.getIzquierdo().setElemento(hi);
                } else {
                    aux.setIzquierdo(new NodoArbol(hi));
                }

                if (aux.getDerecho() != null) {
                    aux.getDerecho().setElemento(hd);
                } else {
                    aux.setDerecho(new NodoArbol(hd));
                }

            } else {
                encontro = alterarParteRecursivo(aux.getIzquierdo(), hi, hd, pp);
                if (!encontro) {
                    encontro = alterarParteRecursivo(aux.getDerecho(), hi, hd, pp);
                }
            }
        }
        return encontro;
    }

    /**
     * Método de recorrido de arbol Preorden, primero visita la raíz, luego
     * recorre al hijo izquierdo seguido al hijo derecho.
     * <p>
     * Retorna una lista ordenadas en Preorden.
     *
     * @return listaPreOrden Lista
     */
    public Lista listarPreorden() {
        Lista listaPreOrden = new Lista();
        if (!this.esVacio()) {
            NodoArbol aux = this.raiz;
            preOrdenRecursivo(aux, listaPreOrden);
        }
        return listaPreOrden;
    }

    private void preOrdenRecursivo(NodoArbol aux, Lista listaPreOrden) {

        if (aux != null) {
            listaPreOrden.insertar(aux.getElemento(), listaPreOrden.longitud() + 1);
            preOrdenRecursivo(aux.getIzquierdo(), listaPreOrden);
            preOrdenRecursivo(aux.getDerecho(), listaPreOrden);
        }

    }

    /**
     * Método de recorrido de arbol Inorden, primero recorre al hijo izquierdo,
     * luego visita a la raíz seguido el hijo derecho.
     * <p>
     * Retorna una lista ordenadas en Inorden.
     *
     * @return listaInOrden Lista
     */
    public Lista listarInorden() {
        Lista listaInOrden = new Lista();
        if (!this.esVacio()) {
            NodoArbol aux = this.raiz;
            inOrdenRecursivo(aux, listaInOrden);
        }
        return listaInOrden;
    }

    private void inOrdenRecursivo(NodoArbol aux, Lista listaInOrden) {

        if (aux != null) {
            inOrdenRecursivo(aux.getIzquierdo(), listaInOrden);
            listaInOrden.insertar(aux.getElemento(), listaInOrden.longitud() + 1);
            inOrdenRecursivo(aux.getDerecho(), listaInOrden);
        }

    }

    /**
     * Método de recorrido de arbol Posorden, primero recorre al hijo izquierdo,
     * seguido el hijo derecho, por último visita a la raíz .
     * <p>
     * Retorna una lista ordenadas en Postorden.
     *
     * @return listaPostOrden Lista
     */
    public Lista listarPosorden() {
        Lista listaPostOrden = new Lista();
        if (!this.esVacio()) {
            NodoArbol aux = this.raiz;
            postOrdenRecursivo(aux, listaPostOrden);
        }
        return listaPostOrden;
    }

    private void postOrdenRecursivo(NodoArbol aux, Lista listapostOrden) {

        if (aux != null) {
            postOrdenRecursivo(aux.getIzquierdo(), listapostOrden);
            postOrdenRecursivo(aux.getDerecho(), listapostOrden);
            listapostOrden.insertar(aux.getElemento(), listapostOrden.longitud() + 1);
        }

    }

    /**
     * Método para buscar el padre de un elemento del árbol. En caso de no
     * encontrarlo devuelve null.
     *
     * @param elem Object
     * @return padre Object
     */
    public Object padre(Object elem) {
        Object padre = null;
        if (!this.esVacio()) {
            if (this.raiz.getElemento().equals(elem)) {
                padre = null;
            } else {
                NodoArbol aux = buscarPadre(this.raiz, elem);
                if (aux != null) {
                    padre = aux.getElemento();
                    System.out.println("PADRE de " + elem + ": " + padre);
                }
            }
        }
        return padre;
    }

    /**
     * buscarPadre
     * <p>
     * Método encargado de buscar el padre de un elemento recibido como
     * argumento para encontrar al padre se debe buscar en sus hijos, si uno de
     * esos hijos coincide con el elemento recibido como args, entonces
     * asignamos el puntero a un variable antes instanciada en null y lo
     * retornamos
     * <p>
     * para cortar la recursiva utilizamos padre==null, en caso de encontrar,
     *
     * @param aux  NodoArbol
     * @param elem Object
     * @return padre NodoArbol
     */
    private NodoArbol buscarPadre(NodoArbol aux, Object elem) {
        NodoArbol padre = null;

        if (aux != null) {
            //
            if (aux.getIzquierdo() != null && aux.getIzquierdo().getElemento().equals(elem)) {

                padre = aux;

            } else if (padre == null && aux.getDerecho() != null && aux.getDerecho().getElemento().equals(elem)) {

                padre = aux;

            } else {
                padre = buscarPadre(aux.getIzquierdo(), elem);
                if (padre == null) {
                    padre = buscarPadre(aux.getDerecho(), elem);
                }
            }
        }
        return padre;
    }

    /**
     * Método que devuelve la altura de un arbol, si un arbol está vacio
     * devuelve -1.
     *
     * @return
     */
    public int altura() {
        int altura = -1;
        if (!this.esVacio()) {
            altura = alturaRecursiva(this.raiz);
        }
        return altura;
    }

    /**
     * Método recursivo de altura, utiliza tres variables más la que se recibe
     * por argumento nodo que es la raiz del arbol. altura, aux1 y aux2 aux1
     * guarda la altura del hijo izquierdo aux2 guarda la altura del hijo
     * derecho
     * <p>
     * Dependiendo de cual es más grande entre aux1 y aux2 se lo asignan a la
     * altura que se retorna.
     *
     * @param aux NodoArbol
     * @return altura Integer
     */
    private int alturaRecursiva(NodoArbol nodo) {

        int altura, aux1, aux2;

        if (nodo == null) {
            altura = -1;
        } else {
            aux1 = alturaRecursiva(nodo.getIzquierdo()) + 1;
            aux2 = alturaRecursiva(nodo.getDerecho()) + 1;

            //altura = Math.max(aux2, aux1);
            if (aux1 > aux2) {
                altura = aux1;
            } else if (aux1 < aux2) {
                altura = aux2;
            } else {
                altura = aux1;
            }
        }
        return altura;
    }

    /**
     * Método que cuenta el recorrido desde la raiz hasta el nodo buscado
     * devolviendo en que nivel se encuentra en el arbol.
     *
     * @param elem Object
     * @return nivel Integer
     */
    public int nivel(Object elem) {
        int nivel = -1;

        if (!esVacio()) {
            if (this.raiz.getElemento().equals(elem)) {
                nivel = 0;
            } else {
                nivel = nivelRecursivo(this.raiz, elem);
            }
        }
        return nivel;
    }

    /**
     * Método auxiliar de nivel, una vez encontrado el elemento va contando
     * hacia atras en la recursiva.
     *
     * @param aux
     * @param elem
     * @return
     */
    private int nivelRecursivo(NodoArbol aux, Object elem) {
        int nivel;
        if (aux == null) {
            nivel = -1;
        } else {
            if (aux.getElemento().equals(elem)) {
                nivel = 0;
            } else {
                nivel = nivelRecursivo(aux.getIzquierdo(), elem);
                //Si encuentra el elemento va contando hacia atras en la recursiva
                if (nivel != -1) {
                    nivel += 1;
                } else {
                    nivel = nivelRecursivo(aux.getDerecho(), elem);
                    if (nivel != -1) {
                        nivel += 1;
                    }
                }
            }
        }
        return nivel;
    }

    /**
     * Método de recorrido por niveles utilizando una cola auxiliar. En caso de
     * que el árbol este vacio, devuelve la lista vacia.
     *
     * @return listaPorNivel Lista
     */
    public Lista listarPorNiveles() {
        Lista listaPorNivel = new Lista();
        if (!this.esVacio()) {
            Cola Q = new Cola();

            Q.poner(this.raiz);

            while (!Q.esVacia()) {
                NodoArbol nodoActual = (NodoArbol) Q.obtenerFrente();
                Q.sacar();

                listaPorNivel.insertar(nodoActual.getElemento(), listaPorNivel.longitud() + 1);
                if (nodoActual.getIzquierdo() != null) {
                    Q.poner(nodoActual.getIzquierdo());
                }
                if (nodoActual.getDerecho() != null) {
                    Q.poner(nodoActual.getDerecho());
                }

            }
        }
        return listaPorNivel;
    }

    /**
     * Método para clonar el árbol creando nuevos nodos, pero con el mismo
     * contenido.
     *
     * @return arbolClone ArbolBinario
     */
    @Override
    public ArbolBin clone() {
        ArbolBin arbolClone = new ArbolBin();

        if (!this.esVacio()) {
            arbolClone.raiz = cloneRecursivo(this.raiz);

        }
        return arbolClone;
    }

    public ArbolBin cloneInvertido() {
        ArbolBin cloneInv = new ArbolBin();

        if (!this.esVacio()) {
            cloneInv.raiz = cloneInvertidoRecursivo(this.raiz);
        }
        return cloneInv;
    }

    private NodoArbol cloneInvertidoRecursivo(NodoArbol aux) {

        NodoArbol nodo = null;

        if (aux != null) {
            nodo = new NodoArbol(aux.getElemento(), cloneInvertidoRecursivo(aux.getDerecho()), cloneInvertidoRecursivo(aux.getIzquierdo()));
        }
        return nodo;

    }

    /**
     * Método auxiliar de clone para recorrer el arbol. Va creando nuevos nodos
     * y los enlaza en la recursiva, tanto como los hijos izquierdo como los
     * derechos.
     *
     * @param aux
     * @return
     */
    private NodoArbol cloneRecursivo(NodoArbol aux) {
        NodoArbol clone = null;

        if (aux != null) {
            clone = new NodoArbol(aux.getElemento(),
                    cloneRecursivo(aux.getIzquierdo()),
                    cloneRecursivo(aux.getDerecho()));
        }
        return clone;
    }

    private NodoArbol cloneRecursivoExtendido(NodoArbol aux) {
        NodoArbol clone;
        if (aux == null) {
            clone = null;
        } else {
            clone = new NodoArbol(aux.getElemento());
            if (aux.getIzquierdo() != null) {
                clone.setIzquierdo(cloneRecursivo(aux.getIzquierdo()));
            }
            if (aux.getDerecho() != null) {
                clone.setDerecho(cloneRecursivo(aux.getDerecho()));
            }
        }
        return clone;
    }

    /**
     * Método para vaciar el árbol dejando sin nodos.
     */
    public void vaciar() {
        this.raiz = null;
    }

    /**
     * *
     * Verifica si el árbol está vacio. Devuelve true si está vacio, false caso
     * contrario.
     *
     * @return
     */
    public boolean esVacio() {
        return (this.raiz == null);
    }

    /**
     * *
     * Método para mostrar la información del árbol binario. Se muestra de la
     * siguiente forma:
     * <p>
     * TipoELemento Raiz, TipoElemento HijoIz: * / TipoElemento, HijoDer: * /
     * TipoElemento
     *
     * @return
     */
    @Override
    public String toString() {
        String cadena;
        if (this.raiz != null) {
            cadena = preOrdenaux(this.raiz);
        } else {
            cadena = "[Esta vacio]";
        }
        return cadena;
    }

    private String preOrdenaux(NodoArbol aux) {
        String cadena = "";
        if (aux != null) {
            if (aux.getDerecho() != null && aux.getIzquierdo() != null) {
                cadena = "\n " + aux.getElemento() + " Hijo izq: " + aux.getIzquierdo().getElemento() + " Hijo derecho: "
                        + aux.getDerecho().getElemento();
            } else if (aux.getDerecho() != null) {
                cadena = "\n " + aux.getElemento() + " Hijo izq: * " + " Hijo derecho: " + aux.getDerecho().getElemento();

            } else if (aux.getIzquierdo() != null) {
                cadena = "\n " + aux.getElemento() + " Hijo izq: " + aux.getIzquierdo().getElemento()
                        + " Hijo derecho: *";
            } else {
                cadena = "\n " + aux.getElemento() + " Hijo izq: * Hijo derecho: *";
            }
            cadena += preOrdenaux(aux.getIzquierdo());
            cadena += preOrdenaux(aux.getDerecho());
        }
        return cadena;
    }

    /**
     * Método que devuelve un listado de los nodos que son ancestros.
     *
     * @return ancestros Lista
     */
    public Lista ancestros() {

        Lista ancestros = new Lista();

        if (!esVacio()) {
            ancestrosRecursivo(this.raiz, ancestros);
        }
        return ancestros;
    }

    private int ancestrosRecursivo(NodoArbol aux, Lista ancestros) {
        int altura, altura2;

        if (aux == null) {
            altura = -1;
        } else {
            altura = ancestrosRecursivo(aux.getIzquierdo(), ancestros) + 1;
            altura2 = ancestrosRecursivo(aux.getDerecho(), ancestros) + 1;

            if (altura >= 2) {
                ancestros.insertar(aux.getElemento(), ancestros.longitud() + 1);
            }
            if (altura2 >= 2) {
                ancestros.insertar(aux.getElemento(), ancestros.longitud() + 1);
            }

        }
        return altura;
    }

    public boolean verificaPatron(Lista l1) {
        boolean coincidePatron = false;

        if (!this.esVacio()) {
            int i = 1;
            coincidePatron = this.verificaPatronRecursivo(this.raiz, l1, i);
        }
        return coincidePatron;
    }

    private boolean verificaPatronRecursivo(NodoArbol aux, Lista l1, int cont) {
        boolean coincide = false;

        if (aux != null) {

            System.out.println("aux: " + aux.getElemento() + " lista: " + l1.recuperar(cont));
            if (l1.recuperar(cont).equals(aux.getElemento())) {
                if (cont == l1.longitud()) {
                    coincide = true;
                } else {
                    coincide = verificaPatronRecursivo(aux.getIzquierdo(), l1, cont + 1);
                    if (coincide != true) {
                        coincide = verificaPatronRecursivo(aux.getDerecho(), l1, cont + 1);
                    }
                }
            }
        }

        return coincide;
    }

    /**
     * Método que devuelve una lista de las hojas del arbol.
     *
     * @return frontera Lista
     */
    public Lista frontera() {
        Lista frontera = new Lista();

        if (!this.esVacio()) {
            fronteraRecursiva(this.raiz, frontera);
        }
        return frontera;
    }

    private void fronteraRecursiva(NodoArbol aux, Lista frontera) {

        if (aux != null) {
            if (aux.getIzquierdo() == null && aux.getDerecho() == null) {
                frontera.insertar(aux.getElemento(), frontera.longitud() + 1);
            } else {
                fronteraRecursiva(aux.getIzquierdo(), frontera);
                fronteraRecursiva(aux.getDerecho(), frontera);
            }
        }
    }


}



