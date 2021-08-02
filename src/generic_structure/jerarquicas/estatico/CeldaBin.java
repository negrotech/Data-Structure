/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generic_structure.jerarquicas.estatico;

/**
 *
 * @author Martin
 */
public class CeldaBin {

    private Object elemento;
    private int izquierdo;
    private int derecho;
    private boolean enUso;

    public CeldaBin() {
        this.elemento = null;
        this.izquierdo = -1;
        this.derecho = -1;
        this.enUso = false;
    }

    public CeldaBin(Object elemento, int posIzquierdo, int posDerecho) {
        this.elemento = elemento;
        this.izquierdo = posIzquierdo;
        this.derecho = posDerecho;
        this.enUso = true;
    }

    //Observadores
    public int getPosIzquierda() {
        return this.izquierdo;
    }

    public int getPosDerecho() {
        return this.derecho;
    }

    public Object getElemento() {
        return this.elemento;
    }

    public boolean enUso() {
        return this.enUso;
    }

    //Modificadores
    public void setPosIzquierdo(int izq) {
        this.izquierdo = izq;
    }

    public void setPosDerecho(int der) {
        this.derecho = der;
    }

    public void setElemento(Object elemento) {
        this.elemento = elemento;
    }

    public void enUso(boolean u) {
        this.enUso = u;
    }
}
