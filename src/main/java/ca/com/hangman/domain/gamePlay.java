package ca.com.hangman.domain;

/**
 * 
 * @author Dann - Dangelo Santana
 * @professor Lounis Zaidi
 * @subject OPJE - v11-Project2005-0902
 *
 */
public class gamePlay {

	private hangUser user;
	int mtk;
	int score;
	private WordReader w1;
	private String wordGuess;
	char[] wordSplited;
	int rigths;
	int countWordLetters;



	public gamePlay(hangUser user) {
		this.user = user;
		this.mtk = 0;
		this.score = user.getScore();
		this.w1 = new WordReader();
		this.wordGuess = w1.getRandomWord();
		this.wordSplited = wordGuess.toCharArray();
		countWordLetters = wordGuess.length();
	}
	
	public gamePlay(hangUser user, int inScore) {
		this.user = user;
		this.mtk = 0;
		this.score = inScore;
		this.w1 = new WordReader();
		this.wordGuess = w1.getRandomWord();
		this.wordSplited = wordGuess.toCharArray();
		countWordLetters = wordGuess.length();
	}

	public int countScore(char c) {

		int cntLetter = 0;// how many letter in the word exist
		for (int i = 0; i < wordSplited.length; i++) {
			if (wordSplited[i] == c) {
				cntLetter++;
			}
		}
		if (cntLetter != 0) {
			score += cntLetter * 100;
			user.setScore(score);

		} else {
			mtk++;
		}
		return score;
	}
	public int countRight() {

		++rigths;
		
		return rigths;
	}

	public int countMistake() {
		++mtk;
		return mtk;
	}

	public int getMtk() {
		return mtk;
	}

	public void setMtk(int mtk) {
		this.mtk = mtk;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public WordReader getW1() {
		return w1;
	}

	public void setW1(WordReader w1) {
		this.w1 = w1;
	}

	public hangUser getUser() {
		return user;
	}

	public void setUser(hangUser user) {
		this.user = user;
	}

	public String getWordGuess() {
		return wordGuess;
	}

	public void setWordGuess(String hmwordGuess) {
		wordGuess = hmwordGuess;
	}

	public char[] getWordSplited() {
		return wordSplited;
	}

	public void setWordSplited(char[] wordSplited) {
		this.wordSplited = wordSplited;
	}

	public int getCountWordLetters() {
		return countWordLetters;
	}

	public void setCountWordLetters(int countWordLetters) {
		this.countWordLetters = countWordLetters;
	}

	
	public int getRigths() {
		return rigths;
	}

	public void setRigths(int rigths) {
		this.rigths = rigths;
	}

}
