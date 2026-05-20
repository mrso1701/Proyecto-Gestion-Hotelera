package view;

import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Gerencia extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Gerencia.class.getName());
    
    java.util.ArrayList<model.clienteslog> listaClientes = new java.util.ArrayList<>();
    java.util.ArrayList<model.empleadoslog> listaEmpleados = new java.util.ArrayList<>();
    java.util.ArrayList<model.habitacionlog> listaHabitaciones = new java.util.ArrayList<>();

    public Gerencia() {
        initComponents();
    }
    
    public void actualizarTabla() {

        javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) clientetab.getModel();
        modelo.setRowCount(0); 
        for (model.clienteslog c : listaClientes) {
            Object[] fila = {c.cedula, c.nombre, c.telefono, c.correo, c.checkin, c.checkout};
            modelo.addRow(fila);
        }
    }
    
        private void limpiarCampos() {
        cedula.setText("");
        nombre.setText("");
        telefono.setText("");
        correo.setText("");
        checkin.setText("");
        checkout.setText("");
    }
        public void actualizarTablaEmp() {
        javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) jTable3.getModel();
        modelo.setRowCount(0); 
        for (model.empleadoslog e : listaEmpleados) {
            Object[] fila = {e.cedula, e.nombre, e.cargo, e.salario, e.fecha};
            modelo.addRow(fila);
        }
    }
    
        private void limpiarCamposEmp() {
            empid.setText("");
            empname.setText("");
            cargoemp.setText("");
            salemp.setText("");
            fechaemp.setText("");
        }

        
        private boolean validarFechas(String fechaInStr, String fechaOutStr) {
        // Definimos los formatos más comunes que el usuario podría escribir
        java.time.format.DateTimeFormatter formato1 = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
        java.time.format.DateTimeFormatter formato2 = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd");

        java.time.LocalDate fechaIn = null;
        java.time.LocalDate fechaOut = null;

        // Intentamos parsear la fecha de Check-In
        try {
            fechaIn = java.time.LocalDate.parse(fechaInStr, formato1);
        } catch (Exception e1) {
            try {
                fechaIn = java.time.LocalDate.parse(fechaInStr, formato2);
            } catch (Exception e2) {
                javax.swing.JOptionPane.showMessageDialog(this, "Formato de fecha de Check-In inválido. Use dd/MM/yyyy o yyyy-MM-dd.", "Error de Formato", javax.swing.JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        // Intentamos parsear la fecha de Check-Out
        try {
            fechaOut = java.time.LocalDate.parse(fechaOutStr, formato1);
        } catch (Exception e1) {
            try {
                fechaOut = java.time.LocalDate.parse(fechaOutStr, formato2);
            } catch (Exception e2) {
                javax.swing.JOptionPane.showMessageDialog(this, "Formato de fecha de Check-Out inválido. Use dd/MM/yyyy o yyyy-MM-dd.", "Error de Formato", javax.swing.JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        // Verificación lógica: ¿La fecha de salida es menor que la de entrada?
        if (fechaOut.isBefore(fechaIn)) {
            javax.swing.JOptionPane.showMessageDialog(this, "No puede ingresar una fecha de Check-Out menor a la fecha de Check-In.", "Inconsistencia de Fechas", javax.swing.JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
        
    public void actualizarTablaHab() {
        javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) jTable2.getModel();
        modelo.setRowCount(0); 
        for (model.habitacionlog h : listaHabitaciones) {
            Object[] fila = {h.numero, h.tipo, h.precio, h.estado};
            modelo.addRow(fila);
        }
    }
    
    private void limpiarCamposHab() {
        numhab.setText("");
        preciohab.setText("");
        tipoHab.setSelectedIndex(0);
        estadohab.setSelectedIndex(0); 
        imagenHab.setIcon(null);     
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField7 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        preciohab = new javax.swing.JTextField();
        numhab = new javax.swing.JTextField();
        tipoHab = new javax.swing.JComboBox<>();
        estadohab = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        addhab = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        modhab = new javax.swing.JButton();
        bushab = new javax.swing.JButton();
        elihab = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        imagenHab = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        empname = new javax.swing.JTextField();
        empid = new javax.swing.JTextField();
        cargoemp = new javax.swing.JTextField();
        salemp = new javax.swing.JTextField();
        fechaemp = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        addemp = new javax.swing.JButton();
        modemp = new javax.swing.JButton();
        eliemp = new javax.swing.JButton();
        buscaremp = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        clientetab = new javax.swing.JTable();
        add = new javax.swing.JButton();
        mod = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        search = new javax.swing.JButton();
        cedula = new javax.swing.JTextField();
        telefono = new javax.swing.JTextField();
        nombre = new javax.swing.JTextField();
        checkout = new javax.swing.JTextField();
        checkin = new javax.swing.JTextField();
        correo = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jPanel2.setBackground(new java.awt.Color(102, 0, 0));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Gerencia");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));

        preciohab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preciohabActionPerformed(evt);
            }
        });

        numhab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numhabActionPerformed(evt);
            }
        });

        tipoHab.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elija una opción", "Sencilla", "Double", "Suite" }));
        tipoHab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoHabActionPerformed(evt);
            }
        });

        estadohab.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elija una opción", "Disponible", "Ocupada", "Mantenimiento" }));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Numero de Habitación");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Tipo de habitación");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Precio");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Estado");

        addhab.setBackground(new java.awt.Color(0, 153, 51));
        addhab.setText("Añadir");
        addhab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addhabActionPerformed(evt);
            }
        });

        jTable2.setBackground(new java.awt.Color(204, 204, 204));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Num Habitacion", "Tipo", "Precio", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        modhab.setBackground(new java.awt.Color(0, 153, 51));
        modhab.setText("Modificar");
        modhab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modhabActionPerformed(evt);
            }
        });

        bushab.setBackground(new java.awt.Color(0, 153, 51));
        bushab.setText("Buscar");
        bushab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bushabActionPerformed(evt);
            }
        });

        elihab.setBackground(new java.awt.Color(0, 153, 51));
        elihab.setText("Eliminar");
        elihab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                elihabActionPerformed(evt);
            }
        });

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/servicio-de-habitaciones.png"))); // NOI18N

        imagenHab.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        imagenHab.setPreferredSize(new java.awt.Dimension(250, 150));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tipoHab, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(preciohab, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(estadohab, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(33, 33, 33)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(modhab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bushab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(numhab, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9))
                            .addGap(27, 27, 27)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(addhab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(elihab, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addComponent(imagenHab, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(160, 160, 160)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(numhab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addhab))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(elihab))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(tipoHab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(modhab))
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel10))
                                    .addComponent(bushab))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(preciohab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(estadohab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(imagenHab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Habitaciones", jPanel7);

        jPanel8.setBackground(new java.awt.Color(204, 204, 204));

        jTable3.setBackground(new java.awt.Color(204, 204, 204));
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Cargo", "Salario", "Fecha"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Gestion de empleados");

        empid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empidActionPerformed(evt);
            }
        });

        jLabel3.setText("ID");

        jLabel4.setText("Nombre");

        jLabel5.setText("Cargo");

        jLabel6.setText("Salario");

        jLabel7.setText("Fecha");

        addemp.setBackground(new java.awt.Color(0, 153, 51));
        addemp.setText("Añadir");
        addemp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addempActionPerformed(evt);
            }
        });

        modemp.setBackground(new java.awt.Color(0, 153, 51));
        modemp.setText("Modificar");
        modemp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modempActionPerformed(evt);
            }
        });

        eliemp.setBackground(new java.awt.Color(0, 153, 51));
        eliemp.setText("Eliminar");
        eliemp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliempActionPerformed(evt);
            }
        });

        buscaremp.setBackground(new java.awt.Color(0, 153, 51));
        buscaremp.setText("Buscar");
        buscaremp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarempActionPerformed(evt);
            }
        });

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/servicio.png"))); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(fechaemp, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(empid, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(addemp, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(eliemp, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(modemp)
                                .addGap(18, 18, 18)
                                .addComponent(buscaremp, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(cargoemp, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(salemp, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(empname, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 142, Short.MAX_VALUE)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44))))
            .addComponent(jScrollPane3)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(empid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(addemp)
                                    .addComponent(eliemp)
                                    .addComponent(modemp)
                                    .addComponent(buscaremp))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(empname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cargoemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(salemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fechaemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Empleados", jPanel8);

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));

        clientetab.setBackground(new java.awt.Color(204, 204, 204));
        clientetab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Cédula", "Nombre", "Teléfono", "Correo", "Fecha Check-In", "Fecha Check-Out"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        clientetab.setCellSelectionEnabled(true);
        clientetab.setDoubleBuffered(true);
        clientetab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clientetabMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(clientetab);

        add.setBackground(new java.awt.Color(0, 153, 51));
        add.setText("Añadir");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        mod.setBackground(new java.awt.Color(0, 153, 51));
        mod.setText("Modificar");
        mod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modActionPerformed(evt);
            }
        });

        delete.setBackground(new java.awt.Color(0, 153, 51));
        delete.setText("Eliminar");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        search.setBackground(new java.awt.Color(0, 153, 51));
        search.setText("Buscar");
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });

        nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreActionPerformed(evt);
            }
        });

        checkout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkoutActionPerformed(evt);
            }
        });

        checkin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkinActionPerformed(evt);
            }
        });

        correo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                correoActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Cédula");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("Check-In");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("Nombre");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("Teléfono");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setText("Correo");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setText("Check-Out");

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/nueva-cuenta.png"))); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(121, 121, 121)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)
                                .addComponent(correo, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(43, 43, 43)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(checkin, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel16)))
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(133, 133, 133)
                                    .addComponent(jLabel13))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                    .addComponent(cedula, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(43, 43, 43)
                                    .addComponent(checkout))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(101, 101, 101)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(mod, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(search, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(152, 152, 152)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(308, 308, 308))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 916, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(correo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel13))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel12))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                                .addComponent(jLabel17)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(mod)
                                    .addComponent(add))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(search)
                                    .addComponent(delete))))
                        .addGap(182, 182, 182)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Clientes", jPanel6);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 916, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("tab");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void preciohabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_preciohabActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_preciohabActionPerformed

    private void numhabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numhabActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numhabActionPerformed

    private void modhabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modhabActionPerformed
        // TODO add your handling code here:
        int filaSeleccionada = jTable2.getSelectedRow();
        if (filaSeleccionada != -1) {
            String numero = numhab.getText().trim();
            String tipo = tipoHab.getSelectedItem().toString();
            String precio = preciohab.getText().trim();
            String estado = estadohab.getSelectedItem().toString(); // <-- NUEVO

            if (!numero.matches("\\d+")) {
                javax.swing.JOptionPane.showMessageDialog(this, "Revisar formato del número de habitación.");
                return;
            }
            if (!precio.matches("\\d+(\\.\\d+)?")) {
                javax.swing.JOptionPane.showMessageDialog(this, "Revisar formato del precio.");
                return;
            }
            
            model.habitacionlog habitacion = listaHabitaciones.get(filaSeleccionada);   
            habitacion.numero = numero;
            habitacion.tipo = tipo;
            habitacion.precio = Double.parseDouble(precio);
            habitacion.estado = estado; // <-- NUEVO: Modifica el estado
            
            actualizarTablaHab();
            limpiarCamposHab();
            javax.swing.JOptionPane.showMessageDialog(this, "Datos de la habitación actualizados.");
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Selecciona una habitación de la tabla para modificar.");
        }
    }//GEN-LAST:event_modhabActionPerformed

    private void nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreActionPerformed

    private void checkoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkoutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkoutActionPerformed

    private void checkinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkinActionPerformed

    private void correoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_correoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_correoActionPerformed

    private void clientetabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clientetabMouseClicked
        // TODO add your handling code here:
        
        int fila = clientetab.getSelectedRow();
        if (fila != -1) {
        cedula.setText(clientetab.getValueAt(fila, 0).toString());
        nombre.setText(clientetab.getValueAt(fila, 1).toString());
        telefono.setText(clientetab.getValueAt(fila, 2).toString());
        correo.setText(clientetab.getValueAt(fila, 3).toString());
        checkin.setText(clientetab.getValueAt(fila, 4).toString());
        checkout.setText(clientetab.getValueAt(fila, 5).toString());
}
    }//GEN-LAST:event_clientetabMouseClicked

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        // TODO add your handling code here:
        String ced = cedula.getText().trim();
        String name = nombre.getText().trim();
        String cel = telefono.getText().trim();
        String cor = correo.getText().trim();
        String fIn = checkin.getText().trim();
        String fOut = checkout.getText().trim();
        
        if (ced.isEmpty() || name.isEmpty() || cel.isEmpty() || cor.isEmpty() || fIn.isEmpty() || fOut.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
        return;
    }
        if (!validarFechas(checkin.getText().trim(), checkout.getText().trim())) {
        return;
    }
        
        if (!name.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
        javax.swing.JOptionPane.showMessageDialog(this, "El nombre solo debe contener letras.");
        return;
    }
        
        
        if (!ced.matches("\\d{7,10}")) {
        javax.swing.JOptionPane.showMessageDialog(this, "La cédula debe tener entre 7 y 10 números.");
        return;
    }
        if (!cel.matches("\\d{10}")) {
        javax.swing.JOptionPane.showMessageDialog(this, "El teléfono debe tener exactamente 10 números.");
        return;
    }
        if (!cor.contains("@") || !cor.contains(".")) {
        javax.swing.JOptionPane.showMessageDialog(this, "Ingrese un correo electrónico válido (ej: usuario@gmail.com).");
        return;
    }
         if (!fIn.matches("\\d{2}/\\d{2}/\\d{4}") || !fOut.matches("\\d{2}/\\d{2}/\\d{4}")) {
        javax.swing.JOptionPane.showMessageDialog(this, "Use el formato de fecha DD/MM/AAAA.");
        return;
    }       
        model.clienteslog nuevo = new model.clienteslog(ced, name, cel, cor, fIn, fOut);
        listaClientes.add(nuevo);
        actualizarTabla();
        limpiarCampos();
        cedula.setText("");
        nombre.setText("");
        telefono.setText("");
        correo.setText("");
        checkin.setText("");
        checkout.setText("");
    }//GEN-LAST:event_addActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        // TODO add your handling code here:
        int filaSeleccionada = clientetab.getSelectedRow();
        if (filaSeleccionada != -1) {
        int respuesta = javax.swing.JOptionPane.showConfirmDialog(this, 
                "¿Estás seguro de eliminar a este cliente?", "Confirmar", 
                javax.swing.JOptionPane.YES_NO_OPTION);
        
        if (respuesta == javax.swing.JOptionPane.YES_OPTION) {
            listaClientes.remove(filaSeleccionada);
            actualizarTabla();
            limpiarCampos();
            
            javax.swing.JOptionPane.showMessageDialog(this, "Cliente eliminado con éxito.");
        }
    } else {
        javax.swing.JOptionPane.showMessageDialog(this, "Por favor, selecciona un cliente de la tabla para eliminar.");
    }
    }//GEN-LAST:event_deleteActionPerformed

    private void modActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modActionPerformed
        // TODO add your handling code here:
        int filaSeleccionada = clientetab.getSelectedRow();
        if (filaSeleccionada != -1) {
        String ced = cedula.getText().trim();
        String nom = nombre.getText().trim();
        String tel = telefono.getText().trim();
        String cor = correo.getText().trim();
        String in = checkin.getText().trim();
        String out = checkout.getText().trim();

        if (!ced.matches("\\d{7,10}") || !tel.matches("\\d{10}")) {
            javax.swing.JOptionPane.showMessageDialog(this, "Revisar formato de Cédula o Teléfono.");
            return;
        }
        if (!cor.contains("@") || !nom.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
            javax.swing.JOptionPane.showMessageDialog(this, "Revisar Nombre o Correo.");
            return;
        }
        
        model.clienteslog cliente = listaClientes.get(filaSeleccionada);   
        cliente.cedula = ced;
        cliente.nombre = nom;
        cliente.telefono = tel;
        cliente.correo = cor;
        cliente.checkin = in;
        cliente.checkout = out;
        actualizarTabla();
        limpiarCampos();
        javax.swing.JOptionPane.showMessageDialog(this, "Datos actualizados correctamente.");
         } else {
        javax.swing.JOptionPane.showMessageDialog(this, "Selecciona un cliente de la tabla para modificar.");
        }
    }//GEN-LAST:event_modActionPerformed

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        // TODO add your handling code here:
            String cedulaBuscar = cedula.getText().trim();

        if (cedulaBuscar.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor, ingrese la cédula en el campo para poder realizar la búsqueda.", "Campo vacío", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean encontrado = false;

        // Buscamos secuencialmente en el ArrayList
        for (model.clienteslog c : listaClientes) {
            if (c.cedula.equals(cedulaBuscar)) {
                // Rellenamos todos los campos de la interfaz con la información del cliente
                nombre.setText(c.nombre);
                telefono.setText(c.telefono);
                correo.setText(c.correo);
                checkin.setText(c.checkin);
                checkout.setText(c.checkout);

                javax.swing.JOptionPane.showMessageDialog(this, "Cliente encontrado. Los datos se han cargado en los campos.", "Búsqueda Exitosa", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                encontrado = true;
                break; // Rompemos el ciclo al encontrarlo
            }
        }

        if (!encontrado) {
            javax.swing.JOptionPane.showMessageDialog(this, "No se encontró ningún cliente registrado con la cédula ingresada.", "Cliente no encontrado", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_searchActionPerformed

    private void elihabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_elihabActionPerformed
        // TODO add your handling code here:int filaSeleccionada = jTable2.getSelectedRow();
        int filaSeleccionada = jTable2.getSelectedRow();
        if (filaSeleccionada != -1) {
            int respuesta = javax.swing.JOptionPane.showConfirmDialog(this, 
                    "¿Estás seguro de eliminar esta habitación?", "Confirmar", 
                    javax.swing.JOptionPane.YES_NO_OPTION);
            
            if (respuesta == javax.swing.JOptionPane.YES_OPTION) {
                listaHabitaciones.remove(filaSeleccionada);
                actualizarTablaHab();
                limpiarCamposHab();
                javax.swing.JOptionPane.showMessageDialog(this, "Habitación eliminada con éxito.");
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor, selecciona una habitación de la tabla para eliminar.");
        }
    }//GEN-LAST:event_elihabActionPerformed

    private void empidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_empidActionPerformed

    private void addempActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addempActionPerformed
        // TODO add your handling code here:
        String id = empid.getText().trim();
        String name = empname.getText().trim();
        String cargo = cargoemp.getText().trim();
        String salario = salemp.getText().trim();
        String fecha = fechaemp.getText().trim();
        
        if (id.isEmpty() || name.isEmpty() || cargo.isEmpty() || salario.isEmpty() || fecha.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
            return;
        }
        
        if (!name.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
            javax.swing.JOptionPane.showMessageDialog(this, "El nombre solo debe contener letras.");
            return;
        }
        
        if (!cargo.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
            javax.swing.JOptionPane.showMessageDialog(this, "El cargo solo debe contener letras.");
            return;
        }
        
        if (!id.matches("\\d+")) {
            javax.swing.JOptionPane.showMessageDialog(this, "El ID debe contener solo números.");
            return;
        }
        
        // VALIDACIÓN DE LONGITUD (DE 7 A 10 CARACTERES)
        if (id.length() < 7 || id.length() > 10) {
            javax.swing.JOptionPane.showMessageDialog(this, "El ID debe tener entre 7 y 10 caracteres.");
            return;
        }
        
        if (!salario.matches("\\d+(\\.\\d+)?")) {
            javax.swing.JOptionPane.showMessageDialog(this, "El salario debe ser un número válido.");
            return;
        }
        
        if (!fecha.matches("\\d{2}/\\d{2}/\\d{4}")) {
            javax.swing.JOptionPane.showMessageDialog(this, "Use el formato de fecha DD/MM/AAAA.");
            return;
        }       
        
        double salDecimal = Double.parseDouble(salario);
        
        model.empleadoslog nuevo = new model.empleadoslog(id, name, "", "", cargo, salDecimal, fecha);
        listaEmpleados.add(nuevo);
        actualizarTablaEmp();
        limpiarCamposEmp();
    }//GEN-LAST:event_addempActionPerformed

    private void buscarempActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarempActionPerformed
        // TODO add your handling code here:
        String idBuscar = empid.getText().trim();

        if (idBuscar.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor, ingrese el ID en el campo para poder realizar la búsqueda.", "Campo vacío", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean encontrado = false;

        for (model.empleadoslog e : listaEmpleados) {
            if (e.cedula.equals(idBuscar)) {
                empname.setText(e.nombre);
                cargoemp.setText(e.cargo);
                salemp.setText(String.valueOf(e.salario));
                fechaemp.setText(e.fecha);

                javax.swing.JOptionPane.showMessageDialog(this, "Empleado encontrado. Los datos se han cargado en los campos.", "Búsqueda Exitosa", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            javax.swing.JOptionPane.showMessageDialog(this, "No se encontró ningún empleado registrado con el ID ingresado.", "Empleado no encontrado", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_buscarempActionPerformed

    private void modempActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modempActionPerformed
        // TODO add your handling code here:
        int filaSeleccionada = jTable3.getSelectedRow();
        if (filaSeleccionada != -1) {
            String id = empid.getText().trim();
            String nom = empname.getText().trim();
            String cargo = cargoemp.getText().trim();
            String salario = salemp.getText().trim();
            String fecha = fechaemp.getText().trim();

            if (!id.matches("\\d+")) {
                javax.swing.JOptionPane.showMessageDialog(this, "Revisar formato de ID (solo números).");
                return;
            }
            if (!salario.matches("\\d+(\\.\\d+)?")) {
                javax.swing.JOptionPane.showMessageDialog(this, "Revisar formato de Salario.");
                return;
            }
            if (!nom.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$") || !cargo.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
                javax.swing.JOptionPane.showMessageDialog(this, "Revisar Nombre o Cargo.");
                return;
            }
            if (!fecha.matches("\\d{2}/\\d{2}/\\d{4}")) {
                javax.swing.JOptionPane.showMessageDialog(this, "Use el formato de fecha DD/MM/AAAA.");
                return;
            }
            
            model.empleadoslog empleado = listaEmpleados.get(filaSeleccionada);   
            empleado.cedula = id;
            empleado.nombre = nom;
            empleado.cargo = cargo;
            empleado.salario = Double.parseDouble(salario);
            empleado.fecha = fecha;
            
            actualizarTablaEmp();
            limpiarCamposEmp();
            javax.swing.JOptionPane.showMessageDialog(this, "Datos actualizados correctamente.");
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Selecciona un empleado de la tabla para modificar.");
        }
    }//GEN-LAST:event_modempActionPerformed

    private void eliempActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliempActionPerformed
        // TODO add your handling code here:
        int filaSeleccionada = jTable3.getSelectedRow();
        if (filaSeleccionada != -1) {
            int respuesta = javax.swing.JOptionPane.showConfirmDialog(this, 
                    "¿Estás seguro de eliminar a este empleado?", "Confirmar", 
                    javax.swing.JOptionPane.YES_NO_OPTION);
            
            if (respuesta == javax.swing.JOptionPane.YES_OPTION) {
                listaEmpleados.remove(filaSeleccionada);
                actualizarTablaEmp();
                limpiarCamposEmp();
                
                javax.swing.JOptionPane.showMessageDialog(this, "Empleado eliminado con éxito.");
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor, selecciona un empleado de la tabla para eliminar.");
        }
    }//GEN-LAST:event_eliempActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:
        int fila = jTable3.getSelectedRow();
        if (fila != -1) {
            empid.setText(jTable3.getValueAt(fila, 0).toString());
            empname.setText(jTable3.getValueAt(fila, 1).toString());
            cargoemp.setText(jTable3.getValueAt(fila, 2).toString());
            salemp.setText(jTable3.getValueAt(fila, 3).toString());
            fechaemp.setText(jTable3.getValueAt(fila, 4).toString());
        }
    }//GEN-LAST:event_jTable3MouseClicked

    private void tipoHabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoHabActionPerformed
        // TODO add your handling code here:
        String opcion = tipoHab.getSelectedItem().toString();
        String ruta = "";
        
        switch (opcion) {
            case "Sencilla":
                ruta = "/images/sencilla.png";
                break;
            case "Double":
                ruta = "/images/double.png";
                break;
            case "Suite":
                ruta = "/images/suite.png";
                break;
            default:
                ruta = "";
                break;
        }
        
        if (!ruta.isEmpty()) {
            try {
                imagenHab.setIcon(new javax.swing.ImageIcon(getClass().getResource(ruta)));
            } catch (Exception e) {
                imagenHab.setIcon(null); // Evita caídas si la ruta falla
            }
        } else {
            imagenHab.setIcon(null);
        }
    }//GEN-LAST:event_tipoHabActionPerformed

    private void addhabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addhabActionPerformed
        // TODO add your handling code here:
        String numero = numhab.getText().trim();
        String tipo = tipoHab.getSelectedItem().toString();
        String precio = preciohab.getText().trim();
        String estado = estadohab.getSelectedItem().toString(); // <-- NUEVO
        
        if (numero.isEmpty() || precio.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
            return;
        }
        
        if (!numero.matches("\\d+")) {
            javax.swing.JOptionPane.showMessageDialog(this, "El número de habitación debe contener solo números.");
            return;
        }
        
        if (!precio.matches("\\d+(\\.\\d+)?")) {
            javax.swing.JOptionPane.showMessageDialog(this, "El precio debe ser un número válido.");
            return;
        }
        
        double precioDecimal = Double.parseDouble(precio);
        
        // Se pasa el estado al crear el objeto
        model.habitacionlog nueva = new model.habitacionlog(numero, tipo, precioDecimal, estado);
        listaHabitaciones.add(nueva);
        actualizarTablaHab();
        limpiarCamposHab();
    }//GEN-LAST:event_addhabActionPerformed

    private void bushabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bushabActionPerformed
        // TODO add your handling code here:
        String numBuscar = numhab.getText().trim();

        if (numBuscar.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor, ingrese el número de habitación para buscar.");
            return;
        }

        boolean encontrado = false;

        for (model.habitacionlog h : listaHabitaciones) {
            if (h.numero.equals(numBuscar)) {
                tipoHab.setSelectedItem(h.tipo);
                preciohab.setText(String.valueOf(h.precio));
                estadohab.setSelectedItem(h.estado); // <-- NUEVO: Carga el estado encontrado

                javax.swing.JOptionPane.showMessageDialog(this, "Habitación encontrada. Datos cargados.");
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            javax.swing.JOptionPane.showMessageDialog(this, "No se encontró ninguna habitación con ese número.");
        }
    }//GEN-LAST:event_bushabActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        int fila = jTable2.getSelectedRow();
        if (fila != -1) {
            numhab.setText(jTable2.getValueAt(fila, 0).toString());
            tipoHab.setSelectedItem(jTable2.getValueAt(fila, 1).toString());
            preciohab.setText(jTable2.getValueAt(fila, 2).toString());
            estadohab.setSelectedItem(jTable2.getValueAt(fila, 3).toString()); // <-- NUEVO: Lee el estado de la tabla
        }
    }//GEN-LAST:event_jTable2MouseClicked

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
        java.awt.EventQueue.invokeLater(() -> new Gerencia().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JButton addemp;
    private javax.swing.JButton addhab;
    private javax.swing.JButton buscaremp;
    private javax.swing.JButton bushab;
    private javax.swing.JTextField cargoemp;
    private javax.swing.JTextField cedula;
    private javax.swing.JTextField checkin;
    private javax.swing.JTextField checkout;
    private javax.swing.JTable clientetab;
    private javax.swing.JTextField correo;
    private javax.swing.JButton delete;
    private javax.swing.JButton eliemp;
    private javax.swing.JButton elihab;
    private javax.swing.JTextField empid;
    private javax.swing.JTextField empname;
    private javax.swing.JComboBox<String> estadohab;
    private javax.swing.JTextField fechaemp;
    private javax.swing.JLabel imagenHab;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JButton mod;
    private javax.swing.JButton modemp;
    private javax.swing.JButton modhab;
    private javax.swing.JTextField nombre;
    private javax.swing.JTextField numhab;
    private javax.swing.JTextField preciohab;
    private javax.swing.JTextField salemp;
    private javax.swing.JButton search;
    private javax.swing.JTextField telefono;
    private javax.swing.JComboBox<String> tipoHab;
    // End of variables declaration//GEN-END:variables
}
