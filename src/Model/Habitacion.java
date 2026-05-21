/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ISABELLA
 */
public class Habitacion implements Identificacion{
    private int numero;
    private TipoHabitacion tipo;
    private int capacidad;
    private String estado;
    private int piso;

    public Habitacion(int numero, TipoHabitacion tipo, int capacidad, String estado, int piso) {
        this.numero = numero;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.estado = estado;
        this.piso = piso;
    }

    public Habitacion() {
    }

    public int getNumero() {
        return numero;
    }
    
    @Override
    public int getId(){
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    @Override
    public String getNombre(){
        return numero +" - "+ tipo.getNombre() + " - Piso"+ piso+" -"+ estado;
    }

    public TipoHabitacion getTipo() {
        return tipo;
    }

    public void setTipo(TipoHabitacion tipo) {
        this.tipo = tipo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }
}
