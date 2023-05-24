package pruebas;

import estructurasLineales.ListaEstatica;
import entradasalida.SalidaPorDefecto;
import registros.empresas.Empleado;
import registros.empresas.Empresas;

public class PruebaEmpresa {
    public static void main(String argumentos[]) {
        Empresas empresa = new Empresas("Jose Cuervo SA de CV", 8);

        //creacion empleado 1
        ListaEstatica comisiones1 = new ListaEstatica(4);
        comisiones1.agregar(400.2);
        comisiones1.agregar(701.2);
        comisiones1.agregar(80.2);
        comisiones1.agregar(4.2);
        Empleado empleado1 = new Empleado(101, "Pedro", 30, comisiones1);

        //creacion empleado2
        ListaEstatica comisiones2 = new ListaEstatica(3);
        comisiones2.agregar(900.3);
        comisiones2.agregar(10.2);
        comisiones2.agregar(498.1);
        Empleado empleado2 = new Empleado(201, "Rosa", 31, comisiones2);

        empresa.agregarEmpleados(empleado1);
        empresa.agregarEmpleados(empleado2);

        empresa.imprimirDatosEmpresa();
        SalidaPorDefecto.terminal("\n");
        empresa.imprimirDatosEmpresaDetalle();

        SalidaPorDefecto.terminal("El promedio de comisiones de Pedro es de: "+empresa.obtenerpromedioEmpleado(empleado1));
    }
}
