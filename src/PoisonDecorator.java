public class PoisonDecorator extends EffectDecorator {
    private static final int ATTACK_PENALTY = 5;

    public PoisonDecorator(Character character) {
        super(character);
    }

    @Override
    public int getAttack() {
        return Math.max(0, super.getAttack() - ATTACK_PENALTY);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Poison";
    }
}
