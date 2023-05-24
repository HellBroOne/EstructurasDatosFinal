package estructurasLineales.auxiliares;

public class NodoClave {
    protected Object clave; //guarda el objeto clave
    protected Object info; //guarda la info del nodo
    protected NodoClave ligaDer; //guarda una direccion de memoria de otro nodo

    /**
     * Constructor por defecto. Siempre se debe pasar una clave a este nodo.
     * @param clave Clave que identifica al nodo.
     */
    public NodoClave(Object clave){
        this.clave = clave; //inicializar la clave
        this.info = null; //info nula por defecto
        this.ligaDer = null; //por defecto
    }

    /**
     * Constructor que pide clave e info. <br/>
     * Se va a pasar una clave a este nodo junto con<br/>
     * el objeto de informacion a guardar.
     * @param clave Clave que identifica al nodo.
     * @param info Info que contiene este nodo.
     */
    public NodoClave(Object clave, Object info){
        this.clave = clave; //inicializar la clave
        this.info = info; //informacion
        ligaDer = null; //por defecto
    }

    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }

    public NodoClave getLigaDer() {
        return ligaDer;
    }

    public void setLigaDer(NodoClave ligaDer) {
        this.ligaDer = ligaDer;
    }

    public Object getClave() {
        return clave;
    }

    public void setClave(Object clave) {
        this.clave = clave;
    }

    public String toString(){
        return clave.toString() +": "+info;
    }
}
