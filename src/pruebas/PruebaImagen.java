package pruebas;

import entradasalida.SalidaPorDefecto;
import estructurasLineales.ListaNumerica;
import estructurasnolineales.Matriz2;
import imagenModificador.ModificadorImagenes;

public class PruebaImagen {
    public static void main(String[] args) {
        ModificadorImagenes imagenMod = new ModificadorImagenes("src/imagenModificador/eren.jpg");
        imagenMod.marco(20, 233, 210, 177);

    }
}
