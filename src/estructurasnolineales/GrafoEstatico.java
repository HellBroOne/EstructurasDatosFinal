package estructurasnolineales;

import entradasalida.SalidaPorDefecto;
import estructurasLineales.*;
import estructurasnolineales.auxiliares.Vertice;
import registros.comunes.EtiquetaGrafo;
import utilerias.comunes.TipoOrden;

/**
 * Clase que implementa un TDA de Grafo Estatico
 * @author Gerardo Rivas Delgado
 * @version 1.0
 */
public class GrafoEstatico {
    protected Matriz2 aristas;
    protected ListaEstatica vertices;

    protected TipoOrden tipoOrden;


    public GrafoEstatico(int numeroVertices){
        aristas = new Matriz2(numeroVertices, numeroVertices);
        vertices = new ListaEstatica(numeroVertices);
    }

    public GrafoEstatico(int numeroVertices, Object inicializador){
        aristas = new Matriz2(numeroVertices, numeroVertices, inicializador);
        vertices = new ListaEstatica(numeroVertices);
    }

    public GrafoEstatico(int numeroVertices, TipoOrden orden){
        this(numeroVertices);
        this.tipoOrden = orden;
        if (tipoOrden == TipoOrden.DEC){
            aristas.rellenar(Double.MAX_VALUE);
        } else {
            aristas.rellenar(Double.MIN_VALUE);
        }
        aristas.matrizDiagonal(0.0);
    }

    public boolean agregarVertice(Object info){
        int indiceVertice = (int) vertices.buscar(info);
        if (indiceVertice != -1){
            return false;
        } else {
            Vertice nuevoVertice = new Vertice();
            nuevoVertice.setInfo(info);
            nuevoVertice.setIndice(vertices.cantidad());
            int resultado = vertices.agregar(nuevoVertice);
            if (resultado == -1){
                return false;
            }else{
                return true;
            }
        }
    }

    public boolean agregarArista(Object origen, Object destino){
        return agregarArista(origen, destino, 1);
    }

    public boolean agregarArista(Object origen, Object destino, double peso){
        int indiceOrigen = (int) vertices.buscar(origen);
        int indiceDestino = (int) vertices.buscar(destino);
        if (indiceOrigen != -1 && indiceDestino != -1){
            return aristas.cambiar(indiceOrigen, indiceDestino, peso);
        }else{ //al menos uno no se encontro
            return false;
        }
    }

    public void imprimir(){
        vertices.imprimir();
        SalidaPorDefecto.terminal("\n");
        aristas.imprimirPorRenglones();
    }

    //Metodo de recorrido de Profundidad
    public ListaDinamica recorridoProfundidad(Object origen){
        PilaEstatica pila = new PilaEstatica(vertices.cantidad());
        ListaEstatica marcados = new ListaEstatica(vertices.cantidad());
        ListaDinamica recorridoSalida = new ListaDinamica();
        //1. Partir de un nodo origen, marcarlo y ponerlo en la pila
        int indiceOrigen = (int) vertices.buscar(origen);
        if (indiceOrigen == -1){
            return null;
        }
        pila.poner(indiceOrigen);
        marcados.rellenar(false, vertices.cantidad());
        marcados.cambiar(indiceOrigen, true);
        //2. Mientras haya elementos en la pila, sacar uno
        while (pila.vacio() != true) {
            int indiceVerticeActual = (int) pila.quitar();
            Vertice verticeActual = (Vertice) vertices.obtener(indiceVerticeActual);
            recorridoSalida.agregar(verticeActual.getInfo());
            //3. Los vertices adyacentes al vertice sacado, mientras no esten marcados,
            // ponerlos en la pila y marcarlos
            marcarYEnpilarVerticesAdyacentes(indiceVerticeActual, marcados, pila);
        }
        return recorridoSalida;
    }

    private void marcarYEnpilarVerticesAdyacentes(int indiceVerticeOrigen, ListaEstatica marcados, PilaEstatica pila){
        for(int cadaDestino = 0; cadaDestino < aristas.obtenerColumnas(); cadaDestino++){
            Double flecha = (Double) aristas.obtener(indiceVerticeOrigen, cadaDestino);
            if (flecha != null && flecha > 0 && (boolean) marcados.obtener(cadaDestino) == false){
                marcados.cambiar(cadaDestino, true);
                pila.poner(cadaDestino);
            }
        }
    }

    public ListaDinamica recorridoAnchura(Object origen){
        ColaEstatica cola = new ColaEstatica(vertices.cantidad());
        ListaEstatica marcados = new ListaEstatica(vertices.cantidad());
        ListaDinamica recorridoSalida = new ListaDinamica();
        //1. Partir de un nodo origen, marcarlo y ponerlo en la pila
        int indiceOrigen = (int) vertices.buscar(origen);
        if (indiceOrigen == -1){
            return null;
        }
        cola.poner(indiceOrigen);
        marcados.rellenar(false, vertices.cantidad());
        marcados.cambiar(indiceOrigen, true);
        //2. Mientras haya elementos en la pila, sacar uno
        while (cola.vacio() != true) {
            int indiceVerticeActual = (int) cola.quitar();
            Vertice verticeActual = (Vertice) vertices.obtener(indiceVerticeActual);
            recorridoSalida.agregar(verticeActual.getInfo());
            //3. Los vertices adyacentes al vertice sacado, mientras no esten marcados,
            // ponerlos en la pila y marcarlos
            marcarYEncolarVerticesAdyacentes(indiceVerticeActual, marcados, cola);
        }
        return recorridoSalida;
    }

    private void marcarYEncolarVerticesAdyacentes(int indiceVerticeOrigen, ListaEstatica marcados,ColaEstatica cola){
        for(int cadaDestino = 0; cadaDestino < aristas.obtenerColumnas(); cadaDestino++){
            Double flecha = (Double) aristas.obtener(indiceVerticeOrigen, cadaDestino);
            if (flecha != null && flecha > 0 && (boolean) marcados.obtener(cadaDestino) == false){
                marcados.cambiar(cadaDestino, true);
                cola.poner(cadaDestino);
            }
        }
    }

    //ORDENACION TOPOLOGICA
    public ListaDinamica ordenacionTopologica() {
        ListaDinamica recorridoOT = new ListaDinamica();
        ColaDinamica cola = new ColaDinamica();
        ListaEstatica marcados = new ListaEstatica(vertices.cantidad());
        ListaEstatica incidencias = new ListaEstatica(vertices.cantidad());
        //Paso 0. No existen ciclos - Este paso se debe de ejecutar en otro proceso que no hare ahorita.

        //Paso 1. Inicializar incidencias (grafo de entrada de nodos, es decir, flechas que llegan al nodo).
        incidenciasTodosVertices(incidencias);
        //Paso 2. Los vértices con incidencia en 0, se meten en la cola y se marcan.
        marcados.rellenar(false, vertices.cantidad());
        encolarYMArcarVerticesIncidencia0(incidencias, marcados, cola);
        while (cola.vacio() != true) {
            //Paso 3. Mientras se tengan elementos en la cola, sacar uno y procesarlo.
            int indiceVerticeActual = (int) cola.quitar();
            Vertice verticeActual = (Vertice) vertices.obtener(indiceVerticeActual);
            recorridoOT.agregar(verticeActual.getInfo());
            //Paso 4. Recalcular las incidencias con base del paso 3.
            recalcularIncidencias(incidencias, indiceVerticeActual, marcados);
            //Paso 5. Los vértices de incidencia en 0, no marcados, se meten en la cola y se marcan. (Ir al paso 3)
            encolarYMArcarVerticesIncidencia0(incidencias, marcados, cola);
        }
        return recorridoOT;
    }

    //Paso 1 de OT
    private int incidenciasDeVertice(int indiceDestino){
        int numeroincidencias = 0;
        for (int cadaFilaOrigen = 0; cadaFilaOrigen< aristas.obtenerRenglones(); cadaFilaOrigen++){
            Double flecha = ((Double) aristas.obtener(cadaFilaOrigen, indiceDestino));
            if (flecha != null && flecha > 0){
                numeroincidencias++;
            }
        }
        return numeroincidencias;
    }

    private void incidenciasTodosVertices(ListaEstatica incidencias){
        for (int cadaColumnaDestino = 0; cadaColumnaDestino< aristas.obtenerColumnas(); cadaColumnaDestino++){
            int numeroIncidenciasCadaDestino = incidenciasDeVertice(cadaColumnaDestino);
            incidencias.agregar(numeroIncidenciasCadaDestino);
        }
    }

    private void encolarYMArcarVerticesIncidencia0(ListaEstatica incidencias, ListaEstatica marcados, ColaDinamica cola){
        for (int cadaVertice = 0; cadaVertice<incidencias.cantidad(); cadaVertice++){
            if (((int) incidencias.obtener(cadaVertice) == 0) && ((boolean) marcados.obtener(cadaVertice) == false)){
                cola.poner(cadaVertice);
                marcados.cambiar(cadaVertice, true);
            }
        }
    }

    //Paso 4 de OT
    private void recalcularIncidencias(ListaEstatica incidencias, int indiceOrigen, ListaEstatica marcados){
        for (int cadaDestino = 0; cadaDestino< aristas.obtenerColumnas(); cadaDestino++){
            Double flecha = (Double) aristas.obtener(indiceOrigen, cadaDestino);
            if (flecha != null && flecha > 0 && ((boolean) marcados.obtener(cadaDestino) == false)){ //hay adyacencia
                int incidenciaDestino = (int) incidencias.obtener(cadaDestino);
                incidencias.cambiar(cadaDestino, incidenciaDestino-1);
            }
        }
    }

    //calculo de primera, la ruta mas corta Dijkstra
    public ListaEstatica rutaMasCortaDijkstra(Object origen){
        ListaEstatica etiquetaOptimas = new ListaEstatica(vertices.cantidad());
        ListaEstatica marcados = new ListaEstatica(vertices.cantidad());
        //Paso 0. Determinar si el origen existe.
        Integer indiceOrigen = (Integer) vertices.buscar(origen);
        if (indiceOrigen == -1){
            return null;
        }
        double infinito = 0.0;
        if (tipoOrden == TipoOrden.DEC){
            infinito = Double.MAX_VALUE;
        }else{
            infinito = Double.MIN_VALUE;
        }
        //paso 1. Inicializar las etiquetas.
        inicializarEtiquetasOptimas(etiquetaOptimas, infinito, 0.0, -1, 0, indiceOrigen);
        marcados.rellenar(false, vertices.cantidad());
        marcados.cambiar(indiceOrigen, true);
        int indiceActual = indiceOrigen;
        for (int iteracion = 1; iteracion<vertices.cantidad(); iteracion++){
            //paso 2. Calcular la metrica acumulada del vertice actual
            //(el primer vertice actual es el origen) hacia cada vecino
            //de no marcados y si la metrica actual es mejor, se sobreescribe.
            calcularMetricasAcumuladasVecinas(etiquetaOptimas, indiceActual, marcados, infinito, iteracion);
            //paso 3. Buscar el vertice con la mejor metrica (mas chica
            //es mejor o mas grande es mejor, dependiendo) se marca y se
            //continue al vertice actual.
            indiceActual = actualizarVerticeActual(etiquetaOptimas, marcados, infinito); //Des
            marcados.cambiar(indiceActual, true);
        }
        return etiquetaOptimas;
    }

    //Paso 1.
    private void inicializarEtiquetasOptimas(ListaEstatica etiquetasOptimas, Double metricaVertices, Double metricaOrigen, int verticeAnteriorVertices, int iteracionInicial, int indiceVerticeOrigen){
        for (int cadaVertice = 0; cadaVertice<etiquetasOptimas.maximo(); cadaVertice++){
            EtiquetaGrafo etiquetaNueva = new EtiquetaGrafo();
            etiquetaNueva.setIteracion(iteracionInicial);
            etiquetaNueva.setMetricaAcumulada(metricaVertices);
            etiquetaNueva.setVerticeAnterior(verticeAnteriorVertices);
            etiquetasOptimas.agregar(etiquetaNueva);
        }
        //solo al origen le cambiaremos los valores propios
        EtiquetaGrafo etiquetaOrigen = (EtiquetaGrafo) etiquetasOptimas.obtener(indiceVerticeOrigen);
        etiquetaOrigen.setMetricaAcumulada(metricaOrigen);
    }
    //Paso 2.
    private void calcularMetricasAcumuladasVecinas(ListaEstatica etiquetasOptimas, int indiceActual, ListaEstatica marcados, double infinito, int iteracion){
        for (int cadaDestinoColumna = 0; cadaDestinoColumna<aristas.obtenerColumnas(); cadaDestinoColumna++){
            Double flechaAdyacencia = (Double) aristas.obtener(indiceActual, cadaDestinoColumna);
            if (flechaAdyacencia!=null && flechaAdyacencia != 0 && flechaAdyacencia != infinito && ((boolean) marcados.obtener(cadaDestinoColumna) == false)){
                EtiquetaGrafo etiquetaVerticeActual = (EtiquetaGrafo) etiquetasOptimas.obtener(indiceActual);
                double metricaVerticeActual = etiquetaVerticeActual.getMetricaAcumulada();
                double metricaAcumuladaOrigenDestino = metricaVerticeActual + flechaAdyacencia;
                EtiquetaGrafo etiquetaDestino = (EtiquetaGrafo) etiquetasOptimas.obtener(cadaDestinoColumna);
                boolean banderaSubstitucion = false;
                if (tipoOrden == TipoOrden.DEC){ //MAS PEQUENIO ES MEJOR
                    if (metricaAcumuladaOrigenDestino < etiquetaDestino.getMetricaAcumulada()){
                        //substituir
                        banderaSubstitucion = true;
                    }
                }else{ //MAS GRANDE ES MEJOR
                    if (metricaAcumuladaOrigenDestino > etiquetaDestino.getMetricaAcumulada()){
                        //substituir
                        banderaSubstitucion = true;
                    }
                }
                if (banderaSubstitucion == true){
                    etiquetaDestino.setMetricaAcumulada(metricaAcumuladaOrigenDestino);
                    etiquetaDestino.setIteracion(iteracion);
                    etiquetaDestino.setVerticeAnterior(indiceActual);
                }
            }
        }
    }

    //Paso 3.
    private int actualizarVerticeActual(ListaEstatica etiquetasOptimas, ListaEstatica marcados, double infinito){
        double mejor = infinito;
        int indiceMejor = -1; //no se conoce
        for (int cadaVerticeCandidato = 0; cadaVerticeCandidato<etiquetasOptimas.cantidad(); cadaVerticeCandidato++){
            if ((boolean) marcados.obtener(cadaVerticeCandidato) == false){ //si NO existe el nodo
                EtiquetaGrafo etiquetaCandidato = (EtiquetaGrafo) etiquetasOptimas.obtener(cadaVerticeCandidato);
                if (tipoOrden == TipoOrden.DEC){ //mas pequenio es mejor
                    if (etiquetaCandidato.getMetricaAcumulada() < mejor){
                        mejor = etiquetaCandidato.getMetricaAcumulada();
                        indiceMejor = cadaVerticeCandidato;
                    }
                } else {
                    if (etiquetaCandidato.getMetricaAcumulada() > mejor){
                        mejor = etiquetaCandidato.getMetricaAcumulada();
                        indiceMejor = cadaVerticeCandidato;
                    }
                }
            }
        }
        return indiceMejor;
    }

    public Double metricaRutaOptima(Object origen, Object destino){
        Integer indiceDestino = (Integer) vertices.buscar(destino);
        ListaEstatica etiquetasOptimas = rutaMasCortaDijkstra(origen);
        if (indiceDestino != -1 && etiquetasOptimas != null){
            EtiquetaGrafo etiquetaDestinoPedido = (EtiquetaGrafo) etiquetasOptimas.obtener(indiceDestino);
            return etiquetaDestinoPedido.getMetricaAcumulada();
        }else{
            return null;
        }
    }

    public ListaDinamica rutaOptima(Object origen, Object destino){
        ListaDinamica ruta = new ListaDinamica();
        Integer indiceDestino = (Integer) vertices.buscar(destino);
        ListaEstatica etiquetasOptimas = rutaMasCortaDijkstra(origen);
        if (indiceDestino != -1 && etiquetasOptimas != null){
            int indiceActual = indiceDestino;

            do { //recorrer del destino al origen
                Vertice verticeActual = (Vertice) vertices.obtener(indiceActual);
                ruta.agregarInicio(verticeActual.getInfo());
                EtiquetaGrafo etiquetaVerticeActual = (EtiquetaGrafo) etiquetasOptimas.obtener(indiceActual);
                indiceActual = etiquetaVerticeActual.getVerticeAnterior();
            } while (indiceActual != -1);
            return ruta;
        } else {
            return null;
        }
    }

    /**
     * Metodo que permite eliminar un vertice en ambos espacios de memoria.
     * @param vertice VerticeProbabilidad a eliminar.
     * @return Regresa el objeto eliminado.
     */
    public Object eliminarVertice(Object vertice){
        int indice = (int) vertices.buscar(vertice);
        Object respaldo = null;
        if (indice == -1){ //no esta el que se va a eliminar
            return null;
        } else { //ahora si eliminar el vertice
            respaldo = vertices.eliminar(vertice);
            Matriz2 nuevaMatriz = eliminarVerticeDelaMatriz(indice);
            aristas.redefinir(nuevaMatriz);
        }
        return respaldo;
    }

    /**
     * Metodo privado que hace los 'acomodos' en la matriz de aristas para evitar<br/>
     * tener incongruencias en los vertices
     * @param indiceADescartar Indice del vertice a eliminar.
     * @return Regresa una nueva matriz sin los elementos de dicho vertice.
     */
    private Matriz2 eliminarVerticeDelaMatriz(int indiceADescartar){
        Matriz2 retorno = new Matriz2(aristas.obtenerRenglones(), aristas.obtenerColumnas(), 0.0);
        int cadaRenglonretorno = 0;
        int cadaColumnaretorno = 0;
        for (int cadaRenglon = 0; cadaRenglon<aristas.obtenerRenglones(); cadaRenglon++){
            if (cadaRenglon != indiceADescartar){ //si el indice es diferente es al que se busca
                for (int cadaColumna = 0; cadaColumna<aristas.obtenerColumnas(); cadaColumna++){
                    if (cadaColumna != indiceADescartar){
                        retorno.cambiar(cadaRenglonretorno, cadaColumnaretorno, aristas.obtener(cadaRenglon, cadaColumna));
                        cadaColumnaretorno++;
                    }
                }
                cadaRenglonretorno++;
                cadaColumnaretorno = 0;
            }
        }
        return retorno;
    }

    /**
     * Metodo que verifica si dos vertices son adyacentes.
     * @param origen VerticeProbabilidad de origen.
     * @param destino VerticeProbabilidad de Destino.
     * @return Regresa True si es verdadero, y false si no son o
     * no se encontro alguno de los vertices.
     */
    public boolean esAdyacente(Object origen, Object destino){
        int indiceOrigen = (int) vertices.buscar(origen);
        int indiceDestino = (int) vertices.buscar(destino);
        if (indiceDestino != -1 && indiceOrigen != -1){
            double resultadoORDES = (double) aristas.obtener(indiceOrigen, indiceDestino);
            double resultadoDESOR = (double) aristas.obtener(indiceDestino, indiceOrigen);
            if (resultadoORDES > 0.0 || resultadoDESOR > 0.0){
                return true;
            }
            return false;
        } else {
            return false;
        }
    }

    /**
     * Metodo que elimina la arista dos vertices.
     * @param origen VerticeProbabilidad de origen.
     * @param destino VerticeProbabilidad de Destino.
     * @return Regresa True si se elimino, y false si no habia arista o
     * no se encontro alguno de los vertices.
     */
    public boolean eliminarArista(Object origen, Object destino){
        int indiceOrigen = (int) vertices.buscar(origen);
        int indiceDestino = (int) vertices.buscar(destino);
        if (indiceDestino != -1 && indiceOrigen != -1){
            double resultado = (double) aristas.obtener(indiceOrigen, indiceDestino); //solo verificar si hay arista
            if (resultado > 0.0){
                return aristas.cambiar(indiceOrigen, indiceDestino, 0.0);
            }
            return false;
        } else {
            return false;
        }
    }


    /**
     * Metodo que busca a un vertice en el grafo, y devuelve la info de este.
     * @param vertice VerticeProbabilidad a buscar.
     * @return Regresa la info si lo encuentra, null si no.
     */
    public Object buscarVertice(Object vertice){
        int indice = (int) vertices.buscar(vertice);
        if (indice != -1){
            Vertice respaldo = (Vertice) vertices.obtener(indice);
            return respaldo.getInfo();
        }else{
            return null;
        }
    }

    /**
     * Metodo que verifica si un grafo es pseudografo (Con todos los nodos)
     * @return Regresa true si es un pseudografo, false si no
     */
    public boolean esPseudografo(){
        boolean retorno = false;
        for (int cadaVertice = 0; cadaVertice<vertices.cantidad()-1; cadaVertice++){
            double resultado = (double) aristas.obtener(cadaVertice, cadaVertice);
            if (resultado > 0.0){
                retorno = true;
            }
        }
        return retorno;
    }

    /**
     * Metodo que verifica si un grafo es multigrafo.
     * @return Regresa true si es multigrafo, false si no.
     */
    public boolean esMultigrafo(){
        int cantidadVertices = 0;
        for (int cadaRenglon = 0; cadaRenglon<aristas.obtenerRenglones(); cadaRenglon++){
            for (int cadaColumna = 0; cadaColumna<aristas.obtenerColumnas(); cadaColumna++){
                if (cadaRenglon != cadaColumna){
                    double aristaObtenida = (double) aristas.obtener(cadaRenglon, cadaColumna);
                    double aristaContrariaObtenida = (double) aristas.obtener(cadaColumna, cadaRenglon);
                    if (aristaObtenida > 0.0 && aristaContrariaObtenida > 0.0){
                        cantidadVertices++;
                    }
                }
            }
        }
        if (cantidadVertices >= 4){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo que permite obtener el grado de un vertice.<br/>
     * (Numero de aristas de un vertice)
     * @param vertice VerticeProbabilidad a buscar.
     * @return Regresa el numero de vertices, o puede regresar -1 si el vertice no existe.
     */
    public int gradoVertice(Object vertice){
        int indice = (int) vertices.buscar(vertice);
        int grado = 0;
        if (indice != -1){
            for (int cadaDestino = 0; cadaDestino<aristas.obtenerRenglones(); cadaDestino++){
                double arista = (double) aristas.obtener(cadaDestino, indice);
                if (arista > 0.0){
                    grado++;
                }
            }
            for (int cadaOrigen = 0; cadaOrigen<aristas.obtenerColumnas(); cadaOrigen++){
                double arista = (double) aristas.obtener(indice, cadaOrigen);
                if (arista > 0.0){
                    grado++;
                }
            }
            return grado;
        }else{
            return -1; //No se encontro el vertice.
        }
    }

    /**
     * Metodo que verifica si hay una ruta de un vertice a otro.
     * @param origen VerticeProbabilidad de origen.
     * @param destino VerticeProbabilidad de destino.
     * @return Regresa true si hay una ruta especifica, false si no.
     */
    public boolean hayRuta(Object origen, Object destino){
        int indiceOrigen = (int) vertices.buscar(origen);
        int indiceDestino = (int) vertices.buscar(destino);
        if (indiceOrigen != -1 && indiceDestino!=-1){
            //1. verifiquemos si hay una ruta directa.
            double arista = (double) aristas.obtener(indiceOrigen, indiceDestino);
            if (arista > 0.0){
                return true;
            } else { //si no, hay que ver cual ruta es
                boolean hayRuta = false;
                int indiceAnterior = indiceDestino;
                for (int cadaOrigen = aristas.obtenerColumnas()-1; cadaOrigen>0; cadaOrigen--){
                    if (cadaOrigen == indiceAnterior){
                        for (int cadaDestinoVertice = 0; cadaDestinoVertice<aristas.obtenerRenglones(); cadaDestinoVertice++){
                            double aristaObtenida = (double) aristas.obtener(cadaDestinoVertice, indiceDestino);
                            if (aristaObtenida > 0.0){
                                hayRuta = true;
                                indiceAnterior = cadaDestinoVertice;
                            }
                        }
                        if (indiceAnterior == indiceOrigen){
                            return true;
                        }
                        if (hayRuta == false){
                            return false;
                        }
                    }
                }
                return hayRuta;
            }
        } else {
            SalidaPorDefecto.terminal(" Alguno de esos nodos no existe. ");
            return false;
        }
    }

    /**
     * Metodo que verifica si un grafo es Conexo.
     * @return Regresa true si si lo es, false si no.
     */
    public boolean esConexo(){
        boolean esConexo = false;
        for (int cadaDestino = 0; cadaDestino<aristas.obtenerRenglones(); cadaDestino++){
            for (int cadaOrigen = 0; cadaOrigen<aristas.obtenerColumnas(); cadaOrigen++){
                Vertice origen = (Vertice) vertices.obtener(cadaDestino);
                Vertice destino = (Vertice) vertices.obtener(cadaOrigen);
                boolean resultado = hayRuta(origen.getInfo(), destino.getInfo());
                if (resultado == true){
                    esConexo = true;
                } else {
                    esConexo = false;
                }
            }
        }
        return esConexo;
    }

    /**
     * Metodo que veririca si entre dos vertices hay un camino simple
     * @param origen VerticeProbabilidad de origen.
     * @param destino VerticeProbabilidad de destino.
     * @return Regresa true si si es, false si no.
     */
    public boolean esCaminoSimple(Object origen, Object destino){
        return hayRuta(origen, destino);
    }

    /**
     * Metodo que verifica si un grafo es dirigido.
     * @return Regresa true si es dirigido, false si no.
     */
    public boolean esDirigido(){
        boolean esDirigido = true;
        for (int cadaOrigen = 0; cadaOrigen<aristas.obtenerRenglones(); cadaOrigen++){
            for (int cadaDestino = 0; cadaDestino<aristas.obtenerColumnas(); cadaDestino++){
                double aristaOrigenDestino = (double) aristas.obtener(cadaOrigen, cadaDestino);
                double aristaDestinoOrigen = (double) aristas.obtener(cadaDestino, cadaOrigen);
                if (aristaOrigenDestino > 0.0 && aristaDestinoOrigen > 0.0){
                    esDirigido = true;
                } else {
                    if (aristaOrigenDestino != aristaDestinoOrigen){
                        return false;
                    }
                }
            }
        }
        return esDirigido;
    }

    /**
     * Metodo que verifica si un grafo es arbol.
     * @return Regresa true si lo es, false si no.
     */
    public boolean esArbol(){
        if (esConexo() == true && esPseudografo() == false){ //sin ciclos y conexo
            int contador = 0;
            boolean esArbol = true;
            for (int cadaDestino = aristas.obtenerColumnas()-1; cadaDestino>0; cadaDestino--){
                for (int cadaOrigen = 0; cadaOrigen<aristas.obtenerRenglones(); cadaOrigen++){
                    double arista = (double) aristas.obtener(cadaOrigen, cadaDestino);
                    if (arista > 0.0){
                        contador++;
                    }
                }
                if (contador > 1){
                    return false;
                } else {
                    contador = 0;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo que lista las aristas en una lista de todas las aristas.
     * @return Regresa una lista con todas las aristas, si no hubieron aristas,
     * se manda un nulo.
     */
    public ListaEstatica listarAristas(){
        ListaEstatica listaRetorno = new ListaEstatica(aristas.obtenerCantidadTotal());
        for (int cadaOrigen = 0; cadaOrigen<aristas.obtenerRenglones(); cadaOrigen++){
            for (int cadaDestino = 0; cadaDestino<aristas.obtenerColumnas(); cadaDestino++){
                ListaEstatica vertice = new ListaEstatica(3);
                Object origen = vertices.obtener(cadaOrigen);
                Object destino = vertices.obtener(cadaDestino);
                double arista = (double) aristas.obtener(cadaOrigen, cadaDestino);
                vertice.agregar(origen);
                vertice.agregar(destino);
                vertice.agregar(arista);
                listaRetorno.agregar(vertice);
            }
        }
        if (listaRetorno.vacia() == false) {
            return listaRetorno;
        } else {
            return null;
        }
    }


    /**
     * Metodo que guarda en una lista a las Aristas (con los pesos).
     * @param vertice VerticeProbabilidad a enlistar.
     * @return Lista Numerica que contiene los vertices, regresa null<br/>
     * si no encontro el vertice o si no hay aristas que agregar.
     */
    public ListaEstatica listarAristas(Object vertice){
        ListaNumerica listaRetorno = new ListaNumerica(aristas.obtenerColumnas());
        int indiceVertice = (int) vertices.buscar(vertice);
        if (indiceVertice != -1){
            for (int cadaDestino = 0; cadaDestino<aristas.obtenerColumnas(); cadaDestino++){
                double aristaObtenida = (double) aristas.obtener(indiceVertice, cadaDestino);
                if (aristaObtenida > 0.0){
                    listaRetorno.agregar(aristaObtenida);
                }
            }
            if (listaRetorno.vacia() == false){
                return listaRetorno;
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

    /**
     * Metodo que devuelve en una lista estatica a los vertices de la lista.
     * @return Devuelve una nueva lista a partir de la actual.
     */
    public ListaEstatica listarVertices(){
        ListaEstatica retorno = (ListaEstatica) vertices.clonar();
        return retorno;
    }

    /**
     * Regresa una copia de la matriz de aristas.
     * @return Regresa una matriz con las aristas.
     */
    public Matriz2 obtenerAristas(){
        Matriz2 retorno = aristas.clonar();
        return retorno;
    }


    /**
     * Busca simplemente el indice de una info.
     * @param info Info a buscar.
     * @return Regresa el indice buscado.
     */
    public int buscarIndice(Object info){
        return (int) vertices.buscar(info);
    }

    /**
     * Metodo que permite simplmente cambiar la info de un vertice dado por su info.
     * @param infoVieja Info Vieja
     * @param infoNueva Info nueva a cambiar.
     * @return Regresa true si se pudo cambiar, false si no.
     */
    public boolean cambiarInfoVertice(Object infoVieja, Object infoNueva){
        int indiceVertice = (int) vertices.buscar(infoVieja);
        if (indiceVertice == -1){
            return false;
        } else {
            Vertice verticeObtenido = (Vertice) vertices.obtener(indiceVertice);
            verticeObtenido.setInfo(infoNueva);
            return true;
        }
    }


    public Object obtenerVertice(int indice){
        Vertice verticeObtenido = (Vertice) vertices.obtener(indice);
        return verticeObtenido.getInfo();
    }
}

