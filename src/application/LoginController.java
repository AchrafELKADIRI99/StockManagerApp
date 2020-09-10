package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com_connection.ConnectionDB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.PasswordField;

public class LoginController {
	@FXML
	private TextField id;
	
	@FXML
	private PasswordField mdp;
	@FXML
	private Label exit;

	@FXML Button btnlog;
	static String loginid;
	@FXML
    private void handleButtonAction(MouseEvent event) {
        if (event.getSource() == exit) {
            System.exit(0);
        }
        else if (event.getSource() == btnlog) {
            
            if (logIn().equals("Success")) {
                try {
                	
                    Alert alert = new Alert (Alert.AlertType.INFORMATION);
                    alert.setTitle("Login Admin");
                    alert.setHeaderText("Information");
                    alert.setContentText("Identification avec succès");
                    alert.showAndWait();
                 
                   
                    
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                  
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource(".fxml")));
                    stage.setScene(scene);
                   // stage.initStyle(StageStyle.TRANSPARENT);
                    stage.show();
                } catch (IOException ex) {
                	
                    Alert alert = new Alert (Alert.AlertType.ERROR);
                    alert.setTitle("Login Admin");
                    alert.setHeaderText("Information");
                    alert.setContentText("Id ou mot de passe incorrects");
                    alert.showAndWait();
                    System.err.println(ex.getMessage());
                }
            }
            
        }
        
      }
	
	 public LoginController() {
	        con = ConnectionDB.conDB();
	    }
	Connection con = null;
 PreparedStatement ps = null;
 ResultSet rs = null;
 private String logIn() {
     String ID = id.getText();
     String password = mdp.getText();
  
     String sql = "SELECT * FROM `admins` WHERE id=? AND mdp=?";

     try {
         ps = con.prepareStatement(sql);
         ps.setString(1, ID);
         ps.setString(2, password);
       
         rs = ps.executeQuery();
         if (rs.next()) {

            
             
             System.out.println("Successful login");
             return "Success";
         } else {

           
             
             System.err.println("Logins incorrectes --///");
             return "error";
         }
     } catch (SQLException ex) {
         System.err.println(ex.getMessage());
         return "Exception";
     }
 }
 
         
   
 
 public static  String getlogid() {
 	String a=loginid;
 	return(a);
 }

}
