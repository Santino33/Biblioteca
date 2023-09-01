package Persistence;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import com.thoughtworks.xstream.security.ExplicitTypePermission;
import com.thoughtworks.xstream.security.TypePermission;
import Model.ColeccionBibliografica;
import Model.Libro;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.*;
import java.nio.charset.Charset;


public class SerialXml {
    String pathName;
    File xmlFile;

    public SerialXml(String pathName) {
        this.pathName = pathName;
        this.xmlFile = new File(pathName);
    }

    public void serializarObjeto(Object OBJ, String alias){
        try{
            FileOutputStream outFile = new FileOutputStream(xmlFile);
            Writer writer = new OutputStreamWriter(outFile, Charset.forName("UTF-8"));

            XStream st = new XStream(new DomDriver("UTF-8"));
            st.addPermission(AnyTypePermission.ANY);
            st.alias(alias, Object.class);
            //guardar archivo
            String xml = st.toXML(OBJ);
            writer.write(xml);
            writer.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object deserializarObjeto(String alias){
        Object objeto = new Object();
        try{
            FileInputStream fis = new FileInputStream(xmlFile);
            XStream st = new XStream(new DomDriver("UTF-8"));
            // Dar permisos
            st.addPermission(AnyTypePermission.ANY);
            // Agregar tipo de dato a almacenar (Clase del objeto)
            st.alias(alias, Object.class);

            // Deserializar el objeto desde el archivo XML
            objeto = st.fromXML(fis);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return objeto;

    }

    public void serializarPractica(Libro libro){
        try{
            FileOutputStream outFile = new FileOutputStream(xmlFile);
            Writer writer = new OutputStreamWriter(outFile, Charset.forName("UTF-8"));

            XStream st = new XStream(new DomDriver("UTF-8"));
            st.alias("libro", Libro.class);
            //guardar en el archivo
            String xml = st.toXML(libro);

            writer.write(xml);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deserializarPractica(){
        try{
            FileInputStream fis = new FileInputStream(xmlFile);
            XStream st = new XStream(new DomDriver("UTF-8"));

            //Agregar tipo de dato a almacenar (Clase del objeto)
            st.alias("libro", Libro.class);
            //traer archivo xml y cinvertirlo en objeto
            Object objeto = st.fromXML(fis);
            //castear el objeto
            Libro libro = (Libro) objeto;
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
