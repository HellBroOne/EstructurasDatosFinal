package registros.empresas;

import estructurasLineales.ListaEstatica;

public class Empleado {
    protected int numeroEmpleado;
    protected String nombre;
    protected int edad;
    protected ListaEstatica comisionesAnio;

    /**
     * Constructor que obtiene cuatro parametros.
     * @param numeroEmpleado
     * @param nombre
     * @param edad
     * @param comisionesAnio
     */
    public Empleado(int numeroEmpleado, String nombre, int edad, ListaEstatica comisionesAnio) {
        this.numeroEmpleado = numeroEmpleado;
        this.nombre = nombre;
        this.edad = edad;
        this.comisionesAnio = comisionesAnio;
    }

    public Empleado(int numeroEmpleado, String nombre, int edad) {
        this.numeroEmpleado = numeroEmpleado;
        this.nombre = nombre;
        this.edad = edad;
        comisionesAnio = new ListaEstatica(12);
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

    public ListaEstatica getComisionesAnio() {
        return comisionesAnio;
    }

    public void setComisionesAnio(ListaEstatica comisionesAnio) {
        this.comisionesAnio = comisionesAnio;
    }

    public double obtenerPromedio(){
        double promedio = 0.0;
        if (comisionesAnio.cantidad() != 0){ //Evitamos que el promedio de comisiones sea 0, evitando error matematico
            for (int cadaMes = 0; cadaMes<comisionesAnio.cantidad(); cadaMes++){
                promedio += (double) comisionesAnio.obtener(cadaMes);
            }
            promedio = promedio/comisionesAnio.cantidad();
            return promedio;
        } else { //se hace
            return promedio;
        }

    }

    @Override
    public String toString() {
        return "" + numeroEmpleado;
    }
}
