package pruebas;

import entradasalida.SalidaPorDefecto;
import estructurasLineales.ListaDinamica;
import estructurasLineales.ListaEstatica;
import registros.empresas.Empleado2;
import registros.empresas.Empresa2;

public class PruebaEmpresa2 {
    public static void main(String argumentos[]) {
        Empresa2 empresa = new Empresa2("Jose Cuervo SA de CV");

        //creacion empleado 1
        ListaDinamica comisiones1 = new ListaDinamica();
        comisiones1.agregar(400.2);
        comisiones1.agregar(701.2);
        comisiones1.agregar(80.2);
        comisiones1.agregar(4.2);
        Empleado2 empleado1 = new Empleado2(101, "Pedro", 30, comisiones1);

        //creacion empleado2
        ListaDinamica comisiones2 = new ListaDinamica();
        comisiones2.agregar(900.3);
        comisiones2.agregar(10.2);
        comisiones2.agregar(498.1);
        Empleado2 empleado2 = new Empleado2(201, "Rosa", 31, comisiones2);

        empresa.agregarEmpleados(empleado1);
        empresa.agregarEmpleados(empleado2);

        empresa.imprimirDatosEmpresa();
        SalidaPorDefecto.terminal("\n");
        empresa.imprimirDatosEmpresaDetalle();

        SalidaPorDefecto.terminal("El promedio de comisiones de Pedro es de: "+empresa.obtenerpromedioEmpleado(empleado1));
    }
}
