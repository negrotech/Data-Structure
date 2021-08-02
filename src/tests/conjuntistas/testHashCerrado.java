package tests.conjuntistas;

import conjuntistas.hash.cerrado.TablaHash;

public class testHashCerrado {
    public static void main(String[] args) {
        TablaHash    hash = new TablaHash();

        hash.insertar(23);
        hash.insertar(13);
        hash.insertar(15);

        System.out.println(hash.listar());
    }

}
