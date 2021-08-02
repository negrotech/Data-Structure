/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generic_structure.jerarquicas.dinamicas;

/**
 *
 * @author Martin
 */
public class NodoGenerico {
    private Object elemento;
    private NodoGenerico hijoIzquierdo;
    private NodoGenerico hermanoDerecho;
    
    public NodoGenerico(Object elem, NodoGenerico hijoIzq, NodoGenerico hermanoDer){
        this.elemento = elem;
        this.hermanoDerecho = hermanoDer;
        this.hijoIzquierdo = hijoIzq;
    }
    
    //Métodos Observadores
    
    /**
     * Método observado getElem(), para obtener el elemento del nodo.
     * @return elemento Object
     */
    
    public Object getElem(){
        return this.elemento;
    }
    
    /**
     * Método observador getHermanoDerecho(), para obtener el nodo de los hermano/s.
     * @return elemento Object
     */
    
    public NodoGenerico getHermanoDerecho(){
        return this.hermanoDerecho;
    }
    /**
     * Método observador getHijoIzquierdo(), para obtener el nodo izquierdo.
     * @return elemento Object
     */
    
    public NodoGenerico getHijoIzquierdo(){
        return this.hijoIzquierdo;
    }
    
    //modificadores
    
     /**
     * Método modificador setElem(Object), para modificar el elemento del nodo.
     * 
     * Sin retorno
     * @param elemento Object
     */
    
    public void setElem(Object elemento){
        this.elemento = elemento;
    }
    
    /**
     * Método modificador setHermanoDerecho(NodoGenerico), para modificar el nodo izquierdo.
     * 
     * Sin retorno
     * @param herDerecho NodoGenerico
     */
    
    public void setHermanoDerecho(NodoGenerico herDerecho){
        this.hermanoDerecho = herDerecho;
    }
    
    /**
     * Método modificador setHijoIzquierdo(NodoGenerico), para modificar el nodo izquierdo.
     * 
     * Sin retorno
     * @param hijoIzq NodoGenerico
     */
    
    public void setHijoIzquierdo(NodoGenerico hijoIzq){
        this.hijoIzquierdo = hijoIzq;
    }
    
}
