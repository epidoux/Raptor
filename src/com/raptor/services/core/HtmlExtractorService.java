package com.raptor.services.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.raptor.entities.core.Article;
import com.raptor.entities.tag.Tag;
import com.raptor.entities.tag.TagContent;
import com.raptor.entities.tag.TagTitle;
import com.raptor.entities.task.TaskCrawlHtml;
import com.raptor.factories.TagFactory;
import com.raptor.properties.Log;

/**
 * Service which extract html content
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
public class HtmlExtractorService {

	private static HtmlExtractorService INSTANCE;
	
	private Integer current_loop=0;
	
	private Boolean crawl_it = true;
	
	private HtmlExtractorService(){
	
	}
	
	
	public static HtmlExtractorService getInstance(){
		if(INSTANCE==null){
			INSTANCE = new HtmlExtractorService();
		}
		return INSTANCE;
	}    

	/**
	 * Parse a weblink 
	 * @param link the link to parse
	 * @param content the content
	 * @param task the task linked
	 * @return the list of articles
	 * @throws Exception
	 */
	public List<Article> parse(String link, String content,TaskCrawlHtml task) throws Exception {
		Log.getInstance().debug("[HtmlExtractorService] Parsing "+link+" for the "+task,null);
		List<Article> articles = new ArrayList<Article>();
		
		//There is a readmore link (change the var link to the readmore)
		if(task.getReadmoreRegex()!=null){
			/*FilterBean fb = new FilterBean();
			fb.setURL(link);
			String html = fb.getNodes().toHtml();
			Pattern pattern = Pattern.compile(task.getReadmoreRegex());
			Matcher matcher = pattern.matcher(html);
			while(matcher.find()){
				String url = matcher.group(1);
				NodeList nodes = this.constructNodes(url, task);
				articles.addAll(this.extractArticles(nodes, task, url));
			}*/
			Document doc = Jsoup.connect(link).timeout(999999).get();
			Elements elems = doc.select(task.getReadmoreRegex());
			
			Log.getInstance().debug("[HtmlExtractorService] Found "+elems.size()+" 'read more' elements ",null);
			for(Element a : elems){
				String url = a.attr("href");
				if(!url.isEmpty() && url!=null){
					//we've got the url of the entire article
					articles.addAll(this.extractArticles(url, task));
				}
				if(!crawl_it)break;
			}
		}
		else{
		
			//No readmore link, everything is here
			//NodeList nodes = this.constructNodes(link,task);
			
			articles.addAll(this.extractArticles(link, task));
		}
		
		//There is a multipage system (recursive function)
		if(task.getMultipageRegex()!=null && !task.getMultipageRegex().isEmpty()){

			INSTANCE.current_loop++;
			if(INSTANCE.current_loop<task.getMultipageLimit() && crawl_it){
				Log.getInstance().debug("[HtmlExtractorService] There is a multipages system, let's another turn! ",null);
				Document doc = Jsoup.connect(link).timeout(999999).get();
				Element el = doc.select(task.getMultipageRegex()).first();
				try{
					articles.addAll(this.parse(el.attr("href"), content, task));
				}
				catch(NullPointerException e){
					//avoid exception for the final lap
				}
			}
			else{
				if(!crawl_it) Log.getInstance().info("[HtmlExtractorService] End of new articles");
				else Log.getInstance().info("[HtmlExtractorService] Limit of multipages exceeded");
			}
			
		}

		Log.getInstance().debug("[HtmlExtractorService] Returning "+articles.size()+" articles ",null);
		return articles;
	}
	
	/**
	 * Prepare a css Query
	 * @param Tag the tag
	 * @return the string css query
	 */
	private String prepareCssQuery(Tag tag){
		String cssQuery = "";
		if(tag.getName()!=null && !tag.getName().isEmpty()){
			cssQuery+=tag.getName();
		}
		if(tag.getClasse()!=null && !tag.getClasse().isEmpty()){
			cssQuery+="."+tag.getClasse();
		}
		if(tag.getIdentifiant()!=null && !tag.getIdentifiant().isEmpty()){
			cssQuery+="#"+tag.getIdentifiant();
		}
		
		return cssQuery;
	}
	
	/**
	 * Extract articles from an url
	 * @param url the url
	 * @param task the task
	 * @return the list of articles
	 * @throws IOException 
	 */
	private List<Article> extractArticles(String url,TaskCrawlHtml task) throws IOException{
		Log.getInstance().debug("[HtmlExtractorService] Extracting articles from "+url,null);
		List<Article> articles = new ArrayList<Article>();
		if(task.getTags()!=null && !task.getTags().isEmpty() && task.getTags().size()>1){
			//There are tags other than MotherTag as Title tag, content tag ...

			Document doc = Jsoup.connect(url).timeout(999999).get();
			Tag motherTag = (Tag)TagFactory.getInstance().findTagInList(task.getTags(), TagFactory.TYPE_MOTHER);
			String cssQuery = this.prepareCssQuery(motherTag);
			Elements elements = doc.select(cssQuery);
			
			for(Element element : elements){
				Article a = new Article();
				Boolean addArticle = true;
				a.setLink(url);
				a.setDateExecution(Calendar.getInstance());
				a.setTask(task);
				//TitleTag?
				TagTitle bt= (TagTitle) TagFactory.getInstance().findTagInList(task.getTags(), TagFactory.TYPE_TITLE);
				if(bt!=null){
					 Element el = element.select(this.prepareCssQuery(bt)).first();
					 if(el!=null)a.setTitle(el.text());
					 else addArticle = false;
				}
				//Content type tag?
				TagContent bc= (TagContent) TagFactory.getInstance().findTagInList(task.getTags(), TagFactory.TYPE_CONTENT);
				if(bc!=null){
					Element el = element.select(this.prepareCssQuery(bc)).first();
					if(el!=null)a.setContent(el.outerHtml());
					else addArticle = false;
				}
				
				//Check the uniqueness of the current list of articles
				if(!task.getArticles().contains(a)){
					//Check to the title and the content uniqueness
					Boolean unique = true;
					for(Article art : task.getArticles()){
						if(art.getLink().equals(a.getLink())){
							unique = false;
							crawl_it = false;
						}
					}
					//if it's unique article, add it
					if(unique && addArticle)articles.add(a);
				}
				else{
					//stop crawling, you already have next ones
					crawl_it = false;
				}
				
				if(!crawl_it) break;
			}
			
			
			
		}
		else{
			//no more tags, transform it in html
			Article a =new Article();
			a.setLink(url);
			a.setDateExecution(Calendar.getInstance());
			a.setTask(task);
			Document doc = Jsoup.connect(url).timeout(999999).get();
			a.setTitle(doc.getElementsByTag("title").text());
			a.setContent(doc.html());
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
/*	private NodeList constructNodes(String url,TaskCrawlHtml task) throws ParserException{
		FilterBean bean = new FilterBean ();

		if(task.getTags()!=null && !task.getTags().isEmpty()){
			Tag mother = null;
			try{
				mother = TagFactory.getInstance().findTagInList(task.getTags(), TagFactory.TYPE_MOTHER);
			}
			catch(Exception e){throw new ParserException("It must exists a mother tag to parse datas");}
			
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

        bean.setURL(url);
        return bean.getNodes();
       
	}*/
	
	/**
	 * Retrieve the node needed
	 * @param list the nodes list
	 * @param balise the balise to retrieve
	 * @return the node needed
	 */
	/*public Node findNode(NodeList node,Tag b){
		//Ordering tags on name
		NodeFilter filtre = new TagNameFilter(b.getName());
		
		NodeList noeuds =node.extractAllNodesThatMatch(filtre);
		//Ordering tags on attributes 
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
	}*/
	
	/**
	 * Retrieve something from the link given as string (not html!!!)
	 * @param link the link to parse
	 * @param languages English%2FFrench
	 * @param msg the string to post
	 * @return the string given in response
	 * @throws IOException 
	 */
	public String getStringTranslationFromUrl(String link,String languages, String msg) throws IOException{
		//&dsttext=&
	   // mode=html&username=&password=&Submit=FREE+Translation&charset=UTF-8&template=textareaResponse-ETS.asp&lwSrc=&lwDest=&lwPair=&Project=&transType=ETS&language=English%2FFrench&targetServer=ETS&srctext=this+is+a+test&srcLang=English&dstLang=English%2FFrench%3A%3A%3A%3Acore%3A%3AETS&submitbut=Translate&respSupplier=FREETRANSLATION&resptext=
				
		Document doc = Jsoup.connect(link)
				  .data("sequence","core","mode","html","Submit","FREE+Translation","charset","UTF-8","template","textareaResponse-ETS.asp","transType","ETS","language","English/French","targetServer","ETS","srctext",msg,"submitbut","Translate","respSupplier","FREETRANSLATION")
				  .userAgent("Mozilla")
				  .cookie("auth", "token")
				  .timeout(999999)
				  .post();

		return doc.text();
	}
}
