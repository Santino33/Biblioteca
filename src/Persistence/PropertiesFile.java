package Persistence;

import java.io.*;
import java.util.Properties;
import java.io.File;


public class PropertiesFile {

    String filePath;

    public PropertiesFile(String filePath) {
        this.filePath = filePath;
        crearArchivo();
    }

    public boolean crearArchivo() {
        File file = new File(filePath);
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


    //Metodo funciona para crear una propiedad asi como para actualizarla
    public void crearPropiedad(String key, String value) {
        Properties prop = loadProperties();
        prop.setProperty(key, value);

        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            prop.store(outputStream, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void eliminarPropiedad(String key){
        Properties prop = loadProperties();
        prop.remove(key);
        guardarProperties(prop);
    }

    public void createPropertiesFile(String key, String value) {
        Properties properties = new Properties();

        try (FileInputStream inputStream = new FileInputStream(filePath)) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.getStackTrace();
        }

        properties.setProperty(key, value);

        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            properties.store(outputStream, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void guardarProperties(Properties properties) {
        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            properties.store(outputStream, null);
        } catch (IOException e) {
            e.printStackTrace();
            }
    }

    private Properties loadProperties() {
        Properties properties = new Properties();
        try (FileInputStream inputStream = new FileInputStream(filePath)) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }

    public String getValue(String key){
        Properties prop = loadProperties();
        return prop.getProperty(key);
    }

    //Se lee y retorna una clave del archivo buscada por parametro
    public String leerPropertiesFile(String nombreRuta, String key){
        File archivo = new File(nombreRuta);
        String value = "";
        try{
            System.out.println("Leyendo el archivo");
            InputStream inputStream = new FileInputStream(archivo);
            Properties prop = loadProperties();
            //prop.load(inputStream);
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



}
