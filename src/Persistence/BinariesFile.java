package Persistence;

import Model.Libro;

import java.io.*;

public class BinariesFile {

    String filePath;
    int librosTotal;
    int colecciones;
    Libro libro;

    public BinariesFile(String filePath) {
        this.filePath = filePath;
        this.librosTotal = 0;
        this.colecciones = 0;
        this.libro = null;
    }

//METODOS DE LECTURA
    public void leerDatos(){
        int librosTotal = leerInt();
        int coleccionesTotal = leerInt();
        Libro libro = leerLibro();

        setlibrosTotal(librosTotal);
        setcolecciones(coleccionesTotal);
        setLibro(libro);
    }

    private int leerInt(){
        int intLeido = 0;
        try(FileInputStream fileInput = new FileInputStream(filePath)){
            DataInputStream dataInput = new DataInputStream(fileInput);
            intLeido = dataInput.readInt();
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Error entrada - salida" + e.getMessage());
        }
        return intLeido;
    }

    private Libro leerLibro(){
        Libro libro = null;
        try(FileInputStream fileInput = new FileInputStream(filePath)){
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);
            libro = (Libro) objectInput.readObject();
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Error entrada - salida" + e.getMessage());
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("Error clase no encontrada" + e.getMessage());
        }
        return libro;
    }


//METODOS DE ESCRITURA
    public void escrituraDatos(int numeroLibrosTotales, int numeroColecciones, Libro libro){
        crearArchivoBin();
        escribirInt(numeroLibrosTotales);
        escribirInt(numeroColecciones);
        escribirLibro(libro);
    }

    private boolean crearArchivoBin() {
        File file = new File(filePath);
        boolean created = false;
        if (!file.exists()) {
            try {
                created = file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return created;
    }

    private void escribirLibro(Libro libro){
        File archivoBin = getFile();

        try(FileOutputStream fos = new FileOutputStream(archivoBin);){
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(libro);
        }catch (FileNotFoundException e){
            e.printStackTrace();
            System.out.println("Archivo no encontrado");
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Error de entrada - salida" + e.getMessage());
        }
    }

    private void escribirInt(int valor){
        try(FileOutputStream fos = new FileOutputStream(filePath)){
            DataOutputStream escritor = new DataOutputStream(fos);

            escritor.writeInt(valor);
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Error entrada - salida"+e.getMessage());
        }
    }

    //GETERS Y SETTERS
    private File getFile(){
        return new File(filePath);
    }

    public int getlibrosTotal() {
        return librosTotal;
    }

    public void setlibrosTotal(int librosTotal) {
        this.librosTotal = librosTotal;
    }

    public int getcolecciones() {
        return colecciones;
    }

    public void setcolecciones(int colecciones) {
        this.colecciones = colecciones;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }
}
