/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemon;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author acarrera
 */
public class MaestroPokemon {
    private String nombre;
    private Map<String, Set<Pokemon>> pokemonsTipo;
    public MaestroPokemon(String nombre) {
        this.nombre = nombre;
        this.pokemonsTipo= new TreeMap<>();
    }
    public String getNombre(){
        return nombre;
    }
    public Map<String, Set<Pokemon>> getPokemonsTipo(){
        return pokemonsTipo;
    }
    
    @Override
    public String toString() {
        return nombre;
    }
}
