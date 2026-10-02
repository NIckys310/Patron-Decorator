package com.game;

import com.game.model.BaseWarrior;
import com.game.model.Character;
import com.game.model.decorators.AttackBoostDecorator;
import com.game.model.decorators.DamageAbsorptionDecorator;
import com.game.model.decorators.SpeedBoostDecorator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas de la acumulacion de efectos de los decoradores.
 * Calculan el bono de cada decorador (decorado - base), asi que funcionan
 * sin importar el valor exacto que haya elegido el Integrante 2.
 */
class CharacterTest {

    /** Personaje falso que solo registra cuanto dano le llega. */
    static class Recorder implements Character {
        int received = 0;
        @Override public int getAttack() { return 0; }
        @Override public int getSpeed() { return 0; }
        @Override public void takeDamage(int damage) { received += damage; }
        @Override public String getDescription() { return "Recorder"; }
    }

    @Test
    void personajeBaseTieneStatsIniciales() {
        Character base = new BaseWarrior();
        assertEquals(15, base.getAttack());
        assertEquals(10, base.getSpeed());
        assertEquals("Base Warrior", base.getDescription());
    }

    @Test
    void mejoraDeAtaqueSumaAtaqueSinTocarVelocidad() {
        Character base = new BaseWarrior();
        Character player = new AttackBoostDecorator(base);
        assertTrue(player.getAttack() > base.getAttack());
        assertEquals(base.getSpeed(), player.getSpeed());
    }

    @Test
    void mejoraDeVelocidadSumaVelocidadSinTocarAtaque() {
        Character base = new BaseWarrior();
        Character player = new SpeedBoostDecorator(base);
        assertTrue(player.getSpeed() > base.getSpeed());
        assertEquals(base.getAttack(), player.getAttack());
    }

    @Test
    void dosMejorasDeAtaqueSeAcumulan() {
        Character base = new BaseWarrior();
        int bonus = new AttackBoostDecorator(base).getAttack() - base.getAttack();
        Character doble = new AttackBoostDecorator(new AttackBoostDecorator(base));
        assertEquals(base.getAttack() + 2 * bonus, doble.getAttack());
    }

    @Test
    void ataqueYVelocidadSeAcumulanJuntos() {
        Character base = new BaseWarrior();
        int bonusAtk = new AttackBoostDecorator(base).getAttack() - base.getAttack();
        int bonusSpd = new SpeedBoostDecorator(base).getSpeed() - base.getSpeed();

        Character player = new SpeedBoostDecorator(new AttackBoostDecorator(base));
        assertEquals(base.getAttack() + bonusAtk, player.getAttack());
        assertEquals(base.getSpeed() + bonusSpd, player.getSpeed());
    }

    @Test
    void elOrdenDeLosDecoradoresNoAlteraElResultado() {
        Character a = new SpeedBoostDecorator(new AttackBoostDecorator(new BaseWarrior()));
        Character b = new AttackBoostDecorator(new SpeedBoostDecorator(new BaseWarrior()));
        assertEquals(a.getAttack(), b.getAttack());
        assertEquals(a.getSpeed(), b.getSpeed());
    }

    @Test
    void sinAbsorcionElDanoLlegaCompleto() {
        Recorder objetivo = new Recorder();
        objetivo.takeDamage(20);
        assertEquals(20, objetivo.received);
    }

    @Test
    void absorcionReduceElDanoRecibido() {
        Recorder objetivo = new Recorder();
        Character player = new DamageAbsorptionDecorator(objetivo);
        player.takeDamage(20);
        assertTrue(objetivo.received < 20, "La absorcion debe reducir el dano");
        assertTrue(objetivo.received >= 0, "El dano recibido no puede ser negativo");
    }

    @Test
    void dosAbsorcionesReducenAunMasElDano() {
        Recorder uno = new Recorder();
        new DamageAbsorptionDecorator(uno).takeDamage(20);

        Recorder dos = new Recorder();
        new DamageAbsorptionDecorator(new DamageAbsorptionDecorator(dos)).takeDamage(20);

        assertTrue(dos.received < uno.received, "Dos absorciones deben absorber mas que una");
    }

    @Test
    void descripcionIncluyeTodosLosEfectos() {
        Character player = new DamageAbsorptionDecorator(
                new SpeedBoostDecorator(
                        new AttackBoostDecorator(new BaseWarrior())));
        String desc = player.getDescription();
        assertTrue(desc.startsWith("Base Warrior"));
        assertTrue(desc.length() > "Base Warrior".length());
    }
}
