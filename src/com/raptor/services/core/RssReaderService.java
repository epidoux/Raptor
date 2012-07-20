/**
 * 
 */
package com.raptor.services.core;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.raptor.entities.core.Article;

/**
 * Used to parse a rss and find content
 * @author erik
 * @version 1.0
 *
 */
public class RssReaderService {

	private static RssReaderService INSTANCE;
	
	private RssReaderService(){

	}
	
	
	public static RssReaderService getInstance(){
		if(INSTANCE==null){
			INSTANCE = new RssReaderService();
		}
		return INSTANCE;
	}
	
	/**
    * Parse RSS link
    * @param feedurl URL of RSS stream
    * @return the array of nodes
    */
   public NodeList parse(String feedurl) throws Exception{
     
           DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
           URL url = new URL(feedurl);
           Document doc = builder.parse(url.openStream());
           NodeList nodes = null;
           Element element = null;
           /**
            * Titre et date du flux
            */
           nodes = doc.getElementsByTagName("title");
           Node node = doc.getDocumentElement();
           /**
            * Elements du flux RSS
            **/
           nodes = doc.getElementsByTagName("item");
           
           return nodes;
   }

   /**
    * Return the content of a node
    * @param _node root node
    * @param _path list of nodes names separate by"|"
    * @return a string which contains the searching node
    */
   public String readNode(Node _node, String _path) {

       String[] paths = _path.split("\\|");
       Node node = null;

       if (paths != null && paths.length > 0) {
           node = _node;

           for (int i = 0; i < paths.length; i++) {
               node = getChildByName(node, paths[i].trim());
           }
       }

       if (node != null) {
           return node.getTextContent();
       } else {
           return "";
       }
   }

   /**
    * Return the name of the son node with its code name
    * @param _node root node
    * @param _name name of the son node
    * @return the son node
    */
   public Node getChildByName(Node _node, String _name) {
       if (_node == null) {
           return null;
       }
       NodeList listChild = _node.getChildNodes();

       if (listChild != null) {
           for (int i = 0; i < listChild.getLength(); i++) {
               Node child = listChild.item(i);
               if (child != null) {
                   if ((child.getNodeName() != null && (_name.equals(child.getNodeName()))) || (child.getLocalName() != null && (_name.equals(child.getLocalName())))) {
                       return child;
                   }
               }
           }
       }
       return null;
   }

}
