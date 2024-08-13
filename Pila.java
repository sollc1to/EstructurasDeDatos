package lineales.estaticas;

public class Pila {
    private  Object [] arreglo ;
    private int tope ;
    private static final int TAMANIO = 6 ;
    
    // constructor
    public Pila (){
        this.arreglo = new Object[TAMANIO];
        this.tope = -1 ;
    }
    // operaciones 
    // apilar
    public boolean apilar (Object elObject){
        boolean exito ;
        if (this.TAMANIO<= this.tope+1){
            exito = false ;
        }
        else {
            this.tope++ ;
            this.arreglo[tope]= elObject ;
            exito = true;
        }
        return exito;
    }
    // desapilar
    public boolean desapilar (){
        boolean exito = false ;
        if (0<=this.tope){
            arreglo[tope]= null;
            tope-- ;
            exito= true ;
        }
        return exito;
    }
    // obtener el objeto que esta en el tope del arreglo
    public Object obtenerTope (){
        Object obtener = null ;
        if (0<=this.tope){
            obtener = arreglo[tope];
        }
        return obtener ;
    }
    // verifica si el arrglo esta vacio
    public boolean esVacio (){
        return this.tope==-1 ;
    }
    // vacia el arreglo
    public void vaciar (){
        if (0<= this.tope){
            for (int i = tope ; 0<= i ; i--){
                arreglo[i]= null;
            }
            tope = -1;
        }
    }
    // clona el arreglo 
    public Pila clone (){
        Pila pilaNueva = new Pila();
        pilaNueva.arreglo = this.arreglo.clone();
        pilaNueva.tope = this.tope ;
        return pilaNueva;
    }
    public String toString (){
        String datos= "" ;
        if (0<= tope){
            for (int i = tope ; 0<= i; i--){
                datos = arreglo[i]+ "  " +datos ;
            }
        }
        return datos  ;
    }
    
}
