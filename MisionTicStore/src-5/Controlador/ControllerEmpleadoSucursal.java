/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Conexion;
import Modelo.DatosModeloDB;
import Modelo.DatosSucursalPuestoTrabajo;
import Modelo.Sucursal;
import Vistas.EmpleadoForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *Controller empleadoForm dynamic selector to populate tblEmpleados from the id of the item selected in cbSucural
 * @author Carlos
 */
public class ControllerEmpleadoSucursal implements ActionListener{
    //set the public class implementing a listener so it can catch when a value in cbSucursal Change
    public final EmpleadoForm view;
    Conexion conexion = new Conexion();
    Connection connection;
    Statement st;
    PreparedStatement pst;
    ResultSet rs;
    // define the arraylist and models to load into info on cbSucursal  and populate tblEmpleados
    ArrayList<DatosSucursalPuestoTrabajo> list;
    DefaultTableModel contenidoTablaEmpleados;
    DatosModeloDB model = new DatosModeloDB();
    
    public ControllerEmpleadoSucursal(EmpleadoForm view) {
        this.view = view;
        this.getListaSucursales();
	//select  the item on cbSucursal for later on get it's id to populate the cbOcupacion
        Sucursal sucursal = (Sucursal) view.cbSucursal.getSelectedItem();
        listarEmpleados(sucursal.getIdSucursal());
	// start events listener when a click on the Jdialog has happen so it can start capture idSucursal
        events();
    }
     //event listener when cbSucursal gets a click from user
      public final void events(){
        view.cbSucursal.addActionListener(this);
    }
     //populate the cbSucursal and get it's id
      public final void getListaSucursales(){
        list = model.getSucursales();
	
	System.out.println("ListaSucursal: " + list.size());
        if(list.size() > 0){
            for (int i = 0; i < list.size(); i++) {
                int idSucursal  = list.get(i).getIdSucursal();
                String nombreSucursal = list.get(i).getNombreSucursal();
                System.out.println(idSucursal + " - " + nombreSucursal);
                view.cbSucursal.addItem(new Sucursal(idSucursal, nombreSucursal));
            }
        }else{
            JOptionPane.showMessageDialog(null, "No existen sucursales aún.", "Sucursales", JOptionPane.WARNING_MESSAGE);
        }
    }
     // from the idSucural from getListaSucursal run a query to populate tblEmpleados using it 
      private void listarEmpleados(int idSucursal) {
        // new variable name for idSucursal so it can be use on the query
	 int id = idSucursal;
	    //query get all the values from the Employee by using the id Sucursal 
	    String queryDepartamentos = "SELECT nombreSucursal , nombrePuestoTrabajo, nombreEmp ,apellidos, CONCAT ('$',salario) AS salario FROM sucursal INNER JOIN puestotrabajo ON sucursal.idSucursal = puestotrabajo.FK_idSucursal INNER JOIN empleado ON puestotrabajo.idPuestoTrabajo = empleado.FK_idPuestoTrabajo WHERE idSucursal = '"+id+"';";
	    System.out.println(queryDepartamentos);
	//trycatch to populate items inside the table from the results of the queryDepartamentos    
	try {
	    connection = conexion.getConnection();
	    st = connection.createStatement();
	    // Exe the query from the variable queryDepartamentos
	    rs = st.executeQuery(queryDepartamentos);
	    
	    // Object to get all elements from the data base to a jTable
	    Object[] empleados = new Object[4];
	    contenidoTablaEmpleados = (DefaultTableModel) view.tblEmpleados.getModel();
	    // While loop to run every element from the query

	    while (rs.next()) {
		empleados[0] = rs.getString("nombreSucursal");
		empleados[1] = rs.getString("nombrePuestoTrabajo");
                empleados[2] = rs.getString("nombreEmp")+" "+rs.getString("apellidos");
		empleados[3] = rs.getString("salario");
		// making a new row on the jtable for each employee
		contenidoTablaEmpleados.addRow(empleados);
		//Sout to print each employee data 
		System.out.println("Sucursal: " + empleados[0] + " , Departamento: " + empleados[1]);
		view.tblEmpleados.setModel(contenidoTablaEmpleados);
	    }

	} catch (SQLException e) {

	    System.out.println("Error de creación de tabla de departamentos");
	    System.out.println(e);
	}
	
	

    }
    //delete elements on the tblEmpleados
    public void borrarDatosTablaEmpleados() {
	// for loop to deleted each element  on each row inside the table 
	for (int i = 0; i < view.tblEmpleados.getRowCount(); i++) {
	    //removing each row inside the table with removeRow
	    contenidoTablaEmpleados.removeRow(i);
	    i -= 1;
	}

    }
      
      
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object evento = ae.getSource();
    //conditional if cbSucursal gets something selected  
      if(evento.equals(view.cbSucursal)){
	  //first deleted the elements in tblEmpleados
            borrarDatosTablaEmpleados();
	    //get the new item selected on cbSucursal
            Sucursal sucursal = (Sucursal)view.cbSucursal.getSelectedItem();
	    // send the new idSucural to listarEmpleados and populate again the tblEmpleados
            listarEmpleados(sucursal.getIdSucursal());
        }
    } 
   
    
}
