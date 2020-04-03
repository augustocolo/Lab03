package it.polito.tdp.spellchecker.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Dictionary {

	private List<String> dict;
	private LanguageChoice loadedLanguage;

	public Dictionary() {
		this.dict = new ArrayList<String>();
		this.loadedLanguage = new LanguageChoice("blank", "blank");
	}

	public void loadDictionary(LanguageChoice lang) {
		ArrayList<String> newDict = new ArrayList<String>();
		try {
			FileReader fr = new FileReader(lang.getPath());
			BufferedReader br = new BufferedReader(fr);
			String word;
			while ((word = br.readLine()) != null) {
				newDict.add(word);
			}
			br.close();
		} catch (IOException e) {
			System.out.println("Errore nella lettura del file");
		}
		
		this.dict = newDict;
		this.loadedLanguage = lang;
	}

	private boolean contains(String word) {
		if (this.dict.contains(word)) {
			return true;
		} else {
			return false;
		}
	}

	public List<String> spellCheckText(String inputText) {
		ArrayList<String> res = new ArrayList<String>();
		
		inputText = inputText.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", " ");
		
		for (String input : inputText.toLowerCase().split(" ")) {
			if (!this.contains(input)) {
				res.add(input);
			}
		}

		return res;
	}

	public LanguageChoice getLoadedLanguage() {
		return loadedLanguage;
	}

}
