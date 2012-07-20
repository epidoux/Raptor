package com.raptor.testCases;

import java.io.UnsupportedEncodingException;

import com.raptor.services.core.TranslatorService;


public class translation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			System.out.println(TranslatorService.getInstance().translate("Un texte est une succession de caractères organisée selon un langage, cette définition désigne aussi bien une sémiotique du langage (proprement acte sémique) qui en tant que tel transcende l'acte textuel. Il en résulte que le texte est exprimé par plusieurs phrases en différentes langues. Pour répondre au critère de l'univocité que garantit, dans le domaine de la scientificité d'une science linguistique, le sens étymologique, on s'intéresse à l'étymologie de tissu. Car, texte, né au XIIe siècle, vient du latin texere = tisser (du participe passé textus d'où est dérivé tissu), au sens figuré d'un tissu qui comporte une chaîne et une trame. La chaîne étant le dispositif vertical sur lequel opère transversalement la trame et comportant des anneaux où celle-ci puisse s'insérer.Dans une tapisserie, on doit en outre considérer une variété de chaînes et de trames colorées, entrelacées pour constituer des figures appelées patterns.Un texte est une suite de mots, et de caractères. On peut écrire un texte dans n'importe quelle langue écrite. Un texte est écrit, puis lu.", TranslatorService.FRENCH, TranslatorService.ENGLISH));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
