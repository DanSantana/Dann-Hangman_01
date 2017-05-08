package ca.dann.hangman;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import ca.com.hangman.domain.gamePlay;
import ca.com.hangman.domain.hangUser;
/**
 * 
 * @author Dann - Dangelo Santana
 * @professor Lounis Zaidi
 * @subject OPJE - v11-Project2005-0902
 *
 */
@SessionScoped
@ManagedBean(name = "MBhangman")
public class hangmanbean {

	private char l1;
	private char l2;
	private char l3;
	private char l4;
	private char l5;
	private char l6;
	private char l7;
	private char l8;
	private char l9;
	private char l10;

	private Boolean playAgain;
	private Boolean playLoser;

	private Boolean l3st = true;
	private Boolean l4st = true;
	private Boolean l5st = true;
	private Boolean l6st = true;
	private Boolean l7st = true;
	private Boolean l8st = true;
	private Boolean l9st = true;
	private Boolean l10st = true;

	private Boolean btQ = false;
	private Boolean btW = false;
	private Boolean btE = false;
	private Boolean btR = false;
	private Boolean btT = false;
	private Boolean btY = false;
	private Boolean btU = false;
	private Boolean btI = false;
	private Boolean btO = false;
	private Boolean btP = false;
	private Boolean btA = false;
	private Boolean btS = false;
	private Boolean btD = false;
	private Boolean btF = false;
	private Boolean btG = false;
	private Boolean btH = false;
	private Boolean btJ = false;
	private Boolean btK = false;
	private Boolean btL = false;
	private Boolean btZ = false;
	private Boolean btX = false;
	private Boolean btC = false;
	private Boolean btV = false;
	private Boolean btB = false;
	private Boolean btN = false;
	private Boolean btM = false;

	private int score;
	private int mistakes;
	private int MISTAKE_LIMIT = 6;
	private int rigthsHit;

	private int letterCountChar;
	private String player;
	private hangUser hangUser;
	private gamePlay g1;
	private String hmWord;
	private int qtyLetters;
	private char[] letters;
	private String hmImageSrc;
	char[] inputbox = new char[] { l1, l2, l3, l4, l5, l6, l7, l8, l9, l10 };

	@PostConstruct
	public void inGame() {
		try {
			playAgain = false;
			System.out.println("[Start]");
			clearRack();
			setL1('L');
			setL2('I');
			setL3('F');
			setL4('E');
			setL5('I');
			setL6('S');
			setL7('G');
			setL8('O');
			setL9('O');
			setL10('D');
			hmImageSrc = "/resources/images/hangman_0.png";
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void takeAction() {

		if (playLoser == true) {

			this.hangUser = new hangUser(player);
			hmWord = "";
			qtyLetters = 0;
			rigthsHit = 0;
			mistakes = 0;
			score = 0;
			resetButtons();
			rePlayGame();

		} else {
			reprepareGame();

		}
	}

	public void reprepareGame() {
		System.out.println("[Re Preparing Game]");
		// TODO IF PLAYER IS EMPTY
		// create a player(user) setting the name as player

		this.hangUser = new hangUser(player, score);
		hmWord = "";
		qtyLetters = 0;
		mistakes = 0;
		rigthsHit = 0;
		resetButtons();
		rePlayGame();

	}

	public void prepareGame() {
		System.out.println("[Preparing Game]");
		// TODO IF PLAYER IS EMPTY
		// create a player(user) setting the name as player
		clearRack();
		this.hangUser = new hangUser();
		this.player = "";
		hmWord = "";
		qtyLetters = 0;
		mistakes = 0;
		rigthsHit = 0;
		score = 0;
		
	}

	public void playGame() {
		System.out.println("[playGame]");
		this.hangUser.setName(player);
		System.out.println(player);
		this.g1 = new gamePlay(hangUser);
		qtyLetters = g1.getCountWordLetters();
		hmWord = g1.getWordGuess();
		System.out.println(hmWord);
		System.out.println(qtyLetters);
		clearRack();
		hideInputbx(qtyLetters);
	}

	public void rePlayGame() {
		System.out.println("[playGame]");
		System.out.println(player);
		this.g1 = new gamePlay(hangUser);
		qtyLetters = g1.getCountWordLetters();
		hmWord = g1.getWordGuess();
		System.out.println(hmWord);
		System.out.println(qtyLetters);
		clearRack();
		hideInputbx(qtyLetters);
	}

	public void playletter(Character guess) {
		letters = g1.getWordSplited();
		Boolean exist = false;

		for (int i = 0; i < qtyLetters; i++) {
			if (letters[i] == guess) {
				insertLetter(guess, i);
				score = g1.countScore(guess);
				rigthsHit = g1.countRight();
				System.out.println(rigthsHit);
				exist = true;

			}
		}
		if (exist == false) {
			mistakes = g1.countMistake();
			System.out.println("misstakes :" + mistakes);
			hmImageSrc = changeImage(mistakes);
		}
		buttonDisable(guess);
		if (qtyLetters == rigthsHit) {
			youwin();
		}
	}

	private void youlost() {
		disableAllButtons();
		l4st = true;
		l5st = true;
		l6st = true;
		l7st = true;
		l8st = true;
		l9st = true;
		l10st = true;
		clearRack();
		playLoser = true;

		setL1('Y');
		setL2('O');
		setL3('U');
		setL4('-');
		setL5('L');
		setL6('O');
		setL7('S');
		setL8('E');
		setL9('!');
		setL10('!');

		playAgain = true;

	}

	private void youwin() {
		disableAllButtons();
		l4st = true;
		l5st = true;
		l6st = true;
		l7st = true;
		l8st = true;
		l9st = true;
		l10st = true;
		clearRack();
		playLoser = false;

		setL1('Y');
		setL2('O');
		setL3('U');
		setL4('-');
		setL5('W');
		setL6('I');
		setL7('N');
		setL8('!');
		setL9('!');
		setL10('!');
		hmImageSrc = "/resources/images/hangman_won.png";
		hangUser.setScore(score);
		playAgain = true;

	}

	private String changeImage(int mstk) {
		switch (mstk) {
		case 1:
			hmImageSrc = "/resources/images/hangman_1.png";
			break;
		case 2:
			hmImageSrc = "/resources/images/hangman_2.png";
			break;
		case 3:
			hmImageSrc = "/resources/images/hangman_3.png";
			break;
		case 4:
			hmImageSrc = "/resources/images/hangman_4.png";
			break;
		case 5:
			hmImageSrc = "/resources/images/hangman_5.png";
			break;
		case 6:
			hmImageSrc = "/resources/images/hangman_lose.png";
			youlost();
			break;
		default:
			break;
		}

		// hmImageSrc = "/resources/images/hangman_0.png"

		return hmImageSrc;

	}

	private void resetButtons() {
		btQ = false;
		btW = false;
		btE = false;
		btR = false;
		btT = false;
		btY = false;
		btU = false;
		btI = false;
		btO = false;
		btP = false;
		btA = false;
		btS = false;
		btD = false;
		btF = false;
		btG = false;
		btH = false;
		btJ = false;
		btK = false;
		btL = false;
		btZ = false;
		btX = false;
		btC = false;
		btV = false;
		btB = false;
		btN = false;
		btM = false;
	}

	private void disableAllButtons() {
		btQ = true;
		btW = true;
		btE = true;
		btR = true;
		btT = true;
		btY = true;
		btU = true;
		btI = true;
		btO = true;
		btP = true;
		btA = true;
		btS = true;
		btD = true;
		btF = true;
		btG = true;
		btH = true;
		btJ = true;
		btK = true;
		btL = true;
		btZ = true;
		btX = true;
		btC = true;
		btV = true;
		btB = true;
		btN = true;
		btM = true;
	}

	public void buttonDisable(char bt) {

		switch (bt) {
		case 'Q':
			btQ = true;
			break;
		case 'W':
			btW = true;
			break;
		case 'E':
			btE = true;
			break;
		case 'R':
			btR = true;
			break;
		case 'T':
			btT = true;
			break;
		case 'Y':
			btY = true;
			break;
		case 'U':
			btU = true;
			break;
		case 'I':
			btI = true;
			break;
		case 'O':
			btO = true;
			break;
		case 'P':
			btP = true;
			break;
		case 'A':
			btA = true;
			break;
		case 'S':
			btS = true;
			break;
		case 'D':
			btD = true;
			break;
		case 'F':
			btF = true;
			break;
		case 'G':
			btG = true;
			break;
		case 'H':
			btH = true;
			break;
		case 'J':
			btJ = true;
			break;
		case 'K':
			btK = true;
			break;
		case 'L':
			btL = true;
			break;
		case 'Z':
			btZ = true;
			break;
		case 'X':
			btX = true;
			break;
		case 'C':
			btC = true;
			break;
		case 'V':
			btV = true;
			break;
		case 'B':
			btB = true;
			break;
		case 'N':
			btN = true;
			break;
		case 'M':
			btM = true;
			break;
		}

	}

	private void insertLetter(char guess, int i) {
		switch (i) {
		case 0:
			setL1(guess);
			break;
		case 1:
			setL2(guess);
			break;
		case 2:
			setL3(guess);
			break;
		case 3:
			setL4(guess);
			break;
		case 4:
			setL5(guess);
			break;
		case 5:
			setL6(guess);
			break;
		case 6:
			setL7(guess);
			break;
		case 7:
			setL8(guess);
			break;
		case 8:
			setL9(guess);
			break;
		case 9:
			setL10(guess);
			break;

		}

	}

	private void clearRack() {
		char vazio = 0;
		setL1(vazio);
		setL2(vazio);
		setL3(vazio);
		setL4(vazio);
		setL5(vazio);
		setL6(vazio);
		setL7(vazio);
		setL8(vazio);
		setL9(vazio);
		setL10(vazio);
	}

	public void hideInputbx(int start) {
		switch (start) {
		case 3:
			l4st = false;
			l5st = false;
			l6st = false;
			l7st = false;
			l8st = false;
			l9st = false;
			l10st = false;
			break;
		case 4:
			l5st = false;
			l6st = false;
			l7st = false;
			l8st = false;
			l9st = false;
			l10st = false;
			break;
		case 5:
			l6st = false;
			l7st = false;
			l8st = false;
			l9st = false;
			l10st = false;
			break;
		case 6:
			l7st = false;
			l8st = false;
			l9st = false;
			l10st = false;
			break;
		case 7:
			l8st = false;
			l9st = false;
			l10st = false;
			break;
		case 8:
			l9st = false;
			l10st = false;
			break;
		case 9:
			l10st = false;
			break;
		}

	}

	public void reload() throws IOException {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
	}

	public char[] getLetters() {
		return letters;
	}

	public char[] getInputbox() {
		return inputbox;
	}

	public void setInputbox(char[] inputbox) {
		this.inputbox = inputbox;
	}

	public void setLetters(char[] letters) {
		this.letters = letters;
	}

	public char getL1() {
		return l1;
	}

	public void setL1(char l1) {
		this.l1 = l1;
	}

	public char getL2() {
		return l2;
	}

	public void setL2(char l2) {
		this.l2 = l2;
	}

	public char getL3() {
		return l3;
	}

	public void setL3(char l3) {
		this.l3 = l3;
	}

	public char getL4() {
		return l4;
	}

	public void setL4(char l4) {
		this.l4 = l4;
	}

	public char getL5() {
		return l5;
	}

	public void setL5(char l5) {
		this.l5 = l5;
	}

	public char getL6() {
		return l6;
	}

	public void setL6(char l6) {
		this.l6 = l6;
	}

	public char getL7() {
		return l7;
	}

	public void setL7(char l7) {
		this.l7 = l7;
	}

	public char getL8() {
		return l8;
	}

	public void setL8(char l8) {
		this.l8 = l8;
	}

	public char getL9() {
		return l9;
	}

	public void setL9(char l9) {
		this.l9 = l9;
	}

	public char getL10() {
		return l10;
	}

	public void setL10(char l10) {
		this.l10 = l10;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getLetterCountChar() {
		return letterCountChar;
	}

	public void setLetterCountChar(int letterCountChar) {
		this.letterCountChar = letterCountChar;
	}

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

	public gamePlay getG1() {
		return g1;
	}

	public void setG1(gamePlay g1) {
		this.g1 = g1;
	}

	public String getHmWord() {
		return hmWord;
	}

	public void setHmWord(String hmWord) {
		this.hmWord = hmWord;
	}

	public hangUser getHangUser() {
		return hangUser;
	}

	public void setHangUser(hangUser hangUser) {
		this.hangUser = hangUser;
	}

	public int getQtyLetters() {
		return qtyLetters;
	}

	public void setQtyLetters(int qtyLetters) {
		this.qtyLetters = qtyLetters;
	}

	public Boolean getL3st() {
		return l3st;
	}

	public void setL3st(Boolean l3st) {
		this.l3st = l3st;
	}

	public Boolean getL4st() {
		return l4st;
	}

	public void setL4st(Boolean l4st) {
		this.l4st = l4st;
	}

	public Boolean getL5st() {
		return l5st;
	}

	public void setL5st(Boolean l5st) {
		this.l5st = l5st;
	}

	public Boolean getL6st() {
		return l6st;
	}

	public void setL6st(Boolean l6st) {
		this.l6st = l6st;
	}

	public Boolean getL7st() {
		return l7st;
	}

	public void setL7st(Boolean l7st) {
		this.l7st = l7st;
	}

	public Boolean getL8st() {
		return l8st;
	}

	public void setL8st(Boolean l8st) {
		this.l8st = l8st;
	}

	public Boolean getL9st() {
		return l9st;
	}

	public void setL9st(Boolean l9st) {
		this.l9st = l9st;
	}

	public Boolean getL10st() {
		return l10st;
	}

	public void setL10st(Boolean l10st) {
		this.l10st = l10st;
	}

	public Boolean getBtG() {
		return btG;
	}

	public void setBtG(Boolean btG) {
		this.btG = btG;
	}

	public Boolean getBtQ() {
		return btQ;
	}

	public void setBtQ(Boolean btQ) {
		this.btQ = btQ;
	}

	public Boolean getBtW() {
		return btW;
	}

	public void setBtW(Boolean btW) {
		this.btW = btW;
	}

	public Boolean getBtE() {
		return btE;
	}

	public void setBtE(Boolean btE) {
		this.btE = btE;
	}

	public Boolean getBtR() {
		return btR;
	}

	public void setBtR(Boolean btR) {
		this.btR = btR;
	}

	public Boolean getBtT() {
		return btT;
	}

	public void setBtT(Boolean btT) {
		this.btT = btT;
	}

	public Boolean getBtY() {
		return btY;
	}

	public void setBtY(Boolean btY) {
		this.btY = btY;
	}

	public Boolean getBtU() {
		return btU;
	}

	public void setBtU(Boolean btU) {
		this.btU = btU;
	}

	public Boolean getBtI() {
		return btI;
	}

	public void setBtI(Boolean btI) {
		this.btI = btI;
	}

	public Boolean getBtO() {
		return btO;
	}

	public void setBtO(Boolean btO) {
		this.btO = btO;
	}

	public Boolean getBtP() {
		return btP;
	}

	public void setBtP(Boolean btP) {
		this.btP = btP;
	}

	public Boolean getBtA() {
		return btA;
	}

	public void setBtA(Boolean btA) {
		this.btA = btA;
	}

	public Boolean getBtS() {
		return btS;
	}

	public void setBtS(Boolean btS) {
		this.btS = btS;
	}

	public Boolean getBtD() {
		return btD;
	}

	public void setBtD(Boolean btD) {
		this.btD = btD;
	}

	public Boolean getBtF() {
		return btF;
	}

	public void setBtF(Boolean btF) {
		this.btF = btF;
	}

	public Boolean getBtH() {
		return btH;
	}

	public void setBtH(Boolean btH) {
		this.btH = btH;
	}

	public Boolean getBtJ() {
		return btJ;
	}

	public void setBtJ(Boolean btJ) {
		this.btJ = btJ;
	}

	public Boolean getBtK() {
		return btK;
	}

	public void setBtK(Boolean btK) {
		this.btK = btK;
	}

	public Boolean getBtL() {
		return btL;
	}

	public void setBtL(Boolean btL) {
		this.btL = btL;
	}

	public Boolean getBtZ() {
		return btZ;
	}

	public void setBtZ(Boolean btZ) {
		this.btZ = btZ;
	}

	public Boolean getBtX() {
		return btX;
	}

	public void setBtX(Boolean btX) {
		this.btX = btX;
	}

	public Boolean getBtC() {
		return btC;
	}

	public void setBtC(Boolean btC) {
		this.btC = btC;
	}

	public Boolean getBtV() {
		return btV;
	}

	public void setBtV(Boolean btV) {
		this.btV = btV;
	}

	public Boolean getBtB() {
		return btB;
	}

	public void setBtB(Boolean btB) {
		this.btB = btB;
	}

	public Boolean getBtN() {
		return btN;
	}

	public void setBtN(Boolean btN) {
		this.btN = btN;
	}

	public Boolean getBtM() {
		return btM;
	}

	public void setBtM(Boolean btM) {
		this.btM = btM;
	}

	public int getMistakes() {
		return mistakes;
	}

	public void setMistakes(int mistakes) {
		this.mistakes = mistakes;
	}

	public int getMISTAKE_LIMIT() {
		return MISTAKE_LIMIT;
	}

	public void setMISTAKE_LIMIT(int mISTAKE_LIMIT) {
		MISTAKE_LIMIT = mISTAKE_LIMIT;
	}

	public String getHmImageSrc() {
		return hmImageSrc;
	}

	public void setHmImageSrc(String hmImageSrc) {
		this.hmImageSrc = hmImageSrc;
	}

	public int getRigthsHit() {
		return rigthsHit;
	}

	public void setRigthsHit(int rigthsHit) {
		this.rigthsHit = rigthsHit;
	}

	public Boolean getPlayAgain() {
		return playAgain;
	}

	public void setPlayAgain(Boolean playAgain) {
		playAgain = playAgain;
	}

	public Boolean getPlayNew() {
		return playLoser;
	}

	public void setPlayNew(Boolean playNew) {
		playLoser = playNew;
	}

}
