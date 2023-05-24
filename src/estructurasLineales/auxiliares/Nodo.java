package estructurasLineales.auxiliares;

public class Nodo {
    protected Object info; //guarda la info del nodo
    protected Nodo ligaDer; //guarda una direccion de memoria de otro nodo

    public Nodo(){
        this.info = null;
        ligaDer = null; //por defecto
    }

    public Nodo(Object info){ //psandole una info al nodo
        this.info = info;
        ligaDer = null;
    }

    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }

    public Nodo getLigaDer() {
        return ligaDer;
    }

    public void setLigaDer(Nodo ligaDer) {
        this.ligaDer = ligaDer;
    }

    @Override
    public String toString() { //obtiene la string de la info
        return info.toString();
    }
}
