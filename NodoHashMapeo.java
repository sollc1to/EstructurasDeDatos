package com.mycompany.conjustistas;

public class NodoHashMapeo {

    private Object dominio;
    private Object rango;
    private NodoHashMapeo enlace;

    public NodoHashMapeo(Object dominio, Object rango, NodoHashMapeo enlace) {
        this.dominio = dominio;
        this.rango = rango;
        this.enlace = enlace;
    }

    public void setDominio(Object dominio) {
        this.dominio = dominio;
    }

    public void setRango(Object rango) {
        this.rango = rango;
    }

    public void setEnlace(NodoHashMapeo enlace) {
        this.enlace = enlace;
    }

    public Object getDominio() {
        return dominio;
    }

    public Object getRango() {
        return rango;
    }

    public NodoHashMapeo getEnlace() {
        return enlace;
    }
    
    
    
    
    

}
