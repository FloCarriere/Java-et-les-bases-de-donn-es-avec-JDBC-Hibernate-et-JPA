package com.flo.tennis.core.repository;

import com.flo.tennis.core.DataSourceProvider;
import com.flo.tennis.core.HibernateUtil;
import com.flo.tennis.core.entity.Joueur;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JoueurRepositoryImpl {



    public void create(Joueur joueur){

            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.persist(joueur);
            System.out.println("Joueur créé");

    }

    public void update(Joueur joueur){
        Connection conn = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris","root","root");


            PreparedStatement preparedStatement=conn.prepareStatement("update joueur set NOM = ?, PRENOM = ?, SEXE = ? where id= ?");//

            preparedStatement.setString(1, joueur.getNom());
            preparedStatement.setString(2, joueur.getPrenom());
            preparedStatement.setString(3, joueur.getSexe().toString());
            preparedStatement.setLong(4, joueur.getId());

            preparedStatement.executeUpdate();

            System.out.println("Joueur modifié");

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if(conn!=null) conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

        finally {
            try {
                if (conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void delete(Long id){
        Joueur joueur= getById(id);
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.delete(joueur);
        System.out.println("Joueur supprimé");
    }

    public Joueur getById(Long id){
        Joueur joueur = null;
        Session session =null;

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        joueur=session.get(Joueur.class, id);
        System.out.println("Joueur lu");

        return joueur;

    }

    public List<Joueur> list(char sexe){
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        Query<Joueur> query = session.createQuery("select j from Joueur j where j.sexe=?0", Joueur.class);
        query.setParameter(0, sexe);
        List<Joueur>joueurs = query.getResultList();
        return joueurs;
    }

}
