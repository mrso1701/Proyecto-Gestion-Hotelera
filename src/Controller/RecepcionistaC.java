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
import static Model.DatosRecepcionista.listaHabitaciones;
import Model.Reserva;
import java.time.LocalDate;
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
    return correo.contains("@gmail.com");
}
    public void agregarConsumo( Reserva reserva, int cantidad, String producto, double precio, LocalDate fecha){
     Consumo c = new Consumo();
    c.setReserva(reserva);
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
  public Cliente buscarClienteRes(int id){
    for (Cliente c : datos.listaClientes) {
        if (c.getId() == id) {
            return c;
        }
    }
    return null;
}
  public void agregarReserva(int id, Cliente cliente, Habitacion habitacion,
                          LocalDate fechaEntrada, LocalDate fechaSalida, String estado) {

    Reserva r = new Reserva();

    r.setId(id);
    r.setCliente(cliente);
    r.setHabitacion(habitacion);
    r.setFechaEntrada(fechaEntrada);
    r.setFechaSalida(fechaSalida);
    r.setEstado(estado);

    datos.listaReservas.add(r);

    JOptionPane.showMessageDialog(null, "Reserva agregada correctamente 😎");
}
  
  public void eliminarReserva(JTable tablaReserva) {

    int fila = tablaReserva.getSelectedRow();

    if (fila == -1) {
        JOptionPane.showMessageDialog(null,
            "Por favor seleccione una reserva de la tabla para eliminar",
            "Ninguna fila seleccionada",
            JOptionPane.WARNING_MESSAGE);
        return;
    }

    int confirmacion = JOptionPane.showConfirmDialog(null,
        "¿Está seguro que desea eliminar esta reserva?\n" +
        "ID: " + tablaReserva.getValueAt(fila, 0) +
        " | Cliente: " + tablaReserva.getValueAt(fila, 1),
        "Confirmar eliminación",
        JOptionPane.YES_NO_OPTION);

    if (confirmacion == JOptionPane.YES_OPTION) {

        datos.listaReservas.remove(fila);

        JOptionPane.showMessageDialog(null,
            "Reserva eliminada correctamente",
            "Éxito",
            JOptionPane.INFORMATION_MESSAGE);
    }
}
  public Reserva buscarReserva(int id){
    for (Reserva r : datos.listaReservas) {
        if (r.getId() == id) {
            return r;
        }
    }
    return null;
}
  public Habitacion buscarHabitacionPorTexto(String texto) {
    for (Habitacion h : listaHabitaciones) {
        String formato = h.getNumero() + " - " 
                       + h.getTipo().getNombre() 
                       + " - piso" + h.getPiso();

        if (formato.equals(texto)) {
            return h;
        }
    }
    return null;
}
  public boolean validarDatos(String documento, String numero) {

    // Validar documento (10 dígitos)
    if (!documento.matches("\\d{10}")) {
        JOptionPane.showMessageDialog(null, "El documento debe tener exactamente 10 dígitos numéricos");
        return false;
    }

    // Validar número (10 dígitos)
    if (!numero.matches("\\d{10}")) {
        JOptionPane.showMessageDialog(null, "El número debe tener exactamente 10 dígitos numéricos");
        return false;
    }

    return true;
}
  private boolean habitacionOcupada(Habitacion h) {
    return h.getEstado().equalsIgnoreCase("Ocupada");
}
  public boolean estaFinalizada(Reserva r) {
    return r.getEstado().equalsIgnoreCase("Finalizada");
}
  
  public void datosPre(){
      
      datos.listaClientes.clear();
    datos.listaHabitaciones.clear();
    datos.listaReservas.clear();
    datos.listaConsumos.clear();
    datos.listaTipoHabitaciones.clear();

    TipoHabitacion sencilla = new TipoHabitacion("Sencilla", 80000);
    TipoHabitacion doble = new TipoHabitacion("Doble", 120000);
    TipoHabitacion suite = new TipoHabitacion("Suite", 200000);

    datos.listaTipoHabitaciones.add(sencilla);
    datos.listaTipoHabitaciones.add(doble);
    datos.listaTipoHabitaciones.add(suite);
    
    Habitacion h1 = new Habitacion(101, sencilla, 2, "Libre", 1);
    Habitacion h2 = new Habitacion(102, doble, 4, "Ocupada", 1);
    Habitacion h3 = new Habitacion(201, suite, 2, "Libre", 2);
    Habitacion h4 = new Habitacion(202, doble, 3, "Inactivo", 2);

    datos.listaHabitaciones.add(h1);
    datos.listaHabitaciones.add(h2);
    datos.listaHabitaciones.add(h3);
    datos.listaHabitaciones.add(h4);
    
     Cliente c1 = new Cliente(1, "Juan Perez", "1234567890", "CC", "Activo", "3001234567", "juan@gmail.com");
    Cliente c2 = new Cliente(2, "Maria Lopez", "0987654321", "CC", "Activo", "3019876543", "maria@gmail.com");
    Cliente c3 = new Cliente(3, "Carlos Ruiz", "1112223334", "CC", "Inactivo", "3021112233", "carlos@gmail.com");

    datos.listaClientes.add(c1);
    datos.listaClientes.add(c2);
    datos.listaClientes.add(c3);
    
       Reserva r1 = new Reserva(1, c1, h1,
            LocalDate.of(2026, 5, 20),
            LocalDate.of(2026, 5, 23),
            "Inactiva");

    Reserva r2 = new Reserva(2, c2, h2,
            LocalDate.of(2026, 5, 18),
            LocalDate.of(2026, 5, 22),
            "Activa");

    Reserva r3 = new Reserva(3, c3, h3,
            LocalDate.of(2026, 5, 10),
            LocalDate.of(2026, 5, 12),
            "Finalizada");

    datos.listaReservas.add(r1);
    datos.listaReservas.add(r2);
    datos.listaReservas.add(r3);
    
     Consumo con1 = new Consumo(1, r2, 2, "Coca-Cola", 5000, 10000, LocalDate.now());
    Consumo con2 = new Consumo(2, r2, 1, "Hamburguesa", 15000, 15000, LocalDate.now());
    Consumo con3 = new Consumo(3, r3, 3, "Agua", 3000, 9000, LocalDate.now());

    datos.listaConsumos.add(con1);
    datos.listaConsumos.add(con2);
    datos.listaConsumos.add(con3);
  }
}
