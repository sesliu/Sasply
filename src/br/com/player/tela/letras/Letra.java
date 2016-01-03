/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.player.tela.letras;
import java.io.IOException;
 
import java.util.ArrayList;
import java.util.List;
 
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
 
/**
 *
 * @author Veronica
 */
public class Letra {
    
 private final static String songLyricsURL = "http://www.songlyrics.com";
 
 
   public static List<String> getLetra( String band, String songTitle) throws IOException {
     List<String> lyrics= new ArrayList<String>();
 
     Document doc = Jsoup.connect(songLyricsURL+ "/"+band.replace(" ", "-").toLowerCase()+"/"+songTitle.replace(" ", "-").toLowerCase()+"-lyrics/").get();
     String title = doc.title();
     System.out.println(title);
     Element p = doc.select("p.songLyricsV14").get(0);
      for (Node e: p.childNodes()) {
          if (e instanceof TextNode) {
            lyrics.add(((TextNode)e).getWholeText());
          }
      }
     return lyrics;
   }
}