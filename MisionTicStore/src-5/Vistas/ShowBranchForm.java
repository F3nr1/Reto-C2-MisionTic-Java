/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Modelo.Conexion;
import Controlador.*;
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *Show info from a Sucursal to edit or deleted
 * @author Carlos
 */
public class ShowBranchForm extends javax.swing.JDialog {

    //Variables Sql connection
    Conexion conexion = new Conexion();
    Connection connection;
    Statement st;
    ResultSet rs;
    ComboBoxModel modelEnumDepartamentos;
    ComboBoxModel modelEnumZonas;
    ComboBoxModel modelEnumTipoCalles;

    public ShowBranchForm(java.awt.Frame parent, boolean modal) {

	super(parent, modal);
	modelEnumDepartamentos = new DefaultComboBoxModel(EnumDepartamento.values());
	modelEnumZonas = new DefaultComboBoxModel(EnumZona.values());
	modelEnumTipoCalles = new DefaultComboBoxModel(EnumTipoCalle.values());
	initComponents();
	this.setLocationRelativeTo(parent);
    }

    public void recibirDatos(String nombreSucursal) {
	System.out.println(nombreSucursal);
	//Single store value retrive from UserMenu
	txtSucursal.setText(nombreSucursal);

	// get FK_idDireccion since is the same as the one use on idDireccion 
	String querySucursal = "SELECT FK_idDireccion FROM sucursal WHERE nombreSucursal = '" + nombreSucursal + "';";
	System.out.println(querySucursal);

	// try can catch to get FK_Id
	try {
	    connection = conexion.getConnection();
	    //Creating  queryConsulta
	    st = connection.createStatement();
	    // Exe the query from the variable queryConsulta
	    rs = st.executeQuery(querySucursal);
	    // Object to get all elements from the data base to a jtable
	    while (rs.next()) {
		//Store FK_id as idDireccion so it will be more easier to link a sucursal with its Address
		int IdDireccion = rs.getInt("FK_idDireccion");
		System.out.println(IdDireccion);

		// from idDireccion get the address values
		String queryAddress = "SELECT zona, nombreDepartamento, tipoCalle, numero1, numero2, numero3 FROM `direccion` WHERE idDireccion = '" + IdDireccion + "';";
		try {
		    connection = conexion.getConnection();

		    st = connection.createStatement();
		    //Exe query
		    rs = st.executeQuery(queryAddress);

		    // loop´to fill all the txt and cb from the values retrive from the db into the jdialog 
		    while (rs.next()) {
			String zona = rs.getString("zona");
			String nombreDepartamento = rs.getString("nombreDepartamento");
			String tipoCalle = rs.getString("tipoCalle");
			String numero1 = rs.getString("numero1");
			String numero2 = rs.getString("numero2");
			String numero3 = rs.getString("numero3");
			modelEnumDepartamentos.setSelectedItem(nombreDepartamento);
			modelEnumZonas.setSelectedItem(zona);
			modelEnumTipoCalles.setSelectedItem(tipoCalle);
			txtNum1.setText(numero1);
			txtNum2.setText(numero2);
			txtNum3.setText(numero3);

		    }

		} catch (SQLException e) {

		}

	    }

	} catch (SQLException e) {

	    System.out.println(e);
	}

    }

    public void actualizarSucursal() {
	// get FK_id from nombre sucursal 
	String nombreSucursal = txtSucursal.getText();

	// data variables
	String nuevaSucursal = txtNewBranchN.getText();
	String departamento = cbDepartamento.getSelectedItem().toString();
	String zona = cbZona.getSelectedItem().toString();
	String tipoCalle = cbTipoDireccion.getSelectedItem().toString();
	String numero1 = txtNum1.getText();
	String numero2 = txtNum2.getText();
	String numero3 = txtNum3.getText();

	// conditionals if address numbers are empty print a warning msg
	if(numero1.isEmpty() || numero2.isEmpty()||numero3.isEmpty()){

	    JOptionPane.showMessageDialog(this, "La direccion no puede estar vacia.", "", JOptionPane.WARNING_MESSAGE);

	
	} else {

	    // get FK_ID  from nombreSucursal since its a not editable txtfield
	    String querySucursal = "SELECT FK_idDireccion FROM sucursal WHERE nombreSucursal = '" + nombreSucursal + "';";
	    System.out.println(querySucursal);

	    // first try and catch 
	    try {
		connection = conexion.getConnection();
		st = connection.createStatement();
		rs = st.executeQuery(querySucursal);

		// run a while to get the FK_Id from the db 
		while (rs.next()) {
		    //Store the FK_Id from sucursales into a variable for later use 
		    int idDireccion = rs.getInt("FK_idDireccion");
		    // print the Id Direccion value
		    System.out.println(idDireccion);

		    //Query to update since the FK_ID is the same as idDireccion we can reuse for the update of new address values
		    String queryUpdate = "UPDATE direccion SET `zona`='" + zona + "' ,`nombreDepartamento`= '" + departamento + "' ,`TipoCalle`= '" + tipoCalle + "' ,`numero1`= '" + numero1 + "',`numero2`= '" + numero2 + "',`numero3`= '" + numero3 + "' WHERE `idDireccion` = " + idDireccion + " ;";
		    System.out.println(queryUpdate);

		    // second try and catch to update direccion db
		    try {
			connection = conexion.getConnection();
			st = connection.createStatement();
			st.executeUpdate(queryUpdate);
			//Show msg when the address has been sucefully updated
			JOptionPane.showMessageDialog(this, "Se ha actualizado los de la sucursal ", "", JOptionPane.INFORMATION_MESSAGE);

			// inner conditional if the txt for a new branch name is empty just finish the window
			if (nuevaSucursal.isEmpty()) {

			    this.dispose();
			    // if the new branch name have something inside exe the the update query to change the nombreSucursal for a new one     
			} else {

			    //Since the FK_id and idDireccion are the same we're going to used it to selecct the row of the current sucursal and rename it
			    String querySucursalN = "UPDATE sucursal SET `nombreSucursal`='" + nuevaSucursal + "' WHERE FK_idDireccion = '" + idDireccion + "'; ";
			    System.out.println(querySucursalN);
			    // try and catch querySucursalN so it can connect to the db and change the old name and later on  finish the window
			    try {
				connection = conexion.getConnection();
				st = connection.createStatement();
				st.executeUpdate(querySucursalN);
				this.dispose();

			    } catch (SQLException e) {
				System.out.println(e);
			    }

			}

		    } catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "No fue posible actualizar los datos de la sucursal.", "", JOptionPane.ERROR_MESSAGE);

		    }

		}

	    } catch (SQLException e) {
		System.out.println(e);

	    }

	}

    }
   //deleted sucursal
    public void eliminarSucursal() {
	String nombreSucursal = txtSucursal.getText();

	String querySucursal = "SELECT idSucursal, idDireccion FROM sucursal INNER JOIN direccion WHERE FK_idDireccion = idDireccion AND nombreSucursal = '" + nombreSucursal + "';";
	System.out.println(querySucursal);
	try {
	    connection = conexion.getConnection();
	    st = connection.createStatement();
	    rs = st.executeQuery(querySucursal);

	    //Show msg when the registry of a new employee its successful 
	    while (rs.next()) {
		int idDireccion = rs.getInt("idDireccion");
		int idSucursal = rs.getInt("idSucursal");
		System.out.println(idDireccion);
		String queryDelAddress = "DELETE  FROM direccion WHERE idDireccion = '" + idDireccion + "';";
		String queryDelSucursal = "DELETE FROM sucursal WHERE  idSucursal = '"+ idSucursal +"';";

		System.out.println(queryDelAddress);
		System.out.println(queryDelSucursal);

		try {
		    st.executeUpdate(queryDelSucursal);
		    st.executeUpdate(queryDelAddress);
		    
		    //Show msg when the registry of a new employee its successful 
		    JOptionPane.showMessageDialog(this, "Se ha eliminado correctamente la Sucursal. ", "", JOptionPane.INFORMATION_MESSAGE);

		

		} catch (SQLException e) {
		    System.out.println(e);

		    JOptionPane.showMessageDialog(this, "No fue posible eliminar la sucursal.", "", JOptionPane.ERROR_MESSAGE);

		}

	    }

	} catch (SQLException e) {
	    System.out.println(e);

	}

    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtInfo1 = new javax.swing.JLabel();
        btnReturn = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        btnDelEmp = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtSucursal = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnUpdateEmp = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtNum3 = new javax.swing.JTextField();
        txtNum2 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtNum1 = new javax.swing.JTextField();
        cbTipoDireccion = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        cbZona = new javax.swing.JComboBox<>();
        cbDepartamento = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNewBranchN = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(183, 155, 250));

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Sucursal");

        txtInfo1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        txtInfo1.setForeground(new java.awt.Color(255, 255, 255));
        txtInfo1.setText("Editar sucursal ");

        btnReturn.setBackground(new java.awt.Color(81, 141, 173));
        btnReturn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReturnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnReturnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnReturnMouseExited(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Regresar");

        javax.swing.GroupLayout btnReturnLayout = new javax.swing.GroupLayout(btnReturn);
        btnReturn.setLayout(btnReturnLayout);
        btnReturnLayout.setHorizontalGroup(
            btnReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnReturnLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel8)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        btnReturnLayout.setVerticalGroup(
            btnReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnReturnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnDelEmp.setBackground(new java.awt.Color(218, 12, 86));
        btnDelEmp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDelEmpMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDelEmpMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDelEmpMouseExited(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Eliminar");

        javax.swing.GroupLayout btnDelEmpLayout = new javax.swing.GroupLayout(btnDelEmp);
        btnDelEmp.setLayout(btnDelEmpLayout);
        btnDelEmpLayout.setHorizontalGroup(
            btnDelEmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnDelEmpLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel9)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        btnDelEmpLayout.setVerticalGroup(
            btnDelEmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnDelEmpLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                .addContainerGap())
        );

        txtSucursal.setEditable(false);
        txtSucursal.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/edit.png"))); // NOI18N

        btnUpdateEmp.setBackground(new java.awt.Color(81, 141, 173));
        btnUpdateEmp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUpdateEmpMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnUpdateEmpMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnUpdateEmpMouseExited(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Actualizar");

        javax.swing.GroupLayout btnUpdateEmpLayout = new javax.swing.GroupLayout(btnUpdateEmp);
        btnUpdateEmp.setLayout(btnUpdateEmpLayout);
        btnUpdateEmpLayout.setHorizontalGroup(
            btnUpdateEmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnUpdateEmpLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel11)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        btnUpdateEmpLayout.setVerticalGroup(
            btnUpdateEmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnUpdateEmpLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel13.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("-");

        jLabel14.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("#");

        cbTipoDireccion.setModel(modelEnumTipoCalles);

        jLabel15.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Tipo calle");

        jLabel16.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Zona");

        cbZona.setModel(modelEnumZonas);

        cbDepartamento.setModel(modelEnumDepartamentos);

        jLabel17.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Departamento");

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Nuevo nombre sucursal");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(txtInfo1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(104, 104, 104)
                .addComponent(btnDelEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel6)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNewBranchN, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbZona, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnUpdateEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbTipoDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtNum1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel14)
                                        .addGap(10, 10, 10)
                                        .addComponent(txtNum2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btnReturn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNum3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(144, 144, 144)))
                .addContainerGap(114, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDelEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(231, 231, 231))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtInfo1)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtNewBranchN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(cbDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)
                            .addComponent(cbZona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(cbTipoDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNum1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNum2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNum3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(jLabel13))
                        .addGap(75, 75, 75)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnUpdateEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReturn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReturnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReturnMouseClicked
	this.dispose();
    }//GEN-LAST:event_btnReturnMouseClicked

    private void btnReturnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReturnMouseEntered
	//hover effect button  enter
	btnReturn.setBackground(new Color(17, 204, 250));
    }//GEN-LAST:event_btnReturnMouseEntered

    private void btnReturnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReturnMouseExited
	// hover effect button exit
	btnReturn.setBackground(new Color(81, 141, 173));
    }//GEN-LAST:event_btnReturnMouseExited

    private void btnDelEmpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDelEmpMouseClicked
	eliminarSucursal();
    }//GEN-LAST:event_btnDelEmpMouseClicked

    private void btnDelEmpMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDelEmpMouseEntered
	//hover effect button  enter
	btnDelEmp.setBackground(new Color(17, 204, 250));
    }//GEN-LAST:event_btnDelEmpMouseEntered

    private void btnDelEmpMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDelEmpMouseExited
	// hover effect button exit
	btnDelEmp.setBackground(new Color(218, 12, 86));
    }//GEN-LAST:event_btnDelEmpMouseExited

    private void btnUpdateEmpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateEmpMouseClicked
	actualizarSucursal();
    }//GEN-LAST:event_btnUpdateEmpMouseClicked

    private void btnUpdateEmpMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateEmpMouseEntered
	//hover effect button  enter
	btnUpdateEmp.setBackground(new Color(17, 204, 250));
    }//GEN-LAST:event_btnUpdateEmpMouseEntered

    private void btnUpdateEmpMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateEmpMouseExited
	// hover effect button exit
	btnUpdateEmp.setBackground(new Color(81, 141, 173));
    }//GEN-LAST:event_btnUpdateEmpMouseExited

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
	} catch (ClassNotFoundException ex) {
	    java.util.logging.Logger.getLogger(ShowBranchForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (InstantiationException ex) {
	    java.util.logging.Logger.getLogger(ShowBranchForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (IllegalAccessException ex) {
	    java.util.logging.Logger.getLogger(ShowBranchForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (javax.swing.UnsupportedLookAndFeelException ex) {
	    java.util.logging.Logger.getLogger(ShowBranchForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	}
	//</editor-fold>

	/* Create and display the dialog */
	java.awt.EventQueue.invokeLater(new Runnable() {
	    public void run() {
		ShowBranchForm dialog = new ShowBranchForm(new javax.swing.JFrame(), true);
		dialog.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent e) {
			System.exit(0);
		    }
		});
		dialog.setVisible(true);
	    }
	});
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnDelEmp;
    private javax.swing.JPanel btnReturn;
    private javax.swing.JPanel btnUpdateEmp;
    private javax.swing.JComboBox<String> cbDepartamento;
    private javax.swing.JComboBox<String> cbTipoDireccion;
    private javax.swing.JComboBox<String> cbZona;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel txtInfo1;
    private javax.swing.JTextField txtNewBranchN;
    private javax.swing.JTextField txtNum1;
    private javax.swing.JTextField txtNum2;
    private javax.swing.JTextField txtNum3;
    private javax.swing.JTextField txtSucursal;
    // End of variables declaration//GEN-END:variables
}
