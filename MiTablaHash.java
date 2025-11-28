/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juegoblackjack;

// Clase auxiliar para los pares Clave-Valor en el mapa
class EntradaMapa {
    String clave;
    Jugador valor;
    EntradaMapa siguiente; // Para manejar colisiones

    public EntradaMapa(String clave, Jugador valor) {
        this.clave = clave;
        this.valor = valor;
        this.siguiente = null;
    }
}

public class MiTablaHash {
    private EntradaMapa[] tabla;
    private int capacidad = 10; // Tamaño inicial arbitrario

    public MiTablaHash() {
        tabla = new EntradaMapa[capacidad];
    }

    // Función hash simple
    private int hash(String clave) {
        return Math.abs(clave.hashCode()) % capacidad;
    }

    public void put(String clave, Jugador valor) {
        int indice = hash(clave);
        EntradaMapa nuevo = new EntradaMapa(clave, valor);
        
        if (tabla[indice] == null) {
            tabla[indice] = nuevo;
        } else {
            // Manejo de colisión: encadenamiento (agregar al final)
            EntradaMapa actual = tabla[indice];
            while (actual.siguiente != null) {
                if (actual.clave.equals(clave)) {
                    actual.valor = valor; // Actualizar si ya existe
                    return;
                }
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
    }

    public Jugador get(String clave) {
        int indice = hash(clave);
        EntradaMapa actual = tabla[indice];
        
        while (actual != null) {
            if (actual.clave.equals(clave)) {
                return actual.valor;
            }
            actual = actual.siguiente;
        }
        return null;
    }
}
