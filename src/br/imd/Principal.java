package br.imd;

import java.io.IOException;

import br.imd.controle.TelaPrincipalController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Principal extends Application {	
	private Stage stage1;
	public Pane layout1;
	public Pane layout2;

	@Override
	public void start(Stage primaryStage) throws IOException{
		this.stage1 = primaryStage;
		this.stage1.setTitle("Image Recognition Java");
		
		// Carrega a tela principal
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Principal.class.getResource("visao/TelaPrincipal.fxml"));
		layout1 = (Pane) loader.load();
		
		// Mostra a cena
		Scene scene = new Scene(layout1);
		stage1.setScene(scene);
		stage1.show();
		
		// Passando o controle
		TelaPrincipalController tpController = loader.getController();
		tpController.setMainApp(this);
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public void iniciarTela() throws IOException {
		// Mostra a cena
		Scene scene = new Scene(layout1);
		stage1.setScene(scene);
		stage1.show();
	}
}
