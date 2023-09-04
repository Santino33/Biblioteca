package Iomanager;

import Model.ColeccionBibliografica;
import Model.Libro;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class View {


    private ImageIcon scaleImage(ImageIcon myImage){
        ImageIcon icon;
        Image image = myImage.getImage();
        Image scaledImage = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledImage);

        JLabel label = new JLabel();
        label.setIcon(icon);
        return icon;
    }

    private int readMenu(String message){
        ImageIcon book = new ImageIcon("C:/Users/willi/Universidad/3 SEMESTRE/programacion 2/Biblioteca/src/Iomanager/login.jpeg");
        String input= JOptionPane.showInputDialog(null, message, "LA BIBLIOTECA CENTRAL", JOptionPane.PLAIN_MESSAGE, scaleImage(book), null, null).toString();
        return Integer.parseInt(input);
    }

    public String readLibro(String message){
        ImageIcon book = new ImageIcon("C:/Users/willi/Universidad/3 SEMESTRE/programacion 2/Biblioteca/src/Iomanager/logoBiblio.jpg");
        return JOptionPane.showInputDialog(null, message, "NUEVO LIBRO", JOptionPane.PLAIN_MESSAGE, scaleImage(book), null, null).toString();
    }

    public int deleteLibro(String message){
        ImageIcon book = new ImageIcon("C:/Users/willi/Universidad/3 SEMESTRE/programacion 2/Biblioteca/src/Iomanager/eliminar.png");
        return Integer.parseInt(JOptionPane.showInputDialog(null, message, "ELIMINAR LIBRO", JOptionPane.PLAIN_MESSAGE, scaleImage(book), null, null).toString());
    }

    public int editLibro(String message){
        ImageIcon book = new ImageIcon("C:/Users/willi/Universidad/3 SEMESTRE/programacion 2/Biblioteca/src/Iomanager/editar.jpeg");
        return Integer.parseInt(JOptionPane.showInputDialog(null, message, "EDITAR LIBRO", JOptionPane.PLAIN_MESSAGE, scaleImage(book), null, null).toString());
    }
    public String editLibroString(String message){
        ImageIcon book = new ImageIcon("C:/Users/willi/Universidad/3 SEMESTRE/programacion 2/Biblioteca/src/Iomanager/editar.jpeg");
        return JOptionPane.showInputDialog(null, message, "ELIMINAR LIBRO", JOptionPane.PLAIN_MESSAGE, scaleImage(book), null, null).toString();
    }

    public String login(String message){
        ImageIcon book = new ImageIcon("C:/Users/willi/Universidad/3 SEMESTRE/programacion 2/Biblioteca/src/Iomanager/log.jpeg");
        return JOptionPane.showInputDialog(null, message, "LOGIN", JOptionPane.PLAIN_MESSAGE, scaleImage(book), null, null).toString();
    }

    public String register(String message){
        ImageIcon book = new ImageIcon("C:/Users/willi/Universidad/3 SEMESTRE/programacion 2/Biblioteca/src/Iomanager/log.jpeg");
        return JOptionPane.showInputDialog(null, message, "REGISTRO", JOptionPane.PLAIN_MESSAGE, scaleImage(book), null, null).toString();
    }
    public int sesion(){
        ImageIcon book = new ImageIcon("C:/Users/willi/Universidad/3 SEMESTRE/programacion 2/Biblioteca/src/Iomanager/log.jpeg");
        String message = "Digite la opcion de ingreso que desea\n"
                + "1. Iniciar sesion con usuario existente\n"
                + "2. Registrarse\n"
                + "3.Volver al menu";
        return (int) JOptionPane.showInputDialog(null, message, "Bienvenida a la biblioteca", JOptionPane.PLAIN_MESSAGE, scaleImage(book), null, null);
    }

    public int mostrarMenuPrincipal(){
        String output = "Bienvenido a la biblioteca central, estos son nuestros servicios: \n"+
                "1. Registrar libro\n"+
                "2. Editar libro\n"+
                "3. Eliminar libro\n"+
                "4. Buscar libro\n"+
                "5. Mostrar todos los libros\n"+
                "6. Guardar datos en el archivo\n"+
                "7. Cargar datos desde el archivo\n"+
                "8. Mostrar colecciones bibliograficas\n"+
                "9. Eliminar todos los datos\n"+
                "10. Crear binaries con libro especial\n"+
                "11. Crear copia de seguridad(Serializar)\n"+
                "12. Usar copia de seguridad (Deserializar)\n"+
                "13. Escribir en el Xml\n"+
                "14. Leer datos desde el Xml\n"+
                "15. Serializar coleccion administrativa\n"+
                "16. Deserializar coleccion administrativa\n"+
                "17. Guardar a Json\n"+
                "18. Traer desde json";



        return readMenu(output);
    }

    public int editarLibroMenu(){
        String output = "¿Que desea editar de su libro?\n"+
                "1. Titulo\n"+
                "2. Autores\n"+
                "3. Editorial\n"+
                "4. Area conocimiento\n"+
                "5. Estado\n"+
                "6. Volver al menu principal";

        return editLibro(output);
    }

    public void mostrarLibro(Libro libro){
        String output = "\nID: " + libro.getId() +
                "\nTitulo: " + libro.getTitulo() +
                "\nAutor: " + libro.getAutor() +
                "\nEditorial: " + libro.getEditorial() +
                "\nArea de conocimiento: " + libro.getAreaConocimiento() +
                "\nEstado: " + libro.getEstado() + "\n\n";
        showGraphicMessage(String.valueOf(output));
    }

    public void mostrarLibros(ArrayList<ColeccionBibliografica> CB, String fecha){
        StringBuilder output = new StringBuilder();
        output.append("  ID       Titulo               Autor\n");
        for (ColeccionBibliografica coleccionBibliografica : CB) {
            HashMap<Integer, Libro> libros = coleccionBibliografica.getLibros();
            for (Map.Entry<Integer, Libro> entry : libros.entrySet()) {
                Integer clave = entry.getKey();
                Libro libro = entry.getValue();
                output.append(libro.getId()).append("    ").append(libro.getTitulo()).append("     ").append(libro.getAutor());
                output.append("\n");
            }
        }
        output.append("\n");
        output.append("Ultimo cambio realizado: ").append(fecha);
        showGraphicMessage(output.toString());
    }

    public void mostrarColecciones(ArrayList<ColeccionBibliografica> CBS){
        StringBuilder output = new StringBuilder("NOMBRE COLECCION    NUMERO LIBROS\n");
        for(ColeccionBibliografica CB: CBS){
            output.append(CB.getNombre()).append("                          ").append(CB.getNumeroLibros() + "\n");
        }
        showGraphicMessage(output.toString());
    }

    public void mostrarColeccion(ColeccionBibliografica CB){
        StringBuilder output = new StringBuilder();
        output.append("  ID       Titulo               Autor\n");
        HashMap<Integer, Libro> libros = CB.getLibros();
        for (Map.Entry<Integer, Libro> entry : libros.entrySet()) {
            Integer clave = entry.getKey();
            Libro libro = entry.getValue();
            output.append(libro.getId()).append("    ").append(libro.getTitulo()).append("     ").append(libro.getAutor());
            output.append("\n");
        }
        showGraphicMessage(output.toString());
    }

    public void mostrarDatosEspeciales(int librosTotales, int numeroColecciones, Libro libroEspecial){
        String output = "Libros totales en la biblioteca: " + librosTotales + "\n" +
                "Numero de colecciones bibliograficas: " + numeroColecciones + "\n\n" +
                "El libro especial es: " +
                "\nID: " + libroEspecial.getId() +
                "\nTitulo: " + libroEspecial.getTitulo() +
                "\nAutor: " + libroEspecial.getAutor() +
                "\nEditorial: " + libroEspecial.getEditorial() +
                "\nArea de conocimiento: " + libroEspecial.getAreaConocimiento() +
                "\nEstado: " + libroEspecial.getEstado() + "\n\n";
        showGraphicMessage(String.valueOf(output));


    }

    public boolean eliminarArchivo(String message){
        ImageIcon book = new ImageIcon("C:/Users/willi/Universidad/3 SEMESTRE/programacion 2/Biblioteca/src/Iomanager/eliminarA.png");
        String option = JOptionPane.showInputDialog(null, message, "ELIMINAR TODOS LOS DATOS", JOptionPane.PLAIN_MESSAGE, scaleImage(book), null, null).toString();
        option = option.toLowerCase();
        return option.equals("y");
    }

    public int readIntGraphicInput(String message) {
        return Integer.parseInt(JOptionPane.showInputDialog(message));
    }
    public String readGraphicInput(String message) {
        return JOptionPane.showInputDialog(message);
    }
    public void showGraphicMessage(String message) {
        JOptionPane.showMessageDialog((Component)null, message);
    }
    public void showGraphicErrorMessage(String message) {
        JOptionPane.showMessageDialog((Component)null, message);
    }

}
