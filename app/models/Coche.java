package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Coche extends Model {
    public String marca;
    public String matricula;
    public String tipo;

    @ManyToOne
    public Usuario usuario;

    public Coche(String mar, String mat, String tip) {
        marca = mar;
        matricula = mat;
        tipo = tip;
    }
}