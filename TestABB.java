package com.mycompany.conjustistas;

public class TestABB {

    public static void main(String[] args) {
        ArbolABB arbol = new ArbolABB();

        arbol.insertar((Comparable) 12);
        arbol.insertar((Comparable) 10);
        arbol.insertar((Comparable) 11);
        arbol.insertar((Comparable) 7);
        arbol.insertar((Comparable) 9);
        arbol.insertar((Comparable) 8);
        arbol.insertar((Comparable) 4);
        arbol.insertar((Comparable) 6);
        arbol.insertar((Comparable) 20);
        arbol.insertar((Comparable) 15);
        arbol.insertar((Comparable) 13);
        arbol.insertar((Comparable) 11);
        arbol.insertar((Comparable) 27);
        arbol.insertar((Comparable) 30);

        System.out.println(arbol.toString());

        arbol.eliminar(10);

        System.out.println(arbol.toString());
        
        arbol.eliminar(7);
        
        System.out.println(arbol.toString());
        
        arbol.eliminar(9);
        
        
        System.out.println(arbol.toString());
        
        
        arbol.eliminar(20);
        
        
        System.out.println(arbol.toString());
        
        
        arbol.eliminar(6);
        
        System.out.println(arbol.toString());
        
        
        arbol.eliminar(12);
        
        System.out.println(arbol.toString());
        
        arbol.eliminar(15);
        
        System.out.println(arbol.toString());
        
        
        arbol.eliminar(27);
        
        
        System.out.println(arbol.toString());
        
        
        arbol.eliminar(11);
        
        
        
        System.out.println(arbol.toString());
        
        arbol.eliminar(8);
        
        System.out.println(arbol.toString());
        
        arbol.eliminar(30);
        
        
        System.out.println(arbol.toString());
        
        
        arbol.eliminar(4);
        
        
        System.out.println(arbol.toString());
        
        
        arbol.eliminar(13);
        
        
        System.out.println(arbol.toString());
        
        
       

    }

}
