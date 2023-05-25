package RedesBayesianas;

import entradasalida.EntradaPorDefecto;
import entradasalida.SalidaPorDefecto;
import estructurasLineales.ListaDinamica;
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
        for (int cadaRenglon = 0; cadaRenglon<actual.obtenerRenglones(); cadaRenglon++){
            for (int cadaColumna = 0; cadaColumna<actual.obtenerColumnas(); cadaColumna++){
                SalidaPorDefecto.terminal("Ingrese la probabilidad de ");
                if (cadaRenglon%2 == 0){
                    SalidaPorDefecto.terminal("que SI "+variable);
                } else {
                    SalidaPorDefecto.terminal("que NO "+variable);
                }
                if (actual.obtenerColumnas() > 1){
                    if ( cadaColumna%2 == 0){
                        SalidaPorDefecto.terminal(" SI");
                    } else {
                        SalidaPorDefecto.terminal(" NO");
                    }
                }
                double probabilidad = EntradaPorDefecto.consolaDouble();
                actual.cambiar(cadaRenglon, cadaColumna, probabilidad);
            }
        }
        SalidaPorDefecto.terminal("\n");
        return red.setProbabilidad(actual, variable);
    }

    public ListaDinamica obtenerPadres(Object variable){
        return null;
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
