/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Conexion;
import Modelo.Sucursal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Carlos
 */
public class CbSucursal {
    
    //Variables Sql connection
     Conexion conexion = new Conexion();
    Connection connection;
    Statement st;
    ResultSet rs;
    
    
    public CbSucursal(){
	
	
	
    }
    
    
    public ArrayList getListaSucursales(){
    System.out.println("Funcion: get list Cb sucursal");
    ArrayList mListSucursales = new ArrayList();
    Sucursal sucursal = null;
    
    try {
	   connection = conexion.getConnection();
	    st = connection.createStatement();
	    
	    rs = st.executeQuery("SELECT idSucursal, nombreSucursal From sucursal;");
	    
	    while(rs.next()){
		sucursal = new Sucursal();
		sucursal.setIdSucursal(rs.getInt("idSucursal"));
		sucursal.setNombreSucursal(rs.getString("nombreSucursal"));
		mListSucursales.add(sucursal);
		
	    }
	
	
	
	} catch (SQLException e) {
	    
	    System.out.println(e);
	    
	    
	    
	    
	}
    
    return mListSucursales;
    
}
	
    
    
    
    
    
}
