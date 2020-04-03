package it.polito.tdp.spellchecker.model;

public class RichWord {
	
	private String word;
	private boolean correct;
	
	public RichWord(String word) {
		this.word = word;
		this.correct = true;
	}

	public String getWord() {
		return word;
	}

	public boolean isCorrect() {
		return correct;
	}
	
	public void setWrong() {
		this.correct = false;
	}

	@Override
	public String toString() {
		return word;
	}
	
	
}
