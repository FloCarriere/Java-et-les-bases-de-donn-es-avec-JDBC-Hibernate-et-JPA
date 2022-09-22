package com.flo.tennis.core.service;

import com.flo.tennis.core.HibernateUtil;
import com.flo.tennis.core.dto.EpreuveLightDto;
import com.flo.tennis.core.dto.JoueurDto;
import com.flo.tennis.core.dto.MatchDto;
import com.flo.tennis.core.dto.TournoiDto;
import com.flo.tennis.core.entity.Epreuve;
import com.flo.tennis.core.entity.Match;
import com.flo.tennis.core.repository.MatchRepositoryImpl;
import com.flo.tennis.core.repository.ScoreRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

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

    public MatchDto getMatch(Long id){
        Session session=null;
        Transaction tx =null;
        Match match=null;
        MatchDto dto =null;

        try {

            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            match=matchRepository.getById(id);

            dto = new MatchDto();
            dto.setId(match.getId());

            JoueurDto joueurFinalisteDto=new JoueurDto();
            joueurFinalisteDto.setId(match.getFinaliste().getId());
            joueurFinalisteDto.setNom(match.getFinaliste().getNom());
            joueurFinalisteDto.setPrenom(match.getFinaliste().getPrenom());
            joueurFinalisteDto.setSexe(match.getFinaliste().getSexe());
            dto.setFinaliste(joueurFinalisteDto);

            JoueurDto joueurVainqueurDto=new JoueurDto();
            joueurVainqueurDto.setId(match.getVainqueur().getId());
            joueurVainqueurDto.setNom(match.getVainqueur().getNom());
            joueurVainqueurDto.setPrenom(match.getVainqueur().getPrenom());
            joueurVainqueurDto.setSexe(match.getVainqueur().getSexe());
            dto.setVainqueur(joueurVainqueurDto);
            tx.commit();

        }catch (Exception e){
            if(tx!=null){
                tx.rollback();
            }
            e.printStackTrace();
        }

        finally {
            if(session != null){
                session.close();
            }

        }

        return dto;
    }
}
