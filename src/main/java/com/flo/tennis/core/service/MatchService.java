package com.flo.tennis.core.service;

import com.flo.tennis.core.entity.Match;
import com.flo.tennis.core.repository.MatchRepositoryImpl;
import com.flo.tennis.core.repository.ScoreRepositoryImpl;

public class MatchService {
    private ScoreRepositoryImpl scoreRepository;
    private MatchRepositoryImpl matchRepository;

    public MatchService(){
        this.scoreRepository=new ScoreRepositoryImpl();
        this.matchRepository=new MatchRepositoryImpl();
    }

    public void EnregistrerNouveauMatch(Match match){
        matchRepository.create(match);
        scoreRepository.create(match.getScore());

    }
}
