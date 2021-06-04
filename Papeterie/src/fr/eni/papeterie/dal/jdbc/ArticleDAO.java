package fr.eni.papeterie.dal.jdbc;

import fr.eni.papeterie.bo.Article;

import java.util.List;

public interface ArticleDAO {
        Article selectById(int id);
        void insert(Article article);

        void update(Article article);

        void delete(int id) throws DALException;

        List<Article> selectAll();
}
