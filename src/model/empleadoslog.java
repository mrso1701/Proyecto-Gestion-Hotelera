package model;

public class empleadoslog extends Personas{
    public String cargo;
    public double salario;
    public String fecha; // <-- AQUÍ ESTÁ LA FECHA QUE TE FALTA
    
    // Constructor con los 7 parámetros necesarios (los 4 de Persona + 3 propios)
    public empleadoslog(String cedula, String nombre, String telefono, String correo, String cargo, double salario, String fecha) {
        super(cedula, nombre, telefono, correo); // Hereda de Personas
        this.cargo = cargo;
        this.salario = salario;
        this.fecha = fecha;
    }
    
    @Override
    public String obtenerInfo() {
        return super.obtenerInfo() + " | Cargo: " + cargo + " | Salario: " + salario + " | Fecha: " + fecha;
    }
}
