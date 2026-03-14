
package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;
import java.util.Random;

public class BattleService {
    private Random random = new Random(1L);

    public BattleService setRandomSeed(long seed) {
        this.random = new Random(seed);
        return this;
    }

    public AdventureResult battle(HeroProfile hero, BossEnemy boss, AttackAction action) {
        AdventureResult result = new AdventureResult();
        int round = 1;

        while (hero.isAlive() && boss.isAlive() && round <= 10) {
            result.addLine("Round " + round + ":");

            // Hero attacks
            int heroDmg = action.getDamage();
            boss.takeDamage(heroDmg);
            result.addLine(hero.getName() + " uses " + action.getActionName() + " for " + heroDmg + " damage!");

            if (!boss.isAlive()) {
                result.setWinner(hero.getName());
                result.addLine(boss.getName() + " has been defeated!");
                break;
            }

            // Boss attacks
            int bossDmg = boss.getAttackPower() + random.nextInt(5);
            hero.takeDamage(bossDmg);
            result.addLine(boss.getName() + " strikes back for " + bossDmg + " damage!");

            if (!hero.isAlive()) {
                result.setWinner(boss.getName());
                result.addLine(hero.getName() + " has fallen in battle...");
                break;
            }
            round++;
        }

        result.setRounds(round > 10 ? 10 : round);
        if (result.getWinner() == null) result.setWinner("Draw");

        return result;
    }
}
