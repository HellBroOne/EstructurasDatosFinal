package pruebas;

import entradasalida.SalidaPorDefecto;
import estructurasLineales.ListaEstatica;
import estructurasnolineales.GrafoEstatico;

public class PruebaGE {
    public static void main(String[] args) {
        GrafoEstatico grafo = new GrafoEstatico(4, 0.0);
        grafo.agregarVertice("A");
        grafo.agregarVertice("B");
        grafo.agregarVertice("D");
        grafo.agregarVertice("C");
        grafo.imprimir(); //no hay mas que ceros

        //Aristas de A
        grafo.agregarArista("A", "B");
        grafo.agregarArista("A", "C");
        grafo.agregarArista("A", "D");
        //Aristas de B
        grafo.agregarArista("B", "D");
        grafo.agregarArista("B", "C");
        //Aristas de D
        grafo.agregarArista("D", "B");
        //Aristas de C
        grafo.agregarArista("C", "B");
        grafo.agregarArista("C", "D");
        SalidaPorDefecto.terminal("\nGrafo con los datos agregados.\n");
        grafo.imprimir();
        SalidaPorDefecto.terminal("\n");
        SalidaPorDefecto.terminal("Probando eliminar D: "+grafo.eliminarVertice("D")+"\n");
        grafo.imprimir();
        SalidaPorDefecto.terminal("\nProbando ver si dos son adyacentes (A y A no son): "+grafo.esAdyacente("A", "A"));
        SalidaPorDefecto.terminal("\nProbando ver si dos son adyacentes (A y C si son, al menos el origen los conecta): "+grafo.esAdyacente("A", "C"));
        SalidaPorDefecto.terminal("\nProbando ver si dos son adyacentes (B y C si son, origen y destino conectados): "+grafo.esAdyacente("B", "C"));
        SalidaPorDefecto.terminal("\nProbando ver si dos son adyacentes (A y D, D no existe): "+grafo.esAdyacente("A", "D"));
        SalidaPorDefecto.terminal("\n\nProbando si es posible eliminar la arista de A y A (esa arista no existe): "+grafo.eliminarArista("A", "A"));
        SalidaPorDefecto.terminal("\nProbando si es posible eliminar la arista de A y C (si existe la arista): "+grafo.eliminarArista("A", "C"));
        SalidaPorDefecto.terminal("\nProbando si es posible eliminar la arista de A y D (ya no existe D): "+grafo.eliminarArista("A", "D")+"\n");
        grafo.imprimir();
        SalidaPorDefecto.terminal("\n\nProbando el metodo buscar (Buscado A): "+grafo.buscarVertice("A")+"\n");
        SalidaPorDefecto.terminal("Probando el metodo buscar (Buscado X): "+grafo.buscarVertice("X")+"\n");
        SalidaPorDefecto.terminal("\n\nProbando el metodo de pseudografo: "+grafo.esPseudografo()+"\nVamos a agregar una arista de B con B...\n");
        grafo.agregarArista("B", "B");
        grafo.imprimir();
        SalidaPorDefecto.terminal("\nProbando el metodo de pseudografo ahora: "+grafo.esPseudografo()+"\n\n");
        grafo.eliminarArista("B", "B");
        SalidaPorDefecto.terminal("Probando si el grafo actual es multigrafo\n" +
                "(el cual no es, ya que en solo B y C estan conectados entre si): "+grafo.esMultigrafo()+"\n\n");
        grafo.agregarArista("B", "B");
        SalidaPorDefecto.terminal("Probando si el grafo actual es multigrafo\n" +
                "(el cual no es, ya que en solo B y C estan conectados entre si, aunque haya un lazo de B con B): "+grafo.esMultigrafo()+"\n\n");
        SalidaPorDefecto.terminal("Agregando arista de C a A y otra de A a C\n");
        grafo.agregarArista("C", "A");
        grafo.agregarArista("A", "C");
        grafo.imprimir();
        SalidaPorDefecto.terminal("Probando si el grafo actual es multigrafo\n" +
                "(ahora si [B y C] y [A, C] estan conectados entre si): "+grafo.esMultigrafo()+"\n\n");
        SalidaPorDefecto.terminal("Veamos el numero de vertices de A: "+grafo.gradoVertice("A")+"\n");
        SalidaPorDefecto.terminal("Veamos el numero de vertices de B: "+grafo.gradoVertice("B")+"\n");
        SalidaPorDefecto.terminal("Veamos el numero de vertices de Z: "+grafo.gradoVertice("Z")+"\n");
        SalidaPorDefecto.terminal("Agregando D sin aristas...");
        grafo.agregarVertice("D");
        SalidaPorDefecto.terminal("Veamos el numero de vertices de D (aislado): "+grafo.gradoVertice("D")+"\n");
        grafo.agregarArista("D", "A");
        grafo.agregarArista("D", "C");
        //grafo.agregarArista("A", "D");
        grafo.imprimir();
        SalidaPorDefecto.terminal("\nProbando si hay ruta de A a D: "+grafo.hayRuta("A", "D")+"\n");
        SalidaPorDefecto.terminal("Probando si hay ruta de C a B: "+grafo.hayRuta("C", "B")+"\n");
        SalidaPorDefecto.terminal("Probando si hay ruta de B a D: "+grafo.hayRuta("B", "D")+"\n\n");
        grafo.imprimir();
        SalidaPorDefecto.terminal("Verificando si es grafo conexo: "+grafo.esConexo()+"\n");
        SalidaPorDefecto.terminal("\nProbando si es dirigido: "+grafo.esDirigido()+"\nDirigiendo al grafo...");
        grafo.agregarArista("A", "D");
        grafo.agregarArista("B", "A");
        grafo.agregarArista("C", "D");
        grafo.imprimir();
        SalidaPorDefecto.terminal("\nProbando a ver si ahora es dirigido: "+grafo.esDirigido());
        SalidaPorDefecto.terminal("\nProbando a ver si es arbol: "+grafo.esArbol());
        SalidaPorDefecto.terminal("\n Imprimiendo el vertice 16:\n");
        ((ListaEstatica) grafo.listarAristas().obtener(15)).imprimir();
        SalidaPorDefecto.terminal("\n Imprimiendo el vertice 4:\n");
        ((ListaEstatica) grafo.listarAristas().obtener(4)).imprimir();
        SalidaPorDefecto.terminal("\nProbando el metodo que enlista a las aristas de un vertices:\n");
        grafo.listarAristas("B").imprimir();
        SalidaPorDefecto.terminal("\nProbando el metodo que enlista a los vertices:\n");
        grafo.listarVertices().imprimir();
    }
}
