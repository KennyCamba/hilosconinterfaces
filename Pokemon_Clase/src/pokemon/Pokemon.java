/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemon;

import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author acarrera
 */
public class Pokemon implements Serializable, Comparable<Pokemon>{
    private String nombre;
    private int poder;
    private int velocidad;
    private String ruta;
    private String tipo;
    final static long serialVersionUID = 42L;
    public Pokemon(String nombre, int poder,int velocidad, String ruta, String tipo) {
        this.nombre = nombre;
        this.poder = poder;
        this.ruta= ruta;
        this.velocidad= velocidad;
        this.tipo= tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPoder() {
        return poder;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public String getRuta() {
        return ruta;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public int compareTo(Pokemon o) {
        return this.nombre.compareTo(o.nombre);
    }
    

         
}
