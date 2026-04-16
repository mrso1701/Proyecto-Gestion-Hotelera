/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ISABELLA
 */
public class TipoHabitacion {
    private String nombre; /// sencilla, double, suite
    private double precioPorNoche;

    public TipoHabitacion(String nombre, double precioPorNoche) {
        this.nombre = nombre;
        this.precioPorNoche = precioPorNoche;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioPorNoche() {
        return precioPorNoche;
    }

    public void setPrecioPorNoche(double precioPorNoche) {
        this.precioPorNoche = precioPorNoche;
    }
    
    
}
