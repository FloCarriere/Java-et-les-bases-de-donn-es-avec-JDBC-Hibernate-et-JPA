package com.flo.tennis.core.repository;

import com.flo.tennis.core.DataSourceProvider;
import com.flo.tennis.core.entity.Joueur;
import com.flo.tennis.core.entity.Match;

import javax.sql.DataSource;
import java.sql.*;

public class MatchRepositoryImpl {

    public void create(Match match){
        Connection conn = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris","root","root");


            PreparedStatement preparedStatement=conn.prepareStatement("insert into match_tennis (ID_EPREUVE, ID_VAINQUEUR, ID_FINALISTE) values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);//

            preparedStatement.setLong(1, match.getEpreuve().getId());
            preparedStatement.setLong(2, match.getVainqueur().getId());
            preparedStatement.setLong(3, match.getFinaliste().getId());

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();

            if (rs.next()){
                match.setId(rs.getLong(1));
            }

            System.out.println("Match créé");

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
