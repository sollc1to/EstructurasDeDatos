 
package com.mycompany.conjustistas;

/**
 *
 * @author PC
 */
public class NodoABB  {
    
    //Peguntar bien al profe el tema de comparar y implements comparable.
    
    private Comparable elem;
    private NodoABB izquierdo;
    private NodoABB derecho;
    
    public NodoABB(Comparable elem, NodoABB izq, NodoABB der){
        this.elem = elem;
        
        this.izquierdo = izq;
        this.derecho = der;
    }
    public NodoABB(Comparable elem){
        this.elem = elem;
        this.izquierdo = null;
        this.derecho = null;
    }

    public Comparable getElem() {
        return elem;
    }

    public void setElem(Comparable elem) {
        this.elem = elem;
    }

    public NodoABB getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoABB izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoABB getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoABB derecho) {
        this.derecho = derecho;
    }
    
    public int compareTo(Comparable elem){
        
        
        return(this.elem.compareTo(elem));
        
        
        
    }
    
}
