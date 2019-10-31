package br.imd.controle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class TelaPrincipalController {
	
	private Principal main;
	ParametrosAnalise parametrosAnalise = new ParametrosAnalise();
	ParametrosAnaliseDAO dao = new ParametrosAnaliseDAO();
	
	@FXML private TextField textField;
	@FXML private Text textoResultado;
    @FXML private ComboBox<MedidaDistancia> comboBox = new ComboBox<MedidaDistancia>();
    @FXML private ImageView imageView;    
    @FXML private Button botaoApagar;
   
    private MedidaDistancia medida;
    private String texto;
    private String imagem;
    private boolean hasPerson;
    
    
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
    	imageView.setVisible(false);
    	textoResultado.setVisible(false);
    	botaoApagar.setVisible(false);
    	textField.textProperty().addListener((observable, oldValue, newValue) -> {
    		this.texto = newValue;
        });   
    	
    	comboBox.setItems(FXCollections.observableArrayList(
    			MedidaDistancia.EUCLIDIANA, 
    			MedidaDistancia.CHEBYSHEV,
    			MedidaDistancia.MANHATTAN
    			));
    	comboBox.valueProperty().addListener((observable, oldValue, newValue) -> this.medida = newValue);    	
    }
    
    @FXML
    private void escolherImagem(ActionEvent event) throws IOException {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Escolher imagem");
        File file = chooser.showOpenDialog(new Stage());
        imagem = file.getAbsolutePath();        
        Image image = new Image(new FileInputStream(this.imagem));       
        this.showImage(image);  
    }
    
    @FXML
    private void apagarImagem(ActionEvent event) throws IOException {
    	imagem = "";
    	imageView.setVisible(false);
    	botaoApagar.setVisible(false);
    }
    
    @FXML
    private void handleSubmitAction(ActionEvent event) {
    	System.out.println(this.texto + ' ' + this.medida + ' ' + this.imagem );
    	hasPerson = dao.calcular(this.texto, this.medida, this.imagem);
    	this.showResult(hasPerson);
    }
    
    private void showImage(Image image) {
    	Rectangle clip = new Rectangle(imageView.getFitWidth(), imageView.getFitHeight());
		clip.setArcWidth(70);
		clip.setArcHeight(70);
		imageView.setClip(clip);		
		imageView.setImage(image);
		imageView.setVisible(true);
		botaoApagar.setVisible(true);
    }
    
    private void showResult(boolean hasPerson) {
    	textoResultado.setText(hasPerson ? "Existe uma pessoa na imagem!" : "Não existe pessoa!" );
    	textoResultado.setStyle(hasPerson ? "-fx-text-fill: #00c853;" : "-fx-text-fill: #d50000;");
    	textoResultado.setVisible(true);
    }
}
