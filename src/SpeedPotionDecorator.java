public class SpeedPotionDecorator extends EffectDecorator {
    private static final int SPEED_BONUS = 10;

    public SpeedPotionDecorator(Character character) {
        super(character);
    }

    @Override
    public int getSpeed() {
        return super.getSpeed() + SPEED_BONUS;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Speed Potion";
    }
}
