/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Moive;

import java.io.File;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Teng
 */
@WebService(serviceName = "Movie")
public class Movie {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "movie")
    public String movie(@WebParam(name = "category") String category, @WebParam(name = "search") String search) {
        String re;
        try {
            File inputFile = new File("C:\\Users\\Teng\\Documents\\NetBeansProjects\\MovieServer\\src\\java\\Moive\\input.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("film");
            re = "";
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                Element eElement = (Element) nNode;
                if (eElement.getElementsByTagName(category).item(0).getTextContent().toLowerCase().contains(search.toLowerCase())) {
                    re = re + "<br> Title: " + eElement.getElementsByTagName("title").item(0).getTextContent();
                    re = re + "<br> Year: " + eElement.getElementsByTagName("year").item(0).getTextContent();
                    re = re + "<br> Types: " + eElement.getElementsByTagName("types").item(0).getTextContent();
                    re = re + "<br> Time: " + eElement.getElementsByTagName("time").item(0).getTextContent();
                    re = re + "<br> Director: " + eElement.getElementsByTagName("director").item(0).getTextContent();
                    re = re + "<br> ---------------------------------------";
                }
            }
            return re;
        } catch (Exception e) {
            search = "Error";
            e.printStackTrace();

        }
        return search;
        
    }
}
