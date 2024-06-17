package models;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDTO {
    public String name;
    public int edad;
    public int numcoches;



    public UsuarioDTO(String name, int edad, int numCoches) {
        this.name = name;
        this.edad = edad;
        this.numcoches=numCoches;
    }

}
