package generic_structure.conjuntistas;

import generic_structure.lineales.dinamico.Lista;
import generic_structure.lineales.dinamico.Pila;

public class ArbolBB {

    private NodoArbol raiz;

    public ArbolBB() {
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

    private boolean insertarRecursivo(NodoArbol aux, Comparable element) {
        boolean seInserto = false;

        if (aux != null) {
            if (aux.getElemento().compareTo(element) == 0) {
                seInserto = false;
            } else {
                if (aux.getElemento().compareTo(element) > 0) {
                    if (aux.getIzquierdo() != null) {
                        seInserto = insertarRecursivo(aux.getIzquierdo(), element);
                    } else {
                        aux.setIzquierdo(new NodoArbol(element, null, null));
                        seInserto = true;
                    }
                } else {
                    if (aux.getDerecho() != null) {
                        seInserto = insertarRecursivo(aux.getDerecho(), element);
                    } else {
                        aux.setDerecho(new NodoArbol(element, null, null));
                        seInserto = true;
                    }
                }
            }
        }
        return seInserto;
    }

    public boolean eliminar(Comparable element) {
        boolean seElimino = false;

        if (!this.esVacio()) {
            NodoArbol buscado;
            buscado = eliminarRecursivo(this.raiz, element);
            if (buscado != null) {
                seElimino = true;
            }
        }
        return seElimino;
    }


    private NodoArbol eliminarRecursivo(NodoArbol aux, Comparable element) {
        NodoArbol buscado = null;
        if (aux != null) {
            if (aux.getIzquierdo() != null &&
                    aux.getIzquierdo().getElemento().compareTo(element) == 0) {
                buscado = aux.getIzquierdo();
                //Es hoja
                if (buscado.getIzquierdo() == null && buscado.getDerecho() == null) {
                    aux.setIzquierdo(null);
                }
                //Tiene un hijo
                else if (buscado.getIzquierdo() != null && buscado.getDerecho() == null) {
                    aux.setIzquierdo(buscado.getIzquierdo());

                }//Tiene dos hijos
                else {
                    NodoArbol cand1 = buscarMenorCandidato(buscado.getIzquierdo(), element);
                    NodoArbol cand2 = buscarMayorCandidato(buscado.getDerecho(), element);

                    if (cand1.getElemento().compareTo(cand2.getElemento()) > 0) {
                        aux.setIzquierdo(cand1);
                    } else {
                        aux.setIzquierdo(cand1);
                    }

                }

            } else if (aux.getDerecho() != null &&
                    aux.getDerecho().getElemento().compareTo(element) == 0) {

                buscado = aux.getDerecho();
                //Es hoja
                if (buscado.getIzquierdo() == null && buscado.getDerecho() == null) {
                    aux.setDerecho(null);
                }
                //Tiene un hijo
                else if (buscado.getIzquierdo() != null && buscado.getDerecho() == null) {
                    aux.setDerecho(buscado.getIzquierdo());

                }//Tiene dos hijos
                else {
                    NodoArbol cand1 = buscarMayorCandidato(buscado.getIzquierdo(), element);
                    NodoArbol cand2 = buscarMenorCandidato(buscado.getDerecho(), element);

                    if (cand1.getElemento().compareTo(cand2.getElemento()) > 0) {
                        aux.setDerecho(cand1);
                    } else {
                        aux.setDerecho(cand2);
                    }

                }
            } else {
                buscado = buscarNodo(aux.getIzquierdo(), element);
                if (buscado == null) {
                    buscado = buscarNodo(aux.getDerecho(), element);
                }
            }

        }
        return buscado;
    }

    private NodoArbol buscarMayorCandidato(NodoArbol aux, Comparable element) {
        NodoArbol candidato = null;

        if (aux != null) {
            if (aux.getDerecho() != null && aux.getDerecho().getElemento().compareTo(element) > 0) {
                if (aux.getDerecho().getDerecho() != null) {
                    candidato = buscarMayorCandidato(aux, element);
                } else {
                    candidato = aux;
                }
            } else {
                candidato = aux;
            }

        }
        return candidato;
    }

    private NodoArbol buscarMenorCandidato(NodoArbol aux, Comparable element) {
        NodoArbol candidato = null;

        if (aux != null) {
            if (aux.getIzquierdo() != null && aux.getIzquierdo().getElemento().compareTo(element) < 0) {
                if (aux.getIzquierdo().getIzquierdo() != null) {
                    candidato = buscarMenorCandidato(aux, element);
                } else {
                    candidato = aux;
                }
            } else {
                candidato = aux;
            }

        }
        return candidato;
    }

    public Lista listar() {
        Lista listaArbol = new Lista();

        if (!this.esVacio()) {
            listarRecursivo(this.raiz, listaArbol);
        }
        return listaArbol;
    }

    public void listarRecursivo(NodoArbol aux, Lista lista) {

        if (aux != null) {
            listarRecursivo(aux.getIzquierdo(), lista);
            lista.insertar(aux.getElemento(), lista.longitud() + 1);
            listarRecursivo(aux.getDerecho(), lista);
        }

    }

    private NodoArbol buscarNodo(NodoArbol aux, Comparable element) {
        NodoArbol buscado = null;
        if (aux != null) {
            if (aux.getElemento().compareTo(element) == 0) {
                buscado = aux;
            } else {
                buscado = buscarNodo(aux.getIzquierdo(), element);
                if (buscado == null) {
                    buscado = buscarNodo(aux.getDerecho(), element);
                }
            }

        }
        return buscado;
    }

    public Lista listarMenorIgual(Comparable element) {
        Lista mayoresA = new Lista();

        if (!this.esVacio()) {
            listarMenorIgualRecursivo(this.raiz, element, mayoresA);
        }
        return mayoresA;
    }

    private void listarMenorIgualRecursivo(NodoArbol aux, Comparable element, Lista mayoresA) {
        if (aux != null) {
            if (aux.getElemento().compareTo(element) <= 0) {
                listarMenorIgualRecursivo(aux.getIzquierdo(), element, mayoresA);
            }
            if (aux.getElemento().compareTo(element) <= 0) {
                mayoresA.insertar(aux.getElemento(), mayoresA.longitud() + 1);
            }
            listarMenorIgualRecursivo(aux.getDerecho(), element, mayoresA);
        }
    }

    public Lista listarMayorIgual(Comparable element) {
        Lista mayoresA = new Lista();

        if (!this.esVacio()) {
            listarMayorIgualRecursivo(this.raiz, element, mayoresA);
        }
        return mayoresA;
    }

    private void listarMayorIgualRecursivo(NodoArbol aux, Comparable element, Lista mayoresA) {
        if (aux != null) {
            if (aux.getElemento().compareTo(element) >= 0) {
                listarMayorIgualRecursivo(aux.getIzquierdo(), element, mayoresA);
            }
            if (aux.getElemento().compareTo(element) >= 0) {
                mayoresA.insertar(aux.getElemento(), 1);
            }
            listarMayorIgualRecursivo(aux.getDerecho(), element, mayoresA);
        }
    }

    public Lista listarRango(Comparable minimo, Comparable maximo) {
        Lista rango = new Lista();

        if (!this.esVacio()) {
            listarRangoRecursivo(this.raiz, minimo, maximo, rango);
        }
        return rango;
    }

    private void listarRangoRecursivo(NodoArbol aux, Comparable minimo, Comparable maximo, Lista ls) {
        if (aux != null) {
            if (aux.getElemento().compareTo(minimo) > 0) {
                listarRangoRecursivo(aux.getIzquierdo(), minimo, maximo, ls);
            }
            if (aux.getElemento().compareTo(minimo) >= 0 && aux.getElemento().compareTo(maximo) <= 0) {
                ls.insertar(aux.getElemento(), ls.longitud() + 1);
            }
            if (aux.getElemento().compareTo(maximo) < 0) {
                listarRangoRecursivo(aux.getDerecho(), minimo, maximo, ls);
            }
        }
    }

    public String concatenarPreordenDesde(char element, int cantCaracteres) {
        String cadena = "###";

        if (this.raiz != null) {
            NodoArbol buscado = buscandoNodo(this.raiz, element);
            if (buscado != null) {

                cadena = concatenarPreordenDesdeRecursivo(buscado, "", cantCaracteres);
                System.out.println("CADENA " + cadena);
                if (cadena.length() < cantCaracteres) {
                    if (cadena.length() == 0) {
                        cadena = "###";
                    } else {
                        cadena = "#" + cadena;
                    }
                }
            }
        }
        return cadena;
    }

    private String concatenarPreordenDesdeRecursivo(NodoArbol aux, String cadena, int cantCaracteres) {
        System.out.println("CADENA: " + cadena);
        if (aux != null) {
            System.out.println("NODO: " + aux.getElemento());
//cadena 0 => 1
            /*
            if (cadena.length() == cantCaracteres) {
                //Caso con 0 y 1;
                cadena = "" + aux.getElemento();
                System.out.println("LLEGO ACÁ");
            } else {
*/
            if (cadena.length() < cantCaracteres) {
                cadena += "" + aux.getElemento();
                if (cadena.length() < cantCaracteres) {
                    cadena = concatenarPreordenDesdeRecursivo(aux.getIzquierdo(), cadena, cantCaracteres);

                    if (cadena.length() < cantCaracteres) {
                        cadena = concatenarPreordenDesdeRecursivo(aux.getDerecho(), cadena, cantCaracteres);
                    }
                }
            }

            //}
            System.out.println("CADENA SALIDA: " + cadena + " NODO: " + aux.getElemento());
        }
        return cadena;
    }


    public NodoArbol buscandoNodo(NodoArbol aux, char elementoBuscado) {
        NodoArbol buscado = null;
        if (aux != null) {
            if ((char) aux.getElemento() == elementoBuscado) {
                buscado = aux;
                System.out.println(aux.getElemento());
            } else {
                buscado = buscandoNodo(aux.getIzquierdo(), elementoBuscado);
                if (buscado == null) {
                    buscado = buscandoNodo(aux.getDerecho(), elementoBuscado);
                }
            }
        }
        return buscado;
    }

    public boolean verificaCamino (Pila p) {
        boolean verificaCamino = false;

        if(this.raiz != null && !p.esVacia()){

            NodoArbol nodoBuscado = buscarNodoP(this.raiz,p.obtenerTope());
            p.desapilar();

            if(nodoBuscado != null) {
                if(p.esVacia()){
                    verificaCamino = true;
                }else{
                    verificaCamino = verificaCaminoRecursivo(nodoBuscado,p);
                }
            }

        } else if(this.raiz == null && p.esVacia()){
            verificaCamino = true;
        }

        return verificaCamino;
    }

    public boolean verificaCaminoRecursivo(NodoArbol aux, Pila p) {
        boolean verificaCamino = false;

        if(aux!= null) {
            if(aux.getIzquierdo() != null && aux.getIzquierdo().getElemento().compareTo(p.obtenerTope())== 0){
                p.desapilar();
                if(!p.esVacia()) {
                    verificaCamino = verificaCaminoRecursivo(aux.getIzquierdo(),p);
                }else{
                    verificaCamino = true;
                }
            }else if(aux.getDerecho() != null && aux.getDerecho().getElemento().compareTo(p.obtenerTope())==0){
                p.desapilar();
                if(!p.esVacia()) {
                    verificaCamino = verificaCaminoRecursivo(aux.getDerecho(),p);
                }else{
                    verificaCamino = true;
                }
            }
        }
        return verificaCamino;
    }

    public NodoArbol buscarNodoP(NodoArbol aux, Object element) {
        NodoArbol nodoBuscado = null;

        if(aux!= null) {
            if(aux.getElemento().compareTo(element) == 0) {
                nodoBuscado = aux;
            }else{
                if(aux.getElemento().compareTo(element) > 0) {

                    nodoBuscado = buscarNodoP(aux.getIzquierdo(),element);
                }else{

                    nodoBuscado = buscarNodoP(aux.getDerecho(),element);
                }
            }
        }
        return nodoBuscado;
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

    private String toStringRecursivo(NodoArbol aux) {
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
