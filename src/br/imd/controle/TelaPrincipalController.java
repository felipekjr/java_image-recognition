package br.imd.controle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
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

	@FXML
	private Text textoResultado;
	@FXML
	private ComboBox<MedidaDistancia> comboBoxMedida = new ComboBox<MedidaDistancia>();
	@FXML
	private ComboBox<Integer> comboBoxK = new ComboBox<Integer>();
	@FXML
	private ImageView imageView;
	@FXML
	private Button botaoApagar;

	private MedidaDistancia medida;
	private Integer valorK = 0;
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

		comboBoxMedida.setItems(FXCollections.observableArrayList(MedidaDistancia.EUCLIDIANA, MedidaDistancia.CHEBYSHEV,
				MedidaDistancia.MANHATTAN));

		comboBoxMedida.valueProperty().addListener((observable, oldValue, newValue) -> this.medida = newValue);
		comboBoxK.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
		comboBoxK.valueProperty().addListener((observable, oldValue, newValue) -> this.valorK = newValue);
	}

	@FXML
	private void escolherImagem(ActionEvent event) throws IOException {
		try {
			FileChooser chooser = new FileChooser();
			chooser.setTitle("Escolher imagem");
			File file = chooser.showOpenDialog(new Stage());
			imagem = file.getAbsolutePath();
			Image image = new Image(new FileInputStream(this.imagem));
			this.showImage(image);
		} catch (Exception e) {
			System.out.println("Erro ao escolher imagem.Por favor, escolha novamente!");
		}

	}

	@FXML
	private void apagarImagem(ActionEvent event) throws IOException {
		imagem = "";
		imageView.setVisible(false);
		botaoApagar.setVisible(false);
	}

	@FXML
	private void handleSubmitAction(ActionEvent event) {
		String erro;
		try {
			erro = this.verificar(this.valorK, this.medida, this.imagem);
			if (erro != "") {
				this.showError(erro);
			} else {
				hasPerson = dao.calcular(this.valorK, this.medida, this.imagem);
				this.showResult(hasPerson);
			}
		} catch (Exception e) {
			System.out.println("Ocorreu um erro de submissão. Por favor, preencha os campos corretamente!");
		}

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

	private void showError(String erro) {
		textoResultado.setText(erro);
		textoResultado.getStyleClass().clear();
		textoResultado.getStyleClass().add("texto-resultado");
		textoResultado.getStyleClass().add("vermelho");
		textoResultado.setVisible(true);
	}

	private void showResult(boolean hasPerson) {
		textoResultado.setText(hasPerson ? "Existe uma pessoa na imagem!" : "Não existe pessoa na imagem!");
		textoResultado.getStyleClass().clear();
		textoResultado.getStyleClass().add("texto-resultado");
		textoResultado.getStyleClass().add(hasPerson ? "verde" : "vermelho");
		textoResultado.setVisible(true);
	}

	public final String verificar(int k, MedidaDistancia medidaDistancia, String caminhoImagem) throws Exception {
		String mimeType = null;
		if (medidaDistancia == null) {
			return "Insira uma métrica de distância";
		}
		if (k == 0) {
			return "Insira um valor K";
		}
		if (caminhoImagem == null) {
			return "Insira uma imagem";
		}
		try {
			java.nio.file.Path path = (java.nio.file.Path) new File(caminhoImagem).toPath();
			mimeType = Files.probeContentType(path);
		} catch (Exception a) {
			System.out.println("Erro de abertura da imagem");
		}
		if (mimeType.equals("image/png")) {
			return "";
		}
		return "A imagem passada não está no formato PNG";
	}
}
