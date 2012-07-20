package com.raptor.services.core;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.beans.FilterBean;
import org.htmlparser.beans.StringBean;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.util.SimpleNodeIterator;

import com.raptor.entities.core.Article;
import com.raptor.entities.tag.Tag;
import com.raptor.entities.tag.TagContent;
import com.raptor.entities.tag.TagTitle;
import com.raptor.entities.task.TaskCrawlHtml;
import com.raptor.factories.TagFactory;


public class HtmlExtractorService {

	private static HtmlExtractorService INSTANCE;
	
	private HtmlExtractorService(){
	
	}
	
	
	public static HtmlExtractorService getInstance(){
		if(INSTANCE==null){
			INSTANCE = new HtmlExtractorService();
		}
		return INSTANCE;
	}
    

	public List<Article> parse(String link, String content,TaskCrawlHtml task) throws Exception {
		
		NodeList nodes = this.constructNodes(link,task);
		List<Article> articles = new ArrayList<Article>();
		if(task.getTags()!=null && !task.getTags().isEmpty() && task.getTags().size()>1){
			//There are tags other than MotherTag as Title tag, content tag ...
			
			SimpleNodeIterator cpt =nodes.elements();
			//For each node matching an article
			while(cpt.hasMoreNodes()){
				Node node = cpt.nextNode();
				
				Article a = new Article();
				a.setLink(link);
				a.setDateExecution(Calendar.getInstance());
				a.setTask(task);
				//TitleTag?
				TagTitle bt= (TagTitle) TagFactory.getInstance().findBaliseInList(task.getTags(), TagFactory.TYPE_TITLE);
				if(bt!=null){
					 
					 Node nTitre = this.findNode(node.getChildren(), bt);
					 String title = "unknown";//default
					 if(nTitre!=null){
						 title = nTitre.toPlainTextString();
					 }
					 a.setTitle(title);
				}
				
				//Content type tag?
				TagContent bc= (TagContent) TagFactory.getInstance().findBaliseInList(task.getTags(), TagFactory.TYPE_CONTENT);
				if(bc!=null){
					 Node nContent = this.findNode(node.getChildren(), bc);
					 String contents = node.toHtml();//par defaut
					 if(nContent!=null){
						 contents = nContent.toHtml();
					 }
					 a.setContent(contents);
				}
				else{
					a.setContent(node.toHtml());
				}
				
				//Check the uniqueness of the current list of articles
				if(!articles.contains(a)){
					//Check to the title and the content uniqueness
					Boolean unique = true;
					for(Article art : articles){
						if(art.getTitle().equals(a.getTitle()) && art.getContent().equals(a.getContent())){
							unique = false;
						}
					}
					//if it's unique article, add it
					if(unique)articles.add(a);
				}
			}
		}
		else{
			//no more tags, transform it in html
			Article a =new Article();
			a.setLink(link);
			a.setDateExecution(Calendar.getInstance());
			a.setTask(task);
			a.setTitle(link);
			a.setContent(nodes.toHtml());
			articles.add(a);
		}

		
		return articles;
	}

	/**
	 * Parse an article 
	 * @param the page link
	 * @param task the task related
	 * @return the list of nodes
	 * @throws ParserException 
	 */
	private NodeList constructNodes(String url,TaskCrawlHtml task) throws ParserException{
		FilterBean bean = new FilterBean ();

		if(task.getTags()!=null && !task.getTags().isEmpty()){
			Tag mother = null;
			try{
				mother = TagFactory.getInstance().findBaliseInList(task.getTags(), TagFactory.TYPE_MOTHER);
			}
			catch(Exception e){throw new ParserException("It may exists a mother tag to parse datas");}
			
			List<NodeFilter> liste_filtres = new ArrayList<NodeFilter>();
			//Adding filters needed to get data
			if(mother.getName()!=null && !"".equals(mother.getName())) liste_filtres.add(new TagNameFilter (mother.getName()));
    		if(mother.getClasse()!=null && !"".equals(mother.getClasse()))liste_filtres.add(new HasAttributeFilter("class",mother.getClasse()));
    		if(mother.getIdentifiant()!=null && !"".equals(mother.getIdentifiant())) liste_filtres.add(new HasAttributeFilter("id",mother.getIdentifiant()));
	     
	        //There are 3 filters (name, attribute class, attribute id)

	        NodeFilter[] array0 = new NodeFilter[liste_filtres.size()];
	        for(int i=0;i<liste_filtres.size();i++){
	        	array0[i]=liste_filtres.get(i);
	        }

	        bean.setFilters(array0);
		}

        bean.setURL(task.getLink());
        return bean.getNodes();
       
	}
	
	/**
	 * Retrieve the node needed
	 * @param list the nodes list
	 * @param balise the balise to retrieve
	 * @return the node needed
	 */
	public Node findNode(NodeList node,Tag b){
		//Ordering balises on name
		NodeFilter filtre = new TagNameFilter(b.getName());
		
		NodeList noeuds =node.extractAllNodesThatMatch(filtre);
		//Ordering balises on attributes 
		if(b.getClasse()!=null && !"".equals(b.getClasse())){
			filtre = new org.htmlparser.filters.HasAttributeFilter("class",b.getClasse());
			noeuds = noeuds.extractAllNodesThatMatch(filtre);
		}
		if(b.getIdentifiant()!=null && !"".equals(b.getIdentifiant())){
			filtre = new org.htmlparser.filters.HasAttributeFilter("id", b.getIdentifiant());
			noeuds = noeuds.extractAllNodesThatMatch(filtre);
		}
		if(noeuds.size()>0){
			return noeuds.elementAt(0);
		}
		else{
			return null;
		}
	}
	
	/**
	 * Retrieve something from the link given
	 * @param link the link to parse
	 * @return the string given in response
	 */
	public String getStringFromUrl(String link){
		 StringBean sb = new StringBean ();
	     sb.setLinks (false);
	     sb.setReplaceNonBreakingSpaces (true);
	     sb.setCollapse (true);
	     sb.setURL (link); // the HTTP is performed here
	     String s = sb.getStrings ();
	     return s;
	}
}
