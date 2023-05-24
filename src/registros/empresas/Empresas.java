package registros.empresas;

import estructurasLineales.ListaEstatica;
import entradasalida.SalidaPorDefecto;

public class Empresas {
    protected String nombre;
    protected ListaEstatica empleados;

    public Empresas(String nombre, ListaEstatica empleados) {
        this.nombre = nombre;
        this.empleados = empleados;
    }

    public Empresas(String nombre, int cantidadEmpleados) {
        this.nombre = nombre;
        empleados = new ListaEstatica(cantidadEmpleados);
    }

    public boolean agregarEmpleados(Empleado objetoEmpleado){
        int retornoEmpleado = (Integer) empleados.buscar(objetoEmpleado);
        if (retornoEmpleado == -1){ //se puede agregar
            int retornoPosicionEmp = empleados.agregar(objetoEmpleado);
            if (retornoPosicionEmp>=0){
                return true;
            }else{
                return false;
            }
        } else { //no se puede agregar
            return false;
        }
    }

    public void imprimirDatosEmpresa(){
        SalidaPorDefecto.terminal("Los datos de la escuela "+ nombre+" son: \n");
                empleados.imprimir();
    }

    public void imprimirDatosEmpresaDetalle(){
        SalidaPorDefecto.terminal("Los datos de la escuela "+ nombre+" son: \n");

        for (int cadaEmpleado=0;cadaEmpleado<empleados.cantidad(); cadaEmpleado++){
            Empleado empleadoTemporal = (Empleado) empleados.obtener(cadaEmpleado);
            SalidaPorDefecto.terminal(empleadoTemporal.getNombre()+
                    " ("+empleadoTemporal.numeroEmpleado+") \n");
            SalidaPorDefecto.terminal("Sus comisiones son: \n");
            empleadoTemporal.getComisionesAnio().imprimir();
            SalidaPorDefecto.terminal("\n");
        }
    }

    public Double obtenerpromedioEmpleado(Empleado objetoEmpleado){
        int posicionEmpleado = (int)empleados.buscar(objetoEmpleado);
        if (posicionEmpleado == -1){ //si no se encuentra
            return null;
        } else { //si se encuentra
            Empleado empleadoTemporal = (Empleado) empleados.obtener(posicionEmpleado);
            return empleadoTemporal.obtenerPromedio();
        }
    }
}
