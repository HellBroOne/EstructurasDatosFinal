package registros.empresas;

import entradasalida.SalidaPorDefecto;
import estructurasLineales.ListaDinamica;
import estructurasLineales.ListaEstatica;

public class Empresa2 {
    protected String nombre;
    protected ListaDinamica empleados;

    public Empresa2(String nombre, ListaDinamica empleados) {
        this.nombre = nombre;
        this.empleados = empleados;
    }

    public Empresa2(String nombre) {
        this.nombre = nombre;
        empleados = new ListaDinamica();
    }

    public boolean agregarEmpleados(Empleado2 objetoEmpleado){
        Object retornoEmpleado = (Empleado2) empleados.buscar(objetoEmpleado);
        if (retornoEmpleado == null){ //se puede agregar
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
        SalidaPorDefecto.terminal("Los datos de la empresa "+ nombre+" son: \n");
                empleados.imprimir();
    }

    public void imprimirDatosEmpresaDetalle(){
        SalidaPorDefecto.terminal("Los datos de la empresa "+ nombre+" son: \n");
        empleados.inicializarIterador();
        while (empleados.hayNodo() == true){
            Empleado2 empleadoTemporal = (Empleado2) empleados.obtenerNodo();
            SalidaPorDefecto.terminal(empleadoTemporal.getNombre()+
                    " ("+empleadoTemporal.numeroEmpleado+") \n");
            SalidaPorDefecto.terminal("Sus comisiones son: \n");
            empleadoTemporal.getComisionesAnio().imprimir();
            SalidaPorDefecto.terminal("\n");
        }
    }

    public Double obtenerpromedioEmpleado(Empleado2 objetoEmpleado){
        Empleado2 empleadoEncontrado = (Empleado2) empleados.buscar(objetoEmpleado);
        if (empleadoEncontrado == null){ //si no se encuentra
            return null;
        } else { //si se encuentra
            return empleadoEncontrado.obtenerPromedio();
        }
    }
}
