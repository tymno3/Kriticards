package com.gamge.game.entities;

import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.JFrame;

import Cards.Enemies;
import Cards.cards;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.applet.*;

import javax.imageio.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.List;

public class drawCards extends Canvas {
	private boolean start = false;
	private boolean unusable = false;
	static Player player = new Player(30, 3);
	public static int round;
	static boolean playerTurn = true;
	static boolean card1 = true;
	static boolean card2 = true;
	static boolean card3 = true;
	static boolean card4 = true;
	public static int cardNum = 0;
	static boolean card5 = true;
	Image topHalf;
	Image botHalf;
	Image background1;
	Image background2;
	Image greenCard;
	Image title;
	Image enemy1;
	Image bonethrowCard;
	Image drainCard;
	Image eatCard;

	public drawCards() throws IOException{
		declareCards();
		setSize(new Dimension(1200, 750));
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent evt) {
				moveIt(evt);
			}
		});
	}
	public void moveIt(KeyEvent evt) {
		switch (evt.getKeyCode()) {
		case KeyEvent.VK_ENTER:
			start = true;
			break;
		case KeyEvent.VK_1:
			if (card1) {
				card1Played();
				card1 = false;
			}
			break;
		case KeyEvent.VK_2:
			if (card2) {
				card2Played();
				card2 = false;
			}
			break;
		case KeyEvent.VK_3:
			if (card3) {
				card3Played();
				card3 = false;
			}
			break;
		case KeyEvent.VK_4:
			if (card4) {
				card4Played();
				card4 = false;
			}
			break;
		case KeyEvent.VK_5:
			if (card5) {
				card5Played();
				card5 = false;
			}
			break;
		case KeyEvent.VK_E:
			Player.setMana(0 - (3 - Player.getMana()));
			playerTurn = false;
			card1 = true;
			card2 = true;
			card3 = true;
			card4 = true;
			card5 = true;
			break;

		}
		repaint();
	}

	public void paint(Graphics g) {
		try {
			title = ImageIO.read(new File("res/imgSrc/KriticardsTitle.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (start == false)
			g.drawImage(title, 0, 0, this);
		if (start == true) {
			System.out.println("You currently have " + Player.getHealth() + " health.");
			g.drawImage(Enemies.enemyImages.get(round), 500, 150, this);
			System.out.println(round);
			g.drawString("Enemy Health: " + Enemies.enemy.get(round).getEnemyHealth(), 500, 50);
			g.drawString("Player Health: " + Player.getHealth(), 0, 25);
			g.drawString("Player Mana: " + Player.getMana(), 0, 50);
			if (Enemies.enemy.get(round).getEnemyHealth() <= 0) {
				round++;
			}
			for (int i = 0; i < 5; i++) {
				if (cardNum >= 15)
					cardNum = 0;
				g.drawImage(cards.cardImages.get(cardNum + i), i * 240, 410, this);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {

		JFrame frame = new JFrame("KritiCards");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		drawCards ex = new drawCards();
		System.out.println("welcome to KritiCards. Press keys 1-5 to play cards against the enemy");
		round = 0;
		frame.getContentPane().add(ex);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		ex.requestFocus();
		System.out.println("take your turn first, play a card.");
		while (round <= Enemies.enemy.size()) {
			if (cardNum > 20)
				cardNum = 0;
			playerTurn = true;
			playerTurn();
			cpuTurn();

			playerTurn = true;
			cardNum += 5;
		}
	}

	public static void playerTurn() {
		System.out.println("Its the players turn");
		playerTurn = true;
		while (playerTurn) {
		}
	}

	public static void cpuTurn() {
		System.out.println("its the enemies turn");

		if (Enemies.getStun()) {
			Enemies.setStun(false);
			return;
		}
		if (Enemies.enemy.get(round).getEnemyHealth() > 0)
			Player.setHealth(Enemies.enemy.get(round).getEnemyDamage());
		playerTurn = true;
	}

	public void card1Played() {

		System.out.println("Card1Played");
		if (Player.getMana() - cards.deck.get(cardNum).getMana() < 0) {
			return;
		}

		else {
			Player.setMana(cards.deck.get(cardNum).getMana());
		}

		Enemies.enemy.get(round).damageEnemy(cards.deck.get(cardNum).getDamage());
		if (cards.deck.get(cardNum).getStun())
			Enemies.setStun(true);
		Player.setHealth(-cards.deck.get(cardNum).getHealth());
		Player.setHealth(cards.deck.get(cardNum).getSelfDamage());
		Enemies.enemy.get(cardNum).setPoison(cards.deck.get(cardNum).getPoison());
		if (Enemies.enemy.get(cardNum).getPosion())
			Enemies.enemy.get(cardNum).damageEnemy((int) (Enemies.enemy.get(cardNum).getEnemyHealth() * .1));
	}

	public void card2Played() {
		cardNum += 1;
		System.out.println("Card2Played");
		if (Player.getMana() - cards.deck.get(cardNum).getMana() < 0) {
			return;
		} else {
			Player.setMana(Player.getMana() - cards.deck.get(cardNum).getMana());
		}
		Enemies.enemy.get(round).damageEnemy(cards.deck.get(cardNum).getDamage());
		System.out.println(Enemies.enemy.get(round).getEnemyHealth());
		if (cards.deck.get(cardNum + 1).getStun())
			Enemies.setStun(true);
		Player.setHealth(-cards.deck.get(cardNum).getHealth());
		Player.setHealth(cards.deck.get(cardNum).getSelfDamage());
		Enemies.enemy.get(cardNum).setPoison(cards.deck.get(cardNum).getPoison());
		if (Enemies.enemy.get(cardNum).getPosion())
			Enemies.enemy.get(cardNum).damageEnemy((int) (Enemies.enemy.get(cardNum).getEnemyHealth() * .1));
	}

	public void card3Played() {
		cardNum += 2;
		System.out.println("Card3Played");
		if (Player.getMana() - cards.deck.get(cardNum).getMana() < 0) {
			return;
		} else {
			Player.setMana(Player.getMana() - cards.deck.get(cardNum).getMana());
		}
		Enemies.enemy.get(round).damageEnemy(cards.deck.get(cardNum).getDamage());
		System.out.println(Enemies.enemy.get(round).getEnemyHealth());
		if (cards.deck.get(cardNum).getStun())
			Enemies.setStun(true);
		Player.setHealth(-cards.deck.get(cardNum).getHealth());
		Player.setHealth(cards.deck.get(cardNum).getSelfDamage());
		Enemies.enemy.get(cardNum).setPoison(cards.deck.get(cardNum).getPoison());
		if (Enemies.enemy.get(cardNum).getPosion())
			Enemies.enemy.get(cardNum).damageEnemy((int) (Enemies.enemy.get(cardNum).getEnemyHealth() * .1));
	}

	public void card4Played() {
		cardNum += 3;
		// if(cardNum==cards.deck.size())
		// cardNum=0;
		System.out.println("Card4Played");
		if (Player.getMana() - cards.deck.get(cardNum).getMana() < 0) {
			return;
		} else {
			Player.setMana(Player.getMana() - cards.deck.get(cardNum).getMana());
		}
		Enemies.enemy.get(round).damageEnemy(cards.deck.get(cardNum).getDamage());
		System.out.println(Enemies.enemy.get(round).getEnemyHealth());

		if (cards.deck.get(cardNum).getStun())
			Enemies.setStun(true);
		Player.setHealth(-cards.deck.get(cardNum).getHealth());
		Player.setHealth(cards.deck.get(cardNum).getSelfDamage());
		Enemies.enemy.get(cardNum).setPoison(cards.deck.get(cardNum).getPoison());
		if (Enemies.enemy.get(cardNum).getPosion())
			Enemies.enemy.get(cardNum).damageEnemy((int) (Enemies.enemy.get(cardNum).getEnemyHealth() * .1));
	}

	public void card5Played() {
		cardNum += 4;
		System.out.println("Card5Played");
		if (Player.getMana() - cards.deck.get(cardNum).getMana() < 0) {
			return;
		} else {
			Player.setMana(Player.getMana() - cards.deck.get(cardNum).getMana());
		}
		Enemies.enemy.get(round).damageEnemy(cards.deck.get(cardNum).getDamage());
		System.out.println(Enemies.enemy.get(round).getEnemyHealth());
		if (cards.deck.get(cardNum).getStun())
			Enemies.setStun(true);
		Player.setHealth(-cards.deck.get(cardNum).getHealth());
		Player.setHealth(cards.deck.get(cardNum).getSelfDamage());
		Enemies.enemy.get(cardNum).setPoison(cards.deck.get(cardNum).getPoison());
		if (Enemies.enemy.get(cardNum).getPosion()) {
			Enemies.enemy.get(cardNum).damageEnemy((int) (Enemies.enemy.get(cardNum).getEnemyHealth() * .1));
		}
	}
	private void declareCards() throws IOException {
		
		File monsters1 = new File("res/Monsters/monster1.png");
		Enemies.enemyImages.add((ImageIO.read(monsters1)));
		File monsters2 = new File("res/Monsters/monster2.png");
		Enemies.enemyImages.add((ImageIO.read(monsters2)));
		File monsters3 = new File("res/Monsters/monster3.png");
		Enemies.enemyImages.add((ImageIO.read(monsters3)));
		File monsters4 = new File("res/Monsters/monster4.png");
		Enemies.enemyImages.add((ImageIO.read(monsters4)));
		File minibosses1 = new File("res/Monsters/miniBoss1.png");
		Enemies.enemyImages.add((ImageIO.read(minibosses1)));
		File monsters5 = new File("res/Monsters/monster5.png");
		Enemies.enemyImages.add((ImageIO.read(monsters5)));
		// File monsters6=new File("res/Monsters/monster6.png");
		// Enemies.enemyImages.add((ImageIO.read(monsters6)));
		File monsters7 = new File("res/Monsters/monster7.png");
		Enemies.enemyImages.add((ImageIO.read(monsters7)));
		File monsters8 = new File("res/Monsters/monster8.png");
		Enemies.enemyImages.add((ImageIO.read(monsters8)));
		File minibosses2 = new File("res/Monsters/miniBoss2.png");
		Enemies.enemyImages.add((ImageIO.read(minibosses2)));
		File monsters9 = new File("res/Monsters/monster9.png");
		Enemies.enemyImages.add((ImageIO.read(monsters9)));
		File monsters10 = new File("res/Monsters/monster10.png");
		Enemies.enemyImages.add((ImageIO.read(monsters10)));
		File finalbosses = new File("res/Monsters/monsterA1.png");
		Enemies.enemyImages.add((ImageIO.read(finalbosses)));
		Enemies monster1 = new Enemies(10, 3);
		Enemies.enemy.add(monster1);
		Enemies monster2 = new Enemies(15, 6);
		Enemies.enemy.add(monster2);
		Enemies monster3 = new Enemies(18, 3);
		Enemies.enemy.add(monster3);
		Enemies monster4 = new Enemies(20, 3);
		Enemies.enemy.add(monster4);
		Enemies miniboss1 = new Enemies(30, 8);
		Enemies.enemy.add(miniboss1);
		Enemies monster5 = new Enemies(25, 4);
		Enemies.enemy.add(monster5);
		Enemies monster6 = new Enemies(26, 5);
		Enemies.enemy.add(monster6);
		Enemies monster7 = new Enemies(27, 6);
		Enemies.enemy.add(monster7);
		Enemies monster8 = new Enemies(28, 7);
		Enemies.enemy.add(monster8);
		Enemies miniboss2 = new Enemies(40, 10);
		Enemies.enemy.add(miniboss2);
		Enemies monster9 = new Enemies(5, 41);
		Enemies.enemy.add(monster9);
		Enemies monster10 = new Enemies(2, 46);
		Enemies.enemy.add(monster10);
		Enemies finalboss = new Enemies(50, 10);
		Enemies.enemy.add(finalboss);
		File SwordSlash = new File("res/Cards/Slash.png");
		cards.cardImages.add((ImageIO.read(SwordSlash)));
		File Frown = new File("res/Cards/Frown.png");
		cards.cardImages.add((ImageIO.read(Frown)));
		File Slam = new File("res/Cards/Slam.png");
		cards.cardImages.add((ImageIO.read(Slam)));
		File Poison = new File("res/Cards/Poison.png");
		cards.cardImages.add((ImageIO.read(Poison)));
		File Eat = new File("res/Cards/Eat.png");
		cards.cardImages.add((ImageIO.read(Eat)));
		File Drink = new File("res/Cards/Drink.png");
		cards.cardImages.add((ImageIO.read(Drink)));
		File Headbutt = new File("res/Cards/Headbut.png");
		cards.cardImages.add((ImageIO.read(Headbutt)));
		File Drain = new File("res/Cards/Drain.png");
		cards.cardImages.add((ImageIO.read(Drain)));
		File Bite = new File("res/Cards/Bite.png");
		cards.cardImages.add((ImageIO.read(Bite)));
		File Slap = new File("res/Cards/Slap.png");
		cards.cardImages.add((ImageIO.read(Slap)));
		File BoneThrow = new File("res/Cards/B.png");
		cards.cardImages.add((ImageIO.read(BoneThrow)));
		File Chernobyl = new File("res/Cards/Chernobyle.png");
		cards.cardImages.add((ImageIO.read(Chernobyl)));
		File Dance = new File("res/Cards/Dance.png");
		cards.cardImages.add((ImageIO.read(Dance)));
		File MegaFireball = new File("res/Cards/Megafireball.png");
		cards.cardImages.add((ImageIO.read(MegaFireball)));
		File ManaRefill = new File("res/Cards/ManaRefill.png");
		cards.cardImages.add((ImageIO.read(ManaRefill)));
		File ManaResurge = new File("res/Cards/ManaResurge.png");
		cards.cardImages.add((ImageIO.read(ManaResurge)));
		File Friendship = new File("res/Cards/Friendship.png");
		cards.cardImages.add((ImageIO.read(Friendship)));
		File BoostBonkBam = new File("res/Cards/BoostBonkBam.png");
		cards.cardImages.add((ImageIO.read(BoostBonkBam)));
		File Concussion = new File("res/Cards/Concussion.png");
		cards.cardImages.add((ImageIO.read(Concussion)));
		System.out.println("Something");

		cards swordSlash = new cards("Sword Slash", 1, 2, 0, 0, false, false, false);
		cards.deck.add(swordSlash);
		cards frown = new cards("Frown", 3, 8, 0, 0, false, false, false);
		cards.deck.add(frown);
		cards slam = new cards("Slam", 3, 5, 0, 0, false, true, false);
		cards.deck.add(slam);
		cards poison = new cards("Poison", 3, 0, 0, 0, true, false, false);
		cards.deck.add(poison);
		cards eat = new cards("Eat", 2, 0, 0, 5, false, false, false);
		cards.deck.add(eat);
		cards drink = new cards("Drink", 1, 0, 0, 2, false, false, false);
		cards.deck.add(drink);
		cards headbutt = new cards("Headbutt", 2, 8, 2, 0, false, false, false);
		cards.deck.add(headbutt);
		cards drain = new cards("Drain", 3, 5, 0, 5, false, true, false);
		cards.deck.add(drain);
		cards bite = new cards("Bite", 3, 11, 0, 0, false, false, true);
		cards.deck.add(bite);
		cards slap = new cards("Slap", 2, 5, 0, 0, false, false, false);
		cards.deck.add(slap);
		cards boneThrow = new cards("Bone Throw", 0, 6, 3, 0, false, false, false);
		cards.deck.add(boneThrow);
		cards chernobyl = new cards("Chernobyl", 1, 0, 0, 0, true, false, true);
		cards.deck.add(chernobyl);
		cards dance = new cards("Dance", 1, 0, 0, 0, false, false, false);
		cards.deck.add(dance);
		cards megaFireball = new cards("Mega Fireball", 3, 15, 10, 0, false, false, false);
		cards.deck.add(megaFireball);
		cards manaRefill = new cards("Mana Refill", -3, 0, 0, 0, false, false, false);
		cards.deck.add(manaRefill);
		cards manaResurge = new cards("Mana Resurge", -3, 0, 0, 3, false, false, false);
		cards.deck.add(manaResurge);
		cards friendship = new cards("Friendship", 1, -5, 0, 7, false, false, false);
		cards.deck.add(friendship);
		cards boostBonkBam = new cards("Boost, Bonk, Bam", 3, 3, 0, 3, false, true, false);
		cards.deck.add(boostBonkBam);
		cards concussion = new cards("Concussion", 2, 12, 0, 0, false, true, false);
		cards.deck.add(concussion);
	}
}
