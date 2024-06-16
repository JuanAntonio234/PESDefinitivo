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
   
    public static void register(){
        render();
    }

    public static void registrar(String nameUser,int edad, String password, String confirmPassword) {
        Usuario conductorExiste = Usuario.find("byName", nameUser).first();
        if (conductorExiste != null) {
            renderText("El nombre de usuario ya está en uso. Por favor, elige otro nombre de usuario.");
        } else {
            if (password.equals(confirmPassword)) {
                Usuario newConductor = new Usuario(nameUser,edad, password).save();
                session.put("usuario", nameUser);
                index();
            } else {
                renderText("Nombre de usuario o contraseña incorrectos.");
            }
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
            Usuario newConductor = new Usuario(nameUser, edad, password).save();
            response.status = 201; // Created
        }
    }
    public static void loginAndroid(String nameUser, String password) {
        Logger.info("Datos recibidos del cliente Android:");
        Logger.info("Nombre de usuario: " + nameUser);
        Logger.info("Password: " + password);
        Usuario usuario = Usuario.find("byNameAndPassword", nameUser, password).first();
        if (usuario != null) {
            response.status=200;
        } else {
            response.status=401;
        }
    }
    public static void login(String nameUser, String password) {
        Usuario usuario = Usuario.find("byNameAndPassword", nameUser, password).first();
        if (usuario != null) {
            session.put("usuario", nameUser);
            Application.index();
        } else {
            renderText("Nombre de usuario o contraseña incorrectos.");
        }
    }


    public static void Login(){
        render();
    }

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

    public static void obtenerCochesDelConductor(String userName) {
        Usuario usuario = Usuario.find("byName", userName).first();
        if (usuario != null) {
            List<Coche> cochesConductor = Coche.find("byConductor", usuario).fetch();
            if (cochesConductor.isEmpty()) {
                renderJSON(cochesConductor);
            } else {
                renderText("El conductor"+userName+" no tiene ningún coche registrado.");
            }
        } else {
            renderText("No se encontró al conductor "+userName+" en la base de datos.");
        }
    }

    public static void obtenerDatosCoche(String carName){
        Coche coche=Coche.find("byName",carName).first();
        if (coche != null) {
            renderJSON(coche);
        }else {
            renderText("No hay ningun coche con este nombre.");
        }
    }
    public static void obtenerDatosConductor(String conductor,String password){
        Usuario usuario=Usuario.find("byNameAndPassword",conductor,password).first();
        if(usuario!= null){
            renderText("Nombre: "+conductor+" edad: "+ usuario.edad+"número de coches: "+ usuario.numcoches);
        }else{
            renderText("No hay ningún usuario asociado a ese nombre.");
        }
    }


    //hacer una consulta para obtener todos los coches


    
///////////////////////////////////////////////////////////////////////////////////////////////////////
}