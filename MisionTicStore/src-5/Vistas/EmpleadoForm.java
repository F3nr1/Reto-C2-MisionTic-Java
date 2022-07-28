
package Vistas;

import Controlador.CbSucursal;
import Controlador.ControllerEmpleadoSucursal;
import Controlador.ControllerSucursalPuestoTrabajo;
import Modelo.*;
import java.awt.Color;
import java.sql.Connection;

import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Color;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.ComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Employee Form List only shows the info from a sucursal and their employees including name and salary
 * @author Carlos
 */
public class EmpleadoForm extends javax.swing.JDialog {
    ControllerEmpleadoSucursal controller;

    //Variables Sql connection
    Conexion conexion = new Conexion();
    Connection connection;
    Statement st;
    ResultSet rs;
   
    ArrayList mListaSucursales;
    CbSucursal cbSucursales;
    
    
    public EmpleadoForm(java.awt.Frame parent, boolean modal) {
	super(parent, modal);
	initComponents();
	this.setLocationRelativeTo(parent);
	
	// exe the controllerEMpleados so it can populate cbSucursal and tblEmpleados
        controller = new ControllerEmpleadoSucursal(this);
	
    }
   
     
    
    
    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        bg = new javax.swing.JPanel();
        txtInfo1 = new javax.swing.JLabel();
        btnCancelNewEmp = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        cbSucursal = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEmpleados = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Lista de empleados por sucursal");

        bg.setBackground(new java.awt.Color(183, 155, 250));
        bg.setPreferredSize(new java.awt.Dimension(637, 452));

        txtInfo1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        txtInfo1.setForeground(new java.awt.Color(255, 255, 255));
        txtInfo1.setText("Empleados Sucursales");

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
        jLabel9.setText("Cerrar");

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
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel10.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Sucursal");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/employee64.png"))); // NOI18N

        tblEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sucursal", "Ocupacion", "Empleado", "Salario"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblEmpleados);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(txtInfo1))
                    .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnCancelNewEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 677, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(bgLayout.createSequentialGroup()
                                .addGap(232, 232, 232)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(bgLayout.createSequentialGroup()
                                .addGap(354, 354, 354)
                                .addComponent(jLabel1)))))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(txtInfo1)
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(36, 36, 36)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(btnCancelNewEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 798, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, 569, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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

    private void btnCancelNewEmpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelNewEmpMouseClicked
        this.dispose();
    }//GEN-LAST:event_btnCancelNewEmpMouseClicked

    private void btnCancelNewEmpMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelNewEmpMouseEntered
        //hover effect button  enter
        btnCancelNewEmp.setBackground(new Color(17, 204, 250));
    }//GEN-LAST:event_btnCancelNewEmpMouseEntered

    private void btnCancelNewEmpMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelNewEmpMouseExited
        // hover effect button exit
        btnCancelNewEmp.setBackground(new Color(218, 12, 86));
    }//GEN-LAST:event_btnCancelNewEmpMouseExited

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
	    java.util.logging.Logger.getLogger(EmpleadoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (InstantiationException ex) {
	    java.util.logging.Logger.getLogger(EmpleadoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (IllegalAccessException ex) {
	    java.util.logging.Logger.getLogger(EmpleadoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (javax.swing.UnsupportedLookAndFeelException ex) {
	    java.util.logging.Logger.getLogger(EmpleadoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	}
	//</editor-fold>

	/* Create and display the dialog */
	java.awt.EventQueue.invokeLater(new Runnable() {
	    public void run() {
		EmpleadoForm dialog = new EmpleadoForm(new javax.swing.JFrame(), true);
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
    public javax.swing.JComboBox<Object> cbSucursal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tblEmpleados;
    private javax.swing.JLabel txtInfo1;
    // End of variables declaration//GEN-END:variables
}
