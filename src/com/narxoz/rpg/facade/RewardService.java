package com.narxoz.rpg.facade;

public class RewardService {
    public String determineReward(AdventureResult battleResult) {
        if (battleResult == null) {
            return "No reward data available";
        }
        if (battleResult.getWinner().equals("Draw")) {
            return "A minor consolation potion.";
        }
        if (!battleResult.getWinner().contains("Boss")) {
            return "Legendary Dungeon Chest & 5000 XP!";
        }
        return "Nothing but a bitter lesson in defeat...";
    }
}