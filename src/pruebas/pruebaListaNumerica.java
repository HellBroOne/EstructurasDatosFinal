package pruebas;

import entradasalida.SalidaPorDefecto;
import estructurasLineales.ListaEstatica;
import estructurasLineales.ListaNumerica;

public class pruebaListaNumerica {
    public static void main(String[] args) {
        ListaNumerica numeros = new ListaNumerica(5);
        SalidaPorDefecto.terminal("Probando el metodo de agregar> \n");
        SalidaPorDefecto.terminal(""+numeros.agregar(1.0));
        SalidaPorDefecto.terminal(""+numeros.agregar(2.0));
        SalidaPorDefecto.terminal(""+numeros.agregar(3.5));
        SalidaPorDefecto.terminal(""+numeros.agregar("test"));
        SalidaPorDefecto.terminal(""+numeros.agregar(6.8));
        SalidaPorDefecto.terminal(""+numeros.agregar(9));
        SalidaPorDefecto.terminal("\nListilla:\n");
        numeros.imprimir();
        SalidaPorDefecto.terminal(""+numeros.porEscalar(2.0)+"\n");
        SalidaPorDefecto.terminal("Probando el metodo de porEscalar> \n");
        numeros.imprimir();
        SalidaPorDefecto.terminal("Probando el metodo de sumarEscalar> \n");
        SalidaPorDefecto.terminal(""+numeros.sumarEscalar(2.0)+"\n");
        numeros.imprimir();

        ListaNumerica numeros2 = new ListaNumerica(5);
        ListaNumerica numeros3 = new ListaNumerica(2);
        numeros2.agregar(1.0);
        numeros2.agregar(0.1);
        numeros2.agregar(5.0);
        numeros2.agregar(7.1);
        numeros2.agregar(6.8);
        numeros2.agregar(10.01);
        numeros3.agregar(3.0);
        numeros3.agregar(4.0);
        SalidaPorDefecto.terminal("Probando el metodo de sumar> \n");
        SalidaPorDefecto.terminal(numeros.sumar(numeros2)+"\n");
        numeros.imprimir();
        SalidaPorDefecto.terminal(numeros.sumar(numeros3)+"\n");
        numeros.imprimir();
        SalidaPorDefecto.terminal("Probando el metodo de multiplicar> \n");
        SalidaPorDefecto.terminal(numeros.multiplicar(numeros2)+"\n");
        numeros.imprimir();
        SalidaPorDefecto.terminal(numeros.multiplicar(numeros3)+"\n");
        numeros.imprimir();
        SalidaPorDefecto.terminal("Probando el metodo de aplicarPotencia> \n");
        numeros3.imprimir();
        SalidaPorDefecto.terminal(numeros3.aplicarPotencia(2.0)+"\n");
        numeros3.imprimir();
        SalidaPorDefecto.terminal("Probando el metodo de aplicarPotencia on listas> \n");
        ListaNumerica listanumeros1 = new ListaNumerica(3);
        ListaNumerica listanumeros2 = new ListaNumerica(3);
        listanumeros1.agregar(1.0);
        listanumeros1.agregar(2.0);
        listanumeros1.agregar(3.0);
        listanumeros2.agregar(20.0);
        listanumeros2.agregar(2.0);
        listanumeros2.agregar(3.0);
        SalidaPorDefecto.terminal(listanumeros1.aplicarPotencia(listanumeros2)+"\n");
        listanumeros1.imprimir();
        SalidaPorDefecto.terminal(listanumeros1.aplicarPotencia(numeros3)+"\n");
        listanumeros1.imprimir();

        ListaNumerica ejemplo = new ListaNumerica(3);
        ejemplo.agregar(1.0);
        ejemplo.agregar(2.0);
        ejemplo.agregar(3.0);
        ListaNumerica ejemplo2 = new ListaNumerica(3);
        ejemplo2.agregar(3.0);
        ejemplo2.agregar(4.0);
        ejemplo2.agregar(5.0);
        SalidaPorDefecto.terminal("La nueva lista a probar es:\n");
        ejemplo.imprimir();
        SalidaPorDefecto.terminal("Probando productoEscalar:\n"+ejemplo.productoEscalar(ejemplo2));
        SalidaPorDefecto.terminal("\nProbando norma:\n"+ejemplo.norma());
        SalidaPorDefecto.terminal("\nProbando norma Euclidiana:\n"+ejemplo.normaEuclidiana(ejemplo2));

        ListaEstatica matriz1 = new ListaEstatica(2);
        matriz1.agregar(1.0);
        matriz1.agregar(2.0);

        ListaEstatica matriz2 = new ListaEstatica(2);
        matriz2.agregar(2.0);
        matriz2.agregar(5.0);

        ListaEstatica matriz3 = new ListaEstatica(2);
        matriz3.agregar(3.0);
        matriz3.agregar(6.0);

        ListaEstatica listas = new ListaEstatica(3);
        listas.agregar(matriz1);
        listas.agregar(matriz2);
        listas.agregar(matriz3);
        SalidaPorDefecto.terminal("\nProbando sumarListaEstatica:\n"+ejemplo.sumarListaEstatica(listas));
        

        ListaNumerica indices = new ListaNumerica(2);
        indices.agregar(0.0);
        indices.agregar(2.0);
        SalidaPorDefecto.terminal("\nProbando sumarIndices:\n"+ejemplo.sumarIndices(indices));

        ListaNumerica escalares = new ListaNumerica(3);
        escalares.agregar(4.0);
        escalares.agregar(1.0);
        escalares.agregar(9.0);
        SalidaPorDefecto.terminal("\nProbando sumarEscalares:\n"+ejemplo.sumarEscalares(escalares));
        ListaNumerica ejemplo3 = new ListaNumerica(3);
        ejemplo3.agregar(1.0);
        ejemplo3.agregar(2.0);
        ejemplo3.agregar(3.0);
        SalidaPorDefecto.terminal("\nProbando subLista:\n");
        ListaEstatica subLista = ejemplo3.subLista(indices);
        subLista.imprimir();

        ListaEstatica vectores = new ListaEstatica(3);
        ListaNumerica numeros1 = new ListaNumerica(2);
        ListaNumerica numerosDep2 = new ListaNumerica(2);
        ListaNumerica numerosDep3 = new ListaNumerica(2);
        numeros1.agregar(2);
        numeros1.agregar(2);
        numerosDep2.agregar(2);
        numerosDep2.agregar(5);
        numerosDep3.agregar(0);
        numerosDep3.agregar(-6);
        vectores.agregar(numeros1);
        vectores.agregar(numerosDep2);
        vectores.agregar(numerosDep3);
        SalidaPorDefecto.terminal("\nProbando sonLinealmenteDependientes:\n"+ejemplo3.sonLinealmenteDependientes(vectores)+"\n");

        ListaNumerica escalaresNulos = new ListaNumerica(3);
        escalaresNulos.agregar(0);
        escalaresNulos.agregar(0);
        escalaresNulos.agregar(0);
        SalidaPorDefecto.terminal("\nProbando sonLinealmenteIndependientes:\n"+escalaresNulos.sonLinealmenteIndependientes(vectores)+"\n");

        ListaNumerica vector1 = new ListaNumerica(3);
        vector1.agregar(-8.0);
        vector1.agregar(-3.0);
        vector1.agregar(10.0);
        vector1.imprimir();
        ListaNumerica vector2 = new ListaNumerica(3);
        vector2.agregar(2.0);
        vector2.agregar(-2.0);
        vector2.agregar(1.0);
        vector2.imprimir();
        SalidaPorDefecto.terminal("\nProbando esOrtogonal:\n"+vector1.esOrtogonal(vector2));

        ListaNumerica vectorU = new ListaNumerica(2);
        ListaNumerica vectorV = new ListaNumerica(2);
        vectorU.agregar(3);
        vectorU.agregar(-1);
        vectorV.agregar(-9);
        vectorV.agregar(3);
        SalidaPorDefecto.terminal("\nProbando esParalelo:\n"+vectorU.esParalelo(vectorV));

    }
}
