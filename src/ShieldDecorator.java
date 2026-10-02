public class ShieldDecorator extends EffectDecorator {
    private static final int DAMAGE_REDUCTION_PERCENTAGE = 50;

    public ShieldDecorator(Character character) {
        super(character);
    }

    @Override
    public void takeDamage(int damage) {
        int reducedDamage = damage * (100 - DAMAGE_REDUCTION_PERCENTAGE) / 100;
        super.takeDamage(reducedDamage);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Shield";
    }
}
