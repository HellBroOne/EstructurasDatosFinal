package registros.competenciasatleticas;

import entradasalida.SalidaPorDefecto;
import estructurasLineales.Lista;
import estructurasLineales.ListaEstatica;
import estructurasnolineales.Matriz3;
import registros.competenciasatleticas.Corredor;
import registros.competenciasatleticas.EventoCompetencia;

public class ControlCompetenciasCorredores {
    protected Matriz3 kmRecorridos;
    protected ListaEstatica anios;
    protected ListaEstatica corredores;
    protected ListaEstatica eventosCompetencias;

    public ControlCompetenciasCorredores(int numAnios, int numCorredores, int numEventos){
        kmRecorridos = new Matriz3(numAnios, numCorredores, numEventos);
        anios= new ListaEstatica(numAnios);
        corredores= new ListaEstatica(numCorredores);
        eventosCompetencias= new ListaEstatica(numEventos);
        kmRecorridos.rellenar(0.0);
    }

    public boolean agregarAnio(int anio){
        int indiceAnio = (int) anios.buscar(anio);
        if (indiceAnio == -1){//puede agregarse, ya que no esta
            int retorno = anios.agregar(anio);
            if (retorno == -1){ //si no se puede agregar, se regresa falso
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean agregarCorredor(Corredor corredor){
        int indiceCorredor = (int) corredores.buscar(corredor); //se busca
        if (indiceCorredor == -1){//puede agregarse, ya que no esta
            int retorno = corredores.agregar(corredor);
            if (retorno == -1){ //si no se puede agregar, se regresa falso
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean agregarEvento(EventoCompetencia evento){
        int indiceEvento = (int) eventosCompetencias.buscar(evento);
        if (indiceEvento == -1){//puede agregarse, ya que no esta
            int retorno = eventosCompetencias.agregar(evento);
            if (retorno == -1){ //si no se puede agregar, se regresa falso
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean agregarKilometrosRecorridos(int numAnio, int numCorredor, String nombreEvento, double cantidadKilometrosRecorridos){
        ListaEstatica listaIndices = buscarIndicesCuboKm(numAnio, numCorredor, nombreEvento);
        if (listaIndices != null){
            return kmRecorridos.cambiar((int) listaIndices.obtener(0), (int) listaIndices.obtener(1), (int) listaIndices.obtener(2), cantidadKilometrosRecorridos);
        }else{
            return false;
        }
    }

    private ListaEstatica buscarIndicesCuboKm(int anio, int numCorredor, String nombreEvento){
        int indiceAnio = (int) anios.buscar(anio);
        int indiceCorredor = (int) corredores.buscar(numCorredor);
        int indiceEvento = (int) eventosCompetencias.buscar(nombreEvento);
        if (indiceAnio>= 0 && indiceCorredor >= 0 && indiceEvento>=0){
            ListaEstatica listaIndices = new ListaEstatica(3);
            listaIndices.agregar(indiceAnio);
            listaIndices.agregar(indiceCorredor);
            listaIndices.agregar(indiceEvento);
            return listaIndices;
        }else{
            return null;
        }
    }

    public void imprimirAnios(){
        SalidaPorDefecto.terminal("Anios: \n");
        anios.imprimir();
    }

    public void imprimirCorredores(){
        SalidaPorDefecto.terminal("Corredores: \n");
        corredores.imprimir();
    }

    public void imprimirEventos(){
        SalidaPorDefecto.terminal("Eventos: \n");
        eventosCompetencias.imprimir();
    }

    public void imprimirKilometros(){
        SalidaPorDefecto.terminal("Kilometros: \n");
        kmRecorridos.imprimirPorColumnas();
    }

    public void imprimirDatosCompetencias(){
        imprimirCorredores();
        imprimirAnios();
        imprimirEventos();
        imprimirKilometros();
    }

    //Preguntas Comunes
    //indicar los kilometros recorridos de lencho en "Viva la carrera"
    //en los anios 2000, 2005, 2020, 2019
    //Indicar los km recorridos por Lencho en 2005

    public double kmXCorredorXEvento(int numCorredor, String nombreEvento, ListaEstatica aniosPedidos){
        double kmsAcumulados = 0.0;
        //recorrer cada anio pedido
        for (int cadaAnio = 0;cadaAnio<anios.cantidad();cadaAnio++){
            int anioPedido = (int) aniosPedidos.obtener(cadaAnio);
            double kmsIndividuales = kmXCorredorXAnio(numCorredor, nombreEvento, anioPedido);
            if(kmsIndividuales>=0){
                kmsAcumulados = kmsAcumulados + kmsIndividuales;
            }
        }
        return kmsAcumulados;
    }

    public double kmXCorredorXAnio(int numCorredor, String nombreEvento, int anio){
        ListaEstatica listaIndices = buscarIndicesCuboKm(anio, numCorredor, nombreEvento);
        if (listaIndices != null){
            return (double) kmRecorridos.obtener((int) listaIndices.obtener(0), (int) listaIndices.obtener(1), (int) listaIndices.obtener(2));
        } else {
            return -1.0;
        }
    }
}
