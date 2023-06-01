package ModelosOcultosMarkov.codigo;

import entradasalida.SalidaPorDefecto;
import estructurasLineales.ListaDinamica;
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
        //5. cambiar los estados
        reemplazarEmisiones();
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
     * Metodo que en vez de mantener los nombres de las emisiones<br/>
     * los cambia por una lista con la estructura siguiente:<br/>
     * [Nombre del Estado, LIsta Dinamica Clave con probabilidades].<br/>
     * La lista de las probabilidades tiene la estructura siguiente:<br/>
     *  Clave !
     */
    private void reemplazarEmisiones(){
        //probabilidades de lluvia
        ListaNumerica lluvia = probabilidadesALista(0.2, 0.5, 0.2, 0.1);
        ponerEmisionesObservacion("Lluvia", obtenerProbabilidadesEmision(lluvia));
        //probabilidades de tormenta
        ListaNumerica tormenta = probabilidadesALista(0.1, 0.5, 0.3, 0.1);
        ponerEmisionesObservacion("Tormenta", obtenerProbabilidadesEmision(tormenta));
        //probabilidades de seco
        ListaNumerica seco = probabilidadesALista(0.3, 0.1, 0.2, 0.4);
        ponerEmisionesObservacion("Seco", obtenerProbabilidadesEmision(seco));
        //probabilidades de humedo
        ListaNumerica humedo = probabilidadesALista(0.1, 0.2, 0.4, 0.3);
        ponerEmisionesObservacion("Humedo", obtenerProbabilidadesEmision(humedo));
    }

    private void ponerEmisionesObservacion(String estado, ListaDinamicaClave probabilidades){
        String obtenido = (String) estados.buscarVertice(estado);
        if (obtenido != null){
            ListaEstatica emisionEstado = new ListaEstatica(2);
            //en la posicion 0 va el nombre [nombreestado, listaclave con probabilidades]
            emisionEstado.agregar(obtenido);
            //en la posicion 1 van las probabilidades [nombreestado, listaclave con probabilidades]
            emisionEstado.agregar(probabilidades);
            estados.cambiarInfoVertice(estado, emisionEstado);
        }
    }

    /**
     * Metodo que enlista a las probabilidades 1, 2, 3 y 4 en una lista numerica.
     * @param probabilidad1 probabilidad del evento 1
     * @param probabilidad2 probabilidad del evento 2
     * @param probabilidad3 probabilidad del evento 3
     * @param probabilidad4 probabilidad del evento 4
     * @return Regresa una lista numerica con las probabilidades en la lista.
     */
    private ListaNumerica probabilidadesALista(double probabilidad1, double probabilidad2, double probabilidad3, double probabilidad4){
        ListaNumerica probabilidadRetorno = new ListaNumerica(4);
        probabilidadRetorno.agregar(probabilidad1);
        probabilidadRetorno.agregar(probabilidad2);
        probabilidadRetorno.agregar(probabilidad3);
        probabilidadRetorno.agregar(probabilidad4);
        return probabilidadRetorno;
    }

    private ListaDinamicaClave obtenerProbabilidadesEmision(ListaNumerica probabilidades){
        ListaDinamicaClave retornoEmision = new ListaDinamicaClave();
        for (int cadaDato = 0; cadaDato<observaciones.cantidad(); cadaDato++){
            retornoEmision.agregar(observaciones.obtener(cadaDato), probabilidades.obtener(cadaDato));
        }
        return retornoEmision;
    }

    /**
     * Metodo que imprime el modelo de Markov en consola.
     */
    public void imprimirModelo(){
        SalidaPorDefecto.terminal("# Probabilidades Iniciales:\n");
        imprimirProbabilidadesIniciales();
        SalidaPorDefecto.terminal("# Observaciones:\n");
        observaciones.imprimirOI();
        SalidaPorDefecto.terminal("# Estados:\n");
        imprimirEstados();
        SalidaPorDefecto.terminal("# Emisiones de Estados:\n");
        imprimirEmisionesEstados();
        SalidaPorDefecto.terminal("# Transiciones:\n");
        imprimirTransiciones();
    }

    public void imprimirProbabilidadesIniciales(){
        for (int cadaDato = 0; cadaDato< probabilidadesIniciales.maximo(); cadaDato++){
            ListaEstatica listaobtenida = (ListaEstatica) estados.obtenerVertice(cadaDato);
            SalidaPorDefecto.terminal(""+listaobtenida.obtener(0)+": "+probabilidadesIniciales.obtener(cadaDato)+"\n");
        }
    }

    public void imprimirEstados(){
        for (int cadaEstado = 0; cadaEstado<4; cadaEstado++){
            ListaEstatica listaObtenida = (ListaEstatica) estados.obtenerVertice(cadaEstado);
            SalidaPorDefecto.terminal(listaObtenida.obtener(0)+"\n");
        }
    }

    public void imprimirEmisionesEstados(){
        for (int cadaEstado = 0; cadaEstado<4; cadaEstado++){
            ListaEstatica listaObtenida = (ListaEstatica) estados.obtenerVertice(cadaEstado);
            ListaDinamicaClave probabilidades = (ListaDinamicaClave) listaObtenida.obtener(1);
            SalidaPorDefecto.terminal(listaObtenida.obtener(0)+": {");
            for (int cadaProbabilidad = 0; cadaProbabilidad<probabilidades.cantidad(); cadaProbabilidad++){
                ListaEstatica emisiones = probabilidades.obtenerClaveValor(cadaProbabilidad);
                SalidaPorDefecto.terminal("("+emisiones.obtener(0)+": "+emisiones.obtener(1)+")");
                if (cadaProbabilidad != (probabilidades.cantidad()-1)){
                    SalidaPorDefecto.terminal(", ");
                }
            }
            SalidaPorDefecto.terminal("}\n");
        }
    }

    public void imprimirTransiciones(){
        estados.obtenerAristas().imprimirPorRenglones();
    }
}
