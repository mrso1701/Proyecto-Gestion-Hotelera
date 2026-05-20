package model;

public class habitacionlog {
    public String numero;
    public String tipo;
    public double precio;
    public String estado;

    // Constructor para inicializar la habitación cuando le des al botón "Añadir"
    public habitacionlog(String numero, String tipo, double precio, String estado) {
        this.numero = numero;
        this.tipo = tipo;
        this.precio = precio;
        this.estado = estado;
    }
}
