package Controller;

import Iomanager.View;
import Model.Biblioteca;
import Model.ColeccionBibliografica;
import Model.Libro;
import Persistence.PropertiesFile;
import Persistence.myFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Control {

    String rutaArchivo;
    String nombreArchivo;
    String nombreProperties;
    View view;
    myFile datosFile;
    PropertiesFile propertiesFile;
    Biblioteca biblio;

    public Control(){

        this.view = new View();
        this.rutaArchivo = "C:/Users/willi/Universidad/3 SEMESTRE/programacion 2/Biblioteca/data/";
        this.nombreArchivo = "colecciones.txt";
        this.nombreProperties = "props.properties";
        this.datosFile = new myFile();
        this.propertiesFile = new PropertiesFile();
        this.biblio = new Biblioteca();
    }

    public boolean login(){
        String rutaProp = rutaArchivo + nombreProperties;
        if (!propertiesFile.existeArchivo(rutaProp)) {
            propertiesFile.crearPropertiesFile(rutaProp, "userAdmin", "admin12345" );
        }
        String username = view.login("Ingrese su nombre de usuario");
        String password = view.login("Ingrese su contraseña");
        if (password.equals(propertiesFile.leerPropertiesFile(rutaProp, username))){
            return true;
        }
        else {
            return false;
        }
    }

    public void manageApp(){
        if (!login()) {
            view.showGraphicErrorMessage("Usuario o contraseña incorrectos");
            manageApp();
        }
        int option = view.mostrarMenuPrincipal();
        switch (option){
            case 1 -> crearLibro();
            case 2 -> editarLibro();
            case 3 -> eliminiarLibro();
            case 4 -> buscarLibro();
            case 5 -> mostrarLibros();
            case 6 -> guardarArchivo();
            case 7 -> cargarArchivo();
            case 8 -> mostrarColecciones();
            case 9 -> eliminarArchivo();
            default -> defaultMenuMethod();
        }
    }

    private void cargarArchivo(){
        biblio.setCB(datosFile.cargarDatos(rutaArchivo, nombreArchivo));
        //Aumentar el numero historico de libros
        biblio.cargarNumeroHistorico();
        manageApp();
    }



    /*
    public void cargarArchivo(){
        //Se cargan datos del archivo al arraylist
        this.CB = datos.cargarDatos(rutaArchivo, nombreArchivo);
        //Se recorren las colecciones bibliograficas para aumentar el numero historico de libros
        for (ColeccionBibliografica CB: this.CB){
            numeroHistoricoLibros += CB.getNumeroLibros();
        }
    }
     */

    private void guardarArchivo(){
        datosFile.escribirArchivo(rutaArchivo, nombreArchivo, biblio.getCB());
        manageApp();
    }
    private void crearLibro(){
        int id = biblio.getNumeroHistoricoLibros() + 1001;
        System.out.println(biblio.getNumeroHistoricoLibros());
        String titulo = view.readLibro("Ingresa el titulo del libro");
        String autor = view.readLibro("Ingresa el autor del libro");
        String editorial = view.readLibro("Ingresa la editorial del libro");
        String areaConocimiento = view.readLibro("Ingresa el area de conocimiento del libro");

        biblio.crearLibro(id, titulo, autor, editorial, areaConocimiento);
        manageApp();
    }

    private void editarLibro(){
        int id = view.editLibro("¿Cual es el id del libro que desea editar");
        if (!(biblio.verificarLibro(id))){
            view.showGraphicErrorMessage("Libro no encontrado, ingrese un id valido");
            editarLibro();
        }
        int option = view.editarLibroMenu();
        switch (option){

            case 1 -> editarTitulo(id);
            case 2 -> editarAutor(id);
            case 3 -> editarEditorial(id);
            case 4 -> editarAreaConocimiento(id);
            case 5 -> editarEstado(id);
            case 6 -> manageApp();
        }
        manageApp();
    }
    private void editarTitulo(int id){
        String nuevoTitulo = view.editLibroString("Ingrese el nuevo TITULO del libro");
        biblio.editarLibroTitulo(id, nuevoTitulo);
    }
    private void editarAutor(int id){
        String nuevoAutor = view.editLibroString("Ingrese el nuevo AUTOR del libro");
        biblio.editarLibroAutor(id, nuevoAutor);
    }
    private void editarEditorial(int id){
        String nuevaEditorial = view.editLibroString("Ingrese la nueva EDITORIAL del libro");
        biblio.editarLibroEditorial(id, nuevaEditorial);
    }
    private void editarAreaConocimiento(int id){
        String nuevaArea = view.editLibroString("Ingrese la nueva AREA DE CONOCIMIENTO del libro");
        biblio.editarLibroAreaConocimiento(id, nuevaArea);
    }

    private void editarEstado(int id){
        biblio.editarLibroEstado(id);
    }

    private void eliminiarLibro(){
        int id = view.deleteLibro("¿Cual es el id del libro a ELIMINAR?");
        biblio.eliminarLibro(id);
        manageApp();
    }

    private void mostrarColecciones(){
        ArrayList<ColeccionBibliografica> CBS = biblio.getCB();
        view.mostrarColecciones(CBS);
        manageApp();
    }

    private void defaultMenuMethod(){
        view.showGraphicErrorMessage("Ingrese una opcion valida");
        manageApp();
    }

    private void eliminarArchivo(){
        boolean option = view.eliminarArchivo("¿Esta seguro que desa eliminar el archivo? Todos los datos se perderán\n Y/N");
        if (option){
            datosFile.eliminarArchivo(rutaArchivo, nombreArchivo);
        }
        manageApp();
    }

    public void buscarLibro(){
        int id = view.readIntGraphicInput("¿Cual es el ID del libro que quiere buscar? (Digite 0 para volver al menu)");
        if (id == 0) manageApp();
        if (!(biblio.verificarLibro(id))){
            view.showGraphicErrorMessage("Libro no encontrado, ingrese un id valido");
            buscarLibro();
        }
        Libro libro = biblio.buscarLibroPorId(id);
        view.mostrarLibro(libro);
        manageApp();
    }


    public void mostrarLibros(){
        ArrayList<ColeccionBibliografica> CB = biblio.getCB();
        view.mostrarLibros(CB);
        manageApp();
    }




}
