package com.raptor.services.core;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.raptor.properties.Log;
import com.raptor.properties.RobotProperties;


/**
 * Service use to translate string using google translation through web interface
 * @author Eric Pidoux
 * @version 1.0
 *  This file is part of Raptor.
 *  Raptor is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Raptor is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Raptor.  If not, see <http://www.gnu.org/licenses/>
 *
 *
 */
public class TranslatorService {
		
	private static TranslatorService INSTANCE;
	
	/**
	 * Google codes for url
	 * ..to complete..
	 * fr(french),en(english),de(german),af(afrikaans),sp(spanish),it(italian),sw(swedish),nl(dutch),hi(hindi),no(norwegian),da(danish)
	 */
	public static final String FRENCH = "fr";
	public static final String ENGLISH = "en";
	public static final String GERMAN = "de";
	public static final String AFRIKAANS = "af";
	public static final String SPANISH = "sp";
	public static final String ITALIAN = "it";
	public static final String SWEDISH = "sw";
	public static final String DUTCH = "nl";
	public static final String HINDI = "hi";
	public static final String NORWEGIAN = "no";
	public static final String DANISH = "da";

	private Map<String,String> translationCodes;
	
	private TranslatorService(){
	
	}
	
	
	public static TranslatorService getInstance(){
		if(INSTANCE==null){
			INSTANCE = new TranslatorService();
			try {
				if(!RobotProperties.getInstance().isServiceTranslatorActive()){

					  Log.getInstance().info("The translator service is not active!");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return INSTANCE;
	}
	
	
  /**
   * Function which translate a string from a language to another
   * @param str the string to translate
   * @param current_language the actual language of str
   * @param needed_language the language in which str has to be translated
   * @return the translated string
 * @throws UnsupportedEncodingException 
   */
  public String translate(String str, String current_language, String needed_language ) throws Exception{
		String result = str;
	  if(RobotProperties.getInstance().isServiceTranslatorActive()){
		if(current_language!=null && needed_language!=null){
			//Check if the str string contains html tags or not
			Pattern pat = Pattern.compile("<(.*?)>");
			Matcher mat = pat.matcher(str);
			if(mat.find()){
				Document doc = Jsoup.parse(str);
				Elements elems = doc.getAllElements();
				
				for(Element elem : elems){
					String st = elem.text();
					//Encode the string for the URL
					//We have to split the string to every point "." because the google translation is ended there 
					String[] sentences = st.split("\\.");
					if(sentences.length==0){
						sentences = new String[1];
						sentences[0] = st;
					}
			
					for(int i = 0; i<sentences.length;i++){
						try{
							String sentence = sentences[i];
							if(!"".equals(sentence)){
								//sentence = URLEncoder.encode(sentence,"UTF-8");
								String link ="http://ets.freetranslation.com/";
								
								String dir = this.findTranslationCode(current_language,needed_language);
								result = HtmlExtractorService.getInstance().getStringTranslationFromUrl(link, dir,sentence);
								//System.out.println("Returning string array = "+resu);
								//String[] tab = resu.split(",\"\",\"\"]]");
								//String[] tab2 = tab[0].split("\",\"");
								//clean the string
								//String clean = tab2[0].substring(4, tab2[0].length());
								//result = URLDecoder.decode(clean,"UTF-8");
								sentences[i] = result;
							}
							else sentences[i] = "";
						}
						catch(Exception e){
							Log.getInstance().debug("[TranslatorService] Error while getting a translation ", e);
						}
					}
			
					//reconstruct all the sentences
					result = "";
					for(String sentence : sentences){
						result += sentence+".";
					}
					elem.text(result);
				}
				
				result = doc.outerHtml();
			}
			else{
				//simple string
				//Encode the string for the URL
				//We have to split the string to every point "." because the traduction is ended there (smart huh?)
				String[] sentences = str.split("\\.");
				if(sentences.length==0){
					sentences = new String[1];
					sentences[0] = str;
				}

				String dir = this.findTranslationCode(current_language,needed_language);
				for(int i = 0; i<sentences.length;i++){
					String sentence = sentences[i];
					if(!"".equals(sentence)){
						//sentence = URLEncoder.encode(sentence,"UTF-8");
						String link ="http://ets.freetranslation.com/";
						result = HtmlExtractorService.getInstance().getStringTranslationFromUrl(link, dir,sentence);
						//System.out.println("Returning string array = "+resu);
						sentences[i] = result;
					}
					else sentences[i] = "";
				}
		
				//reconstruct all the sentences
				result = "";
				for(String sentence : sentences){
					result += sentence+".";
				}
			}
		}

		Log.getInstance().debug("[TranslatorService] Translating "+str+" from "+current_language+" to "+needed_language, null);
	  }
	  else{
		  Log.getInstance().debug("[TranslatorService] This service is not active!", null);
	  }
	  return result;
  }
  private String findTranslationCode(String current_language,
		String needed_language) {
	
	return this.getTranslationCodes().get(current_language+"-"+needed_language);
}


/**
   * Encode a string for an URL
   * @param input string
   * @return encoded string
   */
  public static String encode(String input) {
      StringBuilder resultStr = new StringBuilder();
      for (char ch : input.toCharArray()) {
          if (isUnsafe(ch)) {
              resultStr.append('%');
              resultStr.append(toHex(ch / 16));
              resultStr.append(toHex(ch % 16));
          } else {
              resultStr.append(ch);
          }
      }
      return resultStr.toString();
  }
  
  private static char toHex(int ch) {
      return (char) (ch < 10 ? '0' + ch : 'A' + ch - 10);
  }

  private static boolean isUnsafe(char ch) {
      if (ch > 128 || ch < 0)
          return true;
      return " %$&+,/:;=?@<>#%".indexOf(ch) >= 0;
  }


public Map<String,String> getTranslationCodes() {
	if(translationCodes == null){
		this.translationCodes= new HashMap<String,String>();
		this.translationCodes.put(ENGLISH+"-"+FRENCH, "524289");
	}
	return translationCodes;
}


public void setTranslationCodes(Map<String,String> translationCodes) {
	this.translationCodes = translationCodes;
}


}
