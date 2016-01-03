/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.player.tela.equalizador;

import inicio.TelaPlayer;
import static inicio.TelaPlayer.telaprincipal;
import insidefx.undecorator.UndecoratorScene;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Veronica
 */
public class Equalizador extends Application{

        
         static Stage estado;
	@Override
	
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		 estado = new Stage();
		 
                 
		URL arquivo = Equalizador.class.getResource("equalizador.fxml");
	        FXMLLoader.setDefaultClassLoader(Equalizador.class.getClassLoader());
		Parent parent = (Parent) FXMLLoader.load(arquivo);
                UndecoratorScene scene = new UndecoratorScene(estado, StageStyle.UTILITY, (Region) parent, null);
                estado.setScene(scene);
                estado.initModality(Modality.WINDOW_MODAL);
                estado.initOwner(TelaPlayer.telaprincipal);
                
		estado.setScene(scene);
	        estado.setTitle("Equalizador");
	        estado.show();	
                
                estado.setResizable(false);
	    
    }
    
}
