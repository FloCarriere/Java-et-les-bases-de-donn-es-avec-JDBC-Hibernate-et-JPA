package com.flo.tennis.core.repository;

import com.flo.tennis.core.DataSourceProvider;
import com.flo.tennis.core.HibernateUtil;
import com.flo.tennis.core.entity.Epreuve;
import com.flo.tennis.core.entity.Joueur;
import com.flo.tennis.core.entity.Tournoi;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EpreuveRepositoryImpl {


    public Epreuve getById(Long id) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Epreuve epreuve = session.get(Epreuve.class, id);
        System.out.println("Epreuve lue");

        return epreuve;
    }

    public List<Epreuve> list(String codeTournoi){
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        Query<Epreuve> query = session.createQuery("select e from Epreuve e join fetch e.tournoi where e.tournoi.code=?0", Epreuve.class);
        query.setParameter(0, codeTournoi);
        List<Epreuve>epreuves = query.getResultList();
        return epreuves;
    }

}
