package fr.eni.quelPokemon.bo;

public class Main {
    public static void main(String[] args) {
    Dresseur sacha = new Dresseur("Sacha", null);
        Dresseur ondine = new Dresseur("Ondine", null);
    Attaque statik = new Attaque("Statik",20);
        Attaque paratonnerre = new Attaque("Paratonnerre",75);
    Pokemon pikachu = new Pokemon("Pikachu", 6, (int) 0.4, 120,statik, paratonnerre, null);
        Attaque cran = new Attaque("Cran",20);
        Attaque agitation = new Attaque("Agitation",75);
    Pokemon rattata = new Pokemon("Rattata", 3.5, 0.3, 120, cran, agitation, null);

    sacha.capture(pikachu);
    sacha.afficher();
    pikachu.afficher();

    ondine.capture(rattata);
    ondine.afficher();
    rattata.afficher();

    pikachu.combat(rattata);
    }






}
