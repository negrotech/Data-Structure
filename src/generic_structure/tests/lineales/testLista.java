/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generic_structure.tests.lineales;

import generic_structure.lineales.dinamico.Lista;

/**
 *
 * @author Martin
 */
public class testLista {

    public static void main(String[] args) {

        Lista lista1 = new Lista();

        //9,6,5,0,9,6,5,0,5,6,9
        /*

        lista1.insertar(11, 1);
        lista1.insertar(10, 1);
        lista1.insertar(9, 1);
        lista1.insertar(8, 1);
        lista1.insertar(1, 1);
        lista1.insertar(6, 1);
        lista1.insertar(5, 1);
        lista1.insertar(4, 1);
        lista1.insertar(1, 1);
        lista1.insertar(2, 1);
        lista1.insertar(1, 1);
        lista1.insertar(1, lista1.longitud() + 1);

        //[1,2,3,1,4,7,1,1,8,1]
        */

        lista1.insertar(1, lista1.longitud() + 1);
        lista1.insertar(1, lista1.longitud() + 1);
        /*
        lista1.insertar(2, lista1.longitud() + 1);
        lista1.insertar(3, lista1.longitud() + 1);
        lista1.insertar(1, lista1.longitud() + 1);
        lista1.insertar(4, lista1.longitud() + 1);
        lista1.insertar(7, lista1.longitud() + 1);
        lista1.insertar(1, lista1.longitud() + 1);
        lista1.insertar(1, lista1.longitud() + 1);
        lista1.insertar(8, lista1.longitud() + 1);
        lista1.insertar(1, lista1.longitud() + 1);
*/
        System.out.println(lista1);
        lista1.eliminarSiguiente(1);
        System.out.println(lista1);
/*

        //lista1.eliminar(6);
        //System.out.println("Posicion 6: " + lista1.localizar(0));
        //lista1.eliminarApariciones2(5);
        //System.out.println(lista1);

       // int numero = 3;

        //System.out.println("Multiplos: " + 2 + " : " + lista1.obtenerMultiplos(numero));

        //lista1.eliminar(11);
        //System.out.println(lista1);

        /*
        System.out.println("Lista \n " + lista1);
        lista1.invertir();
        System.out.println(lista1);
        //Lista copia = lista1.clone();
        
        //System.out.println(copia);
        System.out.println(lista1);
        System.out.println((lista1.eliminar(4)) ? "ERROR" : "ELIMINADO");
        System.out.println(lista1);
        System.out.println("LONGITUD: " + lista1.longitud());
        System.out.println((lista1.eliminar(11)) ? "ELIMINADO" : "ERROR");
        System.out.println(lista1);
        
        System.out.println((lista1.eliminar(10)) ? "ELIMINADO" : "ERROR");
        System.out.println((lista1.eliminar(9)) ? "ELIMINADO" : "ERROR");
        System.out.println(lista1);
         */
    }
}
