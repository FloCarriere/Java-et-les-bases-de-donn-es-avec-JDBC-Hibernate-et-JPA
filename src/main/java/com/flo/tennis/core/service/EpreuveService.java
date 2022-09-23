package com.flo.tennis.core.service;

import com.flo.tennis.core.HibernateUtil;
import com.flo.tennis.core.dto.EpreuveFullDto;
import com.flo.tennis.core.dto.EpreuveLightDto;
import com.flo.tennis.core.dto.JoueurDto;
import com.flo.tennis.core.dto.TournoiDto;
import com.flo.tennis.core.entity.Epreuve;
import com.flo.tennis.core.entity.Joueur;
import com.flo.tennis.core.entity.Tournoi;
import com.flo.tennis.core.repository.EpreuveRepositoryImpl;
import com.flo.tennis.core.repository.TournoiRepositoryImpl;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashSet;

public class EpreuveService {
    private EpreuveRepositoryImpl epreuveRepository;

    public EpreuveService(){
        this.epreuveRepository = new EpreuveRepositoryImpl();
    }


    public EpreuveFullDto getEpreuveDetaillee(Long id){
        Session session=null;
        Transaction tx =null;
        Epreuve epreuve=null;
        EpreuveFullDto dto =null;

        try {

            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            epreuve=epreuveRepository.getById(id);

            dto = new EpreuveFullDto();
            dto.setId(epreuve.getId());
            dto.setAnnee(epreuve.getAnnee());
            dto.setTypeEpreuve(epreuve.getTypeEpreuve());
            TournoiDto tournoiDto=new TournoiDto();
            tournoiDto.setId(epreuve.getTournoi().getId());
            tournoiDto.setNom(epreuve.getTournoi().getNom());
            tournoiDto.setCode(epreuve.getTournoi().getCode());
            dto.setTournoi(tournoiDto);

            dto.setParticipants(new HashSet<>());
            for(Joueur joueur : epreuve.getParticipants()){
                final JoueurDto joueurDto=new JoueurDto();
                joueurDto.setId(joueur.getId());
                joueurDto.setNom(joueur.getNom());
                joueurDto.setPrenom(joueur.getPrenom());
                joueurDto.setSexe(joueur.getSexe());
                dto.getParticipants().add(joueurDto);
            }

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

    public EpreuveLightDto getEpreuveByIdSansTournoi(Long id){
        Session session=null;
        Transaction tx =null;
        Epreuve epreuve=null;
        EpreuveLightDto dto =null;

        try {

            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            epreuve=epreuveRepository.getById(id);
            tx.commit();
            dto = new EpreuveLightDto();
            dto.setId(epreuve.getId());
            dto.setAnnee(epreuve.getAnnee());
            dto.setTypeEpreuve(epreuve.getTypeEpreuve());

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
