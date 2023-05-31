package estructurasLineales;

import entradasalida.SalidaPorDefecto;

public class ListaEstatica implements VectorLista{
    protected int tope;
    protected int MAXIMO;
    protected Object informacion[];

    public ListaEstatica(int tamanio){
        MAXIMO = tamanio;
        informacion = new Object[tamanio];
        tope = -1;
    }

    @Override
    public boolean vacia(){
        if(tope == -1){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean llena() {
        if(tope == (MAXIMO-1) ){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int agregar(Object info) {
        if(llena() == false){ // si hay espacio
            tope = tope+1;
            informacion[tope] = info;
            return tope;
        } else { // no hay espacio
            return -1;
        }
    }

    @Override
    public void imprimir() {
        for (int celda = tope; celda>=0; celda--){
            SalidaPorDefecto.terminal(informacion[celda] + "\n");
        }
    }

    @Override
    public void imprimirOI() {
        for (int celda = 0; celda<=tope; celda++){
            SalidaPorDefecto.terminal(informacion[celda] + "\n");
        }
    }

    /**
     * Metodo que busca la posicion de un objeto.
     * @param info Valor a buscar.
     * @return Regresa un 'Objeto' de tipo Entero con la posicion del elemento buscado.
     */
    @Override
    public Object buscar(Object info) {
        int posicion = 0;
        while (posicion<=tope && (info.toString().equalsIgnoreCase(informacion[posicion].toString())) == false){
            posicion = posicion + 1;
        }
        if (posicion>tope){
            return -1; //si no se encuentra devuelve -1
        }else{
            return posicion; //si se encuentra, se devuelve la posicion
        }
    }

    @Override
    public Object eliminar(Object info) {
        int posicion = (Integer) buscar(info); //busca el valor, luego, el resultado lo convierte a entero
        if (posicion >= 0){ // si el valor que resulto de buscar es mayor a 0, significa que si esta en lista
            Object eliminado = informacion[posicion]; // se accede a la posicion del buscado y se guarda en eliminado
            tope = tope-1; // se baja el tope para no acceder al dato anterior
            for (int movs = posicion; movs <= tope; movs++){ // se recorren
                informacion[movs] = informacion[movs+1];
            }
            return eliminado;
        }else{
            return null; //no esta
        }
    }

    @Override
    public boolean esIgual(Object lista2) {
        if (lista2 instanceof ListaEstatica){ //mientras sea lista estatica
            ListaEstatica lista2estatica = (ListaEstatica) lista2;
            int contadorVerificar = 0;
            for (int posicionComparar = 0; posicionComparar <= (MAXIMO-1); posicionComparar++){ //este for lee la posicion de la lista info
                if ( (informacion[posicionComparar]).equals(lista2estatica.informacion[posicionComparar]) ){ //se comparan ambos datos de las posiciones
                    contadorVerificar = contadorVerificar + 1;
                };
            }

            if (contadorVerificar == MAXIMO){
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * Este metodo regresa "Verdadero" si el indice esta en
     * @param indice
     * @return
     */
    public boolean validarRango(int indice){
        if ((indice) >= 0 && (indice) <= tope){
            return true;
        }
        return false;
    }

    @Override
    public Object obtener(int indice){
        if ((indice) >= 0 && (indice) <= tope){ //verificamos que indice este en una posicion que tenga datos
            Object objetoObtenido;
            objetoObtenido = informacion[indice];
            return objetoObtenido;
        }
        return null;
    }

    @Override
    public boolean cambiar(Object infoViejo, Object infoNuevo, int numVeces) {
        int numeroObjetoEjemplo = (Integer) buscar(infoViejo);
        Object objetoEjemplo = informacion[numeroObjetoEjemplo];
        int contadorVecesCambiadas = 0;
        for (int hacerCambio = 1; hacerCambio<numVeces; hacerCambio++){
            for (int posicion = 0; posicion <= tope; posicion++){
                if ( (objetoEjemplo.equals(informacion[posicion])) == true ){ //Si el objeto ejemplar es igual al que esta en la posicion de la lista
                    informacion[posicion] = infoNuevo; //cambiamos el objeto en esa posicion por el nuevo
                    contadorVecesCambiadas = contadorVecesCambiadas + 1;
                }
            }
        }
        if (numVeces <= contadorVecesCambiadas){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean cambiar(int indice, Object info){
        if ( !(informacion[indice] == null) ){
            Object objetoEliminado = informacion[indice];
            informacion[indice] = info;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean cambiarListaEstatica(ListaEstatica indicesBusqueda, ListaEstatica infosNuevos){
        return false;
    }


    @Override
    public ListaEstatica buscarValores(Object info) {
        int contadorDeInfos = 0;
        Object agregadoANuevaLista = null;
        ListaEstatica listaRegresada;
        for(int posicion = 0; posicion<= tope; posicion++){
            if ( (informacion[posicion].equals(info)) == true ){
                contadorDeInfos = contadorDeInfos + 1;
                agregadoANuevaLista = informacion[posicion];
            }
        }
        listaRegresada = new ListaEstatica(contadorDeInfos);
        for (int agregador = 0; agregador<=contadorDeInfos; agregador++){
            listaRegresada.agregar(agregadoANuevaLista);
        }
        return listaRegresada;
    }

    @Override
    public Object eliminar(int indice){
        Object objetoRegresado;
        objetoRegresado = informacion[indice];
        eliminar(informacion[indice]);
        return objetoRegresado;
    }

    @Override
    public Object eliminar() {
        if(vacia() == false){
            Object valorRegreso = informacion[tope];
            eliminar(informacion[tope]);
            return valorRegreso;
        } else {
            return null;
        }
    }

    @Override
    public void vaciar() {
        tope = -1;
    }

    @Override
    public boolean agregarLista(Object lista2) {
        if (lista2 instanceof ListaEstatica){
            int tamanioNuevo = ((ListaEstatica) lista2).maximo();
            ListaEstatica listaEstatica2 = ((ListaEstatica) lista2);
            ListaEstatica nuevaLista = new ListaEstatica(MAXIMO+tamanioNuevo);
            for (int posicion = 0; posicion <= MAXIMO; posicion++){
                nuevaLista.agregar(informacion[posicion]);
            }
            for (int posicion = 0; posicion <= tamanioNuevo; posicion++){
                nuevaLista.agregar(listaEstatica2.informacion[posicion]);
            }
            return true;
        }
        return false;
    }

    @Override
    public void invertir() {
        ListaEstatica listaAuxiliar = new ListaEstatica(MAXIMO);
        for (int posicionUltima = (MAXIMO-1); posicionUltima >= 0; posicionUltima--){
                listaAuxiliar.agregar(informacion[posicionUltima]);
        }
        for(int posicionPrimera = 1; posicionPrimera<MAXIMO; posicionPrimera++) {
                informacion[posicionPrimera] = listaAuxiliar.informacion[posicionPrimera];
        }
    }

    @Override
    public int contar(Object info) {
        int contador = 0;
        for (Object buscar: informacion) {
            if (info.equals(buscar)){
                contador = contador + 1;
            }
        }
        return contador;
    }

    @Override
    public boolean eliminarLista(Object lista2) {
        if (lista2 instanceof ListaEstatica){
            ListaEstatica listaEstatica2 = ((ListaEstatica) lista2);
            for (int posicion = 0; posicion <= (listaEstatica2.MAXIMO); posicion++){
                Object comparadoDeLista2 = listaEstatica2.informacion[posicion];
                for (Object comparadoDeInformacion: informacion) {
                    if ((comparadoDeLista2.equals(comparadoDeInformacion)) == true){
                        eliminar(comparadoDeInformacion);
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public void rellenar(Object info, int cantidad) {
        if (cantidad > MAXIMO){
            cantidad = MAXIMO;
        }
        for (int rellenaContador = 0; rellenaContador < cantidad; rellenaContador++){
            informacion[rellenaContador] = info;
            tope++;
        }
    }

    @Override
    public Lista clonar() {
        ListaEstatica nuevaLista = new ListaEstatica(MAXIMO);
        for (Object clonarObjeto: informacion) {
            nuevaLista.agregar(clonarObjeto);
        }
        return nuevaLista;
    }

    @Override
    public Lista subLista(int indiceInicial, int indiceFinal) {
        ListaEstatica listaRegresada;
        int contadorDeTamanio = 0;
        for(int contadorlista = indiceInicial; contadorlista <= indiceFinal; contadorlista++){
            contadorDeTamanio = contadorDeTamanio + 1;
        }
        listaRegresada = new ListaEstatica(contadorDeTamanio);
        for (int contadoragregar = indiceInicial; contadoragregar <= indiceFinal; contadoragregar++){
            listaRegresada.agregar(informacion[contadoragregar]);
        }
        return listaRegresada;
    }

    @Override
    public boolean redimensionar(int maximo) {
        if (maximo > MAXIMO){
            MAXIMO = maximo;
        }
        if (maximo < MAXIMO){
            int elementosABorrar = MAXIMO - maximo;
            for (int posicionBorrar = MAXIMO; posicionBorrar >= maximo; posicionBorrar++){
                eliminar(informacion[posicionBorrar]);
            }
        }
        return false;
    }

    @Override
    public int maximo(){
        return MAXIMO;
    };
    @Override
    public int cantidad(){
        return tope+1;
    }

    @Override
    public Object verUltimo() {
        if (vacia() == false){
            return informacion[tope];
        }else{
            return null;
        }
    }

    /**
     * Metodo que agrega los datos de un buffer de tramas a una lista estatica.
     * @param buffer Arreglo tipo double del cual se van a copiar datos.
     */
    public void guardarDatosAlArreglo(double[] buffer){
        for (int posicionEnElBuffer = 0; posicionEnElBuffer < buffer.length; posicionEnElBuffer++){
            agregar(buffer[posicionEnElBuffer]);
        }
    }

    /**
     * Este metodo regresa una copia del arreglo de informacion en un arreglo de Object variable.
     * @return Regreso del arreglo leido.
     */
    public Object[] leerArreglo() {
        Object[] arregloCopia = new Object[MAXIMO]; //Inicializamos el arreglo a copiar.
        for (int posicionInformacion = 0; posicionInformacion < MAXIMO; posicionInformacion++){
            arregloCopia[posicionInformacion] = informacion[posicionInformacion];
        }
        return arregloCopia;
    }

    /**
     * Metodo no implementado
     * @return
     */
    @Override
    public Object eliminarInicio() {
        return null;
    }
}
