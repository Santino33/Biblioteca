package Model;

import java.io.Serializable;

public class Libro implements Serializable {
    int id;
    String titulo;
    String autor;
    String Editorial;
    String areaConocimiento;
    String estado;

    public Libro(int id, String titulo, String autor, String editorial, String areaConocimiento, String estado) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        Editorial = editorial;
        this.areaConocimiento = areaConocimiento;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return Editorial;
    }

    public void setEditorial(String editorial) {
        Editorial = editorial;
    }

    public String getAreaConocimiento() {
        return areaConocimiento;
    }

    public void setAreaConocimiento(String areaConocimiento) {
        this.areaConocimiento = areaConocimiento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
