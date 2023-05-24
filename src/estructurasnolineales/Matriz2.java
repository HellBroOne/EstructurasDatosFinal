package estructurasnolineales;

import entradasalida.SalidaPorDefecto;
import estructurasLineales.ListaEstatica;
import utilerias.comunes.TipoColumna;
import utilerias.comunes.TipoRenglon;

/**
 * Clase correspondiente a un TDA Matriz.
 * @author Gerardo Rivas Delgado
 * @version 1.0
 */
public class Matriz2 {
    protected int renglones;
    protected int columnas;
    protected Object informacion[][];

    public Matriz2(int renglones, int columnas) {
        this.renglones = renglones;
        this.columnas = columnas;
        informacion = new Object[renglones][columnas];
    }

    public Matriz2(int renglones, int columnas, Object info) {
        this.renglones = renglones;
        this.columnas = columnas;
        informacion = new Object[renglones][columnas];
        rellenar(info); //rellena por defecto
    }

    public void rellenar(Object info){
        //recorrer todos los renglones
        for(int cadaRenglon = 0; cadaRenglon < renglones; cadaRenglon++){
            //recorrer todas las columnas
            for (int cadaColumna = 0; cadaColumna < columnas; cadaColumna++){
                informacion[cadaRenglon][cadaColumna] = info;
            }
        }
    }

    public int obtenerRenglones() {
        return renglones;
    }

    public void setRenglones(int renglones) {
        this.renglones = renglones;
    }

    public int obtenerColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }


    /**
     * Metodo que cambia un elemento de dicha posicion por el objeto info
     * @param renglon Valor del renglon del elemento a cambiar.
     * @param columna Valor de la columna del elemento a cambiar.
     * @param info Valor a cambiar.
     * @return Se regresa falso si no se cambio, verdadero si si.
     */
    public boolean cambiar(int renglon, int columna, Object info){
        if ((validarRango(renglon, renglones) == true) && (validarRango(columna, columnas) == true)){
            informacion[renglon][columna] = info;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Se obtiene un elemento de una cierta posicion (renglon y columna)
     * @param renglon Renglon del objeto que se quiere obtener
     * @param columna Columna del objeto a obtener.
     * @return Se regresa el objeto de las posiciones anteriores.
     */
    public Object obtener(int renglon, int columna){
        if ((validarRango(renglon, renglones) == true) && (validarRango(columna, columnas) == true)){
            return informacion[renglon][columna];
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

    /**
     * Metodo que imprime la matriz por columnas, es decir columna1, columna2, etc..
     */
    public void imprimirPorColumnas(){
        //lo trataremos como rebanadas donde cada rebanada es una columna
        for (int cadaRenglon = 0; cadaRenglon<columnas; cadaRenglon++){
            //aqui comienza cada rebanada
            for (int cadaColumna = 0; cadaColumna<renglones; cadaColumna++){
                SalidaPorDefecto.terminal(informacion[cadaColumna][cadaRenglon]+" ");
            }
            SalidaPorDefecto.terminal("\n");
        }
    }

    /**
     * Metodo que imprime la matriz por renglones, es decir columna1, columna2, etc..
     */
    public void imprimirPorRenglones(){
        //recorremos cada renglon
        for (int cadaRenglon = 0; cadaRenglon<renglones; cadaRenglon++){
            //recorremos cada columna
            for (int cadaColumna = 0; cadaColumna<columnas; cadaColumna++){
                SalidaPorDefecto.terminal(informacion[cadaRenglon][cadaColumna]+" ");
            }
            SalidaPorDefecto.terminal("\n");
        }
    }

    /**
     * Obtenga la transpuesta de una matriz (actualmente solo funciona para matrices cuadradas)
     */
    public void transpuesta(){
        Matriz2 matrizTemporal = new Matriz2(columnas, renglones, 0.0);
        for (int cadaRenglon = 0; cadaRenglon<renglones;cadaRenglon++){
            for (int cadaColumna = 0; cadaColumna<columnas; cadaColumna++){
                matrizTemporal.cambiar(cadaColumna, cadaRenglon, informacion[cadaRenglon][cadaColumna]);
            }
        }
        redefinir(matrizTemporal);
    }

    /**
     * Metodo que clona la matriz actual a otra matriz.
     * @return Matriz resultante nueva.
     */
    public Matriz2 clonar(){
        Matriz2 matrizNueva = new Matriz2(renglones, columnas);
        for (int cadaRenglon = 0; cadaRenglon<renglones; cadaRenglon++){
            for (int cadaColumna = 0; cadaColumna<renglones; cadaColumna++){
                matrizNueva.cambiar(cadaRenglon, cadaColumna, obtener(cadaRenglon, cadaColumna));
            }
        }
        SalidaPorDefecto.terminal("Matriz Clonada.\n");
        return matrizNueva;
    }

    /**
     * Metodo que redefine una matriz segun el tamanio de la matriz de los parametros
     * @param matriz2 Argumento Matriz2 que obtiene una matriz nueva para redefinir.
     * @return Se regresa True si funciono la redimension.
     */
    public boolean redefinir(Matriz2 matriz2){
        informacion = new Object[matriz2.obtenerRenglones()][matriz2.obtenerColumnas()];
        renglones = matriz2.obtenerRenglones();
        columnas = matriz2.obtenerColumnas();

        for (int cadaRenglon = 0; cadaRenglon < renglones; cadaRenglon++){
            for (int cadaColumna = 0; cadaColumna < columnas; cadaColumna++){
                informacion[cadaRenglon][cadaColumna] = matriz2.obtener(cadaRenglon, cadaColumna);
            }
        }
        return true;
    }

    /**
     * Metodo que verifica que ambas matrices sean iguales.
     * @param matriz2 Matriz nueva a comparar
     * @return True si son iguales, false si no.
     */
    public boolean esIgual(Matriz2 matriz2){
        int contadorIguales = 0;
        for (int cadaRenglon = 0; cadaRenglon<renglones; cadaRenglon++){
            for (int cadaColumna = 0; cadaColumna<columnas; cadaColumna++){
                if ( obtener(cadaRenglon, cadaColumna).equals(matriz2.obtener(cadaRenglon, cadaColumna))){
                    contadorIguales = contadorIguales + 1;
                }
            }
        }
        if (contadorIguales == obtenerCantidadTotal()){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo que obtiene la cantidad total de elementos en el arreglo que no sean null
     * @return Numero de elementos en el arreglo.
     */
    public int obtenerCantidadTotal(){
        int contadorTotal = 0;
        for (int cadaRenglon = 0; cadaRenglon<renglones; cadaRenglon++){
            for (int cadaColumna = 0; cadaColumna<columnas; cadaColumna++) {
                if ((informacion[cadaRenglon][cadaColumna]) != null){
                    contadorTotal++;
                }
            }
        }
        return contadorTotal;
    }


    /**
     * Metodo que rellena las filas de una columna
     * @param filas numero de fila
     * @param info Objeto a llenar
     * @return Regresa true si se lleno correctamente.
     */
    public boolean vectorColumna(int filas, Object info){
        if( (validarRango(filas, columnas-1)) ){
            for (int cadaRenglon = 0; cadaRenglon<renglones; cadaRenglon++){
                cambiar(cadaRenglon, filas, info);
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo que rellena un renglon de una matriz
     * @param columna Numero de Renglon a llenar.
     * @param info Info a llenar
     * @return Regresa true si se lleno.
     */
    public boolean vectorRenglon(int columna, Object info){
        if( (validarRango(columna, (renglones-1))) ){
            for (int cadaColumna = 0; cadaColumna<columnas; cadaColumna++){
                cambiar(columna, cadaColumna, info);
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo que indica si una matriz esta vacia
     * @return True si es vacia.
     */
    public boolean esVacia(){
        int contadorNulos = 0;
        for (int cadaRenglon = 0; cadaRenglon<renglones; cadaRenglon++){
            for (int cadaColumna = 0; cadaColumna<columnas; cadaColumna++){
                if ( informacion[cadaRenglon][cadaColumna] == null){
                    contadorNulos = contadorNulos + 1;
                }
            }
        }
        if (contadorNulos == (renglones*columnas)){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo que agrega un renglon en lista en cada renglon de la matriz
     * @param arreglo arreglo con la info del renglon
     * @return True si se agrego correctamente.
     */
    public boolean agregarRenglon(ListaEstatica arreglo){
        if (arreglo.maximo() == renglones){
            for (int cadaRenglon = 0; cadaRenglon<renglones; cadaRenglon++){
                for (int cadaColumna = 0; cadaColumna<columnas; cadaColumna++){
                    cambiar(cadaRenglon, cadaColumna, arreglo.obtener(cadaColumna));
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo que agrega los datos de una array como columnas
     * @param arreglo Lista Estatica con los datos de la columna
     * @return Regresa True si se pudo cambiar el contenido.
     */
    public boolean agregarColumna(ListaEstatica arreglo) {
        if (arreglo.maximo() == renglones) {
            for (int cadaColumna = 0; cadaColumna < columnas; cadaColumna++) {
                for (int cadaRenglon = 0; cadaRenglon < renglones; cadaRenglon++) {
                    cambiar(cadaRenglon, cadaColumna, arreglo.obtener(cadaRenglon));
                }
            }
            return true;
        } else {
            return false;
        }
    }


    /**
     * Metodo que agrega una matriz nueva en la matriz actual por columnas.
     * @param matriz2 Nueva matriz a agregar
     * @return Regresa True al agregar las columnas.
     */
    public boolean agregarMatrizPorColumna(Matriz2 matriz2){
        for (int cadaColumna = 0; cadaColumna<matriz2.obtenerColumnas(); cadaColumna++){
            for (int cadaRenglon = 0; cadaRenglon<matriz2.renglones; cadaRenglon++){
                cambiar(cadaRenglon, cadaColumna, matriz2.obtener(cadaRenglon, cadaColumna));
            }
        }
        return true;
    }

    /**
     * Metodo que agrega una matriz nueva en la matriz actual por renglones.
     * @param matriz2 Nueva matriz a agregar
     * @return Regresa True al agregar los renglones.
     */
    public boolean agregarMatrizPorRenglones(Matriz2 matriz2){
        for (int cadaColumna = 0; cadaColumna<matriz2.obtenerColumnas(); cadaColumna++){
            for (int cadaRenglon = 0; cadaRenglon<matriz2.renglones; cadaRenglon++){
                cambiar(cadaColumna, cadaRenglon, matriz2.obtener(cadaRenglon, cadaColumna));
            }
        }
        return true;
    }

    /**
     * Metodo que crea un cubo a partir de una matriz y de una lista Estatica del mismo
     * tamanio que el ancho y largo de una matriz
     * @param matrices Valores de la produndidad.
     * @return Regresa un cubo de numero x numero x numero (solo con dimensiones iguales).
     */
    public Matriz3 aMatriz3(ListaEstatica matrices){
        if ((matrices.maximo() == renglones) && (matrices.maximo() == columnas) && (renglones == columnas)){
            Matriz3 nuevoCubo = new Matriz3(renglones, columnas, matrices.maximo());
            for (int cadaRenglon = 0; cadaRenglon < renglones; cadaRenglon++){
                for (int cadaColumna = 0; cadaColumna < columnas; cadaColumna++){
                    for (int cadaProfundidad = 0; cadaProfundidad < matrices.maximo(); cadaProfundidad++){
                        nuevoCubo.cambiar(cadaRenglon, cadaColumna, cadaProfundidad, matrices.obtener(cadaProfundidad));//Agregamos la profundidad
                    }
                }
            }
            return nuevoCubo;
        } else {
            return null;
        }
    }

    public Matriz2 aVectorColumna(){
        return null;
    }

    public Matriz2 aVectorRenglon(){
        return null;
    }

    /**
     * Metodo que quita los elementos de los renglones.
     * @param tipoCol Un enumerado tipo Columna (DERECHA o IZQUIERDA)
     * @return True si se pudieron quitar los elementos.
     */
    public boolean quitarRenglon(TipoColumna tipoCol){
        if (tipoCol.getNumeroColumna() == 1){
            for (int cadaColumna = 0; cadaColumna<columnas; cadaColumna++){
                cambiar(0, cadaColumna, null);
            }
            return true;
        } else if (tipoCol.getNumeroColumna() == 2) {
            for (int cadaColumna = 0; cadaColumna<columnas; cadaColumna++){
                cambiar(renglones-1, cadaColumna, null);
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo que quita los elementos de las columnas.
     * @param tipoCol Un enumerado tipo Columna (SUPERIOR o INFERIOR)
     * @return True si se pudieron quitar los elementos.
     */
    public boolean quitarColumna(TipoRenglon tipoCol){
        if (tipoCol.getNumeroRenglon() == 1){
            for (int cadaRenglon = 0; cadaRenglon<renglones; cadaRenglon++){
                cambiar(cadaRenglon, 0, null);
            }
            return true;
        } else if (tipoCol.getNumeroRenglon() == 2) {
            for (int cadaRenglon = 0; cadaRenglon<renglones; cadaRenglon++){
                cambiar(cadaRenglon, renglones-1, null);
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo que elimina un renglon dado en la matriz.
     * @param renglon Numero de renglon a eliminar.
     * @return True si se pudo eliminar.
     */
    public boolean eliminarRenglon(int renglon){
        if (validarRango(renglon, renglones-1) == true){
            for (int cadaColumna = 0; cadaColumna<columnas; cadaColumna++){
                cambiar(renglon, cadaColumna, null);
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo que elimina una columna dada en la matriz.
     * @param columna Numero de columna a eliminar.
     * @return True si se pudo eliminar.
     */
    public boolean eliminarColumna(int columna){
        if (validarRango(columna, columnas-1) == true){
            for (int cadaRenglon = 0; cadaRenglon<renglones; cadaRenglon++){
                cambiar(cadaRenglon, columna, null);
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo que invierte la informacion de la matriz horizontalmente.
     */
    public void invertirHorizontal(){
        Matriz2 matrizTemporal = new Matriz2(renglones, columnas);
        int posicionAAgregarColumna = (columnas-1);
        for (int cadaRenglon = 0; cadaRenglon<renglones; cadaRenglon++){
            for (int cadaColumna = 0; cadaColumna<columnas; cadaColumna++){
                if (posicionAAgregarColumna > -1){
                    matrizTemporal.cambiar(cadaRenglon, posicionAAgregarColumna, obtener(cadaRenglon, cadaColumna));
                    posicionAAgregarColumna--;
                } else {
                    break;
                }
            }
            posicionAAgregarColumna = (columnas-1);
        }
        redefinir(matrizTemporal);
    }

    /**
     *  Metodo que invierte una matriz en su eje Y
     */
    public void invertirVertical(){
        Matriz2 matrizTemporal = new Matriz2(renglones, columnas);
        int posicionAAgregarRenglon = (renglones-1);
        for (int cadaRenglon = 0; cadaRenglon<renglones; cadaRenglon++){
            for(int cadaColumna = 0; cadaColumna<columnas; cadaColumna++){
                matrizTemporal.cambiar(posicionAAgregarRenglon, cadaColumna, obtener(cadaRenglon,cadaColumna));
            }
            posicionAAgregarRenglon--;
        }
        redefinir(matrizTemporal);
    }

    public void matrizDiagonal(Object contenido){
        if (renglones == columnas){
            for (int cadaRenglon = 0; cadaRenglon<renglones; cadaRenglon++){
                for (int cadaColumna = 0; cadaColumna<columnas; cadaColumna++){
                    if (cadaColumna == cadaRenglon){
                        cambiar(cadaRenglon, cadaColumna, contenido);
                    }
                }
            }
        }
    }

}


