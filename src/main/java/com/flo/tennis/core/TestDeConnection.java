package com.flo.tennis.core;

import com.flo.tennis.core.repository.JoueurRepositoryImpl;
import com.mysql.cj.jdbc.MysqlDataSource;
import com.flo.tennis.core.entity.Joueur;

import java.sql.*;
import java.util.List;

/*modele connection à un serveur de base de données */

public class TestDeConnection {
    public static void main(String... args){
        JoueurRepositoryImpl joueurRepository = new JoueurRepositoryImpl();
        /*Joueur  bartoli = joueurRepository.getById(21L);
        System.out.println(bartoli.getPrenom() +" " + bartoli.getNom());
        */
        /*Joueur sampras = joueurRepository.getById(52L);
        sampras.setPrenom("Pete");
        */

        //joueurRepository.delete(52L);

        List<Joueur> Liste = joueurRepository.list();

        for(Joueur joueur : Liste){
            System.out.println(joueur.getPrenom() +" "+ joueur.getNom());
        }

    }
}

