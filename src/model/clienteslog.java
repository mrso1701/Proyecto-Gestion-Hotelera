package model;

public class clienteslog extends Personas{
    public String checkin;
    public String checkout;
    
    public clienteslog(String cedula, String nombre, String telefono, String correo, String checkin, String checkout) {
        super(cedula, nombre, telefono, correo);
        this.checkin = checkin;
        this.checkout = checkout;
    }
    
    @Override
    public String obtenerInfo() {
        return super.obtenerInfo() + " | Estancia: " + checkin + " a " + checkout;
    }
}
