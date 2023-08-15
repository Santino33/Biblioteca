package Persistence;

import java.io.*;
import java.util.Properties;

public class PropertiesFile {


    public boolean crearPropertiesFile(String nombreRuta, String key, String value){
        File archivo = new File(nombreRuta);
        boolean errors = false;
        try {
            System.out.println("Se inicia la creacion de properties");
            OutputStream outputStream = new FileOutputStream(archivo);
            Properties prop = new Properties();
            prop.setProperty(key, value);
            //Guardar las propiedades creadas en el archivo
            prop.store(outputStream, "Archivo de propiedades");
        } catch (FileNotFoundException e){
            System.out.println("Archivo no encontrado");
            e.printStackTrace();
            errors = true;
        } catch (IOException e){
            System.out.println("Error de entrada salida");
            e.printStackTrace();
            errors = true;
        }
        return errors;
    }

    //Se lee y retorna una clave del archivo buscada por parametro
    public String leerPropertiesFile(String nombreRuta, String key){
        File archivo = new File(nombreRuta);
        String value = "";
        try{
            System.out.println("Leyendo el archivo");
            InputStream inputStream = new FileInputStream(archivo);
            Properties prop = new Properties();
            prop.load(inputStream);
            value = prop.getProperty(key).toString();
        }
        catch (FileNotFoundException e){
            System.out.println("Archivo no encontrado");
            e.printStackTrace();
        } catch (IOException e){
            System.out.println("Error de entrada salida");
            e.printStackTrace();
        }
        catch (NullPointerException e){
            System.out.println("Valores no existentes");
            e.printStackTrace();
        }
        return value;
    }

    public boolean existeArchivo(String ruta) {
        File archivo = new File(ruta);
        return archivo.exists();
    }


}
