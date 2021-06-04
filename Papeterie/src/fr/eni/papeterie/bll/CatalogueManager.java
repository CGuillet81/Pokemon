package fr.eni.papeterie.bll;

import fr.eni.papeterie.bo.Article;

import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;
import fr.eni.papeterie.dal.jdbc.ArticleDAO;
import fr.eni.papeterie.dal.jdbc.DALException;
import fr.eni.papeterie.dal.jdbc.DAOFactory;

import java.util.List;
import java.util.regex.Pattern;

public class CatalogueManager {

    private ArticleDAO articleDAO = DAOFactory.getArticleDAO();

    // Création d'une instance de type manager privé
    private static CatalogueManager instance;

    // Création d'un constructeur privé vide
    private CatalogueManager() {
    }
    //Si l'instance est null alors on en créé une nouvelle
    //Sinon on retourne l'instance existante
    public static CatalogueManager getInstance() {
        if (instance == null) {
            instance = new CatalogueManager();
        }
        return instance;
    }

    public List<Article> getCatalogue() throws BLLException {
        return this.articleDAO.selectAll();
    }
    public void addArticle(Article a) throws BLLException {
        this.validerArticle(a);
        this.articleDAO.insert(a);
    }

    public void updateArticle(Article a) throws BLLException{
        validerArticle(a);
        this.articleDAO.update(a);
    }

    public void removeArticle(int index) throws BLLException{
        try {
            this.articleDAO.delete(index);
        } catch (DALException e) {
            throw new BLLException(e.getMessage());
        }
    }

    public Article getArticle(int index) throws BLLException {
        return this.articleDAO.selectById(index);
    }

    private void validerArticle (Article a) throws BLLException{
        if (a == null) {
            throw new BLLException("L'article est null");
        }
        if (a instanceof Ramette && ((Ramette) a).getGrammage() <= 0) {
            throw new BLLException("Son grammage n'est pas valide");
        }
        var regex = "[0-9]*";
        if (Pattern.matches(regex, ((Stylo) a).getCouleur())) {
            throw new BLLException("La couleur ne peut contenir de chiffres.");
        }
        if (a instanceof Stylo && ((Stylo) a).getCouleur() == null || ((Stylo) a).getCouleur().trim().length() == 0) {
            throw new BLLException("La couleur n'est pas valide");
        }
        if (a.getReference() == null || a.getReference().trim().length() == 0) {
            throw new BLLException("La référence est obligatoire.");
        }
    }

}
