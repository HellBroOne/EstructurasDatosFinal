package estructurasnolineales;

import entradasalida.EntradaPorDefecto;
import entradasalida.SalidaPorDefecto;
import estructurasLineales.auxiliares.NodoDoble;
import utilerias.comunes.Comparadores;

public class ArbolBinarioBusqueda extends ArbolBinario{
    @Override
    protected void crearArbol(NodoDoble subRaiz){
        //  --------- PREGUNTAR POR EL HIJO IZQUIERDO --------
        SalidaPorDefecto.terminal("¿El nodo "+subRaiz.getInfo()+" tiene hijo izquierdo? [S/N] ");
        String respuestaI = EntradaPorDefecto.consolaCadenas();
        if (respuestaI.equalsIgnoreCase("s")){
            SalidaPorDefecto.terminal("Dame el contenido del hijo izquierdo de "+subRaiz.getInfo()+": ");
            String infoIzq = EntradaPorDefecto.consolaCadenas();
            if (Comparadores.comparar(infoIzq, subRaiz.getInfo().toString()) < 0 ){
                NodoDoble nuevoNodoIzq = new NodoDoble(infoIzq);
                if (nuevoNodoIzq != null){
                    subRaiz.setLigaIzq(nuevoNodoIzq);
                    crearArbol(nuevoNodoIzq);
                }
            }
        }
        //  --------- PREGUNTAR POR EL HIJO DERECHO ---------
        SalidaPorDefecto.terminal("¿El nodo "+subRaiz.getInfo()+" tiene hijo derecho? [S/N] ");
        String respuestaD = EntradaPorDefecto.consolaCadenas();
        if (respuestaD.equalsIgnoreCase("s")){
            SalidaPorDefecto.terminal("Dame el contenido del hijo derecho de "+subRaiz.getInfo()+": ");
            String infoDer = EntradaPorDefecto.consolaCadenas();
            if (Comparadores.comparar(infoDer, subRaiz.getInfo().toString()) > 0 ){
                NodoDoble nuevoNodoDer = new NodoDoble(infoDer);
                if (nuevoNodoDer != null){
                    subRaiz.setLigaDer(nuevoNodoDer);
                    crearArbol(nuevoNodoDer);
                }
            }
        }

    }

    public boolean agregar(Object info){
        if (raiz == null){ //si ya hay raiz
            NodoDoble nuevoNodo = new NodoDoble(info);
            if (nuevoNodo != null){
                raiz = nuevoNodo;
                return true;
            } else {
                return false;
            }
        }else{ //si la raiz no es nula (ya hay raiz)
            return agregar(raiz, info);
        }
    }

    private boolean agregar(NodoDoble subraiz, Object info){
        if (Comparadores.comparar(info, subraiz.getInfo()) > 0){ //mayor
            if (subraiz.getLigaDer() != null){ //si la liga der. existe se va a recorrer hacia la derecha
                return agregar(subraiz.getLigaDer(), info);
            }else{ //es nula
                NodoDoble nuevoNodo = new NodoDoble(info);
                if (nuevoNodo != null){ //hay memoria?
                    subraiz.setLigaDer(nuevoNodo);
                    return true;
                }else{
                    return false;
                }
            }
        }else if (Comparadores.comparar(info, subraiz.getInfo()) < 0){ //menor
            if (subraiz.getLigaIzq() != null){ //si la liga izq existe se va a recorrer hacia la izquierda
                return agregar(subraiz.getLigaIzq(), info);
            }else{ //es nula
                NodoDoble nuevoNodo = new NodoDoble(info);
                if (nuevoNodo != null){ //hay memoria?
                    subraiz.setLigaIzq(nuevoNodo);
                    return true;
                }else{
                    return false;
                }
            }
        }else{ //igual
            return false;
        }
    }

    public Object buscar(Object info){
        return buscar(raiz, info);
    }

    private Object buscar(NodoDoble subraiz, Object info){
        if (subraiz != null){
            if (Comparadores.comparar(info, subraiz.getInfo()) > 0){ //mayor al solicitado
                return buscar(subraiz.getLigaDer(), info);
            }else if(Comparadores.comparar(info, subraiz.getInfo()) < 0){ //menor al solicitado
                return buscar(subraiz.getLigaIzq(), info);
            }else{ //mismo objeto
                return subraiz.getInfo();
            }
        }else{
            return null;
        }
    }

    public void eliminar(Object info){
        eliminar(raiz, raiz, info);
    }

    private void eliminar(NodoDoble subraiz, NodoDoble anterior, Object info){
        if (subraiz != null){
            if(Comparadores.comparar(info, subraiz.getInfo()) < 0){ //Si es menor
                eliminar(subraiz.getLigaIzq(), subraiz, info);
            }else{
                if(Comparadores.comparar(info, subraiz.getInfo()) > 0){ //es mayor
                    eliminar(subraiz.getLigaDer(), subraiz, info);
                } else { //es igual al eliminado
                    if (subraiz.getLigaIzq() != null && subraiz.getLigaDer() != null) { //tiene dos hijos
                        NodoDoble auxiliar = subraiz.getLigaIzq();
                        NodoDoble auxiliar1 = null;
                        boolean bo = false;
                        while (auxiliar.getLigaDer() != null) {
                            auxiliar1 = auxiliar;
                            auxiliar = auxiliar1.getLigaDer();
                            bo = true;
                        }
                        subraiz.setInfo(auxiliar.getInfo());
                        if (bo == true) {
                            auxiliar1.setLigaDer(auxiliar.getLigaIzq());
                        } else {
                            subraiz.setLigaIzq(auxiliar.getLigaIzq());
                        }
                    } else {
                        NodoDoble otro = subraiz;
                        if (otro.getLigaDer() == null){ //si el otro es nulo
                            if (otro.getLigaIzq() != null){
                                otro = subraiz.getLigaIzq();
                                if (anterior!=null){
                                    if (Comparadores.comparar(info, anterior.getInfo()) < 0){
                                        anterior.setLigaIzq(otro);
                                    } else {
                                        anterior.setLigaDer(otro);
                                    }
                                } else {
                                    raiz = otro;
                                }
                            } else {
                                if (anterior == null){
                                    raiz = null;
                                } else {
                                    if (Comparadores.comparar(info, anterior.getInfo()) < 0){
                                        anterior.setLigaIzq(null);
                                    } else {
                                        anterior.setLigaDer(null);
                                    }
                                }
                            }
                        } else { //la derecha no es nula
                            if (otro.getLigaIzq() == null){ //solo la liga izquierda es nula
                                otro = subraiz.getLigaDer();
                                if (anterior != null){
                                    if (Comparadores.comparar(info, anterior.getInfo()) < 0){
                                        anterior.setLigaIzq(otro);
                                    } else {
                                        anterior.setLigaDer(otro);
                                    }
                                }else{
                                    raiz = otro;
                                }
                            } //ps si no fue nulo, ambos fueron nulos
                        } //fin de 1.1.1.1.5
                    } //fin del que dice no tiene los dos hijos
                } // fin del que si es igual
            } //fin del que si es menor
        }else{
            SalidaPorDefecto.terminal("El dato no esta en el arbol.\n");
        }
    }
}
