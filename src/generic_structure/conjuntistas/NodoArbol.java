/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generic_structure.conjuntistas;

/**
 * @author Martin
 */
public class NodoArbol {

    private NodoArbol izquierdo;
    private NodoArbol derecho;
    private Comparable elemento;


    public NodoArbol(Comparable elemento, NodoArbol izq, NodoArbol der) {
        this.elemento = elemento;
        this.derecho = der;
        this.izquierdo = izq;
    }

    //Observadores
    public Comparable getElemento() {
        return this.elemento;
    }

    public NodoArbol getIzquierdo() {
        return this.izquierdo;
    }

    public NodoArbol getDerecho() {
        return this.derecho;
    }

    //Modificadores
    public void setElemento(Comparable elem) {
        this.elemento = elem;
    }

    public void setIzquierdo(NodoArbol izq) {
        this.izquierdo = izq;
    }

    public void setDerecho(NodoArbol der) {
        this.derecho = der;
    }

}
