/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juegoblackjack;

public class MiCola {
    // Usamos String porque guardaremos los nombres de los jugadores para los turnos
    private Nodo<String> frente;
    private Nodo<String> finalCola;

    public void encolar(String nombreJugador) {
        Nodo<String> nuevo = new Nodo<>(nombreJugador);
        if (finalCola != null) {
            finalCola.siguiente = nuevo;
        }
        finalCola = nuevo;
        if (frente == null) {
            frente = finalCola;
        }
    }

    public String desencolar() {
        if (frente == null) return null;
        
        String dato = frente.dato;
        frente = frente.siguiente;
        
        if (frente == null) {
            finalCola = null;
        }
        return dato;
    }
    
    public boolean estaVacia() {
        return frente == null;
    }
}
