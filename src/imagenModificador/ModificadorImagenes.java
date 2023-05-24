package imagenModificador;

import entradasalida.SalidaPorDefecto;
import estructurasLineales.ListaNumerica;
import estructurasnolineales.Matriz2;
import estructurasnolineales.Matriz2Numerica;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ModificadorImagenes {
    protected String archivo;
    protected BufferedImage imagen; //imagen original
    protected BufferedImage imagen2; //imagen a copiar
    protected Matriz2Numerica pixeles;
    protected Matriz2 valoresCanales;
    protected int w;
    protected int h;

    /**
     * Constructor de la clase Modificadora de Imagenes.
     * @param archivo Ruta de la imagen a modificar.
     */
    public ModificadorImagenes(String archivo){
        this.archivo = archivo;
        try {
            imagen = ImageIO.read(new File(archivo)); //lee la imagen
            w = imagen.getWidth(); //obtiene el ancho
            h = imagen.getHeight(); //obtiene la altura
            guardarPixelesAMatriz();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Metodo que guarda los pixeles de la matriz en una nueva.
     */
    public void guardarPixelesAMatriz(){
        pixeles = new Matriz2Numerica(imagen.getWidth(), imagen.getHeight());
        for (int height = 0; height<imagen.getWidth(); height++){
            for (int width = 0; width<imagen.getHeight(); width++){
                int pixel = imagen.getRGB(width, height);
                pixeles.cambiar(height, width, pixel);
            }
        }
    }

    /**
     * Metodo que copia la imagen a una nueva imagen.
     */
    public void copiarImagen(){
        imagen2 = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB); //Propiedades de la imagen nueva
        for (int height = 0; height< imagen.getHeight(); height++){
            for (int width = 0; width<imagen.getWidth(); width++){
                int pixel = imagen.getRGB(height, width);
                imagen2.setRGB(height, width, pixel);
            }
        }
        guardarImagen();
    }

    protected void guardarDatosMatrizAImagen2(){
        imagen2 = new BufferedImage(pixeles.obtenerColumnas(), pixeles.obtenerRenglones(), BufferedImage.TYPE_INT_RGB); //Propiedades de la imagen nueva
        for (int height = 0; height< pixeles.obtenerRenglones(); height++){
            for (int width = 0; width<pixeles.obtenerColumnas(); width++){
                int pixel = (int) ((double) pixeles.obtener(height, width));
                imagen2.setRGB(width, height, pixel);
            }
        }
        guardarImagen();
    }

    /**
     * Metodo que exporta la imagen a una nueva.
     */
    public void guardarImagen(){
        try {
            ImageIO.write(imagen2, "JPG", new File("src/imagenModificador/nueva.jpg"));
            SalidaPorDefecto.terminal("\nImagen guardada exitosamente.\n");
        } catch (IOException e) {
            System.err.println("Error: "+e);
            e.printStackTrace();
        }
    }

    /**
     * Metodo que aumenta o disminuye el brillo de una imagen
     * @param brillo Numero de brillo a subir.
     */
    public void brilloImagen(int brillo){
        extraerCanalesDePixeles();
        for (int cadaRenglon = 0; cadaRenglon<valoresCanales.obtenerRenglones(); cadaRenglon++){
            for (int cadaColumna = 0; cadaColumna< valoresCanales.obtenerColumnas(); cadaColumna++){
                ListaNumerica temporal = (ListaNumerica) valoresCanales.obtener(cadaRenglon, cadaColumna);
                int alfa = (int) ((double) temporal.obtener(0));
                int red = (int) ((double) temporal.obtener(1));
                int green = (int) ((double) temporal.obtener(2));
                int blue = (int) ((double) temporal.obtener(3));

                if ((red+brillo < 255) && (red+brillo > 0)){
                    red += brillo;
                }
                if ((green+brillo < 255) && (green+brillo > 0)){
                    green += brillo;
                }
                if ((blue+brillo < 255) && (blue+brillo > 0)){
                    blue += brillo;
                }
                temporal.cambiar(0, alfa);
                temporal.cambiar(1, red);
                temporal.cambiar(2, green);
                temporal.cambiar(3, blue);
                int nuevoValor = ((alfa|(255<<24))<<24)|((red|(255<<16))<<16)|((255<<8)&(green<<8))|(blue&(255));
                pixeles.cambiar(cadaRenglon, cadaColumna, nuevoValor);
            }
        }
        guardarDatosMatrizAImagen2();
    }

    /**
     * Metodo que hace que la imagen se tenga un tono sepia.
     */
    public void tonoSepia(){
        aplicarEscalaGrises();
        pixeles.sumarEscalar(-16);
        guardarDatosMatrizAImagen2();
    }

    public void transponer(){
        pixeles.transpuesta();
        guardarDatosMatrizAImagen2();
    }

    /**
     * Metodo que invierte verticalmente una imagen.
     */
    public void invertirVertical(){
        pixeles.invertirVertical();
        guardarDatosMatrizAImagen2();
    }

    /**
     * Metodo que invierte horizontalmente una imagen.
     */
    public void invertirHorizontal(){
        pixeles.invertirHorizontal();
        guardarDatosMatrizAImagen2();
    }

    /**
     * Metodo que llena una matriz con los valores de pixeles
     */
    public void extraerCanalesDePixeles(){
        valoresCanales = new Matriz2(pixeles.obtenerRenglones(), pixeles.obtenerColumnas());
        ListaNumerica canales;
        for (int cadaRenglon = 0; cadaRenglon< valoresCanales.obtenerRenglones(); cadaRenglon++){
            for (int cadaColumna = 0; cadaColumna < valoresCanales.obtenerColumnas(); cadaColumna++){
                canales = new ListaNumerica(4);
                int valorPixel = (int) ((double) pixeles.obtener(cadaRenglon, cadaColumna));
                int canalAlpha = (valorPixel&(225<<24))>>>24; //obtenemos el numero realizando operadores y recorridos
                int canalRed = (valorPixel&(255<<16))>>16; //numero correspondiente al canal rojo
                int canalGreen = (valorPixel&(255<<8))>>8; //numero correspondiente al canal verde
                int canalBlue = valorPixel&(255); //numero correspondiente al canal blue
                canales.agregar(canalAlpha);
                canales.agregar(canalRed);
                canales.agregar(canalGreen);
                canales.agregar(canalBlue);
                valoresCanales.cambiar(cadaRenglon, cadaColumna, canales);
            }
        }
    }

    /**
     * Metodo que aplica un escalado de Grises a la imagen.
     */
    public void aplicarEscalaGrises(){
        extraerCanalesDePixeles();
        ListaNumerica listaDeCanalesTemporal;
        for (int cadaRenglon = 0; cadaRenglon<valoresCanales.obtenerRenglones(); cadaRenglon++){
            for (int cadaColumna = 0; cadaColumna<valoresCanales.obtenerColumnas(); cadaColumna++){
                listaDeCanalesTemporal = (ListaNumerica) valoresCanales.obtener(cadaRenglon, cadaColumna);
                int canalBinario = listaDeCanalesTemporal.promediarValoresNumericos();
                int nuevoValor = ((canalBinario|(255<<24))<<24)|((canalBinario|(255<<16))<<16)|((255<<8)&(canalBinario<<8))|(canalBinario&(255));
                pixeles.cambiar(cadaRenglon, cadaColumna, nuevoValor);
            }
        }
        guardarDatosMatrizAImagen2();
    }

    /**
     * Metodo que gira la imagen 90 grados por defecto.
     */
    public void girar(){
        Matriz2Numerica temporal = new Matriz2Numerica(pixeles.obtenerColumnas(), pixeles.obtenerRenglones());
        int renglonesAlReves = (pixeles.obtenerRenglones())-1;
        for (int cadaRenglon = 0; cadaRenglon < pixeles.obtenerRenglones(); cadaRenglon++){
            for(int cadaColumna = 0; cadaColumna < pixeles.obtenerColumnas(); cadaColumna++){
                temporal.cambiar(cadaColumna, renglonesAlReves, (int) ((double) pixeles.obtener(cadaRenglon, cadaColumna)));
            }
            renglonesAlReves--;
        }
        pixeles.redefinir(temporal);
    }

    /**
     * Gira la imagen 90 grados.
     */
    public void girar90grados(){
        girar();
        guardarDatosMatrizAImagen2();
    }

    /**
     * Metodo que gira la imagen 180 grados
     */
    public void girar180grados(){
        invertirVertical();
        invertirHorizontal();
    }

    /**
     * Metodo que gira la imagen 270 grados
     */
    public void girar270grados(){
        girar();
        girar();
        girar();
        guardarDatosMatrizAImagen2();
    };

    public void marco(int grosor, int canalRed, int canalGreen, int canalBlue){
        Matriz2Numerica imagenmarco = new Matriz2Numerica(pixeles.obtenerRenglones()+(grosor*2), pixeles.obtenerColumnas()+(grosor*2));
        int colorARellenarMarco = -1;
        //Obtener el color deseado
        if ( ((canalRed > 0)&&(canalRed < 255)) && ((canalGreen > 0)&&(canalGreen < 255)) && ((canalBlue > 0)&&(canalBlue < 255))){
            colorARellenarMarco = ((0<<24)|(canalRed<<16)|(canalGreen<<8)|(canalBlue));
        }
        imagenmarco.rellenar(colorARellenarMarco);
        int posicionEnMarcoRenglon = grosor;
        int posicionEnMarcoColumna = grosor;
        for (int cadaRenglonPixel = 0; cadaRenglonPixel<pixeles.obtenerRenglones(); cadaRenglonPixel++){
            for (int cadaColumnaPixel = 0; cadaColumnaPixel<pixeles.obtenerColumnas(); cadaColumnaPixel++){
                int pixelOriginal = (int) (double) pixeles.obtener(cadaRenglonPixel, cadaColumnaPixel);
                imagenmarco.cambiar(posicionEnMarcoRenglon, posicionEnMarcoColumna, pixelOriginal);
                posicionEnMarcoColumna++;
            }
            posicionEnMarcoColumna = grosor;
            posicionEnMarcoRenglon++;
        }
        pixeles.redefinir(imagenmarco);
        guardarDatosMatrizAImagen2();
    }

}
