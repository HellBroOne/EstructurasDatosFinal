package toneladasCampesinos;


import entradasalida.EntradaPorDefecto;
import entradasalida.SalidaPorDefecto;

import java.util.Objects;

/**Clase controladora del programa de Control de toneladas.
 * @version 1.0
 * @author Gerardo Rivas Delgado
 */
public class menuControlador {
    //definir ambos campesinos
    protected Campesino campesino1;
    protected Campesino campesino2;
    protected Anio anio1;
    protected Anio anio2;
    protected Anio anio3;
    protected Anio anio4;

    /**
     * Para inicializar una instancia de esta clase controladora se requieren 4 objetos
     * de tipo anio, esto con la finalidad de poder guardarlos en el campesino y tener
     * dichos anios como punto de partida.
     * @param anio1 Objeto Anio del anio 1 (Se indica el numero al inicio del programa).
     * @param anio2 Objeto Anio del anio 2 (Se hace automaticamente a partir del anio 1).
     * @param anio3 Objeto Anio del anio 3 (Se hace automaticamente a partir del anio 1).
     * @param anio4 Objeto Anio del anio 4 (Se hace automaticamente a partir del anio 1).
     */
    public menuControlador(Anio anio1, Anio anio2, Anio anio3, Anio anio4){
        this.anio1 = anio1;
        this.anio2 = anio2;
        this.anio3 = anio3;
        this.anio4 = anio4;
        campesino1 = new Campesino("Juan", anio1, anio2, anio3, anio4);
        campesino2 = new Campesino("Paco", anio1, anio2, anio3, anio4);
    }

    /**
     * Metodo de la clase controladora que tiene la posibilidad de mostrar y repetir el menu
     * y obtener una respuesta de lo que se desea hacer y otra de si se quiere continuar.
     */
    public void mostrarMenu(){
        String respuesta = "hola";
        do {
            SalidaPorDefecto.terminal("\nBienvenido, que desea hacer? \n" +
                    "a) Agregar Toneladas\n" +
                    "b) Obtener el promedio anual de un campesino\n" +
                    "d) Mes con menos toneladas por anio de un campesino\n" +
                    "e) Toneladas del ultimo mes de cada anio\n" +
                    "f) Toneladas obtenidas en el segundo trimestre de cada anio de un campesino\n" +
                    "g) Campesino que realizo su peor trabajo en cada anio\n" +
                    "h) Mes que genera mayores dividendos a los campesinos\n" +
                    "i) Estacion del anio que genera mayor produccion\n" +
                    "(Escriba solo la letra del inciso): ");
            String inciso = EntradaPorDefecto.consolaCadenas();
            switch (inciso){
                case "a": agregar(); break;
                case "b": promediarAnio(); break;
                case "c":
                case "d": mesMenorProduccion(); break;
                case "e": toneladasUltimoMes(); break;
                case "f": toneladasSegundoTrimestre(); break;
                case "g": peorCampesino(); break;
                case "h": mesMayorDividendos(); break;
                case  "i": estacionMayorProduccion(); break;
                default: SalidaPorDefecto.terminal("Ingrese un valor valido."); break;
            }
            SalidaPorDefecto.terminal("\nDesea continuar?\nEscriba 'no' para acabar el programa.\nDe lo contrario, ingrese cualquier cosa.\nSu respuesta: ");
            respuesta = EntradaPorDefecto.consolaCadenas();
        } while (respuesta != "no");

    }

    /**
     * Este metodo muestra la lista de Campesinos que se tienen creados.
     * @return Regresa el campesino seleccionado a partir del numero de campesino.
     */
    public Campesino mostrarYObtenerCampesino(){
        SalidaPorDefecto.terminal("Que campesino deseas consultar?\n1. "+campesino1+"\n2. "+campesino2+"\nIngresa el numero: ");
        String opcionCampesino = EntradaPorDefecto.consolaCadenas();
        if (Objects.equals(opcionCampesino, "1")){
            return campesino1;
        } else if (Objects.equals(opcionCampesino, "2")){
            return campesino2;
        } else {
            SalidaPorDefecto.terminal("'"+opcionCampesino+"' no es una opcion valida,\nSe ha seleccionado al campesnio 1.");
            return campesino1;
        }
    }

    /**
     * Este metodo busca un anio en la lista estatica que tiene el campesino sobre sus anios
     * trabajados que se guardan desde el constructor de esta clase.
     * @param campesino Ingresa el Objeto Campesino del cual quieres buscar un anio.
     * @return Se regresa un Objeto Anio del campesino dado por parametros.
     */
    public Anio mostrarYPedirAnios(Campesino campesino){
        for (int numeroOpcion = 1; numeroOpcion<=4; numeroOpcion++){
            SalidaPorDefecto.terminal(numeroOpcion+". "+campesino.listaAnios.obtener(numeroOpcion-1)+"\n");
        }
        SalidaPorDefecto.terminal("Que anio deseas consultar? ");
        int numeroAnio = Integer.parseInt(EntradaPorDefecto.consolaCadenas());
        return (Anio) campesino.listaAnios.obtener(numeroAnio);
    }

    /**
     * Metodo de la clase controladora que permite agregar una tonelada.
     */
    public void agregar(){
        Campesino campesinoPedido = mostrarYObtenerCampesino();
        Anio anioPedido = mostrarYPedirAnios(campesinoPedido);
        for (int numeroAgregado = 0; numeroAgregado <= 11; numeroAgregado++){
            SalidaPorDefecto.terminal("Ingrese el valor de la tonelada para el mes "+(numeroAgregado+1)+": ");
            double toneladaAgregada = EntradaPorDefecto.consolaDouble();
            anioPedido.agregarTonelada(toneladaAgregada);
        }
    }

    /**
     * Metodo que imprime un promedio anual especificado.
     */
    public void promediarAnio(){
        Campesino campesino = mostrarYObtenerCampesino();
        Anio anio = mostrarYPedirAnios(campesino);
        SalidaPorDefecto.terminal(""+anio.obtenerPromedioToneladasAnuales());
    }

    /**
     * Metodo que imprime cual fue el mes de menor produccion en cada anio.
     */
    public void mesMenorProduccion(){
        Campesino campesino = mostrarYObtenerCampesino();
        for (int anio = 0; anio <= 3; anio++){
            Anio anioMeses = (Anio) campesino.listaAnios.obtener(anio);
            SalidaPorDefecto.terminal(""+anioMeses.menorToneladaAnio());
        }
    }

    /** Metodo que imprime las toneladas del ultimo mes
     *  de los anios (Se requiere tener todos los anios de
     *  un campesino llenos, de lo contrario bota un error)..
     */
    public void toneladasUltimoMes(){
        Campesino campesino = mostrarYObtenerCampesino();
        for (int anio = 0; anio <= 3; anio++){
            Anio anioMeses = (Anio) campesino.listaAnios.obtener(anio);
            SalidaPorDefecto.terminal("Ultima tonelada del anio "+anioMeses.numeroAnio+", es de: "+anioMeses.obtenerToneladasUltimoMes());
        }
    }

    /** Metodo que imprime las toneladas del ultimo mes
     *  de los anios (Se requiere tener todos los anios de un
     *  campesino llenos, de lo contrario bota un error).
     */
    public void toneladasSegundoTrimestre(){
        Campesino campesino = mostrarYObtenerCampesino();
        for (int anio = 0; anio <= 3; anio++){
            Anio anioMeses = (Anio) campesino.listaAnios.obtener(anio);
            SalidaPorDefecto.terminal("Las toneladas del segundo trimestre de "+anioMeses.numeroAnio+", son de: "+anioMeses.obtenerTotalSegundoTrimestre());
        }
    }

    /**
     * Metodo que imprime el campesino con el peor Rendimiento de los cuatro anios
     * (Se requiere tener todos los anios de un campesino llenos, de lo contrario bota un error).
     */
    public void peorCampesino(){
        for (int anio = 0; anio <= 3; anio++){
            Anio anioCampesino1 = (Anio) campesino1.listaAnios.obtener(anio);
            Anio anioCampesino2 = (Anio) campesino2.listaAnios.obtener(anio);
            double sumaAnioCampesino1 = anioCampesino1.obtenerSumaTotal();
            double sumaAnioCampesino2 = anioCampesino2.obtenerSumaTotal();
            if (sumaAnioCampesino1<sumaAnioCampesino2){
                SalidaPorDefecto.terminal("El campesino con peor rendimiento en "+anioCampesino2.numeroAnio+" fue "+sumaAnioCampesino2);
            } else {
                SalidaPorDefecto.terminal("El campesino con peor rendimiento en "+anioCampesino1.numeroAnio+" fue "+sumaAnioCampesino1);
            }
        }
    }

    /**
     * Este metodo imprime la estacion que tuvo mayor produccion en el anio seleccionado
     */
    public void estacionMayorProduccion(){
        Campesino campesino = mostrarYObtenerCampesino();
        Anio anio = mostrarYPedirAnios(campesino);
        String estacion = anio.obtenerEstacionMayorGanancia();
        SalidaPorDefecto.terminal("La estacion de mayor produccion del anio "+anio.numeroAnio+" fue en "+estacion);
    }

    /**
     * Este metodo muestra los dividendos mayores que se tienen en todos los anios.
     * (Se requiere tener todos los anios de un campesino llenos, de lo contrario bota un error).
     */
    public void mesMayorDividendos(){
        Campesino campesino = mostrarYObtenerCampesino();
        double mesMayor = 0.0;
        int numeroAnioMayor = 0;
        for (int numeroAnio = 0; numeroAnio <= 3; numeroAnio++){
            Anio anioAImprimir = (Anio) campesino.listaAnios.obtener(numeroAnio);
            double mesMayorComparar = anioAImprimir.obtenerMayorAnual();
            if (mesMayorComparar > mesMayor){
                mesMayor = mesMayorComparar;
                numeroAnioMayor = numeroAnio;
            }
        }
        SalidaPorDefecto.terminal("El mes que tuvo mas ganancia fue en el anio "+campesino.listaAnios.obtener(numeroAnioMayor)+" con una ganancia de "+mesMayor);
    }
}
