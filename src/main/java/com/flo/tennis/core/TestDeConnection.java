package com.flo.tennis.core;

import com.flo.tennis.core.entity.Tournoi;
import com.flo.tennis.core.repository.JoueurRepositoryImpl;
import com.flo.tennis.core.repository.TournoiRepositoryImpl;
import com.mysql.cj.jdbc.MysqlDataSource;
import com.flo.tennis.core.entity.Joueur;

import java.sql.*;
import java.util.List;

/*modele connection à un serveur de base de données */

public class TestDeConnection {
    public static void main(String... args){
        JoueurRepositoryImpl joueurRepository = new JoueurRepositoryImpl();
        TournoiRepositoryImpl tournoiRepository = new TournoiRepositoryImpl();

        //************************ Joueur ************************
        /*Joueur  bartoli = joueurRepository.getById(21L);
        System.out.println(bartoli.getPrenom() +" " + bartoli.getNom());
        */
        /*Joueur sampras = joueurRepository.getById(52L);
        sampras.setPrenom("Pete");
        */

        //joueurRepository.delete(52L);

        /*
        //obtenir la liste des joueurs de la table joueur
        List<Joueur> Liste = joueurRepository.list();

        for(Joueur joueur : Liste){
            System.out.println(joueur.getPrenom() +" "+ joueur.getNom());
        }
         */
        //create joueur
        /*Joueur sampras = new Joueur();
        sampras.setNom("Sampras");
        sampras.setPrenom("Pete");
        sampras.setSexe('H');
        joueurRepository.create(sampras);
         */

        //************************ Tournoi ************************
        //create tournoi
        /*Tournoi iw = new Tournoi();
        iw.setNom("Indian Wells");
        iw.setCode("CA");
        tournoiRepository.create(iw);

        System.out.println(iw.getNom() + " a été créé, son code est : "+iw.getCode());
        System.out.println("L'identifiant de " + iw.getNom() + " est :" + iw.getId());
         */

        //update tournoi
        Tournoi wi = tournoiRepository.getById(3L);
        wi.setCode("WI");
        tournoiRepository.update(wi);



        //tournoiRepository.delete(6L);

        /*List<Tournoi> Liste = tournoiRepository.list();

        for(Tournoi tournoi : Liste){
            System.out.println(tournoi.getCode() +" "+ tournoi.getNom());
        }*/

    }
}

