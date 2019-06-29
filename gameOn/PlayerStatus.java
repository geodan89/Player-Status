package gameOn;
import java.util.Scanner;
public class PlayerStatus {
	
	//Starea interna a obiectelor de tip PlayerStatus	
	private String nickname;
	private int score;
	private int lives;
	private int health;
	private String weaponInHand;
	private double positionX;
	private double positionY;
	private static String gameName = "Ancient Artifact Quest";
	
	//Comportament
	//1.a.Initializare
	public void initPlayer (String nickname) {
		this.nickname = nickname;
		
	}
	//1.b.Initializare
	public void initPlayer (String nickname, int lives) {
		this.nickname = nickname;
		this.lives = lives;
	}
	//1.c.Initializare
	public void initPlayer (String nickname, int lives, int score) {
		this.nickname =  nickname;
		this.lives = lives;
		this.score = score;
	}
	//1.d. Initializare health.
	public void initPlayer (String nickname, int lives, int score, int health) {
		this.nickname =  nickname;
		this.lives = lives;
		this.score = score;
		this.health = health;
	}
	public void diplayFields() {
		System.out.println("\nGame name: "+ gameName +"\nPlayer nickname: " + nickname + "\nscore: " + score
							+ "\nlives: " + lives + "\nhealth: " + health + "\nweapon: " 
							+ weaponInHand + "\npositionX: " + positionX + "\npositionY: " + positionY);
	}
	 //2.a.testeaza daca este numar perfect
	private boolean nrPerfect(int n) {
		int sum = 1;
		for (int i = 2; i < (n-1); i++) {
			if (n % i == 0) {
				sum += i;
			}
		}
		if (sum == n) {
			return true;
		}
		return false;
	}
	//2.b.testeaza daca este numar prim
	private boolean isPrime (int n) {
		if (n < 2) {
			return false;
		}
		if (n==2) {
			return true;
		}
		for (int i = 2; i <= n/2; i++) {
			if (n % i == 0) {
				return false;
		    }
		}
		return true;
	}
	//2.c.testeaza daca este nr. par si suma ciferlor este divizibila cu 3-Capcana!!!!
	private boolean isEvenAndSumDiv3 (int n) {
		int sumDigits = 0;
		int duplicate = n;
		while (duplicate != 0) {
			int ld = duplicate % 10;
			sumDigits += ld;
			duplicate = duplicate/10;
		}
		if ((n % 2 == 0) && (sumDigits % 3 == 0)) {
			return true;
		}
		return false;
	}
	//2.d.gaseste artefact
	public void findArtifact(int artifactCode) {
		
		if (nrPerfect(artifactCode)){
			score += 5000;
			lives+=1;
			health = 100;
		}
		else if (isPrime(artifactCode)) {
			score += 1000;
			lives += 2;
			health += 25; 
			if(health>100) {
				health = 100;
			}
		}
		else if (isEvenAndSumDiv3(artifactCode)) {
			score -= 3000;
			health -= 25;
			if(health <= 0) {
				lives -= 1;
				health = 100;
			}
			if (lives <= 0) {
				System.out.println("Game Over!");
			}
		}else{
			score += artifactCode; //2.d.
		}
	}
	//3.verifica si cumpara arma
	public boolean setWeaponInHand(String weaponInHand) {
		int weaponPrice1 = 1000;
		int weaponPrice2 = 10000;
		int weaponPrice3 = 20000;
		
		
		if(score<1000) {
			System.out.println("Nu ai suficiente puncte la score pentru a cumpara o arma.");
		}
		if ((weaponInHand.equals("knife")) && (score>=1000)) {
			this.weaponInHand = weaponInHand;
			score -= weaponPrice1;
			return true;
		}
		else if((weaponInHand.equals("sniper"))&&(score>=10000)) {
			this.weaponInHand = weaponInHand;
			score -= weaponPrice2;
			return true;
		}
		else if((weaponInHand.equals("kalashnikov"))&&(score>=20000)) {
			this.weaponInHand = weaponInHand;
			score -= weaponPrice3;
			return true;
		}
		else {
			System.out.println("Verifica daca ai suficiente puncte la score "
					+ "pentru a cumpara arma si verifica daca ai "
					+ "introdus corect: knife sau sniper sau kalashnikov.");
			return false;
		}
	}
	//4.returneaza arma curenta a jucatorului
	public String getWeaponInHand() {
		return weaponInHand;
	}
	//5.a.proprietate a obiectelor clasei PlayerStatus
	public double getPositionX() {
		return positionX;
	}
	public void setPositionX(double positionX) {
		this.positionX = positionX;
	}
	//5.b.proprietate a obiectelor clasei PlayerStatus
	public double getPositionY() {
		return positionY;
	}
	public void setPositionY(double positionY) {
		this.positionY = positionY;
	}
	//6.gameName devine o proprietate a clasei PlayerStatus
	public static String getGameName() {
		return gameName;
	}
	static void setGameName(String gameName) {
		PlayerStatus.gameName = gameName;
	}
	//7.actualizeaza pozitia jucatorului
	public void movePlayerTo(double positionX, double positionY) {
		System.out.println("PositionX: ");
		this.positionX = positionX;
		System.out.println("PositionY: ");
		this.positionY = positionY;
	}
	//8.a.numele jucatorului nu ar trebui sa poata fi modificat din exterior, 
	//insa ar trebui sa poata fi citit.
	public String getNickname() {
		return nickname;
	}
	//8.b.returneaza valoarea campului lives
	public int getLives() {
		return lives;
	}
	//8.c.returneaza valoarea campului health
	public int getHealth() {
		return health;
	}
	//8.d.returneaza valoarea campului score;
	public int getScore() {
		return score;
	}
	//9.a.Calculeaza patratul unui expresii matematice.
	private double square(double a, double b) {
		double c = (a-b)*(a-b);
		return c;
	}
	//9.b. Calculeaza distanta intre jucatori
	protected double distance(PlayerStatus opponent) {
		double distance = Math.sqrt((square(this.positionX,opponent.positionX)
									+ square(this.positionY, opponent.positionY)));
										
		return distance;
	}
	
	//9.c.Calculeaza probabilitatea de castig daca jucatorii au aceeasi arma.
	protected boolean winProbability(PlayerStatus opponent) {
		int probability1 = (3 * this.health + this.score / 1000) / 4;
		int probability2 = (3 * opponent.health + opponent.score / 1000) / 4;
		if(probability1 > probability2) {
			return true;	
		}else{
			return false;
		}			
	}
	//9.d.Verifica daca sunt sanse de a castiga un duel cu un oponent.
	public boolean shouldAttackOpponent(PlayerStatus opponent) {
		if (this.weaponInHand.equals(opponent.weaponInHand)){
			if (winProbability(opponent)) {
				System.out.println("You will win!");
				return true;
			} else {
				System.out.println("You will not win!");
				return false;
			}
		}
		else if(!(this.weaponInHand.equals(opponent.weaponInHand))) {
			double distance = distance(opponent);
			if (distance > 1000) {
				if (this.weaponInHand.equals("sniper")) {
					System.out.println("Attack");
					return true;
				}
				else if (this.weaponInHand.equals("kalashnikov") && opponent.weaponInHand.equals("knife")) {
					System.out.println("Attack");
					return true;
				}
			}
			else if(distance <= 1000) {
				if (this.weaponInHand.equals("kalashnikov")) {
					System.out.println("Attack");
					return true;
				}
				else if (this.weaponInHand.equals("sniper") && opponent.weaponInHand.equals("knife")) {
					System.out.println("Attack");
					return true;
				}
			}
		
		}
		System.out.println("No attack");
		return false;
	}
}


