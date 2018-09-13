import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class xml_tstin {
    public static void main(String argv[]){
        try{
            DocumentBuilderFactory dbFactor=DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder=dbFactor.newDocumentBuilder();
            Document doc=dBuilder.newDocument();


            //root Element : customers
            Element rootElement=doc.createElement("customers");
            doc.appendChild(rootElement);

            //customer elements
            Element customer=doc.createElement("customer");
            rootElement.appendChild(customer);

            //attribute for customer element: ID
            Attr attr=doc.createAttribute("ID");
            attr.setValue("0001");
            customer.setAttributeNode(attr);

            //add name, phNO, list of friends
            //placeholder Krishna, 09448002087

            //name
            Element name=doc.createElement("name");
            customer.appendChild(name);
            name.setTextContent("Krishna");

            //phNO
            Element phNO=doc.createElement("phNO");
            customer.appendChild(phNO);
            phNO.setTextContent("09448002087");


            //write to xml
            TransformerFactory transformerFactory= TransformerFactory.newInstance();
            Transformer transformer=transformerFactory.newTransformer();
            DOMSource source=new DOMSource(doc);
            StreamResult result=new StreamResult(new File("G://prog_proj/xml_tstin/src/modxml.xml"));
            transformer.transform(source, result);


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
