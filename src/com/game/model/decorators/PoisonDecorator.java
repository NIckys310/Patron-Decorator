package com.game.model.decorators;

import com.game.model.Character;
import java.util.Objects;

public class PoisonDecorator extends EffectDecorator {
    public static final int DEFAULT_ATTACK_PENALTY = 5;

    private final int attackPenalty;

    public PoisonDecorator(Character character) {
        this(character, DEFAULT_ATTACK_PENALTY);
    }

    public PoisonDecorator(Character character, int attackPenalty) {
        super(Objects.requireNonNull(character, "character cannot be null"));

        if (attackPenalty <= 0) {
            throw new IllegalArgumentException("attack penalty must be greater than zero");
        }

        this.attackPenalty = attackPenalty;
    }

    @Override
    public int getAttack() {
        return Math.max(0, super.getAttack() - attackPenalty);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Poison (-" + attackPenalty + " attack)";
    }
}
