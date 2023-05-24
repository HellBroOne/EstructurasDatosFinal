package registros.centros_computo;

import entradasalida.SalidaPorDefecto;

/**
 * Clase que representa a una Aplicacion en nuestro Programa.
 */
public class Aplicacion {
    protected String nombre;
    protected String logo;
    protected double version;
    protected double ram_requerida;

    protected String autores;

    public Aplicacion(String nombre, String logo, double version, double ram_requerida, String autores){
        this.nombre = nombre;
        this.logo = logo;
        this.version = version;
        this.ram_requerida = ram_requerida;
        this.autores = autores;
    }

    public String getNombre() {
        return nombre;
    }

    public String getLogo() {
        return logo;
    }

    public double getVersion() {
        return version;
    }

    public double getRam_requerida() {
        return ram_requerida;
    }

    @Override
    public String toString() {
        return nombre;
    }

    /**
     * Metodo que imprime los datos de la aplicacion.
     */
    public void imprimirDatosApp(){
        SalidaPorDefecto.terminal("Aplicacion: "+nombre+"\n Logo: "+logo+"\n Version: "+version+"\n Ram Necesaria: "+ram_requerida+"\n Autores: "+autores);
    }

    /**
     * Metodo que permite cambiar a un autor.
     * @param nombreAutor Nueva cadena de autor(es).
     */
    public void cambiarAutores(String nombreAutor){
        this.autores = nombreAutor;
    }
}
