package grafo.matriz_adyecencia;

import lineales.dinamico.Cola;
import lineales.dinamico.Lista;

public class Grafo {
    private static final int TAM = 6;
    private boolean[][] arcos;
    private Object[] vertices;
    private int cantVertices;

    public Grafo() {
        this.arcos = new boolean[TAM][TAM];
        this.vertices = new Object[TAM];
        this.cantVertices = 0;
    }

    public boolean insertarVertice(Object vertice) {
        boolean seInserto = false;
        if (!this.estaLleno()) {
            int i = 0;
            boolean seEncuentra = false;
            //verificar si existe el vertice
            while (!seEncuentra && i < Grafo.TAM) {
                if (this.vertices[i] != null && this.vertices[i].equals(vertice)) {
                    seEncuentra = true;
                } else {
                    i++;
                }
            }
            if (!seEncuentra) {
                i = 0;
                while (!seInserto && i < Grafo.TAM) {
                    if (this.vertices[i] == null) {
                        this.vertices[i] = vertice;
                        this.cantVertices += 1;
                        seInserto = true;
                    } else {
                        i++;
                    }

                }
            }
        }
        return seInserto;
    }

    public boolean eliminarVertice(Object vertice) {
        boolean seElimino = false;
        if (!this.vacio()) {
            int i = 0;
            while (!seElimino && i < Grafo.TAM) {
                if (this.vertices[i].equals(vertice)) {
                    this.vertices[i] = null;
                    this.cantVertices -= 1;
                    seElimino = true;

                    //Eliminar arcos enlazados a él.

                    for (int j = 0; j < Grafo.TAM; j++) {
                        this.arcos[i][j] = false;
                    }

                } else {
                    i++;
                }

            }
        }
        return seElimino;
    }

    public boolean existeVertice(Object vertice) {
        boolean existe = false;
        if (!this.vacio()) {
            int i = 0;
            while (!existe && i < Grafo.TAM) {
                if (this.vertices[i].equals(vertice)) {
                    existe = true;
                } else {
                    i++;
                }
            }
        }
        return existe;
    }

    public boolean insertarArco(Object origen, Object destino) {
        boolean seInsertoArco = false;

        if (!this.vacio()) {
            if (origen.equals(destino)) {
                int posOrigen = buscarVertice(origen);
                if (posOrigen != -1) {
                    this.arcos[posOrigen][posOrigen] = true;
                    seInsertoArco = true;
                }
            } else {
                int posOrigen = buscarVertice(origen);
                int posDestino = buscarVertice(destino);

                if (posOrigen != -1 && posDestino != -1) {
                    this.arcos[posOrigen][posDestino] = true;
                    seInsertoArco = true;
                }
            }
        }
        return seInsertoArco;
    }

    public boolean eliminarArco(Object origen, Object destino) {
        boolean seEliminoArco = false;

        if (!this.vacio()) {
            int posOrigen = buscarVertice(origen);
            int posDestino = buscarVertice(origen);

            if (posOrigen != -1 && posDestino != -1) {
                this.arcos[posOrigen][posDestino] = false;
                seEliminoArco = true;
            }
        }
        return seEliminoArco;
    }

    private int buscarVertice(Object verticeBuscado) {
        int pos = -1;
        int i = 0;
        boolean encontrado = false;
        while (!encontrado && i < Grafo.TAM) {
            if (vertices[i].equals(verticeBuscado)) {
                pos = i;
                encontrado = true;
            } else {
                i++;
            }
        }
        return pos;
    }

    private boolean existeArco(Object origen, Object destino) {
        int i = 0;
        boolean existeArco = false;
        int posOrigen = buscarVertice(origen);
        int posDestino = buscarVertice(destino);

        if (posOrigen != -1 && posDestino != -1) {
            if (this.arcos[posOrigen][posDestino]) {
                existeArco = true;
            }
        }
        return existeArco;
    }

    public boolean existeCamino(Object origen, Object destino) {
        boolean existe = false;
        Lista visitados = new Lista();

        if (!this.vacio()) {
            int posOrigen = buscarVertice(origen);

            int posDestino = buscarVertice(destino);
            if (posOrigen != -1 && posDestino != -1) {
                //Para cada adyecente hasta encontrar destino
                int i = 0;
                existe = existeCaminoRecursivo(posOrigen, posDestino, i, visitados);
            }
        }
        return existe;
    }


    private boolean existeCaminoRecursivo(int posOrigen, int posDestino, int posActual, Lista visitados) {
        boolean existe = false;
        //System.out.println("SALTO");
        System.out.println("PosORIGEN: " + this.vertices[posOrigen]);
        if (visitados.localizar(this.vertices[posOrigen]) == -1) {
            visitados.insertar(vertices[posOrigen], visitados.longitud() + 1);
            //Adyacentes del elemento actual
            int i = 0;

            //Preguntar si sus adyacentes es el buscado

            while (!existe && i < Grafo.TAM) {
                if (this.arcos[posOrigen][i] && i == posDestino) {
                    //System.out.println("Vertice: " + this.vertices[posActual] + " ADY: " + this.vertices[i]);
                    existe = true;
                } else if (this.arcos[posOrigen][i]) {
                    //Recorrer a los adyacentes

                    System.out.println("VerticeOrigen: " + this.vertices[posOrigen] + " - ADY: " + this.vertices[i]);
                    existe = existeCaminoRecursivo(i, posDestino, posActual, visitados);
                }
                i++;
            }
        }
        return existe;
    }

    public Lista caminoMasCorto(Object origen, Object destino) {
        Lista masCorto = new Lista();

        if (!this.vacio()) {
            int posOrigen = buscarVertice(origen);
            int posDestino = buscarVertice(destino);

            if (posOrigen != -1 && posDestino != -1) {
                Lista recorridos = new Lista();
                caminoMasCortoRecursivo(posOrigen, posDestino, masCorto, recorridos);
            }
        }
        return masCorto;
    }

    private void caminoMasCortoRecursivo(int posOrigen, int posDestino, Lista masCorto, Lista recorridos) {
        if (recorridos.localizar(this.vertices[posOrigen]) == -1) {
            recorridos.insertar(vertices[posOrigen], recorridos.longitud() + 1);
            //Adyacentes del elemento actual
            int i = 0;

            //Preguntar si sus adyacentes es el buscado

            boolean encontro = false;

            while (i < Grafo.TAM) {
                if (this.arcos[posOrigen][i] && i == posDestino) {

                    recorridos.insertar(this.vertices[i], recorridos.longitud() + 1);

                    if (masCorto.longitud() == 0 || (masCorto.longitud() >= 2 && masCorto.longitud() > recorridos.longitud())) {
                        masCorto.modificarLista(recorridos);
                    }
                    recorridos.eliminar(recorridos.longitud());
                }
                if (this.arcos[posOrigen][i]) {
                    //Recorrer a los adyacentes

                    caminoMasCortoRecursivo(i, posDestino, masCorto, recorridos);

                }
                i++;
            }
            recorridos.eliminar(recorridos.longitud());
        }
    }

    public Lista caminoMasLargo(Object origen, Object destino) {
        Lista masLargo = new Lista();

        if (!this.vacio()) {
            int posOrigen = buscarVertice(origen);
            int posDestino = buscarVertice(destino);

            if (posOrigen != -1 && posDestino != -1) {
                Lista recorridos = new Lista();

                caminoMasLargoRecursivo(posOrigen, posDestino, masLargo, recorridos);
            }
        }
        return masLargo;
    }

    private void caminoMasLargoRecursivo(int posOrigen, int posDestino, Lista masLargo, Lista recorridos) {
        boolean existe = false;

        if (recorridos.localizar(this.vertices[posOrigen]) == -1) {
            recorridos.insertar(vertices[posOrigen], recorridos.longitud() + 1);
            //Adyacentes del elemento actual
            int i = 0;

            //Preguntar si sus adyacentes es el buscado

            boolean encontro = false;

            while (i < Grafo.TAM) {
                if (this.arcos[posOrigen][i] && i == posDestino) {
                    recorridos.insertar(this.vertices[i], recorridos.longitud() + 1);

                    if (masLargo.longitud() == 0 || (masLargo.longitud() >= 2 && masLargo.longitud() < recorridos.longitud())) {
                        masLargo.modificarLista(recorridos);
                    }
                    recorridos.eliminar(recorridos.longitud());

                }
                if (this.arcos[posOrigen][i]) {
                    //Recorrer a los adyacentes

                    caminoMasLargoRecursivo(i, posDestino, masLargo, recorridos);

                }
                i++;
            }
            recorridos.eliminar(recorridos.longitud());
        }
    }


    public Lista listarEnProfundidad() {
        Lista lsProfundidad = new Lista();

        if (!this.vacio()) {

            for (int i = 0; i < this.cantVertices; i++) {
                if (lsProfundidad.localizar(vertices[i]) == -1) {
                    listarEnProfundidadRecursivo(i, lsProfundidad);
                }
            }
        }
        return lsProfundidad;
    }

    private void listarEnProfundidadRecursivo(int posVertice, Lista visitados) {

        visitados.insertar(this.vertices[posVertice], visitados.longitud() + 1);
        for (int i = 0; i < Grafo.TAM; i++) {
            //System.out.println("VISITADO: " + this.vertices[i]);
            if (visitados.localizar(this.vertices[i]) == -1) {
                if (this.arcos[posVertice][i]) {
                    listarEnProfundidadRecursivo(i, visitados);
                }
            }
        }
    }

    public Lista listarEnAnchura() {
        Lista lsEnAnchura = new Lista();

        if (!this.vacio()) {
            for (int i = 0; i < this.cantVertices; i++) {
                listarEnAnchuraRecursivo(i, lsEnAnchura);
            }
        }
        return lsEnAnchura;
    }

    private void listarEnAnchuraRecursivo(int posVertice, Lista visitados) {
        Cola q = new Cola();

        q.poner(posVertice);
        while (!q.esVacia()) {
            int u = (int) q.obtenerFrente();
            q.sacar();
            for (int i = 0; i < this.cantVertices; i++) {
                if (visitados.localizar(vertices[posVertice]) == -1) {
                    visitados.insertar(this.vertices[u], visitados.longitud() + 1);
                    q.poner(i);
                }
            }
        }
    }

    public boolean vacio() {
        return cantVertices == 0;
    }

    public boolean estaLleno() {
        return (cantVertices == TAM);
    }

    public String toString() {
        String cadena = "";

        if (!this.vacio()) {
            //Vertices
            String vertices = "";
            for (int i = 0; i < Grafo.TAM; i++) {
                vertices += "|      " + this.vertices[i] + "      ";
            }
            System.out.println("////////////" + vertices);
            for (int i = 0; i < Grafo.TAM; i++) {
                if (this.vertices[i] != null) {
                    System.out.print("Vertice: " + this.vertices[i] + " -> ");
                    for (int j = 0; j < Grafo.TAM; j++) {
                        System.out.print((this.arcos[i][j]) ? "  OCUPADO   " : " DESOCUPADO ");
                        System.out.print(" ");
                    }
                    System.out.println("");
                }
            }
        } else {
            cadena = "GRAFO VACIO";
        }
        return cadena;
    }

    @Override
    public Grafo clone() {
        Grafo clone = new Grafo();

        if (!this.vacio()) {
            clone.cantVertices = this.cantVertices;
            for (int i = 0; i < this.cantVertices; i++) {
                clone.vertices[i] = this.vertices[i];
            }
            for (int i = 0; i < this.cantVertices; i++) {
                for (int j = 0; j < this.cantVertices; j++) {
                    clone.arcos[i][j] = this.arcos[i][j];
                }
            }
        }

        return clone;
    }

}
