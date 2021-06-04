package fr.eni.quelPokemon.bo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dresseur {
    private String prenom;
    private Pokemon[] pokemons;

    public Dresseur(String prenom, Pokemon[] pokemons) {
        this.prenom = prenom;
        this.pokemons = pokemons;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Pokemon[] getPokemons() {
        return pokemons;
    }

    public void setPokemons(Pokemon[] pokemons) {
        this.pokemons = pokemons;
    }

    public void afficher() {
        System.out.println("Dresseur : " + "prenom= "+ prenom  +", pokemons =" + Arrays.toString(pokemons));
    }
    public void capture(Pokemon pokemon){
        List<Pokemon> pokemons = new ArrayList<Pokemon>();
        if (pokemon != null) {
            pokemons.add(pokemon);
        }
        if(pokemons != null) {
            this.setPokemons(pokemons.toArray(new Pokemon[pokemons.size()]));
            pokemon.setDresseur(this);
        }
    }

    @Override
    public String toString() {
        return "Dresseur{" +
                "prenom='" + prenom  +
                ", pokemons=" + Arrays.toString(pokemons) +
                '}';
    }
}
