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
 *
 * @author Martin
 */
public class testCadenas {

    public static void main(String[] args) {
        Cola c1 = new Cola();
        //AB#C#DEF
        c1.poner('A');
        c1.poner('B');
        c1.poner('#');
        c1.poner('C');
        c1.poner('#');
        c1.poner('D');
        c1.poner('E');
        c1.poner('F');

        Cola c2 = new Cola();
        //{ 5 + [ 8 * 9 -( 4 / 2 ) + 7 ] -1 }
        c2.poner('{');
        c2.poner('5');
        c2.poner('+');
        c2.poner('[');
        c2.poner('8');
        c2.poner('*');
        c2.poner('9');
        c2.poner('-');
        c2.poner('(');
        c2.poner('4');
        c2.poner('/');
        c2.poner('2');
        c2.poner(')');
        c2.poner('+');
        c2.poner('7');
        c2.poner(']');
        c2.poner('-');
        c2.poner('1');
        c2.poner('}');

        //System.out.println(c1);
        //System.out.println(generar(c1));
        System.out.println(c2);
        System.out.println(verificarBalanceo(c2));
    }

    public static Cola generar(Cola c1) {
        Cola colaGenerada = new Cola();
        Cola colaAux = new Cola();
        Pila pilaAux = new Pila();

        //copiar cadena
        while (!c1.esVacia()) {
            if (!c1.obtenerFrente().equals('#')) {
                Object elemento = c1.obtenerFrente();

                colaGenerada.poner(elemento);
                colaAux.poner(elemento);
                pilaAux.apilar(elemento);
            }
            c1.sacar();
            if (c1.esVacia() || c1.obtenerFrente().equals('#')) {
                //inversa

                while (!pilaAux.esVacia()) {
                    colaGenerada.poner(pilaAux.obtenerTope());
                    pilaAux.desapilar();
                }
                //copia
                while (!colaAux.esVacia()) {
                    colaGenerada.poner(colaAux.obtenerFrente());
                    colaAux.sacar();
                }
                if (!c1.esVacia() && c1.obtenerFrente().equals('#')) {
                    colaGenerada.poner('#');
                }
            }

        }
        return colaGenerada;
    }

    public static boolean verificarBalanceo(Cola q) {
        boolean estaBalanceado = true;
        if (!q.esVacia()) {
            Cola colaAux = new Cola();
            Pila pilaAux = new Pila();
            while (estaBalanceado && !q.esVacia()) {
                if (q.obtenerFrente().equals('{')) {
                    pilaAux.apilar('}');
                    colaAux.poner(q.obtenerFrente());
                } else if (q.obtenerFrente().equals('[')) {
                    pilaAux.apilar(']');
                    colaAux.poner(q.obtenerFrente());

                } else if (q.obtenerFrente().equals('(')) {
                    pilaAux.apilar(')');
                    colaAux.poner(q.obtenerFrente());

                } else {
                    //System.out.println("COLA: " + q.obtenerFrente());
                    if (pilaAux.obtenerTope().equals(q.obtenerFrente())) {
                        System.out.println("COLA: " + q.obtenerFrente());

                        pilaAux.desapilar();
                    } else {
                        if (q.obtenerFrente().equals(')') || q.obtenerFrente().equals(']') || q.obtenerFrente().equals('}')) {
                            estaBalanceado = false;
                        }
                    }
                }
                q.sacar();
            }
            if (!pilaAux.esVacia()) {
                estaBalanceado = false;
            }
        }
        return estaBalanceado;
    }

    public static Lista generarList(Cola c1) {
        Lista listaGenerada = new Lista();
        Cola colaAux = new Cola();
        Pila pilaAux = new Pila();

        //copiar cadena
        while (!c1.esVacia()) {
            if (!c1.obtenerFrente().equals('#')) {
                Object elemento = c1.obtenerFrente();

                listaGenerada.insertar(elemento, listaGenerada.longitud() + 1);
                colaAux.poner(elemento);
                pilaAux.apilar(elemento);
            }
            c1.sacar();
            if (c1.esVacia() || c1.obtenerFrente().equals('#')) {
                //inversa

                while (!pilaAux.esVacia()) {
                    listaGenerada.insertar(pilaAux.obtenerTope(), listaGenerada.longitud() + 1);
                    pilaAux.desapilar();
                }
                //copia
                while (!colaAux.esVacia()) {
                    listaGenerada.insertar(colaAux.obtenerFrente(), listaGenerada.longitud() + 1);
                    colaAux.sacar();
                }
                if (!c1.esVacia() && c1.obtenerFrente().equals('#')) {
                    listaGenerada.insertar('#', listaGenerada.longitud() + 1);
                }
            }

        }
        return listaGenerada;
    }
}
