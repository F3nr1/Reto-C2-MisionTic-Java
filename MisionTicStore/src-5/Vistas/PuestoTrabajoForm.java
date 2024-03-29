/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Modelo.*;
import Controlador.CbSucursal;


import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.ComboBoxModel;

import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Carlos
 */
public class PuestoTrabajoForm extends javax.swing.JDialog {

     //Instance ComboBoxMopdel
    ComboBoxModel CbSucural;

    //Variables Sql connection
    Conexion conexion = new Conexion();
    Connection connection;
    Statement st;
    ResultSet rs;
    ArrayList mListaSucursales;
    CbSucursal cbSucursales;
   
    
    
    public PuestoTrabajoForm(java.awt.Frame parent, boolean modal) {
	super(parent, modal);
	initComponents();
	this.setLocationRelativeTo(parent);
	cbSucursales = new CbSucursal();
        
	mListaSucursales = new ArrayList();
	
	

	LlenarComboBoxSucursales();
	
    }
    
    public String LlenarComboBoxSucursales() {

	mListaSucursales = cbSucursales.getListaSucursales();

	Iterator iterator = mListaSucursales.iterator();

	while (iterator.hasNext()) {
	    Sucursal sucursal = (Sucursal) iterator.next();
	    cbSucursal.addItem(sucursal);

	}

	String nombreSucursal = cbSucursal.getSelectedItem().toString();

	String queryNombreSucursal = "SELECT idSucursal FROM sucursal WHERE nombreSucursal = '" + nombreSucursal + "';";

	return queryNombreSucursal;

    }
    
    
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        txtInfo1 = new javax.swing.JLabel();
        txtSalario = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnSaveNewEmp = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        btnCancelNewEmp = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        cbSucursal = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtNewOcupacion = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        cbPuestoTrabajo = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Creación de cargos");

        bg.setBackground(new java.awt.Color(183, 155, 250));
        bg.setPreferredSize(new java.awt.Dimension(637, 452));

        txtInfo1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        txtInfo1.setForeground(new java.awt.Color(255, 255, 255));
        txtInfo1.setText("Formulario de creacion cargo");

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Salario");

        btnSaveNewEmp.setBackground(new java.awt.Color(81, 141, 173));
        btnSaveNewEmp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSaveNewEmpMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSaveNewEmpMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSaveNewEmpMouseExited(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Guardar");

        javax.swing.GroupLayout btnSaveNewEmpLayout = new javax.swing.GroupLayout(btnSaveNewEmp);
        btnSaveNewEmp.setLayout(btnSaveNewEmpLayout);
        btnSaveNewEmpLayout.setHorizontalGroup(
            btnSaveNewEmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSaveNewEmpLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel8)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        btnSaveNewEmpLayout.setVerticalGroup(
            btnSaveNewEmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnSaveNewEmpLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnCancelNewEmp.setBackground(new java.awt.Color(218, 12, 86));
        btnCancelNewEmp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelNewEmpMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCancelNewEmpMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCancelNewEmpMouseExited(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Roboto", 0, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Cancelar");

        javax.swing.GroupLayout btnCancelNewEmpLayout = new javax.swing.GroupLayout(btnCancelNewEmp);
        btnCancelNewEmp.setLayout(btnCancelNewEmpLayout);
        btnCancelNewEmpLayout.setHorizontalGroup(
            btnCancelNewEmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnCancelNewEmpLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel9)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        btnCancelNewEmpLayout.setVerticalGroup(
            btnCancelNewEmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnCancelNewEmpLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel10.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Sucursal");

        jLabel11.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Cargo");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/employee64.png"))); // NOI18N

        jLabel12.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Otro cargo");

        cbPuestoTrabajo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " Domiciliario", " Gerente", " Operario" }));

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(txtInfo1))
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGap(267, 267, 267)
                        .addComponent(jLabel1))
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbSucursal, 0, 247, Short.MAX_VALUE)
                                    .addComponent(cbPuestoTrabajo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(bgLayout.createSequentialGroup()
                                .addComponent(btnSaveNewEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(84, 84, 84)
                                .addComponent(btnCancelNewEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(bgLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSalario, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(121, 121, 121))
                            .addGroup(bgLayout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNewOcupacion)))))
                .addGap(124, 124, 124))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(txtInfo1)
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGap(39, 39, 39)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(10, 10, 10)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(cbPuestoTrabajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNewOcupacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSalario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(45, 45, 45)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCancelNewEmp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSaveNewEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelNewEmpMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelNewEmpMouseExited
        // hover effect button exit
        btnCancelNewEmp.setBackground(new Color(218, 12, 86));
    }//GEN-LAST:event_btnCancelNewEmpMouseExited

    private void btnCancelNewEmpMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelNewEmpMouseEntered
        //hover effect button  enter
        btnCancelNewEmp.setBackground(new Color(17, 204, 250));
    }//GEN-LAST:event_btnCancelNewEmpMouseEntered

    private void btnCancelNewEmpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelNewEmpMouseClicked
        this.dispose();
    }//GEN-LAST:event_btnCancelNewEmpMouseClicked

    private void btnSaveNewEmpMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveNewEmpMouseExited
        // hover effect button exit
        btnSaveNewEmp.setBackground(new Color(81, 141, 173));
    }//GEN-LAST:event_btnSaveNewEmpMouseExited

    private void btnSaveNewEmpMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveNewEmpMouseEntered
        //hover effect button  enter
        btnSaveNewEmp.setBackground(new Color(17, 204, 250));
    }//GEN-LAST:event_btnSaveNewEmpMouseEntered

    private void btnSaveNewEmpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveNewEmpMouseClicked
    String puestoT = (String) cbPuestoTrabajo.getSelectedItem();
    String queryNombreSucursal = LlenarComboBoxSucursales();
    
    String nOcupacion = txtNewOcupacion.getText();
    String salario = txtSalario.getText();
    
    
    if(nOcupacion.isEmpty()){
	
	try {
	    
	    
		connection = conexion.getConnection();

		st = connection.createStatement();
		rs = st.executeQuery(queryNombreSucursal);
		
		while (rs.next()) {
		    
		    int idSucursal = rs.getInt("idSucursal");
		    
		     String queryCrearEmpleado = "INSERT INTO `puestotrabajo`( `nombrePuestoTrabajo`, `salario`, `FK_idSucursal`) VALUES ('"+puestoT+"','"+salario+"','"+idSucursal+"');";
		    
		
		    try {
			connection = conexion.getConnection();
			st = connection.createStatement();
			st.executeUpdate(queryCrearEmpleado);
			//Show msg when the registry of a new employee its successful 
			JOptionPane.showMessageDialog(this, "Se ha asociado correctamento el puesto de trabajo.", "Nuevo puesto de trabajo", JOptionPane.INFORMATION_MESSAGE);

		    } catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Error no se pudo asociar el puesto de trabajo a la sucursal", "No se pudo crear el puesto de trabajo", JOptionPane.ERROR_MESSAGE);
			System.out.println(queryCrearEmpleado);
			System.out.println(e);
		    }
		}
	    
	} catch (SQLException e) {
	}
	this.dispose();
    }else{
	
	try {
	    
	    
		connection = conexion.getConnection();

		st = connection.createStatement();
		rs = st.executeQuery(queryNombreSucursal);
		
		while (rs.next()) {
		    
		    int idSucursal = rs.getInt("idSucursal");
		    
		     String queryCrearEmpleado = "INSERT INTO `puestotrabajo`( `nombrePuestoTrabajo`, `salario`, `FK_idSucursal`) VALUES ('"+nOcupacion+"','"+salario+"','"+idSucursal+"');";
		    
		
		    try {
			connection = conexion.getConnection();
			st = connection.createStatement();
			st.executeUpdate(queryCrearEmpleado);
			//Show msg when the registry of a new employee its successful 
			JOptionPane.showMessageDialog(this, "Se ha creado el nuevo puesto de trabajo en la sucursal. ", "Nuevo puesto de trabajo", JOptionPane.INFORMATION_MESSAGE);

		    } catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Error no se pudo agregar el nuevo puesto de trabajo a la sucursal", "No se pudo crear el puesto de trabajo", JOptionPane.ERROR_MESSAGE);
			System.out.println(queryCrearEmpleado);
			System.out.println(e);
		    }
		}
	    
	} catch (SQLException e) {
	}
	this.dispose();
	
	
    }
    
    
    
    
    
    
    
      
    }//GEN-LAST:event_btnSaveNewEmpMouseClicked

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
	    java.util.logging.Logger.getLogger(PuestoTrabajoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (InstantiationException ex) {
	    java.util.logging.Logger.getLogger(PuestoTrabajoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (IllegalAccessException ex) {
	    java.util.logging.Logger.getLogger(PuestoTrabajoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (javax.swing.UnsupportedLookAndFeelException ex) {
	    java.util.logging.Logger.getLogger(PuestoTrabajoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	}
	//</editor-fold>

	/* Create and display the dialog */
	java.awt.EventQueue.invokeLater(new Runnable() {
	    public void run() {
		PuestoTrabajoForm dialog = new PuestoTrabajoForm(new javax.swing.JFrame(), true);
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
    private javax.swing.JPanel bg;
    private javax.swing.JPanel btnCancelNewEmp;
    private javax.swing.JPanel btnSaveNewEmp;
    private javax.swing.JComboBox<String> cbPuestoTrabajo;
    private javax.swing.JComboBox<Sucursal> cbSucursal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel txtInfo1;
    private javax.swing.JTextField txtNewOcupacion;
    private javax.swing.JTextField txtSalario;
    // End of variables declaration//GEN-END:variables
}
