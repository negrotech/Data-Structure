/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lineales.dinamico;

/**
 *
 * @author Martin
 */
public class Nodo {

    private Object elemento;
    private Nodo enlace;

    public Nodo(Object elemento, Nodo enlace) {
        this.elemento = elemento;
        this.enlace = enlace;
    }

    public Nodo(Object elemento) {
        this.elemento = elemento;
        this.enlace = null;
    }

    /**
     * Devuelve el tipoElemento del nodo
     *
     * @return Object
     */
    public Object getElemento() {
        return this.elemento;
    }

    /**
     *
     * @param elemento de tipo Object
     */
    public void setElemento(Object elemento) {
        this.elemento = elemento;
    }

    /**
     *
     * @return enlace de Tipo Nodo
     */
    public Nodo getEnlace() {
        return this.enlace;
    }

    /**
     *
     * @param enlace de Tipo Nodo
     */
    public void setEnlace(Nodo enlace) {
        this.enlace = enlace;
    }
}
