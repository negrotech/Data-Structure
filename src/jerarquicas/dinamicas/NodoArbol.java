/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jerarquicas.dinamicas;

/**
 *
 * @author Martin
 */
public class NodoArbol {

    private NodoArbol izquierdo;
    private NodoArbol derecho;
    private Object elemento;
    private int altura;

    public NodoArbol(Object elemento) {
        this.elemento = elemento;
        this.derecho = null;
        this.izquierdo = null;
        this.altura = 0;
    }

    public NodoArbol(Object elemento, NodoArbol izq, NodoArbol der) {
        this.elemento = elemento;
        this.derecho = der;
        this.izquierdo = izq;
    }

    public int altura() {
        if (altura != 0) {
            this.altura = alturaRecursiva(this);
        }
        return this.altura;
    }

    public int alturaRecursiva(NodoArbol aux) {
        int alt = -1, aux1, aux2;

        if (aux != null) {
            aux1 = alturaRecursiva(aux.getIzquierdo());
            aux2 = alturaRecursiva(aux.getDerecho());
            if (aux1 > aux2) {
                alt = aux1;
            } else if (aux1 < aux2) {
                alt = aux2;

            } else {
                alt = aux1;
            }
        }
        return alt;
    }

    //Observadores
    public Object getElemento() {
        return this.elemento;
    }

    public NodoArbol getIzquierdo() {
        return this.izquierdo;
    }

    public NodoArbol getDerecho() {
        return this.derecho;
    }

    //Modificadores
    public void setElemento(Object elem) {
        this.elemento = elem;
    }

    public void setIzquierdo(NodoArbol izq) {
        this.izquierdo = izq;
    }

    public void setDerecho(NodoArbol der) {
        this.derecho = der;
    }

}
