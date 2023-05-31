package ModelosOcultosMarkov.codigo;

import entradasalida.SalidaPorDefecto;
import estructurasLineales.ListaDinamicaClave;
import estructurasLineales.ListaEstatica;
import estructurasLineales.ListaNumerica;
import estructurasnolineales.GrafoEstatico;

/**
 * Clase que implementa un Modelo Oculto de Markov,
 * para el ejemplo propuesto.<br/>
 * La idea es tener 4 estados en este ejemplo asi como 4 observaciones<br/>
 * por ende, muchos metodos son para agregar esas cuatro observaciones<br/>
 * sin que el usuario las pueda establecer, la idea aqui es que el usuario<br/>
 * solamente pueda hacer las preguntas del punto 5 del trabajo final.
 * @author Andres Contreras Sanchez
 * @author Gerardo Rivas Delgado
 * @version 1.0
 */

public class ModeloOcultoMarkov {
    protected GrafoEstatico estados; //grafo completo
    protected ListaNumerica probabilidadesIniciales; //probabilidades Iniciales de cada estado.
    protected ListaEstatica observaciones; //observaciones de cada estado.

    public ModeloOcultoMarkov(){
        this.estados = new GrafoEstatico(4, 0.0);
        probabilidadesIniciales = new ListaNumerica(4);
        observaciones = new ListaEstatica(4);
        inicializarModelo();
    }

    private void inicializarModelo(){
        //1. Iniciar probabilidades Iniciales
        agregarProbabilidadesIniciales();
        //2. creamos las observaciones
        crearObservaciones("Caminar", "Dormir", "Jugar", "Gimnasio");
        //3. agregar estados
        inicializarEstados();
        //4. agregar probabilidades de transicion
        inicializarTransiciones();

    }

    private void agregarProbabilidadesIniciales(){
        probabilidadesIniciales.agregar(0.3);
        probabilidadesIniciales.agregar(0.1);
        probabilidadesIniciales.agregar(0.4);
        probabilidadesIniciales.agregar(0.2);
    }

    private void crearObservaciones(String observacion1, String observacion2, String observacion3, String observacion4){
        observaciones.agregar(observacion1);
        observaciones.agregar(observacion2);
        observaciones.agregar(observacion3);
        observaciones.agregar(observacion4);
    }

    /**
     * Metodo que inicia
     */
    private void inicializarEstados(){
        agregarEstado("Lluvia");
        agregarEstado("Tormenta");
        agregarEstado("Seco");
        agregarEstado("Humedo");
    }

    /**
     * Metodo que agrega un estado al Modelo.
     * @param estado Estado a agregar.
     */
    private void agregarEstado(String estado){
        estados.agregarVertice(estado);
    }

    /**
     * Metodo que permite al usuario iniciar las probabilidades de transicion
     */
    private void inicializarTransiciones(){
        //Agregar las de lluvia
        agregarTransicion("Lluvia", "Lluvia", 0.4);
        agregarTransicion("Lluvia", "Tormenta", 0.3);
        agregarTransicion("Lluvia", "Seco", 0.1);
        agregarTransicion("Lluvia", "Humedo", 0.2);

        //Agregar las de tormenta
        agregarTransicion("Tormenta", "Lluvia", 0.3);
        agregarTransicion("Tormenta", "Tormenta", 0.5);
        agregarTransicion("Tormenta", "Seco", 0.1);
        agregarTransicion("Tormenta", "Humedo", 0.1);

        //Agregar las de seco
        agregarTransicion("Seco", "Lluvia", 0.2);
        agregarTransicion("Seco", "Tormenta", 0.1);
        agregarTransicion("Seco", "Seco", 0.5);
        agregarTransicion("Seco", "Humedo", 0.2);

        //Agregar las de humedo
        agregarTransicion("Humedo", "Lluvia", 0.3);
        agregarTransicion("Humedo", "Tormenta", 0.2);
        agregarTransicion("Humedo", "Seco", 0.1);
        agregarTransicion("Humedo", "Humedo", 0.4);
    }

    /**
     * Metodo que agrega una probabilidad de transicion de un estado A a uno B.
     * @param estadoOrigen Estado A.
     * @param estadoDestino Estado B.
     * @param probabilidad Probabilidad de transicion.
     */
    private void agregarTransicion(String estadoOrigen, String estadoDestino, double probabilidad){
        estados.agregarArista(estadoOrigen, estadoDestino, probabilidad);
    }

    /**
     * Metodo que imprime el modelo de Markov en consola.
     */
    public void imprimirModelo(){
        SalidaPorDefecto.terminal("Probabilidades Iniciales: ");
        probabilidadesIniciales.imprimir();
        SalidaPorDefecto.terminal("Observaciones: ");
        observaciones.imprimir();
        SalidaPorDefecto.terminal("Estados y Transiciones: ");
        estados.imprimir();
    }
}
