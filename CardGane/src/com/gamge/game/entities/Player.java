package com.gamge.game.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
public class Player {
	public static int playerHealth, playerMaxHealth,numMana, tempMana;
	public Player(int health, int mana) {
		playerMaxHealth=health;
		playerHealth=playerMaxHealth;
		numMana=mana;
		tempMana=mana;
	}
	public static int getHealth() {
		return playerHealth;
	}
	public static int getMana() {
		return tempMana;
	}
	public static void setMana(int cost) {
		tempMana-=cost;
	}
	public static void setHealth(int damage) {
		playerHealth= playerHealth-damage;
		if(playerHealth>playerMaxHealth)
			playerHealth= playerMaxHealth;
	}
}


