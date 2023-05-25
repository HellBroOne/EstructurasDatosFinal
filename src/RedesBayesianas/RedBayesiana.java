package RedesBayesianas;

import entradasalida.EntradaPorDefecto;
import entradasalida.SalidaPorDefecto;
import estructurasLineales.ListaDinamica;
import estructurasnolineales.GrafoProbabilidad;
import estructurasnolineales.Matriz2;
import estructurasnolineales.Matriz2Numerica;

/**
 * Clase que indica a una Red Bayesiana.
 * @author Gerardo Rivas Delgado
 * @author Andres Contreras Sanchez
 * @version 1.0
 */
public class RedBayesiana {
    protected GrafoProbabilidad red;

    public RedBayesiana(int numeroNodos){
        this.red = new GrafoProbabilidad(numeroNodos, 0.0);
    }

    /**
     * Metodo que simplemente agrega un objeto como variable.
     * @param variable Variable a agregar.
     * @return Regresa la variable.
     */
    public boolean agregarVariable(Object variable){
        return red.agregarVertice(variable);
    }

    /**
     * Metodo que agrega una relacion a dos nodos.
     * @param origen VerticeProbabilidad de origen.
     * @param destino VerticeProbabilidad de destino
     * @return Regresa true si la relacion fue agregada.
     */
    public boolean agregarArista(Object origen, Object destino){
        return red.agregarArista(origen, destino);
    }

    /**
     * Metodo que permite agregar las probabilidades a cada variable.
     * @param variable Variable a agregar las probabilidades.
     * @return Regresa un numero de probabilidad buscado.
     */
    public boolean agregarProbabilidades(Object variable){
        Matriz2Numerica actual = obtenerMatrizProbabilidad(variable);
        ListaDinamica padres = obtenerPadres(variable);
        int indicePadre = 0;
        String cadena = "";
        for (int cadaRenglon = 0; cadaRenglon<actual.obtenerRenglones(); cadaRenglon++){
            for (int cadaColumna = 0; cadaColumna<actual.obtenerColumnas(); cadaColumna++){
                SalidaPorDefecto.terminal("Ingrese la probabilidad de ");
                if (cadaRenglon%2 == 0){
                    SalidaPorDefecto.terminal("que SI "+variable);
                } else {
                    SalidaPorDefecto.terminal("que NO "+variable);
                }
                padres.inicializarIterador();
                if (actual.obtenerColumnas() > 1){
                    if (cadaColumna < (actual.obtenerColumnas()/2)){
                        cadena = " Si ";
                    } else {
                        cadena = " No ";
                    }
                    if ( cadaColumna%2 == 0){
                        SalidaPorDefecto.terminal(cadena + padres.obtenerNodo());
                    } else {
                        SalidaPorDefecto.terminal(cadena + padres.obtenerNodo());
                    }
                    while (padres.hayNodo()){
                        if ( cadaColumna%2 == 0) {
                            SalidaPorDefecto.terminal(" Si " + padres.obtenerNodo() );
                        } else {
                            SalidaPorDefecto.terminal(" No " + padres.obtenerNodo());
                        }
                    }
                }
                SalidaPorDefecto.terminal(": ");
                double probabilidad = EntradaPorDefecto.consolaDouble();
                actual.cambiar(cadaRenglon, cadaColumna, probabilidad);
            }
        }
        SalidaPorDefecto.terminal("\n");
        return red.setProbabilidad(actual, variable);
    }

    /**
     * Metodo que permite obtener a los padres de un nodo.
     * @param variable Variable a buscar.
     * @return Regresa una Lista Dinamica de los padres.
     */
    private ListaDinamica obtenerPadres(Object variable){
        int indice = red.buscarIndice(variable);
        Matriz2 relaciones = red.obtenerAristas();
        ListaDinamica retorno = new ListaDinamica();
        for (int cadaPadre = 0; cadaPadre<relaciones.obtenerRenglones(); cadaPadre++){
            double obtenido = (double) relaciones.obtener(cadaPadre, indice);
            if (obtenido > 0.0){
                Object verticeObtenido = red.obtenerVertice(cadaPadre);
                retorno.agregar(verticeObtenido);
            }
        }
        if (retorno.vacia() == false){
            return retorno;
        }else{
            return null;
        }
    }


    /**
     * Metodo que permite buscar una probabilidad dada.
     * @param variable Variable a buscar.
     * @return Regresa el numero buscado.
     */
    public double calcularProbabilidad(Object variable){
        //1. tomar el vertice al que nos dirigimos
        Matriz2 probabilidadActual = buscarProbabilidad(variable);
        if (probabilidadActual != null){
            //2. preguntar si o no? sobre el vertice
            int renglon = 0;
            SalidaPorDefecto.terminal(""+variable+" ocurre? [S/N]: ");
            String respuestaRenglon = EntradaPorDefecto.consolaCadenas();
            if (respuestaRenglon.equalsIgnoreCase("s") == true){
                //renglon 0
                renglon = 0;
            } else {
                //renglon 1
                renglon = 1;
            }
            ListaDinamica papis = obtenerPadres(variable);
            int posicion = probabilidadActual.obtenerColumnas();
            if (papis != null){
                while (papis.vacia() == false){
                    SalidaPorDefecto.terminal(""+papis.eliminarInicio()+" ocurre?[S/N]: ");
                    String respuestaDos = EntradaPorDefecto.consolaCadenas();
                    if (respuestaDos.equalsIgnoreCase("s") == true){
                        //mochar de la mitad pa aca
                        posicion = posicion/2;
                    }
                }
            }
            double obtenido = (double) probabilidadActual.obtener(renglon, posicion-1);
            return obtenido;
        } else {
            return -1.0;
        }
    }

    /**
     * Metodo que busca las probabilidades de un grafo.
     * @param variable Variable a Buscar.
     * @return Regresa la matriz buscada.
     */
    public Matriz2Numerica buscarProbabilidad(Object variable){
        Matriz2Numerica matrizObtenida = red.obtenerMatrizVertice(variable);
        return matrizObtenida;
    }

    /**
     * Metodo que obtiene una matriz a partir del padre.
     * @param variable Variable a verificar
     * @return Matriz del padre
     */
    public Matriz2Numerica obtenerMatrizProbabilidad(Object variable){
        int padres = contarPadres(variable);
        int columnas = (int) Math.pow(2, padres);
        Matriz2Numerica retorno = new Matriz2Numerica(2, columnas, 0.0);
        return retorno;
    }

    /**
     * Metodo que permite contar cuantos padres tiene un vertice.
     * @param variable Variable a buscar.
     * @return Regresa un entero a verificar.
     */
    private int contarPadres(Object variable){
        int padres = 0;
        Matriz2 relaciones = red.obtenerAristas();
        int indice = red.buscarIndice(variable);
        for (int cadaOrigen = 0; cadaOrigen<relaciones.obtenerRenglones(); cadaOrigen++){
            double obtenido = (double) relaciones.obtener(cadaOrigen, indice);
            if (obtenido > 0.0){
                padres++;
            }
        }
        return padres;
    }

    public void imprimir(){
        red.imprimir();
    }


    /*
    1.Pedir variable(s) y obtener el valor especifico que el usuario quiera
    2.De los valores especificos obtenidos, multiplicar ambas probabiliades.
    3.Regresar el valor en cuestion.
     */
    public Double calcularProbabilidadConjunta(){
        SalidaPorDefecto.terminal("Cuantas variables quieres multiplicar? ");
        Integer cantidadVeces = EntradaPorDefecto.consolaInteger();
        double resultado = 0.0;
        for (int cadaVariable = 0; cadaVariable<cantidadVeces; cadaVariable++){
            SalidaPorDefecto.terminal("Ingresa la variable "+(cadaVariable+1)+": ");
            String variable = EntradaPorDefecto.consolaCadenas();
            if (variable.equals(" ") == false){
                resultado = calcularProbabilidad(variable);
            }
        }
        return resultado;
    }
}
