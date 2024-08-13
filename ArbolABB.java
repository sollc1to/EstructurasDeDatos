package com.mycompany.conjustistas;

public class ArbolABB implements Comparable {

    private NodoABB raiz;

    public boolean insertar(Comparable elem) {
        boolean exito = true;
        if (this.raiz == null) {
            raiz = new NodoABB(elem);

        } else {
            exito = insertarAux(this.raiz, elem);

        }
        return exito;

    }

    public Lista listarMayorIgual(Object elem) {
        Lista lis = new Lista();

        if (this.raiz != null) {
            listarMayorIgualAux(this.raiz, lis, elem);

        }
        return lis;
    }

    private void listarMayorIgualAux(NodoABB nodo, Lista lis, Object elem) {
        int comparar;
        if (nodo != null) {
            comparar = nodo.getElem().compareTo(elem);

            if (comparar > 0) {

                if (nodo.getIzquierdo() != null) {
                    listarMayorIgualAux(nodo.getIzquierdo(), lis, elem);
                }

            }

            if (comparar >= 0) {
                lis.insertar(nodo.getElem(), 1);

            }

            if (nodo.getDerecho() != null) {
                listarMayorIgualAux(nodo.getDerecho(), lis, elem);
            }

        }

    }

    public boolean eliminarMinimo() {
        boolean eliminado = false;
        NodoABB padreMin;

        if (this.raiz != null) {

            if (this.raiz.getIzquierdo() == null) {
                this.raiz = this.raiz.getDerecho();

            } else {
                padreMin = buscarPadreMin(this.raiz);
                eliminado = eliminarMinimo(padreMin);

            }

        }
        return eliminado;
    }

    private boolean eliminarMinimo(NodoABB padreMin) {
        NodoABB minimo = padreMin.getIzquierdo();

        if (minimo.getDerecho() != null) {

            padreMin.setIzquierdo(minimo.getDerecho());

        } else {

            padreMin.setIzquierdo(null);

        }
        return true;

    }

    public ArbolABB clonarParteInvertia(Object elem) {
        NodoABB nodoRef, nuevoNodo;
        ArbolABB arbol = new ArbolABB();

        if (this.raiz != null) {

            if (!this.raiz.equals(elem)) {
                nodoRef = buscarElem(this.raiz, elem);
                if (nodoRef != null) {
                    nuevoNodo = new NodoABB((Comparable) elem);
                    arbol.raiz = nuevoNodo;

                    clonarArbolInvertido(nodoRef, nuevoNodo);

                }

            } else {
                nuevoNodo = new NodoABB(this.raiz.getElem(), null, null);
                arbol.raiz = nuevoNodo;

                clonarArbolInvertido(this.raiz, nuevoNodo);

            }

        }
        return arbol;

    }

    private void clonarArbolInvertido(NodoABB nodoRef, NodoABB nuevoNodo) {
        NodoABB nuevoNodoAux;

        if (nodoRef != null) {
            if (nodoRef.getIzquierdo() != null) {
                nuevoNodoAux = new NodoABB((Comparable) nodoRef.getIzquierdo().getElem());
                nuevoNodo.setDerecho(nuevoNodoAux);
                clonarArbolInvertido(nodoRef.getIzquierdo(), nuevoNodo.getDerecho());

            }
            if (nodoRef.getDerecho() != null) {
                nuevoNodoAux = new NodoABB((Comparable) nodoRef.getDerecho().getElem());
                nuevoNodo.setIzquierdo(nuevoNodoAux);
                clonarArbolInvertido(nodoRef.getDerecho(), nuevoNodo.getIzquierdo());

            }

        }

    }

    private NodoABB buscarElem(NodoABB nodo, Object elem) {
        NodoABB nodoABB = null;

        if (nodo != null) {

            if (nodo.getElem().equals(elem)) {

                nodoABB = nodo;

            } else {

                nodoABB = buscarElem(nodo.getIzquierdo(), elem);

                if (nodoABB == null) {
                    nodoABB = buscarElem(nodo.getDerecho(), elem);

                }

            }

        }
        return nodoABB;

    }

    private NodoABB buscarPadreMin(NodoABB nodo) {
        NodoABB padreMin = null;

        if (nodo != null) {
            if (nodo.getIzquierdo() != null) {
                if (nodo.getIzquierdo().getIzquierdo() == null) {
                    padreMin = nodo;

                } else {

                    padreMin = buscarPadreMin(nodo.getIzquierdo());
                }

            }

        }
        return padreMin;

    }

    public boolean eliminar(Comparable elem) {
        int comparar;
        boolean eliminado = false;
        NodoABB nodoPadre;

        if (this.raiz != null) {
            if (this.raiz.getElem().equals(elem)) {
                eliminado = determinarCasoYEliminarRaiz(this.raiz);

            } else {

                nodoPadre = buscarPadre(this.raiz, elem);

                if (nodoPadre != null) {
                    eliminado = determinarCasoyEliminar(nodoPadre, elem);

                }

            }
        }
        return eliminado;

    }

    private NodoABB buscarPadre(NodoABB nodo, Comparable elem) {
        int comparableI = -1, comparableD = -1, comparable = -1;
        boolean encontrado = false;
        NodoABB nodoPadre = null;

        if (nodo != null) {
            if (nodo.getIzquierdo() != null) { //Comparo el hijo izuierdo y el derecho con los hijos del nodo actual.
                comparableI = nodo.getIzquierdo().getElem().compareTo(elem);

            }
            if (nodo.getDerecho() != null) {

                comparableD = nodo.getDerecho().getElem().compareTo(elem);

            }

            if (comparableD == 0 || comparableI == 0) { //Verificio si alguno de los hijos es igual a elem
                nodoPadre = nodo;

            } else { //Si no, sigo buscando.
                comparable = nodo.getElem().compareTo(elem);

                if (comparable < 0) {
                    nodoPadre = buscarPadre(nodo.getDerecho(), elem);

                } else {

                    nodoPadre = buscarPadre(nodo.getIzquierdo(), elem);

                }

            }

        }
        return nodoPadre;

    }

    private boolean determinarCasoYEliminarRaiz(NodoABB raiz) {
        if (raiz.getIzquierdo() == null && raiz.getDerecho() == null) { //La raiz es hoja. Caso Nulo

            this.raiz = null;

        } else {
            if (raiz.getDerecho() != null && raiz.getIzquierdo() != null) { //Caso 3.
                eliminarCasoTresRaiz(raiz);

            } else {

                if (raiz.getDerecho() == null && raiz.getIzquierdo() != null) { //Caso 2 HI.

                    eliminarCasoDos(raiz, null, 'r', 'i');

                } else { //Caso 2 HD.
                    eliminarCasoDos(raiz, null, 'r', 'd');

                }
            }

        }
        return true;

    }

    private boolean determinarCasoyEliminar(NodoABB nodoPadre, Comparable elem) {
        int comparableHI = -1, comparableHD = -1;
        NodoABB nodoHijo;
        char lugarHijo;

        if (nodoPadre.getIzquierdo() != null) {
            comparableHI = nodoPadre.getIzquierdo().getElem().compareTo(elem);

        }
        if (nodoPadre.getDerecho() != null) {
            comparableHD = nodoPadre.getDerecho().getElem().compareTo(elem);

        }
        if (comparableHI == 0) { // Veo cuál de sus dos hijos es elem
            nodoHijo = nodoPadre.getIzquierdo();
            lugarHijo = 'i';

        } else {
            nodoHijo = nodoPadre.getDerecho();
            lugarHijo = 'd';

        }
        //Veo que tipo de caso es el que tengo que eliminar
        if (nodoHijo.getIzquierdo() != null && nodoHijo.getDerecho() != null) { //Caso 3, el hijo tiene hijo izquierdo e hijo derecho.
            eliminarCasoTres(nodoPadre, nodoHijo, lugarHijo);

        } else if (nodoHijo.getIzquierdo() != null && nodoHijo.getDerecho() == null) { //Caso 2, el hijo tiene solo hijo izquierdo.
            eliminarCasoDos(nodoPadre, nodoHijo, lugarHijo, 'i');

        } else if (nodoHijo.getDerecho() != null && nodoHijo.getIzquierdo() == null) { //Caso 2, el hijo tiene solo hijo derecho.

            eliminarCasoDos(nodoPadre, nodoHijo, lugarHijo, 'd');

        } else { //Caso 1. Debo determinar si es el izquierdo o derecho.
            if (comparableHI == 0) {
                eliminarCasoUno(nodoPadre, 'i');

            } else {
                eliminarCasoUno(nodoPadre, 'd');

            }

        }
        return true;

    }

    private void eliminarCasoUno(NodoABB nodoPadre, char lugar) {

        if (lugar == 'i') {
            nodoPadre.setIzquierdo(null);

        } else {
            nodoPadre.setDerecho(null);

        }

    }

    private void eliminarCasoDos(NodoABB nodoPadre, NodoABB nodoHijo, char lugar, char hijo) {

        if (lugar == 'r') {//Caso de la raíz

            if (hijo == 'i') {

                this.raiz = nodoPadre.getIzquierdo();

            } else {
                this.raiz = nodoPadre.getDerecho();

            }

        } else if (lugar == 'i') {

            if (hijo == 'i') {
                nodoPadre.setIzquierdo(nodoHijo.getIzquierdo());

            } else {
                nodoPadre.setIzquierdo(nodoHijo.getDerecho());

            }

        } else {

            if (hijo == 'i') {
                nodoPadre.setDerecho(nodoHijo.getIzquierdo());

            } else {

                nodoPadre.setDerecho(nodoHijo.getDerecho());
            }

        }

    }

    private void eliminarCasoTres(NodoABB nodoPadre, NodoABB nodoHijo, char lugarHijo) {
        //Para este caso, debo buscar un cadidato A.
        //Candidato A. Mayor elemento del sub arbol zquierdo de nodoHijo.
        NodoABB candidato;
        NodoABB nodoAux, nodoAuxI;
        //Sabemos que no tiene enlace derecho por ser candidato.getDerecho() = null
        candidato = candidatoA(nodoHijo);

        if (candidato.getElem() != nodoHijo.getIzquierdo().getElem()) {

            if (candidato.getIzquierdo() != null) {

                candidato.getIzquierdo().setIzquierdo(nodoHijo.getIzquierdo());

            } else {

                candidato.setIzquierdo(nodoHijo.getIzquierdo());
            }

            candidato.setDerecho(nodoHijo.getDerecho());

        } else {

            if (nodoHijo.getDerecho() != null) {

                candidato.setDerecho(nodoHijo.getDerecho());

            }

        }

        if (lugarHijo == 'i') {

            nodoPadre.setIzquierdo(candidato);

        } else {

            nodoPadre.setDerecho(candidato);

        }

    }

    private void eliminarCasoTresRaiz(NodoABB nodo) {
        NodoABB nodoAuxIzquierdo;

        NodoABB nodoAux = candidatoA(nodo);

        if (nodoAux.getElem() != nodo.getIzquierdo().getElem()) { //Caso de que el nodoAux no sea el hijo izquierdo de la raiz.

            if (nodoAux.getIzquierdo() != null) { //En caso de que el candidato tenga hijo izquierdo, debo enlazar este
                //Con el hijo izquierdo de la raiz.
                nodoAuxIzquierdo = nodoAux.getIzquierdo();

                nodoAuxIzquierdo.setIzquierdo(nodo.getIzquierdo());

            } else { //Si no, simplemente enlazo el hijo izquierdo.

                nodoAux.setIzquierdo(nodo.getIzquierdo());

            }

            //Tambien enlazo el candidato con el hijo derecho de la raiz.
            nodoAux.setDerecho(nodo.getDerecho());

            this.raiz = nodoAux;

        } else { //En caso de que el candidato A sea el mismo que el hijo izquierdo de la raíz.

            nodoAux.setDerecho(nodo.getDerecho());

            this.raiz = nodoAux;

        }

    }

    private NodoABB candidatoA(NodoABB nodoHijo) {
        NodoABB candidatoA, nodoAnterior = null;
        candidatoA = nodoHijo.getIzquierdo();

        while (candidatoA.getDerecho() != null) {
            nodoAnterior = candidatoA;
            candidatoA = candidatoA.getDerecho();

        }
        if (nodoAnterior != null) { //En caso de que el candidato no sea el primer
            nodoAnterior.setDerecho(null);//Hijo izquierdo del nodo, este debe ser desanlazado.

        }

        return candidatoA;

    }

    private NodoABB candidatoB(NodoABB nodoHijo) {
        //Menor elemento del subarbol derecho de N.
        NodoABB menorNodo;
        menorNodo = nodoHijo.getDerecho();

        while (menorNodo.getIzquierdo() != null) {

            menorNodo = menorNodo.getIzquierdo();
        }
        return menorNodo;

    } //Candidato B es lo mismo pero opuesto(?

    private NodoABB buscarElem(NodoABB nodo, Comparable elem) {
        int comparar;
        NodoABB nodoAux = null;

        if (nodo != null) {
            comparar = nodo.compareTo(elem);

            if (comparar == 0) {

                nodoAux = nodo;

            } else if (comparar < 0) {

                nodoAux = buscarElem(nodo.getDerecho(), elem);

            } else {

                nodoAux = buscarElem(nodo.getIzquierdo(), elem);
            }

        }
        return nodoAux;

    }

    private boolean insertarAux(NodoABB n, Comparable elem) {
        boolean exito = true;
        int comparar;
        if (n != null) {
            comparar = n.getElem().compareTo(elem);
            if (comparar == 0) {
                exito = false;

            } else if (comparar < 0) {
                if (n.getDerecho() != null) {

                    exito = insertarAux(n.getDerecho(), elem);

                } else {

                    n.setDerecho(new NodoABB(elem));

                }

            } else {
                if (n.getIzquierdo() != null) {
                    exito = insertarAux(n.getIzquierdo(), elem);

                } else {
                    n.setIzquierdo(new NodoABB(elem));

                }

            }

        }

        return exito;

    }

    public boolean pertenece(Comparable elem) {
        boolean encontrado = false;

        if (this.raiz != null) {
            encontrado = encontrarElem(this.raiz, elem);

        }
        return encontrado;

    }

    private boolean encontrarElem(NodoABB nodo, Comparable elem) {
        boolean exito = false;
        int comparable;

        if (nodo != null) {

            if (nodo.getElem().equals(elem)) {
                exito = true;

            } else {

                comparable = nodo.getElem().compareTo(elem);

                if (comparable < 0) {
                    exito = encontrarElem(nodo.getDerecho(), elem);

                } else {
                    exito = encontrarElem(nodo.getIzquierdo(), elem);

                }

            }

        }
        return exito;

    }

    public Lista listar() {
        Lista lista = new Lista();

        if (this.raiz != null) {
            listarArbol(this.raiz, lista);

        }

        return lista;

    }

    private void listarArbol(NodoABB nodo, Lista lista) {

        if (nodo != null) {

            listarArbol(nodo.getIzquierdo(), lista);

            lista.insertar(nodo.getElem(), lista.longitud() + 1);

            listarArbol(nodo.getDerecho(), lista);

        }

    }

    public Object maximoElem() {
        Object maximo = null;
        if (this.raiz != null) {
            maximo = maximoElemAux(this.raiz);

        }
        return maximo;

    }

    private Object maximoElemAux(NodoABB nodo) {
        Object maximo = null;

        if (nodo != null) {

            if (nodo.getDerecho() != null) {

                maximo = maximoElemAux(nodo.getDerecho());

            } else {
                maximo = nodo.getElem();
            }

        }
        return maximo;

    }

    public Object minimoElem() {
        Object minimo = null;

        if (this.raiz != null) {

            minimo = buscarMinimo(this.raiz);

        }
        return minimo;

    }

    public boolean vacio() {

        return (this.raiz == null);
    }

    public void vaciar() {

        this.raiz = null;

    }

    private Object buscarMinimo(NodoABB nodo) {
        Object minimoElem = null;

        if (nodo != null) {

            if (nodo.getIzquierdo() != null) {

                minimoElem = buscarMinimo(nodo.getIzquierdo());

            } else {

                minimoElem = nodo.getElem();
            }

        }

        return minimoElem;

    }

    public String toString() {
        String cadena = "";
        if (this.raiz != null) {
            cadena = generarToString(this.raiz);

        }
        return cadena;

    }

    private String generarToString(NodoABB nodo) {

        String cadena = "";

        if (nodo != null) {
            cadena = nodo.getElem() + ", ";

            cadena = cadena + generarToString(nodo.getIzquierdo());

            cadena = cadena + generarToString(nodo.getDerecho());

        }

        return cadena;

    }

    public boolean eliminarElemAnterior(Comparable elem) {

        boolean eliminado = false;
        NodoABB nodoElem = null;

        if (this.raiz != null) {
            //Busco el elemento al cual eliminar el menor
            nodoElem = buscarElemAux(this.raiz, elem);
            if (nodoElem != null) {

                eliminado = eliminarMenorElem(nodoElem);

            }

        }
        return eliminado;
    }

    private boolean eliminarMenorElem(NodoABB nodo) {
        NodoABB nodoAux, nodoAnterior = null;
        boolean eliminado = false;

        nodoAux = nodo.getIzquierdo();

        if (nodoAux != null) {
            eliminado = true;

            if (nodoAux.getDerecho() == null) {
                nodo.setIzquierdo(nodoAux.getIzquierdo());

            } else {

                while (nodoAux.getDerecho() != null) {
                    nodoAnterior = nodoAux;
                    nodoAux = nodoAux.getDerecho();

                }

                nodoAnterior.setDerecho(nodoAux.getIzquierdo());

            }

        }
        return eliminado;

    }

    private NodoABB buscarElemAux(NodoABB nodo, Comparable elem) {
        int comparacion;
        NodoABB nodoElem = null;
        if (nodo != null) {

            comparacion = nodo.getElem().compareTo(elem);

            if (comparacion == 0) {
                nodoElem = nodo;

            } else {

                if (comparacion < 0) {
                    nodoElem = buscarElemAux(nodo.getIzquierdo(), elem);

                } else {

                    nodoElem = buscarElemAux(nodo.getDerecho(), elem);
                }

            }

        }
        return nodoElem;
    }

    @Override
    public int compareTo(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
