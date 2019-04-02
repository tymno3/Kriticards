package Cards;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
public class Enemies {
public static ArrayList<Image> enemyImages=new ArrayList<Image>();
	public static  ArrayList<Enemies> enemy= new ArrayList<Enemies>();
	public static boolean stun=false;
	public static boolean poison=false;
	private int enemyHealth;
	private int enemyDamage;
	public Enemies(int health, int damage) {
		enemyHealth=health;
		enemyDamage=damage;
		}
	/**
	 * 
	 * @param health enemy health
	 * @param damage ene,y damage
	 * @param Sprites enemy sprite
	 */

	public int getEnemyHealth() {
		return enemyHealth;
	}
	public int getEnemyDamage() {
		return enemyDamage;
	}
	public void damageEnemy(int damage) {
		enemyHealth=enemyHealth-damage;
	}
	public static void setStun(boolean something) {
	stun=something;
	}
	public static boolean getStun() {
		// TODO Auto-generated method stub
		return stun;
	}
	public boolean getPosion() {
		return poison;
	}
	public void setPoison(boolean setPoison) {
		poison=setPoison;
		
	}
}


