/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juegoblackjack;


public class Jugador {
    String nombre;
    Carta[] mano;
    int cantidadCartas;
    int puntaje;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.mano = new Carta[20]; 
        this.cantidadCartas = 0;
        this.puntaje = 0;
    }

    public void recibirCarta(Carta c) {
        if (cantidadCartas < mano.length) {
            mano[cantidadCartas++] = c;
            calcularPuntaje();
        }
    }

    public void calcularPuntaje() {
        int total = 0;
        int aces = 0;
        
        for (int i = 0; i < cantidadCartas; i++) {
            total += mano[i].getValor();
            if (mano[i].toString().startsWith("A")) {
                aces++;
            }
        }
        
        while (total > 21 && aces > 0) {
            total -= 10;
            aces--;
        }
        this.puntaje = total;
    }

    public void mostrarMano() {
        for (int i = 0; i < cantidadCartas; i++) {
            System.out.println(mano[i]);
        }
        System.out.println("Puntaje: " + puntaje);
    }

    // --- NUEVO MÉTODO AGREGADO ---
    public void resetearMano() {
        this.cantidadCartas = 0;
        this.puntaje = 0;
        // No es necesario borrar el array, simplemente sobrescribiremos los índices
        // cuando cantidadCartas vuelva a empezar de 0.
    }
}