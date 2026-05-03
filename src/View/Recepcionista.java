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
import Model.Reserva;
import Model.TipoHabitacion;
import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

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
      private DatosRecepcionista datos = new DatosRecepcionista();
      private RecepcionistaC controlador = new RecepcionistaC(datos);
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
        estilizarTabla(jtable_cliente_reserva);
        
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
        jtable_cliente_reserva.setModel(modeloCliente);
        jtable_cliente.setAutoResizeMode(jtable_cliente.AUTO_RESIZE_OFF);
        jtable_cliente_reserva.setAutoResizeMode(jtable_cliente.AUTO_RESIZE_OFF);

         jtable_cliente.getColumnModel().getColumn(0).setPreferredWidth(50);  // ID
         jtable_cliente.getColumnModel().getColumn(1).setPreferredWidth(150); // Nombre
         jtable_cliente.getColumnModel().getColumn(2).setPreferredWidth(100); // Número
         jtable_cliente.getColumnModel().getColumn(3).setPreferredWidth(180); // Correo
         jtable_cliente.getColumnModel().getColumn(4).setPreferredWidth(120); // Documento
         jtable_cliente.getColumnModel().getColumn(5).setPreferredWidth(100); // Tipo
         jtable_cliente.getColumnModel().getColumn(6).setPreferredWidth(80);  // Estado
         
         jtable_cliente_reserva.getColumnModel().getColumn(0).setPreferredWidth(50);  // ID
         jtable_cliente_reserva.getColumnModel().getColumn(1).setPreferredWidth(150); // Nombre
         jtable_cliente_reserva.getColumnModel().getColumn(2).setPreferredWidth(100); // Número
         jtable_cliente_reserva.getColumnModel().getColumn(3).setPreferredWidth(180); // Correo
         jtable_cliente_reserva.getColumnModel().getColumn(4).setPreferredWidth(120); // Documento
         jtable_cliente_reserva.getColumnModel().getColumn(5).setPreferredWidth(100); // Tipo
         jtable_cliente_reserva.getColumnModel().getColumn(6).setPreferredWidth(80);  // Estado
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
            fila[0] = c.getCliente().getNombre();
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
        jPanel7 = new javax.swing.JPanel();
        jTabbedPane8 = new javax.swing.JTabbedPane();
        jPanel17 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        nombre_cliente2_re = new javax.swing.JTextField();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jtable_cliente_reserva = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        id_reserva = new javax.swing.JTextField();
        id_cliente_re = new javax.swing.JTextField();
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
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtable_cliente = new javax.swing.JTable();
        boton_agreegar_cli = new javax.swing.JButton();
        Eliminar_clie = new javax.swing.JButton();
        bt_buscar_cli = new javax.swing.JButton();
        bt_modificar_cli = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        nombre_cli = new javax.swing.JTextField();
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
        combo_cliente = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 0, 51));
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

        jPanel4.setBackground(new java.awt.Color(204, 0, 102));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

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

        jPanel5.setBackground(new java.awt.Color(204, 0, 102));
        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

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
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(204, 0, 102));
        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setFont(new java.awt.Font("Segoe Print", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Consumo");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(53, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(31, 31, 31))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(204, 0, 102));
        jPanel10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel10.setPreferredSize(new java.awt.Dimension(180, 59));

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(219, 219, 219)
                .addComponent(jLabel6)
                .addContainerGap(75, Short.MAX_VALUE))
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

        jComboBox5.setBackground(new java.awt.Color(51, 0, 0));
        jComboBox5.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Habitacion");

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Estado");

        jComboBox6.setBackground(new java.awt.Color(51, 0, 0));
        jComboBox6.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox6ActionPerformed(evt);
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

        bt_eliminar_r.setBackground(new java.awt.Color(0, 0, 0));
        bt_eliminar_r.setFont(new java.awt.Font("Segoe Script", 0, 14)); // NOI18N
        bt_eliminar_r.setForeground(new java.awt.Color(255, 255, 255));
        bt_eliminar_r.setText("Eliminar");

        bt_modificar_r.setBackground(new java.awt.Color(0, 0, 0));
        bt_modificar_r.setFont(new java.awt.Font("Segoe Script", 0, 14)); // NOI18N
        bt_modificar_r.setForeground(new java.awt.Color(255, 255, 255));
        bt_modificar_r.setText("Modificar");

        bt_buscar_r.setBackground(new java.awt.Color(0, 0, 0));
        bt_buscar_r.setFont(new java.awt.Font("Segoe Script", 0, 14)); // NOI18N
        bt_buscar_r.setForeground(new java.awt.Color(255, 255, 255));
        bt_buscar_r.setText("Buscar");

        jtable_reserva.setForeground(new java.awt.Color(255, 255, 255));
        jtable_reserva.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jtable_reserva);

        jtable_cliente_reserva.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jtable_cliente_reserva);

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Clientes: ");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("ID Reserva");

        id_cliente_re.setEditable(false);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel30)
                            .addComponent(boton_agregar_r, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(28, 28, 28)))
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fecha_entrada2)
                                .addGap(153, 153, 153)
                                .addComponent(jLabel31)
                                .addGap(18, 18, 18)
                                .addComponent(fecha_salida2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(108, 108, 108))
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel18Layout.createSequentialGroup()
                                        .addGap(55, 55, 55)
                                        .addComponent(bt_eliminar_r)
                                        .addGap(59, 59, 59)
                                        .addComponent(bt_modificar_r)
                                        .addGap(57, 57, 57))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(id_reserva, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(34, 34, 34)
                                        .addComponent(jLabel26)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(id_cliente_re, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(20, 20, 20)
                                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel29)
                                            .addComponent(jLabel27))
                                        .addGap(18, 18, 18)))
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nombre_cliente2_re, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bt_buscar_r)
                                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(34, 34, 34)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(530, 530, 530))))
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 682, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(308, 308, 308)
                        .addComponent(jLabel20))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 682, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27)
                    .addComponent(nombre_cliente2_re, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(id_reserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(id_cliente_re, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(fecha_entrada2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31)
                    .addComponent(fecha_salida2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boton_agregar_r)
                    .addComponent(bt_eliminar_r)
                    .addComponent(bt_modificar_r)
                    .addComponent(bt_buscar_r))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
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
        tipo_doc_cli.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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

        jComboBox1.setBackground(new java.awt.Color(51, 0, 0));
        jComboBox1.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
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

        Eliminar_clie.setBackground(new java.awt.Color(0, 0, 0));
        Eliminar_clie.setFont(new java.awt.Font("Segoe Script", 0, 14)); // NOI18N
        Eliminar_clie.setForeground(new java.awt.Color(255, 255, 255));
        Eliminar_clie.setText("Eliminar");

        bt_buscar_cli.setBackground(new java.awt.Color(0, 0, 0));
        bt_buscar_cli.setFont(new java.awt.Font("Segoe Script", 0, 14)); // NOI18N
        bt_buscar_cli.setForeground(new java.awt.Color(255, 255, 255));
        bt_buscar_cli.setText("Buscar");

        bt_modificar_cli.setBackground(new java.awt.Color(0, 0, 0));
        bt_modificar_cli.setFont(new java.awt.Font("Segoe Script", 0, 14)); // NOI18N
        bt_modificar_cli.setForeground(new java.awt.Color(255, 255, 255));
        bt_modificar_cli.setText("Modificar");

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Nombre Completo");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(26, 26, 26)
                        .addComponent(tipo_doc_cli, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))
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
                    .addComponent(nombre_cli, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                    .addComponent(documento_cli)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bt_modificar_cli))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 659, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(boton_agreegar_cli)
                        .addGap(74, 74, 74)
                        .addComponent(Eliminar_clie)))
                .addContainerGap(25, Short.MAX_VALUE))
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
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(correo_cli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boton_agreegar_cli)
                    .addComponent(Eliminar_clie)
                    .addComponent(bt_buscar_cli)
                    .addComponent(bt_modificar_cli))
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
        combo_estado_h.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar-", "Activo", "Inactivo", "Mantenimiento" }));

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

        bt_modificar_h.setBackground(new java.awt.Color(0, 0, 0));
        bt_modificar_h.setFont(new java.awt.Font("Segoe Script", 0, 14)); // NOI18N
        bt_modificar_h.setForeground(new java.awt.Color(255, 255, 255));
        bt_modificar_h.setText("Modificar");

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
                        .addGap(82, 82, 82))))
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
                    .addComponent(bt_modificar_h))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );

        jTabbedPane8.addTab("Habit", jPanel8);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(60, 63, 65));
        jLabel22.setText("Producto");

        combo_producto_con.setBackground(new java.awt.Color(51, 0, 0));
        combo_producto_con.setForeground(new java.awt.Color(255, 255, 255));
        combo_producto_con.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        combo_producto_con.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_producto_conActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(60, 63, 65));
        jLabel23.setText("Cantidad");

        cantidad_con.setFont(new java.awt.Font("Segoe Script", 0, 14)); // NOI18N

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(60, 63, 65));
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

        bt_eliminar_con.setBackground(new java.awt.Color(0, 0, 0));
        bt_eliminar_con.setFont(new java.awt.Font("Segoe Script", 0, 14)); // NOI18N
        bt_eliminar_con.setForeground(new java.awt.Color(255, 255, 255));
        bt_eliminar_con.setText("Eliminar");

        bt_buscar_con.setBackground(new java.awt.Color(0, 0, 0));
        bt_buscar_con.setFont(new java.awt.Font("Segoe Script", 0, 14)); // NOI18N
        bt_buscar_con.setForeground(new java.awt.Color(255, 255, 255));
        bt_buscar_con.setText("Buscar");

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
        jLabel19.setForeground(new java.awt.Color(60, 63, 65));
        jLabel19.setText("Cliente");

        combo_cliente.setBackground(new java.awt.Color(51, 0, 0));
        combo_cliente.setForeground(new java.awt.Color(255, 255, 255));
        combo_cliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(combo_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(fecha_con, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo_producto_con, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(97, 97, 97))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 32, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 652, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cantidad_con, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel22)
                    .addComponent(combo_producto_con, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(cantidad_con, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32)
                    .addComponent(fecha_con, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_agregar_con)
                    .addComponent(bt_eliminar_con)
                    .addComponent(bt_buscar_con)
                    .addComponent(bt_modificar_con))
                .addGap(42, 42, 42)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(105, Short.MAX_VALUE))
        );

        jTabbedPane8.addTab("Cons", jPanel9);

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
     
    
    private void jComboBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox6ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void documento_cliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_documento_cliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_documento_cliActionPerformed

    private void fecha_conActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fecha_conActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fecha_conActionPerformed

    private void bt_eliminar_hActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_eliminar_hActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_eliminar_hActionPerformed

    private void numero_cliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numero_cliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numero_cliActionPerformed

    private void combo_producto_conActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_producto_conActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_producto_conActionPerformed

    private void bt_modificar_conActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_modificar_conActionPerformed
        // TODO add your handling code here:
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
    private javax.swing.JButton boton_agreegar_cli;
    private javax.swing.JToggleButton boton_agregar_r;
    private javax.swing.JButton bt_aagregar_h;
    private javax.swing.JButton bt_agregar_con;
    private javax.swing.JButton bt_buscar_cli;
    private javax.swing.JButton bt_buscar_con;
    private javax.swing.JButton bt_buscar_h;
    private javax.swing.JToggleButton bt_buscar_r;
    private javax.swing.JButton bt_eliminar_con;
    private javax.swing.JButton bt_eliminar_h;
    private javax.swing.JToggleButton bt_eliminar_r;
    private javax.swing.JButton bt_modificar_cli;
    private javax.swing.JButton bt_modificar_con;
    private javax.swing.JButton bt_modificar_h;
    private javax.swing.JToggleButton bt_modificar_r;
    private javax.swing.JTextField cantidad_con;
    private javax.swing.JTextField capaci_h;
    private javax.swing.JComboBox<String> combo_cliente;
    private javax.swing.JComboBox<String> combo_estado_h;
    private javax.swing.JComboBox<String> combo_habi;
    private javax.swing.JComboBox<String> combo_producto_con;
    private javax.swing.JTextField correo_cli;
    private javax.swing.JTextField documento_cli;
    private javax.swing.JTextField fecha_con;
    private javax.swing.JTextField fecha_entrada2;
    private javax.swing.JTextField fecha_salida2;
    private javax.swing.JTextField id_cliente_cli;
    private javax.swing.JTextField id_cliente_re;
    private javax.swing.JTextField id_habitacion_h;
    private javax.swing.JTextField id_reserva;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
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
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane8;
    private javax.swing.JTable jtable_cliente;
    private javax.swing.JTable jtable_cliente_reserva;
    private javax.swing.JTable jtable_consumo;
    private javax.swing.JTable jtable_habitac;
    private javax.swing.JTable jtable_reserva;
    private javax.swing.JTextField nombre_cli;
    private javax.swing.JTextField nombre_cliente2_re;
    private javax.swing.JTextField numero_cli;
    private javax.swing.JTextField piso_h;
    private javax.swing.JTextField precio_h;
    private javax.swing.JComboBox<String> tipo_doc_cli;
    // End of variables declaration//GEN-END:variables
}
