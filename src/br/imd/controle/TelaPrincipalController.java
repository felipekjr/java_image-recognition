package br.imd.controle;

import java.io.IOException;

import br.imd.Principal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class TelaPrincipalController {

	private Principal main;
    
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
}
