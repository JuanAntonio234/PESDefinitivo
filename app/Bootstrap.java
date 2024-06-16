import play.test.*;
import play.jobs.*;
import models.*;
import play.db.jpa.JPABase;
import play.db.jpa.Transactional;


@OnApplicationStart
public class Bootstrap extends Job {
    
    public void doJob() {
        // Load default data if the database is empty
        if (Usuario.count() == 0 && Coche.count() == 0 && Reserva.count() == 0) {
            //Inicialitzar la base de dades
            Usuario u1 = new Usuario("Manuel", "2f345").save();
            Usuario u2 = new Usuario("María", "10f24").save();
            Usuario u3 = new Usuario("Ana", "998s5").save();
            Usuario u4 = new Usuario("Alex", "2s756").save();
    
            Coche c1 = new Coche("Peugeot", "47984lKZ", "familiar").save();
            Coche c2 = new Coche("Citroen", "4691GJB", "SUV").save();
            Coche c3 = new Coche("Ferrari", "8564kZZ", "deportivo").save();
            Coche c4 = new Coche("Ford", "4569GHJ", "todoterreno").save();
    
            Reserva r1 = new Reserva("04/05/2024", "05/05/2024", u1, c1).save();
            
            u1.anadirCoche(c1);
            u1.save();
        }
    }
    

}