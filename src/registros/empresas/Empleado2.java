package registros.empresas;

import estructurasLineales.ListaDinamica;
import estructurasLineales.ListaEstatica;

public class Empleado2 {
    protected int numeroEmpleado;
    protected String nombre;
    protected int edad;
    protected ListaDinamica comisionesAnio;

    /**
     * Constructor que obtiene cuatro parametros.
     * @param numeroEmpleado numero del empleado.
     * @param nombre nombre del empleado
     * @param edad edad del empleado
     * @param comisionesAnio comisiones del anio del empleado
     */
    public Empleado2(int numeroEmpleado, String nombre, int edad, ListaDinamica comisionesAnio) {
        this.numeroEmpleado = numeroEmpleado;
        this.nombre = nombre;
        this.edad = edad;
        this.comisionesAnio = comisionesAnio;
    }

    public Empleado2(int numeroEmpleado, String nombre, int edad) {
        this.numeroEmpleado = numeroEmpleado;
        this.nombre = nombre;
        this.edad = edad;
        comisionesAnio = new ListaDinamica();
    }

    public boolean agregarComision(double valorComision){
        int retorno = comisionesAnio.agregar(valorComision);
        if(retorno >= 0){
            return true;
        } else {
            return false;
        }
    }

    public int getNumeroEmpleado() {
        return numeroEmpleado;
    }

    public void setNumeroEmpleado(int numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public ListaDinamica getComisionesAnio() {
        return comisionesAnio;
    }

    public void setComisionesAnio(ListaDinamica comisionesAnio) {
        this.comisionesAnio = comisionesAnio;
    }

    public double obtenerPromedio(){
        double promedio = 0.0;
        int contadorComisiones = 0;
        if (comisionesAnio.vacia() == false){
            comisionesAnio.inicializarIterador();
            while(comisionesAnio.hayNodo() == true){
                promedio = promedio + (double) comisionesAnio.obtenerNodo();
                contadorComisiones++;
            }
            promedio = promedio/contadorComisiones;
        }
        return promedio;
    }

    @Override
    public String toString() {
        return "" + numeroEmpleado;
    }
}
