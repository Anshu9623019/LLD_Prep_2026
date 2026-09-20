package LLD_Interview_Questions.Easy.Googledoc;



///Bad Design
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;

class DocumentEditor1 {
    private List<String> documentElements;
    private String renderedDocument;

    public DocumentEditor1() {
        documentElements = new ArrayList<>();
        renderedDocument = "";
    }

    // Adds text as a plain string
    public void addText(String text) {
        documentElements.add(text);
    }

    // Adds an image represented by its file path
    public void addImage(String imagePath) {
        documentElements.add(imagePath);
    }

    // Renders the document by checking the type of each element at runtime
    public String renderDocument() {
        if (renderedDocument.isEmpty()) {
            StringBuilder result = new StringBuilder();
            for (String element : documentElements) {
                if (element.length() > 4 &&
                        (element.endsWith(".jpg") || element.endsWith(".png"))) {
                    result.append("[Image: ").append(element).append("]\n");
                } else {
                    result.append(element).append("\n");
                }
            }
            renderedDocument = result.toString();
        }
        return renderedDocument;
    }

    public void saveToFile() {
        try {
            FileWriter writer = new FileWriter("document.txt");
            writer.write(renderDocument());
            writer.close();
            System.out.println("Document saved to document.txt");
        } catch (IOException e) {
            System.out.println("Error: Unable to open file for writing.");
        }
    }
}

//class DocumentEditorClient {
//    public static void main(String[] args) {
//        DocumentEditor editor = new DocumentEditor();
//        editor.addText("Hello, world!");
//        editor.addImage("picture.jpg");
//        editor.addText("This is a document editor.");
//
//        System.out.println(editor.renderDocument());
//
//        editor.saveToFile();
//    }
//}


interface DocumentElement{
    String render();
}

class TextElement implements DocumentElement{
    String text;
    TextElement(String text){
        this.text = text;
    }

    @Override
    public String render(){
        return text;
    }
}

class ImageElement implements DocumentElement{
    String path;

    ImageElement(String path){
        this.path = path;
    }

    @Override
    public String render(){
        return path;
    }
}

// NewLineElement represents a line break in the document.
class NewLineElement implements DocumentElement {
    @Override
    public String render() {
        return "\n";
    }
}

// TabSpaceElement represents a tab space in the document.
class TabSpaceElement implements DocumentElement {
    @Override
    public String render() {
        return "\t";
    }
}


class Document{
    List<DocumentElement> documentElements;

    void addElement(DocumentElement element){
        documentElements.add(element);
    }

    List<DocumentElement> getDocumentElements(){
        return documentElements;
    }
}

interface Persistence{
    void save(String data);
}

class FileStorage implements Persistence{
    @Override
    public void save(String data) {
        try {
            FileWriter outFile = new FileWriter("document.txt");
            outFile.write(data);
            outFile.close();
            System.out.println("Document saved to document.txt");
        } catch (IOException e) {
            System.out.println("Error: Unable to open file for writing.");
        }
    }

}

class DBStorage implements Persistence{

    @Override
    public void save(String data) {
        // save to DB
    }
}

class DocumentRender{
    Document doc;

    DocumentRender(Document doc){
        this.doc = doc;
    }

    String render(){
        List<DocumentElement> documentElements = doc.getDocumentElements();
        StringBuilder result = new StringBuilder();
        for (DocumentElement element : documentElements) {
            result.append(element.render());
        }
        return result.toString();
    }
}

class DocumentEditor{
    Document doc;
    Persistence storage;

    DocumentEditor(Document doc,Persistence storage){
        this.doc = doc;
        this.storage = storage;
    }

    void addText(String text){
        doc.addElement(new TextElement(text));
    }

    void addImage(String path){
        doc.addElement(new ImageElement(path));
    }

    void addNewLine(){
        doc.addElement(new NewLineElement());
    }
    void addTabSpace(){
        doc.addElement(new TabSpaceElement());
    }
}

class GoggleDocClient{


    public static void main(String[] args) {
        Document document = new Document();
        Persistence persistence = new FileStorage();

        DocumentEditor editor = new DocumentEditor(document,persistence);
        DocumentRender render = new DocumentRender(document);

        // Simulate a client using the editor with common text formatting features.
        editor.addText("Hello, world!");
        editor.addNewLine();
        editor.addText("This is a real-world document editor example.");
        editor.addNewLine();
        editor.addTabSpace();
        editor.addText("Indented text after a tab space.");
        editor.addNewLine();
        editor.addImage("picture.jpg");
    }

}


