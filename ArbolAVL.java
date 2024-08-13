package com.mycompany.conjustistas;

public class ArbolAVL {

    private NodoAVL raiz;

    public void ArbolAVL() {
        this.raiz = null;
    }

    //MODIFICAR EL COMPARE TO (Preguntar)
    public boolean pertenece(Object elem) {

        boolean pertenece = false;

        if (this.raiz != null) {
            pertenece = buscarElem(this.raiz, elem);

        }

        return pertenece;

    }

    private boolean buscarElem(NodoAVL nodo, Object elem) {
        boolean exito = false;
        int comparable;

        if (nodo != null) {

            if (nodo.getElem().equals(elem)) {
                exito = true;

            } else {

                comparable = nodo.compareTo(elem);

                if (comparable < 0) {
                    exito = buscarElem(nodo.getDerecho(), elem);

                } else {
                    exito = buscarElem(nodo.getIzquierdo(), elem);

                }

            }

        }
        return exito;

    }

    public boolean insertar(Object elem) {
        boolean insertado = false;
        int comparar;
        NodoAVL aux;
        if (this.raiz != null) {
            insertado = insertarAux(elem, this.raiz);
            verificarBalance(raiz, null, 'r'); //Por último, verifico el balance de la raíz.

        } else {
            this.raiz = new NodoAVL(elem, null, null);
            insertado = true;

        }
        return insertado;

    }

    //El insertar debe ser recursivo, ya que al insertar, debo verificar el balance del padre, el padre del padre, etc.. hasta la raíz.
    private boolean insertarAux(Object elem, NodoAVL n) {
        boolean exito = false;
        int comparar;
        NodoAVL aux;
        if (n != null) {
            comparar = n.compareTo(elem);
            if (comparar < 0) { //Si el nodo es menor al elemento, entonces se inserta en lado derecho.
                if (n.getDerecho() != null) {
                    exito = insertarAux(elem, n.getDerecho());

                    if (exito == true) { //Si exito es true, esto quiere decir que debo verificar el balanceo.

                        verificarBalance(n.getDerecho(), n, 'd');

                    }

                } else {
                    exito = true;
                    n.setDerecho(new NodoAVL(elem, null, null));
                    n.getDerecho().recalcularAltura();
                }

            } else if (comparar > 0) {

                {
                    if (n.getIzquierdo() != null) { //Si el nodo es mayor al elemento, se inserta en el lado izquierdo.
                        exito = insertarAux(elem, n.getIzquierdo());
                        if (exito == true) { //Si exito es true, esto quiere decir que debo verificar el balanceo.
                            verificarBalance(n.getIzquierdo(), n, 'i');

                        }

                    } else {
                        n.setIzquierdo(new NodoAVL(elem, null, null));
                        exito = true;
                        n.getIzquierdo().recalcularAltura();

                    }

                }

            }

        }

        return exito;

    }

    private void verificarBalance(NodoAVL nodo, NodoAVL nodoPadre, char lugar) {
        int balance, balanceHijo;
        NodoAVL aux = null;

        nodo.recalcularAltura();

        balance = nodo.calcularBalance();

        if (balance == 2) { //Si el balance es dos, significa que está desbalanceado hacia la izquierda.

            balanceHijo = nodo.getIzquierdo().calcularBalance(); //Verifico el balance del hijo izquierdo.

            if (balanceHijo == -1) { //En este caso se hace una rotación doble izq-der.
                System.out.println("Rotacion doble izquierda-derecha.");

                aux = rotacionSimpleIzquierda(nodo.getIzquierdo());

                nodo.setIzquierdo(aux);

                aux = rotacionSimpleDerecha(nodo);

            } else { //Se hace una rotación simple a derecha.
                System.out.println("Rotacion simple a derecha");
                aux = rotacionSimpleDerecha(nodo);

            }

        } else if (balance == -2) {
            balanceHijo = nodo.getDerecho().calcularBalance();

            if (balanceHijo == 1) { //Rotación doble der-izq.
                System.out.println("Rotacion doble derecha-izquierda.");

                aux = rotacionSimpleDerecha(nodo.getDerecho());

                nodo.setDerecho(aux);

                aux = rotacionSimpleIzquierda(nodo);

            } else { //Rotación simple a derecha.
                System.out.println("Rotacion simple izquierda.");
                aux = rotacionSimpleIzquierda(nodo);

            }

        }
        //Si aux != null, significa que se modificaron los enlaces.
        if (aux != null) {

            if (lugar == 'i') {
                nodoPadre.setIzquierdo(aux);

            } else if (lugar == 'd') {
                nodoPadre.setDerecho(aux);
            } else {

                this.raiz = aux;
            }

        }

    }

    private NodoAVL rotacionSimpleDerecha(NodoAVL nodo) { //Paso el padre como parámetro para ya modificar.

        NodoAVL aux = nodo.getIzquierdo(); //El hijo izquierdo, será ahora el padre de nodo.
        NodoAVL temp = aux.getDerecho();
        aux.setDerecho(nodo);
        nodo.setIzquierdo(temp);

        //Una vez hecho el cambio, recalculo la altura de ambos.
        nodo.recalcularAltura();
        aux.recalcularAltura();

        return aux;

    }

    private NodoAVL rotacionSimpleIzquierda(NodoAVL nodo) {
        NodoAVL aux = nodo.getDerecho(); //El hijo derecho, será ahora el padre del nodo.
        NodoAVL temp = aux.getIzquierdo();
        aux.setIzquierdo(nodo);
        nodo.setDerecho(temp);

        //Recalculo la altura de los dos nodos.
        nodo.recalcularAltura();
        aux.recalcularAltura();

        return aux;

    }

    public boolean eliminar(Comparable elem) {
        int comparar;
        boolean eliminado = false;
        NodoAVL nodoPadre, aux;

        if (this.raiz != null) {
            if (this.raiz.getElem().equals(elem)) {
                eliminado = determinarCasoYEliminarRaiz(this.raiz);

            } else {
                eliminado = buscarPadre(this.raiz, elem);

                if (eliminado) {
                    verificarBalance(this.raiz, null, 'r');

                }

            }
        }
        return eliminado;

    }

    private boolean buscarPadre(NodoAVL nodo, Comparable elem) {
        int comparableI, comparableD, comparable;
        boolean eliminado = false;
        NodoAVL nodoPadre = null, aux;

        if (nodo != null) { //Comparo sus dos hijos para ver si es igual a "elem".
            comparableD = (nodo.getDerecho() != null) ? (nodo.getDerecho().compareTo(elem)) : -1;
            comparableI = ((nodo.getIzquierdo() != null) ? (nodo.getIzquierdo().compareTo(elem)) : -1);

            if (comparableD == 0 || comparableI == 0) { //Verificio si alguno de los hijos es igual a elem. 
                //Una vez que alguno sea igual, lo elimino y empiezo a calcular el balance.
                determinarCasoyEliminar(nodo, elem);

                nodo.recalcularAltura(); //Recalculo la altura de "nodo", el cuál sería el nodo padre del eliminado.

                eliminado = true;

            } else { //Si no, sigo buscando a el elemento.
                comparable = nodo.compareTo(elem);

                if (comparable < 0) {
                    eliminado = buscarPadre(nodo.getDerecho(), elem);
                    if (eliminado) { //En el caso de que eliminado sea true, debo ver el balance
                        verificarBalance(nodo.getDerecho(), nodo, 'd');

                    }

                } else {

                    eliminado = buscarPadre(nodo.getIzquierdo(), elem);
                    if (eliminado) {
                        verificarBalance(nodo.getIzquierdo(), nodo, 'i');
                    }

                }

            }

        }
        return eliminado;

    }

    private boolean determinarCasoYEliminarRaiz(NodoAVL raiz) {

        char lugar;
        if (raiz.getIzquierdo() != null && raiz.getDerecho() != null) { //Caso 3, la raíz tiene hijo izquierdo y derecho.
            eliminarCasoTresRaiz(raiz);
        } else {
            if (raiz.getDerecho() != null || raiz.getIzquierdo() != null) { //Caso 2, la raíz tiene solo un hijo.
                eliminarCasoDos(raiz, null, 'r', (raiz.getDerecho() != null) ? 'd' : 'i');

            } else { //Caso 1, la raíz no tiene hijos.
                this.raiz = null;

            }

        }
        return true;

    }

    private void determinarCasoyEliminar(NodoAVL nodoPadre, Comparable elem) {
        NodoAVL nodoHijo;
        char lugarHijo;
        //Busco el hijo el cual eliminar.
        nodoHijo = ((nodoPadre.getIzquierdo() != null && nodoPadre.getIzquierdo().compareTo(elem) == 0) ? nodoPadre.getIzquierdo() : nodoPadre.getDerecho());

        //Si al comparar no es el hijo izquierdo, entonces es el hijo derecho.
        lugarHijo = ((nodoPadre.getIzquierdo() != null && nodoPadre.getIzquierdo().getElem().equals(elem)) ? 'i' : 'd');
        //Veo que tipo de caso es el que tengo que eliminar
        if (nodoHijo.getIzquierdo() != null && nodoHijo.getDerecho() != null) { //Caso 3, el hijo tiene hijo izquierdo e hijo derecho.
            eliminarCasoTres(nodoPadre, nodoHijo, lugarHijo);

        } else if (nodoHijo.getIzquierdo() != null || nodoHijo.getDerecho() != null) { //Caso dos, existe solo un hijo izquierdo o derecho.
            eliminarCasoDos(nodoPadre, nodoHijo, lugarHijo, (nodoHijo.getIzquierdo() != null ? 'i' : 'd'));

        } else { //Caso 1. Debo determinar si es el izquierdo o derecho.
            eliminarCasoUno(nodoPadre, lugarHijo);

        }
    }

    private void eliminarCasoUno(NodoAVL nodoPadre, char lugar) {

        if (lugar == 'i') {
            nodoPadre.setIzquierdo(null);
        } else {
            nodoPadre.setDerecho(null);
        }

    }

    private void eliminarCasoDos(NodoAVL nodoPadre, NodoAVL nodoHijo, char lugar, char lugarHijo) {

        if (lugar == 'r') {//Caso de la raíz

            this.raiz = (lugarHijo == 'i' ? nodoPadre.getIzquierdo() : nodoPadre.getDerecho());

        } else if (lugar == 'i') { //Si el lugar es 'i', seteo en el lado izquierdo el hijo en lugar hijo.
            //Lo mismo para el lado derecho.

            nodoPadre.setIzquierdo((lugarHijo == 'i') ? nodoHijo.getIzquierdo() : nodoHijo.getDerecho());

        } else {
            nodoPadre.setDerecho((lugarHijo == 'i') ? nodoHijo.getIzquierdo() : nodoHijo.getDerecho());

        }

    }

    private void eliminarCasoTres(NodoAVL nodoPadre, NodoAVL nodoHijo, char lugarHijo) {
        //Para este caso, debo buscar un candidato A.
        //Candidato A. Mayor elemento del sub arbol zquierdo de nodoHijo.
        NodoAVL candidato;
        NodoAVL nodoAux, nodoAuxI;
        //Sabemos que no tiene enlace derecho por ser candidato.getDerecho() = null (El último nodo de los enlaces hacia la derecha)
        candidato = candidatoA(nodoHijo.getIzquierdo());

        if (candidato.getElem() != nodoHijo.getIzquierdo().getElem()) { // Si el candidato es distinto del hijo izquierdo directo del nodo...

            if (candidato.getIzquierdo() != null) { //En caso de que tenga hijo izquierdo, debo setearlo como padre del nodoHijo.getIzquierdo()

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

        nodoPadre.recalcularAltura();

    }

    private void eliminarCasoTresRaiz(NodoAVL nodo) {
        NodoAVL nodoAuxIzquierdo;

        NodoAVL nodoAux = candidatoA(nodo.getIzquierdo());

        nodo.getIzquierdo().recalcularAltura();
        if (nodoAux.compareTo(nodo.getElem()) != 0) { //Caso de que el nodoAux no sea el hijo izquierdo de la raiz.

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

    public NodoAVL candidatoA(NodoAVL nodoHijo) {
        NodoAVL candidatoA = null, nodoAux;

        if (nodoHijo != null && (nodoHijo.getDerecho() != null && nodoHijo.getDerecho().getDerecho() != null)) {
            candidatoA = candidatoA(nodoHijo.getDerecho());
            if (candidatoA != null) { //Verifico el balance una vez encontrado el nodoHijo.
                verificarBalance(nodoHijo.getDerecho(), nodoHijo, 'd');
            }

        } else {
            if (nodoHijo.getDerecho() != null) {
                candidatoA = nodoHijo.getDerecho(); //Necesito el padre para poder desenlazar el candidato.
                nodoHijo.setDerecho(null);
                nodoHijo.recalcularAltura();

            } else {

                candidatoA = nodoHijo; //Caso en el que no hay hijos derechos.
            }

        }
        return candidatoA;

    }

    public String toString() {
        String cadena = "";
        if (this.raiz != null) {
            cadena = generarToString(this.raiz);

        } else {

            cadena = " Arbol vacío. ";
        }
        return cadena;

    }

    private String generarToString(NodoAVL nodo) {

        String cadena = "";

        if (nodo != null) {
            cadena = " P: " + nodo.getElem() + " HI:" + (nodo.getIzquierdo() != null ? nodo.getIzquierdo().getElem() : " null ") + " HD: "
                    + (nodo.getDerecho() != null ? nodo.getDerecho().getElem() : " null. ") + "\n";

            cadena = cadena + generarToString(nodo.getIzquierdo());

            cadena = cadena + generarToString(nodo.getDerecho());

        }

        return cadena;

    }
    
    public Lista listar() {
        Lista lista = new Lista();

        if (this.raiz != null) {
            listarArbol(this.raiz, lista);

        }

        return lista;

    }

    private void listarArbol(NodoAVL nodo, Lista lista) {

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

    private Object maximoElemAux(NodoAVL nodo) {
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

    private Object buscarMinimo(NodoAVL nodo) {
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

    public boolean vacio() {

        return (this.raiz == null);
    }

    public void vaciar() {

        this.raiz = null;

    }
}
