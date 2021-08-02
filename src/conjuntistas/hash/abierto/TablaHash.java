package conjuntistas.hash.abierto;

import conjuntistas.Funciones;
import lineales.dinamico.Lista;

public class TablaHash {

    private static final int TAMANIO = 10;
    private Nodo[] tabla;
    private int cantElementos;

    public TablaHash() {
        this.tabla = new Nodo[TAMANIO];
        this.cantElementos = 0;
    }

    public boolean insertar(Object element) {
        boolean seInserto = false;
        if (cantElementos < TablaHash.TAMANIO) {
            int pos = Funciones.f7((int) element) % TablaHash.TAMANIO;

            if (this.tabla[pos] == null) {
                this.tabla[pos] = new Nodo(element, null);
                cantElementos++;
            } else {
                int elemInsertados = 0, maxInsertados = 3;
                Nodo aux = this.tabla[pos];
                boolean encontro = false;
                //buscarElemento

                while (elemInsertados <= maxInsertados && !encontro & aux != null) {
                    elemInsertados++;
                    encontro = aux.getElement().equals(element);
                    aux.getEnlace();
                }

                if (!encontro) {
                    this.tabla[pos] = new Nodo(element, this.tabla[pos]);
                    seInserto = true;
                    cantElementos++;
                }

            }
        }
        return seInserto;
    }

    public boolean eliminar(Object element) {
        boolean seElimino = false;
        if (!this.esVacio()) {
            int pos = Funciones.f7((int) element) % TablaHash.TAMANIO;
            Nodo aux = this.tabla[pos];
            boolean encontro = false;
            if (aux != null && aux.getElement().equals(element)) {
                System.out.println("ACA");
                this.tabla[pos] = this.tabla[pos].getEnlace();
            } else {
                while (aux != null && !encontro) {
                    if (aux.getEnlace() != null && aux.getEnlace().getElement().equals(element)) {
                        aux.setEnlace(aux.getEnlace().getEnlace());
                        encontro = true;
                        seElimino =true;
                        cantElementos--;
                    }else{
                        aux = aux.getEnlace();
                    }
                }
            }

        }
        return seElimino;
    }
    public boolean pertenece(Object element) {
        boolean seEncuentra = false;

        if(!this.esVacio()){
            int i = 0;
            int elementos = 0;
            while(elementos < this.cantElementos && i< TablaHash.TAMANIO && !seEncuentra ) {
                Nodo aux = this.tabla[i];
                while(!seEncuentra && aux != null){
                    seEncuentra =  aux.getElement().equals(element);
                    aux = aux.getEnlace();

                    elementos++;
                }
                i++;
            }
        }
        return seEncuentra;
    }

    public Lista listar() {
        Lista ls = new Lista();

        if(!this.esVacio()) {
            int i = 0, elementos = 0;

            while(i < TablaHash.TAMANIO && elementos < this.cantElementos) {
                Nodo aux = tabla[i];

                while(aux != null) {
                    System.out.println("listado - Pos: " + i + " elemento: " + aux.getElement());

                    ls.insertar(aux.getElement(), ls.longitud() + 1);
                    aux = aux.getEnlace();
                    elementos++;
                }
                i++;
            }
        }
        return ls;
    }

    public boolean esVacio() {
        return cantElementos == 0;
    }
}
