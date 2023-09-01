package Persistence;

import Model.ColeccionBibliografica;
import Model.Libro;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;


public class XmlFile {

    String pathName;
    File xmlFile;

    public XmlFile(String pathName) {
        this.pathName = pathName;
        this.xmlFile = new File(pathName);
    }

    public void escribirXMLFile(ArrayList<ColeccionBibliografica> CBS) {
        /*
        No es necesario utilizar el metodo build de saxBuilder, ya que este analiza un
        documento que ya tenga contenido pero en este caso el archivo podria estar vacio
         */
        File datos = xmlFile;
        try {
            crearXML();
            Document xmlFile = new Document();
            Element elementoRoot = new Element("biblioteca");
            xmlFile.setRootElement(elementoRoot);
            //Obtener el elemento raiz
            List<Element> listaColeciones = elementoRoot.getChildren();
            //Eliminar colecciones existentes
            listaColeciones.clear();
            //Recorrer el arraylist
            for (ColeccionBibliografica CB : CBS) {
                //Crear coleccion
                String nombreColeccion = CB.getNombre();
                Element nuevaColeccion = new Element(nombreColeccion);
                for (Libro libro : CB.getLibros().values()) {
                    Element nuevoLibro = new Element("libro");
                    nuevoLibro.setAttribute("id", String.valueOf(libro.getId()));
                    Element titulo = new Element("titulo");
                    titulo.addContent(libro.getTitulo());
                    nuevoLibro.addContent(titulo);

                    Element autor = new Element("autor");
                    autor.addContent(libro.getAutor());
                    nuevoLibro.addContent(autor);

                    Element editorial = new Element("editorial");
                    editorial.addContent(libro.getEditorial());
                    nuevoLibro.addContent(editorial);

                    Element areaConocimiento = new Element("areaConocimiento");
                    areaConocimiento.addContent(libro.getAreaConocimiento());
                    nuevoLibro.addContent(areaConocimiento);

                    Element estado = new Element("estado");
                    estado.addContent(libro.getEstado());
                    nuevoLibro.addContent(estado);
                    //nuevoLibro.setAttribute("titulo", libro.getTitulo());
                    //nuevoLibro.setAttribute("autor", libro.getAutor());
                    //nuevoLibro.setAttribute("editorial", libro.getEditorial());
                    //nuevoLibro.setAttribute("areaConocimiento", libro.getAreaConocimiento());
                    //nuevoLibro.setAttribute("estado", libro.getEstado());
                    nuevaColeccion.addContent(nuevoLibro);
                }
                //guardar la nueva coleccion en el elemento principal
                elementoRoot.addContent(nuevaColeccion);
            }
            // Guardar los cambios en el archivo XML
            XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
            xmlOutputter.output(xmlFile, new FileWriter(datos));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error Entrada - salida: " + e.getMessage());
        }
    }

    public ArrayList<ColeccionBibliografica> leerXMLFile() {
        ArrayList<ColeccionBibliografica> CBS = new ArrayList<ColeccionBibliografica>();
        File datos = xmlFile;
        SAXBuilder saxBuilder = new SAXBuilder();
        try {
            Document xmlFile = saxBuilder.build(datos);
            Element elementoRoot = xmlFile.getRootElement();
            List<Element> listaColecciones = elementoRoot.getChildren();

            for (Element coleccion : listaColecciones) {
                String areaConocimiento = coleccion.getName();
                ColeccionBibliografica CB = new ColeccionBibliografica(areaConocimiento);

                List<Element> libros = coleccion.getChildren();
                for (Element libro : libros) {
                    int id = Integer.parseInt(libro.getAttributeValue("id"));
                    String titulo = libro.getChildText("titulo");
                    String autor = libro.getChildText("autor");
                    String editorial = libro.getChildText("editorial");
                    String estado = libro.getChildText("estado");

                    Libro libroRecuperado = new Libro(id, titulo, autor, editorial, areaConocimiento, estado);
                    CB.agregarLibro(libroRecuperado);
                }
                CBS.add(CB);
            }
        } catch (JDOMException e) {
            e.printStackTrace();
            System.out.println("Error JDOM: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error Entrada - salida: " + e.getMessage());
        }
        return CBS;
    }

    //EJERCICIO LEER CUALQUIER XML
    public void leerCualquierXml() {
        File file = new File("C:/Users/willi/Universidad/3 SEMESTRE/programacion 2/Biblioteca/data/datos.xml");
        SAXBuilder saxBuilder = new SAXBuilder();
        String output = "";
        try {
            Document document = saxBuilder.build(file);
            Element elementoRoot = document.getRootElement();
            output = leerDatos(elementoRoot, 0);
        } catch (JDOMException e) {
            e.printStackTrace();
            System.out.println("Error JDOM: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error Entrada - salida: " + e.getMessage());
        }
        imprimirSB(output);
    }
    private String leerDatos(Element element, int nivel){
        String identacion = getIdentacion(nivel);
        if (!hasChildren(element)){
            identacion += element.getName() +":  ";
            identacion += element.getText();
        }
        List<Element> childs = element.getChildren();
        for (Element child : childs) {
            identacion += leerDatos(child, nivel +1);
        }
        return identacion;
    }

    private String getIdentacion(int nivel) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < nivel; i++) {
        sb.append("\n");
    }
    return sb.toString();
    }



    private boolean hasChildren(Element element){
        boolean hasChildren = false;
        List<Element> children = element.getChildren();
        if (children.size() > 0) hasChildren = true;
        return hasChildren;
    }

    private void imprimirSB(String texto){
        System.out.println(texto);
    }

    public boolean crearXML(){
        File file = new File(pathName);
        boolean created = false;
        if (!file.exists()) {
            try {
                created = true;
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return created;
    }

}
