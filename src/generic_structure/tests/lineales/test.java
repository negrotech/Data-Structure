/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generic_structure.tests.lineales;

import generic_structure.lineales.dinamico.Pila;

/**
 *
 * @author Martin
 */
public class test {
    public static void main(String[] args) {
        Pila p1 = new Pila();
        p1.apilar(1);
        p1.apilar(4);
        p1.apilar(5);
        p1.apilar(3);
        p1.apilar(7);
        p1.apilar(2);
        p1.apilar(6);
        p1.apilar(9);
        p1.apilar(10);
        
        //System.out.println(p1);
        Pila p2 = p1.clone();
        p2.desapilar();
        System.out.println(p1);
        System.out.println(p2);

    }
}
