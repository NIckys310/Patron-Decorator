package com.game.model.decorators;

import com.game.model.Character;
import java.util.Objects;

public class ShieldDecorator extends EffectDecorator {
    public static final int DEFAULT_DAMAGE_REDUCTION_PERCENTAGE = 50;

    private final int damageReductionPercentage;

    public ShieldDecorator(Character character) {
        this(character, DEFAULT_DAMAGE_REDUCTION_PERCENTAGE);
    }

    public ShieldDecorator(Character character, int damageReductionPercentage) {
        super(Objects.requireNonNull(character, "character cannot be null"));

        if (damageReductionPercentage < 0 || damageReductionPercentage > 100) {
            throw new IllegalArgumentException("damage reduction percentage must be between 0 and 100");
        }

        this.damageReductionPercentage = damageReductionPercentage;
    }

    @Override
    public void takeDamage(int damage) {
        int remainingDamage = damage * (100 - damageReductionPercentage) / 100;
        super.takeDamage(remainingDamage);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Shield (" + damageReductionPercentage + "% reduction)";
    }
}
