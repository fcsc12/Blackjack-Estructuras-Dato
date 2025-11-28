/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package juegoblackjack;

import java.util.Scanner;

public class BlackjackJuego {
    private MiListaEnlazada baraja;
    private MiPila historial;
    private MiCola turnos;
    private MiTablaHash jugadores;
    private ArbolDecisionDealer cerebroDealer;
    
    private Scanner scanner;

    public BlackjackJuego() {
        // Inicializamos las estructuras vacías, se llenarán en prepararNuevaPartida
        baraja = new MiListaEnlazada();
        historial = new MiPila();
        turnos = new MiCola();
        jugadores = new MiTablaHash();
        cerebroDealer = new ArbolDecisionDealer();
        scanner = new Scanner(System.in);
    }

    // Método para resetear baraja, historial y manos de jugadores
    private void prepararNuevaPartida(String nombreHumano) {
        // 1. Reiniciar baraja y barajar
        baraja = new MiListaEnlazada(); 
        inicializarBaraja();

        // 2. Reiniciar historial (Stack vacía)
        historial = new MiPila();

        // 3. Reiniciar cola de turnos
        turnos = new MiCola();
        turnos.encolar(nombreHumano);
        turnos.encolar("Dealer");

        // 4. Limpiar manos de los jugadores existentes
        Jugador jHumano = jugadores.get(nombreHumano);
        if (jHumano != null) jHumano.resetearMano();
        
        Jugador jDealer = jugadores.get("Dealer");
        if (jDealer != null) jDealer.resetearMano();

        // 5. Repartir cartas iniciales (2 a cada uno)
        repartirCarta(nombreHumano);
        repartirCarta("Dealer");
        repartirCarta(nombreHumano);
        repartirCarta("Dealer");
    }

    private void inicializarBaraja() {
        String[] palos = {"Corazones", "Diamantes", "Treboles", "Picas"};
        String[] numeros = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        int[] valores = {11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};

        Carta[] tempBaraja = new Carta[52];
        int idx = 0;
        for (String palo : palos) {
            for (int i = 0; i < numeros.length; i++) {
                tempBaraja[idx++] = new Carta(numeros[i], palo, valores[i]);
            }
        }

        // Shuffle
        for (int i = 0; i < tempBaraja.length; i++) {
            int random = (int)(Math.random() * tempBaraja.length);
            Carta temp = tempBaraja[i];
            tempBaraja[i] = tempBaraja[random];
            tempBaraja[random] = temp;
        }

        for (Carta c : tempBaraja) {
            baraja.agregar(c);
        }
    }

    private void repartirCarta(String nombreJugador) {
        Carta c = baraja.eliminarPrimero(); 
        if (c != null) {
            Jugador j = jugadores.get(nombreJugador);
            j.recibirCarta(c);
            historial.push(c);
        }
    }

    public void iniciar() {
        System.out.println("Bienvenido a Blackjack version consola");
        System.out.print("Ingrese su nombre: ");
        String nombreHumano = scanner.nextLine();
        
        // Crear jugadores solo una vez
        jugadores.put(nombreHumano, new Jugador(nombreHumano));
        jugadores.put("Dealer", new Jugador("Dealer"));

        boolean seguirJugando = true;

        // Bucle para repetir el juego
        while (seguirJugando) {
            System.out.println(" INICIANDO NUEVA PARTIDA ");
            prepararNuevaPartida(nombreHumano);
            loopJuego(nombreHumano);

            System.out.print("Desea jugar otra partida? (s/n): ");
            String respuesta = scanner.nextLine();
            if (!respuesta.equalsIgnoreCase("s")) {
                seguirJugando = false;
                System.out.println("Gracias por jugar. Hasta luego!");
            }
        }
    }

    private void loopJuego(String humano) {
        while (!turnos.estaVacia()) {
            String turnoActual = turnos.desencolar(); 
            Jugador jugadorActual = jugadores.get(turnoActual);

            System.out.println("\nTurno de: " + turnoActual);
            
            if (!turnoActual.equals("Dealer")) {
                boolean plantado = false;
                while (!plantado && jugadorActual.puntaje < 21) {
                    System.out.println(turnoActual + " tiene:");
                    jugadorActual.mostrarMano();
                    
                    System.out.print("Desea otra carta? (s/n): ");
                    String opcion = scanner.nextLine();
                    
                    if (opcion.equalsIgnoreCase("s")) {
                        repartirCarta(turnoActual);
                        if (jugadorActual.puntaje > 21) {
                            System.out.println("Te pasaste de 21!");
                        }
                    } else {
                        plantado = true;
                    }
                }
            } else {
                // Turno Dealer
                System.out.println("Dealer tiene:");
                jugadorActual.mostrarMano();
                
                boolean termino = false;
                while (!termino) {
                    String decision = cerebroDealer.tomarDecision(jugadorActual.puntaje);
                    
                    if (decision.equals("Pedir")) {
                        System.out.println("El dealer toma una carta...");
                        repartirCarta("Dealer");
                        System.out.println("Nuevo puntaje Dealer: " + jugadorActual.puntaje);
                        
                        if (jugadorActual.puntaje > 21) {
                            System.out.println("Dealer se paso de 21.");
                            termino = true;
                        }
                    } else {
                        System.out.println("El dealer se planta.");
                        termino = true;
                    }
                    try { Thread.sleep(1000); } catch (Exception e) {} 
                }
            }
        }
        determinarGanador(humano);
    }

    private void determinarGanador(String humano) {
        System.out.println("--- RESULTADOS DE LA RONDA ---");
        Jugador jHumano = jugadores.get(humano);
        Jugador jDealer = jugadores.get("Dealer");

        System.out.println(humano + " puntaje: " + jHumano.puntaje);
        System.out.println("Dealer puntaje: " + jDealer.puntaje);
        System.out.println("------------------------------");
        
        if (jHumano.puntaje > 21) {
            System.out.println("Perdiste. Te pasaste de 21.");
        } else if (jDealer.puntaje > 21) {
            System.out.println("Ganaste! El dealer se paso.");
        } else if (jHumano.puntaje > jDealer.puntaje) {
            System.out.println("¡" + humano + " gana!");
        } else if (jHumano.puntaje < jDealer.puntaje) {
            System.out.println("Dealer gana.");
        } else {
            System.out.println("Empate.");
        }
    }

    public static void main(String[] args) {
        new BlackjackJuego().iniciar();
    }
}