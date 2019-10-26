package br.imd.controle;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import br.imd.Principal;
import br.imd.dao.ParametrosAnaliseDAO;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class TelaPrincipalController {
	// classes
	private Principal main;
	ParametrosAnalise parametrosAnalise = new ParametrosAnalise();
	ParametrosAnaliseDAO dao = new ParametrosAnaliseDAO();
	// elementos da view
	@FXML private TextField textField;
    @FXML private ComboBox<MedidaDistancia> comboBox = new ComboBox<MedidaDistancia>();
    @FXML private Button button;
    // variáveis locais
    private MedidaDistancia medida;
    private String texto;
    private String imagem;
    
    
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
    		this.texto = newValue;
        });   
    	// inicializa o select
    	comboBox.setItems(FXCollections.observableArrayList(MedidaDistancia.EUCLIDIANA, MedidaDistancia.CHEBYCHEV));
    	comboBox.valueProperty().addListener((observable, oldValue, newValue) -> this.medida = newValue);
    }
    
    @FXML
    private void escolherImagem(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Escolher imagem");
        File file = chooser.showOpenDialog(new Stage());
        this.imagem = file.getAbsolutePath();
    }
    
    @FXML
    private void handleSubmitAction(ActionEvent event) {
    	System.out.println(this.texto + ' ' + this.medida + ' ' + this.imagem );
    	dao.calcular(this.texto, this.medida, this.imagem);
    }
}
