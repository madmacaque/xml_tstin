import org.w3c.dom.*;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Scanner;

public class xml_modin {

    public static void main (String[] args){

        try{
            File inputFile=new File("src/modxml.xml");
            DocumentBuilderFactory dbFactory= DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder=dbFactory.newDocumentBuilder();
            Document doc=dBuilder.parse(inputFile);

            addNewCustomer(doc);


            //writing it to modxml.xml file
            TransformerFactory transformerFactory= TransformerFactory.newInstance();
            Transformer transformer=transformerFactory.newTransformer();
            DOMSource source=new DOMSource(doc);
            StreamResult result= new StreamResult(new File("src/modxml.xml"));
            transformer.transform(source, result);




        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //add new customer with user input name, ph, and id
    static void addNewCustomer(Document doc){
        Element newCustomer= doc.createElement("customer");

        Node customersRootNode=doc.getFirstChild();
        customersRootNode.appendChild(newCustomer);

        //set id
        //TODO: replace this by setting the id to +1 of customer's length
        Attr attrID = doc.createAttribute("ID");
        Scanner idreader =new Scanner(System.in);
        System.out.println("Enter a new ID: ");
        String id=idreader.next();
        attrID.setValue(id);


        //set the customer name
        Element custName= doc.createElement("name");
        Scanner namereader =new Scanner(System.in);
        System.out.println("Enter a Name: ");
        String name=namereader.next();
        custName.appendChild(doc.createTextNode(name));


        /*
        Scanner namereader =new Scanner(System.in);
        System.out.println("Enter a Name: ");
        String name=namereader.next());
        customerName.setTextContent(name);



        //set customer phNO
        Node customerPhNO=customerCharacteristics.item(1);
        Scanner phreader =new Scanner(System.in);
        System.out.println("Enter a Phone Number: ");
        String phNO=phreader.next();
        customerPhNO.setTextContent(phNO);
        phreader.close();
        */
        namereader.close();
        idreader.close();
    }
}
