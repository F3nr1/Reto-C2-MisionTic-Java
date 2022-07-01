package Vistas;

import java.awt.Color;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        bg = new javax.swing.JPanel();
        Minticlogo = new javax.swing.JLabel();
        Usuario = new javax.swing.JLabel();
        Welcome = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        txtUserName = new javax.swing.JTextField();
        Contrasena = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        txtUserPass = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JPanel();
        SignIn = new javax.swing.JLabel();
        btnResetPass = new javax.swing.JPanel();
        resetPass = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MisionTic Store ");
        setLocationByPlatform(true);
        setResizable(false);

        bg.setBackground(new java.awt.Color(130, 208, 250));

        Minticlogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/logo-mision-store.png"))); // NOI18N

        Usuario.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        Usuario.setForeground(new java.awt.Color(255, 255, 255));
        Usuario.setText("Usuario");

        Welcome.setFont(new java.awt.Font("Roboto", 1, 48)); // NOI18N
        Welcome.setForeground(new java.awt.Color(255, 255, 255));
        Welcome.setText("Bienvenido");

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));

        txtUserName.setBackground(new java.awt.Color(130, 208, 250));
        txtUserName.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtUserName.setForeground(new java.awt.Color(102, 102, 102));
        txtUserName.setText("Ingrese su nombre de usuario");
        txtUserName.setBorder(null);
        txtUserName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtUserNameMousePressed(evt);
            }
        });
        txtUserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserNameActionPerformed(evt);
            }
        });

        Contrasena.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        Contrasena.setForeground(new java.awt.Color(255, 255, 255));
        Contrasena.setText("Contraseña");

        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));

        txtUserPass.setBackground(new java.awt.Color(130, 208, 250));
        txtUserPass.setForeground(new java.awt.Color(102, 102, 102));
        txtUserPass.setText("********");
        txtUserPass.setBorder(null);
        txtUserPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtUserPassMousePressed(evt);
            }
        });

        btnLogin.setBackground(new java.awt.Color(81, 141, 173));
        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLoginMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLoginMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLoginMouseExited(evt);
            }
        });

        SignIn.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        SignIn.setForeground(new java.awt.Color(255, 255, 255));
        SignIn.setText("Ingresar");

        javax.swing.GroupLayout btnLoginLayout = new javax.swing.GroupLayout(btnLogin);
        btnLogin.setLayout(btnLoginLayout);
        btnLoginLayout.setHorizontalGroup(
            btnLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnLoginLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(SignIn)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        btnLoginLayout.setVerticalGroup(
            btnLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(SignIn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnResetPass.setBackground(new java.awt.Color(81, 141, 173));
        btnResetPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnResetPassMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnResetPassMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnResetPassMouseExited(evt);
            }
        });

        resetPass.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        resetPass.setForeground(new java.awt.Color(255, 255, 255));
        resetPass.setText("Recuperar Clave");
        resetPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                resetPassMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout btnResetPassLayout = new javax.swing.GroupLayout(btnResetPass);
        btnResetPass.setLayout(btnResetPassLayout);
        btnResetPassLayout.setHorizontalGroup(
            btnResetPassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnResetPassLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(resetPass)
                .addGap(18, 18, 18))
        );
        btnResetPassLayout.setVerticalGroup(
            btnResetPassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnResetPassLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(resetPass)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGap(274, 274, 274)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Welcome)
                            .addComponent(Minticlogo)))
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGap(243, 243, 243)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtUserName)
                            .addComponent(Contrasena, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(btnResetPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtUserPass)
                            .addComponent(Usuario, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2))))
                .addContainerGap(249, Short.MAX_VALUE))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(Minticlogo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Welcome)
                .addGap(46, 46, 46)
                .addComponent(Usuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Contrasena)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUserPass, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnResetPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(74, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    private void btnLoginMouseEntered(java.awt.event.MouseEvent evt) {                                      
         //Hover effect button  
	btnLogin.setBackground(new Color(52,89,110));
    }                                     

    private void txtUserNameActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void btnLoginMouseExited(java.awt.event.MouseEvent evt) {                                     
         //Hover effect button  
	btnLogin.setBackground( new Color(81,141,173));
    }                                    

    private void txtUserNameMousePressed(java.awt.event.MouseEvent evt) {                                         
         // When txtUserName is press by  user empty the field  and change font color to white while the user write something
	if(txtUserName.getText().equals("Ingrese su nombre de usuario")){
             txtUserName.setText("");
             txtUserName.setForeground(Color.white);
            
        }// if the txtUserPass is empty write on the field a msg so the user know what to do on grey color 
        if (String.valueOf(txtUserPass.getPassword()).isEmpty()){
                txtUserPass.setText("********");
                txtUserPass.setForeground(Color.gray);
        }
       
       
    }                                        

    private void txtUserPassMousePressed(java.awt.event.MouseEvent evt) {                                         
          // When txtUserPass is press by  user empty the field  and change font color to white while the user write something
	if(String.valueOf(txtUserPass.getPassword()).equals("********")){
                txtUserPass.setText("");
                txtUserPass.setForeground(Color.white);
          } // if the txtUserName is empty write on the field a msg so the user know what to do on grey color 
         if (txtUserName.getText().isEmpty()){
                txtUserName.setText("Ingrese su nombre de usuario");
                txtUserName.setForeground(Color.gray);
           }
          
         
    }                                        

    private void btnLoginMouseClicked(java.awt.event.MouseEvent evt) {                                      
        //1. Getting data from txt fields and password fields 
        String username = txtUserName.getText();
        String password = txtUserPass.getText();
        //2. review data on console 
        System.out.println("Username: " + username + ", password: " + password);
        //3. check that theres not empty fields  and show msg 
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Hay campos sin diligenciar", "Información", JOptionPane.WARNING_MESSAGE);
        /// if pass and user fields have something check for the user name and password equals to 
        } else {
            if (username.equals("MisionTIC") && password.equals("Ciclo2")) {
                JOptionPane.showMessageDialog(null, "Bienvenido al sistema Misión TIC");
                //open the user menu Jframe
                UserMenu usermenu = new UserMenu();
                usermenu.setVisible(rootPaneCheckingEnabled);
                //kill the login Jframe
                this.dispose();
            } // else if the user and password doesn't  match send a error JOptionPane
	    else {
                JOptionPane.showMessageDialog(null, "Error al iniciar sesión", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }                                     

    private void btnResetPassMouseClicked(java.awt.event.MouseEvent evt) {                                          
             // run  change pass form Jform
	     ChangePassForm  changePassF = new ChangePassForm (this, rootPaneCheckingEnabled);
	     changePassF.setVisible(rootPaneCheckingEnabled);
    }                                         

    private void btnResetPassMouseEntered(java.awt.event.MouseEvent evt) {                                          
              // Hover effect button
	     btnResetPass.setBackground(new Color(52,89,110));
    }                                         

    private void btnResetPassMouseExited(java.awt.event.MouseEvent evt) {                                         
               // Hover effect button
	      btnResetPass.setBackground( new Color(81,141,173));
    }                                        

    private void resetPassMouseEntered(java.awt.event.MouseEvent evt) {                                       
        // TODO add your handling code here:
    }                                      

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
			new Login().setVisible(true);
		});
    }

    // Variables declaration - do not modify                     
    private javax.swing.JLabel Contrasena;
    private javax.swing.JLabel Minticlogo;
    private javax.swing.JLabel SignIn;
    private javax.swing.JLabel Usuario;
    private javax.swing.JLabel Welcome;
    private javax.swing.JPanel bg;
    private javax.swing.JPanel btnLogin;
    private javax.swing.JPanel btnResetPass;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel resetPass;
    private javax.swing.JTextField txtUserName;
    private javax.swing.JPasswordField txtUserPass;
    // End of variables declaration                   

    private Color Color(int i, int i0, int i1) {
	    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
