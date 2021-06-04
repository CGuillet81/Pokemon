package fr.eni.papeterie.dal.jdbc;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAOJdbcImpl implements ArticleDAO {
    private final String URL = Settings.getPropriete("url");
    private final String SELECT_BY_ID = "SELECT * FROM Articles WHERE idArticle=?";
    private final String UPDATE = "UPDATE Articles SET reference=?, marque=?, designation=?, qteStock=?, prixUnitaire=?, grammage=?, couleur=? WHERE idArticle=?";
    private final String INSERT = "INSERT INTO Articles (reference=?, marque=?, designation=?, qteStock=?, prixUnitaire=?, grammage=?, couleur=?" +
            "VALUES (?,?,?,?,?,?,?,?);";
    private final String SQL_DELETE = "DELETE FROM Articles WHERE idArticle=?";
    private final String SELECT_ALL = "SELECT idArticle, reference, designation, marque, prixUnitaire, qteStock, grammage, couleur, type FROM articles WHERE marque LIKE ? OR designation LIKE?";

    public Article selectById(int idArticle){
        Article article = null;
        try {
            Connection connection = DriverManager.getConnection(this.URL);
            Statement selectId = connection.createStatement();
            String sql = "SELECT idArticle, reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, " +
            "type FROM Articles WHERE idArticle = " + idArticle + ";";
            ResultSet rs = selectId.executeQuery(sql);
            if (rs.next()) {
                if (rs.getString("type").trim().equalsIgnoreCase("RAMETTE")) {
                    article = new Ramette(
                            rs.getInt("idArticle"),
                            rs.getString("reference"),
                            rs.getString("marque"),
                            rs.getString("designation"),
                            rs.getFloat("prixUnitaire"),
                            rs.getInt("qteStock"),
                            rs.getInt("grammage"));

                }
                if (rs.getString("type").trim().equalsIgnoreCase("STYLO")) {
                    article = new Stylo(
                            rs.getInt("idArticle"),
                            rs.getString("reference"),
                            rs.getString("marque"),
                            rs.getString("designation"),
                            rs.getFloat("prixUnitaire"),
                            rs.getInt("qteStock"),
                            rs.getString("couleur"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return article;
    }

    public List<Article> selectAll(){
        List<Article> articleList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(this.URL);
             Statement selectTout = connection.createStatement()) {
            String sql = "SELECT * FROM Articles;";
            ResultSet rs = selectTout.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return articleList;
    }

    public void update(Article article) {
        try (Connection connection = DriverManager.getConnection(this.URL)){
            PreparedStatement etat = connection.prepareStatement(this.UPDATE);
            etat.setString(1, article.getReference());
            etat.setString(2, article.getMarque());
            etat.setString(3, article.getDesignation());
            etat.setInt(4, article.getQteStock());
            etat.setFloat(5, article.getPrixUnitaire());
            etat.setInt(8, article.getIdArticle());
            if (article instanceof Stylo) {
                etat.setString(7, ((Stylo) article).getCouleur());
            }
            if (article instanceof Ramette) {
                etat.setInt(6, ((Ramette) article).getGrammage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) throws DALException {
        try (Connection connection = DriverManager.getConnection(this.URL)){
            PreparedStatement reqPreparee = connection.prepareStatement(this.SQL_DELETE);
            reqPreparee.setInt(1,id);
            reqPreparee.executeUpdate();
        } catch (SQLException e) {
            throw new DALException ("Erreur dans la méthode delete().");
        }
    }

    public void insert(Article article) {
        try (Connection connection = DriverManager.getConnection(this.URL);
             PreparedStatement etat = connection.prepareStatement(this.INSERT)) {
            etat.setString(1, article.getReference());
            etat.setString(2, article.getMarque());
            etat.setString(3, article.getDesignation());
            etat.setInt(4, article.getQteStock());
            etat.setFloat(5, article.getPrixUnitaire());
            if (article instanceof Stylo) {
                etat.setString(7, ((Stylo) article).getCouleur());
                etat.setString(8, "RAMETTE");
            }
            if (article instanceof Ramette) {
                etat.setInt(6, ((Ramette) article).getGrammage());
                etat.setString(8, "STYLO");
            }
            etat.executeUpdate();
            ResultSet rs = etat.getGeneratedKeys(); // Je récupère l'id auto généré par la méthode insert
            if (rs.next()) {
                article.setIdArticle(rs.getInt(1)); // Et je le met dans l'article en utilisant le setter
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
