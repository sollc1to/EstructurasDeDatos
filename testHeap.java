/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.conjustistas;

/**
 *
 * @author PC
 */
public class testHeap {

    public static void main(String[] args) {
        HeapMin arbolH = new HeapMin();

        arbolH.insertar(1);
        arbolH.insertar(3);
        arbolH.insertar(5);
        arbolH.insertar(8);
        arbolH.insertar(0);
        System.out.println(arbolH.toString());

        arbolH.eliminarCima();

        System.out.println(arbolH.toString());

        arbolH.insertar(-1);
        arbolH.insertar(10);
        arbolH.insertar(80);
                System.out.println(arbolH.toString());
                
                
                

    }

}
