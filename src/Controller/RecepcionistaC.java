/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Habitacion;
import Model.TipoHabitacion;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import Model.DatosRecepcionista;


/**
 *
 * @author ISABELLA
 */
public class RecepcionistaC {
    private DatosRecepcionista datos;

    public RecepcionistaC(DatosRecepcionista datos) {
        this.datos = datos;
    }
    
    public void agregarHabitacion( int numero, TipoHabitacion tipo, int capacidad, String estado, int piso){
        Habitacion h = new Habitacion();
        
        h.setNumero(numero);
        h.setTipo(tipo);
        h.setCapacidad(capacidad);
        h.setEstado(estado);
        h.setPiso(piso);
        
        datos.listaHabitaciones.add(h);
        JOptionPane.showMessageDialog(null, "Habitacion Agregada Correctamente ");
    }
}
