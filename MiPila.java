/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juegoblackjack;

public class MiPila {
    private Nodo<Carta> tope;

    // Push: Agregar al tope
    public void push(Carta carta) {
        Nodo<Carta> nuevo = new Nodo<>(carta);
        nuevo.siguiente = tope;
        tope = nuevo;
    }

    // Visualizar historial sin eliminar datos
    public void mostrarHistorial() {
        Nodo<Carta> actual = tope;
        System.out.println("--- Historial de Cartas Jugadas (Stack) ---");
        while (actual != null) {
            System.out.println(actual.dato);
            actual = actual.siguiente;
        }
    }
}