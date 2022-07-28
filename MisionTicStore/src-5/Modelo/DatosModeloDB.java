package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Datos Model db get info from data base before exe Jdialog
 * @author Carlos
 */

public class DatosModeloDB {
 // get connection to db
    Conexion conexion = new Conexion();
    Connection connection;
    Statement st;
    PreparedStatement pst;
    ResultSet rs;
    DatosSucursalPuestoTrabajo datosDB;

    // arraylist to get all info from the sucursal table

    public ArrayList<DatosSucursalPuestoTrabajo> getSucursales() {
        ArrayList listaSucursales = new ArrayList();
	
        String query = "SELECT idSucursal, nombreSucursal FROM sucursal;";
	//trycatch 
        try {
            connection = conexion.getConnection();
	    //preload a query to keep some data for the fill of cb sucursal
            pst = connection.prepareStatement(query);
	    //check pts gets a value 
	    System.out.println("PST sucursal: "+pst);
            rs = pst.executeQuery();
	    
	    // while to store info and idSUcursal
            while (rs.next()) {
                datosDB = new DatosSucursalPuestoTrabajo();
                int idSucursalC = rs.getInt("idSucursal");
		System.out.println(idSucursalC);
                String nombreSucursal = rs.getString("nombreSucursal");
                datosDB.setIdSucursal(idSucursalC);
                datosDB.setNombreSucursal(nombreSucursal);
                listaSucursales.add(datosDB);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        System.out.println("Sucursales lista: " + listaSucursales);
	//return value from table sucursal 
        return listaSucursales;
    }
    
    //array list to get PuestoTrabajo by using idSucursal from getSucursales
     public ArrayList<DatosSucursalPuestoTrabajo> getPuestosTrabajos(int idSucursal) {
        
        ArrayList listPuestosTrabajo = new ArrayList();
	// query for get values of the puestoTrabajo table by using the idSucursal already retrived.
        // in this case the value  of idSucursal is not set in the query but it will way when its add.
	String queryPuestoTrabajo = "SELECT idPuestoTrabajo, nombrePuestoTrabajo, salario, FK_idSucursal FROM puestotrabajo INNER JOIN sucursal ON (idSucursal = FK_idSucursal) WHERE idSucursal = ? ;";
        //print query
	System.out.println(queryPuestoTrabajo);
	//trycatch to connect to db
	try {
            connection = conexion.getConnection();
            pst = connection.prepareStatement(queryPuestoTrabajo);
	    System.out.println(pst);
	    //set the value of idSucursal from the already retrived one from getSucursales
            pst.setInt(1,idSucursal);
	    //check pst has a value in the output query
	    System.out.println("pst int 1: "+pst);
            rs = pst.executeQuery();
	    System.out.println("rs: "+rs);
	    //Loop to store all de db info into a list
            while (rs.next()) {
                datosDB = new DatosSucursalPuestoTrabajo();
                int idPuestoTrabajo = rs.getInt("idPuestoTrabajo");
                String nombrePuestoTrabajo = rs.getString("nombrePuestoTrabajo");
                float salario = rs.getFloat("salario");
                datosDB.setIdPuestoTrabajo(idPuestoTrabajo);
                datosDB.setNombrePuestoTrabajo(nombrePuestoTrabajo);
                datosDB.setSalario(salario);
                listPuestosTrabajo.add(datosDB);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        System.out.println("Puestos de trabajo" + listPuestosTrabajo);
        return listPuestosTrabajo;
    }
}
