package utilerias.archivosAudio;

import java.io.*;

import entradasalida.SalidaPorDefecto;
import estructurasLineales.ListaNumerica;
import utilerias.archivosAudio.wavfile.WavFile;

public class AudioFileRecord {
    private long numFrames;  //numero de tramas, número de muestras totales del archivo por canal
    private long sampleRate; //numero de muestras por segundo a la que se discretiza la señal
    private int numChannels; //número de canales
    private double[] buffer; //audio original
    private double[] buffer2; //audio modificado en lista estatica numerica
    private String archivo;   //nombre de archivo dado por el usuario
    private WavFile wavFileR; //apuntador de archivo leido
    private WavFile wavFileW;  //apuntador de archivo a escribir
    private String nomArchivo; //nombre archivo (uno.wav)
    private String nomRuta;    //ruta donde esta guardado el archivo (/home)
    private int validBits;     //bits usados para la discretización
    private Object[] copiaTemporal;
    private double[] bufferNuevo; //buffer auxiliar

    protected ListaNumerica listaTramas;

    public AudioFileRecord(String archivo) {
        this.archivo = archivo;
        // Abre el archivo wav y asigna parámetros a las variables
        try {
            File file = new File(archivo);
            wavFileR = WavFile.openWavFile(file);
            nomArchivo = file.getName();
            nomRuta = file.getParent();
        } catch (Exception e) {

        }
        numChannels = wavFileR.getNumChannels();
        numFrames = wavFileR.getNumFrames();
        sampleRate = wavFileR.getSampleRate();
        validBits=wavFileR.getValidBits();

    }

    public void leerAudio() {
        try {

            // Muestra datos del archivo
            wavFileR.display();

            // Crea buffer de origen y de temporal
            buffer = new double[(int) numFrames * numChannels];
            buffer2 = new double[buffer.length];
            listaTramas = new ListaNumerica(buffer2.length);
            //tramas leidas
            int framesRead;

            // Lee tramas totales
            framesRead = wavFileR.readFrames(buffer, (int) numFrames);

            // Recorrer todas las tramas del archivo y guardarlas en el arreglo.
            for (int s = 0; s < framesRead * numChannels; s++) {
                buffer2[s] = buffer[s];
                //SalidaPorDefecto.terminal("Trama "+s+": ");
                SalidaPorDefecto.terminal(buffer2[s]+"\n"); //Solo para pruebas :)
            }
            // Guardar las tramas al arreglo de Lista numerica
            listaTramas.guardarDatosAlArreglo(buffer2);

            // Cierra archivo
            wavFileR.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void EscribirAudio() {
        //Inicializamos el valor de copiaTemporal
        copiaTemporal = listaTramas.leerArreglo();

        //Sobreescribimos el contenido de buffer 2
        for (int posicionArreglo = 0; posicionArreglo<copiaTemporal.length; posicionArreglo++){
            buffer2[posicionArreglo] = ((double) copiaTemporal[posicionArreglo]);
            //Solo para consultar los datos SalidaPorDefecto.terminal(buffer2[posicionArreglo]+"\n");
        }

        try {
            //Crear el apuntador al archivo para guardar datos del temporal
            File file = new File(nomRuta + "/2_" + nomArchivo);

            // Creamos un nuevo archivo de audio con los mismos datos que el original
            wavFileW = WavFile.newWavFile(file, numChannels, numFrames, validBits, sampleRate);

            // Escribimos los frames o muestras totales del temporal
            wavFileW.writeFrames(buffer2, (int) numFrames);

            // Cerramos el archivo
            wavFileW.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void escribirAudioPorlista(ListaNumerica lista) {
        //Inicializamos el valor de copiaTemporal
        copiaTemporal = lista.leerArreglo();
        bufferNuevo = new double[copiaTemporal.length];

        //Sobreescribimos el contenido de buffer 2
        for (int posicionArreglo = 0; posicionArreglo<copiaTemporal.length; posicionArreglo++){
            bufferNuevo[posicionArreglo] = ((double) copiaTemporal[posicionArreglo]);
            // Solo para datos SalidaPorDefecto.terminal(bufferNuevo[posicionArreglo]+"\n");
        }

        try {
            //Crear el apuntador al archivo para guardar datos del temporal
            File file = new File(nomRuta + "/2_" + nomArchivo);

            // Creamos un nuevo archivo de audio con los mismos datos que el original
            wavFileW = WavFile.newWavFile(file, numChannels, numFrames, validBits, sampleRate);

            // Escribimos los frames o muestras totales del temporal
            wavFileW.writeFrames(bufferNuevo, (int) numFrames);

            // Cerramos el archivo
            wavFileW.close();
        } catch (Exception e) {
            SalidaPorDefecto.terminal("\nAudio creado.");
            //System.err.println(e);
        }
    }

    public ListaNumerica obtenerListaDeTramas(){
        return listaTramas;
    }

    public void agregarListaDeTramas(ListaNumerica lista){
        listaTramas = lista;
    }

    public void subirVolumen(int intensidad){
        double volumen = (double) intensidad;
        listaTramas.sumarEscalar(volumen);
    }
    public void bajarVolumen(int intensidad){
        double volumen = (double) intensidad;
        listaTramas.porEscalar((1/volumen));
    }

    public long getNumFrames() {
        return numFrames;
    }

    public void setNumFrames(long numFrames) {
        this.numFrames = numFrames;
    }

    public int getNumChannels() {
        return numChannels;
    }

    public void setNumChannels(int numChannels) {
        this.numChannels = numChannels;
    }
}
