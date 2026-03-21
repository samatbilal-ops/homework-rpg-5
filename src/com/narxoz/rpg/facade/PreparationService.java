package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

public class PreparationService {
    public String prepare(HeroProfile hero, BossEnemy boss, AttackAction action) {
        if (hero == null || boss == null || action == null) {
            return "ERROR: Missing combatants or weapon. Preparation failed";
        }
        return "SUCCESS: " + hero.getName() + " steps into the dark dungeon, wielding a [" + action.getActionName() + "] to fight " + boss.getName() + "!";
    }
}