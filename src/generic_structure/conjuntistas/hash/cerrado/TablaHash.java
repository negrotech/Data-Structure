package generic_structure.conjuntistas.hash.cerrado;

import generic_structure.conjuntistas.Funciones;
import generic_structure.lineales.dinamico.Lista;

public class TablaHash {

    private static final int TAMANIO = 10;
    private CeldaHash[] tabla;
    private int cantElementos;
    private static final int VACIO = 0, OCUPADO = 1, BORRADO = -1;

    public TablaHash() {
        this.tabla = new CeldaHash[TAMANIO];
        this.cantElementos = 3;
        this.llenarCeldas();
    }

    private void llenarCeldas() {
        for (int i = 0; i < TAMANIO; i++) {
            this.tabla[i] = new CeldaHash(-1, VACIO);
        }
    }

    public boolean insertar(Object nuevoElem) {
        //int pos = nuevoElem.hashCode() % TAMANIO;
        int pos = Funciones.h((int) nuevoElem) % TAMANIO;
        int incremento = Funciones.h2((int) nuevoElem) % this.tabla.length;
        System.out.println("POS h1: " + pos + " ELEMENTO: " + nuevoElem);
        boolean seInserto = false;

        if (this.tabla[pos].getEstado() != VACIO) {
            int intento = 1;
            while (!seInserto && intento < TablaHash.TAMANIO) {

                /*
                pos = (Funciones.h((int) nuevoElem) +
                        (intento - 1) * (Funciones.h2((int) nuevoElem)) % tabla.length)
                        % TablaHash.TAMANIO;
                        */

                if (this.tabla[pos].getEstado() != OCUPADO) {
                    this.tabla[pos].setElement(nuevoElem);
                    this.tabla[pos].setEstado(OCUPADO);
                    seInserto = true;
                    cantElementos++;
                }
                System.out.println("POS: " + pos + " INTENTO: " + intento + " INCREMENTO " + incremento);
                pos = (pos + intento * incremento) % TablaHash.TAMANIO;
                intento++;
            }
        } else {
            System.out.println("ENTRO ACA");
            this.tabla[pos].setElement(nuevoElem);
            this.tabla[pos].setEstado(OCUPADO);
        }
        return seInserto;
    }

    public boolean esVacio() {
        return this.cantElementos == 0;
    }

    public Lista listar() {
        Lista ls = new Lista();

        if (!this.esVacio()) {
            int i = 0, elementos = 0;

            while (i < TablaHash.TAMANIO && elementos <= cantElementos) {
                if (this.tabla[i].getEstado() == TablaHash.OCUPADO) {
                    System.out.println("POS: " + i + " ELEMENTO: " + tabla[i].getElement());
                    ls.insertar(tabla[i].getElement(), ls.longitud() + 1);
                    elementos++;
                }
                i++;
            }
        }

        return ls;
    }
}
