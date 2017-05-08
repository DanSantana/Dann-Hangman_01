package ca.com.hangman.domain;

/**
 * 
 * @author Dann - Dangelo Santana
 * @professor Lounis Zaidi
 * @subject OPJE - v11-Project2005-0902
 *
 */
public class hangUser {

	private String name;
	private int hmScore;
	
	public hangUser() {
		System.out.println("[new hangUser]");
		this.name = "AAAA";
		hmScore = 0;
	}
	

	public hangUser(String name) {
		this.name = name;
		hmScore = 0;
	}
	public hangUser(String name, int score) {
		this.name = name;
		hmScore = score;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return hmScore;
	}

	public void setScore(int score) {
		hmScore = score;
	}
}
