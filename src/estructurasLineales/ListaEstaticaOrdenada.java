package estructurasLineales;

import entradasalida.SalidaPorDefecto;
import utilerias.comunes.Comparadores;
import utilerias.comunes.TipoOrden;

public class ListaEstaticaOrdenada extends ListaEstatica{
    protected TipoOrden orden;

    public ListaEstaticaOrdenada(int tamanio, TipoOrden orden) {
        super(tamanio);
        this.orden = orden;
    }


    //@Override - No se pq pero este metodo raro agregaba... de una manera mas complicada ( -_-')
    public int agregar2(Object info){
        if (orden.getNumeroOrden() == 1){
            int posicionAgregar = 0;
            if (llena() == false){
                for(int buscarPosicion = 0; buscarPosicion <= tope; buscarPosicion++){
                    Integer resultado = Comparadores.comparar(info, informacion[buscarPosicion]);
                    if (resultado > 0){
                        posicionAgregar = buscarPosicion;
                        break;
                    }
                } // si no entro al for es que tope es de -1 o simplemente sale
                if (informacion[posicionAgregar+1] != null){
                    tope = tope + 1;
                    for (int copiarElementos = tope; copiarElementos > posicionAgregar; copiarElementos--){
                        informacion[copiarElementos] = informacion[copiarElementos-1];
                    }
                    informacion[posicionAgregar] = info;
                    return posicionAgregar;
                } else {
                    tope = tope+1;
                    informacion[tope] = info;
                    return posicionAgregar;
                }
            }
        } else {// si es orden decremental
            if (llena() == false){
                int posicionAguardar = 0;
                for (int buscarPosicion = 0; buscarPosicion<=tope; buscarPosicion++){
                    Integer resultadoComparar = Comparadores.comparar(info, informacion[buscarPosicion]);
                    if (resultadoComparar > 0){ //si el objeto es mayor
                        posicionAguardar = buscarPosicion;
                        SalidaPorDefecto.terminal("La posicion a guardar es "+posicionAguardar);
                        break;
                    }
                }
                tope = tope+1;
                if (informacion[posicionAguardar+1] != null){
                    for (int copiarDatos = tope; copiarDatos < posicionAguardar; copiarDatos--){ //recorrer datos
                        informacion[copiarDatos+1] = informacion[copiarDatos];
                    }
                } else {
                    informacion[posicionAguardar] = info;
                    return posicionAguardar;
                }
            }
        }
        return -1;
    }

    /**
     * Agrega un elemento a una lista y lo ordena en su lugar
     * @param info Es el nuevo valor a añadir.
     * @return Regresa la posicion donde se agrego el objeto.
     */
    @Override
    public int agregar(Object info){
        if (llena() == false){
            int posicion = (int) buscar(info);
            if (posicion < 0){
                tope = tope + 1;
                posicion = posicion * -1;
                posicion = posicion - 1;
                for (int movs = tope; movs>posicion; movs--){
                    informacion[movs] = informacion[movs-1];
                }
                informacion[posicion] = info;
                return posicion;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }

    /**
     * Metodo que busca en un arreglo un objeto.
     * @param info Valor a buscar.
     * @return Siempre y cuando se encuentre, se envia un numero positivo indicando su posicion
     */
    @Override
    public Object buscar(Object info){
        int posicion = 0;
        while((posicion <= tope) && (Comparadores.comparar(info, informacion[posicion]) == 1)){
            posicion = posicion+1;
        }
        if ((posicion > tope) || (Comparadores.comparar(info, informacion[posicion]) == -1)){
            return (posicion + 1) * (-1);
        } else {
            return (posicion + 1);
        }
    }

    //@Override
    public Object buscar2(Object info){
        if (orden.getNumeroOrden() == 1){
            for (int posicion = 0; posicion <= tope; posicion++) {
                int resultado = Comparadores.comparar(info, informacion[posicion]);
                if (resultado == 0){
                    return posicion;
                } else
                if (resultado > 0){
                    return (posicion*-1);
                }
            }
        } else {
            for (int posicion = tope; posicion <= 0; posicion--){
                int resultado = Comparadores.comparar(info, informacion[posicion]);
                if (resultado == 0){
                    return posicion;
                }
                if (resultado > 0){
                    return (posicion*-1);
                }
            }
        }
        return -1;
    }

    /**
     * Metodo que cambia un arreglo y lo ordena
     * @param indice Se ingresa un entero como valor indice del arreglo.
     * @param info Se ingresa el objeto el cual va a ser cambiado.
     * @return se regresa true, si se cambio, false, si no se pudo.
     */
    public boolean cambiar(int indice, Object info){
        if ((indice >=0 )||(indice<=tope)){
            Object respaldo = informacion[indice];
            informacion[indice] = info;
            for(int posicion = 0; posicion < tope; posicion++){
                int resultado = (int) Comparadores.comparar(informacion[indice-1], informacion[indice]);
                if (resultado > 0){
                    Object aux = informacion[indice];
                    informacion[indice+1] = informacion[indice];
                    informacion[indice] = aux;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Invierte un arreglo ordenado a la vez de invertir su tipo de orden.
     */
    public void invertir(){
        ListaEstatica listaAuxiliar = new ListaEstatica(MAXIMO);
        for (int posicionUltima = (MAXIMO-1); posicionUltima >= 0; posicionUltima--){
            listaAuxiliar.agregar(informacion[posicionUltima]);
        }
        for(int posicionPrimera = 0; posicionPrimera<MAXIMO; posicionPrimera++) {
            informacion[posicionPrimera] = listaAuxiliar.informacion[posicionPrimera];
        }
        if (orden.getNumeroOrden() == 1){
            orden = TipoOrden.DEC;
        } else {
            orden = TipoOrden.INC;
        }
    }

    /**
     * Elimina un elemento que este dentro de la array.
     * @param info Objeto que se desea eliminar.
     * @return Se regresa el objeto eliminado, si no esta se devuelve un nulo.
     */
    @Override
    public Object eliminar(Object info){
        int posicion = (int) buscar(info);
        if (posicion >0){ //si si lo encontro
            posicion = posicion - 1;
            Object respaldo = informacion[posicion];
            tope = tope - 1;
            for (int movs = posicion; movs <= tope; movs++){
                informacion[movs] = informacion[movs+1];
            }
            return respaldo;
        } else {
            return null;
        }
    }

    /**
     * Metodo que agrega una lista al final de una array
     * @param lista2 Recibe un argumento tipo ListaOrdenada.
     * @return regresa true si si se agrego.
     */
    public boolean agregarLista(Lista lista2){
        if(lista2 instanceof ListaEstaticaOrdenada){
            ListaEstaticaOrdenada listaDos = (ListaEstaticaOrdenada) lista2;
            if (( ((ListaEstaticaOrdenada) lista2).MAXIMO) > (MAXIMO - tope)){
                MAXIMO = MAXIMO + ((ListaEstaticaOrdenada) lista2).MAXIMO;
                SalidaPorDefecto.terminal("\nentro a la condicion1 con un MAXIMO de "+MAXIMO);
            }
            for (int posicionLista2 = 0; posicionLista2 <= ((ListaEstaticaOrdenada) lista2).tope; posicionLista2++){
                agregar(((ListaEstaticaOrdenada) lista2).informacion[posicionLista2]);
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Devuelve una lista desordenada
     * @return Lista desordenada
     */
    public Lista arregloDesordenado(){
        Lista nuevaLista = new ListaEstatica(MAXIMO);
        for (int posicion = 0; posicion <= tope; posicion++){
            if ((posicion % 2) == 0){
                nuevaLista.agregar(informacion[posicion]);
            } else {
                int numeroAtras = tope - posicion;
                nuevaLista.agregar(informacion[numeroAtras]);
            }
        }
        return nuevaLista;
    }



    /**
     * Metodo que retiene la informacion de una lista 2 en la lista actual.
     * @param lista2 valor del objeto lista2
     * @return regresa true si se ha eliminado, false, si no.
     */
    public boolean retenerLista(Lista lista2){
        if(lista2 instanceof ListaEstatica){
            for (int posicionLista2 = 0; posicionLista2 < lista2.maximo(); posicionLista2++){
                for (int posicionListaActual = 0; posicionListaActual <= tope; posicionListaActual++){
                    if ( !((ListaEstaticaOrdenada)lista2).informacion[posicionLista2].equals(informacion[posicionListaActual]) ){
                        eliminar(informacion[posicionListaActual]);
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public Object verUltimo(){
        return null;
    }

}
