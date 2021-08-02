package generic_structure.jerarquicas.estatico;

/**
 *
 * @author Martin
 */
public class ArbolBinario {

    private static final int TAMANIO = 10;
    private CeldaBin[] arreglo;
    private int raiz;

    public ArbolBinario() {
        this.arreglo = new CeldaBin[TAMANIO];
        this.raiz = -1;

        for (int i = 0; i < arreglo.length; i++) {
            arreglo[i] = new CeldaBin();
        }
    }

    public boolean insertar(Object elemNuevo, Object elemPadre, char pos) {

        boolean seInserto = false;

        if (this.esVacio()) {
            this.arreglo[2].setElemento(elemNuevo);
            this.arreglo[2].enUso(true);

        } else {
            CeldaBin padre = buscarCelda(elemPadre);
            if (padre != null) {
                if (padre.getPosDerecho() == -1 && pos == 'D') {
                    int espacio = buscarEspacio();
                    if (espacio != -1) {
                        padre.setPosDerecho(espacio);
                        this.arreglo[espacio].setElemento(elemNuevo);
                        seInserto = true;
                    }
                } else {
                    if (padre.getPosIzquierda() == -1 && pos == 'I') {
                        int espacio = buscarEspacio();
                        if (espacio != -1) {
                            padre.setPosIzquierdo(espacio);
                            this.arreglo[espacio].setElemento(elemNuevo);
                            seInserto = true;

                        }
                    } else {
                        //El padre ya tiene ocupados los hijos.
                        
                        seInserto = false;
                    }
                }

            }
        }
        return seInserto;
    }
    
    /**
     * 
     * @param elem
     * @return 
     */
    
    public boolean eliminar(Object elem){
        boolean seElimino = false;
        
        if(!this.esVacio()){
            CeldaBin celda = buscarCelda(elem);
            if(celda != null){
                //Elimina todo el enlace y sus hijos supongo
                celda.setElemento(null);
                celda.enUso(false);
            }
        }
        return seElimino;
    }

    private CeldaBin buscarCelda(Object elem) {
        CeldaBin padre = null;

        if (!this.esVacio()) {
            boolean encontro = false;
            int i = 0;
            while (!encontro && i < arreglo.length) {
                if (this.arreglo[i].enUso()) {
                    CeldaBin celda = this.arreglo[i];
                    if (celda.getElemento().equals(elem)) {
                        encontro = true;
                        padre = celda;
                    }
                }
            }
        }
        return padre;
    }

    private Object buscarPadre(Object elem) {
        Object padre = null;

        if (!this.esVacio()) {
            boolean encontro = false;
            int i = 0;
            while (!encontro && i < arreglo.length) {
                if (this.arreglo[i].enUso()) {
                    CeldaBin celda = this.arreglo[i];
                    int posIz = celda.getPosIzquierda(), posDer = celda.getPosDerecho();

                    if (posIz != -1 && this.arreglo[posIz].getElemento().equals(elem)) {
                        encontro = true;
                        padre = this.arreglo[posIz].getElemento();
                    } else if (posDer != -1 && this.arreglo[posDer].getElemento().equals(elem)) {
                        encontro = true;
                        padre = this.arreglo[posIz].getElemento();

                    } else {
                        i++;
                    }
                }
            }
        }
        return padre;
    }

    public boolean esVacio() {
        return (raiz == -1);
    }

    private int buscarEspacio() {
        int espacio = -1;
        if (!this.esVacio()) {
            boolean encontro = false;
            int i = 0;
            while (!encontro && i < arreglo.length) {
                if (!this.arreglo[i].enUso()) {
                    espacio = i;
                    encontro = true;
                } else {
                    i++;
                }
            }
        }
        return espacio;
    }
}
