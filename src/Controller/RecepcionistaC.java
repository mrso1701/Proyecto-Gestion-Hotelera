/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Cliente;
import Model.Consumo;
import Model.Habitacion;
import Model.TipoHabitacion;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import Model.DatosRecepcionista;
import static Model.DatosRecepcionista.listaConsumos;
import java.util.Date;
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
             "Por favor seleccione una habitacion de la tabla para eliminar", 
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
             "Habitacion eliminado correctamente", 
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
    
    public void agregarCliente(int id, String nombre, String documento, String tipoDocumento, String estado, String numero, String correo){
        Cliente c = new Cliente();
        
        c.setId(id);
        c.setNombre(nombre);
        c.setDocumento(documento);
        c.setTipoDocumento(tipoDocumento);
        c.setEstado(estado);
        c.setNumero(numero);
        c.setCorreo(correo);
        
        datos.listaClientes.add(c);
        JOptionPane.showMessageDialog(null, "Cliente agregado correctamente 😈");
             }
    
   
    public void eliminarCliente(JTable tablaCliente){
        int eli = tablaCliente.getSelectedRow();
         if (eli==-1) {
            JOptionPane.showMessageDialog(null, 
             "Por favor seleccione un cliente de la tabla para eliminar", 
             "Ninguna fila seleccionada", 
             JOptionPane.WARNING_MESSAGE);
         return;
        }
         
           int confirmacion = JOptionPane.showConfirmDialog(null,
         "¿Está seguro que desea eliminar este Cliente?\n" +
         "Nombre: " + tablaCliente.getValueAt(eli, 1)+
         " Id:"  + tablaCliente.getValueAt(eli, 0),
         "Confirmar eliminación",
         JOptionPane.YES_NO_OPTION);
         
         if (confirmacion == JOptionPane.YES_OPTION) {
         datos.listaClientes.remove(eli);
        
         JOptionPane.showMessageDialog(null, 
             "Cliente eliminado correctamente", 
             "Éxito", 
             JOptionPane.INFORMATION_MESSAGE);
     }
         
         
    }
    public Cliente buscarCliente(int id) {
    for (Cliente c : datos.listaClientes) {
        if (c.getId() == id) {
            return c;
        }
    }
    return null;
}
    
     public boolean validarSoloLetras(String... campos) {
    for (String campo : campos) {
        if (!campo.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            return false;
        }
    }
    return true;
}
    
    public boolean validarSoloNumeros(String texto) {
    return texto.matches("\\d+"); // solo dígitos
}
    public boolean validarCorreo(String correo) {
    return correo.contains("@");
}
    public void agregarConsumo( Cliente cliente, int cantidad, String producto, double precio, Date fecha){
     Consumo c = new Consumo();
    c.setCliente(cliente);
    c.setCantidad(cantidad);
    c.setProducto(producto);
    c.setPrecio(precio);

    
    c.setTotal(precio * cantidad);

    c.setFecha(fecha);

    datos.listaConsumos.add(c);

    JOptionPane.showMessageDialog(null, "Consumo agregado correctamente 😈");
    }
    
    public void eliminarConsumo(JTable tablaConsumo) {
    // 1. Obtener la fila seleccionada
    int eli = tablaConsumo.getSelectedRow();

    // 2. Verificar si hay una fila seleccionada
    if (eli == -1) {
        JOptionPane.showMessageDialog(null,
                "Por favor seleccione un consumo de la tabla para eliminar",
                "Ninguna fila seleccionada",
                JOptionPane.WARNING_MESSAGE);
        return;
    }

    // 3. Obtener datos de la fila para la confirmación (basado en las columnas de tu imagen)
    // Asumiendo: Columna 0 = Cliente, Columna 2 = Producto, Columna 4 = Fecha
    String cliente = tablaConsumo.getValueAt(eli, 0).toString();
    String producto = tablaConsumo.getValueAt(eli, 2).toString();
    String fecha = tablaConsumo.getValueAt(eli, 4).toString();

    // 4. Ventana de confirmación
    int confirmacion = JOptionPane.showConfirmDialog(null,
            "¿Está seguro que desea eliminar este registro de consumo?\n\n"
            + "Cliente: " + cliente + "\n"
            + "Producto: " + producto + "\n"
            + "Fecha: " + fecha,
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION);

    // 5. Ejecutar eliminación si el usuario acepta
    if (confirmacion == JOptionPane.YES_OPTION) {
        // Elimina de la lista en el controlador/modelo
        // Asegúrate de que 'controlador' o 'datos' sea accesible desde donde pegues esto
        listaConsumos.remove(eli); 

  

        JOptionPane.showMessageDialog(null,
                "Consumo eliminado correctamente 😈",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
    
  public Cliente buscarClienteCon(int id) {
    for (Cliente c : datos.listaClientes) {
        if (c.getId() == id) {
            return c;
        }
    }
    return null;
}
}
