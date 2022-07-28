package Controlador;

import Modelo.Conexion;
import Modelo.DatosModeloDB;
import Modelo.DatosSucursalPuestoTrabajo;
import Modelo.Sucursal;
import Vistas.AddUserForm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;


/**
 * Controller Sucursal Ocupacion
 * @author Carlos
 */
// set the public class implementing a listener so it can catch when a value in cbSucursal Change
public class ControllerSucursalPuestoTrabajo implements ActionListener{
    //exe commands on the AddUserForm by using view 
    public final AddUserForm view;
    Conexion conexion = new Conexion();
    Connection connection;
    Statement st;
    PreparedStatement pst;
    ResultSet rs;
    // define the arraylist and models to load into info on cbSucursal cbOcupacion
    ArrayList<DatosSucursalPuestoTrabajo> list;
    DatosModeloDB model = new DatosModeloDB();
    
    
   
 
    public ControllerSucursalPuestoTrabajo(AddUserForm view) {
        this.view = view;
	
        this.getListaSucursales();
	//select  the item on cbSucursal for later on get it's id to populate the cbOcupacion
        Sucursal sucursal = (Sucursal) view.cbSucursal.getSelectedItem();
        getListaPuestosTrabajo(sucursal.getIdSucursal());
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
    //populate cbOcupacion and gets it's id
     public final void getListaPuestosTrabajo(int idSucursal){
        list = model.getPuestosTrabajos(idSucursal);
        if(list.size() > 0){
            for (int i = 0; i < list.size(); i++) {
                DefaultComboBoxModel model = (DefaultComboBoxModel)view.cbOcupacion.getModel();
                Object [] puestoTrabajo = new Object[2];
                puestoTrabajo[0]  = list.get(i).getIdPuestoTrabajo();
                puestoTrabajo[1] = list.get(i).getNombrePuestoTrabajo();
                model.addElement(puestoTrabajo[1]);
                view.cbOcupacion.setModel(model);
                System.out.println(model);
            }
        }else{
            JOptionPane.showMessageDialog(null, "No existen puestos de trabajo aún.", "Sucursales", JOptionPane.WARNING_MESSAGE);
        }
    }
   
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object evento = ae.getSource();
	//conditional if cbSucursal gets something selected  
      if(evento.equals(view.cbSucursal)){
	    //remove all cbOcupacion items  if the cbSucursal selection changes
            view.cbOcupacion.removeAllItems();
	    //get the new item selected on cbSucursal
            Sucursal sucursal = (Sucursal)view.cbSucursal.getSelectedItem();
	    // send the new idSucursal to populate the cbOcupacion with the related items from the idSucursal
            getListaPuestosTrabajo(sucursal.getIdSucursal());
        }
    }
}
