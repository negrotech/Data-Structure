/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests.jerarquicas;

import jerarquicas.dinamicas.ArbolBin;

/**
 *
 * @author Martin
 */
public class testArbolBinario {

    public static void main(String[] args) {
        ArbolBin arbol = new ArbolBin();

        arbol.insertar(45, null, 'R');
        arbol.insertar(32, 45, 'I');
        arbol.insertar(63, 45, 'D');
        arbol.insertar(3, 32, 'I');
        arbol.insertar(3, 5, 'D');
        arbol.insertar(87, 63, 'D');
        arbol.insertar(20, 87, 'I');
        
        System.out.println(arbol);
        boolean seAltero = arbol.alterarParte(50, 96, 63);
        System.out.println((seAltero) ? arbol : "NO ENCONTRO AL PADRE");

        /*
        arbol.insertar(1, null, 'R');
        arbol.insertar(5, 1, 'I');
        arbol.insertar(2, 1, 'D');
        arbol.insertar(3, 5, 'D');
        arbol.insertar(8, 5, 'I');
        arbol.insertar(7, 2, 'I');
        
        //arbol.insertar(13, 7, 'I');
        arbol.insertar(6, 3, 'I');
        //arbol.insertar(11, 6, 'I');
        
        System.out.println(arbol);
        Lista l1 = new Lista();
        
        l1.insertar(1, l1.longitud() + 1);
        l1.insertar(5, l1.longitud() + 1);
        l1.insertar(8, l1.longitud() + 1);
        l1.insertar(3, l1.longitud() + 1);
        l1.insertar(6, l1.longitud() + 1);
        l1.insertar(2, l1.longitud() + 1);
        l1.insertar(7, l1.longitud() + 1);
        System.out.println(l1);
        System.out.println(arbol.listarPreorden());
        System.out.println("VERIFICA PATRON: " + ((arbol.verificaPatron(l1)) ? "CUMPLE" : "NO CUMPLE" ));
        //ArbolBinario cloneInvertido = arbol.cloneInvertido();
        //System.out.println(cloneInvertido);
         */
 /*
        N0          1   
                  /   \
        N1       5     2
               /  \   /
        N2    8    3  7
                  /
        N3       6
         */

 /*
        Lista listaPreOrden = arbol.preOrden();
        Lista listainOrden = arbol.inOrden();
        Lista listaPostOrden = arbol.postOrden();
        System.out.println(listaPreOrden + " PRE ORDEN");
        System.out.println(listainOrden + " IN ORDEN");
        System.out.println(listaPostOrden + " POST ORDEN");
        System.out.println("Padre de 2: " + arbol.padre(8));
         */
        //listarPorNiveles
        //Lista porNivel = arbol.listarPorNiveles();
        //System.out.println(porNivel);
        //System.out.println(arbol.nivel(6));
        //clone
        /*
        ArbolBinario clone = arbol.clone();
        System.out.println("ORIGINAL -> " + arbol.preOrden());
        System.out.println("COPIA    -> " + clone.preOrden());
         */
        //Ancestros
        // /*
        //System.out.println(arbol.ancestros());
    }
}
