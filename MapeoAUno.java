/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.conjustistas;

/**
 *
 * @author PC
 */
public class MapeoAUno {

    private int TAMANIO = 17;

    private NodoHashMapeo[] tabla;
    private int cantidad = 0;

    public MapeoAUno() {

        tabla = new NodoHashMapeo[TAMANIO];

    }

    public boolean asociar(Object dominio, Object rango) {

        int pos = Math.abs(dominio.hashCode() % this.TAMANIO);

        this.tabla[pos] = new NodoHashMapeo(dominio, rango, this.tabla[pos]);
        this.cantidad++;
        calcularPromedio();
        return true;

    }

    private void calcularPromedio() { //Este método me servirá para calcular el promedio de elementos que hay por celda. En caso de haber mayor a 3, 
        //Debo agrandar la tabla.
        int promedio = (this.cantidad / this.TAMANIO);

        if (promedio >= 3) {
            expandirTabla();

        }

    }

    private void expandirTabla() {
        int tamanioNuevo = this.TAMANIO + (this.TAMANIO / 2); //Le agrego 1/2 del tamaño.

        NodoHashMapeo[] tablaNueva = new NodoHashMapeo[tamanioNuevo]; //Creo la nueva tabla.
        NodoHashMapeo aux;
        int pos;

        for (int i = 0; i < TAMANIO; i++) {
            aux = tabla[i];

            while (aux != null) { //Reacomodo cada nodo en la celda. Simplemente calculo
                //Su nueva posición y lo guardo.

                pos = Math.abs((aux.getDominio().hashCode() % tamanioNuevo));

                tablaNueva[pos] = new NodoHashMapeo(aux.getDominio(), aux.getRango(), tablaNueva[pos]);

                aux = aux.getEnlace();

            }

        }

        this.tabla = tablaNueva;
        this.TAMANIO = tamanioNuevo;

    }

    public boolean desasociar(Object dominio) {
        int pos = Math.abs(dominio.hashCode() % this.TAMANIO);
        boolean eliminado = false;
        NodoHashMapeo aux = tabla[pos];

        if (aux.getDominio().equals(dominio)) {
            this.tabla[pos] = aux.getEnlace();
            eliminado = true;

        } else {

            while (aux.getEnlace() != null && !eliminado) {

                if (aux.getEnlace().getDominio().equals(dominio)) {
                    aux.setEnlace(aux.getEnlace().getEnlace());
                    eliminado = true;

                } else {

                    aux = aux.getEnlace();

                }

            }
        }
        return eliminado;

    }

    public Object obtenerValor(Object dominio) {
        Object elem = null;
        int pos = Math.abs(dominio.hashCode() & this.TAMANIO);
        NodoHashMapeo aux = tabla[pos];

        while (aux != null && !aux.getDominio().equals(dominio)) {

            aux = aux.getEnlace();

        }

        if (aux != null) {
            elem = aux.getRango();

        }
        return elem;

    }

    public Lista obtenerConjuntoDominio() {

        Lista lista = new Lista();
        NodoHashMapeo aux;

        for (int i = 0; i < TAMANIO; i++) {
            aux = tabla[i];

            while (aux != null) {
                lista.insertar(aux.getDominio(), lista.longitud() + 1);

                aux = aux.getEnlace();

            }

        }
        return lista;

    }

    public Lista obtenerConjuntoRango() {

        Lista lista = new Lista();
        NodoHashMapeo aux;

        for (int i = 0; i < TAMANIO; i++) {
            aux = tabla[i];

            while (aux != null) {
                lista.insertar(aux.getRango(), lista.longitud() + 1);

                aux = aux.getEnlace();

            }

        }
        return lista;

    }
     public String toString() {
        String cadena = "";
        NodoHashMapeo aux;
        if (this.cantidad > 0) {
            for (int i = 0; i < TAMANIO; i++) {
                aux = tabla[i];

                cadena = cadena + " Posicion: " + i + "  \t[" + ((aux != null) ? "Dominio:" + aux.getDominio().toString() +" Rango: " + aux.getRango().toString() : "null") + "]";

                while (aux != null && aux.getEnlace() != null) {

                    cadena = cadena + "  -> [ Dominio: " + aux.getEnlace().getDominio().toString() + " Rango: " + aux.getEnlace().getRango().toString() + "]";

                    aux = aux.getEnlace();
                }

                cadena = cadena + "\n";

            }

        }

        return cadena;

    }

}
