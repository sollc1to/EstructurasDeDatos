package com.mycompany.conjustistas;

public class TestAVL {

    public static void main(String[] args) {
        //Preguntar tema del compare to

        ArbolAVL arbol = new ArbolAVL();
      
        arbol.insertar(75);
        arbol.insertar(20);
        arbol.insertar(15);
        arbol.insertar(80);
        arbol.insertar(93);
        arbol.insertar(77);
        arbol.insertar(18);
        
        System.out.println(arbol.toString());

    }
}
