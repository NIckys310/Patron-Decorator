package com.game;

import com.game.model.BaseWarrior;
import com.game.model.Character;
import com.game.model.decorators.AttackBoostDecorator;
import com.game.model.decorators.DamageAbsorptionDecorator;
import com.game.model.decorators.SpeedBoostDecorator;

/**
 * Simulacion de una partida usando el patron Decorator.
 * Equipa efectos en secuencia y muestra el estado del jugador en consola.
 */
public class GameDemo {

    public static void main(String[] args) {
        System.out.println("=== SIMULACION DE JUEGO: PATRON DECORATOR ===\n");

        Character player = new BaseWarrior();
        mostrarEstado("Estado inicial", player);

        System.out.println(">> Equipando mejora de ataque...");
        player = new AttackBoostDecorator(player);
        mostrarEstado("Con mejora de ataque", player);

        System.out.println(">> Equipando mejora de velocidad...");
        player = new SpeedBoostDecorator(player);
        mostrarEstado("Con ataque y velocidad", player);

        System.out.println(">> Equipando absorcion de dano...");
        player = new DamageAbsorptionDecorator(player);
        mostrarEstado("Con los tres efectos", player);

        System.out.println(">> El enemigo ataca con 20 de dano...");
        player.takeDamage(20);
    }

    private static void mostrarEstado(String titulo, Character player) {
        System.out.println("--- " + titulo + " ---");
        System.out.println("Descripcion: " + player.getDescription());
        System.out.println("Ataque:      " + player.getAttack());
        System.out.println("Velocidad:   " + player.getSpeed());
        System.out.println();
    }
}