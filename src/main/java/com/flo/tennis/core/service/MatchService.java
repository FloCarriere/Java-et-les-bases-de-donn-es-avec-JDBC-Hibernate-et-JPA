package com.flo.tennis.core.service;

import com.flo.tennis.core.HibernateUtil;
import com.flo.tennis.core.dto.*;
import com.flo.tennis.core.entity.Epreuve;
import com.flo.tennis.core.entity.Joueur;
import com.flo.tennis.core.entity.Match;
import com.flo.tennis.core.entity.Score;
import com.flo.tennis.core.repository.EpreuveRepositoryImpl;
import com.flo.tennis.core.repository.JoueurRepositoryImpl;
import com.flo.tennis.core.repository.MatchRepositoryImpl;
import com.flo.tennis.core.repository.ScoreRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MatchService {
    private ScoreRepositoryImpl scoreRepository;
    private MatchRepositoryImpl matchRepository;
    private EpreuveRepositoryImpl epreuveRepository;
    private JoueurRepositoryImpl joueurRepository;

    public MatchService(){
        this.scoreRepository=new ScoreRepositoryImpl();
        this.matchRepository=new MatchRepositoryImpl();
        this.epreuveRepository=new EpreuveRepositoryImpl();
        this.joueurRepository=new JoueurRepositoryImpl();
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

            EpreuveFullDto epreuveDto = new EpreuveFullDto();
            epreuveDto.setId(match.getEpreuve().getId());
            epreuveDto.setAnnee(match.getEpreuve().getAnnee());
            epreuveDto.setTypeEpreuve(match.getEpreuve().getTypeEpreuve());
            TournoiDto tournoiDto=new TournoiDto();
            tournoiDto.setId(match.getEpreuve().getTournoi().getId());
            tournoiDto.setNom(match.getEpreuve().getTournoi().getNom());
            tournoiDto.setCode(match.getEpreuve().getTournoi().getCode());
            epreuveDto.setTournoi(tournoiDto);

            dto.setEpreuve(epreuveDto);

            ScoreFullDto scoreDto = new ScoreFullDto();
            scoreDto.setId(match.getScore().getId());

            scoreDto.setSet1(match.getScore().getSet1());
            scoreDto.setSet2(match.getScore().getSet2());
            scoreDto.setSet3(match.getScore().getSet3());
            scoreDto.setSet4(match.getScore().getSet4());
            scoreDto.setSet5(match.getScore().getSet5());

            dto.setScore(scoreDto);
            scoreDto.setMatch(dto);

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

    public void tapisVert(Long id) {
        Session session = null;
        Transaction tx = null;
        Match match = null;

        try {

            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            match = matchRepository.getById(id);

            Joueur ancienVainqueur = match.getVainqueur();
            match.setVainqueur(match.getFinaliste());
            match.setFinaliste(ancienVainqueur);

            match.getScore().setSet1((byte)0);
            match.getScore().setSet2((byte)0);
            match.getScore().setSet3((byte)0);
            match.getScore().setSet4((byte)0);
            match.getScore().setSet5((byte)0);



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

    public void createMatch(MatchDto dto) {
        Session session = null;
        Transaction tx = null;
        Match match = null;

        try {

            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            match = new Match();
            match.setEpreuve(epreuveRepository.getById(dto.getEpreuve().getId()));
            match.setVainqueur(joueurRepository.getById(dto.getVainqueur().getId()));
            match.setFinaliste(joueurRepository.getById(dto.getFinaliste().getId()));

            Score score = new Score();
            score.setMatch(match);
            match.setScore(score);
            score.setSet1(dto.getScore().getSet1());
            score.setSet2(dto.getScore().getSet2());
            score.setSet3(dto.getScore().getSet3());
            score.setSet4(dto.getScore().getSet4());
            score.setSet5(dto.getScore().getSet5());

            matchRepository.create(match);

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

    public void deleteMatch(Long id){

        Session session = null;
        Transaction tx = null;
        Match match = null;

        try {

            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            matchRepository.delete(id);

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
