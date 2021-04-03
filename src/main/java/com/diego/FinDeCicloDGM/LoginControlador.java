package com.diego.FinDeCicloDGM;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
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
import javafx.scene.control.Button;
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
    private Pane root;

    @FXML
    private TextField usuarioRegistro;

    @FXML
    private PasswordField contrasenaRegistro;

    @FXML
    private TextField email;

    @FXML
    private TextField nombre;

    @FXML
    private TextField apellidos;

    @FXML
    private DatePicker fechaNacimiento;

    @FXML
    private PasswordField repetirContrasena;

    @FXML
    private TextField usuarioLogin;

    @FXML
    private PasswordField contrasenaLogin;

    @FXML
    private VBox vbox;
    
    @FXML
    private Button crearCuenta;
    
    @FXML
    private Button iniciarSesion;
    
    private Parent fxml;
    
    private LocalDate date = LocalDate.parse("1900-01-01");
    
    private UsuarioDao usuarioDao;
    private Usuario usuarioEncontrado;
    
    private boolean usuarioRegistroEs = false, contrasenaRegistroEs = false, repetirContrasenaEs = false, emailEs = false, nombreEs = false, apellidosEs = false, 
    		usuarioLoginEs = false, contrasenaLoginEs = false;

    // Método que ejecuta la animación y muestra el FXML que acompaña a la vista del registro
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

    // Método que ejecuta la animación y muestra el FXML que acompaña a la vista del login 
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
        		// Llamamos al método de limpiar campos cuando la animación termine, para que el usuario no vea desaparecer la información de repente
        		limpiarCamposRegistro();
        	} catch (IOException ex) {
        		ex.printStackTrace();
        	}
        });
    	
    }

    // Método que comprueba si el usuario introducido existe y le permite entrar en la aplicación
    public void iniciarSesion() {
        
        boolean logear = false;

        usuarioEncontrado = usuarioDao.existeUsuario(usuarioLogin.getText(), contrasenaLogin.getText());
         
         if((usuarioEncontrado.getNombre() != null) && (usuarioEncontrado.getRango() == 1)) {
             
             Alert usuarioEncontrado = lanzarPopup("Bienvenid@", "Login completado con éxito", 1);
             usuarioEncontrado.showAndWait();
             
              this.layout.mostrarComoPantallaActual("libros");
              this.layout.getStylesheets().addAll(getClass().getResource("..\\..\\..\\estilos\\libros.css").toExternalForm());
              
              usuarioLogin.clear();
              contrasenaLogin.clear();
              
         }  else if((usuarioEncontrado.getNombre() != null) && (usuarioEncontrado.getRango() == 2)) {
             
             Alert usuarioEncontrado = lanzarPopup("Bienvenid@", "Login completado con éxito", 1);
             usuarioEncontrado.showAndWait();
             
             this.layout.mostrarComoPantallaActual("librosAdmin");
             this.layout.getStylesheets().addAll(getClass().getResource("..\\..\\..\\estilos\\libros.css").toExternalForm());
             
             usuarioLogin.clear();
             contrasenaLogin.clear();
             
         } else {
             Alert usuarioNoEncontrado = lanzarPopup("Error", "No existe ningún usuario que corresponda a los valores introducidos. Por favor, inténtelo de nuevo", 2);
             usuarioNoEncontrado.showAndWait();
         }
  
    }
    
    // Método para crear un usuario nuevo e insertarlo en la base de datos
    public void registro() {
    	
    	Date fecha;
    	
    	// Comprobamos si el usuario ha introducido una fecha, ya que no es un campo obligatorio
    	if(fechaNacimiento.getValue() == null) {
    		fecha = Date.valueOf(date);
    	} else {
    		fecha = Date.valueOf(fechaNacimiento.getValue());
    	}
    	
    	Usuario usuario = new Usuario(usuarioRegistro.getText(), contrasenaRegistro.getText(), email.getText(), fecha, nombre.getText(), apellidos.getText());
    	
    	if(usuarioDao.insertarUsuario(usuario)) {
    		mostrarIniciarSesion(null);
    	} else {
    		System.out.println("Error al insertar el usuario");
    	}
    	
    	
    }
    
    private void limpiarCamposRegistro() {
    	// Método que limpia los campos del formulario de registro
    	
    	nombre.clear();
    	apellidos.clear();
    	fechaNacimiento.setValue(null);
    	usuarioRegistro.clear();
    	contrasenaRegistro.clear();
    	repetirContrasena.clear();
    	email.clear();
    	
    	crearCuenta.setDisable(true);
    	
    }
    
    public void escribirNombre() {
    	// Método que escucha cada vez que se pulsa una tecla en el campo de nombre y comprueba si está vacio o no
    	nombreEs = !nombre.getText().isEmpty();
    	activarCrearCuenta();
    }
    
    public void escribirApellidos() {
    	// Método que escucha cada vez que se pulsa una tecla en el campo de apellidos y comprueba si está vacio o no
    	apellidosEs = !apellidos.getText().isEmpty();
    	activarCrearCuenta();
    }
    
    public void escribirUsuarioRegistro() {
    	// Método que escucha cada vez que se pulsa una tecla en el campo de usuario y comprueba si está vacio o no
    	usuarioRegistroEs = !usuarioRegistro.getText().isEmpty();
    	activarCrearCuenta();
    }
    
    public void escribirContrasenaRegistro() {
    	// Método que escucha cada vez que se pulsa una tecla en el campo de contraseña y comprueba si está vacio o no
    	contrasenaRegistroEs = !contrasenaRegistro.getText().isEmpty();
    	activarCrearCuenta();
    }
    
    public void escribirRepetirContrasena() {
    	// Método que escucha cada vez que se pulsa una tecla en el campo de repetir contraseña y comprueba si está vacio o no
    	repetirContrasenaEs = !repetirContrasena.getText().isEmpty();
    	activarCrearCuenta();
    }
    
    public void escribirEmail() {
    	// Método que escucha cada vez que se pulsa una tecla en el campo de email y comprueba si está vacio o no
    	emailEs = !email.getText().isEmpty();
    	activarCrearCuenta();
    }
    
    private void activarCrearCuenta() {
    	// Método que comprueba si todos los campos del registro están cubiertos y activa o desactiva el botón en consecuencia
    	if((nombreEs == true) && (apellidosEs == true) && (usuarioRegistroEs == true) && (contrasenaRegistroEs == true) && (repetirContrasenaEs == true) && (emailEs == true)) {
    		crearCuenta.setDisable(false);
    	} else {
    		crearCuenta.setDisable(true);
    	}
    	
    }
    
    public void escribirUsuarioLogin() {
    	// Método que escucha cada vez que se pulsa una tecla en el campo de usuario del login y comprueba si está vacio o no
    	usuarioLoginEs = !usuarioLogin.getText().isEmpty();
    	activarIniciarSesion();
    }
    
    public void escribirContrasenaLogin() {
    	// Método que escucha cada vez que se pulsa una tecla en el campo de contraseña del login y comprueba si está vacio o no
    	contrasenaLoginEs = !contrasenaLogin.getText().isEmpty();
    	activarIniciarSesion();
    }
    
    private void activarIniciarSesion() {
    	// Método que comprueba si todos los campos del login están cubiertos y activa o desactiva el botón en consecuencia
    	if((usuarioLoginEs == true) && (contrasenaLoginEs == true)) {
    		iniciarSesion.setDisable(false);
    	} else {
    		iniciarSesion.setDisable(true);
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
    
   @Override
   public void initialize(URL location, ResourceBundle resources) {
    	
       usuarioDao = new UsuarioDao();
       usuarioEncontrado = new Usuario();
       
       crearCuenta.setDisable(true);
       iniciarSesion.setDisable(true);
 
       try {
		fxml = FXMLLoader.load(getClass().getResource("IniciarSesion.fxml"));
		vbox.getChildren().removeAll();
		vbox.getChildren().setAll(fxml);
	} catch (IOException e) {
		e.printStackTrace();
	}
       
   }
    

}
