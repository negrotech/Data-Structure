/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests.lineales;

import lineales.estatico.Cola;

/**
 *
 * @author Martin
 */
public class testCola {

    public static void main(String[] args) {
        Cola cola1 = new Cola();

        cola1.poner((1));
        cola1.poner((5));
        cola1.poner((3));
        cola1.poner((7));
        cola1.poner((9));
        System.out.println(cola1);
        cola1.sacar();
        cola1.sacar();
        cola1.sacar();
        cola1.sacar();
        cola1.sacar();
        System.out.println(cola1);

        cola1.poner((9));
        cola1.poner((9));
        cola1.poner((2));
        cola1.poner((7));
        cola1.poner((9));

        System.out.println(cola1);
        
        Cola cola2 = cola1.clone();
        cola2.sacar();
        cola2.sacar();
        cola2.sacar();
        cola2.sacar();
        cola2.sacar();
        cola2.sacar();
        System.out.println(cola2);
    }
}
