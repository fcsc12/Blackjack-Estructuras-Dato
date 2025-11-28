/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juegoblackjack;

public class MiListaEnlazada {
    private Nodo<Carta> cabeza;
    private int tamano;

    public MiListaEnlazada() {
        this.cabeza = null;
        this.tamano = 0;
    }

    public void agregar(Carta carta) {
        Nodo<Carta> nuevo = new Nodo<>(carta);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo<Carta> actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
        tamano++;
    }

    // Simula el acto de "robar" carta eliminando la primera de la lista
    public Carta eliminarPrimero() {
        if (cabeza == null) return null;
        
        Carta carta = cabeza.dato;
        cabeza = cabeza.siguiente;
        tamano--;
        return carta;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }
}
