package com.raptor.services.core;

import java.util.ArrayList;
import java.util.List;

import com.raptor.entities.core.Article;

/**
 * Use to Concat. some unknown data with another
 */
public class RefilledService {
	
	private static RefilledService INSTANCE;
	
	private static final String TYPE_LIST_ARTICLE = "LIST<ARTICLE>";
	private static final String TYPE_ARTICLE = "ARTICLE";
	private static final String TYPE_STRING = "STRING";
	private static final String TYPE_INTEGER = "INTEGER";
	private static final String TYPE_HTML = "HTML";
	
	private RefilledService(){
	}
	
	
	public static RefilledService getInstance(){
		if(INSTANCE==null){
			INSTANCE = new RefilledService();
		}
		return INSTANCE;
	}
	
	/**
	 * Prepare the data from an type to another
	 * @param out the actual content type
	 * @param in the expected content type
	 * @param filled the content
	 * @return the expected content
	 */
	public Object prepareData(String out,String in,Object filled){
		//Try to find which method do we use to transform data
		Object data = filled;
		if(out.equals(TYPE_LIST_ARTICLE)){
			if(in.equals(TYPE_STRING)){
				data = this.transformFromListArticleToString(filled);
			}
			else if(in.equals(TYPE_HTML)){

				data = this.transformFromListArticleToHtml(filled);
			}
		}
		else if(out.equals(TYPE_ARTICLE)){
			if(in.equals(TYPE_STRING)){

				data = this.transformFromArticleToString(filled);
			}
			else if(in.equals(TYPE_HTML)){
				data = this.transformFromArticleToHtml(filled);
			}
		}
		else if(out.equals(TYPE_STRING)){
			if(in.equals(TYPE_HTML)){
				data = this.transformFromStringToHtml(filled);
			}
		}
		else if(out.equals(TYPE_INTEGER)){
			if(in.equals(TYPE_STRING)){
				data = this.transformFromIntegerToString(filled);
			}
			
		}
		else if(out.equals(TYPE_HTML)){
			//nothing for the moment
		}
		
		return data;
	}
    
	/**
	 * Transform the content from Integer type to String
	 * @param filled the integer content
	 * @return the string content
	 */
    private Object transformFromIntegerToString(Object filled) {
		return ""+filled;
	}

	/**
	 * Transform the content from String type to Html
	 * @param filled the String content
	 * @return the html content
	 */
	private Object transformFromStringToHtml(Object filled) {
		return filled;
	}


	/**
	 * Transform the content from Article type to Html
	 * @param filled the integer content
	 * @return the string content
	 */
	private Object transformFromArticleToHtml(Object filled) {
		Article a = (Article) filled;
		Object data = "<h3>"+a.getTitle()+"</h3><br/>" +
				"<p>"+a.getContent()+"</p><br/><hr/><br/>";
		
		return data;
	}
	
	/**
	 * Transform the content from Article type to string
	 * @param filled the content
	 * @return the string content
	 */
	private Object transformFromArticleToString(Object filled) {
		Article a = (Article) filled;
		return a.getTitle()+" : "+a.getContent()+"\r\n";
	}


	/**
	 * Transform the content from List of Article type to Html
	 * @param filled the content
	 * @return the html content
	 */
	@SuppressWarnings("unchecked")
	private Object transformFromListArticleToHtml(Object filled) {
		List<Article> list = (List<Article>) filled;
		String data= "";
		for(Article a : list){
			data += this.transformFromArticleToHtml(a);
		}
		return data;
	}

	/**
	 * Transform the content from List of Article type to string
	 * @param filled the content
	 * @return the string content
	 */
	@SuppressWarnings("unchecked")
	private Object transformFromListArticleToString(Object filled) {
		List<Article> list = (List<Article>) filled;
		String data= "";
		for(Article a : list){
			data += this.transformFromArticleToString(a);
		}
		return data;
	}


	/**
     * Refilled function needed to put something into something else (very abstract...)
     * @param s1 something
     * @param s2 something 
     * @return a new thing (s2 with s1)
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public Object refilled(Object s1, Object s2){
    	//trying to determine s2
    	if(s1 instanceof Article){
    		if(s2 instanceof List<?>){
    			//put an article into a list
    			//tying to define what kind of list
    			Object o = ((List) s2).get(0);
    			if(o !=null){
    				if(o instanceof Article){
    					((List) s2).add(o);
    				}
    				else if(o instanceof String){
    					((List) s2).add(null);
    					//TODO internationalise la conversion en string
    				}
    				else{
    					//error : Article can be put only in List of Article or String
    				}
    			}
    			else{
    				//article
					((List) s2).add(o);
    			}
    		}
    		else if( s2 instanceof String){
    			//TODO internationalise la conversion en string
    		}
    		else{
    			//TODO error: impossible to find what is S2 (not conventionnal)
    		}
    	}
    	return null;
    }
}
