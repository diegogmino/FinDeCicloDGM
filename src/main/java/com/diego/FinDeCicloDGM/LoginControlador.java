package com.diego.FinDeCicloDGM;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.diego.FinDeCiclo.pojos.Usuario;
import com.diego.FinDeCicloDGM.dao.UsuarioDao;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class LoginControlador extends ControladorConNavegabilidad implements Initializable {
    
	@FXML
    public Pane root;

    @FXML
    private TextField usuario;

    @FXML
    private PasswordField contrasena;

    @FXML
    private TextField email;

    @FXML
    private TextField nombre;

    @FXML
    private TextField apellidos;

    @FXML
    private DatePicker nacimiento;

    @FXML
    private TextField usuario1;

    @FXML
    private PasswordField contrasena1;

    @FXML
    public VBox vbox;
    
    private Parent fxml;
    
    UsuarioDao usuarioDao;
    Usuario usuarioEncontrado;

    @FXML
    void mostrarCrearCuenta() {
    	
        TranslateTransition translate = new TranslateTransition(Duration.seconds(2), vbox);
        translate.setToX(vbox.getLayoutX() + (root.getPrefWidth() - vbox.getPrefWidth()));
        translate.play();
        
        translate.setOnFinished((e)->{
        	try {
        		fxml = FXMLLoader.load(getClass().getResource("CrearCuenta.fxml"));
        		vbox.getChildren().removeAll();
        		vbox.getChildren().setAll(fxml);
        	} catch (IOException ex) {
        		ex.printStackTrace();
        	}
        });
        
        
    	
    }

    @FXML
    void mostrarIniciarSesion(ActionEvent event) {

        TranslateTransition translate = new TranslateTransition(Duration.seconds(2), vbox);
        translate.setToX(root.getLayoutX());
        translate.play();
        
        translate.setOnFinished((e)->{
        	try {
        		fxml = FXMLLoader.load(getClass().getResource("IniciarSesion.fxml"));
        		vbox.getChildren().removeAll();
        		vbox.getChildren().setAll(fxml);
        	} catch (IOException ex) {
        		ex.printStackTrace();
        	}
        });
    	
    }

    
    public void login() {
        
        boolean logear = false;

        usuarioEncontrado = usuarioDao.existeUsuario(usuario.getText(), contrasena.getText());
         
         if((usuarioEncontrado.getNombre() != null) && (usuarioEncontrado.getRango() == 1)) {
             
             Alert usuarioEncontrado = lanzarPopup("Bienvenid@", "Login completado con éxito", 1);
             usuarioEncontrado.showAndWait();
             
              this.layout.mostrarComoPantallaActual("libros");
              this.layout.getStylesheets().addAll(getClass().getResource("..\\..\\..\\estilos\\libros.css").toExternalForm());
              
              usuario.clear();
              contrasena.clear();
              
         }  else if((usuarioEncontrado.getNombre() != null) && (usuarioEncontrado.getRango() == 2)) {
             
             Alert usuarioEncontrado = lanzarPopup("Bienvenid@", "Login completado con éxito", 1);
             usuarioEncontrado.showAndWait();
             
             this.layout.mostrarComoPantallaActual("librosAdmin");
             this.layout.getStylesheets().addAll(getClass().getResource("..\\..\\..\\estilos\\libros.css").toExternalForm());
             
             usuario.clear();
             contrasena.clear();
             
         } else {
             Alert usuarioNoEncontrado = lanzarPopup("Error", "No existe ningún usuario que corresponda a los valores introducidos. Por favor, inténtelo de nuevo", 2);
             usuarioNoEncontrado.showAndWait();
         }
  
    }
    
     public Alert lanzarPopup(String titulo, String contenido, int tipo) {
       // Método para crear un popup con los string recibidos y devolverlo 
       Alert popup = null;
       if (tipo == 1) {
           popup = new Alert(Alert.AlertType.INFORMATION);
       } else if(tipo == 2) {
           popup = new Alert(Alert.AlertType.ERROR);
       }
       
       popup.setTitle(titulo);
       popup.setHeaderText(null);
       popup.setContentText(contenido);
       popup.initStyle(StageStyle.DECORATED); 
       Stage stage = (Stage) popup.getDialogPane().getScene().getWindow();
       stage.getIcons().add(new Image("/img/libro.png"));
       
       DialogPane dialogPane = popup.getDialogPane();
        dialogPane.getStylesheets().add(
        getClass().getResource("popup.css").toExternalForm());
        dialogPane.getStyleClass().add("popup");
       
        return popup;
            
   }
    
    public void crearCuenta() {
        
    	System.out.println("registro");
    	
    }
    
   @Override
   public void initialize(URL location, ResourceBundle resources) {
    	
       usuarioDao = new UsuarioDao();
       usuarioEncontrado = new Usuario();
 
       try {
		fxml = FXMLLoader.load(getClass().getResource("IniciarSesion.fxml"));
		vbox.getChildren().removeAll();
		vbox.getChildren().setAll(fxml);
	} catch (IOException e) {
		e.printStackTrace();
	}
       
   }
    

}
