/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemon;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javafx.application.Application;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author acarrera
 */
public class Principal extends Application {
    
    
    public static ArrayList<MaestroPokemon> entrenadores= new ArrayList<>();
    public static ArrayList<Pokemon> pokemonsLibres;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        llenarDatosEntrenador();
        llenarPokemoneslibres();
        launch();
    }
    public static void llenarDatosEntrenador(){
        entrenadores.add(new MaestroPokemon("Ash"));
        entrenadores.add(new MaestroPokemon("Missi"));
        entrenadores.add(new MaestroPokemon("Brooke"));
        
    }

    private static void llenarPokemoneslibres() {
        try(ObjectInputStream oi = new ObjectInputStream(
                new FileInputStream("src/recursos/archivos/pokemonslibres.dat"))) {
            pokemonsLibres = new ArrayList<>();
            pokemonsLibres.addAll((ArrayList<Pokemon>)oi.readObject());
        
        }catch(IOException|ClassNotFoundException ex){
            ex.printStackTrace();
        }
        
    }
    
    @Override
    public void start(Stage primaryStage) {
           Scene sc = new Scene(new PanelJuego().getRoot(),600,400);
           primaryStage.setScene(sc);
           System.out.println(pokemonsLibres);
           primaryStage.show();
    }
    
    @Override
    public void stop() {
        PanelJuego.run = false;
    }
}
