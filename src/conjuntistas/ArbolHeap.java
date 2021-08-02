package conjuntistas;

public class ArbolHeap {

    private static final int TAMANIO = 10;
    private Comparable[] heap;
    private int ultimo;

    public ArbolHeap() {
        this.heap = new Comparable[TAMANIO];
        this.ultimo = 0;
    }

    public boolean insertar(Comparable element) {

        boolean seInserto = false;

        if (this.esVacio()) {
            this.heap[1] = element;
            this.ultimo++;
        } else {
            if (this.ultimo < heap.length) {
                subirCima(element);
                seInserto = true;
                this.ultimo++;
            }
        }
        return seInserto;

    }

    private void subirCima(Comparable element) {
        int i = 1, padre, nuevo;
        Comparable auxiliar;
        boolean subio = false;
        //Insertar el elemento en el último insertado.

        //Guardo el elemento en la última posición. Nuevo indica la posición del elemento recien
        //agregado.
        nuevo = this.ultimo + 1;
        heap[nuevo] = element;

        //Utilizo una variable "subir" para que continue comparando con su antecesor.
        while (!subio) {
            //encuentro al padre dividiendo la posición del nuevo elemento por 2 para obtener la posición
            //del padre.
            padre = nuevo / 2;
            System.out.println("COMPARANDO padre: " + heap[padre] + " NUEVO" + heap[nuevo]);
            //comparo si el elemento "nuevo" es menor que el padre. Si es el caso, intercambian valores
            //y el padre pasa a ser hijo.
            if (heap[nuevo].compareTo(heap[padre]) < 0) {

                auxiliar = heap[padre];
                heap[padre] = element;
                heap[nuevo] = auxiliar;

                //Si el padre no es la raiz entonces, el elemento "nuevo" ocupa el nueva posición del padre.
                if (padre != 1) {
                    nuevo = padre;
                } else {
                    subio = true;
                }
            } else {
                subio = true;
            }
        }
    }

    public boolean eliminarCima() {
        boolean seElimino = false;

        this.heap[1] = this.heap[ultimo];
        this.ultimo--;

        if (!this.esVacio()) {
            bajarCima(1);
        }

        return seElimino;
    }

    private void bajarCima(int posPadre) {
        Comparable auxiliar;
        boolean bajoCima = false;
        int posH;

        while (!bajoCima) {
            posH = posPadre * 2;
            if (posH <= this.ultimo) {

                if (posH < this.ultimo) {
                    if(this.heap[posH + 1].compareTo(this.heap[posH]) < 0){
                        posH++;
                    }
                    if (this.heap[posH].compareTo(this.heap[posPadre]) < 0) {
                        auxiliar = this.heap[posPadre];
                        this.heap[posPadre] = this.heap[posH];
                        this.heap[posH] = auxiliar;
                        posPadre = posH;
                    } else {
                        bajoCima = true;
                    }
                }
            } else {
                bajoCima = true;
            }
        }
    }

    public boolean esVacio() {
        return this.ultimo == 0;
    }

    @Override
    public String toString() {
        String cadena = "";

        if (!this.esVacio()) {
            if (this.ultimo == 1) {
                cadena += "Padre: " + heap[1];

                cadena += " - Hijo Izq: *";
                cadena += " - Hijo Der: *";

            } else {
                int i = 1;

                while (i <= this.ultimo) {
                    cadena += "\nPadre: " + this.heap[i];
                    if (this.ultimo == i) {
                        cadena += " - Hijo Izq: * - Hijo Der: *";

                    } else {
                        cadena += " - Hijo Izq: " + (((2 * i) <= this.ultimo) ? this.heap[2 * i] : "*");
                        cadena += " - Hijo Der: " + (((2 * i + 1) <= this.ultimo) ? this.heap[2 * i + 1] : "*");
                        /*
                        cadena += " - Hijo Izq: " + ((2 * i > this.ultimo) ? "*" : this.heap[2 * i]);
                        if (i + 1 == this.ultimo) {
                            cadena += " - Hijo Der: " + ((this.ultimo % 2 != 0) ? "*" : this.heap[2 * i + 1]);
                        } else {
                            cadena += " - Hijo Der: " + (((2 * i + 1) > this.ultimo) ? "*" : this.heap[2 * i + 1]);
                        }
                        */

                    }
                    i++;
                }
            }
        } else {
            cadena = "VACIO";
        }
        return cadena;
    }

}
