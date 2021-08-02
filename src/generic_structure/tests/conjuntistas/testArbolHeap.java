package generic_structure.tests.conjuntistas;

import generic_structure.conjuntistas.ArbolHeap;

public class testArbolHeap {
    public static void main(String[] args) {
        ArbolHeap arbolMinimo = new ArbolHeap();

        arbolMinimo.insertar(38);
        arbolMinimo.insertar(24);
        arbolMinimo.insertar(23);
        arbolMinimo.insertar(19);
        arbolMinimo.insertar(17);
        arbolMinimo.insertar(20);
        arbolMinimo.insertar(20);
        arbolMinimo.insertar(17);


        System.out.println(arbolMinimo);

        arbolMinimo.eliminarCima();
        System.out.println(arbolMinimo);
    }
}
