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
import javax.swing.JTable;


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
    
    public void aliminarGabitacion(JTable tablaHabitacion){
         int eli=tablaHabitacion.getSelectedRow();
        if (eli==-1) {
            JOptionPane.showMessageDialog(null, 
             "Por favor seleccione un estudiante de la tabla para eliminar", 
             "Ninguna fila seleccionada", 
             JOptionPane.WARNING_MESSAGE);
         return;
        }
        
         int confirmacion = JOptionPane.showConfirmDialog(null,
         "¿Está seguro que desea eliminar esta Habitacion?\n" +
         "Nombre: " + tablaHabitacion.getValueAt(eli, 1)+
         " Numero:"  + tablaHabitacion.getValueAt(eli, 0),
         "Confirmar eliminación",
         JOptionPane.YES_NO_OPTION);
         
         if (confirmacion == JOptionPane.YES_OPTION) {
         datos.listaHabitaciones.remove(eli);
        
         JOptionPane.showMessageDialog(null, 
             "Curso eliminado correctamente", 
             "Éxito", 
             JOptionPane.INFORMATION_MESSAGE);
     }
    }
    
    public Habitacion buscarHabitacion(int numero){
          for (Habitacion h : datos.listaHabitaciones) {
        if (h.getNumero() == numero) {
            return h;
        }
    }

    return null;
   }
}
