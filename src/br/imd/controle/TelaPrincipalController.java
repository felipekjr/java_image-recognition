package br.imd.controle;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import br.imd.Principal;
import br.imd.modelo.MedidaDistancia;
import br.imd.modelo.ParametrosAnalise;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

public class TelaPrincipalController {
	
	private Principal main;
	ParametrosAnalise parametrosAnalise = new ParametrosAnalise();
	
	@FXML private TextField textField;
    @FXML private ComboBox<MedidaDistancia> comboBox = new ComboBox<MedidaDistancia>();
    @FXML private Button button;
    
    public void setMainApp(Principal main) {
		this.main = main;
	}    
    
    @FXML
    void abrirTelaPrincipal(ActionEvent event) {
    	try {
			main.iniciarTela();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    protected void initialize() {
    	// escutar campo de texto    	
    	textField.textProperty().addListener((observable, oldValue, newValue) -> {
    		this.parametrosAnalise.setK(Integer.parseInt(newValue));
        });   
    	// inicializa o select
    	comboBox.setItems(FXCollections.observableArrayList(MedidaDistancia.EUCLIDIANA, MedidaDistancia.CHEBYCHEV));
    	comboBox.valueProperty().addListener((newValue) -> System.out.println(newValue));
    }
    
    @FXML
    private void handleSubmitAction(ActionEvent event) {
    	 	
    }
}
