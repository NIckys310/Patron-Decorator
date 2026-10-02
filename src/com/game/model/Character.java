package com.game.model;

// Interfaz base del componente principal
public interface Character {
    int getAttack();
    int getSpeed();
    void takeDamage(int damage);
    String getDescription();
}