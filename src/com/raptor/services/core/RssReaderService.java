/**
 * 
 */
package com.raptor.services.core;

import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Used to parse a rss and find content
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
