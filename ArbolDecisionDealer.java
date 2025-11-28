/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juegoblackjack;

// Clase auxiliar interna para los nodos del árbol
class NodoArbol {
    String accion;        // "Pedir" o "Plantarse"
    int condicionPuntaje; // Valor de corte (ej. 17)
    NodoArbol izquierda;  // Camino si puntaje < condicion
    NodoArbol derecha;    // Camino si puntaje >= condicion

    public NodoArbol(String accion, int condicionPuntaje) {
        this.accion = accion;
        this.condicionPuntaje = condicionPuntaje;
    }
}

public class ArbolDecisionDealer {
    private NodoArbol raiz;

    public ArbolDecisionDealer() {
        // Construcción del árbol según regla: < 17 pide, >= 17 se planta
        raiz = new NodoArbol("Evaluar", 17);
        raiz.izquierda = new NodoArbol("Pedir", 0);
        raiz.derecha = new NodoArbol("Plantarse", 0);
    }

    public String tomarDecision(int puntajeActual) {
        // [cite: 30, 31] Reglas del PDF
        if (puntajeActual < raiz.condicionPuntaje) {
            return raiz.izquierda.accion;
        } else {
            return raiz.derecha.accion;
        }
    }
}
