package Persistence;

import Model.ColeccionBibliografica;
import Model.Libro;

import javax.json.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class JsonFile {
    String pathName;
    File jsonFile;

    public JsonFile(String pathName) {
        this.pathName = pathName;
        this.jsonFile = new File(pathName);
    }

    public void escribirDatos(ArrayList<ColeccionBibliografica> CBS){
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (ColeccionBibliografica CB : CBS){
            JsonObjectBuilder coleccionBuilder = Json.createObjectBuilder();
            coleccionBuilder.add("Coleccion", CB.getNombre());
            JsonArrayBuilder librosBuilder = Json.createArrayBuilder();
            for (Libro libro: CB.getLibros().values()){
                JsonObjectBuilder libroBuilder = Json.createObjectBuilder();
                libroBuilder.add("id", libro.getId());
                libroBuilder.add("Titulo", libro.getTitulo());
                libroBuilder.add("Autor", libro.getAutor());
                libroBuilder.add("Area", libro.getAreaConocimiento());
                libroBuilder.add("Editorial", libro.getEditorial());
                libroBuilder.add("Estado", libro.getEstado());
                librosBuilder.add(libroBuilder);
            }
            coleccionBuilder.add("libros", librosBuilder.build());
            arrayBuilder.add(coleccionBuilder.build());
        }
        JsonArray bibliotecaJson = arrayBuilder.build();
        try(JsonWriter writer = Json.createWriter(new FileWriter(pathName))){
            writer.write(bibliotecaJson);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public ArrayList<ColeccionBibliografica> importarDatos(){
        ArrayList<ColeccionBibliografica> CBS = new ArrayList<ColeccionBibliografica>();
        try (JsonReader jsonReader = Json.createReader(new FileReader(jsonFile)); ){
            JsonArray arrayJson = jsonReader.readArray();
            for (JsonValue jsonValue : arrayJson){
                JsonObject coleccionObj = (JsonObject) jsonValue;
                String nombreCol = coleccionObj.getString("Coleccion");
                ColeccionBibliografica CB = new ColeccionBibliografica(nombreCol);
                for (JsonValue libroValue : coleccionObj.getJsonArray("libros")){
                    JsonObject libroObj = (JsonObject) libroValue;
                    int id = libroObj.getInt("id");
                    String titulo = libroObj.getString("Titulo");
                    String autor = libroObj.getString("Autor");
                    String area = libroObj.getString("Area");
                    String editorial = libroObj.getString("Editorial");
                    String estado = libroObj.getString("Estado");
                    Libro libro = new Libro(id, titulo, autor, editorial,area, estado);
                    CB.agregarLibro(libro);
                }
                CBS.add(CB);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return CBS;
    }

    private JsonValue iterarJsonObject(JsonObject jsonObject){
        Set<Map.Entry<String, JsonValue>> entradas = jsonObject.entrySet();
        JsonValue value = null;
        for (Map.Entry<String, JsonValue> entrada : entradas){
            value = entrada.getValue();
            if (value.getValueType() == JsonValue.ValueType.OBJECT){
                iterarJsonObject((JsonObject) value);
            }
        }
        return value;
    }
}
