package com.flo.tennis.core;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.*;

/*modele connection à un serveur de base de données */

public class TestDeConnection {
    public static void main(String... args){
        Connection conn = null;
        try {
            MysqlDataSource dataSource = new MysqlDataSource();

            dataSource.setUrl("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris");
            dataSource.setUser("root");
            dataSource.setPassword("root");

            conn = dataSource.getConnection();

            //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris","root","root");

            conn.setAutoCommit(false);

            PreparedStatement preparedStatement=conn.prepareStatement("insert into joueur (NOM, PRENOM, SEXE) values (?, ?, ?)");//
            String nom = "Capriati";
            String prenom = "Jennifer";
            String sexe = "F";

            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, sexe);

            preparedStatement.executeUpdate();

            nom = "Johanson";
            prenom = "Thomas";
            sexe = "M";

            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, sexe);

            preparedStatement.executeUpdate();

            conn.commit();

            System.out.println("success");

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
}

