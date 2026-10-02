package com.game.model.decorators;

import com.game.model.Character;
import java.util.Objects;

public class SpeedPotionDecorator extends EffectDecorator {
    public static final int DEFAULT_SPEED_BONUS = 10;

    private final int speedBonus;

    public SpeedPotionDecorator(Character character) {
        this(character, DEFAULT_SPEED_BONUS);
    }

    public SpeedPotionDecorator(Character character, int speedBonus) {
        super(Objects.requireNonNull(character, "character cannot be null"));

        if (speedBonus <= 0) {
            throw new IllegalArgumentException("speed bonus must be greater than zero");
        }

        this.speedBonus = speedBonus;
    }

    @Override
    public int getSpeed() {
        return super.getSpeed() + speedBonus;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Speed Potion (" + speedBonus + " speed)";
    }
}
