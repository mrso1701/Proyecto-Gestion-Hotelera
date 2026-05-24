/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.RecepcionistaC;
import Model.Cliente;
import Model.Consumo;
import Model.DatosRecepcionista;
import Model.Habitacion;
import Model.Identificacion;
import Model.Reserva;
import Model.TipoHabitacion;
import java.awt.Color;
import java.awt.Component;
import java.awt.HeadlessException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import javax.swing.ComboBoxModel;

/**
 *
 * @author ISABELLA
 */
public class Recepcionista extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Recepcionista.class.getName());
      DefaultTableModel modeloCliente = new DefaultTableModel();
      DefaultTableModel modeloHabitacion = new DefaultTableModel();
      DefaultTableModel modeloConsumo = new DefaultTableModel();
      DefaultTableModel modeloReserva = new DefaultTableModel();
      DefaultTableModel modeloCheckOut = new DefaultTableModel();
      private DatosRecepcionista datos = new DatosRecepcionista();
      private RecepcionistaC controlador = new RecepcionistaC(datos);
      private Habitacion habitacionSeleccionada = null;
      private Cliente clienteSeleccionado = null;
      private Consumo consumoSeleccionado = null;
      private Reserva reservaSeleccionada = null;
      private Reserva reservaActual = null;
    /**
     * Creates new form Recepcionista
     */
    public Recepcionista() {
        initComponents();
     
      cargarTiposHabitacion();
      
         
         cargarTiposHabitacion();
        estilizarTabla(jtable_habitac);
        estilizarTabla(jtable_consumo);
        estilizarTabla(jtable_cliente);
        estilizarTabla(jtable_reserva);
        
        estilizarTextField(piso_h);
        estilizarTextField(cantidad_con); estilizarTextField(fecha_con);
        estilizarTextField(id_habitacion_h);estilizarTextField(precio_h); estilizarTextField(capaci_h);
        estilizarTextField(id_cliente_cli); estilizarTextField(numero_cli); estilizarTextField(correo_cli); estilizarTextField(nombre_cli); estilizarTextField(documento_cli);
        estilizarTextField(id_reserva); estilizarTextField(id_cliente_re); estilizarTextField(nombre_cliente2_re); estilizarTextField(fecha_entrada2); estilizarTextField(fecha_salida2);
        //cliente
        modeloCliente.addColumn("ID");
        modeloCliente.addColumn("Nombre");
        modeloCliente.addColumn("Numero");
        modeloCliente.addColumn("Correo");
        modeloCliente.addColumn("Documeto");
        modeloCliente.addColumn("Tipo");
        modeloCliente.addColumn("Estado");
        
        refrescarTablaCientes();
        
        //habitacion
        modeloHabitacion.addColumn("Numero");
        modeloHabitacion.addColumn("Tipo");
        modeloHabitacion.addColumn("Precio");
        modeloHabitacion.addColumn("Capacidad");
        modeloHabitacion.addColumn("Estado");
        modeloHabitacion.addColumn("Piso");
        
        refrescarTablaHabitacion();
        
        //consumo
        modeloConsumo.addColumn("Cliente");
        modeloConsumo.addColumn("Producto");
        modeloConsumo.addColumn("Cantidad");
        modeloConsumo.addColumn("Precio");
        modeloConsumo.addColumn("Total");
        modeloConsumo.addColumn("Fecha");
        
        refrescarTablaConsumo();
        
        //reserva
        modeloReserva.addColumn("ID Reserva");
        modeloReserva.addColumn("ID Cliente");
        modeloReserva.addColumn("Nombre");
        modeloReserva.addColumn("Habitacion");
        modeloReserva.addColumn("Fecha Entrada");
        modeloReserva.addColumn("Fecha Salida");
        modeloReserva.addColumn("Estado");
        
        refrescarTablaReserva();
        
        //check ouy
        
        modeloCheckOut.addColumn("Producto/Servicio");
        modeloCheckOut.addColumn("Cantidad");
        modeloCheckOut.addColumn("Precio");
        modeloCheckOut.addColumn("Total");
        
        refrescarTablaCheckOut(reservaSeleccionada);
        
        iniciar();
    }
    
    //////////////// refrescar tablaaasasasas
     public void refrescarTablaCientes(){
        modeloCliente.setRowCount(0); ///limpia pues la tabla 
        
        for (Cliente c : DatosRecepcionista.listaClientes) {
            Object[] fila = new Object[7];
            fila[0] = c.getId();
            fila[1] = c.getNombre();
            fila[2] = c.getNumero();
            fila[3] = c.getCorreo();
            fila[4] = c.getDocumento();
            fila[5] = c.getTipoDocumento();
            fila[6] = c.getEstado();
            
            modeloCliente.addRow(fila);

        }
        jtable_cliente.setModel(modeloCliente);
        jtable_cliente.setAutoResizeMode(jtable_cliente.AUTO_RESIZE_OFF);

         jtable_cliente.getColumnModel().getColumn(0).setPreferredWidth(50);  // ID
         jtable_cliente.getColumnModel().getColumn(1).setPreferredWidth(150); // Nombre
         jtable_cliente.getColumnModel().getColumn(2).setPreferredWidth(100); // Número
         jtable_cliente.getColumnModel().getColumn(3).setPreferredWidth(180); // Correo
         jtable_cliente.getColumnModel().getColumn(4).setPreferredWidth(120); // Documento
         jtable_cliente.getColumnModel().getColumn(5).setPreferredWidth(100); // Tipo
         jtable_cliente.getColumnModel().getColumn(6).setPreferredWidth(80);  // Estado
         
    }
    
    public void refrescarTablaHabitacion(){
        modeloHabitacion.setRowCount(0);
        
        for (Habitacion h : DatosRecepcionista.listaHabitaciones) {
            Object[] fila = new Object[6];
            fila[0] = h.getNumero();
            fila[1] = h.getTipo().getNombre();
            fila[2] = h.getTipo().getPrecioPorNoche();
            fila[3] = h.getCapacidad();
            fila[4] = h.getEstado();
            fila[5] = h.getPiso();
            
            modeloHabitacion.addRow(fila);
        }
        jtable_habitac.setModel(modeloHabitacion);
    }
    
    public void refrescarTablaConsumo(){
        modeloConsumo.setRowCount(0);
        
        for (Consumo c : DatosRecepcionista.listaConsumos) {
            Object[] fila = new Object[6];
            fila[0] = c.getReserva().getId()+" - "+c.getReserva().getCliente().getNombre();
            fila[1] = c.getProducto();
            fila[2] = c.getCantidad();
            fila[3] = c.getPrecio();
            fila[4] = c.getTotal();
            fila[5] = c.getFecha();
            
            modeloConsumo.addRow(fila);
        }
        jtable_consumo.setModel(modeloConsumo);
    }
    
    public void refrescarTablaReserva(){
        modeloReserva.setRowCount(0);
        
        for (Reserva r : DatosRecepcionista.listaReservas) {
            Object[] fila = new Object[7];
            fila[0] = r.getId();
            fila[1] = r.getCliente().getId();
            fila[2] = r.getCliente().getNombre();
            fila[3] = r.getHabitacion().getNumero();
            fila[4] = r.getFechaEntrada();
            fila[5] = r.getFechaSalida();
            fila[6] = r.getEstado();
            
            modeloReserva.addRow(fila);
        }
        jtable_reserva.setModel(modeloReserva);
        
        jtable_reserva.setAutoResizeMode(jtable_reserva.AUTO_RESIZE_OFF);

        jtable_reserva.getColumnModel().getColumn(0).setPreferredWidth(80);  // ID Reserva
        jtable_reserva.getColumnModel().getColumn(1).setPreferredWidth(90);  // ID Cliente
        jtable_reserva.getColumnModel().getColumn(2).setPreferredWidth(150); // Nombre
        jtable_reserva.getColumnModel().getColumn(3).setPreferredWidth(90);  // Habitación
        jtable_reserva.getColumnModel().getColumn(4).setPreferredWidth(130); // Fecha Entrada
        jtable_reserva.getColumnModel().getColumn(5).setPreferredWidth(130); // Fecha Salida
        jtable_reserva.getColumnModel().getColumn(6).setPreferredWidth(80); // Estado
    }
    
    private void estilizarTabla(JTable tabla) {

    // 🎯 HEADER (encabezado)
    JTableHeader header = tabla.getTableHeader();
    header.setOpaque(false);

    header.setDefaultRenderer(new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {

            Component c = super.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, column);

            c.setBackground(new Color(70, 0, 20)); // vinotinto oscuro
            c.setForeground(Color.WHITE);

            return c;
        }
    });

    // CUERPO DE LA TABLA
    tabla.setBackground(new Color(30, 30, 30)); // fondo oscuro
    tabla.setForeground(Color.WHITE);

    //  LÍNEAS (cuadritos)
    tabla.setGridColor(new Color(90, 0, 20)); // vinotinto
    tabla.setShowGrid(true);

    //  BORDE EXTERNO
    tabla.setBorder(
        javax.swing.BorderFactory.createLineBorder(new Color(90, 0, 20))
    );

    // ✨ SELECCIÓN (cuando haces click)
    tabla.setSelectionBackground(new Color(90, 0, 20));
    tabla.setSelectionForeground(Color.WHITE);

    // 💡 opcional: altura de filas (se ve mejor)
    tabla.setRowHeight(25);
}
    
   private void estilizarTextField(JTextField txt) {
    txt.setBorder(
        javax.swing.BorderFactory.createLineBorder(new Color(90, 0, 20), 2)
    );
  
   
}
   
   public void iniciar (){
       controlador.datosPre();
       refrescarTablaCientes();
       refrescarTablaConsumo();
       refrescarTablaHabitacion();
       refrescarTablaReserva();
       cargarHabitacionesEnCombo();
   }
   private void cargarTiposHabitacion() {
    combo_habi.removeAllItems();
    combo_habi.addItem("Seleccione...");
    combo_habi.addItem("Suite");
    combo_habi.addItem("Doble");
    combo_habi.addItem("Sencilla");
}
   
   private TipoHabitacion obtenerTipo(String nombre) {
    switch (nombre) {
        case "Suite": return new TipoHabitacion("Suite", 200000);
        case "Doble": return new TipoHabitacion("Doble", 120000);
        case "Sencilla": return new TipoHabitacion("Sencilla", 80000);
        default: return null;
      
    }
}
   




   public void cargarHabitacionesEnCombo() {
    DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();

    for (Habitacion h : DatosRecepcionista.listaHabitaciones) {
        modelo.addElement(h.getNumero() +  " - "+ h.getTipo().getNombre()+ " - piso"+ h.getPiso());
    }

    combo_reserva.setModel((ComboBoxModel) modelo); // 🔥 FIX
}


public Habitacion obtenerHabitacionSeleccionada() {

    String texto = combo_reserva.getSelectedItem().toString();

    int numero = Integer.parseInt(texto.split(" - ")[0]);

    for (Habitacion h : DatosRecepcionista.listaHabitaciones) {
        if (h.getNumero() == numero) {
            return h;
        }
    }

    return null;
}

  private boolean habitacionOcupada(Habitacion h) {
        return h.getEstado().equalsIgnoreCase("Ocupada");
    }





public double obtenerPrecioProducto(String producto) {

    switch (producto) {
        case "Coca-Cola":
            return 3000;
        case "Agua":
            return 2000;
        case "Pizza":
            return 15000;
        case "Hamburguesa":
            return 12000;
        case "Dulces":
            return 600;
        case "Desayuno-Panqueques": 
              return 12000;
        case "Almuerzo-Mote de queso":
            return 12000;
        case "Cena-Chuleta con papas":
            return 20000;
        case "Picada":
            return 20000;
        case "Servicio Buseo":    
            return 50000;
        case "Recorrido Turistico":
            return 50000;
        case "Servicio Lancha":
            return 100000;
        default:
            return 0;
    }
}

public void refrescarTablaCheckOut(Reserva reserva){
   
modeloCheckOut.setRowCount(0);

for (Consumo c : datos.listaConsumos) {
    if (c.getReserva().getId() == reserva.getId()) {
       Object[] fila = new Object[4];
       fila[0] = c.getProducto();
       fila[1] = c.getCantidad();
       fila[2] = c.getPrecio();
       fila[3] = c.getTotal();
       
       modeloCheckOut.addRow(fila);
    }
}
    tabla_checkout.setModel(modeloCheckOut);
}
private Reserva obtenerReservaPorId() {
    String texto = reserva_con.getText().trim();

    if (texto.isEmpty()) return null;

    try {
        int id = Integer.parseInt(texto);

        for (Reserva r : DatosRecepcionista.listaReservas) {
            if (r.getId() == id) {
                return r;
            }
        }

    } catch (Exception e) {
        return null;
    }

    return null;
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jTabbedPane8 = new javax.swing.JTabbedPane();
        jPanel17 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        nombre_cliente2_re = new javax.swing.JTextField();
        combo_reserva = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        estado_reserva = new javax.swing.JComboBox<>();
        jLabel30 = new javax.swing.JLabel();
        fecha_entrada2 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        fecha_salida2 = new javax.swing.JTextField();
        boton_agregar_r = new javax.swing.JToggleButton();
        bt_eliminar_r = new javax.swing.JToggleButton();
        bt_modificar_r = new javax.swing.JToggleButton();
        bt_buscar_r = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtable_reserva = new javax.swing.JTable();
        jLabel24 = new javax.swing.JLabel();
        id_reserva = new javax.swing.JTextField();
        id_cliente_re = new javax.swing.JTextField();
        bt_buscar_cliente_re = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        id_cliente_cli = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tipo_doc_cli = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        documento_cli = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        numero_cli = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        correo_cli = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        estado_c = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtable_cliente = new javax.swing.JTable();
        boton_agreegar_cli = new javax.swing.JButton();
        Eliminar_clie = new javax.swing.JButton();
        bt_buscar_cli = new javax.swing.JButton();
        bt_modificar_cli = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        nombre_cli = new javax.swing.JTextField();
        Limpiar_C = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        id_habitacion_h = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        combo_habi = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        precio_h = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        capaci_h = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        combo_estado_h = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        piso_h = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtable_habitac = new javax.swing.JTable();
        bt_aagregar_h = new javax.swing.JButton();
        bt_eliminar_h = new javax.swing.JButton();
        bt_buscar_h = new javax.swing.JButton();
        bt_modificar_h = new javax.swing.JButton();
        limpiar_h = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        combo_producto_con = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        cantidad_con = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        fecha_con = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        jtable_consumo = new javax.swing.JTable();
        bt_agregar_con = new javax.swing.JButton();
        bt_eliminar_con = new javax.swing.JButton();
        bt_buscar_con = new javax.swing.JButton();
        bt_modificar_con = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        precio_con = new javax.swing.JTextField();
        reserva_con = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        nombre_reserva_chekin = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        fecha_entrada_resChek = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        fecha_salidaCheevk = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        dni_checkin = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        id_res_chek = new javax.swing.JTextField();
        habitacioncheckout = new javax.swing.JTextField();
        check_in = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        cliendecheckout = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        documentoCheckou = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        habitacion_checkout = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        fengtrada_checkout = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        fsalidacheckout = new javax.swing.JTextField();
        scrool = new javax.swing.JScrollPane();
        tabla_checkout = new javax.swing.JTable();
        bt_calcular_checkout = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        calcular_checkout = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 0, 0));
        jPanel1.setForeground(new java.awt.Color(102, 0, 51));
        jPanel1.setToolTipText("");

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Segoe Script", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("PANEL DE RECEPCIONISTA");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 576, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setPreferredSize(new java.awt.Dimension(150, 484));

        jPanel4.setBackground(new java.awt.Color(153, 0, 0));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe Print", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Reservas");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(153, 0, 0));
        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe Print", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Clientes");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(46, 46, 46))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(153, 0, 0));
        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel6MouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe Print", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Consumo");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(35, 35, 35))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(153, 0, 0));
        jPanel10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel10.setPreferredSize(new java.awt.Dimension(180, 59));
        jPanel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel10MouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe Print", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Habitaciones");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Segoe Print", 0, 14)); // NOI18N
        jLabel6.setText("Salir");

        jPanel11.setBackground(new java.awt.Color(153, 0, 0));
        jPanel11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel11.setPreferredSize(new java.awt.Dimension(198, 59));
        jPanel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel11MouseClicked(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Segoe Print", 0, 24)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Check-in");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel33)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel33)
                .addGap(20, 20, 20))
        );

        jPanel12.setBackground(new java.awt.Color(153, 0, 0));
        jPanel12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel12.setPreferredSize(new java.awt.Dimension(198, 59));
        jPanel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel12MouseClicked(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Segoe Print", 0, 24)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("Check-out");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel35)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(jLabel6)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        jTabbedPane8.setBackground(new java.awt.Color(102, 0, 0));
        jTabbedPane8.setForeground(new java.awt.Color(153, 0, 0));

        jPanel17.setForeground(new java.awt.Color(255, 255, 255));

        jLabel25.setFont(new java.awt.Font("Segoe Script", 0, 36)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(102, 0, 0));
        jLabel25.setText("Bienvenido Recepcionista");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(108, Short.MAX_VALUE)
                .addComponent(jLabel25)
                .addGap(80, 80, 80))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(185, 185, 185)
                .addComponent(jLabel25)
                .addContainerGap(391, Short.MAX_VALUE))
        );

        jTabbedPane8.addTab("tap1", jPanel17);

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setForeground(new java.awt.Color(255, 255, 255));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("ID Cliente");

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Nombre");

        nombre_cliente2_re.setEditable(false);

        combo_reserva.setBackground(new java.awt.Color(51, 0, 0));
        combo_reserva.setForeground(new java.awt.Color(255, 255, 255));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Habitacion");

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Estado");

        estado_reserva.setBackground(new java.awt.Color(51, 0, 0));
        estado_reserva.setForeground(new java.awt.Color(255, 255, 255));
        estado_reserva.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar...", "Activa", "Inactiva", "Finalizado" }));
        estado_reserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estado_reservaActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Fecha Entrada");

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Fecha Salida");

        fecha_salida2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fecha_salida2ActionPerformed(evt);
            }
        });

        boton_agregar_r.setBackground(new java.awt.Color(0, 0, 0));
        boton_agregar_r.setFont(new java.awt.Font("Segoe Script", 0, 14)); // NOI18N
        boton_agregar_r.setForeground(new java.awt.Color(255, 255, 255));
        boton_agregar_r.setText("Agregar");
        boton_agregar_r.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_agregar_rActionPerformed(evt);
            }
        });

        bt_eliminar_r.setBackground(new java.awt.Color(0, 0, 0));
        bt_eliminar_r.setFont(new java.awt.Font("Segoe Script", 0, 14)); // NOI18N
        bt_eliminar_r.setForeground(new java.awt.Color(255, 255, 255));
        bt_eliminar_r.setText("Eliminar");
        bt_eliminar_r.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_eliminar_rActionPerformed(evt);
            }
        });

        bt_modificar_r.setBackground(new java.awt.Color(0, 0, 0));
        bt_modificar_r.setFont(new java.awt.Font("Segoe Script", 0, 14)); // NOI18N
        bt_modificar_r.setForeground(new java.awt.Color(255, 255, 255));
        bt_modificar_r.setText("Modificar");
        bt_modificar_r.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_modificar_rActionPerformed(evt);
            }
        });

        bt_buscar_r.setBackground(new java.awt.Color(0, 0, 0));
        bt_buscar_r.setFont(new java.awt.Font("Segoe Script", 0, 14)); // NOI18N
        bt_buscar_r.setForeground(new java.awt.Color(255, 255, 255));
        bt_buscar_r.setText("Buscar");
        bt_buscar_r.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_buscar_rActionPerformed(evt);
            }
        });

        jtable_reserva.setForeground(new java.awt.Color(255, 255, 255));
        jtable_reserva.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jtable_reserva);

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("ID Reserva");

        id_cliente_re.setEditable(false);

        bt_buscar_cliente_re.setBackground(new java.awt.Color(0, 0, 0));
        bt_buscar_cliente_re.setFont(new java.awt.Font("Segoe Print", 0, 12)); // NOI18N
        bt_buscar_cliente_re.setForeground(new java.awt.Color(255, 255, 255));
        bt_buscar_cliente_re.setText("Buscar Cliente");
        bt_buscar_cliente_re.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_buscar_cliente_reActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 682, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(boton_agregar_r, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addGap(12, 12, 12))
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30))
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel18Layout.createSequentialGroup()
                                    .addGap(53, 53, 53)
                                    .addComponent(bt_eliminar_r)
                                    .addGap(78, 78, 78)
                                    .addComponent(bt_modificar_r)
                                    .addGap(71, 71, 71)
                                    .addComponent(bt_buscar_r)
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(jPanel18Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(fecha_entrada2)
                                        .addComponent(combo_reserva, 0, 109, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel31)
                                    .addGap(18, 18, 18)
                                    .addComponent(fecha_salida2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(34, 34, 34))
                                .addGroup(jPanel18Layout.createSequentialGroup()
                                    .addGap(19, 19, 19)
                                    .addComponent(id_reserva, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(37, 37, 37)
                                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel18Layout.createSequentialGroup()
                                            .addComponent(bt_buscar_cliente_re)
                                            .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(jPanel18Layout.createSequentialGroup()
                                            .addComponent(jLabel26)
                                            .addGap(18, 18, 18)
                                            .addComponent(id_cliente_re, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel27)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(nombre_cliente2_re, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGap(287, 287, 287)
                                .addComponent(jLabel29)
                                .addGap(56, 56, 56)
                                .addComponent(estado_reserva, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)))))
                .addGap(0, 2, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(id_reserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(id_cliente_re, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombre_cliente2_re, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(combo_reserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29)
                    .addComponent(estado_reserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(fecha_entrada2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31)
                    .addComponent(fecha_salida2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(bt_buscar_cliente_re)
                .addGap(29, 29, 29)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boton_agregar_r)
                    .addComponent(bt_eliminar_r)
                    .addComponent(bt_modificar_r)
                    .addComponent(bt_buscar_r))
                .addGap(42, 42, 42)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        jTabbedPane8.addTab("Res", jPanel18);

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Id Cliente");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tipo Documento");

        tipo_doc_cli.setBackground(new java.awt.Color(51, 0, 0));
        tipo_doc_cli.setForeground(new java.awt.Color(255, 255, 255));
        tipo_doc_cli.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar...", "C.C", "Pasaporte" }));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Documento");

        documento_cli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                documento_cliActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Numero");

        numero_cli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numero_cliActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Correo");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Estado");

        estado_c.setBackground(new java.awt.Color(51, 0, 0));
        estado_c.setForeground(new java.awt.Color(255, 255, 255));
        estado_c.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar...", "Activo", "Inactivo", " " }));
        estado_c.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estado_cActionPerformed(evt);
            }
        });

        jtable_cliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jtable_cliente);

        boton_agreegar_cli.setBackground(new java.awt.Color(0, 0, 0));
        boton_agreegar_cli.setFont(new java.awt.Font("Segoe Script", 0, 14)); // NOI18N
        boton_agreegar_cli.setForeground(new java.awt.Color(255, 255, 255));
        boton_agreegar_cli.setText("Agregar");
        boton_agreegar_cli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_agreegar_cliActionPerformed(evt);
            }
        });

        Eliminar_clie.setBackground(new java.awt.Color(0, 0, 0));
        Eliminar_clie.setFont(new java.awt.Font("Segoe Script", 0, 14)); // NOI18N
        Eliminar_clie.setForeground(new java.awt.Color(255, 255, 255));
        Eliminar_clie.setText("Eliminar");
        Eliminar_clie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Eliminar_clieActionPerformed(evt);
            }
        });

        bt_buscar_cli.setBackground(new java.awt.Color(0, 0, 0));
        bt_buscar_cli.setFont(new java.awt.Font("Segoe Script", 0, 14)); // NOI18N
        bt_buscar_cli.setForeground(new java.awt.Color(255, 255, 255));
        bt_buscar_cli.setText("Buscar");
        bt_buscar_cli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_buscar_cliActionPerformed(evt);
            }
        });

        bt_modificar_cli.setBackground(new java.awt.Color(0, 0, 0));
        bt_modificar_cli.setFont(new java.awt.Font("Segoe Script", 0, 14)); // NOI18N
        bt_modificar_cli.setForeground(new java.awt.Color(255, 255, 255));
        bt_modificar_cli.setText("Modificar");
        bt_modificar_cli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_modificar_cliActionPerformed(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Nombre Completo");

        Limpiar_C.setBackground(new java.awt.Color(0, 0, 0));
        Limpiar_C.setForeground(new java.awt.Color(255, 255, 255));
        Limpiar_C.setText("L");
        Limpiar_C.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Limpiar_CActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tipo_doc_cli, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(17, 17, 17))
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(numero_cli, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(id_cliente_cli))
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(correo_cli, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel34)
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addGap(20, 20, 20))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel19Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(bt_buscar_cli)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nombre_cli)
                            .addComponent(documento_cli)
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(estado_c, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel19Layout.createSequentialGroup()
                                        .addComponent(bt_modificar_cli)
                                        .addGap(28, 28, 28)
                                        .addComponent(Limpiar_C, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 659, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(boton_agreegar_cli)
                        .addGap(74, 74, 74)
                        .addComponent(Eliminar_clie)))
                .addGap(39, 39, 39))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(id_cliente_cli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34)
                    .addComponent(nombre_cli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tipo_doc_cli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(documento_cli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numero_cli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel10)
                    .addComponent(estado_c, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(correo_cli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boton_agreegar_cli)
                    .addComponent(Eliminar_clie)
                    .addComponent(bt_buscar_cli)
                    .addComponent(bt_modificar_cli)
                    .addComponent(Limpiar_C))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        jTabbedPane8.addTab("Cliente", jPanel19);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("ID Habitacion");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Tipo");

        combo_habi.setBackground(new java.awt.Color(51, 0, 0));
        combo_habi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        combo_habi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_habiActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Precio");

        precio_h.setEditable(false);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Capacidad");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Estado");

        combo_estado_h.setBackground(new java.awt.Color(51, 0, 0));
        combo_estado_h.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar...", "Libre", "Inactivo", "Ocupada" }));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Piso");

        jtable_habitac.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(jtable_habitac);

        bt_aagregar_h.setBackground(new java.awt.Color(0, 0, 0));
        bt_aagregar_h.setFont(new java.awt.Font("Segoe Script", 0, 14)); // NOI18N
        bt_aagregar_h.setForeground(new java.awt.Color(255, 255, 255));
        bt_aagregar_h.setText("Agregar");
        bt_aagregar_h.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_aagregar_hActionPerformed(evt);
            }
        });

        bt_eliminar_h.setBackground(new java.awt.Color(0, 0, 0));
        bt_eliminar_h.setFont(new java.awt.Font("Segoe Script", 0, 14)); // NOI18N
        bt_eliminar_h.setForeground(new java.awt.Color(255, 255, 255));
        bt_eliminar_h.setText("Eliminar");
        bt_eliminar_h.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_eliminar_hActionPerformed(evt);
            }
        });

        bt_buscar_h.setBackground(new java.awt.Color(0, 0, 0));
        bt_buscar_h.setFont(new java.awt.Font("Segoe Script", 0, 14)); // NOI18N
        bt_buscar_h.setForeground(new java.awt.Color(255, 255, 255));
        bt_buscar_h.setText("Buscar");
        bt_buscar_h.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_buscar_hActionPerformed(evt);
            }
        });

        bt_modificar_h.setBackground(new java.awt.Color(0, 0, 0));
        bt_modificar_h.setFont(new java.awt.Font("Segoe Script", 0, 14)); // NOI18N
        bt_modificar_h.setForeground(new java.awt.Color(255, 255, 255));
        bt_modificar_h.setText("Modificar");
        bt_modificar_h.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_modificar_hActionPerformed(evt);
            }
        });

        limpiar_h.setBackground(new java.awt.Color(0, 0, 0));
        limpiar_h.setForeground(new java.awt.Color(255, 255, 255));
        limpiar_h.setText("L");
        limpiar_h.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiar_hActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(combo_estado_h, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(132, 132, 132)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(id_habitacion_h, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(81, 81, 81)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGap(71, 71, 71)
                                        .addComponent(bt_eliminar_h))
                                    .addComponent(precio_h, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(53, 53, 53)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bt_buscar_h)))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(bt_aagregar_h)))
                .addGap(22, 22, 22)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(piso_h, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(combo_habi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(capaci_h, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(bt_modificar_h)
                        .addGap(32, 32, 32)
                        .addComponent(limpiar_h, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 629, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(id_habitacion_h, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(combo_habi, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(jLabel16)
                        .addComponent(precio_h, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(capaci_h, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(piso_h, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo_estado_h, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_aagregar_h)
                    .addComponent(bt_eliminar_h)
                    .addComponent(bt_buscar_h)
                    .addComponent(bt_modificar_h)
                    .addComponent(limpiar_h))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );

        jTabbedPane8.addTab("Habit", jPanel8);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Producto");

        combo_producto_con.setBackground(new java.awt.Color(51, 0, 0));
        combo_producto_con.setForeground(new java.awt.Color(255, 255, 255));
        combo_producto_con.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione...", "Coca-Cola", "Agua", "Pizza", "Hamburguesa", "Dulces", "Desayuno-Panqueques", "Almuerzo-Mote de queso", "Cena-Chuleta con papas", "Picada", "Servicio Buseo    ", "Recorrido Turistico", "Servicio Lancha" }));
        combo_producto_con.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_producto_conActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Cantidad");

        cantidad_con.setFont(new java.awt.Font("Segoe Script", 0, 14)); // NOI18N

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Fecha");

        fecha_con.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fecha_conActionPerformed(evt);
            }
        });

        jtable_consumo.setBackground(new java.awt.Color(102, 102, 102));
        jtable_consumo.setForeground(new java.awt.Color(255, 255, 255));
        jtable_consumo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(jtable_consumo);

        bt_agregar_con.setBackground(new java.awt.Color(0, 0, 0));
        bt_agregar_con.setFont(new java.awt.Font("Segoe Script", 0, 14)); // NOI18N
        bt_agregar_con.setForeground(new java.awt.Color(255, 255, 255));
        bt_agregar_con.setText("Agregar");
        bt_agregar_con.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_agregar_conActionPerformed(evt);
            }
        });

        bt_eliminar_con.setBackground(new java.awt.Color(0, 0, 0));
        bt_eliminar_con.setFont(new java.awt.Font("Segoe Script", 0, 14)); // NOI18N
        bt_eliminar_con.setForeground(new java.awt.Color(255, 255, 255));
        bt_eliminar_con.setText("Eliminar");
        bt_eliminar_con.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_eliminar_conActionPerformed(evt);
            }
        });

        bt_buscar_con.setBackground(new java.awt.Color(0, 0, 0));
        bt_buscar_con.setFont(new java.awt.Font("Segoe Script", 0, 14)); // NOI18N
        bt_buscar_con.setForeground(new java.awt.Color(255, 255, 255));
        bt_buscar_con.setText("Buscar");
        bt_buscar_con.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_buscar_conActionPerformed(evt);
            }
        });

        bt_modificar_con.setBackground(new java.awt.Color(0, 0, 0));
        bt_modificar_con.setFont(new java.awt.Font("Segoe Script", 0, 14)); // NOI18N
        bt_modificar_con.setForeground(new java.awt.Color(255, 255, 255));
        bt_modificar_con.setText("Modificar");
        bt_modificar_con.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_modificar_conActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Reserva");

        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Precio");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 652, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(bt_agregar_con)
                .addGap(59, 59, 59)
                .addComponent(bt_eliminar_con)
                .addGap(79, 79, 79)
                .addComponent(bt_buscar_con)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bt_modificar_con)
                .addGap(46, 46, 46))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(combo_producto_con, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(cantidad_con, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(reserva_con, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(fecha_con, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(precio_con, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(97, 97, 97))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel22)
                    .addComponent(combo_producto_con, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reserva_con, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel23)
                        .addComponent(cantidad_con, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel36))
                    .addComponent(precio_con))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel32)
                    .addComponent(fecha_con, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_agregar_con)
                    .addComponent(bt_eliminar_con)
                    .addComponent(bt_buscar_con)
                    .addComponent(bt_modificar_con))
                .addGap(42, 42, 42)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(96, Short.MAX_VALUE))
        );

        jTabbedPane8.addTab("Cons", jPanel9);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        jPanel15.setBackground(new java.awt.Color(153, 153, 153));
        jPanel15.setBorder(new javax.swing.border.MatteBorder(null));

        jButton2.setBackground(new java.awt.Color(204, 0, 0));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setText("Buscar reserva");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Huesped");

        nombre_reserva_chekin.setEditable(false);

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("Fecha entrada");

        fecha_entrada_resChek.setEditable(false);
        fecha_entrada_resChek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fecha_entrada_resChekActionPerformed(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("Fecha Salida");

        fecha_salidaCheevk.setEditable(false);

        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("DNI/Pasaporte");

        dni_checkin.setEditable(false);

        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("Habitacion Asignada");

        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(0, 0, 0));
        jLabel41.setText("ID Reserva");

        id_res_chek.setEditable(false);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(233, 233, 233)
                        .addComponent(jButton2))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel20))
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel15Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel40)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(habitacioncheckout, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel15Layout.createSequentialGroup()
                            .addGap(26, 26, 26)
                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel39))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(fecha_salidaCheevk)
                                .addComponent(dni_checkin, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addGap(0, 25, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel37)
                        .addGap(18, 18, 18)
                        .addComponent(fecha_entrada_resChek, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nombre_reserva_chekin, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(id_res_chek, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(259, 259, 259))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jButton2)
                .addGap(20, 20, 20)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(id_res_chek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(nombre_reserva_chekin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(fecha_entrada_resChek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(fecha_salidaCheevk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(dni_checkin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(habitacioncheckout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        check_in.setBackground(new java.awt.Color(204, 0, 0));
        check_in.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        check_in.setForeground(new java.awt.Color(255, 255, 255));
        check_in.setText("CHECK-IN");
        check_in.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_inActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(270, 270, 270)
                        .addComponent(check_in)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(check_in)
                .addContainerGap(167, Short.MAX_VALUE))
        );

        jTabbedPane8.addTab("checkin", jPanel13);

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        jPanel16.setBackground(new java.awt.Color(153, 153, 153));
        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton3.setBackground(new java.awt.Color(255, 0, 0));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Buscar Reserva ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setText("Cliente: ");

        cliendecheckout.setEditable(false);

        jLabel43.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setText("Documento:");

        documentoCheckou.setEditable(false);

        jLabel44.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setText("Habitacion:");

        habitacion_checkout.setEditable(false);

        jLabel45.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(0, 0, 0));
        jLabel45.setText("Fecha entrada: ");

        fengtrada_checkout.setEditable(false);

        jLabel46.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setText("Fecha Salida:");

        fsalidacheckout.setEditable(false);
        fsalidacheckout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fsalidacheckoutActionPerformed(evt);
            }
        });

        tabla_checkout.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrool.setViewportView(tabla_checkout);

        bt_calcular_checkout.setBackground(new java.awt.Color(255, 0, 0));
        bt_calcular_checkout.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bt_calcular_checkout.setForeground(new java.awt.Color(255, 255, 255));
        bt_calcular_checkout.setText("Calcular Total");
        bt_calcular_checkout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_calcular_checkoutActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(255, 0, 0));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton5.setText("Check out");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrool))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(221, 221, 221)
                                .addComponent(jButton3))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                        .addComponent(jLabel43)
                                        .addGap(18, 18, 18)
                                        .addComponent(documentoCheckou))
                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                        .addComponent(jLabel42)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cliendecheckout, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(habitacion_checkout))
                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel45)
                                            .addComponent(jLabel46))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(fengtrada_checkout)
                                            .addComponent(fsalidacheckout)))))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(bt_calcular_checkout)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(calcular_checkout, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 66, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jButton3)
                .addGap(26, 26, 26)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(cliendecheckout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(documentoCheckou, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(habitacion_checkout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(fengtrada_checkout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(fsalidacheckout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrool, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_calcular_checkout)
                    .addComponent(calcular_checkout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5))
                .addContainerGap(71, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jTabbedPane8.addTab("checkout", jPanel14);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addComponent(jTabbedPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jTabbedPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 669, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/recepcion.png"))); // NOI18N
        jLabel21.setText("jLabel21");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel21))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 689, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
     
    
    private void estado_reservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estado_reservaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_estado_reservaActionPerformed

    private void estado_cActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estado_cActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_estado_cActionPerformed

    private void documento_cliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_documento_cliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_documento_cliActionPerformed

    private void fecha_conActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fecha_conActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fecha_conActionPerformed

    private void bt_eliminar_hActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_eliminar_hActionPerformed
        controlador.aliminarGabitacion(jtable_habitac);
        refrescarTablaHabitacion();
    }//GEN-LAST:event_bt_eliminar_hActionPerformed

    private void numero_cliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numero_cliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numero_cliActionPerformed

    private void combo_producto_conActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_producto_conActionPerformed
        String producto = combo_producto_con.getSelectedItem().toString();

       double precio = obtenerPrecioProducto(producto);

      precio_con.setText(String.valueOf(precio));
    }//GEN-LAST:event_combo_producto_conActionPerformed

    private void bt_modificar_conActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_modificar_conActionPerformed
      if (consumoSeleccionado == null) {
    JOptionPane.showMessageDialog(this, "Primero busque un consumo");
    return;
}

try {
    int cantidad = Integer.parseInt(cantidad_con.getText());
    double precio = Double.parseDouble(precio_con.getText());

    if (cantidad <= 0 || precio <= 0) {
        JOptionPane.showMessageDialog(this, "Cantidad y precio deben ser mayores a 0");
        return;
    }

    String producto = combo_producto_con.getSelectedItem().toString();

    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate fecha = LocalDate.parse(fecha_con.getText(), formato);

    // 🔥 NUEVO: obtener reserva
    Reserva reserva = obtenerReservaPorId();

    if (reserva == null) {
        JOptionPane.showMessageDialog(this, "Reserva no válida");
        return;
    }

    // 💥 MODIFICAR
    consumoSeleccionado.setCantidad(cantidad);
    consumoSeleccionado.setPrecio(precio);
    consumoSeleccionado.setProducto(producto);
    consumoSeleccionado.setFecha(fecha);

    // 🔥 CAMBIO CLAVE
    consumoSeleccionado.setReserva(reserva);

    // 🔥 total
    consumoSeleccionado.setTotal(precio * cantidad);

    JOptionPane.showMessageDialog(this, "Consumo modificado 😎");

    refrescarTablaConsumo();

} catch (Exception e) {
    JOptionPane.showMessageDialog(this, "Error al modificar");
}
    }//GEN-LAST:event_bt_modificar_conActionPerformed

    private void fecha_salida2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fecha_salida2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fecha_salida2ActionPerformed

    private void bt_aagregar_hActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_aagregar_hActionPerformed
      
            String numero = id_habitacion_h.getText();
            String capacidad = capaci_h.getText();
            String piso =piso_h.getText();
            String estado = combo_estado_h.getSelectedItem().toString();
           TipoHabitacion tipo = obtenerTipo(combo_habi.getSelectedItem().toString());
            

    ///vacios 
         if (numero.isEmpty() || capacidad.isEmpty() || piso.isEmpty() || estado.isEmpty()) {
        JOptionPane.showMessageDialog(this, "NO SE PUEDEN REGISTRAR CAMPOS VACIOS ");
        return;
         }
         if (tipo == null) {
        JOptionPane.showMessageDialog(this, "Selecciona un tipo de habitación");
        return;
    }
         if (estado.equalsIgnoreCase("Seleccionar...")) {
             JOptionPane.showMessageDialog(this, "Selecciona un tipo de estado");
             return;
        }
         int numeroInt, capacidadInt, pisoInt;
         try {
             numeroInt = Integer.parseInt(numero);
            capacidadInt = Integer.parseInt(capacidad);
             pisoInt= Integer.parseInt(piso);
        } catch (Exception e) {
              JOptionPane.showMessageDialog(this, "Número, capacidad y piso deben ser números");
               return;
        }
         
         controlador.agregarHabitacion(numeroInt, tipo, capacidadInt, estado, pisoInt);
         refrescarTablaHabitacion();
         cargarHabitacionesEnCombo();
    }//GEN-LAST:event_bt_aagregar_hActionPerformed

    private void combo_habiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_habiActionPerformed
       if (combo_habi.getSelectedItem() == null) {
        return; 
    }
     
    String tipoSeleccionado = combo_habi.getSelectedItem().toString();
    
    if (tipoSeleccionado.equals("Seleccione...")) {
        precio_h.setText(""); // vacío
        return;
    }
    int precio = 0;

    switch (tipoSeleccionado) {
        case "Suite": precio = 200000; break;
        case "Doble": precio = 120000; break;
        case "Sencilla": precio = 80000; break;
    }

    precio_h.setText(String.valueOf(precio));
    }//GEN-LAST:event_combo_habiActionPerformed

    private void bt_buscar_hActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_buscar_hActionPerformed
        String numeroB = id_habitacion_h.getText().trim();
        if (numeroB.isEmpty()) {
              JOptionPane.showMessageDialog(this, "Ingrese el numero de la habitacion para buscar");
        return;
        }
        try {
            int numero = Integer.parseInt(numeroB);
            Habitacion h = controlador.buscarHabitacion(numero);
            if (h!= null) {
                habitacionSeleccionada=h;
                capaci_h.setText(String.valueOf(h.getCapacidad()));
                piso_h.setText(String.valueOf(h.getPiso()));
                combo_estado_h.setSelectedItem(h.getEstado());
                combo_habi.setSelectedItem(h.getTipo().getNombre());
                precio_h.setText(String.valueOf(h.getTipo().getPrecioPorNoche()));
                
                JOptionPane.showMessageDialog(this, "Habitación encontrada 😎");
            }else{
                 JOptionPane.showMessageDialog(this, "Habitacion no encontrada 😭");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Número inválido");
        }
    }//GEN-LAST:event_bt_buscar_hActionPerformed

    private void bt_modificar_hActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_modificar_hActionPerformed
       if (habitacionSeleccionada == null) {
        JOptionPane.showMessageDialog(this, "Primero busque una habitación");
        return;
}        
       habitacionSeleccionada.setCapacidad(Integer.parseInt(capaci_h.getText()));
        habitacionSeleccionada.setPiso(Integer.parseInt(piso_h.getText()));
        habitacionSeleccionada.setEstado(combo_estado_h.getSelectedItem().toString());

        TipoHabitacion tipo = obtenerTipo(combo_habi.getSelectedItem().toString());
        habitacionSeleccionada.setTipo(tipo);

        JOptionPane.showMessageDialog(this, "Habitación modificada 😎");

        refrescarTablaHabitacion();   
    }//GEN-LAST:event_bt_modificar_hActionPerformed

    private void boton_agreegar_cliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_agreegar_cliActionPerformed
        String idc=id_cliente_cli.getText();
        String nombrec= nombre_cli.getText();
        String documentoc=documento_cli.getText();
        String tipoDoc = tipo_doc_cli.getSelectedItem().toString();
        String estadoc = estado_c.getSelectedItem().toString();
        String numero = numero_cli.getText();
        String correoc = correo_cli.getText();
        
        if (idc.isEmpty() || nombrec.isEmpty() || documentoc.isEmpty() || tipoDoc.isEmpty() || estadoc.isEmpty() || numero.isEmpty() || correoc.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se pueden registrar Campos vacios 👎👎👎");
            return;
        }
        if (!controlador.validarSoloLetras(nombrec)) {
            JOptionPane.showMessageDialog(null, "Nombre debe de contener unicamente letras");
            return;
        }
        if (!controlador.validarSoloNumeros(numero)) {
            JOptionPane.showMessageDialog(null, "El número solo debe contener dígitos");
            return;
        }
        if (!controlador.validarSoloNumeros(documentoc)) {
            JOptionPane.showMessageDialog(null, "El documento debe contener digitos");
            return;
        }
        if (!controlador.validarCorreo(correoc)) {
            JOptionPane.showMessageDialog(null, "El correo debe de contener @gamil.com");
            return;
        }
        
        if (tipoDoc.equalsIgnoreCase("Seleccionar...")) {
            JOptionPane.showMessageDialog(null, "Seleccione un tipo de documento");
            return;
        }
        
        if (estadoc.equalsIgnoreCase("Seleccionar...")) {
            JOptionPane.showMessageDialog(null, "Seleccione un estado para el Cliente");
            return;
        }
      
        int id;
        try {
            id = Integer.parseInt(idc);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID debe de ser numerico");
            return;
        }
        
        if (!controlador.validarDatos(documentoc, numero)) {
    return; // 
}
        controlador.agregarCliente(id, nombrec, documentoc, tipoDoc, estadoc, numero, correoc);
        refrescarTablaCientes();
         
    }//GEN-LAST:event_boton_agreegar_cliActionPerformed

    private void Eliminar_clieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Eliminar_clieActionPerformed
        controlador.eliminarCliente(jtable_cliente);
        refrescarTablaCientes();
    }//GEN-LAST:event_Eliminar_clieActionPerformed

    private void bt_buscar_cliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_buscar_cliActionPerformed
        String idB = id_cliente_cli.getText().trim();

    if (idB.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Ingrese el ID del cliente para buscar");
        return;
    }

    try {
        int id = Integer.parseInt(idB);

        Cliente c = controlador.buscarCliente(id);

        if (c != null) {
            clienteSeleccionado = c;

            nombre_cli.setText(c.getNombre());
            documento_cli.setText(c.getDocumento());
            tipo_doc_cli.setSelectedItem(c.getTipoDocumento());
            estado_c.setSelectedItem(c.getEstado());
            numero_cli.setText(c.getNumero());
            correo_cli.setText(c.getCorreo());

            JOptionPane.showMessageDialog(this, "Cliente encontrado 😎");
        } else {
            JOptionPane.showMessageDialog(this, "Cliente no encontrado 😭");
        }

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "ID inválido");
    }
    }//GEN-LAST:event_bt_buscar_cliActionPerformed

    private void bt_modificar_cliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_modificar_cliActionPerformed
        if (clienteSeleccionado == null) {
    JOptionPane.showMessageDialog(this, "Primero busque un cliente");
    return;
}

// Actualizar datos
clienteSeleccionado.setNombre(nombre_cli.getText());
clienteSeleccionado.setDocumento(documento_cli.getText());
clienteSeleccionado.setTipoDocumento(tipo_doc_cli.getSelectedItem().toString());
clienteSeleccionado.setEstado(estado_c.getSelectedItem().toString());
clienteSeleccionado.setNumero(numero_cli.getText());
clienteSeleccionado.setCorreo(correo_cli.getText());

JOptionPane.showMessageDialog(this, "Cliente modificado 😎");

refrescarTablaCientes();
    }//GEN-LAST:event_bt_modificar_cliActionPerformed

    private void Limpiar_CActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Limpiar_CActionPerformed
      id_cliente_cli.setText("");
      nombre_cli.setText("");
      numero_cli.setText("");
      correo_cli.setText("");
      documento_cli.setText("");
      tipo_doc_cli.setSelectedIndex(0);
      estado_c.setSelectedIndex(0);
    }//GEN-LAST:event_Limpiar_CActionPerformed

    private void limpiar_hActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiar_hActionPerformed
     id_habitacion_h.setText("");
     capaci_h.setText("");
     piso_h.setText("");
     combo_estado_h.setSelectedIndex(0);
     combo_habi.setSelectedIndex(0);
    }//GEN-LAST:event_limpiar_hActionPerformed

    private void bt_agregar_conActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_agregar_conActionPerformed
       Reserva reserva = obtenerReservaPorId();
        String cantidadTxt = cantidad_con.getText();
        String precioTx = precio_con.getText();
        String producto = combo_producto_con.getSelectedItem().toString();
        String fechaTx = fecha_con.getText();
        
        if (producto.equals("Seleccione...") || cantidadTxt.isEmpty() || precioTx.isEmpty() || fechaTx.isEmpty()) {
    JOptionPane.showMessageDialog(this, "Complete todos los campos");
    return;
}
  
  if (reserva == null) {
    JOptionPane.showMessageDialog(this, "Reserva no existe");
    return;
}
    int cantidad, id;
    double precio;
    LocalDate fecha;
    try {
       
        cantidad = Integer.parseInt(cantidadTxt);
        precio = Double.parseDouble(precioTx);
        if (cantidad <= 0 || precio<=0) {
            JOptionPane.showMessageDialog(this, "Cantidad y precio deben ser mayor a 0");
            return;
        }
         DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      fecha = LocalDate.parse(fechaTx, formato);
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error en los datos");
        return;
    }
    
    controlador.agregarConsumo( reserva, cantidad, producto, precio, fecha);
    refrescarTablaConsumo();
    }//GEN-LAST:event_bt_agregar_conActionPerformed

    private void bt_eliminar_conActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_eliminar_conActionPerformed
        controlador.eliminarConsumo(jtable_consumo);
        refrescarTablaConsumo();
    }//GEN-LAST:event_bt_eliminar_conActionPerformed

    private void bt_buscar_conActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_buscar_conActionPerformed
 String input = JOptionPane.showInputDialog(this, "Ingrese el ID de la Reserva:");

if (input == null || input.trim().isEmpty()) {
    return;
}

try {
    int idBuscado = Integer.parseInt(input.trim());
    boolean encontrado = false;

    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    for (Consumo con : datos.listaConsumos) {

      
        if (con.getReserva().getId() == idBuscado) {

            consumoSeleccionado = con;

            cantidad_con.setText(String.valueOf(con.getCantidad()));
            precio_con.setText(String.valueOf(con.getPrecio()));
            fecha_con.setText(con.getFecha().format(formato));

            combo_producto_con.setSelectedItem(con.getProducto());

         
            reserva_con.setText(String.valueOf(con.getReserva().getId()));

            encontrado = true;
            break;
        }
    }

    if (!encontrado) {
        JOptionPane.showMessageDialog(this, "No hay consumos para esta reserva");
    }

} catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(this, "Ingrese un ID válido");
}
   

    }//GEN-LAST:event_bt_buscar_conActionPerformed

    private void bt_buscar_cliente_reActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_buscar_cliente_reActionPerformed
     String idText = JOptionPane.showInputDialog(this, "Ingrese el ID del cliente:");

if (idText == null || idText.trim().isEmpty()) {
    JOptionPane.showMessageDialog(this, "Debe ingresar un ID");
    return;
}

try {
    int id = Integer.parseInt(idText);

    Cliente c = controlador.buscarCliente(id);

    if (c != null) {
        clienteSeleccionado = c;

        id_cliente_re.setText(String.valueOf(c.getId()));
        nombre_cliente2_re.setText(c.getNombre());

        JOptionPane.showMessageDialog(this, "Cliente encontrado 😎");
    } else {
        JOptionPane.showMessageDialog(this, "Cliente no existe 😭");
    }

} catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(this, "ID inválido");
}       
    }//GEN-LAST:event_bt_buscar_cliente_reActionPerformed

    private void boton_agregar_rActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_agregar_rActionPerformed
                                                        

    String idr = id_reserva.getText();
    String fechaE = fecha_entrada2.getText();
    String fechaS = fecha_salida2.getText();
    String estado = estado_reserva.getSelectedItem().toString();

    // 🔥 VALIDAR CLIENTE (OBJETO)
    if (clienteSeleccionado == null) {
        JOptionPane.showMessageDialog(this, "Primero busque un cliente");
        return;
    }
    
     // 🔥 OBTENER HABITACIÓN DESDE EL COMBO
  Habitacion habitacionSeleccionada = obtenerHabitacionSeleccionada();

if (habitacionSeleccionada == null) {
    JOptionPane.showMessageDialog(this, "Seleccione una habitación");
    return;
}

// 🔥 ESTADO REAL DE LA HABITACIÓN
String estadoHabitacion = habitacionSeleccionada.getEstado();

// 🔴 VALIDACIONES CORRECTAS
if (estadoHabitacion.equalsIgnoreCase("Ocupada")) {
    JOptionPane.showMessageDialog(this, "La habitación está ocupada ❌");
    return;
}

if (estadoHabitacion.equalsIgnoreCase("Inactivo")) {
    JOptionPane.showMessageDialog(this, "La habitación está inactiva ❌");
    return;
}

    // 🔥 VALIDAR HABITACIÓN (OBJETO)
    if (habitacionSeleccionada == null) {
        JOptionPane.showMessageDialog(this, "Seleccione una habitación");
        return;
    }
    // 🔥 VALIDAR SI ESTÁ OCUPADA


    // 🔥 CAMPOS VACÍOS
    if (idr.isEmpty() || fechaE.isEmpty() || fechaS.isEmpty()) {
        JOptionPane.showMessageDialog(null, "No se permiten campos vacíos 👎");
        return;
    }

    // 🔥 VALIDAR COMBO ESTADO
    if (estado.equalsIgnoreCase("Seleccionar...")) {
        JOptionPane.showMessageDialog(null, "Seleccione un estado");
        return;
    }

    // 🔥 VALIDAR ID
    int idReserva;
    try {
        idReserva = Integer.parseInt(idr);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "ID Reserva debe ser numérico");
        return;
    }

    // 🔥 CONVERTIR FECHAS
   DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

LocalDate entrada, salida;

try {
    entrada = LocalDate.parse(fechaE.trim(), formato);
    salida = LocalDate.parse(fechaS.trim(), formato);
} catch (Exception e) {
    JOptionPane.showMessageDialog(null, "Formato inválido (dd/MM/yyyy)");
    return;
}

    // 🔥 VALIDAR FECHAS
    if (salida.isBefore(entrada)) {
        JOptionPane.showMessageDialog(null, "La fecha de salida no puede ser menor a la de entrada");
        return;
    }

    // 🔥 AGREGAR (OBJETOS 🔥🔥🔥)
    controlador.agregarReserva(idReserva,clienteSeleccionado,
        habitacionSeleccionada,
        entrada,
        salida,
        estado
    );

    JOptionPane.showMessageDialog(this, "Reserva agregada 😎");

    refrescarTablaReserva();

    }//GEN-LAST:event_boton_agregar_rActionPerformed

    private void bt_eliminar_rActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_eliminar_rActionPerformed
        controlador.eliminarReserva(jtable_reserva);
        refrescarTablaReserva();
    }//GEN-LAST:event_bt_eliminar_rActionPerformed

    private void bt_modificar_rActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_modificar_rActionPerformed
     if (reservaSeleccionada == null) {
    JOptionPane.showMessageDialog(this, "Primero busque una reserva");
    return;
}

try {
    // Actualizar fechas (ajusta el parseo según cómo las manejes)
    reservaSeleccionada.setFechaEntrada(LocalDate.parse(fecha_entrada2.getText()));
    reservaSeleccionada.setFechaSalida(LocalDate.parse(fecha_salida2.getText()));

    // Estado
    reservaSeleccionada.setEstado(estado_reserva.getSelectedItem().toString());

    // Cliente (solo si estás usando el ID para reasignar)
    int idCliente = Integer.parseInt(id_cliente_re.getText());
    Cliente cliente = controlador.buscarCliente(idCliente);
    if (cliente != null) {
        reservaSeleccionada.setCliente(cliente);
    }

    // Habitacion (según el texto del combo)
    String seleccionado = combo_reserva.getSelectedItem().toString();
    Habitacion h = controlador.buscarHabitacionPorTexto(seleccionado);
    if (h != null) {
        reservaSeleccionada.setHabitacion(h);
    }

    JOptionPane.showMessageDialog(this, "Reserva modificada 😎");

    refrescarTablaReserva();

} catch (Exception e) {
    JOptionPane.showMessageDialog(this, "Error al modificar reserva: " + e.getMessage());
}
    }//GEN-LAST:event_bt_modificar_rActionPerformed

    private void bt_buscar_rActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_buscar_rActionPerformed
           String idB = id_reserva.getText().trim();

if (idB.isEmpty()) {
    JOptionPane.showMessageDialog(this, "Ingrese el ID de la reserva para buscar");
    return;
}

try {
    int id = Integer.parseInt(idB);

    Reserva r = controlador.buscarReserva(id);

    if (r != null) {
        reservaSeleccionada = r;

        // 👇 FECHAS (BIEN USADAS)
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        fecha_entrada2.setText(r.getFechaEntrada().format(formatter));
        fecha_salida2.setText(r.getFechaSalida().format(formatter));

        estado_reserva.setSelectedItem(r.getEstado());

        // 👇 OBJETOS
        id_cliente_re.setText(String.valueOf(r.getCliente().getId()));
        nombre_cliente2_re.setText(String.valueOf(r.getCliente().getNombre()));
      String texto = r.getHabitacion().getNumero() + " - "  + r.getHabitacion().getTipo().getNombre() + " - piso" + r.getHabitacion().getPiso();

      combo_reserva.setSelectedItem(texto);

        JOptionPane.showMessageDialog(this, "Reserva encontrada 😎");
    } else {
        JOptionPane.showMessageDialog(this, "Reserva no encontrada 😭");
    }

} catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(this, "ID inválido");
}
    }//GEN-LAST:event_bt_buscar_rActionPerformed

    private void check_inActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_inActionPerformed
   if (reservaSeleccionada == null) {
    JOptionPane.showMessageDialog(this, "Primero busque una reserva");
    return;
}
if (reservaSeleccionada.getEstado().equalsIgnoreCase("Finalizada")) {
    JOptionPane.showMessageDialog(this, "No se puede hacer check-in. La reserva ya está finalizada.");
    return;
}

if (reservaSeleccionada.getEstado().equalsIgnoreCase("Check-in")) {
    JOptionPane.showMessageDialog(this, "Esta reserva ya tiene check-in realizado.");
    return;
}
Habitacion h = reservaSeleccionada.getHabitacion();

// Mostrar
habitacioncheckout.setText(h.toString());

// Cambiar estados
h.setEstado("Ocupada");
reservaSeleccionada.setEstado("Check-in");

// Refrescar tablas
refrescarTablaHabitacion();
refrescarTablaReserva();

JOptionPane.showMessageDialog(this, "Check-in realizado 😎");

id_res_chek.setText("");
nombre_reserva_chekin.setText("");
fecha_entrada_resChek.setText("");
fecha_salidaCheevk.setText("");
dni_checkin.setText("");
habitacioncheckout.setText("");
    }//GEN-LAST:event_check_inActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
          String input = JOptionPane.showInputDialog(this, "Ingrese el ID de la reserva:");

    if (input == null || input.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Debe ingresar un ID");
        return;
    }

    try {
        int idBuscado = Integer.parseInt(input.trim());
        boolean encontrado = false;

        for (Reserva r : datos.listaReservas) {

            if (r.getId() == idBuscado) {

                reservaSeleccionada = r;

                // 🔥 LLENAR CAMPOS
                id_res_chek.setText(String.valueOf(r.getId()));
                fecha_entrada_resChek.setText(r.getFechaEntrada().toString()); // LocalDate
                fecha_salidaCheevk.setText(r.getFechaSalida().toString());

                // Cliente
                nombre_reserva_chekin.setText(r.getCliente().getNombre());
                dni_checkin.setText(r.getCliente().getDocumento());

                // Habitación
           habitacioncheckout.setText(
    r.getHabitacion().getNumero() + " - " +
    r.getHabitacion().getTipo().getNombre() + " - piso " +
    r.getHabitacion().getPiso()
);

   habitacioncheckout.setEditable(false);

                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            JOptionPane.showMessageDialog(this, "Reserva no encontrada");
        }

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "ID inválido");
    }
    refrescarTablaHabitacion();
    refrescarTablaReserva();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void fsalidacheckoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fsalidacheckoutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fsalidacheckoutActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       String input = JOptionPane.showInputDialog(this, "Ingrese el ID de la reserva:");

    if (input == null || input.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Debe ingresar un ID");
        return;
    }

    try {
        int id = Integer.parseInt(input);

        Reserva reservaEncontrada = null;

        for (Reserva r : DatosRecepcionista.listaReservas) {
            if (r.getId() == id) {
                reservaEncontrada = r;
                break;
            }
        }

        if (reservaEncontrada == null) {
            JOptionPane.showMessageDialog(this, "Reserva no encontrada");
            return;
        }

        
        cliendecheckout.setText(reservaEncontrada.getCliente().getNombre());
        documentoCheckou.setText(reservaEncontrada.getCliente().getDocumento());
        habitacion_checkout.setText(String.valueOf(reservaEncontrada.getHabitacion().getNumero()));
        fengtrada_checkout.setText(reservaEncontrada.getFechaEntrada().toString());
        fsalidacheckout.setText(reservaEncontrada.getFechaSalida().toString());

        
         //referencia
        this.reservaActual = reservaEncontrada;
       
        refrescarTablaCheckOut(reservaEncontrada);

        JOptionPane.showMessageDialog(this, "Reserva cargada 😎");

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "ID inválido");
    }        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void bt_calcular_checkoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_calcular_checkoutActionPerformed
      if (reservaActual == null) {
        JOptionPane.showMessageDialog(this, "Primero cargue una reserva");
        return;
    }

    double totalConsumos = 0;

   
    for (Consumo c : datos.listaConsumos) {
        if (c.getReserva().getId() == reservaActual.getId()) {
            totalConsumos += c.getTotal();
        }
    }

   
    long noches = ChronoUnit.DAYS.between(
        reservaActual.getFechaEntrada(),
        reservaActual.getFechaSalida()
    );

    
    double precioNoche = reservaActual.getHabitacion().getTipo().getPrecioPorNoche();

    double totalHabitacion = noches * precioNoche;

    
    double totalFinal = totalConsumos + totalHabitacion;

   
    calcular_checkout.setText("Total: $" + totalFinal);       
    }//GEN-LAST:event_bt_calcular_checkoutActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
                                             

    if (reservaActual == null) {
        JOptionPane.showMessageDialog(this, "Primero cargue una reserva");
        return;
    }
    if (reservaActual.getEstado().equalsIgnoreCase("Finalizada")) {
    JOptionPane.showMessageDialog(this, "Esta reserva ya está finalizada. No se puede hacer check-out otra vez.");
    return;
}

if (!reservaActual.getEstado().equalsIgnoreCase("Check-in")) {
    JOptionPane.showMessageDialog(this, "No se puede hacer check-out sin haber hecho check-in.");
    return;
}

    long noches = ChronoUnit.DAYS.between(
    reservaActual.getFechaEntrada(),
    reservaActual.getFechaSalida()
);
  noches = Math.max(noches, 1);
  
    double totalConsumos = 0;
    String ticket = "";

    ticket += "====== FACTURA ======\n";
    ticket += "Cliente: " + reservaActual.getCliente().getNombre() + "\n";
    ticket += "Documento: " + reservaActual.getCliente().getDocumento() + "\n";
    ticket += "Correo: " + reservaActual.getCliente().getCorreo() + "Numero: "+ reservaActual.getCliente().getNumero() + " \n";
    ticket += "Habitación: " + reservaActual.getHabitacion().getNumero()+" - "+reservaActual.getHabitacion().getTipo().getNombre()+" $: "+reservaActual.getHabitacion().getTipo().getPrecioPorNoche() + "\n";
    ticket += "Dias: "+ noches+"\n\n";
    ticket += "Consumos:\n";

    // 🔥 RECORRER TODOS LOS CONSUMOS
    for (Consumo c : datos.listaConsumos) {
        if (c.getReserva().getId() == reservaActual.getId()) {

            ticket += "- " + c.getProducto()
                    + " | Cant: " + c.getCantidad()
                    + " | $" + c.getTotal() + "\n";

            totalConsumos += c.getTotal();
        }
    }

    
   
double precioNoche = reservaActual.getHabitacion().getTipo().getPrecioPorNoche();
double totalHabitacion = noches * precioNoche;

double totalFinal = totalConsumos + totalHabitacion;

    ticket += "\nHabitación: $" + totalHabitacion;
    ticket += "\nTOTAL: $" + totalFinal;

    // 🔥 MOSTRAR TICKET
    JOptionPane.showMessageDialog(this, ticket);

    // 🔥 CAMBIAR ESTADOS
    reservaActual.setEstado("Finalizada");
    reservaActual.getHabitacion().setEstado("Libre");

    // 🔥 LIMPIAR
    reservaActual = null;
 
    refrescarTablaHabitacion();
    refrescarTablaReserva();
    
    cliendecheckout.setText("");
    documentoCheckou.setText("");
    habitacion_checkout.setText("");
    fengtrada_checkout.setText("");
    fsalidacheckout.setText("");
    refrescarTablaCheckOut(reservaActual);

    }//GEN-LAST:event_jButton5ActionPerformed

    private void fecha_entrada_resChekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fecha_entrada_resChekActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fecha_entrada_resChekActionPerformed

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        jTabbedPane8.setSelectedIndex(1);
    }//GEN-LAST:event_jPanel4MouseClicked

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked
          jTabbedPane8.setSelectedIndex(2);
    }//GEN-LAST:event_jPanel5MouseClicked

    private void jPanel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseClicked
          jTabbedPane8.setSelectedIndex(4);
    }//GEN-LAST:event_jPanel6MouseClicked

    private void jPanel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseClicked
          jTabbedPane8.setSelectedIndex(3);
    }//GEN-LAST:event_jPanel10MouseClicked

    private void jPanel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel11MouseClicked
         jTabbedPane8.setSelectedIndex(5);
    }//GEN-LAST:event_jPanel11MouseClicked

    private void jPanel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel12MouseClicked
          jTabbedPane8.setSelectedIndex(6);
    }//GEN-LAST:event_jPanel12MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Recepcionista().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Eliminar_clie;
    private javax.swing.JButton Limpiar_C;
    private javax.swing.JButton boton_agreegar_cli;
    private javax.swing.JToggleButton boton_agregar_r;
    private javax.swing.JButton bt_aagregar_h;
    private javax.swing.JButton bt_agregar_con;
    private javax.swing.JButton bt_buscar_cli;
    private javax.swing.JButton bt_buscar_cliente_re;
    private javax.swing.JButton bt_buscar_con;
    private javax.swing.JButton bt_buscar_h;
    private javax.swing.JToggleButton bt_buscar_r;
    private javax.swing.JButton bt_calcular_checkout;
    private javax.swing.JButton bt_eliminar_con;
    private javax.swing.JButton bt_eliminar_h;
    private javax.swing.JToggleButton bt_eliminar_r;
    private javax.swing.JButton bt_modificar_cli;
    private javax.swing.JButton bt_modificar_con;
    private javax.swing.JButton bt_modificar_h;
    private javax.swing.JToggleButton bt_modificar_r;
    private javax.swing.JTextField calcular_checkout;
    private javax.swing.JTextField cantidad_con;
    private javax.swing.JTextField capaci_h;
    private javax.swing.JButton check_in;
    private javax.swing.JTextField cliendecheckout;
    private javax.swing.JComboBox<String> combo_estado_h;
    private javax.swing.JComboBox<String> combo_habi;
    private javax.swing.JComboBox<String> combo_producto_con;
    private javax.swing.JComboBox<String> combo_reserva;
    private javax.swing.JTextField correo_cli;
    private javax.swing.JTextField dni_checkin;
    private javax.swing.JTextField documentoCheckou;
    private javax.swing.JTextField documento_cli;
    private javax.swing.JComboBox<String> estado_c;
    private javax.swing.JComboBox<String> estado_reserva;
    private javax.swing.JTextField fecha_con;
    private javax.swing.JTextField fecha_entrada2;
    private javax.swing.JTextField fecha_entrada_resChek;
    private javax.swing.JTextField fecha_salida2;
    private javax.swing.JTextField fecha_salidaCheevk;
    private javax.swing.JTextField fengtrada_checkout;
    private javax.swing.JTextField fsalidacheckout;
    private javax.swing.JTextField habitacion_checkout;
    private javax.swing.JTextField habitacioncheckout;
    private javax.swing.JTextField id_cliente_cli;
    private javax.swing.JTextField id_cliente_re;
    private javax.swing.JTextField id_habitacion_h;
    private javax.swing.JTextField id_res_chek;
    private javax.swing.JTextField id_reserva;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane8;
    private javax.swing.JTable jtable_cliente;
    private javax.swing.JTable jtable_consumo;
    private javax.swing.JTable jtable_habitac;
    private javax.swing.JTable jtable_reserva;
    private javax.swing.JButton limpiar_h;
    private javax.swing.JTextField nombre_cli;
    private javax.swing.JTextField nombre_cliente2_re;
    private javax.swing.JTextField nombre_reserva_chekin;
    private javax.swing.JTextField numero_cli;
    private javax.swing.JTextField piso_h;
    private javax.swing.JTextField precio_con;
    private javax.swing.JTextField precio_h;
    private javax.swing.JTextField reserva_con;
    private javax.swing.JScrollPane scrool;
    private javax.swing.JTable tabla_checkout;
    private javax.swing.JComboBox<String> tipo_doc_cli;
    // End of variables declaration//GEN-END:variables
}
