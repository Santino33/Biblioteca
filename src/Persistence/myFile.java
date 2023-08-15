package Persistence;

import Model.ColeccionBibliografica;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Logger;

public class myFile {


    public ArrayList<ColeccionBibliografica> cargarDatos(String rutaArchivo, String nombreArchivo) {
        ArrayList<ColeccionBibliografica> CB = new ArrayList<>();
        rutaArchivo += nombreArchivo;

        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return CB;
        }

        try {
            FileInputStream fileIn = new FileInputStream(rutaArchivo);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            CB = (ArrayList<ColeccionBibliografica>) objectIn.readObject();
            objectIn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return CB;
    }

    public void escribirArchivo(String rutaArchivo, String nombreArchivo, ArrayList<ColeccionBibliografica> CB){
        rutaArchivo = rutaArchivo + nombreArchivo;
        try {
            FileOutputStream fileOut = new FileOutputStream(rutaArchivo);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(CB);
            objectOut.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void eliminarArchivo(String rutaArchivo, String nombreArchivo) {
        rutaArchivo = rutaArchivo + nombreArchivo;
        File archivo = new File(rutaArchivo);

        // Verificar si el archivo existe antes de eliminarlo
        if (archivo.exists()) {
            // Eliminar el archivo
            archivo.delete();
        }
    }



}
