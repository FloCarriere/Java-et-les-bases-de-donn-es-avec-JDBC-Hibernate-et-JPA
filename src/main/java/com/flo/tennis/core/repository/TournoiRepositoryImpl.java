package com.flo.tennis.core.repository;

import com.flo.tennis.core.DataSourceProvider;
import com.flo.tennis.core.entity.Joueur;
import com.flo.tennis.core.entity.Tournoi;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TournoiRepositoryImpl {

    public void create(Tournoi tournoi){
        Connection conn = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            PreparedStatement preparedStatement=conn.prepareStatement("insert into tournoi (NOM, CODE) values (?, ?)", Statement.RETURN_GENERATED_KEYS);//

            preparedStatement.setString(1, tournoi.getNom());
            preparedStatement.setString(2, tournoi.getCode());

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();

            if (rs.next()){
                tournoi.setId(rs.getLong(1));
            }

            System.out.println("Tournoi créé");

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
        Connection conn = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            PreparedStatement preparedStatement=conn.prepareStatement("delete from tournoi where id= ?");//

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

            System.out.println("Tournoi supprimé");

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

    public Tournoi getById(Long id){
        Connection conn = null;
        Tournoi tournoi = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            PreparedStatement preparedStatement=conn.prepareStatement("select nom, code from tournoi where id= ?");//

            preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()){
                tournoi = new Tournoi();
                tournoi.setId(id);
                tournoi.setNom(rs.getString("NOM"));
                tournoi.setCode(rs.getString("CODE"));
            }

            System.out.println("Tournoi lu");

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
