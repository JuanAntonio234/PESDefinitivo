package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;


//solo podemos usar render o render template

public class Application extends Controller {

    public static void index() {
        render();
    }

    public static void consultas(){
        render();
    }
    public static void datosCoche(){
        render();
    }
    public static void register(){
        render();
    }

    public static void registerUser(String nameUser, int edad, String password) {
        Logger.info("Intentando registrar usuario: " + nameUser);

        Usuario usuarioExistente = Usuario.find("byName", nameUser).first();
        if (usuarioExistente != null) {
            renderText("El nombre de usuario ya está en uso. Por favor, elige otro nombre de usuario.");
        } else {
            Usuario nuevoUsuario = new Usuario(nameUser, edad, password, 0).save();
            Logger.info("Usuario registrado correctamente: " + nameUser);
            session.put("usuario", nameUser);
            render("Application/index.html");
        }
    }
    public static void registerAndroid(String nameUser,int edad, String password){
        Logger.info("Datos recibidos del cliente Android:");
        Logger.info("Nombre de usuario: " + nameUser);
        Logger.info("Edad: " + edad);
        Logger.info("Password: " + password);
        Usuario conductorExiste = Usuario.find("byName", nameUser).first();
        if (conductorExiste != null) {
            response.status = 409; // Conflict
        }else {
            new Usuario(nameUser, edad, password,0).save();
            session.put("usuario",nameUser);
            response.status = 201; // Created
        }
    }

    public static void loginAndroid(String nameUser, String password) {
        Logger.info("Datos recibidos del cliente Android:");
        Logger.info("Nombre de usuario: " + nameUser);
        Logger.info("Password: " + password);
        Usuario usuario = Usuario.find("byNameAndPassword", nameUser, password).first();
        if (usuario != null) {
            session.put("user",nameUser);
            Logger.info("Nombre de usuario almacenado en la sesión: " + session.get("user"));
            response.status=200;
        } else {
            response.status=401;
        }
    }

    public static void login(String nameUser, String password) {
        Logger.info("Usurio: " + nameUser,password);

        Usuario usuario = Usuario.find("byNameAndPassword", nameUser, password).first();
        if (usuario != null) {
            session.put("usuario", nameUser);
          //  Application.index();
        } else {
            renderText("Nombre de usuario o contraseña incorrectos.");
        }
    }

/* public static void login(){
        render();
    }
*/
    public static void logout(){
        session.remove("usuario");
        renderText("Sesión cerrada exitosamente");
        index();
    }

    /*
    public static void mostrarCoches(){
        String usuario=session.get("usuario");
        if(usuario!=null){
            renderText("Bienvenido");
        }else{
            Login();
        }
    }*/
/////////////////////////////////////Consultas///////////////////////////////////////////////////////

    /*public static void cochesConductor(String nameDriver) {
        Logger.info("das: " + nameDriver);

        Usuario usuario = Usuario.find("byName", nameDriver).first();
        Logger.info("DAS1" + usuario);

        if (usuario != null) {
            Logger.info("Usuario encontrado: " + usuario.name);

            List<Coche> cochesConductor = Coche.find("byUsuario", usuario).fetch();
            Logger.info("Número de coches encontrados: " + cochesConductor.size());

            if (!cochesConductor.isEmpty()) {
                renderJSON(cochesConductor);
            } else {
                renderJSON("{\"mensaje\":\"El conductor " + nameDriver + " no tiene ningún coche registrado.\"}");
            }
        } else {
            renderJSON("{\"error\":\"No hay ningun usuario con este nombre.\"}");
        }
    }*/

    public static void datosCoche(String carName){

        Coche coche=Coche.find("byMarca",carName).first();
        Logger.info("Marca del coche buscado: " + carName);

        if (coche != null) {
            CocheDTO cocheDTO = new CocheDTO(coche.marca, coche.matricula, coche.tipo);
            renderJSON(cocheDTO);
        }else {
            renderJSON("{\"error\":\"No hay ningun coche con este nombre.\"}");
        }
    }
    
    public static void datosConductor(String nameDriver){
        Logger.info("Nombre de usuario recuperado de la sesión: " + session.get("user"));

        Usuario usuario=Usuario.find("byName",nameDriver).first();
        if(usuario==null){
            renderJSON("{\"error\":\"No hay ningun usuario con este nombre.\"}");
        }else{
            UsuarioDTO usuarioDTO=new UsuarioDTO(usuario.name, usuario.edad, usuario.numcoches);
            Logger.info("Nombre de usuario: " + usuario.name);
            renderJSON(usuarioDTO);
        }
    }
}