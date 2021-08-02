package conjuntistas;

public class ArbolAVL {
    private NodoAVL raiz;

    public ArbolAVL() {
        this.raiz = null;
    }


    public boolean esVacio() {
        return this.raiz == null;
    }

    public boolean insertar(Comparable element) {
        boolean seInserto = false;
        System.out.println(element + " elemento");

        if (!this.esVacio()) {
            seInserto = insertarRecursivo(this.raiz, element);
            this.raiz = this.verificaBalanceo(this.raiz);
        } else {
            this.raiz = new NodoAVL(element, null, null);
            seInserto = true;
        }
        this.raiz.recalcularAltura();
        return seInserto;
    }

    public boolean insertarRecursivo(NodoAVL aux, Comparable element) {
        boolean seInserto = false;

        if (aux != null) {
            if (aux.getElemento().compareTo(element) == 0) {
                seInserto = false;
            } else {
                if (aux.getElemento().compareTo(element) > 0) {
                    //element es menor que el nodo actual.

                    if (aux.getIzquierdo() != null) {
                        seInserto = insertarRecursivo(aux.getIzquierdo(), element);
                        aux.recalcularAltura();
                        aux.setIzquierdo(this.verificaBalanceo(aux.getIzquierdo()));
                        aux.recalcularAltura();

                    } else {
                        aux.setIzquierdo(new NodoAVL(element, null, null));
                        seInserto = true;
                    }

                } else {
                    //element es mayor que el nodo actual.

                    if (aux.getDerecho() != null) {
                        seInserto = insertarRecursivo(aux.getDerecho(), element);
                        aux.recalcularAltura();
                        aux.setDerecho(this.verificaBalanceo(aux.getDerecho()));
                        aux.recalcularAltura();
                    } else {
                        aux.setDerecho(new NodoAVL(element, null, null));
                        seInserto = true;
                    }
                }
                aux.recalcularAltura();
            }
        }
        return seInserto;
    }


    private NodoAVL verificaBalanceo(NodoAVL raiz) {
        int balancePadre;

        if (raiz != null) {
            balancePadre = calcularBalance(raiz);

            if (balancePadre == 2 || balancePadre == -2) {
                /*
                int balanceHijo = calcularBalance(balancePadre == 2 ) ? raiz.getIzquierdo() : raiz.getDerecho());
                raiz = auxverificaBalanceo(raiz, balancePadre, balanceHijo);

                 */
                int balanceHijo = (balancePadre == 2) ? calcularBalance(raiz.getIzquierdo()) : calcularBalance(raiz.getDerecho());
                raiz = auxverificaBalanceo(raiz, balancePadre, balanceHijo);
            } else {
                //Esta balanceado
            }

        }
        return raiz;
    }

    private NodoAVL auxverificaBalanceo(NodoAVL padre, int balancePadre, int balanceHijo) {

        System.out.println(padre.getElemento() + " : balance: " + balancePadre + " : balancehijo: " + balanceHijo);

        if (balancePadre == 2 && (balanceHijo == 1 || balanceHijo == 0)) {
            padre = rotarDerecha(padre);
        } else if (balancePadre == 2 && balanceHijo == -1) {
            padre = rotacionDobleIzquierdaDerecha(padre);
        } else if (balancePadre == -2 && (balanceHijo == -1 || balanceHijo == 0)) {
            padre = rotarIzquierda(padre);
        } else if (balancePadre == -2 && balanceHijo == 1) {
            padre = rotacionDobleDerechaIzquierda(padre);
        }
        return padre;
    }

    public int calcularBalance(NodoAVL raiz) {
        int alturaIzq, alturaDer;

        int balancePadre = 0;
        if (raiz != null) {
            alturaIzq = ((raiz.getIzquierdo() != null) ? raiz.getIzquierdo().getAltura() : -1);
            alturaDer = ((raiz.getDerecho() != null) ? raiz.getDerecho().getAltura() : -1);

            balancePadre = alturaIzq - alturaDer;
        }
        return balancePadre;
    }


    private NodoAVL rotarIzquierda(NodoAVL raiz) {
        System.out.println("SE HIZO ROTACION SIMPLE IZQUIERDA");
        NodoAVL hijo = raiz.getDerecho();
        NodoAVL temp = hijo.getIzquierdo();
        hijo.setIzquierdo(raiz);
        raiz.setDerecho(temp);
        return hijo;
    }

    private NodoAVL rotarDerecha(NodoAVL raiz) {
        System.out.println("SE HIZO ROTACION SIMPLE DERECHA");

        NodoAVL hijo = raiz.getIzquierdo();
        NodoAVL temp = (hijo != null) ? hijo.getDerecho() : null;
        hijo.setDerecho(raiz);
        raiz.setIzquierdo(temp);
        return hijo;
    }

    private NodoAVL rotacionDobleIzquierdaDerecha(NodoAVL raiz) {
        System.out.println("SE HIZO DOBLE ROTACION IZQUIERDA DERECHA");

        NodoAVL pivote = raiz.getIzquierdo();
        NodoAVL hijoIzquierdo = this.rotarIzquierda(pivote);
        raiz.setIzquierdo(hijoIzquierdo);
        return this.rotarDerecha(raiz);
    }

    private NodoAVL rotacionDobleDerechaIzquierda(NodoAVL raiz) {

        System.out.println("SE HIZO DOBLE ROTACION DERECHA IZQUIERDA");
        raiz.setDerecho(this.rotarDerecha(raiz.getDerecho()));
        return this.rotarIzquierda(raiz);
    }

    @Override
    public String toString() {
        String cadena;

        if (!this.esVacio()) {
            cadena = toStringRecursivo(this.raiz);
        } else {
            cadena = "ARBOL VACIO";
        }

        return cadena;
    }

    private String toStringRecursivo(NodoAVL aux) {
        String cadena = "";

        if (aux != null) {
            cadena += "\n Padre: " + aux.getElemento();
            cadena += " - HijoIzq: " + ((aux.getIzquierdo() != null) ? aux.getIzquierdo().getElemento() : "*");
            cadena += " - HijoDer: " + ((aux.getDerecho() != null) ? aux.getDerecho().getElemento() : "*");

            if (aux.getIzquierdo() != null) {
                cadena += toStringRecursivo(aux.getIzquierdo());
            }
            if (aux.getDerecho() != null) {
                cadena += toStringRecursivo(aux.getDerecho());
            }
        }
        return cadena;
    }


}
