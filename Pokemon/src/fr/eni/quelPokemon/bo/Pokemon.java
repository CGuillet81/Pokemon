package fr.eni.quelPokemon.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Pokemon {
    private String nomP;
    private double taille;
    private double poids;
    private int pointsvie;
    private Attaque attaque1;
    private Attaque attaque2;
    private Dresseur dresseur;

    Random rd = new Random();

    public Pokemon(String nomP, double taille, double poids, int pointsvie, Attaque attaque1, Attaque attaque2, Dresseur dresseur) {
        this.nomP = nomP;
        this.taille = taille;
        this.poids = poids;
        this.pointsvie = pointsvie;
        this.attaque1 = attaque1;
        this.attaque2 = attaque2;
        this.dresseur = dresseur;
    }

    public String getNomP() {
        return nomP;
    }

    public void setNomP(String nomP) {
        this.nomP = nomP;
    }

    public double getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public int getPointsvie() {
        return pointsvie;
    }

    public void setPointsvie(int pointsvie) {
        this.pointsvie = pointsvie;
    }

    public Attaque getAttaque1() {
        return attaque1;
    }

    public void setAttaque1(Attaque attaque1) {
        this.attaque1 = attaque1;
    }

    public Attaque getAttaque2() {
        return attaque2;
    }

    public void setAttaque2(Attaque attaque2) {
        this.attaque2 = attaque2;
    }

    public Dresseur getDresseur() {
        return dresseur;
    }

    public void setDresseur(Dresseur dresseur) {
        this.dresseur = dresseur;
    }

    public void afficher() {
        System.out.println("Ce Pokemon s'appelle " + nomP );
        System.out.println("Sa 1ère attaque est" + this.attaque1);
        System.out.println("Sa 2ème attaque est"+ this.attaque2);
    }

    public void attaque(Pokemon p) {
        if(p!= null) {
            Attaque attaque1 = p.getAttaque1();
            Attaque attaque2 = p.getAttaque2();
            int pvRestant = 0;
            int nb = 1 + rd.nextInt(2-1);

            if(nb ==1) {
                pvRestant = this.getPointsvie()-attaque1.getDegats();
                this.setPointsvie(pvRestant);
            }

            if(nb ==2) {
                pvRestant = this.getPointsvie() - attaque1.getDegats();
                this.setPointsvie(pvRestant);
            }
        }
    }

    public void combat(Pokemon pokemon) {

        int pvPokemon1 = this.getPointsvie();
        int pvPokemon2 = pokemon.getPointsvie();

        int index = 0 + rd.nextInt(1 - 0);
        List<Pokemon> poks = new ArrayList<Pokemon>();
        poks.add(pokemon);
        poks.add(this);

        Pokemon premierAttaquant = poks.get(index);

        do {
            if (premierAttaquant.equals(pokemon)) {
                pokemon.attaque(this);
                pvPokemon1 = this.getPointsvie();
                pvPokemon2 = pokemon.getPointsvie();
            } else {
                this.attaque(pokemon);
                pvPokemon1 = this.getPointsvie();
                pvPokemon2 = pokemon.getPointsvie();
            }
            if(pvPokemon1<=0) {

                System.out.println(this.getNomP() + " est KO " + pokemon.getNomP() + " est vainqueur" );
            } else {
                System.out.println(pokemon.getNomP() + " est KO " + this.getNomP() + " est vainqueur" );
            }
        } while (pvPokemon1 <= 0 || pvPokemon2 <= 0);
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "nomP='" + nomP + '\'' +
                ", taille=" + taille +
                ", poids=" + poids +
                ", pointsvie=" + pointsvie +
                '}';
    }
}
