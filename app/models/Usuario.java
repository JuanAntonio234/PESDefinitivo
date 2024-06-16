package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario extends Model {
    public String name;
    public int edad;
    public int numcoches;
    public String password;
    @OneToMany(mappedBy = "usuario")
    public List<Coche> listaCoches = new ArrayList<>();

    public Usuario(String name, int edad, String password) {
        this.name = name;
        this.edad = edad;
        this.password=password;
    }
    public Usuario(String name,String password){
        this.name=name;
        this.password=password;
    }

    public int anadirCoche(Coche coche) {
        if (coche != null) {
            if (!listaCoches.contains(coche)) {
                listaCoches.add(coche);
                return 0; // Éxito
            }
        }
        return -1; // Parámetro nulo
    }
}