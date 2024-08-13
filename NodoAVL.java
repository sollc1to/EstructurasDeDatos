package com.mycompany.conjustistas;

public class NodoAVL implements Comparable {

    private Object elem;

    private int altura;

    private NodoAVL izquierdo;

    private NodoAVL derecho;

    public NodoAVL(Object elem, NodoAVL izquierdo, NodoAVL derecho) {

        this.altura = 0;

        this.elem = elem;

        this.izquierdo = izquierdo;

        this.derecho = derecho;

    }

    public Object getElem() {

        return this.elem;
    }

    public void setElem(Object elem) {

        this.elem = elem;
    }

    public int getAltura() {

        return this.altura;
    }

    public NodoAVL getIzquierdo() {

        return this.izquierdo;

    }

    public NodoAVL getDerecho() {

        return this.derecho;
    }

    public void setIzquierdo(NodoAVL izquierdo) {
        this.izquierdo = izquierdo;
    }

    public void setDerecho(NodoAVL derecho) {

        this.derecho = derecho;
    }

    public void recalcularAltura() {
        int alturaHD = (this.derecho ==null) ? -1: this.derecho.getAltura();
        int alturaHI = (this.izquierdo == null)? -1 : this.izquierdo.getAltura();
        
        this.altura = Math.max(alturaHI,alturaHD)+1;
    
        
        

    }

    public int calcularBalance() {
        int alturaHI = (this.izquierdo == null) ? -1: this.izquierdo.getAltura();
        int alturaHD = (this.derecho == null)? -1: this.derecho.getAltura();

        return (alturaHI - alturaHD);

    }

  
    public int compareTo(Object elem) { //Modificar el compareTo

        return (((Comparable) this.elem).compareTo((Comparable) elem));

    }

}
