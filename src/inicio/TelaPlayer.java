/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inicio;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Veronica
 */
public class TelaPlayer extends Application {
 static public Stage telaprincipal;
    @Override
    public void start(Stage primaryStage) throws Exception {
        
                telaprincipal =  new Stage();
                
                
                
              
		
		URL arquivo = TelaPlayer.class.getResource("stagedecoration.fxml");
	        FXMLLoader.setDefaultClassLoader(TelaPlayer.class.getClassLoader());
		Parent parent = (Parent) FXMLLoader.load(arquivo);
            	Scene scene = new Scene(parent);
            	telaprincipal.setScene(scene);
	        telaprincipal.setTitle("Inicio");
	        telaprincipal.initStyle(StageStyle.TRANSPARENT);
	        scene.getStylesheets().add("br/com/player/tela/skin/cores.css");
	        //fecha =estado;
	        telaprincipal.show();	
	        telaprincipal.centerOnScreen();
	        telaprincipal.setResizable(false);
               scene.setFill(Color.TRANSPARENT);

               telaprincipal.setTitle("Sasply 0.6");
               telaprincipal.sizeToScene();        
	       telaprincipal.show();
                   
                   
		 
    }
    
    
    public  Stage getStage()
    {
        return telaprincipal;
    }
    
   
    
	
	public static void main(String[] args) {
		
		launch(args);
	}
    
    
}
