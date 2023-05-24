package estructurasnolineales;

import entradasalida.SalidaPorDefecto;
import estructurasLineales.ListaEstatica;

public class Matriz3 {
    protected int renglones;
    protected int columnas;
    protected int profundidad;
    protected Object informacion[][][];

    public Matriz3(int renglones, int columnas, int profundidad) {
        this.renglones = renglones;
        this.columnas = columnas;
        this.profundidad = profundidad;
        informacion = new Object[renglones][columnas][profundidad];
    }

    public Matriz3(int renglones, int columnas, int profundidad, Object info) {
        this.renglones = renglones;
        this.columnas = columnas;
        this.profundidad = profundidad;
        informacion = new Object[renglones][columnas][profundidad];
        rellenar(info); //rellena por defecto
    }

    public void rellenar(Object info){
        //recorrer todos los renglones
        for(int cadaRenglon = 0; cadaRenglon < renglones; cadaRenglon++){
            //recorrer todas las columnas
            for (int cadaColumna = 0; cadaColumna < columnas; cadaColumna++){
                //recorrer toda la profundidad
                for (int cadaProfundidad = 0; cadaProfundidad < profundidad; cadaProfundidad++){
                    informacion[cadaRenglon][cadaColumna][cadaProfundidad] = info;
                }
            }
        }
    }

    public int getRenglones() {
        return renglones;
    }

    public void setRenglones(int renglones) {
        this.renglones = renglones;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public int getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(int profundidad) {
        this.profundidad = profundidad;
    }

    public boolean cambiar(int renglon, int columna, int prof, Object info){
        if ((validarRango(renglon, renglones) == true) && (validarRango(columna, columnas) == true) && (validarRango(prof, profundidad) == true)){
            informacion[renglon][columna][prof] = info;
            return true;
        } else {
            return false;
        }
    }

    public Object obtener(int renglon, int columna, int prof){
        if ((validarRango(renglon, renglones) == true) && (validarRango(columna, columnas) == true) && (validarRango(prof, profundidad) == true)){
            return informacion[renglon][columna][prof];
        } else {
            return null;
        }
    }

    private boolean validarRango(int indice, int limite){
        if ((indice >= 0)&&(indice <= limite)){
            return true;
        }else{
            return false;
        }
    }

    public void imprimirPorColumnas(){
        //lo trataremos como rebanadas donde cada rebanada es una columna
        for (int cadaRebanada = 0; cadaRebanada<columnas; cadaRebanada++){
            //aqui comienza cada rebanada
            for (int cadaRenglon = 0; cadaRenglon<renglones; cadaRenglon++){
                for (int cadaColumna = 0; cadaColumna<profundidad; cadaColumna++){
                    SalidaPorDefecto.terminal(informacion[cadaRenglon][cadaRebanada][cadaColumna]+" ");
                }
                //cuando se acaban todas las columnas
                SalidaPorDefecto.terminal("\n");
            }
            //despues de todos los renglones de una rebanada
            SalidaPorDefecto.terminal("\n");
        }
    }

    public ListaEstatica aMatrices2(){
        ListaEstatica matrices2 = new ListaEstatica(columnas);
        for (int cadaRebanada=0;cadaRebanada<columnas;cadaRebanada++){ //recorre la matriz cada columna de la 3D
            Matriz2 matriz2Temporal = new Matriz2(renglones,profundidad);
            for (int renglon=0;renglon<renglones;renglon++){ //
                for (int profCol=0;profCol<profundidad;profCol++){
                    //llenar la matriz2
                    matriz2Temporal.cambiar(renglon, profCol, informacion[renglon][cadaRebanada][profCol]);
                }
            }
            matrices2.agregar(matriz2Temporal);
        }
        return matrices2;
    }

}
