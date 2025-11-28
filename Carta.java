/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juegoblackjack;

// Archivo: Carta.java
public class Carta {
    private String palo;
    private String numero;
    private int valor;

    public Carta(String numero, String palo, int valor) {
        this.numero = numero;
        this.palo = palo;
        this.valor = valor;
    }

    public int getValor() { return valor; }
    
    @Override
    public String toString() {
        return numero + " de " + palo;
    }
}



