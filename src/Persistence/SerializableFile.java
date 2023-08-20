package Persistence;

import Model.Biblioteca;

import java.io.*;

public class SerializableFile implements Serializable {

    String serialRuta;

    public SerializableFile(String serialRuta) {
        this.serialRuta = serialRuta;
    }

    public void PersistirObjeto(Biblioteca biblio){
        File archivo = new File(serialRuta);
        try (FileOutputStream os = new FileOutputStream(archivo)){
            ObjectOutputStream objFile = new ObjectOutputStream(os);
            if (objFile!=null){
                objFile.writeObject(biblio);
                System.out.println("Objeto guardado");
            }
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Error entrada - salida" + e.getMessage());
        }
    }

    public Biblioteca deSerializarObjeto(){
        File archivo = new File(serialRuta);
        Biblioteca Biblio = null;
        try(FileInputStream inFile = new FileInputStream(archivo);){
            ObjectInputStream objFile = new ObjectInputStream(inFile);

            if (objFile != null) {
                try {
                    Biblio = (Biblioteca) objFile.readObject();
                } catch (EOFException e) {
                    System.out.println("Error entrada - salida " + e.getMessage());
                } catch (IOException e){
                    System.out.println("Error clase no encontrada " + e.getMessage());
                } catch (ClassNotFoundException e) {
                    System.out.println("Error entrada -salida " + e.getMessage());
                }
                return Biblio;
            }
        }catch (EOFException e){
            System.out.println("Error entrada - salida " + e.getMessage());
        }
        catch (IOException e){
            System.out.println("Error entrada - salida " + e.getMessage());
        }
        return Biblio;
    }
}
