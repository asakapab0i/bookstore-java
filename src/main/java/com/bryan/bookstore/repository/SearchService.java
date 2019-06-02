package com.bryan.bookstore.repository;

import com.bryan.bookstore.entity.Author;
import com.bryan.bookstore.entity.Book;
import com.bryan.bookstore.entity.Category;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class SearchService {

    @PersistenceContext
    private EntityManager entityManager;

    public List getBooksByString(String term){
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Book.class).get();

//        Query query = queryBuilder.keyword()
//                .onFields("title", "subtitle", "description")
//                .matching(term)
//                .createQuery();

        Query query = queryBuilder
                .keyword()
                .fuzzy()
                .withEditDistanceUpTo(2)
                .withPrefixLength(0)
                .onFields("title", "subtitle", "description")
                .matching(term)
                .createQuery();

        FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(query, Book.class);

        return jpaQuery.getResultList();
    }

    public List getAuthorsByString(String term){
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Author.class).get();

//        Query query = queryBuilder.keyword()
//                .onFields("title", "subtitle", "description")
//                .matching(term)
//                .createQuery();

        Query query = queryBuilder
                .keyword()
                .fuzzy()
                .withEditDistanceUpTo(2)
                .withPrefixLength(0)
                .onFields("first_name", "last_name")
                .matching(term)
                .createQuery();

        FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(query, Author.class);

        return jpaQuery.getResultList();
    }

    public List getCategoriesByString(String term){
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Category.class).get();

//        Query query = queryBuilder.keyword()
//                .onFields("title", "subtitle", "description")
//                .matching(term)
//                .createQuery();

        Query query = queryBuilder
                .keyword()
                .fuzzy()
                .withEditDistanceUpTo(2)
                .withPrefixLength(0)
                .onFields("category")
                .matching(term)
                .createQuery();

        FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(query, Category.class);

        return jpaQuery.getResultList();
    }
}
