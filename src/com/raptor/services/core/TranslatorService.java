package com.raptor.services.core;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.client.utils.URIUtils;


/**
 * Service use to translate string using google translation through web interface
 * @author erik
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

	
	private TranslatorService(){
	
	}
	
	
	public static TranslatorService getInstance(){
		if(INSTANCE==null){
			INSTANCE = new TranslatorService();
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
  public String translate(String str, String current_language, String needed_language ) throws UnsupportedEncodingException{
	  String result = str;
	  if(current_language!=null && needed_language!=null){
		  //Encode the string for the URL
		  //We have to split the string to every point "." because the traduction is ended there (smart huh?)
		  String[] sentences = str.split("\\.");
		  if(sentences.length==0){
			  sentences = new String[1];
			  sentences[0] = str;
		  }
		  
		  for(int i = 0; i<sentences.length;i++){
			  String sentence = sentences[i];
			  sentence = URLEncoder.encode(sentence,"UTF-8");
			  String link ="http://translate.google.com/translate_a/t?client=t&text="+sentence+"&hl="+current_language+"&sl="+current_language+"&tl="+needed_language+"&ie=UTF-8&oe=UTF-8&multires=1&otf=1&pc=1&trs=1&ssel=3&tsel=6&sc=1";
			  
			  String resu = HtmlExtractorService.getInstance().getStringFromUrl(link);
			 //System.out.println("Returning string array = "+resu);
			  String[] tab = resu.split(",\"\",\"\"]]");
			  String[] tab2 = tab[0].split("\",\"");
			  //clean the string
			  String clean = tab2[0].substring(4, tab2[0].length());
			  result = clean;
			  sentences[i] = result;
		  }
		  
		  //reconstruct all the sentences
		  result = "";
		  for(String sentence : sentences){
			  result += sentence+".";
		  }
	  }

	  return result;
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


}
