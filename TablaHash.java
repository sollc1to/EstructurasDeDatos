package com.mycompany.conjustistas;

public class TablaHash {

    int TAMANIO = 5; // Lo hago más pequeño a propósito, para después agrandarlo y reacomodarlos.
    private NodoHash[] tabla;
    private int cantidad = 0; //Cantidad de enlaces en las celdas.

    public TablaHash() {
        tabla = new NodoHash[TAMANIO];

    }

    public boolean insertar(Object elem) {

        int pos = Math.abs((elem.hashCode() % this.TAMANIO)); //Agregué Math.abs ya que me daba posiciones negativas
        this.tabla[pos] = new NodoHash(elem, this.tabla[pos]);
        this.cantidad++;

        calcularPromedio();

        return true;

    }

    public boolean pertenece(Object elem) {
        int pos = Math.abs(elem.hashCode() % this.TAMANIO);
        boolean encontrado = false;
        NodoHash aux = tabla[pos];

        while (aux != null && !encontrado) {

            if (aux.getElem().equals(elem)) {
                encontrado = true;

            }
            aux = aux.getEnlace();

        }
        return encontrado;

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

        NodoHash[] tablaNueva = new NodoHash[tamanioNuevo]; //Creo la nueva tabla.
        NodoHash aux;
        int pos;

        for (int i = 0; i < TAMANIO; i++) {
            aux = tabla[i];

            while (aux != null) { //Reacomodo cada nodo en la celda. Como se que no están repetidos, simoplemente calculo
                //Su nueva posicioón y lo guardo.

                pos = Math.abs((aux.getElem().hashCode() % tamanioNuevo));

                tablaNueva[pos] = new NodoHash(aux.getElem(), tablaNueva[pos]);

                aux = aux.getEnlace();

            }

        }

        this.tabla = tablaNueva;
        this.TAMANIO = tamanioNuevo;

    }

    public boolean eliminar(Object elem) {

        int pos = Math.abs(elem.hashCode() % this.TAMANIO);
        boolean eliminado = false;
        NodoHash aux = tabla[pos];

        if (aux.getElem().equals(elem)) {
            this.tabla[pos] = aux.getEnlace();
            eliminado = true;

        } else {

            while (aux.getEnlace() != null && !eliminado) {

                if (aux.getEnlace().getElem().equals(elem)) {
                    aux.setEnlace(aux.getEnlace().getEnlace());
                    eliminado = true;

                }else{
                    
                    aux = aux.getEnlace();
                    
                }

            }
        }
        return eliminado;

    }

    public String toString() {
        String cadena = "";
        NodoHash aux;

        for (int i = 0; i < TAMANIO; i++) {
            aux = tabla[i];

            cadena = cadena + " Posicion: " + i + "  \t[" + ((aux != null) ? aux.toString() : "null") + "]";

            while (aux != null && aux.getEnlace() != null) {

                cadena = cadena + "  -> [ " + aux.getEnlace().toString() + " ]";

                aux = aux.getEnlace();
            }

            cadena = cadena + "\n";

        }
        return cadena;

    }

}
