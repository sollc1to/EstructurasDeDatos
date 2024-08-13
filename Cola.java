package lineales.estaticas;

public class Cola {
    private Object[] arreglo;
    private int frente;
    private int fin;
    private static final int TAMANIO = 4;

    public Cola() {
        this.arreglo = new Object[TAMANIO];
        this.frente = 0;
        this.fin = 0;

    }

    public boolean poner(Object elem) {
        boolean exito;
        if ((this.fin + 1) % this.TAMANIO != this.frente) {
            this.arreglo[this.fin] = elem;
            this.fin = (this.fin + 1) % this.TAMANIO;
            exito = true;
        } else {
            exito = false;
        }
        return exito;
    }

    public boolean sacar() {
        boolean exito = false;
        if (this.fin != this.frente) {
            this.arreglo[this.frente] = null;
            this.frente = (this.frente + 1) % this.TAMANIO;
            exito = true;
        }
        return exito;
    }

    public Object obtenerFrente() {
        Object frente = null;
        if (this.frente != this.fin) {
            frente = this.arreglo[this.frente];
        }
        return frente;
    }

    public boolean esVacio() {
        return this.frente == this.fin;
    }

    public void vaciar() {
        if (this.frente != this.fin) {
            while (this.arreglo[this.frente] != null) {
                this.arreglo[this.frente] = null;
                this.frente = (this.frente + 1) % this.TAMANIO;
            }
            this.frente= 0;
            this.fin=0;
        }

    }

    public Cola clone() {
        Cola clon = new Cola();
        clon.arreglo = this.arreglo.clone();
        clon.frente = this.frente;
        clon.fin = this.fin;
        return clon;
    }

    public String toString() {
        String datos = " cola vacia ";
        int i = this.frente;
        if (!esVacio()) {
            datos = "[ ";
            while ((i + 1) % this.TAMANIO != this.frente && this.arreglo[i] != null) {
                datos = datos + " " + this.arreglo[i];
                i = (i + 1) % this.TAMANIO;
            }
            datos = datos + " ]";
        }
        return datos;
    }

}
