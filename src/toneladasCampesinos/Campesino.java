package toneladasCampesinos;

import estructurasLineales.ListaEstatica;
/**
 * Clase del Campesino, esta clase guarda el nombre del campesino junto a una lista estatica
 * de cuales anios ha trabajado, estos anios son los mismos que se guardan y se piden al inicio
 * de la clase de pruebas del programa de Campesinos.
 * @version 1.0
 * @author Gerardo Rivas Delgado
 */
public class Campesino {
    protected String nombreCampesino;
    protected ListaEstatica listaAnios;

    /**
     * Constructor de la clase Campesino, se le pasa un nombre y una lista estatica.
     * @param nombreCampesino Este parametro se le asigna el nombre del campesino.
     *
     */
    public Campesino(String nombreCampesino, Anio anio1, Anio anio2, Anio anio3, Anio anio4){
        this.nombreCampesino = nombreCampesino;
        listaAnios = new ListaEstatica(4);
        listaAnios.agregar(anio1);
        listaAnios.agregar(anio2);
        listaAnios.agregar(anio3);
        listaAnios.agregar(anio4);
    }

    public String toString(){
        return nombreCampesino;
    }
}
