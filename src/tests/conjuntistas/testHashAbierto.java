package tests.conjuntistas;

import conjuntistas.hash.abierto.TablaHash;

public class testHashAbierto {
    public static void main(String[] args) {
        TablaHash tabla = new TablaHash();

        tabla.insertar(3);
        tabla.insertar(5);
        tabla.insertar(13);
        tabla.insertar(6);
        tabla.insertar(25);
        tabla.insertar(35);


        System.out.println(tabla.listar());
        tabla.eliminar(13);
        tabla.eliminar(25);

        System.out.println(tabla.listar());
        System.out.println(tabla.pertenece(5) ? " PERTENECE" : "NO PERTENECE" );
    }
}
