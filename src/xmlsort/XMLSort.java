/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlsort;

import java.util.TreeMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

      




/**
 *
 * @author tgdaero9
 */
public class XMLSort {

    static Map<String, Counter> mapCounters = new HashMap();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        String inputFile = "/home/tgdaero9/NetBeansProjects/XMLSort/VM_01_SC.export.xml";
        try {
            // Reads text from a character-input stream
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            
            // Defines a factory API that enables applications top obtain a parser that produces DOM object trees from XML documents
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            
            /* 
                The Document interface represents the entire HTML or XML document. 
                Conceptially, it is the root of the document tree, and provides the primary access to the documents data.
                !!! Need to change XML  encoding="UTF-16" to UTF-8 to prevent failure. Need a resolution to read origin !!!
            */
            Document doc = factory.newDocumentBuilder().parse(inputFile);
            
            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            
            // Get input element from user
            System.out.print("Enter element name: ");
            String element = reader.readLine();
            
            /*
                Returns a NodeList of all the Elements in document order with a given tag name
                and are contained in the document
            */
            /*
            NodeList nodes = doc.getElementsByTagName(element);
            System.out.println("\nHere you go => Total # of Elements: " + nodes.getLength());
            
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                System.out.println("\nCurrent element: " + node.getNodeName());
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    System.out.println(element + " Name: " + eElement.getAttribute("name"));
                }
                
            }
            */
            //NodeList childNodes = doc.getChildNodes();
            NodeList childNodes = doc.getElementsByTagName(element);
            
            HashMap<String, Integer> mapUniqueChildNodes = new HashMap<>();
            HashMap<String, Node> mapSortElementNodes = new HashMap<>();
            String sortElement = "actionconfig";
            
            for (int i = 0; i < childNodes.getLength(); i++) {
                NodeList childNodesStart = childNodes.item(i).getChildNodes();
                for (int i2 = 0; i2 < childNodesStart.getLength(); i2++) {
                    Node childNode = childNodesStart.item(i2);
                    if (childNode.getNodeName().equalsIgnoreCase(sortElement)) {
                        if (childNode.getNodeType() == Node.ELEMENT_NODE){
                            Element eElement = (Element) childNode;
                            if (eElement.getAttribute("type").equalsIgnoreCase("SERVER")){
                                mapSortElementNodes.put(eElement.getAttribute("name"), childNode);
                                System.out.println(getCountVal(eElement.getAttribute("type")) + "\t" + eElement.getAttribute("type") + "\t" + eElement.getAttribute("name"));
                            }else {
                                System.out.println(getCountVal(eElement.getAttribute("type")) + "\t" + eElement.getAttribute("type") + "\t" + eElement.getAttribute("name"));
                            }                       
                            
                        }                    
                    
                    }
                    String nodeName = childNode.getNodeName();
                    mapUniqueChildNodes.put(childNode.getNodeName(), getCountVal(nodeName));
                }
                
            }
            Set set = mapUniqueChildNodes.entrySet();
            Iterator iterator = set.iterator();
            while(iterator.hasNext()) {
                Map.Entry mapEntry = (Map.Entry)iterator.next();
                System.out.println(mapEntry.getValue() + "\tentries for actionconfig = " + mapEntry.getKey());
            }
            Map<String, Node> sortedElementNodes = new TreeMap<String, Node>(mapSortElementNodes);
            int i3 = 0;
            for (Map.Entry entry : sortedElementNodes.entrySet()){
                System.out.println("actionconfig Server Tools sorted: " + ++i3 + "\t" + entry.getKey());
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        // TODO code application logic here
    }
    
    static int getCountVal(String cName) {
         if(mapCounters.get(cName) != null) {
             return mapCounters.get(cName).getCount();
         }else {
             mapCounters.put(cName, new Counter());
             return mapCounters.get(cName).getCount();
         }      
        
    }
    
}
