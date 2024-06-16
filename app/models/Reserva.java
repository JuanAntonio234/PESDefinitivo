package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Reserva extends Model {
    public String fechaInicio;
    public String fechaFin;

    @ManyToOne
    public Usuario usuario;

    @ManyToOne
    public Coche coche;

    public Reserva(String fechaInicio, String fechaFin, Usuario usuario, Coche coche) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.usuario = usuario;
        this.coche = coche;
    }
}