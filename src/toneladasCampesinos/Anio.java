package toneladasCampesinos;

import estructurasLineales.ListaEstatica;
import entradasalida.SalidaPorDefecto;

/**
 * Clase del Anio, esta se crea con el fin de tener como atributo
 * una lista estatica de tamanio de 12, la cual corresponde a todos
 * las cantidades de toneladas por mes.
 * @version 1.0
 * @author Gerardo Rivas Delgado
 */
public class Anio {
    protected int numeroAnio;
    protected ListaEstatica toneladasAnuales;
    protected ListaEstatica listaMeses = new ListaEstatica(12);

    /**
     * Constructor del Anio, se agrega el numero del anio que se va a dar de alta y el campesino que
     * trabajo ese anio.
     * @param numeroAnio Ingresa el numerol anio que esta registrado
     */
    public Anio(int numeroAnio){
        this.numeroAnio = numeroAnio;
        toneladasAnuales = new ListaEstatica(12);
        listaMeses.agregar("Enero");
        listaMeses.agregar("Febrero");
        listaMeses.agregar("Marzo");
        listaMeses.agregar("Abril");
        listaMeses.agregar("Mayo");
        listaMeses.agregar("Junio");
        listaMeses.agregar("Julio");
        listaMeses.agregar("Agosto");
        listaMeses.agregar("Septiembre");
        listaMeses.agregar("Octubre");
        listaMeses.agregar("Noviembre");
        listaMeses.agregar("Diciembre");
    }

    /**
     * Este metodo permite agregar un double como valor a una tonelada menusal al anio del campesino.
     * @param toneladaValor Agregue un valor double que indicara el peso de la tonelada.
     * @return Se regresa true si el valor ha sido agregado, false, si no se agrego.
     */
    public boolean agregarTonelada(double toneladaValor){
        int valorConfirmacion = toneladasAnuales.agregar(toneladaValor);
        if(valorConfirmacion >= 0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Metodo que regresa el promedio del valor de las comisiones del anio como double
     * @return Se obtiene un numero Double del promedio.
     */
    public double obtenerPromedioToneladasAnuales(){
        double promedio_regresado = 0.0;
        if (toneladasAnuales.cantidad() != 0){
            for (int numeroMesTonelada = 0; numeroMesTonelada < toneladasAnuales.cantidad(); numeroMesTonelada++){
                promedio_regresado += (double) toneladasAnuales.obtener(numeroMesTonelada);
            }
            promedio_regresado = promedio_regresado/toneladasAnuales.cantidad();
            return promedio_regresado;
        } else {
            return promedio_regresado;
        }
    }

    /**
     * Este metodo obtiene la tonelada mayor por anio
     * @return numero de tonelada mayor
     */
    public double obtenerMayorAnual(){
        double toneladaMayor = 0;
        for (int numeroTonelada = 0; numeroTonelada < toneladasAnuales.cantidad(); numeroTonelada++){
            double comparado = (double) toneladasAnuales.obtener(numeroTonelada);
            if (toneladaMayor < comparado){
                toneladaMayor = comparado;
            }
        }
        return toneladaMayor;
    }

    /**
     * Este metodo imprime los meses mayores al promedio que se obtenga
     */
    public void mesesMayoresAPromedio(){
        double promedio = obtenerPromedioToneladasAnuales();
        for (int mes = 0; mes < toneladasAnuales.cantidad(); mes++){
            double tonelada = (double) toneladasAnuales.obtener(0);
            if (tonelada > promedio){
                SalidaPorDefecto.terminal("El mes "+listaMeses.obtener(mes)+" es mayor, con una produccion de "+tonelada+" respecto al promedio");
            }
        }
    }

    /**
     * Este metodo regresa la tonelada menor de la array de las toneladas Anuales
     * @return Se regresa la tonelada menor de un Anio
     */
    public double menorToneladaAnio(){
        double menorTonelada = (double) toneladasAnuales.obtener(0);
        int numeroMesMenor = 0;
        for (int toneladaRecorrer = 0; toneladaRecorrer < toneladasAnuales.cantidad(); toneladaRecorrer++){
            double tonelada = (double) toneladasAnuales.obtener(toneladaRecorrer);
            if (tonelada < menorTonelada){
                menorTonelada = tonelada;
                numeroMesMenor = toneladaRecorrer;
            }
        }
        SalidaPorDefecto.terminal("El mes con menor produccion fue "+listaMeses.obtener(numeroMesMenor)+". ");
        return menorTonelada;
    }

    /**
     * Este metodo regresa la ultima tonelada que se registro
     * @return Se regresa el ultimo valor de la tonelada del mes
     */
    public double obtenerToneladasUltimoMes(){
        double toneladaUltima = (double) toneladasAnuales.obtener(11);
        return toneladaUltima;
    }

    /**
     * Metodo que obtiene el total del segundo trimestre del anio
     * @return se regresa la suma total del segundo trimestre
     */
    public double obtenerTotalSegundoTrimestre(){
        double totalTrimestre = 0.0;
        for (int mes = 3; mes <= 5; mes++){
            totalTrimestre += (double) toneladasAnuales.obtener(mes);
        }
        return totalTrimestre;
    }

    /**
     * Metodo que permite obtener la suma total de un anio
     * @return regresa el total de toneladas al anio
     */
    public double obtenerSumaTotal(){
        double totalToneladas = 0.0;
        for (int numeroTonelada = 0; numeroTonelada < toneladasAnuales.cantidad(); numeroTonelada++){
            totalToneladas += (double) toneladasAnuales.obtener(numeroTonelada);
        }
        return totalToneladas;
    }

    /**
     * Este metodo obtiene la tonelada mayor por anio
     * @return numero de tonelada mayor
     */
    public double obtenerMayor(){
        double toneladaMayor = 0.0;
        for (int numeroTonelada = 0; numeroTonelada < toneladasAnuales.cantidad(); numeroTonelada++){
            double comparado = (double) toneladasAnuales.obtener(numeroTonelada);
            if (toneladaMayor < comparado){
                toneladaMayor = comparado;
            }
        }
        return toneladaMayor;
    }

    public String obtenerEstacionMayorGanancia() {
        double gananciaInvierno = 0.0;
        double gananciaPrimavera = 0.0;
        double gananciaVerano = 0.0;
        double gananciaOtonio = 0.0;
        //obtener la suma de los meses de primavera
        for (int numeroMesPrimavera = 2; numeroMesPrimavera <= 4; numeroMesPrimavera++) {
            gananciaPrimavera += (double) toneladasAnuales.obtener(numeroMesPrimavera);
        }
        //obtener la suma de los meses de verano
        for (int numeroMesVerano = 5; numeroMesVerano <= 7; numeroMesVerano++) {
            gananciaVerano += (double) toneladasAnuales.obtener(numeroMesVerano);
        }
        //obtener la suma de los meses de otonio
        for (int numeroMesOtonio = 8; numeroMesOtonio <= 10; numeroMesOtonio++) {
            gananciaOtonio += (double) toneladasAnuales.obtener(numeroMesOtonio);
        }
        //obtener la suma de los meses de invierno
        for (int numeroMesInvierno = 0; numeroMesInvierno <= 1; numeroMesInvierno++) {
            gananciaInvierno += (double) toneladasAnuales.obtener(numeroMesInvierno);
        }
        gananciaInvierno += (double) toneladasAnuales.obtener(11);

        //teniendo esta ganancia, solo hay que comparar cual ganancia es mayor
        if ((gananciaInvierno > gananciaPrimavera) && (gananciaInvierno > gananciaOtonio) && (gananciaInvierno > gananciaVerano)) {
            return "Invierno";
        } else if ((gananciaPrimavera > gananciaInvierno) && (gananciaPrimavera > gananciaOtonio) && (gananciaPrimavera > gananciaVerano)) {
            return "Primavera";
        } else if ((gananciaOtonio > gananciaInvierno) && (gananciaOtonio > gananciaPrimavera) && (gananciaOtonio > gananciaVerano)) {
            return "Otonio";
        } else if ((gananciaVerano > gananciaInvierno) && (gananciaVerano > gananciaPrimavera) && (gananciaVerano > gananciaOtonio)) {
            return "Verano";
        } else {
            return "no hay ganancia diferente";
        }
    }

    public String toString(){
        return ""+numeroAnio;
    }

}
