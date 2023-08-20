package Persistence;

import Model.Libro;

import java.io.*;

/*
Para leer y escribir mas de 1 tipo de dato en un archivo .bin hay que manejar la escritura y
lectura de todos los datos que queramos manejar en un solo metodo, ya que si abrimos varias veces
el archivo para guardar datos por separado, cada vez que abramos el archivo, este va a cambiar
automaticamente
 */
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
        int numeroLibros = 0;
        int numeroColecciones = 0;
        Libro libro = null;
        try(FileInputStream fileInput = new FileInputStream(filePath)){
            DataInputStream dataInput = new DataInputStream(fileInput);
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);
            numeroLibros = dataInput.readInt();
            numeroColecciones = dataInput.readInt();
            libro = (Libro) objectInput.readObject();
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Error entrada - salida " + e.getMessage());
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("Clase no encontrada " + e.getMessage());
        }

        setlibrosTotal(numeroLibros);
        setcolecciones(numeroColecciones);
        setLibro(libro);
    }

    /*
    private int leerValores(){
        int numeroLibros = 0;
        int numeroColecciones = 0;
        Libro libro = null;
        try(FileInputStream fileInput = new FileInputStream(filePath)){
            DataInputStream dataInput = new DataInputStream(fileInput);
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);
            numeroLibros = dataInput.readInt();
            numeroColecciones = dataInput.readInt();
            libro = (Libro) objectInput.readObject();
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Error entrada - salida " + e.getMessage());
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("Clase no encontrada " + e.getMessage());
        }

    }

     */

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
        escribirValores(numeroLibrosTotales, numeroColecciones, libro);
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

    private void escribirValores(int numeroLibros, int numeroColecciones, Libro libro){
        try(FileOutputStream fos = new FileOutputStream(filePath)){
            DataOutputStream escritor = new DataOutputStream(fos);

            ObjectOutputStream oos = new ObjectOutputStream(fos);

            escritor.writeInt(numeroLibros);
            escritor.writeInt(numeroColecciones);
            oos.writeObject(libro);
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
