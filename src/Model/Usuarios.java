package Model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Usuarios {
    ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

    public Usuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
