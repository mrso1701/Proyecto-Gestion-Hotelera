package model;

public class Personas {
    public String cedula;
    public String nombre;
    public String telefono;
    public String correo;
    
    public Personas(String cedula, String nombre, String telefono, String correo) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
    }
    
    public String obtenerInfo() {
        return "Nombre: " + nombre + " | ID: " + cedula;
    }
}
