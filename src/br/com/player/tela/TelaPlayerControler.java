/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.player.tela;


import br.com.player.tela.equalizador.Equalizador;
import br.com.player.tela.letras.Letra;
import inicio.TelaPlayer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Math.floor;
import static java.lang.String.format;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.AmbientLight;
import javafx.scene.Cursor;
import javafx.scene.PointLight;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.Light.Distant;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;


/**
 *
 * @author Veronica
 */
public class TelaPlayerControler implements Initializable{
    @FXML
    private Button btnBack;
    @FXML
    private Slider sldMusica;

     @FXML
    private AmbientLight luzTela;
    
    
    @FXML
    private ToolBar toolPlaylist;
    @FXML
    private Button btnPlaylist;

    @FXML
    private Button btnAbrir;

//    @FXML
//    private Button btnForward;

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnNext;

    @FXML
    private Button btnLimpar;
//
//    @FXML
//    private Button btnBackward;
    @FXML
    private Button btnStop;
    
    @FXML
    private Button btnMute;
    
    @FXML
    private Label lblMusica;
    
    @FXML
    private Label lblContador;
    
    @FXML
    private Label lblStatus;
    @FXML
    private Label lblTempoMsg;
    @FXML
    private Label lblTempo;
    @FXML
    private Label lblVolume;
     @FXML
    private Label lblMax = new Label();
     
    @FXML
    private Label lblZeta;
    
    @FXML
    private Rectangle rectPrincipal;

   @FXML
    private ToggleButton btnEq;   
     
    @FXML
    private ListView<String> llstPlaylist = new ListView<>() ;
    @FXML
    private Button btnRemover;
    
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnCarregar;
     @FXML
    private Button btnAdicionar;  
     @FXML
    private Circle crcPlay;
    @FXML
    private Label lblPlaylist;
    
    
    @FXML
    private Circle crcStop;
    
    @FXML
    private Circle crcMute;
    
    @FXML
    private Label lblArtista;

    @FXML
    private Label lblNomeArtista;
    
    @FXML
    private Label  lblMusicaMsg;
    
    @FXML
    private Label lblAno;
    
    @FXML
    private Label  lblAnoMsg;
   
    @FXML
    private Pane pnePrincipal;
    
    
    @FXML
    private Label lblalbumMsg;
    
    @FXML
    private Label lblNomeAlbum;
    
    @FXML
    private ImageView imgCapa;
    
   @FXML
    private PointLight ligStop;
   
   @FXML
    private PointLight ligMute;
    
    @FXML
    private AmbientLight ambStop;
    @FXML
    private ToggleButton btnLetra;
     @FXML
    private TextArea txtLetra;
    
   @FXML
    private Label lblLetraMsg;
 
   
    @FXML
    private Button btnMinimizar;
   
    @FXML
    private Button btnFechar;
     
    @FXML
    private StackPane stackTela;
    
    @FXML
    private Button btnExibirLetra;
    
     
    @FXML
    private Label lblMin;
    ArrayList <File>arquivos =new ArrayList<File>();
    ArrayList <File>musicas =new ArrayList<File>();
    List <File>carrega =new ArrayList<File>();
    ArrayList<File>playmusicas = new ArrayList<File>();
    File arquivo;
    File arquivoTocando;
    int posicaoMouse = 0;
    int remove = 0;
    Media media; 
    MediaPlayer mediaPlayer = null; 
    String musica = "";
    Double volumeMax =0.0; 
    Double volumeMin =0.0; 
    int posicao =0;
    int posicaoVolta =0;
    int maximo;
    boolean barra =false;
   
    String tipo = "nao escolhido";
   Duration duration =Duration.ZERO;
   int tocando = 0;
   TelaPlayer telaPlayer = new TelaPlayer();
   Image play,stop,pause,forward,backward,next,back,abrir,lista,excluir,alto,medio,baixo;
   File plsZeta;
    Distant dis = new Distant();
    Lighting light = new Lighting();
   double  tempoBarra; 
   double tempoTotal; 
  
    final ProgressBar pb = new ProgressBar(0);
    private static double xOffset = 0;
    private static double yOffset = 0;   
    
    
    


   
   
   
   
protected void updateValues() {
if (lblContador != null) {
 Platform.runLater(() -> {
Duration currentTime = mediaPlayer.getCurrentTime();

tempoBarra =  Double.parseDouble(formatTime(currentTime, duration).replace(":", ""));
tempoTotal =   Double.parseDouble(formatTime(media.getDuration(), duration).replace(":", ""));



sldMusica.setMax(tempoTotal);

 lblContador.setText(formatTime(currentTime, duration));
 
if(!sldMusica.isPressed())
{    
    sldMusica.setValue(tempoBarra); 
}


 
 
  
//  sldMusica.setON.setOnMousePressed(new EventHandler<MouseEvent>() {
//
//    @Override
//    public void handle(MouseEvent event) {
//        
//        if(event.getClickCount()==1)
//        {    
//            mediaPlayer.seek(Duration.seconds(sldMusica.getValue()+1.0));
//            sldMusica.adjustValue(tempoBarra);
//     
//        }
//       
//    }
//    
//   
//});
//     
// 
//  sldMusica.setOnMouseClicked(new EventHandler<MouseEvent>() {
//
//    @Override
//    public void handle(MouseEvent event) {
//        
//        
//        if(event.getClickCount()==1)
//        {    
//            mediaPlayer.seek(Duration.seconds(sldMusica.getValue()+1.0));
//            sldMusica.adjustValue(tempoBarra);
//     
//        }
//       
//        
//       
//    }
//}); 
   
    
});
}
}
 private static String formatTime(Duration elapsed, Duration duration) {
int intElapsed = (int) floor(elapsed.toSeconds());
int elapsedHours = intElapsed / (60 * 60);
if (elapsedHours > 0) {
intElapsed -= elapsedHours * 60 * 60;
}
int elapsedMinutes = intElapsed / 60;
int elapsedSeconds = intElapsed - elapsedHours * 60 * 60
- elapsedMinutes * 60;

if (duration.greaterThan(Duration.ZERO)) {
int intDuration = (int) floor(duration.toSeconds());
int durationHours = intDuration / (60 * 60);
if (durationHours > 0) {
intDuration -= durationHours * 60 * 60;
}
int durationMinutes = intDuration / 60;
int durationSeconds = intDuration - durationHours * 60 * 60
- durationMinutes * 60;
if (durationHours > 0) {
return format("%d:%02d:%02d/%d:%02d:%02d",
elapsedHours, elapsedMinutes, elapsedSeconds,
durationHours, durationMinutes, durationSeconds);
} else {
return format("%02d:%02d/%02d:%02d",
elapsedMinutes, elapsedSeconds, durationMinutes,
durationSeconds);
}
} else {
if (elapsedHours > 0) {
return format("%d:%02d:%02d", elapsedHours,
elapsedMinutes, elapsedSeconds);
} else {
return format("%02d:%02d", elapsedMinutes,
elapsedSeconds);
}
}
}   
 
 
 private void logo()
 {
      lblZeta.setVisible(true);
     TranslateTransition transition = new TranslateTransition(Duration.seconds(1.90));
      RotateTransition rodar = new RotateTransition(Duration.millis(3000), lblZeta);
     rodar.setByAngle(360);
     rodar.setCycleCount(1);
     rodar.setAutoReverse(true);
     rodar.play();
     
     transition.setNode(lblZeta);
  transition.setFromY(-100);
  transition.setToY(0);
    
      transition.play();
      
      
 }
 
 
 
 
Task<Void> zeta = new Task<Void> ()   
		     {  
		    	@Override public Void call() throws InterruptedException
		    	{
		    		int max = 10;
		    		int i;
		    		for(i =0;i<=max;i++)
		    		
                                 {
		    		Thread.sleep(500);
		    		Platform.runLater(new Runnable() {
						
				@Override
				public   void  run() {
                                    
                                       if (sldMusica.isPressed()==true)
                                        {
                                            
                                           tocando = -3;
                                           mediaPlayer.seek(Duration.seconds(sldMusica.getValue()));
                                           Duration novoTempo = mediaPlayer.getCurrentTime();
                                           Double tempoBarraNovo =   Double.parseDouble(formatTime(novoTempo, duration).replace(":", ""));
                                            mediaPlayer.pause();
                                           sldMusica.adjustValue(tempoBarraNovo);
                                           tocando =1;
                                          // mediaPlayer.play();
                                           //updateValues();
                                           barra = true;
                                          

                                    }
                                     
                                       if(barra== true)
                                       {
                                           mediaPlayer.play();
                                           
                                           barra = false;
                                       }
                                   
                                }
                             
		    		});	
		    		
		    			if(i==9)
		    			{
		    			   i=0;
		    			}
		    		}
					return null;
		    	}
		     };
 
 
Task<Void> playlist = new Task<Void> ()   
		     {  
		    	@Override public Void call() throws InterruptedException
		    	{
		    		int max = 10;
		    		int i;
		    		for(i =0;i<=max;i++)
		    		
                                 {
		    		Thread.sleep(1300);
		    		Platform.runLater(new Runnable() {
						
						@Override
			   public  synchronized void  run() {
                                                    
                             if(!arquivos.isEmpty())
                                       {
                                            btnLimpar.setDisable(false);
                                            btnPlaylist.setDisable(true);
                                       }
                                       else if(arquivo != null)
                                       {
                                            btnLimpar.setDisable(false);
                                             btnPlaylist.setDisable(true);
                                       }
                                       else
                                       {
                                           btnLimpar.setDisable(true);
                                            btnPlaylist.setDisable(false);
                                       }
                                         
                                      
                            
                               
                                              
       
                             if(btnNext.isPressed() && posicao ==musicas.size()-1)
                             {
                                 btnNext.setDisable(true);
                             }
                             if(btnBack.isPressed() && posicao == 0)
                             {
                                 btnBack.setDisable(true);
                             }
                             
                             if(llstPlaylist.getItems().isEmpty())
                             {
                                 btnAdicionar.setDisable(true);
                                 btnRemover.setDisable(true);
                                 btnSalvar.setDisable(true);
                                
                             }
                               else
                             {
                                 btnAdicionar.setDisable(false);
                                 btnRemover.setDisable(false);
                                 btnSalvar.setDisable(false);
                                 
                             }
   
                    
                             
                               if(lblStatus.getText() == "Tocando")
                               {
                                    btnPlay.setGraphic(new ImageView(pause));
                               }
                               
                                                     
                        if(mediaPlayer == null)
                        {
                             btnLetra.setDisable(true);
                        }
                         else
                        {
                             btnLetra.setDisable(false);
                        }
                       
                                                
                        if(mediaPlayer != null)
                        {   
                            
                            
                            sldMusica.setVisible(true);
                             lblZeta.setVisible(false);
                            
                            
                            if(posicao <=0)
                            {
                                 btnBack.setDisable(true);
                                 llstPlaylist.getSelectionModel().select(0);
                                 
                            }
                             else
                            {
                                 btnBack.setDisable(false);
                            }
                             
                          
                            if(posicao == musicas.size())
                            {
                                btnNext.setDisable(true);
                            }
                            
                         
                            
                            if(posicao >= musicas.size() )
                            {
                                    btnNext.setDisable(true);
                                   
                            }
                            else
                            {
                                btnNext.setDisable(false);
                            }
                           
                            if(!llstPlaylist.getItems().isEmpty())
                            {
                                llstPlaylist.getSelectionModel().select(posicao);
                               
                                
                                try
                                {
                                lblTempo.setVisible(true);
                                lblTempoMsg.setVisible(true);
                                lblTempoMsg.setText("Tempo Total");
                               
                                lblTempo.setText(formatTime(media.getDuration(), duration));
                               
                               
                                if(media.getMetadata().get("artist") != null)
                                {
                                     
                                     lblArtista.setVisible(true);
                                    lblNomeArtista.setVisible(true);
                                    lblNomeArtista.setText(media.getMetadata().get("artist").toString());
                                }
                                 else
                                {
                                      lblArtista.setVisible(false);
                                      lblNomeArtista.setVisible(false);
                                }
                                
                               
                                lblMusicaMsg.setVisible(true);
                               
                                if(media.getMetadata().get("title") != null)
                                {
                                     lblMusica.setVisible(true); 
                                    lblMusica.setText(media.getMetadata().get("title").toString().replace(".mp3", ""));
                                }
                                 else
                                {
                                    lblMusica.setVisible(true); 
                                    lblMusica.setText(musicas.get(posicao).getName().toString().replace(".mp3", ""));
                                }
                                
                                if(media.getMetadata().get("year") != null)
                                {
                                    lblAno.setVisible(true);
                                    lblAnoMsg.setVisible(true);
                                    
                                    lblAno.setText(media.getMetadata().get("year").toString());
                                }
                                 else
                                {
                                    lblAno.setVisible(false);
                                    lblAnoMsg.setVisible(false);
                                }
                                
                                if(media.getMetadata().get("album") != null)
                                {
                                     lblalbumMsg.setVisible(true);
                                    lblNomeAlbum.setVisible(true);
                                      lblNomeAlbum.setText(media.getMetadata().get("album").toString());
                                } 
                                else 
                                {
                                    lblalbumMsg.setVisible(false);
                                    lblNomeAlbum.setVisible(false);
                                }
                                      
                                
                                
                                
                                if(media.getMetadata().get("image") != null)
                                {
                                   
                                    imgCapa.setVisible(true);
                                    
                                    imgCapa.setImage((Image)media.getMetadata().get("image"));
                                    
                                }
                                 else
                                {
                                    imgCapa.setVisible(false);
                                }
                                
                                }catch (NumberFormatException| IndexOutOfBoundsException ex){}
                                
                                
                            }
                             else
                                {
                                    lblTempo.setVisible(false);
                                    lblTempoMsg.setVisible(false);
                                    mediaPlayer.stop();
                                    lblContador.setVisible(false);
                                    lblStatus.setVisible(false);
                                    lblMusica.setVisible(false);
                                    lblArtista.setVisible(false);
                                    lblNomeArtista.setVisible(false);
                                    lblMusicaMsg.setVisible(false);
                                    lblAno.setVisible(false);
                                    lblAnoMsg.setVisible(false);
                                    lblalbumMsg.setVisible(false);
                                     lblNomeAlbum.setVisible(false);
                                     imgCapa.setVisible(false);
                                      sldMusica.setVisible(false);
                                    
                                }
                            
                            
                            mediaPlayer.currentTimeProperty().addListener((Observable ov) -> {
                           
                            updateValues();
                            String tempoTotal= formatTime(media.getDuration(), duration);
                            String tempoDecorrido = formatTime( mediaPlayer.getCurrentTime(), duration);
                           
                            int tempoFinal = Integer.parseInt(tempoTotal.replace(":", ""));
                            int tempoCorrente = Integer.parseInt(tempoDecorrido.replace(":", ""));
                        
                            
                            
                          
                            
                            if(tocando == 1 && tempoCorrente >= tempoFinal )
                            {
                               mediaPlayer.stop();
                               tocando =0;
                               lblStatus.setVisible(false);
                               btnPlay.setGraphic(new ImageView(play));
                               
                               if(musicas !=null  && tocando ==0)
                                 {
                                     
                                    posicao++;
                                   btnPlay.setGraphic(new ImageView(play));  
                                 }
                               
                              
                            }
                           
                           
                            });       
                             
                         
				if(!musicas.isEmpty() && tocando==0 )
                                {
                                   
                                   
                                    if(posicao >0 && posicao < musicas.size())
                                    {
                                        musica = musicas.get(posicao).toURI().toString();
                                        lblMusica.setVisible(true);
                                        lblMusica.setText(musicas.get(posicao).getName());
                                        media = new Media(musica);
                                        mediaPlayer = new MediaPlayer(media);
                                        mediaPlayer.setVolume(mediaPlayer.getVolume());
                                        lblStatus.setVisible(true);
                                        lblStatus.setText("Tocando");
                                         mediaPlayer.play();
                                         tocando=1;
                                            
                                       arquivoTocando =  musicas.get(posicao);
                                             
                                          
                                         
                                    }
                                }
                                
                                if( musicas != null)
                                {   
                                  if(posicao > musicas.size())
                                  {
                                      mediaPlayer.stop();
                                      tocando = 0;
                                      lblStatus.setText("Fim da Playlist");
                                       btnPlay.setGraphic(new ImageView(play));
                                  }
				}
                                }
                              
                                 }
		    		});	
                                	
		    			if(i==9)
		    			{
		    			   i=0;
		    			}
		    		}
					return null;
		    	}
		     };

 

private void playlistExpandido()
{
    txtLetra.setVisible(false);
    llstPlaylist.setPrefSize(215, 420);
    toolPlaylist.setLayoutY(240);
}


private void playlistReduzido()
{
    llstPlaylist.setPrefSize(215, 203);
    toolPlaylist.setLayoutY(133);
    txtLetra.setVisible(true);
}

private void inicializar()
{
    lblMusica.setVisible(false);
     lblStatus.setVisible(false);
     lblTempoMsg.setVisible(false);
     lblTempo.setVisible(false);
     lblPlaylist.setText("Sem playlist Carregada...");
     lblArtista.setVisible(false);
     lblNomeArtista.setVisible(false);
     lblMusicaMsg.setVisible(false);
     lblAno.setVisible(false);
     lblAnoMsg.setVisible(false);
     lblalbumMsg.setVisible(false);
    lblNomeAlbum.setVisible(false);
    imgCapa.setVisible(false);
    lblLetraMsg.setVisible(false);
     btnLetra.setDisable(true);
     sldMusica.setVisible(false);

      sldMusica.setValue(0.0);
      
     
     logo();
     String homeZeta;
    
     
    

      homeZeta = System.getProperty("user.home")+"/Documents/Zeta Player/"; 
      plsZeta = new File(homeZeta);
      plsZeta.mkdir();
      
      if(plsZeta.exists())
      {
          plsZeta = new File(homeZeta+"playlists");
          plsZeta.mkdir();
      }
   
     luzTela.setLightOn(true);
     luzTela.setColor(Color.GREENYELLOW);
     play = new Image(getClass().getResourceAsStream("start.png"));
     stop = new Image(getClass().getResourceAsStream("stop.png"));
     pause = new Image(getClass().getResourceAsStream("pause.png"));
     forward = new Image(getClass().getResourceAsStream("forward.png"));
     backward = new Image(getClass().getResourceAsStream("backward.png"));
     next = new Image(getClass().getResourceAsStream("skip-forward.png"));
     back = new Image(getClass().getResourceAsStream("skip-backward.png"));
     abrir = new Image(getClass().getResourceAsStream("open.png"));
     lista = new Image(getClass().getResourceAsStream("list.png"));
     excluir = new Image(getClass().getResourceAsStream("remove.png"));
     alto = new Image(getClass().getResourceAsStream("alto.png"));
     baixo = new Image(getClass().getResourceAsStream("baixo.png"));
     medio = new Image(getClass().getResourceAsStream("meio.png"));
     
     btnPlay.setGraphic(new ImageView(play));
     btnStop.setGraphic(new ImageView(stop));
//     btnForward.setGraphic(new ImageView(forward));
//     btnBackward.setGraphic(new ImageView(backward));
     btnBack.setGraphic(new ImageView(back));
     btnNext.setGraphic(new ImageView(next));
     btnAbrir.setGraphic(new ImageView(abrir));
     btnPlaylist.setGraphic(new ImageView(lista));
     btnLimpar.setGraphic(new ImageView(excluir));
     lblVolume.setGraphic(new ImageView(medio));
    
     llstPlaylist.getStyleClass().add("list-cell");
    
    btnLimpar.setDisable(true);
    
    
    playlistExpandido();
    
    
}




    @Override
    public void initialize(URL location, ResourceBundle resources) {
     
     inicializar();
     Thread treadPlaylist = new Thread(playlist);
     Thread theadZeta = new Thread(zeta);
    
    telaPlayer.getStage().setOnShowing(new EventHandler<WindowEvent>() {

         @Override
         public void handle(WindowEvent event) {
              Platform.runLater(new Runnable() {
                  @Override
                  public synchronized void run() {
                      
                      treadPlaylist.start();
                      theadZeta.start();
    			            
                  }
                });
				
         }
     });
    
    
    telaPlayer.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {

         @Override
         public void handle(WindowEvent event) {
             System.exit(0);
         }
     });
    
    
    
    btnLimpar.setOnAction(new EventHandler<ActionEvent>() {

         @Override
         public void handle(ActionEvent event) {
         
            llstPlaylist.getItems().clear();
             mediaPlayer.stop();
             tocando =-1;
             lblContador.setText("");
             lblMusica.setText("");
             lblTempo.setText("");
             lblTempoMsg.setText("");
             lblStatus.setText("");
             mediaPlayer=null;
            
             lblPlaylist.setText("Sem playlist Carregada...");
             arquivos = new ArrayList();
             arquivo = null;
             musicas = new ArrayList();
             lblArtista.setVisible(false);
            lblNomeArtista.setVisible(false);
            lblMusicaMsg.setVisible(false);
            lblAno.setVisible(false);
            lblAnoMsg.setVisible(false);
             lblalbumMsg.setVisible(false);
            lblNomeAlbum.setVisible(false);
            imgCapa.setVisible(false);
            txtLetra.setText("");
             btnPlay.setGraphic(new ImageView(play));
             lblLetraMsg.setVisible(false);
            sldMusica.setVisible(false);
            sldMusica.setValue(0.0);
            btnExibirLetra.setStyle("-fx-background-color: white");
            btnExibirLetra.setStyle("-fx-background-radius: 12em");
             logo(); 
             
         }
     });
    
    
    
    
    btnAbrir.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				     FileChooser diretorio = new FileChooser();
				    diretorio.getExtensionFilters().add(new FileChooser.ExtensionFilter("Músicas MP3, M4a","*.mp3","*.m4a"));
				    diretorio.setTitle("Abrir música");
                                    arquivo = diretorio.showOpenDialog(telaPlayer.getStage());
				      if((tocando==1 || tocando==2) && arquivo != null)
                                        {
                                            mediaPlayer.stop();
                                            llstPlaylist.getItems().clear();
                                            musicas = new ArrayList<File>();
                                            musica = arquivo.toURI().toString();
                                            media = new Media(musica);
                                            mediaPlayer = new MediaPlayer(media);
                                            tocando=0;
                                            lblStatus.setText("");
                                            mediaPlayer.setVolume(mediaPlayer.getVolume());
                                            tipo = "musica";
                                            
                                        
                                        }
                                      else if(arquivo != null)
                                      {
                                            musica = arquivo.toURI().toString();

                                            media = new Media(musica);
                                            mediaPlayer = new MediaPlayer(media);
                                            tocando=0;
                                            mediaPlayer.setVolume(mediaPlayer.getVolume());
                                            tipo = "musica";
                                      }
                                      
                                       if(lblMusica.getText().trim().length() >100)
                                       {
                                           lblMusica.setLayoutX(140);
                                           String tamanho = lblMusica.getText().substring(140, 30);
                                           lblMusica.setText(tamanho);
                                       }
                                      arquivoTocando = arquivo;
                                      
                                       if(arquivo != null)
                                         {
                                
                                             
                                             llstPlaylist.getItems().addAll(arquivo.getName().replace(".mp3", ""));
                                       
                                          }
                                       posicao =0;
                                       musicas.add(arquivo);
                                       
			
                        }
			  
		});

    
    btnPlaylist.setOnMouseEntered(new EventHandler<MouseEvent>() {

         @Override
         public void handle(MouseEvent event) {
             Tooltip tooltip = new Tooltip();
             tooltip.setText("Montar Playlist");
             btnPlaylist.setTooltip(tooltip);
             
         }
     });
    
     btnSalvar.setOnMouseEntered(new EventHandler<MouseEvent>() {

         @Override
         public void handle(MouseEvent event) {
             Tooltip tooltip = new Tooltip();
             tooltip.setText("Salvar Playlist");
             btnSalvar.setTooltip(tooltip);
             
         }
     });
    
     
      btnSalvar.setOnMouseEntered(new EventHandler<MouseEvent>() {

         @Override
         public void handle(MouseEvent event) {
             Tooltip tooltip = new Tooltip();
             tooltip.setText("Salvar Playlist");
             btnSalvar.setTooltip(tooltip);
             
         }
     });
    
    
     btnCarregar.setOnMouseEntered(new EventHandler<MouseEvent>() {

         @Override
         public void handle(MouseEvent event) {
             Tooltip tooltip = new Tooltip();
             tooltip.setText("Carregar Playlist");
             btnCarregar.setTooltip(tooltip);
             
         }
     });
    
    
    btnAbrir.setOnMouseEntered(new EventHandler<MouseEvent>() {

         @Override
         public void handle(MouseEvent event) {
             Tooltip tooltip = new Tooltip();
             tooltip.setText("Abrir Música");
             btnAbrir.setTooltip(tooltip);
             
         }
     });
    
   
     btnAdicionar.setOnMouseEntered(new EventHandler<MouseEvent>() {

         @Override
         public void handle(MouseEvent event) {
             Tooltip tooltip = new Tooltip();
             tooltip.setText("Adicionar Música");
             btnAdicionar.setTooltip(tooltip);
             
         }
     });
    
      btnRemover.setOnMouseEntered(new EventHandler<MouseEvent>() {

         @Override
         public void handle(MouseEvent event) {
             Tooltip tooltip = new Tooltip();
             tooltip.setText("Remover Música");
             btnRemover.setTooltip(tooltip);
             
         }
     });
    
     
      
     btnPlay.setOnMouseEntered(new EventHandler<MouseEvent>() {

         @Override
         public void handle(MouseEvent event) {
             
              btnPlay.setScaleX(1.1);
             crcPlay.setScaleX(1.1);
             crcPlay.setScaleY(1.1);
             btnPlay.setScaleY(1.1);
         }
     });
      
      btnPlay.setOnMouseExited(new EventHandler<MouseEvent>() {

         @Override
         public void handle(MouseEvent event) {
              btnPlay.setScaleX(1.0);
             btnPlay.setScaleY(1.0);
               crcPlay.setScaleX(1.0);
              crcPlay.setScaleY(1.0);
         }
     });
    btnNext.setOnMouseEntered(new EventHandler<MouseEvent>() {

         @Override
         public void handle(MouseEvent event) {
            btnNext.setScaleX(1.2);
             btnNext.setScaleY(1.2);
         }
     });
     
    
     btnNext.setOnMouseExited(new EventHandler<MouseEvent>() {

         @Override
         public void handle(MouseEvent event) {
            btnNext.setScaleX(1.0);
             btnNext.setScaleY(1.0);
         }
     });
     
     btnBack.setOnMouseEntered(new EventHandler<MouseEvent>() {

         @Override
         public void handle(MouseEvent event) {
            btnBack.setScaleX(1.2);
             btnBack.setScaleY(1.2);
         }
     });
     
     btnBack.setOnMouseExited(new EventHandler<MouseEvent>() {

         @Override
         public void handle(MouseEvent event) {
            btnBack.setScaleX(1.0);
            btnBack.setScaleY(1.0);
         }
     });
     
     
     
       btnMute.setOnMouseEntered(new EventHandler<MouseEvent>() {

         @Override
         public void handle(MouseEvent event) {
            btnMute.setScaleX(1.2);
             btnMute.setScaleY(1.2);
            crcMute.setScaleX(1.2);
              crcMute.setScaleY(1.2); 
         }
     });
     
     btnMute.setOnMouseExited(new EventHandler<MouseEvent>() {

         @Override
         public void handle(MouseEvent event) {
            btnMute.setScaleX(1.0);
            btnMute.setScaleY(1.0);
             crcMute.setScaleX(1.0);
              crcMute.setScaleY(1.0);
         }
     });
     
       btnStop.setOnMouseEntered(new EventHandler<MouseEvent>() {

         @Override
         public void handle(MouseEvent event) {
            
            
            
             btnStop.setScaleX(1.2);
             btnStop.setScaleY(1.2);
             crcStop.setScaleX(1.2);
              crcStop.setScaleY(1.2);
              
          
              
         }
     });
     
     
     
     btnStop.setOnMouseExited(new EventHandler<MouseEvent>() {

         @Override
         public void handle(MouseEvent event) {
            btnStop.setScaleX(1.0);
            btnStop.setScaleY(1.0);
            crcStop.setScaleX(1.0);
            crcStop.setScaleY(1.0);
           
            
         }
     });
     
     
//     
//     
//     btnForward.setOnMouseExited(new EventHandler<MouseEvent>() {
//
//         @Override
//         public void handle(MouseEvent event) {
//            btnForward.setScaleX(1.0);
//            btnForward.setScaleY(1.0);
//         }
//     });
//     
//      btnForward.setOnMouseEntered(new EventHandler<MouseEvent>() {
//
//         @Override
//         public void handle(MouseEvent event) {
//            btnForward.setScaleX(1.2);
//             btnForward.setScaleY(1.2);
//         }
//     });
     
//    btnBackward.setOnMouseExited(new EventHandler<MouseEvent>() {
//
//         @Override
//         public void handle(MouseEvent event) {
//            btnBackward.setScaleX(1.0);
//            btnBackward.setScaleY(1.0);
//         }
//     });
     
//      btnBackward.setOnMouseEntered(new EventHandler<MouseEvent>() {
//
//         @Override
//         public void handle(MouseEvent event) {
//            btnBackward.setScaleX(1.2);
//             btnBackward.setScaleY(1.2);
//         }
//     });
    
     
    
     
     
    btnPlaylist.setOnAction(new EventHandler<ActionEvent>() {

         @Override
         public void handle(ActionEvent event) {
             
                    FileChooser diretorio = new FileChooser();
                    diretorio.getExtensionFilters().add(new FileChooser.ExtensionFilter("Músicas MP3, M4a","*.mp3","*.m4a"));
                    diretorio.setTitle("Criar Playlist");
                   
                    carrega =  diretorio.showOpenMultipleDialog(telaPlayer.getStage());
                    if(carrega !=null)
                    {    
                        musicas = new ArrayList();
                        arquivos = new ArrayList();
                        arquivos = new ArrayList<File>(carrega);
                        if((tocando==1 || tocando==2))
                         {
                        llstPlaylist.getItems().clear();
                        mediaPlayer.stop();
                        
                        for(File file:arquivos)
                        {
                            musicas.add(file);
                           
                        }  
                    
                        musica = musicas.get(0).toURI().toString();
//                        lblMusica.setVisible(true);
//                        lblMusica.setText(musicas.get(0).getName().replace(".mp3", ""));
                        media = new Media(musica);
                        mediaPlayer = new MediaPlayer(media);
                        tocando=0;
                        posicao = 0;
                        mediaPlayer.setVolume(mediaPlayer.getVolume());
                        arquivoTocando = musicas.get(0);
                        tipo = "playlist";
                         
                       
                     }
                     else if(arquivos != null)
                     {
                        for(File file:arquivos)
                        {
                            musicas.add(file);
                            
                        }  
                        
                        musica = musicas.get(0).toURI().toString();
                        lblMusica.setVisible(true);
                        lblMusica.setText(musicas.get(0).getName().replace(".mp3", ""));
                        media = new Media(musica);
                        mediaPlayer = new MediaPlayer(media);
                        tocando=0;
                        posicao = 0;
                        mediaPlayer.setVolume(mediaPlayer.getVolume());
                        arquivoTocando = musicas.get(0);
                        tipo = "playlist";
                        
                         
                     }
                     
                             for(File file: musicas)
                             {
                                //Stream.of(file.getName().replace(".mp3", "")).forEach(llstPlaylist.getItems()::add);
                                
                                llstPlaylist.getItems().addAll(file.getName().replace(".mp3", ""));
                                
                             }
                            
                         maximo =   llstPlaylist.getItems().size();
                    }                  
     }
     });
        
        btnPlay.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
             
                  
                lblContador.setVisible(true);
                
                 if(tipo ==  "nao escolhido") 
                 {
                     lblStatus.setVisible(true);
                     lblStatus.setText("Sem Música");
                     return;
                 }
                
                  if(tipo == "musica" &&  arquivo == null) 
                    {
                      lblStatus.setVisible(true);
                     lblStatus.setText("Sem Música");
                     return;
                    }
                  
                  if(tipo == "playlist" &&  arquivos == null) 
                    {
                     lblStatus.setVisible(true);
                     lblStatus.setText("Sem Música");
                     return;
                    }
                
                if(volumeMax == null )
                {
                    mediaPlayer.setVolume(0.5);
                }
                else if(volumeMin == null)
                {
                    mediaPlayer.setVolume(0.5);
                }
                else if(mediaPlayer != null)
                {
                    mediaPlayer.setVolume(mediaPlayer.getVolume());
                }
               
                
                    if(tocando==1 || tocando==2)
                    {
                          mediaPlayer.pause();
                          tocando =-1;
                          lblStatus.setText("Pausado");
                          btnPlay.setGraphic(new ImageView(play));
                    }
                    else if(tocando == 0 || tocando== -1)
                    {
                        tocando =0;
                        mediaPlayer.play();
                        tocando =1;
                        lblStatus.setVisible(true);
                        lblStatus.setText("Tocando");
                        btnPlay.setGraphic(new ImageView(pause));
                       
                    
                            mediaPlayer.setOnReady(() -> {
                            duration = mediaPlayer.getMedia().getDuration();
                             
                            updateValues();
                          

                            });
                   }
                   
                 }
           });
  
 
     btnStop.setOnAction(new EventHandler<ActionEvent>() {

         @Override
         public void handle(ActionEvent event) {
            
                 if(!mediaPlayer.isMute())
                 {    
                   mediaPlayer.stop();
                   tocando=-1;
                   lblStatus.setText("Parado");
                 }
                 else  if(mediaPlayer.isMute() == true)
                    {
                        mediaPlayer.setMute(false);
                    }
                     btnPlay.setGraphic(new ImageView(play));
         }
     });

      
//     btnForward.setOnAction(new EventHandler<ActionEvent>() {
//
//         @Override
//         public void handle(ActionEvent event) {
//        
//             if(tocando==1 ||  tocando==2)
//              {
//                   mediaPlayer.seek(mediaPlayer.getCurrentTime().multiply(1.5));
//                  
//              }
//             
//         }
//     });
//     
     
//      btnBackward.setOnAction(new EventHandler<ActionEvent>() {
//
//         @Override
//         public void handle(ActionEvent event) {
//        
//             if(tocando==1  || tocando==2)
//              {
//                  mediaPlayer.seek(mediaPlayer.getCurrentTime().divide(1.5));
//                  
//              }
//             
//         }
//     });
         
     btnMute.setOnAction(new EventHandler<ActionEvent>() {

         @Override
         public void handle(ActionEvent event) {
             if(mediaPlayer.isMute() == false)
             {
                 mediaPlayer.setMute(true);
                  lblStatus.setText(" )))Mute(((");
                 
             }
             else{
                 mediaPlayer.setMute(false);
                  lblStatus.setText("Tocando");
                  
                  
             }
             
         }
     });
     
     
     this.btnNext.setOnMouseClicked( new EventHandler<MouseEvent>() {

         @Override
         public void handle(MouseEvent event) {
             if( posicao < musicas.size() )
             {
                 
                  try{
                  btnBack.setDisable(false);
                  
                  mediaPlayer.stop();
                  lblStatus.setText("Parado");
                //  posicao++;
                  mediaPlayer.stop();
                  tocando = 0;
                   musica = musicas.get(posicao).toURI().toString();
//                  lblMusica.setVisible(true);
//                  lblMusica.setText(musicas.get(posicao).getName());
                  media = new Media(musica);
                  mediaPlayer = new MediaPlayer(media);
                  mediaPlayer.setVolume(mediaPlayer.getVolume());
                  lblStatus.setVisible(true);
                  lblStatus.setText("Tocando");
                  mediaPlayer.play();
                  tocando=1;
                 posicaoVolta = 0;
                  
                  }catch (IndexOutOfBoundsException e){}
                         
             }
              else
                  {
                    btnNext.setDisable(true);
                }
         }
     });
      
     this.btnBack.setOnMouseClicked(new EventHandler<MouseEvent>() {

         @Override
         public void handle(MouseEvent event) {
           
             if(event.getClickCount() ==2)
             {
                if(posicao >= 0 )
                {
                
                 try{
                     btnNext.setDisable(false);
                     mediaPlayer.stop();
                    tocando = 0;
                     lblStatus.setText("Parado");
                      posicao--;
                      musica = musicas.get(posicao).toURI().toString();
//                      lblMusica.setVisible(true);
//                      lblMusica.setText(musicas.get(posicao).getName());
                      media = new Media(musica);
                      mediaPlayer = new MediaPlayer(media);
                      mediaPlayer.setVolume(mediaPlayer.getVolume());
                     lblStatus.setVisible(true);
                     lblStatus.setText("Tocando");
                     mediaPlayer.play();
                     tocando=1;
                     posicaoVolta = 1;
                 }catch(ArrayIndexOutOfBoundsException ex){}        
             }
              else
                  {
                    btnBack.setDisable(true);
                }
         }
             else if(event.getClickCount() == 1)
             {
                 mediaPlayer.stop();
                 mediaPlayer.play();
                 
             }
                 
             
         }  
     });
     
     
    
     
     lblMax.setOnMouseEntered(new EventHandler<MouseEvent>() {

         @Override
         public void handle(MouseEvent event) {
           
             lblMax.setScaleX(1.5);
             lblMax.setScaleY(1.5);
         }
     });
     
     
     lblMax.setOnMouseExited(new EventHandler<MouseEvent>(){
         
         
         @Override
         public void handle(MouseEvent event) {
           
             lblMax.setScaleX(1.0);
             lblMax.setScaleY(1.0);
         }
     });
     
     
       lblMin.setOnMouseEntered(new EventHandler<MouseEvent>() {

         @Override
         public void handle(MouseEvent event) {
           
             lblMin.setScaleX(1.5);
             lblMin.setScaleY(1.5);
         }
     });
     
     
     lblMin.setOnMouseExited(new EventHandler<MouseEvent>(){
         
         
         @Override
         public void handle(MouseEvent event) {
           
             lblMin.setScaleX(1.0);
             lblMin.setScaleY(1.0);
         }
     });
     
     
     
     
    lblMax.setOnMouseClicked(new EventHandler<MouseEvent>() {

         @Override
         public void handle(MouseEvent event) {
           
             if(tocando==1 ||tocando==2 )
             {    
                volumeMax=mediaPlayer.getVolume();  
                lblMin.setDisable(false);
                if(volumeMax<=1.0)
                {    
                    volumeMax=volumeMax+0.1;
                    mediaPlayer.setVolume(volumeMax);
                }
                
                 if (mediaPlayer.getVolume() < 0.6)
                {
                    
                    lblVolume.setGraphic(new ImageView(medio));
                    
                }
                
               if (mediaPlayer.getVolume() > 1.0)
                {
                    lblMax.setDisable(true);
                    lblVolume.setGraphic(new ImageView(alto));
                    mediaPlayer.setVolume(1.0);
                    return;
                }
                
                System.out.println(volumeMax);
             }
         }
     });
    
     lblMin.setOnMouseClicked(new EventHandler<MouseEvent>() {

         @Override
         public void handle(MouseEvent event) {
           
             if(tocando==1 ||tocando==2 )
             {    
                lblMax.setDisable(false);
                volumeMin=mediaPlayer.getVolume(); 
                if(volumeMin > 0.0)
                {    
                    
                    volumeMin=volumeMin-0.1;
                    mediaPlayer.setVolume(volumeMin);
                }
               if (mediaPlayer.getVolume() < 0.6)
                {
                    
                    lblVolume.setGraphic(new ImageView(medio));
                    
                }
                if (mediaPlayer.getVolume() < 0.0)
                {
                
                    lblMin.setDisable(true);
                    lblVolume.setGraphic(new ImageView(baixo));
                    mediaPlayer.setVolume(0.0);
                    return;
                
                }
                }
                
                System.out.println(volumeMin);
             }
         
     });
    
    
     
     
     
     
     this.llstPlaylist.setOnMouseClicked(new EventHandler<MouseEvent>() {

         @Override
         public void handle(MouseEvent event) {
             
           
             posicaoMouse =  llstPlaylist.getSelectionModel().getSelectedIndex();
             
           try{  
              mediaPlayer.stop();
              posicao = posicaoMouse;
              tocando = 0;
              musica = musicas.get(posicaoMouse).toURI().toString();
              media = new Media(musica);
              mediaPlayer = new MediaPlayer(media);
              mediaPlayer.setVolume(mediaPlayer.getVolume());
              lblStatus.setVisible(true);
              lblStatus.setText("Tocando");
              tocando =1;
              mediaPlayer.play();
              
            
           }catch(IndexOutOfBoundsException ex) {}
         }
          
            
            
     });
     
     
     
     
    
       this.llstPlaylist.setOnDragOver (new EventHandler<DragEvent>() {

         @Override
         public void handle(DragEvent event) {
            
             Dragboard db = event.getDragboard();
             
             try{
               
                
             
             
             if(db.hasFiles() && (db.getFiles().toString().contains(".mp3") || db.getFiles().toString().contains(".m4a")))
             {
                 event.acceptTransferModes(TransferMode.COPY);
                
             }
              else
             {
                  event.acceptTransferModes(TransferMode.NONE);
                  db.clear();
                  
                  
                  
             }
             } catch(Exception ex) {}
             event.consume();
             
         }
     });
     
     
     
     
     
     this.llstPlaylist.setOnDragDropped(new EventHandler<DragEvent>() {

        
         @Override
         public void handle(DragEvent event) {
             
          Dragboard db = event.getDragboard();
           List<File>adiciona = new ArrayList<>();
          
           
         
           
           adiciona = db.getFiles();
          
           
           
           if(db.hasFiles())
          {
                
                 playmusicas = new ArrayList();
                 playmusicas = new ArrayList<File>(adiciona);
                    
                 
                   for(int arquivo = 0;arquivo < playmusicas.size();arquivo++)
                   {
                       if(!playmusicas.get(arquivo).toString().contains(".mp3") && !playmusicas.get(arquivo).toString().contains(".m4a"))
                       {
                           playmusicas.remove(playmusicas.get(arquivo));
                       }
                   }
                 
                 
                 
                   for(File file: playmusicas)
                   {
                       musicas.add(file);
                        
                   }
                    
                  
                   if(llstPlaylist.getItems().isEmpty())
                      {    
                        arquivos = musicas;
                        musica = musicas.get(0).toURI().toString();
                        media = new Media(musica);
                        mediaPlayer = new MediaPlayer(media);
                        
                         tocando = 0;
                         posicao = 0; 
                        tipo="playlist";
                      }
                    llstPlaylist.autosize();
                    
                    
                    for(File file:playmusicas)
                    {   
                          llstPlaylist.getItems().add(file.getName().replace(".mp3", "")); 
                         
                           
                         
                    }
                   
                     
              }
             event.consume();
             db.clear();
           
            
         }

         
     });

        
             
      
     
     this.btnRemover.setOnAction(new EventHandler<ActionEvent>() {

         @Override
         public void handle(ActionEvent event) {
             
            try{ 
               
                if(posicao == posicaoMouse )
                {   
                    if(tocando == 1 || tocando==2)
                 {
                     
                  mediaPlayer.stop();
                  tocando = -1;
                 
                  musicas.remove(posicaoMouse);
                  
                  ArrayList musicas_nova = new ArrayList(musicas);
                  ArrayList arquivos_novo = new ArrayList(arquivos);
                  musicas.clear();
                  arquivos.clear();
                  musicas = new ArrayList(musicas_nova);
                  arquivos = new ArrayList(arquivos_novo);
                  tocando =0;
               
                
                  try{
                   llstPlaylist.getItems().clear();
                                  
                       for(File file: musicas)
                            {
                                
                                llstPlaylist.getItems().add(file.getName().replace(".mp3", ""));

                              maximo = llstPlaylist.getItems().size();

                              System.out.println(file.getName());
                              System.out.println(posicaoMouse);
                          } 
                  
                  
                  System.out.println(posicaoMouse);
                  
                 } catch(NullPointerException ex){}
                 }
             } 
            }catch(IndexOutOfBoundsException ex){}
             
           
         }
     } );
     
     
     this.btnAdicionar.setOnAction( new EventHandler<ActionEvent>() {

         @Override
         public void handle(ActionEvent event) {
        
             try{
                FileChooser diretorio = new FileChooser();
                diretorio.getExtensionFilters().add(new FileChooser.ExtensionFilter("Músicas MP3, M4a","*.mp3","*.m4a"));
                diretorio.setTitle("Adicionar musicas");
                List<File>adiciona = new ArrayList<>();
              
                    adiciona =  diretorio.showOpenMultipleDialog(telaPlayer.getStage());
                    playmusicas = new ArrayList();
                    
                    playmusicas = new ArrayList<File>(adiciona);
                    
                 
                    
                   for(File file: playmusicas)
                   {
                       musicas.add(file);
                        
                   }
                    
                  llstPlaylist.autosize();
                   
                    for(File file:playmusicas)
                    {   
                          llstPlaylist.getItems().add(file.getName().replace(".mp3", "")); 
                           
                         
                    }
                
                  
             }catch(IllegalArgumentException ex) {}  
                       
                        
         }
     });
     
     
     
     this.btnSalvar.setOnAction(new EventHandler<ActionEvent>() {

         @Override
         public void handle(ActionEvent event) {
             
              FileChooser diretorio = new FileChooser();
             
              diretorio.setInitialDirectory(plsZeta);
              diretorio.getExtensionFilters().add(new FileChooser.ExtensionFilter("Arquivos pys","*.pys"));
              
              diretorio.setTitle("Salvar Playlist");
              File file = diretorio.showSaveDialog(telaPlayer.getStage());
              FileWriter fileWriter = null;
            

    
              
              if(file != null)
              { 
                try {
                    fileWriter = new FileWriter(file);
                    String playlist;
                    playlist= musicas.toString();
                    StringBuilder retirarConchetes = new StringBuilder(playlist);
                    retirarConchetes.insert(playlist.indexOf("["),"|");
                     retirarConchetes.insert(playlist.lastIndexOf("]")+2,"|");
                   fileWriter.write(retirarConchetes.toString().replace("|[", "").replace("]|", "").replace(", null", ""));
              
               fileWriter.close();
               } catch (IOException ex) {
                
             }
            }
         }
     });
    
     this.btnCarregar.setOnAction(new EventHandler<ActionEvent>() {

         @Override
         public void handle(ActionEvent event) {
         
             try {
                    FileChooser diretorio = new FileChooser();
                    File carregaPlaylist ;
                    File arquivo = null;
                    List<String>carrega = new ArrayList<>();
                    List<File>arqplay = new ArrayList<>();
                    diretorio.setInitialDirectory(plsZeta);
                    diretorio.getExtensionFilters().add(new FileChooser.ExtensionFilter("Arquivos pys","*.pys"));
                    diretorio.setTitle("Carregar Playlist");
                    carregaPlaylist =  diretorio.showOpenDialog(telaPlayer.getStage());
                    FileReader fr = new FileReader(carregaPlaylist);
                    BufferedReader br = new BufferedReader(fr);
                    String play;
                    String [] plays;
                    carregaPlaylist.list();
                   
                    while ((play = br.readLine()) != null)
                    {
                        
                        plays = play.split(",");
                        
                        
                        for(String c: plays)
                        {    
                            
                           
                            String importar = c.toString().trim();
                            carrega.add(importar);
                            
                        } 
                        
                        for(int i=0; i<carrega.size();i++)
                        {
                            File file = new File(carrega.get(i).toString());
                            arquivos.add(file);
                            
                         
                        }
                          System.out.println(arquivos.get(0).toURI());
                    }
                          br.close();
                          fr.close();
                        
                      
                        lblPlaylist.setVisible(true);
                        lblPlaylist.setText("Playlist: "+carregaPlaylist.getName().replace(".pys",""));
                        musicas = arquivos;
                        maximo = musicas.size();
                        musica = musicas.get(0).toURI().toString();
                        media = new Media(musica);
                        mediaPlayer = new MediaPlayer(media);
                        tocando=0;
                        posicao = 0;
                        tipo="playlist";
                 
                     
                 if(llstPlaylist != null)
                 {  
                        for(File file: musicas)
                             {
                              
                                llstPlaylist.getItems().addAll(file.getName().replace(".mp3", ""));
                                
                             }
                      maximo=  llstPlaylist.getItems().size();
                 }                   
                   
                    
             } catch (IOException ex) {
                 Logger.getLogger(TelaPlayerControler.class.getName()).log(Level.SEVERE, null, ex);
             }    
         }
     });
     
     
     
     btnLetra.setOnAction(new EventHandler<ActionEvent>() {

         @Override
         public synchronized void handle(ActionEvent event) {
            
                 if(mediaPlayer != null)
                    {  
                 
                            btnLetra.setCursor(Cursor.WAIT);
                            Letra letra = new Letra();
                            List <String> lista = null;
                
                
                        try {
                            lista = letra.getLetra(media.getMetadata().get("artist").toString(),media.getMetadata().get("title").toString());
                            txtLetra.setText(lista.toString());
                            btnExibirLetra.requestFocus();
                            btnExibirLetra.setStyle("-fx-background-color: yellow");
                           
                    
                             } catch ( NullPointerException e) {
                                Logger.getLogger(TelaPlayerControler.class.getName()).log(Level.SEVERE, null, e);
                            lblLetraMsg.setVisible(true);
                            btnExibirLetra.setStyle("-fx-background-color: white");
                            playlistExpandido();
                            
                        } catch (IOException ex) {
                        Logger.getLogger(TelaPlayerControler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                            if(lista == null || media == null)
                            {
                                lblLetraMsg.setVisible(true);
                            if(txtLetra.getText().length() > 0 )
                            {
                                txtLetra.setText("");
                                btnExibirLetra.setStyle("-fx-background-color: white");
                                playlistExpandido();
                                
                            }
                        }
                        else{
                                 lblLetraMsg.setVisible(false);
                                  btnExibirLetra.setStyle("-fx-background-color: yellow");
                                  
                            }
                            btnLetra.setCursor(Cursor.DEFAULT);
                        }
                        }
                                
               
         
     });
     
     btnEq.setOnAction(new EventHandler<ActionEvent>() {

         @Override
         public void handle(ActionEvent event) {
            Equalizador equalizador = new Equalizador();
            Stage estado  = new Stage();
             try {
                 equalizador.start(estado);
             } catch (Exception ex) {
                 Logger.getLogger(TelaPlayerControler.class.getName()).log(Level.SEVERE, null, ex);
             }
            
             
         }
     });
     
     
     
     
   stackTela.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Stage stage = new Stage();
                
                
                
                xOffset = telaPlayer.getStage().getX() - event.getScreenX();
                yOffset = telaPlayer.getStage().getY() - event.getScreenY();
            }
        });
   
   
   stackTela.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                telaPlayer.getStage().setX(event.getScreenX() + xOffset);
                telaPlayer.getStage().setY(event.getScreenY() + yOffset);
            }
        });
   
   
   btnMinimizar.setOnAction(new EventHandler<ActionEvent>() {

         @Override
         public void handle(ActionEvent event) {
              telaPlayer.getStage().setIconified(true);
         }
     });

   
    btnMinimizar.setOnMouseEntered(new EventHandler<MouseEvent>() {

         @Override
         public void handle(MouseEvent event) {
            
               btnMinimizar.setScaleX(1.3);
               btnMinimizar.setScaleY(1.3);
               Tooltip tooltip = new Tooltip();
               tooltip.setText("Minimizar");
             btnMinimizar.setTooltip(tooltip);
             btnMinimizar.setText("-");
               
             
         }
     }
    );
   
    btnMinimizar.setOnMouseExited(new EventHandler<MouseEvent>() {

         @Override
         public void handle(MouseEvent event) {
            
               btnMinimizar.setScaleX(1.0);
               btnMinimizar.setScaleY(1.0);
                 btnMinimizar.setText("");
               
             
         }
     }
    );
    

    btnFechar.setOnAction(new EventHandler<ActionEvent>() {

         @Override
         public void handle(ActionEvent event) {
             
             System.exit(0);
          
         }
     });
    
    
     btnFechar.setOnMouseEntered(new EventHandler<MouseEvent>() {

         @Override
         public void handle(MouseEvent event) {
            
               btnFechar.setScaleX(1.3);
               btnFechar.setScaleY(1.3);
               Tooltip tooltip = new Tooltip();
               tooltip.setText("Fechar");
             btnFechar.setTooltip(tooltip);
               
             
         }
     }
    );
   
    btnFechar.setOnMouseExited(new EventHandler<MouseEvent>() {

         @Override
         public void handle(MouseEvent event) {
            
               btnFechar.setScaleX(1.0);
               btnFechar.setScaleY(1.0);
               
             
         }
     }
    );
    
    
    btnExibirLetra.setOnAction(new EventHandler<ActionEvent>() {

         @Override
         public void handle(ActionEvent event) {
             
             
             if(btnExibirLetra.getStyle().equals("-fx-background-color: yellow") && btnExibirLetra.getText().equals("Exibir Letra"))
             {
                 playlistReduzido();
                 btnExibirLetra.setText("Esconder Letra");
             }
             else  if(btnExibirLetra.getText().equals("Esconder Letra"))
             {
                 playlistExpandido();
                  btnExibirLetra.setText("Exibir Letra");
             }
        
             
             
             
         }
     });
    
    
    
    
    }

 }