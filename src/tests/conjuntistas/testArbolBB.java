package tests.conjuntistas;

import conjuntistas.ArbolBB;
import lineales.dinamico.Pila;

public class testArbolBB {
    public static void main(String[] args) {
        ArbolBB arbol = new ArbolBB();
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
        arbol.insertar(50);
        arbol.insertar(32);
        arbol.insertar(91);
        arbol.insertar(21);
        arbol.insertar(44);
        arbol.insertar(67);
        arbol.insertar(131);
        arbol.insertar(58);
        arbol.insertar(73);
        arbol.insertar(63);

        Pila p = new Pila();

        p.apilar(63);
        p.apilar(67);
        p.apilar(63);

        System.out.println(arbol);
        System.out.println(arbol.verificaCamino(p));

        //System.out.println(arbol.concatenarPreordenDesde('R', 1));

        //System.out.println(arbol);
        //System.out.println(arbol.concatenarPreordenDesde('R',4));
        //System.out.println(arbol.listar());
        //int numeroPrueba = 5;
        //System.out.println("LISTA ORDENADA MAYOR "+numeroPrueba+": " + arbol.listarMayorIgual(numeroPrueba));
        //System.out.println("LISTA ORDENADA MENOR "+numeroPrueba+": " + arbol.listarMenorIgual(numeroPrueba));

    }

}
