package Cards;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class cards {
private String name;
private int manaCost, cardDamage, cardHealth,selfDamage ;
private boolean poison, stun,poisonSelf;
public static ArrayList<cards> deck= new ArrayList<cards>();
public static ArrayList<Image> cardImages=new ArrayList<Image>();
/**
 * 
 * @param cardName- Card Name
 * @param mana- Mana cost
 * @param damage- Damage dealt
 * @param health- Health restored
 * @param card- Card Image
 * @throws IOException 
 */
	public cards(String cardName, int mana, int damage, int damageself, int health, boolean poisons, boolean stun, boolean poisonself ) throws IOException {
		
		name=cardName;
		manaCost=mana;
		cardDamage=damage;
		cardHealth=health;
	//	cardImage=card;
		selfDamage=damageself;
		this.poison=poisons;
		this.stun=stun;
		poisonSelf=poisonself;
	}
	public int getDamage() {
		return cardDamage;
	}
	public int getMana() {
		return manaCost;
	}
	public int getHealth() {
		return cardHealth;
	}
	public String getName() {
		return name;
	}
	public  int getSelfDamage() {
		return selfDamage;
	}
	public boolean getPoison() {
		return poison;
	}
	public boolean getStun() {
		return stun;
	}
	
/*	private String name;
	private int manaCost, cardDamage, cardHealth,selfDamage ;
	private boolean poison, stun,poisonSelf;
	private BufferedImage cardImage;
	*/
}


