package estructurasLineales;

import entradasalida.SalidaPorDefecto;

public class ColaEstatica implements Lote{
    protected int MAXIMO;
    protected int inicio;
    protected int fin;
    protected Object informacion[];

    public ColaEstatica(int tamanio){
        MAXIMO = tamanio;
        inicio = -1;
        fin = -1;
        informacion = new Object[MAXIMO];
    }

    @Override
    public boolean lleno(){
        if((inicio == 0 && fin == (MAXIMO-1)) || (fin == inicio-1)){
            return true;
        }else{
            return false;
        }
    }
    @Override
    public boolean vacio(){
        if (inicio == -1){
            return true;
        }else{
            return false;
        }
    }
    @Override
    public boolean poner(Object info){
        if (lleno() == false){
            if (vacio() == true){ //d)
                inicio = 0;
                fin = 0;
            } else if( fin == (MAXIMO-1)){ //
                fin = 0;
            } else {
                fin++;
            }
            informacion[fin] = info;
            return true;
        }else{
            return false;
        }

    }
    @Override
    public Object quitar(){
        if(vacio() == false){ //mientras no sea vacio no se puede quitar
            Object respaldo = informacion[inicio];
            if(inicio == fin){ //e)
                inicio = -1;
                fin = -1;
            } else if (inicio == (MAXIMO-1)) { //d)
                inicio = 0;
            } else { //b) y c)
                inicio++;
            }
            return respaldo;
        }else{
            return null;
        }
    };
    @Override
    public void imprimir(){
        if (vacio() == false){
            if (inicio <= fin){
                for (int indice = inicio; indice <= fin; indice++){
                    SalidaPorDefecto.terminal(informacion[indice]+" ");
                }
            }else{
                for (int indice = inicio; indice <= (MAXIMO-1); indice++){
                    SalidaPorDefecto.terminal(informacion[indice]+" ");
                }
                for (int indice = 0; indice <= fin; indice++){
                    SalidaPorDefecto.terminal(informacion[indice]+" ");
                }
            }
        }
    }
    @Override
    public Object verTope(){
        if (vacio() == false){
            return informacion[fin];
        } else {
            return null;
        }
    }

    public Object verInicio(){
        if (vacio() == false){
            return informacion[inicio];
        } else {
            return null;
        }
    }
}
