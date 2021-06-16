package de.hsmw.main;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SaveState {
    private final Game game;
    private final File saveFile;
    private final Handler handler;

    public SaveState(Game game, String file, Handler handler){
        this.game = game;
        this.handler = handler;
        saveFile = new File(file);
    }
    public void outputFormatter(Document outputDoc, File outputFile){
        try{
            DOMSource src = new DOMSource(outputDoc);
            StreamResult result = new StreamResult(outputFile);
            TransformerFactory transfac = TransformerFactory.newInstance();
            Transformer transformer = transfac.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{https://xml.apache.org/xlst}indent-amount", "4");
            transformer.transform(src,result);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void writeToFile(){
        List<abstractGameObject> objectsList = new ArrayList<>(handler.object);
        DocumentBuilderFactory outputFac = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = outputFac.newDocumentBuilder();
            Document outputDoc = builder.newDocument();
            Element ouRoot = outputDoc.createElement("newGame");

            for(abstractGameObject o : objectsList){

                if(o.getId() != ID.Trail) {
                    Element gameObject = outputDoc.createElement("GameObject");
                    gameObject.setAttribute("x", String.valueOf(o.getX()));
                    gameObject.setAttribute("y", String.valueOf(o.getY()));
                    gameObject.setAttribute("id", String.valueOf(o.getId()));
                    ouRoot.appendChild(gameObject);
                }

            }

            outputDoc.appendChild(ouRoot);
            outputFormatter(outputDoc,saveFile);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public NodeList readFromSaveState(String filePath){
        try{
            File input = new File(filePath);
            DocumentBuilderFactory outputFac = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = outputFac.newDocumentBuilder();
            Document outputDoc = builder.parse(input);
            return outputDoc.getElementsByTagName("GameObject");
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void createGame(){

    }

}
