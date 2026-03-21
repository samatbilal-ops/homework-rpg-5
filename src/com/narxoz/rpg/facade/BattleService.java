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
        int rounds = 0;

        result.addLine("--- BATTLE COMMENCE ---");

        while (hero.isAlive() && boss.isAlive() && rounds < 50) {
            rounds++;
            result.addLine("\n[Round " + rounds + "]");

            int heroDmg = action.getDamage();
            if (random.nextInt(100) < 10) {
                result.addLine(boss.getName() + " swiftly dodged the attack!");
            } else {
                boss.takeDamage(heroDmg);
                result.addLine(hero.getName() + " strikes with " + action.getActionName() + " for " + heroDmg + " DMG.");
                result.addLine("   -> Effects: " + action.getEffectSummary());
            }

            if (!boss.isAlive()) {
                result.addLine("*** " + boss.getName() + " collapses! ***");
                break;
            }

            int bossDmg = boss.getAttackPower();
            if (random.nextInt(100) < 20) {
                bossDmg = (int) (bossDmg * 1.5);
                result.addLine(boss.getName() + " unleashes a devastating heavy strike!");
            }
            hero.takeDamage(bossDmg);
            result.addLine(boss.getName() + " retaliates, hitting " + hero.getName() + " for " + bossDmg + " DMG.");

            if (!hero.isAlive()) {
                result.addLine("*** " + hero.getName() + " has fallen in battle! ***");
                break;
            }
        }

        result.setRounds(rounds);
        if (hero.isAlive() && !boss.isAlive()) {
            result.setWinner(hero.getName());
        } else if (!hero.isAlive() && boss.isAlive()) {
            result.setWinner(boss.getName());
        } else {
            result.setWinner("Draw");
        }
        return result;
    }
}