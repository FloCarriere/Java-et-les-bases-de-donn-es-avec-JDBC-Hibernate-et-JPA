package com.flo.tennis.core.service;

import com.flo.tennis.core.entity.Joueur;
import com.flo.tennis.core.entity.Tournoi;
import com.flo.tennis.core.repository.JoueurRepositoryImpl;
import com.flo.tennis.core.repository.TournoiRepositoryImpl;

public class TournoiService {
    private TournoiRepositoryImpl tournoiRepository;

    public TournoiService(){
        this.tournoiRepository = new TournoiRepositoryImpl();
    }

    public void createTournoi(Tournoi tournoi){
        tournoiRepository.create(tournoi);
    }

    public Tournoi getTournoiById(Long id){
        return tournoiRepository.getById(id);
    }
}
