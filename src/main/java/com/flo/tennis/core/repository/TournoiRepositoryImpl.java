package com.flo.tennis.core.repository;

import com.flo.tennis.core.DataSourceProvider;
import com.flo.tennis.core.HibernateUtil;
import com.flo.tennis.core.entity.Joueur;
import com.flo.tennis.core.entity.Tournoi;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TournoiRepositoryImpl {

    public void create(Tournoi tournoi){
        Session session =null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.persist(tournoi);
            tx.commit();
            System.out.println("Tournoi créé");
        } catch (Exception e){
            if(tx !=null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        finally {
            if(session != null){
                session.close();
            }
        }

    }

    public void update(Tournoi tournoi){
        Connection conn = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            PreparedStatement preparedStatement=conn.prepareStatement("update tournoi set NOM = ?, CODE = ? where id= ?");//

            preparedStatement.setString(1, tournoi.getNom());
            preparedStatement.setString(2, tournoi.getCode());
            preparedStatement.setLong(3, tournoi.getId());

            preparedStatement.executeUpdate();

            System.out.println("Tournoi modifié");

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
        Tournoi tournoi= getById(id);
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.delete(tournoi);
        System.out.println("Tounoi supprimé");

    }

    public Tournoi getById(Long id){
        Tournoi tournoi = null;
        Session session =null;

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            tournoi=session.get(Tournoi.class, id);
            System.out.println("Tournoi lu");

        }catch (Throwable t){
            t.printStackTrace();
        }

        finally {
            if(session != null){
                session.close();
            }

        }
        return tournoi;

    }

    public List<Tournoi> list(){
        Connection conn = null;
        List<Tournoi> tournois = new ArrayList<>();

        try {

           DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            PreparedStatement preparedStatement=conn.prepareStatement("select id, nom, code from tournoi ");//

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                Tournoi tournoi = new Tournoi();
                tournoi.setId(rs.getLong("ID"));
                tournoi.setNom(rs.getString("NOM"));
                tournoi.setCode(rs.getString("CODE"));
                tournois.add(tournoi);
            }

            System.out.println("Tournoi lus");

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
        return tournois;

    }
}
