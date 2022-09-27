package com.flo.tennis.core.service;

import com.flo.tennis.core.HibernateUtil;
import com.flo.tennis.core.dto.*;
import com.flo.tennis.core.entity.Joueur;
import com.flo.tennis.core.entity.Match;
import com.flo.tennis.core.entity.Score;
import com.flo.tennis.core.repository.JoueurRepositoryImpl;
import com.flo.tennis.core.repository.ScoreRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ScoreService {
    private ScoreRepositoryImpl scoreRepository;

    public ScoreService() {
        this.scoreRepository = new ScoreRepositoryImpl();
    }


    public ScoreFullDto getScoreById(Long id) {

        Session session = null;
        Transaction tx = null;
        Score score = null;

        ScoreFullDto dto =null;

        try {

            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            score = scoreRepository.getById(id);

            dto = new ScoreFullDto();
            dto.setId(score.getId());

            dto.setSet1(score.getSet1());
            dto.setSet2(score.getSet2());
            dto.setSet3(score.getSet3());
            dto.setSet4(score.getSet4());
            dto.setSet5(score.getSet5());




            MatchDto matchDto = new MatchDto();
            matchDto.setId(score.getMatch().getId());

            JoueurDto joueurFinalisteDto=new JoueurDto();
            joueurFinalisteDto.setId(score.getMatch().getFinaliste().getId());
            joueurFinalisteDto.setNom(score.getMatch().getFinaliste().getNom());
            joueurFinalisteDto.setPrenom(score.getMatch().getFinaliste().getPrenom());
            joueurFinalisteDto.setSexe(score.getMatch().getFinaliste().getSexe());
            matchDto.setFinaliste(joueurFinalisteDto);

            JoueurDto joueurVainqueurDto=new JoueurDto();
            joueurVainqueurDto.setId(score.getMatch().getVainqueur().getId());
            joueurVainqueurDto.setNom(score.getMatch().getVainqueur().getNom());
            joueurVainqueurDto.setPrenom(score.getMatch().getVainqueur().getPrenom());
            joueurVainqueurDto.setSexe(score.getMatch().getVainqueur().getSexe());
            matchDto.setVainqueur(joueurVainqueurDto);


            EpreuveFullDto epreuveDto = new EpreuveFullDto();
            epreuveDto.setId(score.getMatch().getEpreuve().getId());
            epreuveDto.setAnnee(score.getMatch().getEpreuve().getAnnee());
            epreuveDto.setTypeEpreuve(score.getMatch().getEpreuve().getTypeEpreuve());
            TournoiDto tournoiDto=new TournoiDto();
            tournoiDto.setId(score.getMatch().getEpreuve().getTournoi().getId());
            tournoiDto.setNom(score.getMatch().getEpreuve().getTournoi().getNom());
            tournoiDto.setCode(score.getMatch().getEpreuve().getTournoi().getCode());
            epreuveDto.setTournoi(tournoiDto);
            matchDto.setEpreuve(epreuveDto);

            dto.setMatch(matchDto);

            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }

        }
        return dto;
    }

    public void deleteScore(Long id){

        Session session = null;
        Transaction tx = null;

        try {

            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            scoreRepository.delete(id);

            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }
}


