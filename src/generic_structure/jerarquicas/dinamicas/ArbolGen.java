/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generic_structure.jerarquicas.dinamicas;

import generic_structure.lineales.dinamico.Cola;
import generic_structure.lineales.dinamico.Lista;

/**
 * @author Martin
 */
public class ArbolGen {

    private NodoGenerico raiz;

    public ArbolGen() {
        this.raiz = null;
    }

    /**
     * <b>insertar(TipoElemento elemNuevo, TipoElemento elemPadre)</b><br>
     * <p>Método para agregar elementos al arbol generico.
     * Ingresa como parámetro el elemento que desea agregar de tipo Object y
     * Elemento padre de tipo Object. En caso ser el primero en insertarse, crea un nuevo
     * NodoGenerico y lo agrega como primer elemento.
     * </p>
     * <p>Se agrega en el recorre hasta el último hermano y lo inserta.
     * Están contempladas las dos formas.</p>
     *
     * @param elemNuevo Object
     * @param elemPadre Object
     * @return seInserto boolean
     */

    public boolean insertar(Object elemNuevo, Object elemPadre) {
        boolean seInserto = false;

        if (!this.esVacio()) {
            //En caso de no estar vacio, busca el nodo y verifica que no sea nulo.
            NodoGenerico padre = buscaNodo(elemPadre);

            if (padre != null) {
                NodoGenerico hijo = padre.getHijoIzquierdo();
                //En caso de ser el primer hijo, se crea un nuevo nodo
                if (hijo == null) {
                    padre.setHijoIzquierdo(new NodoGenerico(elemNuevo, null, null));
                } else {
                    //Va hasta su último hermano
                    /*
                    while (hijo.getHermanoDerecho() != null) {
                        hijo = hijo.getHermanoDerecho();
                    }
                    hijo.setHermanoDerecho(new NodoGenerico(elemNuevo, null, null));
*/
                    //Inserción en el primer elemento

                    padre.setHijoIzquierdo(new NodoGenerico(elemNuevo, null, hijo));
                }
                seInserto = true;

            }
        } else {
            //En caso de ser el primer nodo, crea un nuevo nodo y lo apunta a la raiz.
            this.raiz = new NodoGenerico(elemNuevo, null, null);
            seInserto = true;
        }

        return seInserto;
    }

    /**
     * Método privado para encontrar nodos en el arbol.
     * Se posiciona en el padre y recorre a sus hijos hasta encontrarlo
     * en caso de no encontrar, devuelve null.
     *
     * @param elemPadre Object
     * @return nodoBuscado Object
     */

    private NodoGenerico buscaNodo(Object elemPadre) {
        NodoGenerico nodoBuscado = null;

        if (!this.esVacio()) {
            nodoBuscado = buscaNodoRecursivo(this.raiz, elemPadre);
        }
        return nodoBuscado;
    }

    /**
     * Método recursivo de buscarNodo, utilizado para recorrer el arbol y encontrar el elemento
     *
     * @param aux       NodoGenerico
     * @param elemPadre Object
     * @return nodoBuscado NodoGenerico
     */

    private NodoGenerico buscaNodoRecursivo(NodoGenerico aux, Object elemPadre) {
        NodoGenerico nodoBuscado = null;

        if (aux != null) {
            if (aux.getElem().equals(elemPadre)) {
                nodoBuscado = aux;
            } else {
                NodoGenerico hijoIzq = aux.getHijoIzquierdo();
                while (nodoBuscado == null && hijoIzq != null) {
                    nodoBuscado = buscaNodoRecursivo(hijoIzq, elemPadre);
                    hijoIzq = hijoIzq.getHermanoDerecho();
                }

            }
        }
        return nodoBuscado;
    }

    /**
     * Método que devuelve los ancestros (padre y abuelos) de un nodo en especifico.
     * Devuelve una lista con los ancestros, en caso de no encontrar o ser la raiz el elemento buscado
     * devuelve una lista vacia.
     *
     * @param elementoBuscado Object
     * @return ancestros Lista
     */

    public Lista ancestros(Object elementoBuscado) {
        Lista ancestros = new Lista();
        if (!this.esVacio()) {
            if (!this.raiz.getElem().equals(elementoBuscado)) {
                ancestrosRecursivo(ancestros, this.raiz, elementoBuscado);
            }
        }
        return ancestros;
    }

    /**
     * Método privado para recorrer al albol, utiliza una variable para cortar la recursiva
     * en caso de encontrar el elemento.
     *
     * @param ancestros       Lista
     * @param aux             NodoGenerico
     * @param elementoBuscado Object
     * @return encontro boolean
     */

    private boolean ancestrosRecursivo(Lista ancestros, NodoGenerico aux, Object elementoBuscado) {
        boolean encontro = false;

        if (aux != null) {

            NodoGenerico hijo = aux.getHijoIzquierdo();
            while (!encontro && hijo != null) {
                if (hijo.getElem().equals(elementoBuscado)) {
                    encontro = true;
                    ancestros.insertar(aux.getElem(), ancestros.longitud() + 1);
                }
                hijo = hijo.getHermanoDerecho();

            }
            hijo = aux.getHijoIzquierdo();

            while (!encontro && hijo != null) {
                encontro = ancestrosRecursivo(ancestros, hijo, elementoBuscado);
                if (encontro) {
                    ancestros.insertar(aux.getElem(), ancestros.longitud() + 1);
                }
                hijo = hijo.getHermanoDerecho();

            }
        }
        return encontro;

    }

    /**
     * Método para conocer la altura de un arbol.
     * Si el arbol está vacio devuelve -1, si el arbol es raiz y hoja
     * devuelve 0. Utiliza una variable para guardar la máxima altura por rama.
     *
     * @return altura Integer
     */

    public int altura() {
        int altura = -1;

        if (!this.esVacio()) {
            altura = alturaRecursiva(this.raiz);
        }
        return altura;
    }

    /**
     * Método privado de altura que recorre al arbol comparando las alturas de la recursiva.
     * Va hasta el último nodo y devuelve -1, por cada bajada se le agrega uno.
     *
     * @param aux NodoGenerico
     * @return altura Integer
     */

    private int alturaRecursiva(NodoGenerico aux) {
        int altura, altAux1;

        if (aux == null) {
            altura = -1;
        } else {
            NodoGenerico hijo = aux.getHijoIzquierdo();
            altura = 0;
            while (hijo != null) {
                altAux1 = alturaRecursiva(hijo) + 1;
                if (altAux1 > altura) {
                    altura = altAux1;
                }
                hijo = hijo.getHermanoDerecho();
            }
        }
        return altura;

    }

    /**
     * Método para listar todos los ancestros que tiene el arbol.
     *
     * @return
     */

    public Lista listarAncestros() {
        Lista ancestros = new Lista();
        if (!this.esVacio()) {

            listarAncestrosRecursivo(ancestros, this.raiz);
        }
        return ancestros;
    }

    /**
     * Método privado de listarAncestros que recorre al arbol y va comparando si el nodo tiene a más de dos nodos
     * enlazados.
     *
     * @param ancestros Lista
     * @param aux       NodoGenerico
     * @return altura Integer
     */

    private int listarAncestrosRecursivo(Lista ancestros, NodoGenerico aux) {
        int altura = -1;

        if (aux != null) {
            NodoGenerico hijo = aux.getHijoIzquierdo();

            while (hijo != null) {
                altura += listarAncestrosRecursivo(ancestros, hijo) + 1;
                if (altura > 2) {
                    ancestros.insertar(aux.getElem(), ancestros.longitud() + 1);
                }

                hijo = hijo.getHermanoDerecho();

            }

        }
        return altura;

    }

    /**
     * Método para vaciar el arbol
     * No retorna.
     */

    public void vaciar() {
        this.raiz = null;
    }

    /**
     * Método que devuelve el nivel de un arbol. Si un árbol es raíz y hoja devuelve 0. Si arbol esta
     * vacio devuelve -1.
     *
     * @param elemento Object
     * @return nivel Integer
     */

    public int nivel(Object elemento) {
        int nivel = -1;

        if (!this.esVacio()) {
            if (this.raiz.getElem().equals(elemento)) {
                nivel = 0;
            } else {
                nivel = nivelRecursivo(this.raiz, elemento);
            }
        }

        return nivel;
    }

    /**
     * Método privado de nivel.
     *
     * @param aux     NodoGenerico
     * @param element Object
     * @return nivel Integer
     */

    private int nivelRecursivo(NodoGenerico aux, Object element) {

        int nivel = -1;

        if (aux != null) {
            if (aux.getElem().equals(element)) {
                nivel = 0;
            } else {
                NodoGenerico hijo = aux.getHijoIzquierdo();
                while (nivel == -1 && hijo != null) {
                    nivel = nivelRecursivo(hijo, element);
                    if (nivel != -1) {
                        nivel += 1;
                    }
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
        return nivel;
    }

    /**
     * <b>pertenece(Object elementoBuscado)</b><br>
     * <p>Método para verificar si pertenece el elemento al arbol definido.</p>
     *
     * @param elementoBuscado Object
     * @return pertenece boolean
     */

    public boolean pertenece(Object elementoBuscado) {
        boolean pertenece = false;

        if (!this.esVacio()) {
            pertenece = perteneceRecursivo(this.raiz, elementoBuscado);
        }

        return pertenece;

    }

    /**
     * <b>perteneceRecursivo(NodoGenerico aux, Object elementoBuscado) </b>
     * <p>Método privado que recorre el arbol de forma recursiva, utiliza una variable boolean en
     * caso de encontrar el elemento buscado. En caso de no encontrar devuelve false.</p>
     *
     * @param aux             NodoGenerico
     * @param elementoBuscado Object
     * @return
     */

    private boolean perteneceRecursivo(NodoGenerico aux, Object elementoBuscado) {
        boolean pertenece = false;

        if (aux != null) {
            if (aux.getElem().equals(elementoBuscado)) {
                pertenece = true;
            } else {
                NodoGenerico hijo = aux.getHijoIzquierdo();

                while (!pertenece && hijo != null) {
                    pertenece = perteneceRecursivo(hijo, elementoBuscado);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
        return pertenece;
    }

    /**
     * <b>toString()</b>
     * <p>Método debug, para visualizar los nodos del arbol con un formato definido. </p>
     *
     * @return cadena String
     */

    @Override
    public String toString() {
        String cadena = "";

        if (!this.esVacio()) {
            cadena = toStringAux(this.raiz);
        } else {
            cadena += "ARBOL VACIO";
        }
        return cadena;
    }

    /**
     * Método privado para recorrer el arbol retornando una cadena con el formato: padre: - hijo: - +....
     *
     * @param aux NodoGenerico
     * @return cadena String
     */

    private String toStringAux(NodoGenerico aux) {

        String cadena = "";

        if (aux != null) {

            cadena += "\n Padre: " + aux.getElem();
            NodoGenerico hijo = aux.getHijoIzquierdo();
            cadena += " Hijos: ";

            if (hijo == null) {
                cadena += "*";
            } else {
                while (hijo != null) {
                    if (hijo.getHermanoDerecho() == null) {
                        cadena += "" + hijo.getElem();
                    } else {
                        cadena += hijo.getElem() + " - ";
                    }
                    hijo = hijo.getHermanoDerecho();

                }
                hijo = aux.getHijoIzquierdo();

                while (hijo != null) {
                    cadena += toStringAux(hijo);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }

        return cadena;

    }

    /**
     * <b>padre(Object elemEnc)</b>
     * <p>Método encargado de encontrar el padre del elemento enviado como argumento.</p>
     * <p>En caso de no encontrarlo, devuelve null.</p>
     *
     * @param elemEnc Object
     * @return padre Object
     */

    public Object padre(Object elemEnc) {
        Object padre = null;

        if (!this.esVacio()) {
            if (!this.raiz.getElem().equals(elemEnc)) {
                NodoGenerico existePadre = padreRecursivo(this.raiz, elemEnc);
                if (existePadre != null) {
                    padre = existePadre.getElem();
                }

            }
        }
        return padre;
    }

    private NodoGenerico padreRecursivo(NodoGenerico aux, Object elementoEnc) {

        NodoGenerico padre = null;

        if (aux != null) {
            NodoGenerico hijo = aux.getHijoIzquierdo();
            while (padre == null && hijo != null) {
                if (hijo.getElem().equals(elementoEnc)) {
                    padre = aux;
                }
                hijo = hijo.getHermanoDerecho();
            }
            hijo = aux.getHijoIzquierdo();
            while (padre == null && hijo != null) {
                padre = padreRecursivo(hijo, elementoEnc);
                hijo = hijo.getHermanoDerecho();

            }

        }
        return padre;

    }

    /**
     * <b>esVacio()</b>
     * <p>Método para comprobar si un arbol está vacio.</p>
     *
     * @return estaVacio boolean
     */

    public boolean esVacio() {
        return (this.raiz == null);
    }

    /**
     * <b>listarPreorden()</b>
     * <p>Método que devuelve una lista con los elementos ordenados en preorden</p>
     *
     * @return preorden Lista
     */

    public Lista listarPreorden() {
        Lista preorden = new Lista();

        if (!this.esVacio()) {
            preordenRecursivo(this.raiz, preorden);
        }
        return preorden;
    }

    private void preordenRecursivo(NodoGenerico aux, Lista list) {

        if (aux != null) {
            list.insertar(aux.getElem(), list.longitud() + 1);
            NodoGenerico hijo = aux.getHijoIzquierdo();
            while (hijo != null) {
                preordenRecursivo(hijo, list);
                hijo = hijo.getHermanoDerecho();
            }
        }
    }

    /**
     * <b>listarInorden()</b>
     * <p>Método que devuelve una lista con los elementos ordenados en inorden</p>
     *
     * @return inorden Lista
     */

    public Lista listarInorden() {
        Lista inOrden = new Lista();

        if (!this.esVacio()) {
            inordenRecursivo(this.raiz, inOrden);
        }
        return inOrden;
    }

    private void inordenRecursivo(NodoGenerico aux, Lista list) {

        if (aux != null) {
            NodoGenerico hijo = aux.getHijoIzquierdo();
            //Va hasta el ultimo hijo
            if (hijo != null) {
                inordenRecursivo(hijo, list);
            }

            //Visita el nodo
            list.insertar(aux.getElem(), list.longitud() + 1);

            if (hijo != null) {
                hijo = aux.getHijoIzquierdo().getHermanoDerecho();
                while (hijo != null) {
                    inordenRecursivo(hijo, list);

                    hijo = hijo.getHermanoDerecho();
                }
            }

        }
    }


    /**
     * <b>listarPosorden()</b>
     * <p>Método que devuelve una lista con los elementos ordenados en posorden</p>
     *
     * @return posorden Lista
     */

    public Lista listarPosorden() {
        Lista posOrden = new Lista();

        if (!this.esVacio()) {
            posOrdenRecursivo(this.raiz, posOrden);
        }
        return posOrden;
    }

    private void posOrdenRecursivo(NodoGenerico aux, Lista list) {

        if (aux != null) {
            NodoGenerico hijo = aux.getHijoIzquierdo();
            //Va hasta el ultimo hijo
            if (hijo != null) {
                posOrdenRecursivo(hijo, list);
            }

            //Visita el nodo


            if (hijo != null) {
                hijo = aux.getHijoIzquierdo().getHermanoDerecho();

                while (hijo != null) {
                    posOrdenRecursivo(hijo, list);

                    hijo = hijo.getHermanoDerecho();
                }
            }
            list.insertar(aux.getElem(), list.longitud() + 1);
        }

    }

    /**
     * <b>listarPorNiveles()</b>
     * <p>Método que devuelve una lista ordenada por niveles. Utiliza una estructura Cola auxiliar.
     * </p>
     *
     * @return listaPorNivel Lista
     */

    public Lista listarPorNiveles() {
        Lista listaPorNivel = new Lista();

        if (!this.esVacio()) {
            Cola Q = new Cola();

            Q.poner(this.raiz);

            while (!Q.esVacia()) {
                NodoGenerico nodoActual = (NodoGenerico) Q.obtenerFrente();
                Q.sacar();

                listaPorNivel.insertar(nodoActual.getElem(), listaPorNivel.longitud() + 1);
                NodoGenerico hijo = nodoActual.getHijoIzquierdo();
                while (hijo != null) {
                    Q.poner(hijo);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
        return listaPorNivel;
    }

    /**
     * <b>grado()</b>
     * <p>Método devuelve el numero máximo de hijos del arbol.
     * </p>
     *
     * @return grado Integer
     */
    public int grado() {
        int grado = -1;

        if (!this.esVacio()) {
            grado = gradoRecursivo(this.raiz);
        }
        return grado;
    }

    private int gradoRecursivo(NodoGenerico aux) {
        int grado = 0, gradoMax = 0;

        if (aux != null) {

            NodoGenerico hijo = aux.getHijoIzquierdo();

            while (hijo != null) {
                gradoMax += 1;
                hijo = hijo.getHermanoDerecho();
            }
            hijo = aux.getHijoIzquierdo();
            while (hijo != null) {
                grado = gradoRecursivo(hijo);
                if (grado <= gradoMax) {
                    grado = gradoMax;
                }
                hijo = hijo.getHermanoDerecho();
            }
        }
        return grado;
    }

    /**
     * <b>gradoSubarbol()</b>
     * <p>Método que devuelve la cantidad máxima de hijos en un nodo especifico</p>
     *
     * @param elemBuscado Object
     * @return gradoSubarbol Integer
     */

    public int gradoSubarbol(Object elemBuscado) {
        int gradoSubarbol = -1;
        if (!this.esVacio()) {
            NodoGenerico nodoElementoBuscado = buscaNodo(elemBuscado);
            if (nodoElementoBuscado != null) {
                gradoSubarbol = gradoRecursivo(nodoElementoBuscado);
            }
        }
        return gradoSubarbol;
    }

    /**
     * <b>fronteras()</b>
     * <p>Devuelve una lista con los nodos que son hojas, la raiz que no tiene hijo se considera hoja.</p>
     *
     * @return fronteras Lista
     */

    public Lista fronteras() {
        Lista fronteras = new Lista();

        if (!this.esVacio()) {
            if (this.raiz.getHijoIzquierdo() == null) {
                fronteras.insertar(this.raiz.getElem(), fronteras.longitud() + 1);
            } else {
                fronterasRecursivo(this.raiz, fronteras);
            }
        }
        return fronteras;
    }

    private void fronterasRecursivo(NodoGenerico aux, Lista fronteras) {

        if (aux != null) {
            if (aux.getHijoIzquierdo() == null) {
                fronteras.insertar(aux.getElem(), fronteras.longitud());
            } else {
                NodoGenerico hijo = aux.getHijoIzquierdo();

                while (hijo != null) {
                    fronterasRecursivo(hijo, fronteras);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }

    }

    /**
     * <b>clone()</b>
     * <p>Método para clonar un arbol, devuelve un arbol nuevo identico</p>
     *
     * @return clone ArbolGenerico
     */

    @Override
    public ArbolGen clone() {
        ArbolGen clone = new ArbolGen();

        if (!this.esVacio()) {
            //clone.raiz = new NodoGenerico(raiz.getElem(), null, null);
            clone.raiz = cloneRecursivo(this.raiz);

            //cloneAux(this.raiz, clone.raiz);
        }
        return clone;
    }

    /**
     * <b>CloneRecursivo(NodoGenerico aux)</b>
     * <p>Método privado de clone, que recorre al arbol utilizando un nodo auxiliar.
     * Utiliza un nodo que va generando nuevos nodos en la recursiva, en su retorno el nodo clone
     * que es creado cuando aux es distinto null, modifica su hijo izquierdo por el hijoAuxiliar.
     * se realiza un primer recorrido con el hijo para ir enlazandolos con sus hermanos derecho.</p>
     *
     * @param aux
     * @return
     */

    private NodoGenerico cloneRecursivo(NodoGenerico aux) {
        NodoGenerico nodoClone = null;

        if (aux != null) {
            NodoGenerico hijoAuxiliar = null, hermano;
            nodoClone = new NodoGenerico(aux.getElem(), null, null);

            NodoGenerico hijo = aux.getHijoIzquierdo();


            if (hijo != null) {
                //Va hasta el último hijo.
                hijoAuxiliar = cloneRecursivo(hijo);
                //Almacena el nuevo nodo recuperado de la recursiva en el nodo clone.
                nodoClone.setHijoIzquierdo(hijoAuxiliar);
                //
                hijo = hijo.getHermanoDerecho();

                while (hijo != null) {

                    hermano = cloneRecursivo(hijo);
                    hijoAuxiliar.setHermanoDerecho(hermano);

                    hijoAuxiliar = hijoAuxiliar.getHermanoDerecho();
                    hijo = hijo.getHermanoDerecho();

                }

            }
        }

        return nodoClone;
    }

    /**
     * <b>cloneRecursivoDosNodos(NodoGenerico aux, nodoClon)</b>
     * <p>Método privado de clone, que recorre al arbol utilizando un nodo auxiliar.
     * Creo el nodoClon en la primera instancia, y obtengo sus hijos moviendo el puntero, cambiando la referencia de nodo al hijo.</p>
     *
     * @param nodo     NodoGenerico
     * @param nodoClon NodoGenerico
     */

    private void cloneRecursivoDosNodos(NodoGenerico nodo, NodoGenerico nodoClon) {

        NodoGenerico hijo, hijoClon;

        if (nodo.getHijoIzquierdo() != null) {

            nodoClon.setHijoIzquierdo(new NodoGenerico(nodo.getHijoIzquierdo().getElem(), null, null));

            nodo = nodo.getHijoIzquierdo();
            nodoClon = nodoClon.getHijoIzquierdo();

            hijo = nodo.getHermanoDerecho();
            hijoClon = nodoClon;
            while (hijo != null) {
                hijoClon.setHermanoDerecho(new NodoGenerico(hijo.getElem(), null, null));
                hijo = hijo.getHermanoDerecho();
                hijoClon = hijoClon.getHermanoDerecho();
            }

            hijo = nodo;
            hijoClon = nodoClon;
            while (hijo != null) {
                cloneRecursivoDosNodos(hijo, hijoClon);
                hijo = hijo.getHermanoDerecho();
                hijoClon = hijoClon.getHermanoDerecho();
            }

        }
    }

    /**
     * <b>cloneRecursivoInvertido2(NodoGenerico aux)</b>
     * <p>Método encargado de devolver un arbol nuevo clonado, pero con sus nodos invertidos.</p>
     *
     * @param aux NodoGenerico
     * @return nodo NodoGenerico
     */
    private NodoGenerico cloneRecursivoInvertido2(NodoGenerico aux) {
        NodoGenerico nodo = null;

        if (aux != null) {
            NodoGenerico hijo = aux.getHijoIzquierdo();
            NodoGenerico hijoAuxiliar = null;
            NodoGenerico hermano = null;
            nodo = new NodoGenerico(aux.getElem(), null, null);
            System.out.println("AUX: " + aux.getElem());
            while (hijo != null) {
                hijoAuxiliar = new NodoGenerico(
                        hijo.getElem(),
                        cloneRecursivoInvertido(hijo.getHijoIzquierdo()),
                        null);
                hijoAuxiliar.setHermanoDerecho(hermano);
                hermano = hijoAuxiliar;

                hijo = hijo.getHermanoDerecho();

            }
            nodo.setHijoIzquierdo(hijoAuxiliar);

        }

        return nodo;
    }

    /**
     * <b>cloneRecursivoInvertido(NodoGenerico aux)</b>
     * <p>Método encargado de devolver un arbol nuevo clonado, pero con sus nodos invertidos.</p>
     *
     * @param aux NodoGenerico
     * @return nodo NodoGenerico
     */
    private NodoGenerico cloneRecursivoInvertido(NodoGenerico aux) {
        NodoGenerico nodo = null;

        if (aux != null) {
            nodo = new NodoGenerico(aux.getElem(), null, null);
            NodoGenerico hijo = aux.getHijoIzquierdo();
            NodoGenerico hijoAuxiliar = null;
            //System.out.println("AUX: " + aux.getElem() + ((hijo != null) ? " hijo: " + hijo.getElem() : " sin hijo"));
            while (hijo != null) {
                hijoAuxiliar = new NodoGenerico(
                        hijo.getElem(),
                        cloneRecursivoInvertido(hijo.getHijoIzquierdo()),
                        hijoAuxiliar);

                hijo = hijo.getHermanoDerecho();
            }
            nodo.setHijoIzquierdo(hijoAuxiliar);

        }
        return nodo;
    }

    public boolean verificarCamino(Lista ls) {
        boolean verifica = false;
        if (!this.esVacio()) {
            if (this.raiz.getElem().equals(ls.recuperar(1))) {
                verifica = verificarCaminoRecursivo(this.raiz, ls, 2);
            }
        }
        return verifica;
    }

    private boolean verificarCaminoRecursivo(NodoGenerico aux, Lista ls, int pos) {
        boolean verifica = false;

        if (aux != null) {
            if (ls.longitud() + 1 == pos) {
                verifica = true;
            } else {
                NodoGenerico hijo = aux.getHijoIzquierdo();
                while (hijo != null && !verifica) {
                    if (hijo.getElem().equals(ls.recuperar(pos))) {
                        verifica = verificarCaminoRecursivo(hijo, ls, pos + 1);
                        if (!verifica) {
                            hijo = null;
                        }
                    } else {
                        hijo = hijo.getHermanoDerecho();
                    }
                }
            }
        }
        return verifica;
    }

    public Lista listarHastaNivel(int nivMaximo) {
        Lista ls = new Lista();

        if (!this.esVacio()) {
            listarHastaNivel(this.raiz, nivMaximo, 1, ls);
        }
        return ls;
    }

    private void listarHastaNivel(NodoGenerico aux, int nivMaximo, int nivelActual, Lista ls) {
        System.out.println("NIVEL ACTUAL " + nivelActual + " raiz: " + aux.getElem());
        if (aux != null) {
            NodoGenerico hijo = aux.getHijoIzquierdo();
            if (nivelActual <= nivMaximo) {
                ls.insertar(aux.getElem(), ls.longitud() + 1);
                while (hijo != null) {

                    listarHastaNivel(hijo, nivMaximo, nivelActual + 1, ls);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
    }

    public Lista listarEntreNiveles(int niv1, int niv2) {
        Lista ls = new Lista();

        if (!this.esVacio()) {
            listarEntreNivelesRecursivo(this.raiz, ls, niv1, niv2, 1);
        }
        return ls;
    }

    private void listarEntreNivelesRecursivo(NodoGenerico aux, Lista ls, int niv1, int niv2, int nivActual) {
        if (aux != null) {
            NodoGenerico hijo = aux.getHijoIzquierdo();
            //Va hasta el ultimo hijo
            if (hijo != null) {
                listarEntreNivelesRecursivo(hijo, ls, niv1, niv2, nivActual + 1);
            }

            //Visita el nodo
            if (nivActual >= niv1 && nivActual <= niv2) {
                ls.insertar(aux.getElem(), ls.longitud() + 1);
            }
            if (hijo != null && nivActual + 1 <= niv2) {
                NodoGenerico hermanoDerecho = aux.getHijoIzquierdo().getHermanoDerecho();
                while (hermanoDerecho != null) {
                    listarEntreNivelesRecursivo(hermanoDerecho, ls, niv1, niv2, nivActual + 1);
                    hermanoDerecho = hermanoDerecho.getHermanoDerecho();
                }
            }
        }
    }

/*
    public boolean esHijoDe(Object a, Object b) {
        boolean esHijo = false;

        if (this.raiz != null) {
            esHijo = esHijoDeRecursivo(this.raiz, a, b);
        }
        return esHijo;
    }

    private boolean esHijoDeRecursivo(NodoGenerico aux, Object hijoBuscado, Object padreBuscado) {
        boolean esHijo = false;
        if (aux != null) {
            NodoGenerico hijo;
            if (aux.getElem().equals(padreBuscado)) {
                hijo = aux.getHijoIzquierdo();
                while (!esHijo && hijo != null) {
                    if (hijo.getElem().equals(hijoBuscado)) {
                        esHijo = true;
                    }
                    hijo = hijo.getHermanoDerecho();
                }

            }else{
                hijo = aux.getHijoIzquierdo();
                while(!esHijo && hijo!=null) {
                    esHijo = esHijoDeRecursivo(hijo, hijoBuscado,padreBuscado);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
        return esHijo;
    }
*/

    public boolean esHijoDe(Object a, Object b) {
        boolean esHijo = false;

        if (!this.esVacio()) {
            esHijo = esHijoDeRecursivo(this.raiz, a, b);
        }
        return esHijo;
    }

    private boolean esHijoDeRecursivo(NodoGenerico aux, Object a, Object b) {
        boolean esHijo = false;
        if (aux != null) {
            NodoGenerico hijo = aux.getHijoIzquierdo();
            //Si encuentra al padre, verifica si alguno de sus hijos es el buscado
            if (aux.getElem().equals(b)) {
                while (!esHijo && hijo != null) {
                    if (aux.getElem().equals(b) && hijo.getElem().equals(a)) {
                        esHijo = true;
                    }
                    hijo = hijo.getHermanoDerecho();
                }
                if (!esHijo) {
                    hijo = aux.getHijoIzquierdo();
                    while (!esHijo && hijo != null) {
                        esHijo = esHijoDeRecursivo(hijo, a, b);
                        hijo = hijo.getHijoIzquierdo();
                    }
                }
            } else {
                //Caso contrario que no sea busca en sus hijos.
                hijo = aux.getHijoIzquierdo();
                while (!esHijo && hijo != null) {
                    esHijo = esHijoDeRecursivo(hijo, a, b);
                    hijo = hijo.getHijoIzquierdo();
                }
            }

        }
        return esHijo;
    }

    public boolean esHermanoPosterior(Object a, Object b) {
        boolean esHermanoPost = false;
        if (!this.esVacio()) {
            esHermanoPost = esHermanoPosteriorRecursivo(this.raiz, a, b);
        }
        return esHermanoPost;
    }

    private boolean esHermanoPosteriorRecursivo(NodoGenerico aux, Object a, Object b) {
        boolean esHermanoPost = false;

        if (aux != null) {
            NodoGenerico hijo = aux.getHijoIzquierdo();
            NodoGenerico post = null;
            while (!esHermanoPost && hijo != null) {
                if (hijo.getElem().equals(b)) {
                    post = aux;
                    hijo = hijo.getHermanoDerecho();
                }
                if (post != null && hijo.getElem().equals(a)) {
                    esHermanoPost = true;
                }
                hijo = hijo.getHermanoDerecho();
            }
            if (!esHermanoPost) {
                hijo = aux.getHijoIzquierdo();

                while (!esHermanoPost && hijo != null) {
                    esHermanoPost = esHermanoPosteriorRecursivo(hijo, a, b);
                    hijo = hijo.getHermanoDerecho();
                }
            }

        }
        return esHermanoPost;
    }

    public boolean insertarSobrino(Object tio, Object hermano, Object sobrino) {
        boolean seInserto = false;

        if (this.raiz != null) {
            if (hermano.equals(tio)) {
                seInserto = false;
            } else {
                seInserto = insertarSobrinoRecursivo(this.raiz, tio, hermano, sobrino);
            }
        }
        return seInserto;
    }

    private boolean insertarSobrinoRecursivo(NodoGenerico aux, Object tio, Object hermano, Object sobrino) {
        boolean seInserto = false;
        if (aux != null) {
            //buscarTio
            NodoGenerico hermanoAuxiliar = aux;
            NodoGenerico tioAux = null;
            while (tioAux == null && hermanoAuxiliar != null) {
                if (hermanoAuxiliar.getElem().equals(tio)) {
                    tioAux = hermanoAuxiliar;
                }
                hermanoAuxiliar = hermanoAuxiliar.getHermanoDerecho();
            }

            if (tioAux != null) {
                //Busco hermano
                hermanoAuxiliar = aux;
                while (!seInserto && hermanoAuxiliar != null) {
                    if (hermanoAuxiliar.getElem().equals(hermano)) {
                        hermanoAuxiliar.setHijoIzquierdo(new NodoGenerico(sobrino, null, hermanoAuxiliar.getHijoIzquierdo()));
                        seInserto = true;
                    }
                    hermanoAuxiliar = hermanoAuxiliar.getHermanoDerecho();
                }
                if (!seInserto) {
                    NodoGenerico hijo = aux.getHijoIzquierdo();

                    while (!seInserto && hijo != null) {
                        seInserto = insertarSobrinoRecursivo(hijo, tio, hermano, sobrino);
                        hijo = hijo.getHermanoDerecho();
                    }
                }
            } else {
                NodoGenerico hijo = aux.getHijoIzquierdo();

                while (!seInserto && hijo != null) {
                    seInserto = insertarSobrinoRecursivo(hijo, tio, hermano, sobrino);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
        return seInserto;
    }

/**

 }

 if (aux.getElem().equals(tio)) {
 NodoGenerico hermanoAuxiliar = aux.getHermanoDerecho();
 //NodoGenerico hermanoEncontrado= null;
 while (!seInserto && hermanoAuxiliar != null) {
 if (hermanoAuxiliar.getElem().equals(hermano)) {
 hermanoAuxiliar.setHijoIzquierdo(new NodoGenerico(sobrino, null, hermanoAuxiliar.getHijoIzquierdo()));
 seInserto = true;
 }
 hermanoAuxiliar = hermanoAuxiliar.getHermanoDerecho();
 }
 if (!seInserto) {
 NodoGenerico hijo = aux.getHijoIzquierdo();

 while (!seInserto && hijo != null) {
 seInserto = insertarSobrinoRecursivo(hijo, tio, hermano, sobrino);
 hijo = hijo.getHermanoDerecho();
 }
 }
 } else {
 NodoGenerico hijo = aux.getHijoIzquierdo();

 while (!seInserto && hijo != null) {
 seInserto = insertarSobrinoRecursivo(hijo, tio, hermano, sobrino);
 hijo = hijo.getHermanoDerecho();
 }

 }
 */
}
