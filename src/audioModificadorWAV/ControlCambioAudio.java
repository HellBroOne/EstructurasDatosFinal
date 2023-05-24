package audioModificadorWAV;

import entradasalida.SalidaPorDefecto;
import estructurasLineales.ListaNumerica;
import utilerias.archivosAudio.AudioFileRecord;

public class ControlCambioAudio {
    protected ListaNumerica tramas;
    protected AudioFileRecord audioGrabadora;

    /**
     * Constructor por defecto de la clase ControlCambioAudio
     * @param archivo Solo se requiere pasar una cadena que contenga la ruta del archivo.
     */
    public ControlCambioAudio(String archivo){
        audioGrabadora = new AudioFileRecord(archivo);
        audioGrabadora.leerAudio();
        tramas = audioGrabadora.obtenerListaDeTramas();
    }

    /**
     * Aplique un filtro FIR de primer orden en altas frecuencias.
     */
    public void preEnfasis(){
        for (int posicionDelArreglo = 0; posicionDelArreglo < tramas.maximo(); posicionDelArreglo++){
            if (posicionDelArreglo > 0){
                double xarreglo = (double) tramas.obtener(posicionDelArreglo);
                double resultadoAlfa = 0.96 * (double) tramas.obtener(posicionDelArreglo - 1);
                double resultado = xarreglo + resultadoAlfa;
                tramas.cambiar(posicionDelArreglo, resultado);
            }
        }
        audioGrabadora.agregarListaDeTramas(tramas);
        audioGrabadora.EscribirAudio();
    }

    /**
     * Metodo que sube el volumen al audio.
     * @param intensidad Numero de Volumen a subir.
     */
    public void subirVolumen(int intensidad){
        audioGrabadora.subirVolumen(intensidad);
        audioGrabadora.agregarListaDeTramas(tramas);
        audioGrabadora.EscribirAudio();
    }

    /**
     * Metodo que baja el volumen al audio.
     * @param intensidad Numero de Volumen a bajar.
     */
    public void bajarVolumen(int intensidad){
        audioGrabadora.bajarVolumen(intensidad);
        audioGrabadora.agregarListaDeTramas(tramas);
        audioGrabadora.EscribirAudio();
    }

    /**
     * Cree un arreglo nuevo el cual contendra los datos de un audio acelerado.
     * @param velocidad Velocidad a la que se requiere acelerar.
     */
    public void acelerar(int velocidad){
        int tamanioNuevoArreglo = (tramas.maximo()/velocidad);
        audioGrabadora.setNumFrames(audioGrabadora.getNumFrames()/velocidad);
        ListaNumerica arregloAcelerado = new ListaNumerica(tamanioNuevoArreglo); //dividir la tasa de frames
        for (int posicionesInformacion = 0; posicionesInformacion<tramas.maximo(); posicionesInformacion++){
            if (((posicionesInformacion % 2)!=0) && (posicionesInformacion>0)){
                double promedio = (((double) tramas.obtener(posicionesInformacion)) + ((double) tramas.obtener(posicionesInformacion-1)))/2;
                if (arregloAcelerado.llena() == false){
                    arregloAcelerado.agregar(promedio);
                }
            }
        }
        audioGrabadora.escribirAudioPorlista(arregloAcelerado);
    }

    /**
     * Metodo que realentiza la voz a una velocidad dada
     * @param velocidad
     */
    public void retrasar(int velocidad){
        int tamanioNuevoArreglo = (tramas.maximo()*velocidad);
        audioGrabadora.setNumFrames(audioGrabadora.getNumFrames()*velocidad); //multiplicar la tasa de frames
        ListaNumerica arregloAtrasado = new ListaNumerica(tamanioNuevoArreglo);
        tramas.agregarTamanioNuevosArreglos(tamanioNuevoArreglo);
        int posicionArregloAtrasado = 0;
        for (int posicionArregloNuevo = 0; posicionArregloNuevo <= arregloAtrasado.cantidad(); posicionArregloNuevo++){ //recorremos el arreglo atrasado y vamos agregando
            if(posicionArregloNuevo%2 == 0){
                arregloAtrasado.agregar(tramas.obtener(posicionArregloAtrasado));
                posicionArregloAtrasado++;
            }else{
                if (posicionArregloNuevo == (arregloAtrasado.maximo()-1)){
                    double promedio = ( ((double) tramas.obtener(posicionArregloAtrasado-1)) + ((double) tramas.obtener(posicionArregloAtrasado-2)) )/2;
                    arregloAtrasado.agregar(promedio);
                } else {
                    double promedio = ( ((double) tramas.obtener(posicionArregloAtrasado)) + ((double) tramas.obtener(posicionArregloAtrasado-1)) )/2;
                    arregloAtrasado.agregar(promedio);
                }
            }
        }
        //arregloAtrasado.imprimir();
        audioGrabadora.escribirAudioPorlista(arregloAtrasado);
    }

    /**
     * Metodo que elimina una mayor parte del silencio en el audio.
     */
    public void eliminarSilencio(){
        int nuevasMuestras = 0;

        for(int numeroTrama = 0; numeroTrama<tramas.maximo(); numeroTrama++){
            double tramaElegida = ((double) tramas.obtener(numeroTrama));
            if ( ! (tramaElegida <= 1.0) || (tramaElegida >= -1.0) ){
                nuevasMuestras = nuevasMuestras + 1;
            }
        }
        ListaNumerica nuevasTramas = new ListaNumerica(nuevasMuestras);
        for (int posicionTramasNuevas = 0; posicionTramasNuevas<tramas.maximo(); posicionTramasNuevas++){
            double tramaAComparar = ((double) tramas.obtener(posicionTramasNuevas));
            if((tramaAComparar <= 1.0) || (tramaAComparar >= -1.0)){
                nuevasTramas.agregar((double) tramas.obtener(posicionTramasNuevas));
            }
        }
        audioGrabadora.escribirAudioPorlista(nuevasTramas);
    }

    /**
     * Metodo que hace que la voz suene aca bien demoniaca (Invierte el audio).
     */
    public void invertirEjeX(){
        tramas.invertir();
        audioGrabadora.agregarListaDeTramas(tramas);
        audioGrabadora.EscribirAudio();
    }

    /**
     * Metodo que invierte los sonidos en el eje Y
     */
    public void invertirEjeY(){
        tramas.porEscalar(-1.0);
        audioGrabadora.agregarListaDeTramas(tramas);
        audioGrabadora.EscribirAudio();
    }

    /**
     * Metodo que devuelve en double el valor de la energia de los audios
     * @return Se regresa el valor de la energia
     */
    public double obtenerEnergia(){
        double energia = tramas.productoEscalar(tramas);
        return energia;
    }
}
