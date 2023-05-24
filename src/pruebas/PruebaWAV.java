package pruebas;

import audioModificadorWAV.ControlCambioAudio;
import entradasalida.SalidaPorDefecto;
import estructurasLineales.ListaNumerica;
import utilerias.archivosAudio.AudioFileRecord;

public class PruebaWAV {
    public static void main(String[] args) {
        ControlCambioAudio control = new ControlCambioAudio("src/utilerias/prueba.wav");
        control.acelerar(6);
    }
}
