package estructurasnolineales;

import entradasalida.SalidaPorDefecto;
import estructurasLineales.ListaNumerica;
import utilerias.matematicas.TipoLogaritmo;

/**
 * Clase que implementa un TDA de Matriz 2D Numerica
 */
public class Matriz2Numerica extends Matriz2{

    /**
     * Constructor de la clase Matriz2Numerica que recibe como argumento los renglones
     * y columnas. Se inicializa en 0.
     * @param renglones Numero de tamanio de Renglones.
     * @param columnas Numero de tamanio de Columnas.
     */
    public Matriz2Numerica(int renglones, int columnas) {
        super(renglones, columnas);
        rellenar(0);
    }

    /**
     * Constructor de la clase Matriz2Numerica que recibe como argumento los renglones
     * y columnas. Se inicializa con el valor que se le pase como info.
     * @param renglones Numero de tamanio de Renglones.
     * @param columnas Numero de tamanio de Columnas.
     * @param info Objeto (Numerico) el cual inicializara la variable.
     */
    public Matriz2Numerica(int renglones, int columnas, Object info) {
        super(renglones, columnas);
        rellenar(info);
    }

    /**
     * Metodo que cambia el valor de una posicion (x,y)
     * @param renglon Valor del renglon del elemento a cambiar.
     * @param columna Valor de la columna del elemento a cambiar.
     * @param info Valor a cambiar.
     * @return Regresa True si se pudo cambiar.
     */
    public boolean cambiar(int renglon, int columna, Object info){
        if (info instanceof Number){
            if (info instanceof Integer){
                double valor = (int) info;
                super.cambiar(renglon, columna, valor);
                return true;
            } else if (info instanceof Double) {
                super.cambiar(renglon, columna, info);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


    /**
     * Metodo que rellena las posiciones de una matriz con un elemento dado.
     * @param info Objeto Numerico que se desea guardar.
     */
    public void rellenar(Object info){
        if ((info instanceof Number) == true) { //verifiquemos que sea instancias de number
            if (info instanceof Integer){ //verificar si es int
                double valor = (int) info;
                super.rellenar(valor);
            } else if ((info instanceof Double) == true){ //verificar si es double
                super.rellenar(info);
            }
        }

    }

    /**
     * Metodo que multiplica un escalar por la matriz.
     * @param escalar Numero de escalar.
     * @return Regresa
     */
    public boolean porEscalar(Number escalar){
        if ((escalar instanceof Integer) == true){
            double valorEscalar = (int) escalar;
            for (int cadaRenglon = 0; cadaRenglon<renglones; cadaRenglon++){
                for (int cadaColumna = 0; cadaColumna<columnas; cadaColumna++){
                    cambiar(cadaRenglon, cadaColumna, ((double) obtener(cadaRenglon, cadaColumna) * valorEscalar));
                }
            }
            return true;
        } else if ((escalar instanceof Double) == true) {
            for (int cadaRenglon = 0; cadaRenglon<renglones; cadaRenglon++){
                for (int cadaColumna = 0; cadaColumna<columnas; cadaColumna++){
                    cambiar(cadaRenglon, cadaColumna, ((double) obtener(cadaRenglon, cadaColumna) * (double) escalar));
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Multiplica los valores de una Lista Numerica por los elementos en clumnas de la matriz.
     * @param escalares Lista con los escalares.
     * @return Regresa True si se multiplicaron.
     */
    public boolean porEscalares(ListaNumerica escalares){
        if (escalares.maximo() == columnas){
            for (int cadaRenglon = 0; cadaRenglon<renglones; cadaRenglon++){//recorrer las filas/renglones
                for (int cadaColumna = 0; cadaColumna<columnas; cadaColumna++){//recorrer las columnas
                    cambiar(cadaRenglon, cadaColumna, (((double) obtener(cadaRenglon, cadaColumna) * (double) escalares.obtener(cadaColumna)) ));
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo que suma un escalar por valor a la matriz.
     * @param escalar Objeto tipo Number a sumar.
     * @return Regresa True si se pudo sumar.
     */
    public boolean sumarEscalar(Number escalar){
        if ((escalar instanceof Integer) == true){
            double valorEscalar = (int) escalar;
            for (int cadaRenglon = 0; cadaRenglon<renglones; cadaRenglon++){
                for (int cadaColumna = 0; cadaColumna<columnas; cadaColumna++){
                    cambiar(cadaRenglon, cadaColumna, ((double) obtener(cadaRenglon, cadaColumna) + valorEscalar));
                }
            }
            return true;
        } else if ((escalar instanceof Double) == true) {
            for (int cadaRenglon = 0; cadaRenglon<renglones; cadaRenglon++){
                for (int cadaColumna = 0; cadaColumna<columnas; cadaColumna++){
                    cambiar(cadaRenglon, cadaColumna, ((double) obtener(cadaRenglon, cadaColumna) + (double) escalar));
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo que suma los valores de una Lista Numerica por los valores de las columnas.
     * @param escalares Lista numerica de escalares.
     * @return Regresa True si si los suma.
     */
    public boolean sumarEscalares(ListaNumerica escalares){
        if (escalares.maximo() == columnas){
            for (int cadaRenglon = 0; cadaRenglon<renglones; cadaRenglon++){//recorrer las filas/renglones
                for (int cadaColumna = 0; cadaColumna<columnas; cadaColumna++){//recorrer las columnas
                    cambiar(cadaRenglon, cadaColumna, (((double) obtener(cadaRenglon, cadaColumna) + (double) escalares.obtener(cadaColumna)) ));
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo que multiplica dos matrices segun la teoria de matrices.
     * @param matriz2 Nueva matriz a agregar.
     * @return Regresa True si las matrices verificaron sus dimensiones.
     */
    public boolean multiplicar(Matriz2Numerica matriz2){
        if (columnas == matriz2.obtenerRenglones()){ //si y solo si
            Matriz2Numerica temporal = new Matriz2Numerica(renglones, matriz2.obtenerColumnas());
            for (int cadaRenglon = 0; cadaRenglon<renglones; cadaRenglon++){ //recorrer la fila de la matriz actual
                for (int cadaColumnaMatriz2 = 0; cadaColumnaMatriz2<matriz2.obtenerColumnas(); cadaColumnaMatriz2++){ //recorrer la columna de la matriz de paramtero
                    double suma = 0.0;
                    for (int cadaValor = 0; cadaValor<columnas; cadaValor++){ //recorrer cada valor de los
                        suma = suma + (((double) obtener(cadaRenglon, cadaValor))*((double) matriz2.obtener(cadaValor, cadaColumnaMatriz2)));
                        temporal.cambiar(cadaRenglon, cadaColumnaMatriz2, suma);
                    }
                }
            }
            redefinir(temporal);
            return true;
        }else{
            return false;
        }
    }

    /**
     * Metodo que suma dos matrices segun la teoria de matrices.
     * @param matriz2 Matriz a sumar (del mismo tamanio).
     * @return True si se validaron las dimensiones.
     */
    public boolean sumar(Matriz2Numerica matriz2){
        if ( ((columnas == matriz2.obtenerColumnas()) == true) && ((renglones == matriz2.obtenerRenglones()) == true)  ){
            for (int cadaRenglon = 0; cadaRenglon<renglones; cadaRenglon++){
                for (int cadaColumna = 0; cadaColumna<columnas; cadaColumna++){
                    double suma = ((double) obtener(cadaRenglon, cadaColumna)) + ((double) matriz2.obtener(cadaRenglon, cadaColumna));
                    cambiar(cadaRenglon, cadaColumna, suma);
                }
            }
            return true;
        }else{
            return false;
        }
    }

    /**
     * Metodo que aplica una potencia en parametros a los valores de la matriz.
     * @param escalar Numero escalar a potenciar.
     * @return Regresa True si se pudo aplicar la potencia.
     */
    public boolean aplicarPotencia(Number escalar){
        if ((escalar instanceof Integer) == true){
            double valorEscalar = (int) escalar;
            for (int cadaRenglon = 0; cadaRenglon<renglones; cadaRenglon++){
                for (int cadaColumna = 0; cadaColumna<columnas; cadaColumna++){
                    cambiar(cadaRenglon, cadaColumna, Math.pow(((double) obtener(cadaRenglon, cadaColumna)), valorEscalar));
                }
            }
            return true;
        } else if ((escalar instanceof Double) == true) {
            for (int cadaRenglon = 0; cadaRenglon<renglones; cadaRenglon++){
                for (int cadaColumna = 0; cadaColumna<columnas; cadaColumna++){
                    cambiar(cadaRenglon, cadaColumna, Math.pow(((double) obtener(cadaRenglon, cadaColumna)), (double) escalar));
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo que aplica un Logaritmo a cada una de los valores de la Matriz.
     * @param tipoLogaritmo Objeto tipo TipoLogaritmo.
     * @return Regresa True si se aplico el logaritmo,
     * false si hay un 0 o se especifico otro tipo de base.
     */
    public boolean aplicarLogaritmo(TipoLogaritmo tipoLogaritmo){
       if (tipoLogaritmo.getBase() == 1){ //Si es natural
           for (int cadaRenglon = 0; cadaRenglon<renglones; cadaRenglon++){
               for (int cadaColumna = 0; cadaColumna<columnas; cadaColumna++){
                   if (((double) obtener(cadaRenglon, cadaColumna)) != 0.0){
                       cambiar(cadaRenglon, cadaColumna, Math.log((double) obtener(cadaRenglon, cadaColumna)));
                   } else {
                       return false;
                   }
               }
           }
           return true;
       } else if (tipoLogaritmo.getBase() == 2) { //Si es base 2
           for (int cadaRenglon = 0; cadaRenglon<renglones; cadaRenglon++){
               for (int cadaColumna = 0; cadaColumna<columnas; cadaColumna++){
                   if (((double) obtener(cadaRenglon, cadaColumna)) != 0.0){
                   cambiar(cadaRenglon, cadaColumna, (Math.log10((double) obtener(cadaRenglon, cadaColumna))/Math.log10(2) ) );
                   } else {
                       return false;
                   }
               }
           }
           return true;
       } else if (tipoLogaritmo.getBase() == 3) { //Si es base 10
           for (int cadaRenglon = 0; cadaRenglon<renglones; cadaRenglon++){
               for (int cadaColumna = 0; cadaColumna<columnas; cadaColumna++){
                   if (((double) obtener(cadaRenglon, cadaColumna)) != 0.0){
                       cambiar(cadaRenglon, cadaColumna, Math.log10((double) obtener(cadaRenglon, cadaColumna)));
                   } else {
                       return false;
                   }
               }
           }
           return true;
       } else {
           return false;
       }
    }

    /**
     * Metodo que cambia la matriz actual a una diagonal, con su respectivo contenido.
     * @param contenido Numero que forme la diagonal
     * @return Regresa true si se pudo cambiar la matriz.
     */
    public boolean matrizDiagonal(Number contenido){
        if ((renglones == columnas) == true){
            double valor = 0.0;
            if (contenido instanceof Integer){
                valor = (int) contenido;
            } else if (contenido instanceof Double){
                valor = (double) contenido;
            } else {
                return false;
            }
            for (int cadaRenglon = 0; cadaRenglon<renglones; cadaRenglon++){
                for (int cadaColumna = 0; cadaColumna<columnas; cadaColumna++){
                    if (cadaColumna == cadaRenglon){
                        cambiar(cadaRenglon, cadaColumna, ((double) obtener(cadaRenglon, cadaColumna))+valor);
                    } else {
                        cambiar(cadaRenglon, cadaColumna, 0.0);
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo que determina si una matriz es diagonal superior.
     * @return Devuelve True si es diagonal superior, false si no.
     */
    public boolean esDiagonalSuperior(){
        if (renglones == columnas){
            double numeroDeMatriz = (int) renglones-1;
            double sucesionTriangular = (numeroDeMatriz*(numeroDeMatriz+1))/2; //numero de 0s que debemos tener
            double contadorCeros = 0.0; //contador de 0s en el triangulo de 0s inferior
            int limiteEvaluarColumnas = 1;
            for (int renglon = 1; renglon<renglones; renglon++){
                for (int columna = 0; columna<limiteEvaluarColumnas; columna++){
                    if (((double) obtener(renglon, columna)) == 0.0){
                        contadorCeros++;
                    }
                }
                limiteEvaluarColumnas++;
            }
            if (contadorCeros == sucesionTriangular){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    /**
     * Metodo que determina si una matriz es diagonal inferior.
     * @return Devuelve True si es diagonal inferior, false si no.
     */
    public boolean esDiagonalInferior(){
        if (renglones == columnas){
            double numeroDeMatriz = (int) renglones-1;
            double sucesionTriangular = (numeroDeMatriz*(numeroDeMatriz+1))/2; //numero de 0s que debemos tener
            double contadorCeros = 0.0; //contador de 0s en el triangulo de 0s inferior
            int limiteEvaluarColumnas = 1;
            for (int renglon = 0; renglon<renglones; renglon++){
                for (int columna = limiteEvaluarColumnas; columna<columnas; columna++){
                    if (((double) obtener(renglon, columna)) == 0.0){
                        contadorCeros++;
                    }
                }
                limiteEvaluarColumnas++;
            }
            if (contadorCeros == sucesionTriangular){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    /**
     * Obtiene la potencia de una matriz en la matriz acutal.
     * @param exponente Exponente que indica cuantas veces se multiplicara
     * @return Regresa True al haber finalizado con la potenciacion.
     */
    public boolean potencia(int exponente){
        if (exponente > 1){ //se hace n-1 veces para no hacerla desde 0 hasta n
            Matriz2Numerica matrizTemporal = clonar();
            for (int vecesAMultiplicar = 0; vecesAMultiplicar<(exponente-1);vecesAMultiplicar++){
                multiplicar(matrizTemporal);
            }
            return true;
        } else if ((exponente == 0) ||(exponente == 1)){ //no se multiplica, aunque en 0 da 1, pero no cambiaremos la estructura de la matriz.
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Metodo que clona la Matriz Numerica actual en otra.
     * @return Regresa una Matriz2Numerica.
     */
    public Matriz2Numerica clonar(){
        Matriz2Numerica matrizNueva = new Matriz2Numerica(renglones, columnas);
        for (int cadaRenglon = 0; cadaRenglon<renglones; cadaRenglon++){
            for (int cadaColumna = 0; cadaColumna<renglones; cadaColumna++){
                matrizNueva.cambiar(cadaRenglon, cadaColumna, obtener(cadaRenglon, cadaColumna));
            }
        }
        return matrizNueva;
    }

    /**
     * Metodo que dobla las Columnas de una matriz.
     * @return Regresa true si se pudieron doblar.
     */
    public boolean doblarColumnas(){ //funciona con caso par
        Matriz2Numerica temporal;
        if ((renglones%2) == 0 && (renglones != 1)){//Si los renglones son pares
            int numRenglones = renglones/2;
            temporal = new Matriz2Numerica(numRenglones, columnas);
            int renglonesTemporal = 0;
            for (int renglon = 0; renglon<renglones; renglon = renglon+2){//recorrer cada 2 renglones de la matriz actual
                for (int columna = 0; columna<columnas; columna++){ //recorrer todas las columnas de la matriz temporal.
                    double suma = ((double) obtener(renglon, columna))+((double) obtener(renglon+1, columna));
                    temporal.cambiar(renglonesTemporal, columna, suma);
                }
                renglonesTemporal++;
            }
            redefinir(temporal);
            return true;
        } else { //si es impar
            return false;
        }
    }


    /**
     * Metodo que dobla los renglones de una matriz.
     * @return Regresa true si se pudieron doblar.
     */
    public boolean doblarRenglones(){
        if ((columnas%2)==0){ //si las columnas son pares
            int numeroColumnas = columnas/2;
            Matriz2Numerica temporal = new Matriz2Numerica(renglones, numeroColumnas); //nueva matriz temporal
            int columnasTemporal = 0;
            for (int cadaRenglon = 0; cadaRenglon<renglones;cadaRenglon++){ //cada renglon se iterara las veces de la columna.
                for (int columna = 0; columna<columnas; columna = columna + 2){
                    double suma = ((double) obtener(cadaRenglon, columna))+((double) obtener(cadaRenglon, columna+1));
                    temporal.cambiar(cadaRenglon, columnasTemporal, suma);
                    columnasTemporal++;
                }
                columnasTemporal = 0; //se tiene que reiniciar la columna a donde se va a agregar.
            }
            redefinir(temporal);
            return true;
        } else {
            int posicionColumnaCentral = ((columnas-1)/2);
            int contadorColumnasPorderecha = 0;
            int contadorColumnasPorizquierda = 0;
            for (int contarDerecha = 0; contarDerecha<posicionColumnaCentral;contarDerecha++){
                contadorColumnasPorderecha++;
            }
            for (int contarIzquierda = (columnas-1); contarIzquierda>posicionColumnaCentral;contarIzquierda--){
                contadorColumnasPorizquierda++;
            }
            if ((contadorColumnasPorderecha%2 == 0)&&(contadorColumnasPorizquierda%2 == 0)){ //podemos aplicar operaciones
                Matriz2Numerica temporal = new Matriz2Numerica(renglones, contadorColumnasPorderecha+1);
                //rellenar la columna de enmedio
                int posicionDeEnMedio = (temporal.obtenerColumnas()-1)/2;
                for (int renglon = 0; renglon<renglones; renglon++){
                    temporal.cambiar(renglon, posicionDeEnMedio, ((double) obtener(renglon, posicionColumnaCentral)));
                }
                //rellenar las columnas anteriores
                int columnasTemporal = 0;
                for (int cadaRenglon = 0; cadaRenglon<renglones;cadaRenglon++){ //cada renglon se iterara las veces de la columna.
                    for (int columna = 0; columna<posicionColumnaCentral; columna = columna + 2){
                        double suma = ((double) obtener(cadaRenglon, columna))+((double) obtener(cadaRenglon, columna+1));
                        temporal.cambiar(cadaRenglon, columnasTemporal, suma);
                        columnasTemporal++;
                    }
                    columnasTemporal = 0; //se tiene que reiniciar la columna a donde se va a agregar.
                }
                //rellenar las que van despues
                columnasTemporal = temporal.obtenerColumnas()-1;
                for (int cadaRenglon = 0; cadaRenglon<renglones;cadaRenglon++){ //cada renglon se iterara las veces de la columna.
                    for (int columna = (columnas-1); columna>posicionColumnaCentral; columna = columna - 2){
                        double suma = ((double) obtener(cadaRenglon, columna))+((double) obtener(cadaRenglon, columna-1));
                        SalidaPorDefecto.terminal(""+suma);
                        temporal.cambiar(cadaRenglon, columnasTemporal, suma);
                        columnasTemporal--;
                    }
                    columnasTemporal = temporal.obtenerColumnas()-1; //se tiene que reiniciar la columna a donde se va a agregar.
                }
                redefinir(temporal);
                return true;
            }else{
                return false;
            }
        }
    }

}
