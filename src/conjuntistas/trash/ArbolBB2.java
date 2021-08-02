package conjuntistas.trash;

import conjuntistas.NodoArbol;
import lineales.dinamico.Cola;
import lineales.dinamico.Lista;

public class ArbolBB2 {

    private NodoArbol raiz;

    public ArbolBB2() {
        this.raiz = null;
    }

    public boolean esVacio() {
        return this.raiz == null;
    }

    public boolean insertar(Comparable element) {
        boolean seInserto = false;

        if (!this.esVacio()) {
            seInserto = insertarRecursivo(this.raiz, element);
        } else {
            this.raiz = new NodoArbol(element, null, null);
        }
        return seInserto;
    }

    public boolean insertarRecursivo(NodoArbol aux, Comparable element) {
        boolean seInserto = false;

        if (aux != null) {
            if (aux.getElemento().compareTo(element) < 0) {
                if (aux.getDerecho() != null) {
                    seInserto = insertarRecursivo(aux.getDerecho(), element);
                } else {
                    aux.setDerecho(new NodoArbol(element, null, null));
                    seInserto = true;
                }
            } else if (aux.getElemento().compareTo(element) > 0) {
                if (aux.getIzquierdo() != null) {
                    seInserto = insertarRecursivo(aux.getIzquierdo(), element);

                } else {
                    aux.setIzquierdo(new NodoArbol(element, null, null));
                    seInserto = true;
                }

            }
        }
        return seInserto;
    }

    public boolean eliminar(Comparable element) {
        boolean seElimino = false;
        if (!this.esVacio()) {
            if (this.raiz.getElemento().compareTo(element) == 0) {
                //cuando se eliminar la raiz
                if (this.raiz.getDerecho() == null && this.raiz.getIzquierdo() == null) {
                    this.raiz = null;
                } else if (this.raiz.getIzquierdo() != null && this.raiz.getDerecho() == null) {
                    this.raiz = this.raiz.getIzquierdo();
                } else if (this.raiz.getDerecho() != null && this.raiz.getIzquierdo() == null) {
                    this.raiz = this.raiz.getDerecho();
                } else {
                    NodoArbol candidato = buscarMenorCandidato(this.raiz.getDerecho());
                    //NodoArbol candidato = buscarMayorCandidato((this.raiz.getIzquierdo()));
                    candidato.setIzquierdo(this.raiz.getIzquierdo());
                    candidato.setDerecho(this.raiz.getDerecho());
                    this.raiz = candidato;
                }
            } else {
                NodoArbol padre = buscaPadre(this.raiz, element);
                if (padre != null) {
                    if (padre.getIzquierdo() != null && padre.getIzquierdo().getElemento().compareTo(element) == 0) {
                        NodoArbol hijo = padre.getIzquierdo();
                        if (hijo.getIzquierdo() == null && hijo.getDerecho() == null) {
                            //caso 1, es hoja
                            eliminarCaso1(padre, hijo);
                        } else if (hijo.getIzquierdo() != null && hijo.getDerecho() == null) {
                            padre.setIzquierdo(hijo.getIzquierdo());
                        } else if (hijo.getIzquierdo() == null && hijo.getDerecho() != null) {
                            padre.setIzquierdo(hijo.getIzquierdo());
                        } else {

                            //NodoArbol candidato1 = buscarMayorCandidato(this.raiz.getIzquierdo());
                            NodoArbol candidato2 = buscarMenorCandidato((this.raiz.getDerecho()));
                            padre.setIzquierdo(candidato2);

                        }
                    } else {
                        NodoArbol hijo = padre.getDerecho();
                        if (hijo.getIzquierdo() == null && hijo.getDerecho() == null) {
                            //caso 1, es hoja
                            eliminarCaso1(padre, hijo);
                        } else if (hijo.getIzquierdo() != null && hijo.getDerecho() == null) {
                            padre.setDerecho(hijo.getIzquierdo());
                        } else if (hijo.getIzquierdo() == null && hijo.getDerecho() != null) {
                            padre.setDerecho(hijo.getIzquierdo());
                        } else {

                            //NodoArbol candidato1 = buscarMenorCandidato(this.raiz.getIzquierdo());
                            NodoArbol candidato2 = buscarMenorCandidato((this.raiz.getDerecho()));
                            padre.setDerecho(candidato2);

                        }
                    }
                }
            }
        }
        return seElimino;
    }

    public NodoArbol buscaPadre(NodoArbol aux, Comparable element) {
        NodoArbol padre = null;

        if (aux != null) {
            if (aux.getIzquierdo() != null && aux.getIzquierdo().getElemento().compareTo(element) == 0) {
                padre = aux;
            } else if (aux.getDerecho() != null && aux.getDerecho().getElemento().compareTo(element) == 0) {
                padre = aux;
            } else {
                padre = buscaPadre(aux.getIzquierdo(), element);
                if (padre == null) {
                    padre = buscaPadre(aux.getDerecho(), element);
                }
            }
        }
        return padre;
    }

    public void eliminarCaso2(NodoArbol padre, NodoArbol hijo) {

        if (padre.getIzquierdo().getElemento().compareTo(hijo.getElemento()) == 0 && hijo.getDerecho() == null) {
            padre.setIzquierdo(hijo.getIzquierdo());
        } else if (padre.getIzquierdo().getElemento().compareTo(hijo.getElemento()) == 0 && hijo.getIzquierdo() == null) {
            padre.setDerecho(hijo.getDerecho());
        }
    }

    public void eliminarCaso1(NodoArbol padre, NodoArbol hijo) {
        if (padre.getIzquierdo() != null && padre.getIzquierdo().getElemento().compareTo(hijo.getElemento()) == 0) {
            padre.setIzquierdo(null);
        } else {
            padre.setDerecho(null);
        }
    }

    public NodoArbol buscarMenorCandidato(NodoArbol aux) {
        NodoArbol menor = null;
        if (aux != null) {
            if (aux.getIzquierdo() == null) {
                menor = aux;
                this.raiz.setDerecho(aux.getDerecho());
            } else {
                menor = buscarMenorCandidatoRecursivo(aux);
            }
        }

        return menor;
    }

    public NodoArbol buscarMenorCandidatoRecursivo(NodoArbol aux) {
        NodoArbol menor = null;
        if (aux != null) {
            if (aux.getIzquierdo() != null && aux.getIzquierdo().getIzquierdo() != null) {
                menor = buscarMenorCandidato(aux.getIzquierdo());
            } else {
                menor = aux.getIzquierdo();
                aux.setIzquierdo(aux.getIzquierdo().getDerecho());
            }
        }

        return menor;
    }

    public NodoArbol buscarMayorCandidato(NodoArbol aux) {
        NodoArbol mayor = null;

        if (aux != null) {
            if (aux.getDerecho() != null && aux.getDerecho().getDerecho() != null) {
                mayor = buscarMayorCandidato(aux.getDerecho());
            } else if (aux.getDerecho() != null) {
                mayor = aux.getDerecho();
            } else {
                mayor = aux;
            }
        }
        return mayor;
    }

    public boolean pertenece(Comparable element) {
        boolean pertenece = false;

        if (!this.esVacio()) {
            pertenece = perteneceRecursivo(this.raiz, element);
        }
        return pertenece;
    }

    private boolean perteneceRecursivo(NodoArbol aux, Comparable element) {
        boolean pertenece = false;

        if (aux != null) {
            if (aux.getElemento().compareTo(element) == 0) {
                pertenece = true;
            } else {
                pertenece = perteneceRecursivo(aux.getIzquierdo(), element);
                if (!pertenece) {
                    pertenece = perteneceRecursivo(aux.getDerecho(), element);
                }
            }
        }
        return pertenece;
    }

    public Lista listar() {
        Lista ls = new Lista();

        if (!this.esVacio()) {
            listarRecursivo(this.raiz, ls);
        }
        return ls;
    }

    private void listarRecursivo(NodoArbol aux, Lista ls) {
        if (aux != null) {
            listarRecursivo(aux.getIzquierdo(), ls);
            ls.insertar(aux.getElemento(), ls.longitud() + 1);
            listarRecursivo(aux.getDerecho(), ls);
        }
    }

    public Lista listarRango(int elemMinimo, int elemMaximo) {
        Lista ls = new Lista();

        if (!this.esVacio()) {
            listarRangoRecursivo(this.raiz, elemMinimo, elemMaximo, ls);
        }
        return ls;
    }

    private void listarRangoRecursivo(NodoArbol aux, int elemMinimo, int elemMaximo, Lista ls) {

        if (aux != null) {
            if (aux.getElemento().compareTo(elemMinimo) > 0) {
                listarRangoRecursivo(aux.getIzquierdo(), elemMinimo, elemMaximo, ls);
            }
            if (aux.getElemento().compareTo(elemMaximo) <= 0 && aux.getElemento().compareTo(elemMaximo) >= 0) {
                ls.insertar(aux.getElemento(), ls.longitud() + 1);
            }
            if (aux.getElemento().compareTo(elemMaximo) < 0) {
                listarRangoRecursivo(aux.getDerecho(), elemMinimo, elemMaximo, ls);
            }
        }
    }

    /*
        public int sumarPosOrden(Comparable dato, int z) {
            NodoArbol elemento;
            int valor;
            valor = 0;
            if (this.raiz != null) {
                elemento = buscaNodo(this.raiz, dato);
                if (elemento != null) {
                    valor = SumarEnPosOrdenAux(elemento, z, 0);
                    if (valor < z) {
                        valor = -1 * valor;
                    }
                }
            }
            return valor;
        }

        private int SumarEnPosOrdenAux(NodoArbol nodo, int limite, int acumulador) {

            if (nodo != null) {
                // visita el elemento en el nodo
                System.out.println("valor nodo : " + nodo.getElemento());

                acumulador = SumarEnPosOrdenAux(nodo.getIzquierdo(), limite, acumulador);
                acumulador = SumarEnPosOrdenAux(nodo.getDerecho(), limite, acumulador);

                if (acumulador <= limite) {
                    System.out.println("Nodo que se suman :" + nodo.getElemento());
                    acumulador = acumulador + (int) nodo.getElemento();

                }
            }
            return acumulador;
        }
    */
    private NodoArbol buscaNodo(NodoArbol aux, Comparable element) {
        NodoArbol nodo = null;
        if (aux != null) {
            if (aux.getElemento().compareTo(element) == 0) {
                nodo = aux;
            } else {
                nodo = buscaNodo(aux.getIzquierdo(), element);
                if (nodo == null) {
                    nodo = buscaNodo(aux.getDerecho(), element);
                }
            }
        }
        return nodo;
    }

    public int sumarEnPostorden(Comparable dato, int z) {
        int suma = 0;
        if (this.raiz != null) {
            suma = sumarEnPostordenRecursivo(this.raiz, 0, z);
        }
        return suma;
    }

    private int sumarEnPostordenRecursivo(NodoArbol aux, int acumulador, int limiteishon) {

        if (aux != null) {
            acumulador = sumarEnPostordenRecursivo(aux.getIzquierdo(), acumulador, limiteishon);
            if (acumulador <= limiteishon) {
                acumulador = sumarEnPostordenRecursivo(aux.getDerecho(), acumulador, limiteishon);
                if (acumulador <= limiteishon) {
                    acumulador += (int) aux.getElemento();
                }
            }

        }
        return acumulador;
    }

    public int sumarEnPreorden(Comparable dato, int z) {
        int suma = 0;
        if (this.raiz != null) {
            NodoArbol nodo = buscaNodo(this.raiz, dato);

            if (nodo != null) {
                suma = sumarEnPreordenRecursivo(nodo, 0, z);
                if (suma < z) {
                    suma = -1 * suma;
                }
            }
        }
        return suma;
    }

    private int sumarEnPreordenRecursivo(NodoArbol aux, int acumulador, int z) {

        if (aux != null) {
            if (acumulador <= z) {
                acumulador += (int) aux.getElemento();
                if (acumulador < z) {
                    acumulador = sumarEnPreordenRecursivo(aux.getIzquierdo(), acumulador, z);
                    if (acumulador < z) {
                        acumulador = sumarEnPreordenRecursivo(aux.getDerecho(), acumulador, z);
                    }
                }
            }
        }
        return acumulador;
    }

    public int altura() {
        int altura = -1;

        if (!this.esVacio()) {
            altura = alturaRecursivo(this.raiz);
        }
        return altura;
    }

    private int alturaRecursivo(NodoArbol aux) {
        int altura = -1, alturaIz, alturaDer;

        if (aux != null) {
            alturaIz = alturaRecursivo(aux.getIzquierdo()) + 1;
            alturaDer = alturaRecursivo(aux.getDerecho()) + 1;

            if(alturaIz >= alturaDer) {
                altura = alturaIz;
            }else{
                altura = alturaDer;
            }
        }
        return altura;
    }

    public Lista listarPorNiveles() {
        Lista ls = new Lista();

        if(!this.esVacio()) {
            Cola cola = new Cola();
            cola.poner(this.raiz);

            while(!cola.esVacia()) {
                NodoArbol nodoActual = (NodoArbol) cola.obtenerFrente();
                cola.sacar();
                ls.insertar(nodoActual.getElemento(),ls.longitud() + 1);
                if(nodoActual.getIzquierdo() != null) {
                    cola.poner(nodoActual.getIzquierdo());
                }
                if(nodoActual.getDerecho() != null) {
                    cola.poner(nodoActual.getDerecho());
                }
            }
        }
        return ls;
    }

    @Override
    public String toString() {
        String cadena = "";

        if (!this.esVacio()) {
            cadena = toStringRecursivo(this.raiz);
        } else {
            cadena = "ARBOL VACIO";
        }
        return cadena;
    }

    private String toStringRecursivo(NodoArbol aux) {
        String cadena = "";

        if (aux != null) {
            cadena += "\nRaiz: " + aux.getElemento() + " " +
                    "HIzq: " + ((aux.getIzquierdo() != null) ? aux.getIzquierdo().getElemento() : "*") +
                    " - HDer: " + ((aux.getDerecho() != null) ? aux.getDerecho().getElemento() : "*")
            ;
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
