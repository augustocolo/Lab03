package it.polito.tdp.spellchecker.model;

public class LanguageChoice {
	
	private String name;
	private String path;
	
	public LanguageChoice(String name, String path) {
		this.name = name;
		this.path = path;
	}

	@Override
	public String toString() {
		return name;
	}
	
	public String getPath() {
		return this.path;
	}
	
	public boolean equals(LanguageChoice lang2) {
		return this.path.contentEquals(lang2.getPath());		
	}
	
	
	
	

}
