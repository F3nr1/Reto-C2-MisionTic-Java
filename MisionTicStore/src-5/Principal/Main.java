/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import Vistas.*;
import Modelo.Conexion;

/**
 *
 * @author Carlos
 */
public class Main {

    public static void main(String[] args) {

	Conexion conexiondb = new Conexion();
	conexiondb.getConnection();
	Login login = new Login();
	
	

	login.setVisible(true);
    }
}
