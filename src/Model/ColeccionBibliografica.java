package Model;

import java.io.Serializable;
import java.util.HashMap;

public class ColeccionBibliografica implements Serializable {
    //Atributos
    HashMap<Integer, Libro> libros = new HashMap<Integer, Libro>();
    String nombre;
    int numeroLibros;

    //Constructor
    public ColeccionBibliografica() {
    }

    public ColeccionBibliografica(String nombre) {
        this.nombre = nombre;
    }

    //Metodos

    public void agregarLibro(Libro libroNuevo){
        libros.put(libroNuevo.getId(), libroNuevo);
        numeroLibros++;
        System.out.println("He agreagado el libro "+ libroNuevo.getTitulo() +" en " +nombre);
    }
    public void eliminarLibro(Libro libroEliminado){
        libros.remove(libroEliminado.getId());
        numeroLibros--;
    }

    //Getters y setters
    //La clave del hash es el id de cada libro
    public HashMap<Integer, Libro> getLibros() {
        return libros;
    }

    public void setLibros(HashMap<Integer, Libro> libros) {
        this.libros = libros;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroLibros() {

        return libros.size();
    }

    public void setNumeroLibros(int numeroLibros) {
        this.numeroLibros = numeroLibros;
    }
}
