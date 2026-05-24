/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author ISABELLA
 */
public class Consumo {
    private int id_consumo;
    private Reserva reserva;
    private int cantidad;
    private String producto;
    private double precio;
    private double total;
    private LocalDate fecha;

    public Consumo(int id_consumo, Reserva reserva, int cantidad, String producto, double precio, double total, LocalDate fecha) {
        this.id_consumo = id_consumo;
        this.reserva = reserva;
        this.cantidad = cantidad;
        this.producto = producto;
        this.precio = precio;
        this.total = total;
        this.fecha = fecha;
    }

   

   

    public Consumo() {
    }
    

    public int getId_consumo() {
        return id_consumo;
    }

    public void setId_consumo(int id_consumo) {
        this.id_consumo = id_consumo;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

   

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getTotal() {
        return cantidad*precio;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
