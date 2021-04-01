package com.diego.FinDeCicloDGM;

import java.net.URL;
import java.util.ResourceBundle;

import com.diego.FinDeCiclo.pojos.Usuario;
import com.diego.FinDeCicloDGM.dao.UsuarioDao;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginControlador extends ControladorConNavegabilidad implements Initializable {
    
    @FXML
    TextField usuario ;
    
    @FXML
    TextField contrasena ;
    
    UsuarioDao usuarioDao;
    Usuario usuarioEncontrado;
    
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
        this.layout.mostrarComoPantallaActual("registro");
    }
    
    @Override
   public void initialize(URL location, ResourceBundle resources) {
       
       usuarioDao = new UsuarioDao();
       usuarioEncontrado = new Usuario();
       
   }
    

}
