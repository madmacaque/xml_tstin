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
            //parsing the original document
            File inputFile=new File("src/modxml.xml");
            DocumentBuilderFactory dbFactory= DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder=dbFactory.newDocumentBuilder();
            Document doc=dBuilder.parse(inputFile);

            askUser(doc);

            //writing the modifications to modxml.xml file
            TransformerFactory transformerFactory= TransformerFactory.newInstance();
            Transformer transformer=transformerFactory.newTransformer();
            DOMSource source=new DOMSource(doc);
            StreamResult result= new StreamResult(new File("src/modxml.xml"));
            transformer.transform(source, result);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //asks user if they'd like to add a new customer.
    //responses accepted yes YES y Y no n N NO
    static void askUser (Document doc){
        Scanner doWhat=new Scanner(System.in);
        System.out.println("Would you like to add a new customer? ");
        String response=doWhat.next();
        if (response.equals("y") || response.equals("Y") || response.equals("YES") || response.equals("yes")){
            addNewCustomer(doc);
            return;
        }
        else if (response.equals("no") || response.equals("NO") || response.equals("n") || response.equals("N")) {
            return;
        }
        else
            System.out.println("Response not recognized. Please try again.");
            askUser(doc);
    }


    //add new customer with user input name, ph, and id
    static void addNewCustomer(Document doc) {
        //root customers element
        Node rootElement=doc.getFirstChild();

        //check latest ID
        NodeList listofCustomers=doc.getElementsByTagName("customer");
        int latestID=listofCustomers.getLength();

        //customer elements
        Element newCustomer = doc.createElement("customer");
        rootElement.appendChild(newCustomer);

        //attribute for customer element: ID
        //ID number goes up by one each time we add a customer
        Attr attr = doc.createAttribute("ID");
        attr.setValue(Integer.toString(latestID+1));
        newCustomer.setAttributeNode(attr);

        //add name, phNO, list of friends
        //placeholder Krishna, 09448002087
        //name
        Element name = doc.createElement("name");
        Scanner nameReder=new Scanner(System.in);
        System.out.println("Enter Customer Name: ");
        name.setTextContent(nameReder.next());
        newCustomer.appendChild(name);
        //phNO
        Element phNO = doc.createElement("phNO");
        Scanner phNOREader=new Scanner(System.in);
        System.out.println("Enter Customer's Phone Number: ");
        newCustomer.appendChild(phNO);
        phNO.setTextContent(phNOREader.next());

        phNOREader.close();
        nameReder.close();
    }
}
