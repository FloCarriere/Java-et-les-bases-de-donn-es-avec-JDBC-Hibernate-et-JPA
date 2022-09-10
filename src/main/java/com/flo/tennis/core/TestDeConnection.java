package com.flo.tennis.core;

import java.sql.*;

public class TestDeConnection {
    public static void main(String... args){
        Connection conn = null;
        try {

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris","root","root");

            PreparedStatement preparedStatement=conn.prepareStatement("update joueur set NOM = ?, PRENOM = ? WHERE ID = ?");
            long identifiant=24L;
            String nom = "Errani";
            String prenom = "Sara";

            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setLong(3, identifiant);

            int nbEnregistrementsModifies = preparedStatement.executeUpdate();

            System.out.println("nbEnregistrementsModifies = " + nbEnregistrementsModifies);
            System.out.println("success");
        } catch (SQLException e) {
            e.printStackTrace();
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

