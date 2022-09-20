package com.flo.tennis.core.service;

import com.flo.tennis.core.entity.Joueur;
import com.flo.tennis.core.repository.JoueurRepositoryImpl;

public class JoueurService {
    private JoueurRepositoryImpl joueurRepository;

    public JoueurService(){
        this.joueurRepository = new JoueurRepositoryImpl();
    }

    public void createJoueur(Joueur joueur){
        joueurRepository.create(joueur);
    }

    public Joueur getJoueurById(Long id){
        return joueurRepository.getById(id);
    }

    public void renomme(Long id, String nouveauNom){
        joueurRepository.renomme(id, nouveauNom);
    }
}
