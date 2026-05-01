package Model;

public class Usuario {
    private String email; 
    private String nombre;
    private String password;
    private boolean tieneCheckIn;
    private int numeroHabitacion;

    public Usuario(String email, String nombre, String password) {
        //Sin inicializar para evitar errores de validacion 
        this.email = "";
        this.nombre = "";
        this.password = "";
        
        //Validaciones en los sets
        setEmail(email);
        setNombre(nombre);
        setPassword(password);
        
        this.tieneCheckIn = false;
        this.numeroHabitacion = 0;
    }

    public String getEmail() {
        return email;
    }

    public boolean setEmail(String email) {
       if (email != null && email.contains("@") && email.contains(".")) {
            this.email = email;
            return true;
        }
        return false;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean setNombre(String nombre) {
       if (nombre != null && nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
            this.nombre = nombre;
            return true;
        }
        return false;
    }

    public String getPassword() {
        return password;
    }

    public boolean setPassword(String password) {
        if (password != null && password.length() >= 6) {
            this.password = password;
            return true;
        }
        return false;
    }

    public boolean isTieneCheckIn() {
        return tieneCheckIn;
    }

    public void setTieneCheckIn(boolean tieneCheckIn) {
        this.tieneCheckIn = tieneCheckIn;
    }

    public int getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public void setNumeroHabitacion(int numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }
    
    
    
}


