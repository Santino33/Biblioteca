package Model;

import java.util.*;

public class Biblioteca {

    ArrayList<ColeccionBibliografica> CB ;
    String rutaArchivo;
    String nombreArchivo;
    //Numero de libros que han existido en la biblioteca
    int numeroHistoricoLibros;
    //Cargue y escritura


    public Biblioteca() {
        this.CB = new ArrayList<ColeccionBibliografica>();
        this.rutaArchivo = "C:/Users/willi/Universidad/3 SEMESTRE/programacion 2/Biblioteca/data/";
        this.nombreArchivo = "colecciones.txt";
        this.numeroHistoricoLibros = 0;
    }

    public void cargarNumeroHistorico(){
        //Se recorren las colecciones bibliograficas para aumentar el numero historico de libros
        for (ColeccionBibliografica CB: this.CB){
            numeroHistoricoLibros += CB.getNumeroLibros();
        }
    }
    public void crearLibro(int id, String titulo, String autor, String editorial, String areaConocimiento){
        Libro libro = new Libro(id, titulo, autor, editorial, areaConocimiento, "Disponible");
        boolean found = false;
        for (ColeccionBibliografica coleccionBibliografica : CB) {
            if (coleccionBibliografica.getNombre().equals(libro.getAreaConocimiento())) {
                coleccionBibliografica.agregarLibro(libro);
                found = true;
            }
        }
        if (!found){
            ColeccionBibliografica nuevaColeccion = new ColeccionBibliografica(libro.areaConocimiento);
            CB.add(nuevaColeccion);
            nuevaColeccion.agregarLibro(libro);
        }
        numeroHistoricoLibros++;
    }

    public Libro buscarLibroPorId(int id ){
        for (ColeccionBibliografica coleccion : CB) {
            if (coleccion.libros.containsKey(id)) {
                return coleccion.libros.get(id);
            }
        }
        return null; // Libro no encontrado
    }

    public boolean verificarLibro(int id ){
        for (ColeccionBibliografica coleccion : CB) {
            if (coleccion.libros.containsKey(id)) {
                return true;
            }
        }
        return false; // Libro no encontrado
    }

    //Metodos para diferentes casos para editar un libro
    public void editarLibroTitulo(int id, String titulo){
        for (ColeccionBibliografica coleccion : CB) {
            if (coleccion.libros.containsKey(id)) {
                coleccion.libros.get(id).setTitulo(titulo);
            }
        }
    }
    public void editarLibroAutor(int id, String autor){
        for (ColeccionBibliografica coleccion : CB) {
            if (coleccion.libros.containsKey(id)) {
                coleccion.libros.get(id).setAutor(autor);
            }
        }
    }
    public void editarLibroEditorial(int id, String editorial){
        for (ColeccionBibliografica coleccion : CB) {
            if (coleccion.libros.containsKey(id)) {
                coleccion.libros.get(id).setEditorial(editorial);
            }
        }
    }
    public void editarLibroAreaConocimiento(int id, String areaConocimiento)throws IllegalArgumentException{
        Libro libro = buscarLibroPorId(id);
        libro.setAreaConocimiento(areaConocimiento);
        boolean setted = false;
        if (libro != null){
            //Eliminar el libro del area de conocimiento actual
            eliminarLibro(id);
            for (ColeccionBibliografica coleccionBibliografica : CB) {
                //Agregar libro a area de conocimiento
                if (Objects.equals(coleccionBibliografica.getNombre(), areaConocimiento)){
                    coleccionBibliografica.agregarLibro(libro);
                    setted = true;
                }
            }
            //Crear area de conocimiento si no se ha agregado ya y agregar el libro
            if (!setted){
                ColeccionBibliografica nuevaColeccion = new ColeccionBibliografica(libro.areaConocimiento);
                CB.add(nuevaColeccion);
                nuevaColeccion.agregarLibro(libro);
            }
        }
        else throw new IllegalArgumentException("Id no registrado");


        //Agregar el libro a la nueva area de conocimiento
        libro.setAreaConocimiento(areaConocimiento);

        boolean found = false;
        for (ColeccionBibliografica coleccionBibliografica : CB) {
            if (coleccionBibliografica.getNombre().equals(libro.getAreaConocimiento())) {
                coleccionBibliografica.agregarLibro(libro);
                found = true;
            }
        }
        if (found){
            ColeccionBibliografica nuevaColeccion = new ColeccionBibliografica(libro.areaConocimiento);
            CB.add(nuevaColeccion);
        }
    }


    public void editarLibroEstado(int id){
        Libro libro = buscarLibroPorId(id);
        String estadoActual = libro.getEstado();
        if (estadoActual.equals("Disponible")) libro.setEstado("En prestamo");
        else if (estadoActual.equals("En prestamo")) libro.setEstado("Disponible");
    }

    public void eliminarLibro(int id) throws IllegalArgumentException{
        if (buscarLibroPorId(id) != null){
            for (ColeccionBibliografica coleccion : CB) {
                coleccion.libros.remove(id);
            }
        }
        else throw new IllegalArgumentException("Libro no encontrado");
    }

    public ArrayList<ColeccionBibliografica> getCB() {
        return CB;
    }

    public int getNumeroHistoricoLibros(){
        return numeroHistoricoLibros;
    }

    public void setNumeroHistoricoLibros(int numeroHistoricoLibros) {
        this.numeroHistoricoLibros = numeroHistoricoLibros;

    }

    public void setCB(ArrayList<ColeccionBibliografica> CB) {
        this.CB = CB;
    }
}
