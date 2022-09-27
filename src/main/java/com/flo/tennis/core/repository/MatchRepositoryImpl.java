package com.flo.tennis.core.repository;

import com.flo.tennis.core.DataSourceProvider;
import com.flo.tennis.core.HibernateUtil;
import com.flo.tennis.core.entity.Epreuve;
import com.flo.tennis.core.entity.Joueur;
import com.flo.tennis.core.entity.Match;
import org.hibernate.Session;

import javax.sql.DataSource;
import java.sql.*;

public class MatchRepositoryImpl {

    public void create(Match match){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(match);
        System.out.println("Match ajouté");
    }

    public Match getById(Long id) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Match match = session.get(Match.class, id);
        System.out.println("Match lu");

        return match;
    }

}
