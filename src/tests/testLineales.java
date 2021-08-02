/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import lineales.dinamico.Cola;
import lineales.dinamico.Lista;
import lineales.dinamico.Pila;

/**
 * @author Martin
 */
public class testLineales {

    public static void main(String[] args) {

        Cola cola = new Cola();

        cola.poner('z');
        cola.poner('t');
        cola.poner('r');
        cola.poner('$');
        cola.poner('t');
        cola.poner('y');
        cola.poner('$');
        cola.poner('r');
        cola.poner('z');
        cola.poner('$');
        cola.poner('w');
        cola.poner('y');
        cola.poner('x');
        cola.poner('$');
        cola.poner('a');
        cola.poner('b');

        Pila pila1 = new Pila();
        pila1.apilar('D');
        pila1.apilar('B');
        pila1.apilar('E');
        pila1.apilar('&');
        pila1.apilar('D');
        pila1.apilar('E');
        pila1.apilar('F');
        pila1.apilar('&');
        pila1.apilar('E');
        pila1.apilar('A');
        pila1.apilar('C');
        pila1.apilar('&');
        pila1.apilar('B');
        pila1.apilar('A');

        System.out.println(pila1);
        System.out.println(armarLista(pila1));
        //System.out.println(cola);
        //System.out.println(crearLista(cola));

    }

    public static Lista armarLista(Pila pila) {
        Lista ls = new Lista();

        if (!pila.esVacia()) {
            //No tenga &

            if (!pila.obtenerTope().equals('&')) {
                while (!pila.esVacia()) {
                    int i = 1;
                    while (!pila.esVacia() && !pila.obtenerTope().equals('&')) {
                        if (i % 2 == 0) {
                            System.out.println("ACA");
                            while (!pila.esVacia() && !pila.obtenerTope().equals('&')) {
                                ls.insertar(pila.obtenerTope(), ls.longitud() + 1);
                                pila.desapilar();
                            }
                        } else {
                            //System.out.println("ACA");
                            Pila aux = new Pila();
                            while (!pila.esVacia() && !pila.obtenerTope().equals('&')) {
                                aux.apilar(pila.obtenerTope());
                                pila.desapilar();
                            }
                            //System.out.println(aux);
                            while (!aux.esVacia()) {
                              //  System.out.println("TOPE: " + aux.obtenerTope());
                                ls.insertar(aux.obtenerTope(), ls.longitud() + 1);
                                aux.desapilar();
                            }

                        }
                        if(!pila.esVacia() && pila.obtenerTope().equals('&')) {
                            ls.insertar('&',ls.longitud()+1);
                            pila.desapilar();
                        }
                        i++;
                    }

                }

            }

            //una ves
            //nveces
        }
        return ls;
    }

    public static Lista crearLista(Cola c1) {
        Lista lista = new Lista();

        if (!c1.esVacia()) {
            int i = 1;
            Pila pilaAuxiliar = new Pila();
            //Cola colaAuxiliar = new Cola();
            boolean encontro;
            while (!c1.esVacia()) {
                //IMPAR
                if (!c1.obtenerFrente().equals('$')) {

                    if (i % 2 != 0) {
                        encontro = false;
                        while (!encontro && !c1.esVacia()) {
                            if (c1.obtenerFrente().equals('$')) {
                                encontro = true;
                            } else {
                                pilaAuxiliar.apilar(c1.obtenerFrente());
                                c1.sacar();
                            }
                        }
                        while (!pilaAuxiliar.esVacia()) {
                            lista.insertar(pilaAuxiliar.obtenerTope(), lista.longitud() + 1);
                            pilaAuxiliar.desapilar();
                        }

                    } else {
                        System.out.println("PAR");
                        encontro = false;
                        while (!encontro && !c1.esVacia()) {
                            if (c1.obtenerFrente().equals('$')) {
                                encontro = true;
                            } else {
                                lista.insertar(c1.obtenerFrente(), lista.longitud() + 1);
                                c1.sacar();
                            }

                        }
                    }
                    if (!c1.esVacia()) {
                        lista.insertar('$', lista.longitud() + 1);
                    }
                    c1.sacar();
                    i++;
                    //desapilar
                } else {
                    /*
                    if (c1.obtenerFrente().equals('$') || c1.esVacia()) {
                        if (c1.obtenerFrente().equals('$')) {
                            lista.insertar('$', lista.longitud() + 1);
                            c1.sacar();
                        }
                    */

                }

            }
        }

        return lista;
    }
}
