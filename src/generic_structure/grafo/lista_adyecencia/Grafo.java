package generic_structure.grafo.lista_adyecencia;

import generic_structure.lineales.dinamico.Lista;

public class Grafo {
    private NodoVertice inicio;

    public Grafo() {
        this.inicio = null;
    }

    public boolean insertarVertice(Object element) {
        boolean seInserto = false;
        //Buscar vertice si se encuentra;
        NodoVertice aux = this.ubicarVertice(element);
        if (aux == null) {
            this.inicio = new NodoVertice(element, this.inicio, null);
            seInserto = true;
        }
        return seInserto;
    }

    private NodoVertice ubicarVertice(Object element) {
        NodoVertice aux = this.inicio;

        while (aux != null && !aux.getElement().equals(element)) {
            aux = aux.getSgteVertice();
        }
        return aux;
    }

    public boolean eliminarVertice(Object element) {
        boolean seElimino = false;
        if (!this.vacio()) {

            NodoVertice aux = this.inicio;
            if (this.inicio.equals(element)) {
                this.inicio = this.inicio.getSgteVertice();
                this.eliminarAdyacentes(element);
            } else {
                while (!seElimino && aux != null) {
                    if (aux.getSgteVertice() != null && aux.getSgteVertice().equals(element)) {
                        aux.setSgteVertice(aux.getSgteVertice().getSgteVertice());
                        seElimino = true;

                        //Eliminar adyacentes de todos los vertices.
                        this.eliminarAdyacentes(element);
                    }
                    aux = aux.getSgteVertice();
                }
            }
        }
        return seElimino;
    }

    private void eliminarAdyacentes(Object element) {

        if (!this.vacio()) {
            NodoVertice aux = this.inicio;

            while (aux != null) {
                NodoAdy ady = aux.getPrimerAdy();
                if (ady.getVertice().getElement().equals(element)) {
                    aux.setPrimerAdy(aux.getPrimerAdy().getSgteAdyacente());
                } else {
                    boolean encontro = false;
                    while (!encontro && ady != null) {
                        if (ady.getSgteAdyacente() != null &&
                                ady.getSgteAdyacente().getVertice().getElement().equals(element)) {
                            ady.setSgteAdyacente(ady.getSgteAdyacente().getSgteAdyacente());
                            encontro = true;
                        }
                        ady = ady.getSgteAdyacente();
                    }
                }
                aux = aux.getSgteVertice();
            }

        }
    }


    public boolean insertarArco(Object origen, Object destino) {
        boolean seInsertoArco = false;

        if (!this.vacio()) {
            NodoVertice nodoOrigen = this.ubicarVertice(origen);
            NodoVertice nodoDestino = this.ubicarVertice(destino);

            if (nodoOrigen != null && nodoDestino != null) {
                //buscar si existe arco

                if (!existeArco(origen, destino)) {

                    nodoOrigen.setPrimerAdy(new NodoAdy(nodoDestino, nodoOrigen.getPrimerAdy(), ""));
                    seInsertoArco = true;
                }
            }
        }
        return seInsertoArco;
    }

    public boolean eliminarArco(Object origen, Object destino) {
        boolean seElimino = false;

        if (!this.vacio()) {

            //Verificar si existen los vertices en el generic_structure.grafo.
            NodoVertice verticeOrigen = ubicarVertice(origen);
            NodoVertice verticeDestino = ubicarVertice(destino);

            if (verticeOrigen != null && verticeDestino != null) {
                //Obtener el primer adyacente del vertice origen.
                NodoAdy ady = verticeOrigen.getPrimerAdy();
                //En caso de que el primer adyacente sea el buscado, se sustituye con el siguiente.
                if (ady.getVertice().getElement().equals(destino)) {
                    verticeOrigen.setPrimerAdy(verticeOrigen.getPrimerAdy().getSgteAdyacente());
                } else {
                    //Busca los adyacentes del vertice origen hasta encontrar.
                    boolean encontro = false;
                    while (!encontro && ady != null) {
                        if (ady.getSgteAdyacente() != null &&
                                ady.getSgteAdyacente().getVertice().getElement().equals(destino)) {
                            ady.setSgteAdyacente(ady.getSgteAdyacente().getSgteAdyacente());
                            encontro = true;
                        }
                        ady = ady.getSgteAdyacente();
                    }
                }
            }
        }

        return seElimino;
    }

    public boolean existeVertice(Object verticeBuscado) {
        boolean encontro = false;
        if (!this.vacio()) {
            NodoVertice aux = this.inicio;
            while (!encontro && aux != null) {
                if (aux.getElement().equals(verticeBuscado)) {
                    encontro = true;
                }
                aux = aux.getSgteVertice();
            }
        }
        return encontro;
    }

    public boolean existeArco(Object origen, Object destino) {
        boolean existe = false;
        if (!this.vacio()) {
            NodoVertice verticeOrigen = ubicarVertice(origen);
            if (verticeOrigen != null) {

                NodoAdy ady = verticeOrigen.getPrimerAdy();
                while (!existe && ady != null) {
                    if (ady.getVertice().equals(destino)) {
                        existe = true;
                    }
                    ady = ady.getSgteAdyacente();
                }
            }
        }
        return existe;
    }

    public boolean existeCamino(Object origen, Object destino) {
        boolean existe = false;
        if (!this.vacio()) {
            NodoVertice verticeOrigen = ubicarVertice(origen);
            NodoVertice verticeDestino = ubicarVertice(destino);

            if (verticeOrigen != null && verticeDestino != null) {
                NodoAdy ady = verticeOrigen.getPrimerAdy();
                Lista visitados = new Lista();
                while (!existe && ady != null) {
                    existe = existeCaminoRecursivo(ady.getVertice(), destino, visitados);
                    ady = ady.getSgteAdyacente();
                }
            }
        }
        return existe;
    }

    private boolean existeCaminoRecursivo(NodoVertice vertice, Object destino, Lista visitados) {
        boolean existe = false;

        if (visitados.localizar(vertice.getElement()) == -1) {
            visitados.insertar(vertice.getElement(), visitados.longitud() + 1);
            if (vertice.getElement().equals(destino)) {
                existe = true;
            } else {
                NodoAdy adyVertice = vertice.getPrimerAdy();
                while (!existe && adyVertice != null) {
                    existe = existeCaminoRecursivo(adyVertice.getVertice(), destino, visitados);
                    adyVertice = adyVertice.getSgteAdyacente();
                }
            }
        }

        return existe;
    }

    public Lista caminoMasCorto(Object origen, Object destino) {
        Lista caminoMasCorto = new Lista();
        if (!this.vacio()) {
            NodoVertice verticeOrigen = ubicarVertice(origen);
            NodoVertice verticeDestino = ubicarVertice(destino);

            if (verticeOrigen != null && verticeDestino != null) {
                Lista visitados = new Lista();
                NodoAdy ady = verticeOrigen.getPrimerAdy();
                while (ady != null) {
                    caminoMasCortoRecursivo(ady.getVertice(), destino, caminoMasCorto, visitados);
                    ady = ady.getSgteAdyacente();
                }
            }
        }
        return caminoMasCorto;
    }

    private void caminoMasCortoRecursivo(NodoVertice vertice, Object destino, Lista caminoMasCorto, Lista visitados) {
        if (visitados.localizar(vertice.getElement()) == -1) {

            visitados.insertar(vertice.getElement(), visitados.longitud() + 1);
            if (vertice.getElement().equals(destino)) {

                if (caminoMasCorto.longitud() > visitados.longitud() || caminoMasCorto.longitud() == 0) {

                    caminoMasCorto.modificarLista(visitados);

                }
                System.out.println(visitados);
            }
            NodoAdy ady = vertice.getPrimerAdy();
            while (ady != null) {
                caminoMasCortoRecursivo(ady.getVertice(), destino, caminoMasCorto, visitados);
                ady = ady.getSgteAdyacente();
            }
            visitados.eliminar(visitados.localizar(vertice.getElement()));
        }
    }
    public Lista caminoMasLargo(Object origen, Object destino) {
        Lista caminoMasLargo = new Lista();
        if (!this.vacio()) {
            NodoVertice verticeOrigen = ubicarVertice(origen);
            NodoVertice verticeDestino = ubicarVertice(destino);

            if (verticeOrigen != null && verticeDestino != null) {
                Lista visitados = new Lista();
                NodoAdy ady = verticeOrigen.getPrimerAdy();
                while (ady != null) {
                    caminoMasLargoRecursivo(ady.getVertice(), destino, caminoMasLargo, visitados);
                    ady = ady.getSgteAdyacente();
                }
            }
        }
        return caminoMasLargo;
    }

    private void caminoMasLargoRecursivo(NodoVertice vertice, Object destino, Lista caminoMasLargo, Lista visitados) {
        if (visitados.localizar(vertice.getElement()) == -1) {

            visitados.insertar(vertice.getElement(), visitados.longitud() + 1);
            if (vertice.getElement().equals(destino)) {
                if (caminoMasLargo.longitud() < visitados.longitud() || caminoMasLargo.longitud() == 0) {
                    caminoMasLargo.modificarLista(visitados);
                }
            }
            NodoAdy ady = vertice.getPrimerAdy();
            while (ady != null) {
                caminoMasLargoRecursivo(ady.getVertice(), destino, caminoMasLargo, visitados);
                ady = ady.getSgteAdyacente();
            }
            visitados.eliminar(visitados.localizar(vertice.getElement()));
        }
    }

    public boolean vacio() {
        return this.inicio == null;
    }

    @Override
    public String toString() {
        String cadena = "";

        if (this.vacio()) {
            cadena = "GRAFO VACIO";
        } else {
            //Vertices
            NodoVertice aux = this.inicio;
            while (aux != null) {
                cadena += "\nV: " + aux.getElement();
                NodoAdy ady = aux.getPrimerAdy();
                if (ady == null) {
                    cadena += " -> *";
                } else {
                    while (ady != null) {
                        cadena += " -> " + ady.getVertice().getElement();
                        ady = ady.getSgteAdyacente();
                    }
                }
                aux = aux.getSgteVertice();
            }
        }

        return cadena;
    }
}
