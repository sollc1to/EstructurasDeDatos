/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.conjustistas;

/**
 *
 * @author PC
 */
public class Nodo {
 
    private Object elem ;
    private Nodo enlace ;

    //constructor
    public Nodo (Object elem, Nodo enlace){
        this.elem = elem;
        this.enlace = enlace;
    }

    //modificadoras 
    public void setElem (Object elem){
        this.elem = elem;
    }
    public void setEnlace(Nodo enlace){
        this.enlace= enlace;
    }

    // obvservadores
    public Object getElem (){
        return this.elem;
    }
    public Nodo getEnlace (){
        return this.enlace;
    }
}
    


