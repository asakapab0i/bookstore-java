package com.bryan.bookstore.controller;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static org.hibernate.search.jpa.Search.getFullTextEntityManager;

@Transactional
@RestController
@RequestMapping(path="admin")
public class AdminController {
    @PersistenceContext
    private EntityManager entityManager;


    @GetMapping("/reindex")
    public String reindexSearch(){
        try {
            FullTextEntityManager fullTextEntityManager =
                    getFullTextEntityManager(entityManager);
            fullTextEntityManager.createIndexer().startAndWait();
        }
        catch (InterruptedException e) {
            System.out.println(
                    "An error occurred trying to build the search index: " +
                            e.toString());
        }
        return "Reindexed.";
    }

}
