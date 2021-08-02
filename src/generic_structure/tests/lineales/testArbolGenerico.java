/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generic_structure.tests.lineales;

import generic_structure.jerarquicas.dinamicas.ArbolGen;
//import generic_structure.jerarquicas.trash.ArbolGen;


/**
 *
 * @author Martin
 */
public class testArbolGenerico {

    public static void main(String[] args) {

        ArbolGen arbol = new ArbolGen();
        /*
        arbol.insertar('Ñ', null);
        arbol.insertar('M', 'Ñ');
        arbol.insertar('H', 'M');
        arbol.insertar('K', 'M');
        arbol.insertar('M', 'Ñ');
        arbol.insertar('F', 'Ñ');
        arbol.insertar('H', 'M');
        arbol.insertar('T', 'M');
        arbol.insertar('G', 'M');
        arbol.insertar('M', 'Ñ');
        arbol.insertar('J', 'M');
        arbol.insertar('Z', 'G');
*/
        arbol.insertar('Z', null);
        arbol.insertar('M', 'Z');
        arbol.insertar('J', 'M');
        arbol.insertar('A', 'J');
        arbol.insertar('F', 'Z');
        arbol.insertar('H', 'Z');
        arbol.insertar('T', 'H');
        arbol.insertar('Ñ', 'H');
        arbol.insertar('H', 'H');
        arbol.insertar('X', 'Z');
        arbol.insertar('K', 'X');
        arbol.insertar('H', 'X');

        System.out.println(arbol);
        System.out.println(arbol.esHermanoPosterior('H','Ñ') ? "VERDADERO" : "FALSO" );
        //System.out.println(arbol.listarPreorden());
        //System.out.println(arbol.ancestros('K'));
        //System.out.println(arbol.altura());


        /*
        arbol.insertar('A', 'H');
        arbol.insertar('M', 'Ñ');
        arbol.insertar('J', 'M');
        arbol.insertar('F', 'Ñ');
        arbol.insertar('M', 'Ñ');
        arbol.insertar('H', 'M');
        arbol.insertar('G', 'M');
        arbol.insertar('T', 'M');
        arbol.insertar('K', 'H');
*/
        //System.out.println(arbol);
        //System.out.println(arbol.esHijoDe('T','M') ? "ES HIJO"  : "NO ES HIJO" );
/*
        arbol.insertar(1, null);
        arbol.insertar(3, 1);
        arbol.insertar(2, 1);
        arbol.insertar(7, 1);
        arbol.insertar(11, 2);
        arbol.insertar(13, 11);
        arbol.insertar(15, 13);
        ArbolGen arbol2 = new ArbolGen();

        arbol2.insertar(20,null);
        arbol2.insertar(13,20);
        arbol2.insertar(54,20);
        arbol2.insertar(15,13);
        arbol2.insertar(12,13);
        arbol2.insertar(11,54);
        arbol2.insertar(27,54);
        arbol2.insertar(4,54);
        arbol2.insertar(17,27);
        System.out.println(arbol2);
        //System.out.println("LISTAR HASTA NIVEL: "+arbol2.listarHastaNivel(4));
        System.out.println("LISTAR ENTRE NIVEL: "+arbol2.listarEntreNiveles(2,3));
*/
        //Lista camino = new Lista();
/*
        camino.insertar(20,camino.longitud() + 1);
        camino.insertar(54,camino.longitud() + 1);
        camino.insertar(27,camino.longitud() + 1);

        camino.insertar(20,camino.longitud() + 1);
        camino.insertar(13,camino.longitud() + 1);
        camino.insertar(12,camino.longitud() + 1);
        camino.insertar(45,camino.longitud() + 1);
        //System.out.println(camino);

        camino.insertar(20,camino.longitud() + 1);
        camino.insertar(17,camino.longitud() + 1);
*/
        //System.out.println((arbol2.verificarCamino(camino) ? "VERIFICA CAMINO" : "NO VERIFICA CAMINO"));
/*
        arbol.pertenece(3);
        ArbolGen clone = arbol.clone();
        System.out.println(arbol);
        System.out.println("ARBOL CLONE");
        System.out.println(clone);
        arbol.ancestros(1);
        System.out.println("PREORDEN Original: "+arbol.listarPreorden());
        System.out.println("POST ORDEN Or: "+arbol.listarPosorden());
        System.out.println("INORDEN "+arbol.listarInorden());
        //System.out.println("POSTORDEN "+arbol.listarPosorden());

        //System.out.println(arbol.ancestros(15));
        //System.out.println("ALTURA: " + arbol.altura());
        */

    }
}
