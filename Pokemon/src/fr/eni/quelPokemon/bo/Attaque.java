package fr.eni.quelPokemon.bo;

public class Attaque extends Main{
    private String nom;
    private int degats;

    public Attaque(String nom, int degats) {
        this.nom = nom;
        this.degats = degats;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getDegats() {
        return degats;
    }

    public void setDegats(int degats) {
        this.degats = degats;
    }


    public void afficher() {
        System.out.printf("L'attaque %s inflige %d points de dégâts",
                this.nom, this.degats);
    }

    @Override
    public String toString() {
        return "Attaque{" +
                "nom='" + nom + '\'' +
                ", degats=" + degats +
                '}';
    }
}
