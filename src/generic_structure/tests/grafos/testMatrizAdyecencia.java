package generic_structure.tests.grafos;

import generic_structure.grafo.matriz_adyecencia.Grafo;

public class testMatrizAdyecencia {
    public static void main(String[] args) {
        Grafo grafito = new Grafo();


        grafito.insertarVertice('A');
        grafito.insertarVertice('E');
        grafito.insertarVertice('C');
        grafito.insertarVertice('D');
        grafito.insertarVertice('B');

        grafito.insertarArco('A', 'B');
        grafito.insertarArco('B', 'C');
        //grafito.insertarArco('E','A');
        grafito.insertarArco('C', 'D');
        grafito.insertarArco('A', 'D');
        grafito.insertarArco('D', 'C');
        grafito.insertarArco('D', 'E');
        Grafo clone = grafito.clone();

        System.out.println(grafito);
        //System.out.println((grafito.existeCamino('C','D')) ?"EXISTE CAMINO" : "NO EXISTE CAMINO" );
        System.out.println("Camino mas Corto de A -> E" + grafito.caminoMasCorto('A', 'E'));
        System.out.println("Camino mas Corto de A -> E" + grafito.caminoMasLargo('A', 'E'));
        System.out.println("Lista profundidad" + grafito.listarEnProfundidad());
        System.out.println("Listar en Anchura" + grafito.listarEnAnchura());
        System.out.println("Grafo clonado");
        System.out.println(clone);
        clone.insertarVertice('F');
        System.out.println(clone);
    }
}
