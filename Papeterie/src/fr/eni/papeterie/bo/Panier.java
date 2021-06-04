package fr.eni.papeterie.bo;

import java.util.List;

public class Panier {
    private float montant;
    private Ligne ligne;

    private List<Ligne> lignesPanier;

    public Panier() {
        this.montant = montant;
    }

    private float getMontant() {
        return montant;
    }

    

    public Ligne getLigne(int index) {
        return lignesPanier.get(index);
    }

    private List<Ligne> getLignesPanier() {
        return lignesPanier;
    }

    public void addLigne(Article article, int qte){
        Ligne newLigne = new Ligne(qte, article);
        lignesPanier.add(newLigne);
    }

    public void updateLigne(int index, int newQte){
        this.getLigne(index).setQte(newQte);
    }

    public void removeLigne(int index){
        lignesPanier.remove(index);
    }

    @Override
    public String toString() {
        return "Panier{" +
                "montant=" + montant +
                ", ligne=" + ligne +
                ", lignesPanier=" + lignesPanier +
                '}';
    }
}
