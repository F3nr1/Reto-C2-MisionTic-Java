package Principal;
import Vistas.*;
import Modelo.*;
/**
 *
 * @author Carlos
 */
public class Main {
    
    public static void main(String[] args) {
	     // get connection to db 
	      Conexion conexiondb = new Conexion();
		    // login Jframe ini 
        Login login = new Login();
        // Login Jframe visible true
        login.setVisible(true);
    }
}
