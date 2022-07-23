
package Vistas;

import Controlador.*;
import Modelo.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

import javax.swing.JOptionPane;

/**
 * User menu Frame
 * @author Carlos
 */
public class UserMenu extends javax.swing.JFrame {
    //Variables Sql connection

    Conexion conexion = new Conexion();
    Connection connection;
    Statement st;
    ResultSet rs;
    DefaultTableModel contenidoTablaEmpleados;
    DefaultTableModel contenidoTablaDepartamentos;
    ComboBoxModel modelEnumDepartamentos;
    ComboBoxModel modelEnumZona;
    ComboBoxModel modelEnumTipoCalle;

    /**
     * @author Carlos
     * Creates new form UserMenu
     */
    
    public UserMenu() {
	modelEnumDepartamentos = new DefaultComboBoxModel(EnumDepartamento.values());
	modelEnumZona = new DefaultComboBoxModel(EnumZona.values());
	modelEnumTipoCalle = new DefaultComboBoxModel(EnumTipoCalle.values());
	initComponents();
	listarEmpleados();
	listarDepartamentos();
    }

    //employees sql connection to jtable
    private void listarEmpleados() {
	String empFinder = txtFindEmp.getText();
	if (empFinder.isEmpty()) {

	    String queryConsulta = "SELECT nombreEmp,apellidos,tipoDocumento,documento,correo, nombreSucursal FROM empleado INNER JOIN sucursal ON empleado.FK_idSucursal = sucursal.idSucursal";
	    try {
		connection = conexion.getConnection();
		//Creating  queryConsulta
		st = connection.createStatement();
		// Exe the query from the variable queryConsulta
		rs = st.executeQuery(queryConsulta);
		// Object to get all elements from the data base to a jtable

		Object[] empleados = new Object[6];
		contenidoTablaEmpleados = (DefaultTableModel) tblEmpleados.getModel();
		// While loop to run every element from the query

		while (rs.next()) {
		   
		    empleados[0] = rs.getString("nombreEmp");
		    empleados[1] = rs.getString("apellidos");
		    empleados[2] = rs.getString("tipoDocumento");
		    empleados[3] = rs.getString("documento");
		    empleados[4] = rs.getString("correo");
		    empleados[5] = rs.getString("nombreSucursal");
		    // making a new row on the jtable for each employee
		    contenidoTablaEmpleados.addRow(empleados);
		    //Sout to print each employee data 
		    System.out.println(", nombre: " + empleados[0] + " , apellido: " + empleados[1] + ", documento: "
			    + empleados[2] + " " + empleados[3] + ", correo: " + empleados[4]+ ", sucursal " + empleados[5]);
		    tblEmpleados.setModel(contenidoTablaEmpleados);
		}

	    } catch (SQLException e) {
		System.out.println("1 Error de creación de tabla ");

	    }
	} else {
	    String queryFind = "SELECT nombreEmp ,apellidos, tipodocumento, documento, correo, nombreSucursal FROM empleado INNER JOIN sucursal ON FK_idSucursal = idSucursal WHERE nombreEmp "+" LIKE '%"+ empFinder + "%' OR apellidos LIKE '%"+ empFinder + "%' OR documento LIKE '%"+ empFinder + "%' OR correo LIKE '%"+ empFinder + "%'OR nombreSucursal LIKE '%"+ empFinder + "%';";
             System.out.println(queryFind);
	    try {
		connection = conexion.getConnection();
		//Creating  queryConsulta
		st = connection.createStatement();
		// Exe the query from the variable queryConsulta
		rs = st.executeQuery(queryFind);
		// Object to get all elements from the data base to a jtable

		Object[] empleados = new Object[6];
		contenidoTablaEmpleados = (DefaultTableModel) tblEmpleados.getModel();
		// While loop to run every element from the query

		while (rs.next()) {
		    empleados[0] = rs.getString("nombreEmp");
		    empleados[1] = rs.getString("apellidos");
		    empleados[2] = rs.getString("tipoDocumento");
		    empleados[3] = rs.getString("documento");
		    empleados[4] = rs.getString("correo");
		    empleados[5] = rs.getString("nombreSucursal");
		    // making a new row on the jtable for each employee
		    contenidoTablaEmpleados.addRow(empleados);
		    //Sout to print each employee data 
		    System.out.println(", nombre: " + empleados[0] + " , apellido: " + empleados[1] + ", documento: "
			    + empleados[2] + " " + empleados[3] + ", correo: " + empleados[4]+ ", sucursal " + empleados[5]);
		    tblEmpleados.setModel(contenidoTablaEmpleados);
		}

	    } catch (SQLException e) {
		System.out.println(" 2 Error de creación de tabla ");

	    }

	}

    }
    // method to deleted all the data from the jtable

    public void borrarDatosTabla() {
	// for loop to deleted each element  on each row inside the table 
	for (int i = 0; i < tblEmpleados.getRowCount(); i++) {
	    //removing each row inside the table with removeRow
	    contenidoTablaEmpleados.removeRow(i);
	    i -= 1;
	}
    }

    // Departamento sql connection to jtable 
    private void listarDepartamentos() {
        String branchFinder = txtSrchBranch.getText();
	

	if (branchFinder.isEmpty()){
	    
	    //query 
	    String queryDepartamentos = "SELECT nombreSucursal, nombreDepartamento, CONCAT ('Zona: ',zona, ' - ', tipoCalle,' ',numero1,' #N° ', numero2, ' - ', numero3 ) AS direccion FROM direccion INNER JOIN sucursal  ON idDireccion  = FK_idDireccion;";
	    System.out.println(queryDepartamentos);
	try {
	    connection = conexion.getConnection();
	    //Creating  queryConsulta
	    st = connection.createStatement();
	    // Exe the query from the variable queryConsulta
	    rs = st.executeQuery(queryDepartamentos);
	    // Object to get all elements from the data base to a jtable

	    Object[] departamentos = new Object[3];
	    contenidoTablaDepartamentos = (DefaultTableModel) tblDepartamentos.getModel();
	    // While loop to run every element from the query

	    while (rs.next()) {
		departamentos[0] = rs.getString("nombreSucursal");
		departamentos[1] = rs.getString("nombreDepartamento");
                departamentos[2] = rs.getString("direccion");
		// making a new row on the jtable for each employee
		contenidoTablaDepartamentos.addRow(departamentos);
		//Sout to print each employee data 
		System.out.println("Sucursal: " + departamentos[0] + " , Departamento: " + departamentos[1]);
		tblDepartamentos.setModel(contenidoTablaDepartamentos);
	    }

	} catch (SQLException e) {

	    System.out.println("Error de creación de tabla de departamentos");
	    System.out.println(e);
	}
	
	}else{
	    
	    String querySrcBranch = "SELECT nombreSucursal, nombreDepartamento, CONCAT (zona, ' - ', tipoCalle,' ',numero1,' #N° ', numero2, ' - ', numero3 ) AS direccion  FROM direccion INNER JOIN sucursal  ON idDireccion  = FK_idDireccion WHERE nombreSucursal" + " LIKE '%" + branchFinder + "%' OR nombreDepartamento LIKE '%" + branchFinder+ "%' ;";
	    System.out.println(branchFinder);
	    System.out.println(querySrcBranch);
	    try {
	    connection = conexion.getConnection();
	    //Creating  queryConsulta
	    st = connection.createStatement();
	    // Exe the query from the variable queryConsulta
	    rs = st.executeQuery(querySrcBranch);
	    // Object to get all elements from the data base to a jtable

	    Object[] departamentos = new Object[3];
	    contenidoTablaDepartamentos = (DefaultTableModel) tblDepartamentos.getModel();
	    // While loop to run every element from the query

	    while (rs.next()) {
		departamentos[0] = rs.getString("nombreSucursal");
		departamentos[1] = rs.getString("nombreDepartamento");
		departamentos[2] = rs.getString("direccion");

		// making a new row on the jtable for each employee
		contenidoTablaDepartamentos.addRow(departamentos);
		//Sout to print each employee data 
		System.out.println("Sucursal: " + departamentos[0] + " , Departamento: " + departamentos[1]);
		tblDepartamentos.setModel(contenidoTablaDepartamentos);
	    }

	} catch (SQLException e) {

	    System.out.println("Error de creación de tabla de departamentos");
	    System.out.println(e);
	}
	         
	    
	    
	}

    }

    public void borrarDatosTablaDepartamentos() {
	// for loop to deleted each element  on each row inside the table 
	for (int i = 0; i < tblDepartamentos.getRowCount(); i++) {
	    //removing each row inside the table with removeRow
	    contenidoTablaDepartamentos.removeRow(i);
	    i -= 1;
	}

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg1 = new javax.swing.JPanel();
        bg2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnHome = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        btnEmpleados = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        btnSales = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        btnProducts = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        uiFrameMenuTab = new javax.swing.JTabbedPane();
        homeTab = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        txtWelcome = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDepartamentos = new javax.swing.JTable();
        txtSrchBranch = new javax.swing.JTextField();
        btnBuscarDepart = new javax.swing.JButton();
        btnEmpleado = new javax.swing.JButton();
        btnAddBranch = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        empTab = new javax.swing.JPanel();
        empInfoBg = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEmpleados = new javax.swing.JTable();
        txtInfo = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();
        btnAddEmp = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtFindEmp = new javax.swing.JTextField();
        salesTab = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        productsTab = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dashboard MinTic Store");
        setBackground(new java.awt.Color(204, 204, 204));
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setName("Empleados"); // NOI18N
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bg1.setBackground(new java.awt.Color(81, 141, 173));
        bg1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bg2.setBackground(new java.awt.Color(142, 161, 249));
        bg2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/logo-mision.png"))); // NOI18N
        bg2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 11, -1, -1));

        btnHome.setBackground(new java.awt.Color(90, 76, 122));
        btnHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHomeMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("SUCURSALES");

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/home 28.png"))); // NOI18N

        javax.swing.GroupLayout btnHomeLayout = new javax.swing.GroupLayout(btnHome);
        btnHome.setLayout(btnHomeLayout);
        btnHomeLayout.setHorizontalGroup(
            btnHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnHomeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        btnHomeLayout.setVerticalGroup(
            btnHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnHomeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(btnHomeLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel15))
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        bg2.add(btnHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 126, 180, -1));

        btnEmpleados.setBackground(new java.awt.Color(183, 155, 250));
        btnEmpleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEmpleadosMouseClicked(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("EMPLEADOS");

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/employee28.png"))); // NOI18N

        javax.swing.GroupLayout btnEmpleadosLayout = new javax.swing.GroupLayout(btnEmpleados);
        btnEmpleados.setLayout(btnEmpleadosLayout);
        btnEmpleadosLayout.setHorizontalGroup(
            btnEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnEmpleadosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addGap(52, 52, 52))
        );
        btnEmpleadosLayout.setVerticalGroup(
            btnEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnEmpleadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(btnEmpleadosLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel17))
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        bg2.add(btnEmpleados, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 186, -1));

        btnSales.setBackground(new java.awt.Color(183, 155, 250));
        btnSales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSalesMouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("VENTAS");

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/acquisition28.png"))); // NOI18N

        javax.swing.GroupLayout btnSalesLayout = new javax.swing.GroupLayout(btnSales);
        btnSales.setLayout(btnSalesLayout);
        btnSalesLayout.setHorizontalGroup(
            btnSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnSalesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addContainerGap(77, Short.MAX_VALUE))
        );
        btnSalesLayout.setVerticalGroup(
            btnSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnSalesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(btnSalesLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel16))
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        bg2.add(btnSales, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 186, -1));

        btnProducts.setBackground(new java.awt.Color(183, 155, 250));
        btnProducts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProductsMouseClicked(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("PRODUCTOS");

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/box28.png"))); // NOI18N

        javax.swing.GroupLayout btnProductsLayout = new javax.swing.GroupLayout(btnProducts);
        btnProducts.setLayout(btnProductsLayout);
        btnProductsLayout.setHorizontalGroup(
            btnProductsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnProductsLayout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jLabel18)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addGap(44, 44, 44))
        );
        btnProductsLayout.setVerticalGroup(
            btnProductsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnProductsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnProductsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(btnProductsLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel18))
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        bg2.add(btnProducts, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 186, -1));

        bg1.add(bg2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 600));

        getContentPane().add(bg1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel5.setBackground(new java.awt.Color(81, 141, 173));

        jLabel19.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("DASHBOARD");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(331, 331, 331)
                .addComponent(jLabel19)
                .addContainerGap(378, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel19)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 850, 100));

        uiFrameMenuTab.setBackground(new java.awt.Color(183, 155, 250));

        jPanel4.setBackground(new java.awt.Color(183, 155, 250));
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));

        txtWelcome.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        txtWelcome.setForeground(new java.awt.Color(255, 255, 255));
        txtWelcome.setText("Sucursales");

        tblDepartamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sucursal", "Departamento", "Direccion"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDepartamentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDepartamentosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblDepartamentos);
        if (tblDepartamentos.getColumnModel().getColumnCount() > 0) {
            tblDepartamentos.getColumnModel().getColumn(0).setResizable(false);
            tblDepartamentos.getColumnModel().getColumn(1).setResizable(false);
            tblDepartamentos.getColumnModel().getColumn(2).setResizable(false);
        }

        txtSrchBranch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSrchBranchKeyPressed(evt);
            }
        });

        btnBuscarDepart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/search16.png"))); // NOI18N
        btnBuscarDepart.setText("Consultar");
        btnBuscarDepart.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnBuscarDepartKeyPressed(evt);
            }
        });

        btnEmpleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/profile.png"))); // NOI18N
        btnEmpleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEmpleadoMouseClicked(evt);
            }
        });

        btnAddBranch.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnAddBranch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/plus16.png"))); // NOI18N
        btnAddBranch.setText("Crear Sucursal");
        btnAddBranch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddBranchMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Buscar");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnEmpleado)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtWelcome)
                        .addContainerGap(741, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txtSrchBranch, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscarDepart, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAddBranch, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(77, 77, 77))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(txtWelcome)
                .addGap(24, 24, 24)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSrchBranch, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(btnBuscarDepart)
                    .addComponent(btnAddBranch))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEmpleado)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout homeTabLayout = new javax.swing.GroupLayout(homeTab);
        homeTab.setLayout(homeTabLayout);
        homeTabLayout.setHorizontalGroup(
            homeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        homeTabLayout.setVerticalGroup(
            homeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        uiFrameMenuTab.addTab("Home", homeTab);

        empInfoBg.setBackground(new java.awt.Color(183, 155, 250));

        tblEmpleados.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        tblEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Apellido(s)", "Tipo Documento", "Documento", "Correo", "Sucursal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblEmpleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEmpleadosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblEmpleados);

        txtInfo.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        txtInfo.setForeground(new java.awt.Color(255, 255, 255));
        txtInfo.setText("Información Empleados");

        btnSearch.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/search16.png"))); // NOI18N
        btnSearch.setText("Consultar");
        btnSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchMouseClicked(evt);
            }
        });

        btnAddEmp.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        btnAddEmp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/plus16.png"))); // NOI18N
        btnAddEmp.setText("Crear Empleado");
        btnAddEmp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddEmpMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Buscar");

        txtFindEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFindEmpActionPerformed(evt);
            }
        });
        txtFindEmp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFindEmpKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout empInfoBgLayout = new javax.swing.GroupLayout(empInfoBg);
        empInfoBg.setLayout(empInfoBgLayout);
        empInfoBgLayout.setHorizontalGroup(
            empInfoBgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(empInfoBgLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(empInfoBgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 739, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(empInfoBgLayout.createSequentialGroup()
                        .addGroup(empInfoBgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtInfo)
                            .addGroup(empInfoBgLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtFindEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAddEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(72, Short.MAX_VALUE))
        );
        empInfoBgLayout.setVerticalGroup(
            empInfoBgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, empInfoBgLayout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addComponent(txtInfo)
                .addGap(28, 28, 28)
                .addGroup(empInfoBgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSearch)
                    .addComponent(txtFindEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(btnAddEmp))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73))
        );

        javax.swing.GroupLayout empTabLayout = new javax.swing.GroupLayout(empTab);
        empTab.setLayout(empTabLayout);
        empTabLayout.setHorizontalGroup(
            empTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(empInfoBg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        empTabLayout.setVerticalGroup(
            empTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(empInfoBg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        uiFrameMenuTab.addTab("Emp", empTab);

        jPanel2.setBackground(new java.awt.Color(183, 155, 250));

        jLabel13.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Linea de ventas");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel13)
                .addContainerGap(713, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabel13)
                .addContainerGap(455, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout salesTabLayout = new javax.swing.GroupLayout(salesTab);
        salesTab.setLayout(salesTabLayout);
        salesTabLayout.setHorizontalGroup(
            salesTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        salesTabLayout.setVerticalGroup(
            salesTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        uiFrameMenuTab.addTab("Sales", salesTab);

        jPanel3.setBackground(new java.awt.Color(183, 155, 250));

        jLabel14.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Productos");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel14)
                .addContainerGap(746, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jLabel14)
                .addContainerGap(456, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout productsTabLayout = new javax.swing.GroupLayout(productsTab);
        productsTab.setLayout(productsTabLayout);
        productsTabLayout.setHorizontalGroup(
            productsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        productsTabLayout.setVerticalGroup(
            productsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        uiFrameMenuTab.addTab("Products", productsTab);

        getContentPane().add(uiFrameMenuTab, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 860, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseClicked
	//Home btn Click event 

	uiFrameMenuTab.setSelectedIndex(0);
	if (uiFrameMenuTab.getTitleAt(uiFrameMenuTab.getSelectedIndex()).equals("Home")) {
	    btnHome.setBackground(new Color(90, 76, 122));
	    btnEmpleados.setBackground(new Color(183, 155, 250));
	    btnSales.setBackground(new Color(183, 155, 250));
	    btnProducts.setBackground(new Color(183, 155, 250));
	}

    }//GEN-LAST:event_btnHomeMouseClicked

    private void btnEmpleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEmpleadosMouseClicked
	//Employee btn CLick event
	uiFrameMenuTab.setSelectedIndex(1);
	if (uiFrameMenuTab.getTitleAt(uiFrameMenuTab.getSelectedIndex()).equals("Emp")) {
	    btnEmpleados.setBackground(new Color(90, 76, 122));
	    btnHome.setBackground(new Color(183, 155, 250));
	    btnSales.setBackground(new Color(183, 155, 250));
	    btnProducts.setBackground(new Color(183, 155, 250));

	}
    }//GEN-LAST:event_btnEmpleadosMouseClicked

    private void btnSalesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalesMouseClicked
	// Sale btn click event
	uiFrameMenuTab.setSelectedIndex(2);
	if (uiFrameMenuTab.getTitleAt(uiFrameMenuTab.getSelectedIndex()).equals("Sales")) {
	    btnSales.setBackground(new Color(90, 76, 122));
	    btnHome.setBackground(new Color(183, 155, 250));
	    btnEmpleados.setBackground(new Color(183, 155, 250));
	    btnProducts.setBackground(new Color(183, 155, 250));
	}
    }//GEN-LAST:event_btnSalesMouseClicked

    private void btnProductsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProductsMouseClicked
	// Products btn click event
	uiFrameMenuTab.setSelectedIndex(3);
	if (uiFrameMenuTab.getTitleAt(uiFrameMenuTab.getSelectedIndex()).equals("Products")) {
	    btnProducts.setBackground(new Color(90, 76, 122));
	    btnHome.setBackground(new Color(183, 155, 250));
	    btnEmpleados.setBackground(new Color(183, 155, 250));
	    btnSales.setBackground(new Color(183, 155, 250));
	}

    }//GEN-LAST:event_btnProductsMouseClicked

    private void txtFindEmpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFindEmpKeyPressed
	// on enter find employee
	if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
	    borrarDatosTabla();
	    listarEmpleados();
	}

    }//GEN-LAST:event_txtFindEmpKeyPressed

    private void txtFindEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFindEmpActionPerformed
	// TODO add your handling code here:
    }//GEN-LAST:event_txtFindEmpActionPerformed

    private void btnAddEmpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddEmpMouseClicked
	AddUserForm addUserF = new AddUserForm(this, rootPaneCheckingEnabled);
	addUserF.setVisible(rootPaneCheckingEnabled);
	borrarDatosTabla();
	listarEmpleados();

    }//GEN-LAST:event_btnAddEmpMouseClicked

    private void btnSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMouseClicked
	borrarDatosTabla();
	listarEmpleados();
    }//GEN-LAST:event_btnSearchMouseClicked

    private void tblEmpleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmpleadosMouseClicked
	// get information from row num
	int row = tblEmpleados.getSelectedRow();
	System.out.println("Fila seleccionada: " + row);

	//Validate data
	if (row < 0) {
	    JOptionPane.showMessageDialog(this, "Selecciona un  empleado ", " ", JOptionPane.WARNING_MESSAGE);

	} else {
	    //Get employee (id, name, surname,  id type , id num , e-mail )
	    
	    // row 0 column 1 =  employee name
	    String nombre = (String) tblEmpleados.getValueAt(row, 0);
	    String apellidos = (String) tblEmpleados.getValueAt(row, 1);
	    String tipoDocumento = (String) tblEmpleados.getValueAt(row, 2);
	    String documento = (String) tblEmpleados.getValueAt(row, 3);
	    String correo = (String) tblEmpleados.getValueAt(row, 4);
	    String sucursal = (String) tblEmpleados.getValueAt(row, 5);
	    System.out.println( ", empleado: " + nombre + " " + apellidos
		    + ", tipo documento: " + tipoDocumento + ", numero: " + documento + ",  correo: " + correo + ",  sucursal: " + sucursal);

	    // instance for jDialog to show the selected employee info
	    ShowUserForm showUserForm = new ShowUserForm(this, true);
	    showUserForm.recibirDatos(nombre, apellidos, documento, correo,sucursal);
	    showUserForm.setVisible(true);

	    //update data from table
	    borrarDatosTabla();
	    listarEmpleados();

	}
    }//GEN-LAST:event_tblEmpleadosMouseClicked

    private void tblDepartamentosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDepartamentosMouseClicked
    // get information from row num
	int row = tblDepartamentos.getSelectedRow();
	System.out.println("Fila seleccionada: " + row);

	//Validate data
	if (row < 0) {
	    JOptionPane.showMessageDialog(this, "Selecciona una sucursal ", " ", JOptionPane.WARNING_MESSAGE);

	} else {
	    //Get employee (id, name, surname,  id type , id num , e-mail )
	    
	    // row 0 column 1 =  employee name
	    String nombreSucursal = (String) tblDepartamentos.getValueAt(row, 0);
	    String departamento = (String) tblDepartamentos.getValueAt(row, 1);
	    String direccion = (String) tblDepartamentos.getValueAt(row, 2);
	    
	    System.out.println( ", Nombre sucursal : " + nombreSucursal + ", Departamento " + departamento + ", Direccion: "+ direccion);

	    // instance for jDialog to show the selected employee info
	    ShowBranchForm showBranchF = new ShowBranchForm(this, true);
	    showBranchF.recibirDatos(nombreSucursal);
	    showBranchF.setVisible(true);

	    //update data from table
	    borrarDatosTablaDepartamentos();
	    listarDepartamentos();

	}
	

    }//GEN-LAST:event_tblDepartamentosMouseClicked

    private void btnEmpleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEmpleadoMouseClicked

	int row = tblDepartamentos.getSelectedRow();
	System.out.println(row);

	// row 0 column 1 = sucursal 
	String sucursal = (String) tblDepartamentos.getValueAt(row, 0);

	// query Sucursal get from row name to find selected Sucursal
	String querySucursal = "SELECT idSucursal FROM `sucursal` WHERE nombreSucursal = '" + sucursal + "';";
	System.out.println("querySucursal clicked: " + querySucursal);

	try {
	    connection = conexion.getConnection();
	    st = connection.createStatement();
	    rs = st.executeQuery(querySucursal);
	    while (rs.next()) {
		int idSucursal = rs.getInt("idSucursal");
		
		System.out.println("Envia Id Sucursal: " + idSucursal);
		EmpleadoForm empleadoForm = new EmpleadoForm(this, true);
		empleadoForm.setVisible(true);
		empleadoForm.recibeIdSucursal(idSucursal);

	    }

	} catch (SQLException e) {

	}


    }//GEN-LAST:event_btnEmpleadoMouseClicked

    private void txtSrchBranchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSrchBranchKeyPressed
       
	if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
	    borrarDatosTablaDepartamentos();
	    listarDepartamentos();
	}

    }//GEN-LAST:event_txtSrchBranchKeyPressed

    private void btnBuscarDepartKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnBuscarDepartKeyPressed
           borrarDatosTablaDepartamentos();
	   listarDepartamentos();
    }//GEN-LAST:event_btnBuscarDepartKeyPressed

    private void btnAddBranchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddBranchMouseClicked
        SucursalForm addNewB = new SucursalForm(this, rootPaneCheckingEnabled);
	addNewB.setVisible(rootPaneCheckingEnabled);
	borrarDatosTablaDepartamentos();
	listarDepartamentos();
    }//GEN-LAST:event_btnAddBranchMouseClicked

    /**
     *
     *
     *
     *
     *
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
	} catch (ClassNotFoundException ex) {
	    java.util.logging.Logger.getLogger(UserMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (InstantiationException ex) {
	    java.util.logging.Logger.getLogger(UserMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (IllegalAccessException ex) {
	    java.util.logging.Logger.getLogger(UserMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (javax.swing.UnsupportedLookAndFeelException ex) {
	    java.util.logging.Logger.getLogger(UserMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	}
	//</editor-fold>

	/* Create and display the form */
	java.awt.EventQueue.invokeLater(() -> {
	    new UserMenu().setVisible(true);
	});
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg1;
    private javax.swing.JPanel bg2;
    private javax.swing.JButton btnAddBranch;
    private javax.swing.JButton btnAddEmp;
    private javax.swing.JButton btnBuscarDepart;
    private javax.swing.JButton btnEmpleado;
    private javax.swing.JPanel btnEmpleados;
    private javax.swing.JPanel btnHome;
    private javax.swing.JPanel btnProducts;
    private javax.swing.JPanel btnSales;
    private javax.swing.JButton btnSearch;
    private javax.swing.JPanel empInfoBg;
    private javax.swing.JPanel empTab;
    private javax.swing.JPanel homeTab;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel productsTab;
    private javax.swing.JPanel salesTab;
    private javax.swing.JTable tblDepartamentos;
    private javax.swing.JTable tblEmpleados;
    private javax.swing.JTextField txtFindEmp;
    private javax.swing.JLabel txtInfo;
    private javax.swing.JTextField txtSrchBranch;
    private javax.swing.JLabel txtWelcome;
    private javax.swing.JTabbedPane uiFrameMenuTab;
    // End of variables declaration//GEN-END:variables
}
