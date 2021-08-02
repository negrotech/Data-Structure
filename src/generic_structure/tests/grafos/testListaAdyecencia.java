package generic_structure.tests.grafos;

import generic_structure.grafo.lista_adyecencia.Grafo;

public class testListaAdyecencia {
    public static void main(String[] args) {
        Grafo grafito = new Grafo();


        grafito.insertarVertice('A');
        grafito.insertarVertice('B');
        grafito.insertarVertice('C');
        grafito.insertarVertice('D');
        grafito.insertarVertice('E');
        grafito.insertarVertice('F');
        grafito.insertarVertice('G');
        grafito.insertarVertice('H');


        grafito.insertarArco('A', 'B');
        grafito.insertarArco('A', 'G');
        grafito.insertarArco('B', 'D');
        grafito.insertarArco('B', 'F');
        grafito.insertarArco('C', 'A');
        grafito.insertarArco('C', 'H');
        grafito.insertarArco('C', 'F');

        grafito.insertarArco('D', 'G');
        grafito.insertarArco('D', 'E');

        //grafito.insertarArco('E', 'E');
        //grafito.insertarArco('F', 'F');
        grafito.insertarArco('G', 'E');
        //grafito.insertarArco('H', 'H');

        //Grafo clone = grafito.clone();

        System.out.println(grafito);
        //grafito.eliminarArco('C','F');
        //System.out.println(grafito);

        System.out.println((grafito.existeCamino('C','G')) ?"EXISTE CAMINO" : "NO EXISTE CAMINO" );
        System.out.println("Camino mas Corto de C -> G" + grafito.caminoMasCorto('C', 'G'));
        System.out.println("Camino mas Largo de C -> G" + grafito.caminoMasLargo('C', 'G'));
        //System.out.println("Lista profundidad" + grafito.listarEnProfundidad());
        //System.out.println("Listar en Anchura" + grafito.listarEnAnchura());
        //System.out.println("Grafo clonado");
        //System.out.println(clone);
        //clone.insertarVertice('F');
        //System.out.println(clone);
    }
}
