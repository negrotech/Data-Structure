package generic_structure.conjuntistas;

public class NodoAVL {
    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */

    private NodoAVL izquierdo;
    private NodoAVL derecho;
    private Comparable elemento;
    private int altura;


    public NodoAVL(Comparable elemento, NodoAVL izq, NodoAVL der) {
        this.elemento = elemento;
        this.derecho = der;
        this.izquierdo = izq;
        this.altura = 0;
    }

    public int getAltura() {
        return this.altura;
    }

    public void recalcularAltura() {
        int altIzq = -1;
        int altDer = -1;

        if (this.izquierdo != null) {
            altIzq = this.izquierdo.getAltura();
        }
        if (this.derecho != null) {
            altDer = this.derecho.getAltura();
        }
        if (altIzq > altDer) {
            this.altura = altIzq;
        } else if (altIzq < altDer) {
            this.altura = altDer;
        } else {
            this.altura = altIzq;
        }
        //Sumo la raiz
        this.altura += 1;
    }

    //Observadores
    public Comparable getElemento() {
        return this.elemento;
    }

    public NodoAVL getIzquierdo() {
        return this.izquierdo;
    }

    public NodoAVL getDerecho() {
        return this.derecho;
    }

    //Modificadores
    public void setElemento(Comparable elem) {
        this.elemento = elem;
    }

    public void setIzquierdo(NodoAVL izq) {
        this.izquierdo = izq;
    }

    public void setDerecho(NodoAVL der) {
        this.derecho = der;
    }

    public void setAltura(int alt) {
        this.altura = alt;
    }
}


