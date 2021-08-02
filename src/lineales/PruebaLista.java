package lineales;

import lineales.dinamico.Cola;
import lineales.dinamico.Lista;
import lineales.dinamico.Pila;

/**
 *
 * @author Martin
 */
public class PruebaLista {

    public static void main(String[] args) {
        Lista l1 = new Lista();
        Lista l2 = new Lista();

        l1.insertar(6, 1);
        l1.insertar(4, 1);
        l1.insertar(2, 1);

        l2.insertar(7, 1);
        l2.insertar(6, 1);
        l2.insertar(1, 1);
        l2.insertar(5, 1);

        // 2 4 6 - 5 1 6 7
        Lista l3 = new Lista();

        l3.insertar(9, l3.longitud() + 1);
        l3.insertar(6, l3.longitud() + 1);
        l3.insertar(5, l3.longitud() + 1);
        //l3.insertar(0, l3.longitud() + 1);
        l3.insertar(9, l3.longitud() + 1);
        l3.insertar(6, l3.longitud() + 1);
        l3.insertar(5, l3.longitud() + 1);
        //l3.insertar(0, l3.longitud() + 1);
        l3.insertar(5, l3.longitud() + 1);
        l3.insertar(6, l3.longitud() + 1);
        l3.insertar(9, l3.longitud() + 1);
        l3.insertar(9, l3.longitud() + 1);

        Lista l4 = new Lista();

        l4.insertar(1, 1);
        l4.insertar(2, 1);
        //l4.insertar(2, 1);
        l4.insertar(1, 1);

        System.out.println((esCapicua(l4) ? "Es capicua" : "No es capicua"));

        //Lista concatenar = concatenar(l1, l2);
        //System.out.println(concatenar);
        //System.out.println(l3);
        //System.out.println("CADENA ES: " + ((comprobar(l3)) ? "VALIDA" : "NO VALIDA"));
        //Lista interca = intercalar(l1, l2);
        //System.out.println("Cant de veces de 6: " + contarR(9, l3));
    }

    public static Lista concatenar(Lista l1, Lista l2) {
        Lista concatenar = new Lista();

        if (!l1.esVacia() && !l2.esVacia()) {
            int longitudL1 = l1.longitud();
            int longitudL2 = l2.longitud();

            concatenar = l1.clone();

            int i = longitudL1 + 1, j = 1;
            int longTotal = longitudL1 + longitudL2;
            while (i <= longTotal) {
                concatenar.insertar(l2.recuperar(j), i);
                i++;
                j++;
            }
        }
        return concatenar;
    }

    public static boolean comprobar(Lista l1) {
        boolean esCadenaValida = false;
        if (!l1.esVacia()) {

            //Primera secuencia
            boolean encontro = false;
            int i = 1;
            Cola colaAuxiliar = new Cola();
            while (!encontro && i <= l1.longitud()) {
                if ((int) l1.recuperar(i) == 0) {
                    encontro = true;
                } else {
                    colaAuxiliar.poner(l1.recuperar(i));
                    i++;
                }
            }
            System.out.println("cola: " + colaAuxiliar);
            int largoCadena = i - 1;
            System.out.println("LARGO CADENA " + largoCadena);
            i++;
            System.out.println("I: " + i);
            if (!encontro) {
                esCadenaValida = false;
            } else {
                System.out.println("PASO LA PRIMERA ETAPA");
                encontro = false;
                Pila pilaAuxiliar = new Pila();
                boolean coincide = true;
                while (!encontro && i <= l1.longitud() && coincide && !colaAuxiliar.esVacia()) {
                    if ((int) l1.recuperar(i) != (int) colaAuxiliar.obtenerFrente()) {
                        coincide = false;
                    } else {
                        if ((int) l1.recuperar(i) == 0) {
                            encontro = true;
                        } else {

                            colaAuxiliar.sacar();

                            pilaAuxiliar.apilar(l1.recuperar(i));
                        }
                    }
                    i++;
                }

                if ((!encontro || !coincide) && !colaAuxiliar.esVacia()) {
                    esCadenaValida = false;
                    System.out.println("NO PASO LA SEGUNDA ETAPA");
                } else {
                    i++;
                    System.out.println("I: " + i);
                    System.out.println("PASO LA SEGUNDA ETAPA");
                    coincide = true;
                    System.out.println("PILA: " + pilaAuxiliar);
                    while (i <= l1.longitud() && coincide && !pilaAuxiliar.esVacia()) {
                        System.out.println("RECUPERA pos " + i + ": " + l1.recuperar(i) + " compara " + pilaAuxiliar.obtenerTope());
                        if (pilaAuxiliar.obtenerTope().equals(l1.recuperar(i))) {
                            System.out.println("SACO: " + colaAuxiliar.obtenerFrente());
                            pilaAuxiliar.desapilar();
                            i++;
                        } else {
                            coincide = false;
                            esCadenaValida = false;
                        }
                    }
                    if (pilaAuxiliar.esVacia() & coincide) {
                        esCadenaValida = true;
                        System.out.println("PASO LA TERCERA ETAPA");
                    }
                }
            }
        }

        return esCadenaValida;
    }

    public static Lista invertir(Lista l1) {
        Lista invertido = new Lista();
        if (!l1.esVacia()) {
            int n = l1.longitud();

            Pila pilaAuxiliar = new Pila();
            for (int i = 1; i <= n; i++) {
                pilaAuxiliar.apilar(l1.recuperar(i));
            }
            while (!pilaAuxiliar.esVacia()) {
                invertido.insertar(pilaAuxiliar.obtenerTope(), 1);
                pilaAuxiliar.desapilar();
            }
        }
        return invertido;
    }

    public static Lista intercalar(Lista l1, Lista l2) {
        Lista intercalado = new Lista();
        if (!l1.esVacia() && !l2.esVacia()) {
            int i = 1;

            while (i <= l1.longitud() || i <= l2.longitud()) {
                if (i > l1.longitud()) {
                    intercalado.insertar(l2.recuperar(i), intercalado.longitud() + 1);
                } else if (i > l2.longitud()) {
                    intercalado.insertar(l1.recuperar(i), intercalado.longitud() + 1);

                } else {
                    intercalado.insertar(l1.recuperar(i), intercalado.longitud() + 1);
                    intercalado.insertar(l2.recuperar(i), intercalado.longitud() + 1);
                }
                i++;
            }
        }
        return intercalado;
    }

    public static int contarIterativo(Object elem, Lista l1) {
        int veces = 0;

        if (!l1.esVacia()) {
            int i = 1;
            while (i <= l1.longitud()) {
                if (l1.recuperar(i).equals(elem)) {
                    veces += 1;
                }
                i++;

            }
        }
        return veces;
    }

    public static int contarR(Object elem, Lista l1) {
        int veces = 0;

        if (!l1.esVacia()) {
            int i = 1;
            veces = contarRecursivo(elem, i, l1);
        }
        return veces;
    }

    private static int contarRecursivo(Object elem, int i, Lista l1) {
        int veces = 0;
        if (l1.longitud() == i) {
            if (l1.recuperar(i).equals(elem)) {
                veces = 1;
            } else {
                veces = 0;
            }
        } else {
            if (l1.recuperar(i).equals(elem)) {
                veces = contarRecursivo(elem, i + 1, l1) + 1;
            } else {
                veces = contarRecursivo(elem, i + 1, l1);

            }
        }
        return veces;
    }

    public static boolean esCapicua(Lista l1) {
        boolean esCapicua = true;

        if (!l1.esVacia()) {
            int mitad = (l1.longitud() / 2);
           
            int i = 1;
            int j = l1.longitud();
            while (esCapicua && i <= mitad) {

                if (!l1.recuperar(i).equals(l1.recuperar(j))) {
                    esCapicua = false;
                } 
                i++;
                j--;
            }
        }
        return esCapicua;
    }
}
