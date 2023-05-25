package RedesBayesianas;

import entradasalida.EntradaPorDefecto;
import entradasalida.SalidaPorDefecto;
import estructurasLineales.ListaDinamica;
import estructurasLineales.ListaEstatica;
import estructurasnolineales.GrafoEstatico;
import estructurasnolineales.Matriz2;

public class RedBayesiana {
    protected GrafoEstatico red;

    public RedBayesiana(int numeroNodos){
        this.red = new GrafoEstatico(numeroNodos, 0.0);
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
     * @param origen Vertice de origen.
     * @param destino Vertice de destino
     * @return Regresa true si la relacion fue agregada.
     */
    public boolean agregarArista(Object origen, Object destino){
        return red.agregarArista(origen, destino);
    }

    /**
     * Metodo que permite agregar las probabilidades a cada variable.
     * @param variable Variable a agregar las probabilidades.
     * @return Regresa
     */
    public boolean agregarProbabilidades(Object variable){
        Matriz2 actual = obtenerMatrizProbabilidad(variable);
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

    public ListaDinamica obtenerPadres(Object variable){
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


    //3. preguntar del padre, y varificar si si o no
    //4. ir a dicha posicion
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
    public Matriz2 buscarProbabilidad(Object variable){
        Matriz2 matrizObtenida = red.obtenerMatrizVertice(variable);
        return matrizObtenida;
    }

    /**
     * Metodo que obtiene una matriz a partir del padre.
     * @param variable Variable a verificar
     * @return Matriz del padre
     */
    public Matriz2 obtenerMatrizProbabilidad(Object variable){
        int padres = contarPadres(variable);
        int columnas = (int) Math.pow(2, padres);
        Matriz2 retorno = new Matriz2(2, columnas, 0.0);
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
}
