package com.game.model;

public class BaseWarrior implements Character{

    private int health = 100;

    @Override
    public int getAttack() {
        return 15;
    }
    @Override
    public int getSpeed() {
        return 10;
    }
    @Override
    public void takeDamage(int damage) {
        this.health -= damage;
        System.out.println("El guerrero recibio " + damage + " damage. Vida restante: " + health);
    }
    @Override
    public String getDescription() {
        return "Base Warrior";
    }
}