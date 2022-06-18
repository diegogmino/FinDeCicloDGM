package com.diego.FinDeCicloDGM;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import com.diego.FinDeCiclo.hilos.HiloIniciarSesion;
import com.diego.FinDeCiclo.hilos.HiloRegistro;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
    
    @FXML
    private ProgressIndicator procesandoRegistro;
    
    @FXML
    private ProgressIndicator procesandoLogin;
    
    @FXML
    private Label mensajeError;
    
    @FXML
    private Label mensajeNombreUsuario;

    @FXML
    private Label mensajeErrorInsertar;

    @FXML
    private Label mensajeEmail;

    @FXML
    private Label mensajeContrasena;
    
    @FXML
    private Label mensajeObligatorio;
    
    private Parent fxml;
    
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
        		// Llamamos al método que limpia los campos de iniciar sesión después de que acabe la animación
        		limpiarCamposLogin();
        	} catch (IOException ex) {
        		ex.printStackTrace();
        	}
        }); 
    	
    }

    // Método que ejecuta la animación y muestra el FXML que acompaña a la vista del login 
    @FXML
    public void mostrarIniciarSesion() {

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
        		procesandoRegistro.setVisible(false);
        		
        		// Volvemos a mostrar el mensaje por defecto y ocultamos el resto
        		mensajeObligatorio.setVisible(true);
        		mensajeEmail.setVisible(false);
        		mensajeNombreUsuario.setVisible(false);
        		mensajeErrorInsertar.setVisible(false);
        		mensajeContrasena.setVisible(false);
        		
        		// Volvemos a deshabilitar el boton de crear cuenta
        		camposEsVacios();
        		
        	} catch (IOException ex) {
        		ex.printStackTrace();
        	}
        });
    	
    }
    
    // Método que pone las variables que hacen referencia a los campos escritos, es decir, las terminadas en Es, a false
    private void camposEsVacios() {
    	
    	usuarioRegistroEs = false;
    	contrasenaRegistroEs = false;
    	repetirContrasenaEs = false;
    	emailEs = false;
    	nombreEs = false;
    	apellidosEs = false;
    	usuarioLoginEs = false;
        contrasenaLoginEs = false;
    	
    }

    // Método que comprueba si el usuario introducido existe y le permite entrar en la aplicación
    public void iniciarSesion() {

    	procesandoLogin.setVisible(true);
    	
    	HiloIniciarSesion hiloSesion = new HiloIniciarSesion(usuarioLogin, contrasenaLogin, procesandoLogin, this, mensajeError);
    	hiloSesion.start();
    	
    	iniciarSesion.setDisable(true);
    	usuarioLoginEs = false;
    	contrasenaLoginEs = false;
  
    }
    
    // Método que cambia la pantalla actual por la de selectorColeccion
    public void mostrarSelectorColeccion() {
    	this.layout.mostrarComoPantallaActual("selectorColeccion");
    }
    
    // Método para crear un usuario nuevo e insertarlo en la base de datos usando hilos
    public void registro() {
    	
    	procesandoRegistro.setVisible(true);
    	
    	HiloRegistro hiloRegistro = new HiloRegistro(usuarioRegistro, contrasenaRegistro, repetirContrasena, nombre, apellidos, fechaNacimiento, email, this, procesandoRegistro,
    			mensajeContrasena, mensajeEmail, mensajeErrorInsertar, mensajeNombreUsuario, mensajeObligatorio);
    	hiloRegistro.start();
    	
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
    
    private void limpiarCamposLogin() {
    	// Método que limpia los campos del iniciar sesion
    	
    	usuarioLogin.clear();
    	contrasenaLogin.clear();
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
    
   @Override
   public void initialize(URL location, ResourceBundle resources) {
    	
	   mensajeError.setVisible(false);
       
       procesandoLogin.setVisible(false);
       procesandoRegistro.setVisible(false);
       
       crearCuenta.setDisable(true);
       iniciarSesion.setDisable(true);
 
       try {
		fxml = FXMLLoader.load(getClass().getResource("IniciarSesion.fxml"));
		vbox.getChildren().removeAll();
		vbox.getChildren().setAll(fxml);
       } catch (IOException e) {
		e.printStackTrace();
       }
       
       LocalDate fechaBloqueo = LocalDate.now().minusYears(18);
       
       
       // Muestra en el calendario la fecha actual menos 16 años
       fechaNacimiento.showingProperty().addListener((observableValue, wasFocused, isNowFocus) -> {
    	    if (isNowFocus && fechaNacimiento.getValue() == null) {
    	    	fechaNacimiento.setValue(fechaBloqueo);
    	        Platform.runLater(()->{
    	        	fechaNacimiento.getEditor().clear();
    	        });
    	    }
    	});
       
    // Bloquea las fechas mayores que la fecha actual menos 16 años para evitar que los menores de dicha 
    // edad puedan darse de alta en la aplicación
    fechaNacimiento.setDayCellFactory(param -> new DateCell() {
    	@Override
    	public void updateItem(LocalDate date, boolean empty) {
    		super.updateItem(date, empty);
    		setDisable(empty || date.compareTo(fechaBloqueo) > 0 );
    	}
    });
       
   }
    

}
