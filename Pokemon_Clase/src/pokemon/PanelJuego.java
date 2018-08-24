/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemon;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import java.security.SecureRandom;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author acarrera
 */
public class PanelJuego {
    private final BorderPane root = new BorderPane();
    private final Pane paneJuego = new Pane();
    private final ComboBox<MaestroPokemon> entrenadores = new ComboBox();
    private final HBox panelTop= new HBox(new Label("Entrenador: "),entrenadores);
    private final HBox panelInferior= new HBox(new Label("EN ESTA SECCION IRAN LOS DATOS DEL POKEMON QUE SE ATRAPARA"));
    private final FlowPane panelDerecho = new FlowPane(Orientation.VERTICAL);
    private int tiempo;
    static boolean run = true;
    
    public PanelJuego() {
        entrenadores.getItems().addAll(Principal.entrenadores);
        root.setTop(panelTop);
        
        paneJuego.setStyle("-fx-background-image: url('"+getClass().getResource("fondo.jpg").toExternalForm()+"');"
                                        + "-fx-background-repeat: stretch;"             
                                        + "-fx-background-position: top center;");
        root.setCenter(paneJuego);
         
        root.setRight(panelDerecho);
        
        root.setBottom(panelInferior);
        aparecer();
        Thread t = new Thread(new Timer());
        t.start();
        panelDerecho();
    }
    
    public void aparecer() {
       SecureRandom rand = new SecureRandom();
       int var = 0;
       if(!Principal.pokemonsLibres.isEmpty()){
        var = rand.nextInt(Principal.pokemonsLibres.size());
        Pokemon p = Principal.pokemonsLibres.get(var);
         System.out.println(CONSTANTES.RUTA_IMAGENES + p.getRuta());
        ImageView  iv = new ImageView(new Image(CONSTANTES.RUTA_IMAGENES + p.getRuta()));
        iv.setTranslateX(200);
        iv.setTranslateY(200);
        paneJuego.getChildren().add(iv);
        iv.setOnMouseClicked(e -> {
            MaestroPokemon mp = entrenadores.getValue();
            if(mp != null){
                paneJuego.getChildren().clear();
                Map<String, Set<Pokemon>> pokemons = mp.getPokemonsTipo();
                if(pokemons.containsKey(p.getTipo()))
                    pokemons.get(p.getTipo()).add(p);
                else{
                    Set<Pokemon> set = new TreeSet<>();
                    set.add(p);
                    pokemons.put(p.getTipo(), set);
                }
                Principal.pokemonsLibres.remove(p);
                System.out.println("lista " +Principal.pokemonsLibres);
                mostrarPanel();
                aparecer();
                tiempo = 0;
            }
           }); 
        }
    }
    
    public BorderPane getRoot() {
        return root;
    }
    
    private void panelDerecho() {
        entrenadores.setOnAction(e-> {
            mostrarPanel();
        }); 
        
    }
 
    void mostrarPanel(){
        panelDerecho.getChildren().clear();
            MaestroPokemon mp = entrenadores.getValue();
            if(mp != null){
                Map<String, Set<Pokemon>> mapa = mp.getPokemonsTipo();
                for(String key: mapa.keySet()){
                    panelDerecho.getChildren().add(new Label(key.toUpperCase()));
                    for(Pokemon p: mapa.get(key)){
                        ImageView  iv = new ImageView(new Image(CONSTANTES.RUTA_IMAGENES + p.getRuta()));
                        panelDerecho.getChildren().add(iv);
                        panelDerecho.getChildren().addAll(new Label("Nombre: " + p.getNombre()),
                                new Label("Poder: " + p.getPoder()));
                    }
                }
            }
    }    
    private class Timer implements Runnable{
        
        @Override
        public void run() {
            while(run){
                esperar();
                tiempo++;
                if(tiempo >= 15) {
                    //Ejecuta el hilo principal de la aplicación
                    Platform.runLater(() ->{
                        paneJuego.getChildren().clear();
                        aparecer();
                    }); 
//                    Platform.runLater(new Runnable() {
//                        @Override
//                        public void run() {
//                            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                        }
//                    });
                    tiempo = 0;
                }
            }
        }
        
        private void esperar() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(PanelJuego.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    }
    
}
