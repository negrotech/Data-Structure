package generic_structure.tests.conjuntistas;

import generic_structure.conjuntistas.ArbolAVL;

public class testArbolAVL {
    public static void main(String[] args) {
        ArbolAVL arbol = new ArbolAVL();

        arbol.insertar(1);
        arbol.insertar(7);
        arbol.insertar(5);
        System.out.println(arbol);
        arbol.insertar(3);
        System.out.println(arbol);
        arbol.insertar(21);
        System.out.println(arbol);
        arbol.insertar(51);
        System.out.println(arbol);
        arbol.insertar(2);

        arbol.insertar(8);
        arbol.insertar(15);
        arbol.insertar(15);

        System.out.println(arbol);
    }
}
