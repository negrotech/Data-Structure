package tests.conjuntistas;

import conjuntistas.trash.ArbolBB2;

public class testArbolBB2 {
    public static void main(String[] args) {
        ArbolBB2 arbol = new ArbolBB2();
/*
        arbol.insertar('H');
        arbol.insertar('F');
        arbol.insertar('R');
        arbol.insertar('B');
        arbol.insertar('E');
        arbol.insertar('M');
        arbol.insertar('Y');
        arbol.insertar('J');
        arbol.insertar('P');
*/
        arbol.insertar(7);
        arbol.insertar(5);
        arbol.insertar(3);
        arbol.insertar(9);
        arbol.insertar(18);
        arbol.insertar(4);
        arbol.insertar(6);
        arbol.insertar(2);
        arbol.insertar(1);
        System.out.println(arbol);
        System.out.println(arbol.listarPorNiveles());

        int suma = arbol.sumarEnPreorden(5,14);

        System.out.println("SUMA : " + suma);
        System.out.println("altura: " + arbol.altura());
        arbol.eliminar(1);
        System.out.println(arbol);
        System.out.println("altura: " + arbol.altura());
        arbol.eliminar(2);
        arbol.eliminar(4);
        System.out.println(arbol);
        System.out.println("altura: " + arbol.altura());
        System.out.println(arbol.listarPorNiveles());
    }
}
