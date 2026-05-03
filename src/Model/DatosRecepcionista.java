/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.List;
import java.util.ArrayList;
public class DatosRecepcionista {
    
    public static List<Cliente> listaClientes = new ArrayList<>();
    public static List<Habitacion> listaHabitaciones = new ArrayList<>();
    public static List<Reserva> listaReservas = new ArrayList<>();
    public static List<Consumo> listaConsumos = new ArrayList<>();
    public static List<TipoHabitacion> listaTipoHabitaciones = new ArrayList<>();

    public static List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public static List<Habitacion> getListaHabitaciones() {
        return listaHabitaciones;
    }

    public static List<Reserva> getListaReservas() {
        return listaReservas;
    }

    public static List<Consumo> getListaConsumos() {
        return listaConsumos;
    }

    public static List<TipoHabitacion> getListaTipoHabitaciones() {
        return listaTipoHabitaciones;
    }
    
    
    
}
