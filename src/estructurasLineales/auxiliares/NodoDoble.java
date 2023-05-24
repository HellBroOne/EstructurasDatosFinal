package estructurasLineales.auxiliares;

public class NodoDoble {
    protected NodoDoble ligaIzq;
    protected Object info;
    protected NodoDoble ligaDer;

    public NodoDoble (Object info){
        this.info = info;
        ligaIzq = null;
        ligaDer = null;
    }

    public NodoDoble getLigaIzq() {
        return ligaIzq;
    }

    public void setLigaIzq(NodoDoble ligaIzq) {
        this.ligaIzq = ligaIzq;
    }

    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }

    public NodoDoble getLigaDer() {
        return ligaDer;
    }

    public void setLigaDer(NodoDoble ligaDer) {
        this.ligaDer = ligaDer;
    }

    public String toString(){
        return info.toString();
    }
}
