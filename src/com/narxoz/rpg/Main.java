package com.narxoz.rpg;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.decorator.BasicAttack;
import com.narxoz.rpg.decorator.CriticalFocusDecorator;
import com.narxoz.rpg.decorator.FireRuneDecorator;
import com.narxoz.rpg.decorator.PoisonCoatingDecorator;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.facade.AdventureResult;
import com.narxoz.rpg.facade.DungeonFacade;
import com.narxoz.rpg.hero.HeroProfile;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Homework 5 Demo: Decorator + Facade ===\n");

        HeroProfile hero = new HeroProfile("Batyr", 150);
        BossEnemy boss = new BossEnemy("Ancient Golem", 200, 20);

        AttackAction basic = new BasicAttack("Heavy Strike", 20);

        AttackAction ultimateAttack = new FireRuneDecorator(
                new PoisonCoatingDecorator(
                        new CriticalFocusDecorator(basic)));

        System.out.println("--- Decorator Preview ---");
        System.out.println("Base Action: " + basic.getActionName());
        System.out.println("Base Damage: " + basic.getDamage());
        System.out.println("Base Effects: " + basic.getEffectSummary());
        System.out.println();
        System.out.println("Ultimate Action: " + ultimateAttack.getActionName());
        System.out.println("Ultimate Damage: " + ultimateAttack.getDamage());
        System.out.println("Ultimate Effects: " + ultimateAttack.getEffectSummary());

        System.out.println("\n--- Facade Dungeon Run ---");

        DungeonFacade facade = new DungeonFacade().setRandomSeed(777L);
        AdventureResult result = facade.runAdventure(hero, boss, ultimateAttack);

        System.out.println("Winner: " + result.getWinner());
        System.out.println("Total Rounds: " + result.getRounds());
        System.out.println("Final Reward: " + result.getReward());

        System.out.println("\n--- Detailed Adventure Log ---");
        for (String line : result.getLog()) {
            System.out.println("> " + line);
        }

        System.out.println("\n=== Demo Complete ===");
    }
}