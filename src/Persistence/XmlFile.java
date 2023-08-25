package Persistence;

import Model.ColeccionBibliografica;
import Model.Libro;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class XmlFile {

    String pathName;
    File xmlFile;

    public XmlFile(String pathName) {
        this.pathName = pathName;
        this.xmlFile = new File(pathName);
    }

    //
    public void leerXMLFilePractica(){
        File inputFile = new File("C:/Users/willi/Universidad/3 SEMESTRE/programacion 2/Biblioteca/data/libros.xml");
        ArrayList<ColeccionBibliografica> CBS = new ArrayList<ColeccionBibliografica>();
        SAXBuilder saxBuilder = new SAXBuilder();
        try {
            Document documento = saxBuilder.build(inputFile);
            Element elementoInicial = documento.getRootElement();

            List<Element> listaLibros = elementoInicial.getChildren();

            for (int i = 0; i < listaLibros.size(); i++) {
                Element libro= listaLibros.get(i);
                System.out.println("\nElemento :" + libro.getName());
                System.out.println("Id: " + libro.getChild("id").getText());
                System.out.println("Nombre: " + libro.getChild("titulo").getText());

                Element autores = libro.getChild("autores");
                System.out.println("\nElemento :" + autores.getName());
                Attribute atributo =  autores.getAttribute("numero");
                System.out.println("Número de autores: "+ atributo.getValue() );
                List<Element> autoresLista = autores.getChildren("autor");
                for (int j = 0; j < autoresLista.size(); j++) {
                    Element autor = autoresLista.get(j);

                    System.out.println("Nombre: " + autor.getChild("nombre").getText());
                }
            }

        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void escribirXMLFile(ArrayList<ColeccionBibliografica> CBS){
        /*
        No es necesario utilizar el metodo build de saxBuilder, ya que este analiza un
        documento que ya tenga contenido pero en este caso el archivo podria estar vacio
         */
        File datos = xmlFile;
        try{
            crearXML();
            Document xmlFile = new Document();
            Element elementoRoot = new Element("crearXML");
            xmlFile.setRootElement(elementoRoot);
            //Obtener el elemento raiz
            List<Element> listaColeciones = elementoRoot.getChildren();
            //Eliminar colecciones existentes
            listaColeciones.clear();
            //Recorrer el arraylist
            for (ColeccionBibliografica CB : CBS){
                //Crear coleccion
                String nombreColeccion = CB.getNombre();
                Element nuevaColeccion = new Element(nombreColeccion);
                for (Libro libro: CB.getLibros().values()){
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
        }
        catch (IOException e){
            e.printStackTrace();
            System.out.println("Error Entrada - salida: " +e.getMessage());
        }
    }

    public ArrayList<ColeccionBibliografica> leerXMLFile(){
        ArrayList<ColeccionBibliografica>CBS = new ArrayList<ColeccionBibliografica>();
        File datos = xmlFile;
        SAXBuilder saxBuilder = new SAXBuilder();
        try{
            Document xmlFile = saxBuilder.build(datos);
            //Obtener el elemento raiz
            Element elementoRoot = xmlFile.getRootElement();
            List<Element> listaElementos = elementoRoot.getChildren();
            for (Element colecccion : listaElementos){
                Element libro = colecccion.getChild("");
            }
        }catch (JDOMException e){
            e.printStackTrace();
            System.out.println("Error JDOM: " +e.getMessage());
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Error Entrada - salida: " +e.getMessage());
        }
        return CBS;
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
