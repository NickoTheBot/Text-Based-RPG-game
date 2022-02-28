import java.util.Scanner;
import java.util.Random;

public class rpg{
static int hp,ap,dmg,hdmg,ehp,edmg,lvl,mana,crit;
static int hPotion = 5;
static String name, enemy;
static boolean battle = true;
static Scanner scan = new Scanner(System.in);
static Random rng = new Random();
	public static void main(String[] args){
	intro();
	}
	public static void intro(){
	System.out.println("CXXXXX{}::::::::::::::::::::::>");
	System.out.printf("******Text RPG****** %n");
	System.out.println("CXXXXX{}::::::::::::::::::::::>");
	System.out.printf("%n%n ~What is your name?%n>");
	name = scan.nextLine();
	
	System.out.printf("%nHello %s, you are a warrior, and you will fight undead enemies and goblins. Have fun! %n (Classes and specializations coming soon) %n",name);
	System.out.println("The journey has started...");
	dungeon0();
	}
	public static void crit(){
	crit =  rng.nextInt(10+1);
	if (crit<=2){
	dmg = 5;
	hdmg = (dmg*2)+10;
	System.out.printf("%n%s slipped, dealing less damage.%n ",name);
		}
	else if (crit>2 && crit<6){
	dmg = 15;
	hdmg = (dmg*2)+10;
		}
	else if (crit>=6 && crit<8){
	dmg = 25;
	hdmg = (dmg*2)+10;
		}
	else if (crit>=8 && crit<=9){
	dmg = 35;
	hdmg = (dmg*2)+10;
	System.out.printf("%n~CRITICAL~%n");
		}
	else if (crit==10){
	dmg = 70;
	hdmg = (dmg*2)+10;
	System.out.printf("%n~MEGA CRITICAL~%n");
		}
	else {
	dmg = 20;
	}
	}
	public static void dungeon0(){
	hp = 100;
	ap = 100;
	enemy = "Goblin";
	ehp = 50;
	edmg = 5;
	System.out.printf("%nA %s blocks your path, and wishes to kill %s %n",enemy,name);
	battle();
	}
	public static void dungeon1(){
	enemy = "Undead Zombie";
	ehp = 75;
	edmg = 8;
	System.out.printf("%n A %s blocks your path, and wishes to kill %s %n",enemy,name);
	battle = true;
	battle(); 
	}
	public static void dungeon2(){
	enemy = "Greater Undead Zombie";
	ehp = 100;
	edmg = 10;
	System.out.printf("%n A %s blocks your path, and wishes to kill %s %n",enemy,name);
	battle = true;
	battle();
	}
	public static void dungeon3(){
	enemy = "Necromancer";
	hPotion += 5;
	System.out.printf("%n %s found some Health Potions! Health potions: %d %n",name,hPotion);
	ehp = 100;
	edmg = 15;
	System.out.printf("%n The %s wishes to kill %s %n",enemy,name);
	battle = true;
	battle();
	}
	public static void dungeon4(){
	enemy = "Necromancer Lord";
	ehp = 250;
	edmg = 15;
	System.out.printf("%n Finally, %s reached the end of the dungeon. The %s stands in the final room. Prepare for battle.",name, enemy);
	battle = true;
	battle();
	}
	public static void battle(){
	System.out.printf("%nThe battle begins.%n");
	while(battle == true){
	System.out.printf("1) Light Attack (10 AP) %n2) Heavy Attack (20 AP) %n3) Block (0 ap) %n4) Heal (%d Health Potion(s)) %n %n",hPotion);
	System.out.printf("%n%s Health: %d /  Stamina: %d | %s Health: %d %n>",name,hp,ap,enemy,ehp);
	int act = scan.nextInt();
	switch(act){
		case 1:
		if (ap<=0){
			System.out.printf("%n%s is tired, he is unable to do that %n", name);
			}
		else{
		crit();
		System.out.printf("%n%s strikes the %s for %d damage %n",name,enemy,dmg);
		ehp -= dmg;
		System.out.printf("%nThe %s strikes %s for %d damage %n",enemy,name,edmg);
		hp -= edmg;
		ap -= 10;
		status();
		}
		break;
		case 2:
		if (ap<=0){
		System.out.printf("%n%s is too tired for that. %n",name);
		}
		else{
		crit();
		System.out.printf("%n%s strikes the %s with all his might. %d damage %n",name,enemy,hdmg);
		ehp -= hdmg;
		System.out.printf("%nThe %s striked %s for %d damage %n",enemy,name,edmg);
		hp -= 5;
		ap -= 20;
		status();
		}
		break;
		case 3:
		if (ap < 150){
		System.out.printf("%n%s assumes a defensive position. Damage for this turn has been reduced. %d damage %n",name,edmg);
		hp -= edmg/2;
		ap += 5;
		status();
		}
		else{
		System.out.printf("%n%s finds a weakness within %s's defense, %s should counter attack %n",enemy,name,name);
		hp-=edmg*2;
		status();
		}
		break;
		case 4:
		if (hPotion <= 0){
		System.out.printf("%n%s reaches into his pouch to find nothing. No more health potions", name);
		}
		else{
		hPotion -= 1;
		System.out.printf("%n%s drinks a health potion. -1 health potion (%d) %n",name,hPotion);
		hp += 20;
		}
		break;
		default:
		System.out.printf("%n%s is confused, the %s attacks anyway %n",name,enemy);
		hp -= edmg;
		status();
		}
	}
	}
	public static void status(){
	if (ehp <= 0){
	battle = false;
	System.out.printf("%n%s has killed the %s %n",name,enemy);
	lvl++;
	System.out.printf("%n -------------------------------- %nNext Enemy...%n%n");
	checklvl();
		}
	else if (hp <= 0){
	System.out.printf("%n%s has fallen. GAME OVER!!!%n",name);
	battle = false;
		}
	}
	public static void checklvl(){
	switch(lvl){
	case 0:
	dungeon0();
	break;
	case 1:
	dungeon1();
	break;
	case 2:
	dungeon2();
	break;
	case 3:
	dungeon3();
	break;
	case 4:
	dungeon4();
	default: 
	if (battle == false && hp>0){
	System.out.printf("%n%n The dungeon is clear of enemies and %s found the ancient riches! Congrats you won! %n", name);
		}
	else{
	System.out.printf("%n%s has fallen. GAME OVER!!!%n",name);
	}
	break;
		}
	}
}
